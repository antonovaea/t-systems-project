package project.spaceshop.service.impl;

import org.springframework.stereotype.Service;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.entity.Order;
import project.spaceshop.entity.User;
import project.spaceshop.entity.UserAddress;
import project.spaceshop.repository.OrderRepository;
import project.spaceshop.service.api.AddressService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.UserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;

    private final BasketProductServiceImpl basketProductService;

    private final AddressService addressService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, BasketProductServiceImpl basketProductService, AddressService addressService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.basketProductService = basketProductService;
        this.addressService = addressService;
    }

}
