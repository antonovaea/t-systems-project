package project.spaceshop.service.impl;

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
import project.spaceshop.service.api.AddressService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.ProductInOrderService;
import project.spaceshop.service.api.UserService;

import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final TopCategoryRepository topCategoryRepository;

    private final OrderRepository orderRepository;

    private final UserService userService;

    private final BasketProductServiceImpl basketProductService;

    private final AddressService addressService;

    private final ProductInOrderService productInOrderService;

    @Autowired
    public OrderServiceImpl(TopCategoryRepository topCategoryRepository, OrderRepository orderRepository, UserService userService, BasketProductServiceImpl basketProductService, AddressService addressService, ConverterBasketProduct converterBasketProduct, ProductInOrderRepository productInOrderRepository, ProductInOrderService productInOrderService) {
        this.topCategoryRepository = topCategoryRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.basketProductService = basketProductService;
        this.addressService = addressService;
        this.productInOrderService = productInOrderService;
    }

    @Override
    public Order saveOrder(int idAddress, String paymentType, List<BasketProductDto> basket) {
        User user = userService.findUserFromSecurityContextHolder();
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
            TopCategory topCategory = new TopCategory(product.getCategory(), basketItem.getAmount());
            order.getProducts().add(productInOrder);
            productInOrderService.saveProductInOrder(productInOrder);
            topCategoryRepository.save(topCategory);
        }
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
    public List<Order> findByDatePeriod(Date dateFirst, Date dateSecond) {
        return orderRepository.findOrderByDateOrderBetween(dateFirst, dateSecond);
    }

    @Override
    public boolean changeOrderStatusById(int idOrder, String orderStatus) {
        Order order = orderRepository.getById(idOrder);
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
        return true;
    }

}
