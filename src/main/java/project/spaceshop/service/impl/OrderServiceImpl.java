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
import project.spaceshop.exeption.productAlreadySoldException;
import project.spaceshop.repository.OrderRepository;
import project.spaceshop.repository.ProductInOrderRepository;
import project.spaceshop.repository.TopCategoryRepository;
import project.spaceshop.service.api.*;

import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * Injected by spring ProductService bean
     */
    private final ProductService productService;

    /**
     * Injected by spring TopCaegoryRepository bean
     */
    private final TopCategoryRepository topCategoryRepository;

    /**
     * Injected by spring TopCategoryService bean
     */
    private final TopCategoryService topCategoryService;

    /**
     * Injected by spring OrderRepository bean
     */
    private final OrderRepository orderRepository;

    /**
     * Injected by spring userService bean
     */
    private final UserService userService;

    /**
     * Injected by spring BasketProductService bean
     */
    private final BasketProductServiceImpl basketProductService;

    /**
     * Injected by spring AddressService bean
     */
    private final AddressService addressService;

    /**
     * Injected by spring ProductInOrderService bean
     */
    private final ProductInOrderService productInOrderService;

    /**
     * Injected by spring EmailService bean
     */
    private final EmailService emailService;

    /**
     * Injected constructor.
     *
     * @param productService        that must be injected.
     * @param topCategoryRepository that must be injected.
     * @param topCategoryService    that must be injected.
     * @param orderRepository       that must be injected.
     * @param userService           that must be injected.
     * @param basketProductService  that must be injected.
     * @param addressService        that must be injected.
     * @param productInOrderService that must be injected.
     * @param emailService          that must be injected.
     */
    @Autowired
    public OrderServiceImpl(ProductService productService, TopCategoryRepository topCategoryRepository, TopCategoryService topCategoryService, OrderRepository orderRepository, UserService userService, BasketProductServiceImpl basketProductService, AddressService addressService, ProductInOrderService productInOrderService, EmailService emailService) {
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

    /**
     * Method adds order to database.
     *
     * @param idAddress   param id of shipping address.
     * @param paymentType type of selected payment method, may be card or cash.
     * @param basket      all ordered products.
     * @return saved order.
     */
    @Override
    public Order saveOrder(int idAddress, String paymentType, List<BasketProductDto> basket) throws productAlreadySoldException {
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
                if (product.getAmountInStock() != 0){
                ProductInOrder productInOrder = new ProductInOrder(order, product, basketItem.getAmount());
                product.setAmountInStock(product.getAmountInStock() - basketItem.getAmount());
                productService.saveProduct(product);
                TopCategory topCategory = topCategoryRepository.findTopCategoryByCategory_Id(product.getCategory().getId());
                topCategoryService.changeAmountOfSoldProducts(topCategory, basketItem.getAmount());
                order.getProducts().add(productInOrder);
                productInOrderService.saveProductInOrder(productInOrder);
                log.info("order saved");
                return orderRepository.save(order);
            } else {
                    throw new productAlreadySoldException();
                }
        }
        return order;
    }

    /**
     * Method divides list of existing orders on pages.
     *
     * @param pageNo   page number.
     * @param pageSize number of showing orders on one page.
     * @return pages with existing orders.
     */
    @Override
    public Page<Order> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.orderRepository.findAll(pageable);
    }

    /**
     * Method divides list of existing orders of current user on pages.
     *
     * @param pageNo   page number.
     * @param pageSize number of showing orders on one page.
     * @return pages with existing and previous orders of current user.
     */
    @Override
    public Page<Order> findPaginatedOrderByUser(int pageNo, int pageSize) {
        User user = userService.findUserFromSecurityContextHolder();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return orderRepository.findOrdersByUser(pageable, user);
    }

    /**
     * Method looks for all existing orders.
     *
     * @return all orders found in database.
     */
    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    /**
     * Method calculates total income for the period of time between two selected dates.
     *
     * @param dateFirst  start period date.
     * @param dateSecond end period date.
     * @return total income obtained in the selected period of time in Integer form.
     */
    @Override
    public int getIncomeByDatePeriod(Date dateFirst, Date dateSecond) {
        try {
            List<Order> list = orderRepository.findOrderByDateOrderBetween(dateFirst, dateSecond);
            int income = 0;
            for (Order order : list) {
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

    /**
     * Method changes order status in database by id.
     *
     * @param idOrder     param id of order which status must be changed.
     * @param orderStatus new order status that we want to set up.
     * @return true if status successfully changed or false if not.
     */
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

    /**
     * Method sends email message with order info to user email address after placing order.
     *
     * @param order     placed order.
     * @param user      user which placed new order.
     * @param idAddress param id or user shipping address.
     */
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
