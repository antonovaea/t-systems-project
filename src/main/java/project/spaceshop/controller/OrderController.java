package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.dto.converter.ConverterBasketProduct;
import project.spaceshop.entity.*;
import project.spaceshop.entity.enums.PaymentMethodEnum;
import project.spaceshop.repository.ProductInOrderRepository;
import project.spaceshop.service.BasketBean;
import project.spaceshop.service.api.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/home")
public class OrderController {

    private final OrderService orderService;
    private final BasketBean basketBean;
    private final UserService userService;
    private final BasketProductService basketProductService;
    private final ProductService productService;
    private final ProductInOrderRepository productInOrderRepository;
    private final ConverterBasketProduct converterBasketProduct;
    private final ProductInOrderService productInOrderService;

    @Autowired
    public OrderController(OrderService orderService, BasketBean basketBean, ProductService productService,
                           UserService userService, BasketProductService basketProductService, ProductInOrderRepository productInOrderRepository, ConverterBasketProduct converterBasketProduct, ProductInOrderService productInOrderService) {
        this.orderService = orderService;
        this.basketBean = basketBean;
        this.userService = userService;
        this.basketProductService = basketProductService;
        this.productService = productService;
        this.productInOrderRepository = productInOrderRepository;
        this.converterBasketProduct = converterBasketProduct;
        this.productInOrderService = productInOrderService;
    }

    @GetMapping(value = "/order")
    public String orderPage(Model model) {
        User user = userService.findUserFromSecurityContextHolder();
        Order order = new Order();
        order.setUser(user);
        List<UserAddress> userAddressList = user.getUserAddresses();
        model.addAttribute("user", user);
        model.addAttribute("addresses", userAddressList);
        model.addAttribute("count", basketProductService.countProductsInBasket(basketBean.getBasket()));
        model.addAttribute("totalPrice", basketProductService.totalPrice((basketBean.getBasket())));
        model.addAttribute("user", userService.findUserFromSecurityContextHolder());
        model.addAttribute("basket", basketBean.getBasket());
        model.addAttribute("CARD", PaymentMethodEnum.CARD.toString());
        model.addAttribute("CASH", PaymentMethodEnum.CASH.toString());
        return "order";
    }

    @GetMapping(value = "/order/pay")
    public String orderPay(@RequestParam(name = "idAddress") int idAddress,
                           @RequestParam(name = "paymentType") String paymentType, Order order) {
        User user = userService.findUserFromSecurityContextHolder();
        orderService.saveOrder(idAddress, paymentType, (basketBean.getBasket()));
//        for (BasketProductDto basketProductDto : basketBean.getBasket()) {
//            Product product = converterBasketProduct.fromBasketProductDtoToProduct(basketProductDto);
//            ProductInOrder productInOrder = new ProductInOrder();
//            productInOrder.setOrder(order);
//            productInOrder.setProduct(product);
//            productInOrder.setAmountInOrder(basketProductService.countProductsInBasketById(basketProductDto.getId(), basketBean.getBasket()));
//            productInOrderService.saveProductInOrder(productInOrder);
//        }
        basketBean.setBasket(new ArrayList<>());
        return "orderSuccess";
    }

    @GetMapping(value = "/account/history")
    public String orderHistory(Model model) {
        List<Order> list = orderService.findOrderByUser();
        for (Order order : list) {
            model.addAttribute("order", order);
            model.addAttribute("orders", list);
        }
        return "history";
    }

}
