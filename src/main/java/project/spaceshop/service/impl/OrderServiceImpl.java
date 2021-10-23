package project.spaceshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.dto.converter.ConverterBasketProduct;
import project.spaceshop.entity.*;
import project.spaceshop.entity.enums.OrderStatusEnum;
import project.spaceshop.entity.enums.PaymentMethodEnum;
import project.spaceshop.entity.enums.PaymentStatusEnum;
import project.spaceshop.repository.OrderRepository;
import project.spaceshop.repository.ProductInOrderRepository;
import project.spaceshop.service.api.AddressService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;

    private final BasketProductServiceImpl basketProductService;

    private final AddressService addressService;

    private final ConverterBasketProduct converterBasketProduct;

    private final ProductInOrderRepository productInOrderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, BasketProductServiceImpl basketProductService, AddressService addressService, ConverterBasketProduct converterBasketProduct, ProductInOrderRepository productInOrderRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.basketProductService = basketProductService;
        this.addressService = addressService;
        this.converterBasketProduct = converterBasketProduct;
        this.productInOrderRepository = productInOrderRepository;
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
            Product product = converterBasketProduct.fromBasketProductDtoToProduct(basketItem);
            ProductInOrder productInOrder = new ProductInOrder(order, product, basketItem.getAmount());
//            productInOrderRepository.save(productInOrder);
            order.getProducts().add(productInOrder);
        }
//        userService.saveUser(user);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByUser() {
        User user = userService.findUserFromSecurityContextHolder();
        List<Order> list = orderRepository.findOrdersByUser(user);
        return list;
    }

    @Override
    public List<Order> findAllOrder() {
        List<Order> list = orderRepository.findAll();
        return list;
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
