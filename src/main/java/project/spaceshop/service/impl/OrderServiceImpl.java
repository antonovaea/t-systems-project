package project.spaceshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.dto.converter.ConverterBasketProduct;
import project.spaceshop.entity.*;
import project.spaceshop.entity.enums.OrderStatusEnum;
import project.spaceshop.entity.enums.PaymentMethodEnum;
import project.spaceshop.entity.enums.PaymentStatusEnum;
import project.spaceshop.repository.OrderRepository;
import project.spaceshop.repository.ProductInOrderRepository;
import project.spaceshop.repository.TopCategoryRepository;
import project.spaceshop.service.api.*;

import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final ProductService productService;

    private final TopCategoryRepository topCategoryRepository;

    private final TopCategoryService topCategoryService;

    private final OrderRepository orderRepository;

    private final UserService userService;

    private final BasketProductServiceImpl basketProductService;

    private final AddressService addressService;

    private final ProductInOrderService productInOrderService;

    private final EmailService emailService;

    @Autowired
    public OrderServiceImpl(ProductService productService, TopCategoryRepository topCategoryRepository, TopCategoryService topCategoryService, OrderRepository orderRepository, UserService userService, BasketProductServiceImpl basketProductService, AddressService addressService, ConverterBasketProduct converterBasketProduct, ProductInOrderRepository productInOrderRepository, ProductInOrderService productInOrderService, EmailService emailService) {
        this.productService = productService;
        this.topCategoryRepository = topCategoryRepository;
        this.topCategoryService = topCategoryService;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.basketProductService = basketProductService;
        this.addressService = addressService;
        this.productInOrderService = productInOrderService;
        this.emailService = emailService;
    }

    @Override
    public Order saveOrder(int idAddress, String paymentType, List<BasketProductDto> basket) {
        UserAddress address = addressService.findAddressById(idAddress);
        Order order = new Order();
        order.setUser(address.getUser());
        order.setUserAddress(address.toStringForOrder());
        if (paymentType.equals("CASH")) {
            order.setPaymentMethod(PaymentMethodEnum.CASH.toString());
            order.setPaymentStatus(PaymentStatusEnum.AWAITING_PAYMENT.toString());
        } else {
            order.setPaymentMethod(PaymentMethodEnum.CARD.toString());
            order.setPaymentStatus(PaymentStatusEnum.PAID.toString());
        }
        order.setOrderStatus(OrderStatusEnum.AWAITING_SHIPMENT.toString());
        order.setDateOrder(new Date());
        int totalPrice = basketProductService.totalPrice(basket);
        order.setOrderPrice(totalPrice);
        for (BasketProductDto basketItem : basket) {
            Product product = basketProductService.convertBasketProductDtoToProduct(basketItem);
            ProductInOrder productInOrder = new ProductInOrder(order, product, basketItem.getAmount());
            product.setAmountInStock(product.getAmountInStock() - basketItem.getAmount());
            productService.saveProduct(product);
            TopCategory topCategory = topCategoryRepository.findTopCategoryByCategory_Id(product.getCategory().getId());
            topCategoryService.changeAmountOfSoldProducts(topCategory, basketItem.getAmount());
            order.getProducts().add(productInOrder);
            productInOrderService.saveProductInOrder(productInOrder);
        }
        log.info("order saved");
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findPaginatedOrderByUser(int pageNo, int pageSize){
        User user = userService.findUserFromSecurityContextHolder();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return orderRepository.findOrdersByUser(pageable, user);
    }

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public int getIncomeByDatePeriod(Date dateFirst, Date dateSecond) {
        try {
            List<Order> list = orderRepository.findOrderByDateOrderBetween(dateFirst, dateSecond);
            int income = 0;
            for (Order order : list){
                income += order.getOrderPrice();
            }
            log.info("income for the period is found = " + income);
            return income;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("income for the period is not found");
            return 0;
        }
    }

    @Override
    public boolean changeOrderStatusById(int idOrder, String orderStatus) {
        Order order = orderRepository.getById(idOrder);
        try {
            switch (orderStatus) {
                case "AWAITING_SHIPMENT":
                    order.setOrderStatus(OrderStatusEnum.AWAITING_SHIPMENT.toString());
                    break;
                case "SHIPPED":
                    order.setOrderStatus(OrderStatusEnum.SHIPPED.toString());
                    break;
                case "DELIVERED":
                    order.setOrderStatus(OrderStatusEnum.DELIVERED.toString());
                    break;
                case "DONE":
                    order.setOrderStatus(OrderStatusEnum.DONE.toString());
                    break;
                default:
                    break;
            }
            orderRepository.save(order);
            log.info("status for order with id " + idOrder + " changed");
            return true;
        } catch (Exception e) {
            log.info("status for order with id " + idOrder + " has not changed");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void sendEmailMessage(Order order, User user, int idAddress) {
        try {
            String message = "Hello, " + user.getUserName() + "!" + System.lineSeparator() + System.lineSeparator()
                    + "Your order ID = " + order.getId() + " is confirmed." + System.lineSeparator()
                    + "Delivery address: " + addressService.findAddressById(idAddress).toString() + System.lineSeparator() + System.lineSeparator()
                    + "Thank for you order!";

            emailService.sendSimpleMessage(message);
            log.info("email message for " + user.getEmail() + " has sent");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("email message for " + user.getEmail() + " has not sent");
        }

    }

}
