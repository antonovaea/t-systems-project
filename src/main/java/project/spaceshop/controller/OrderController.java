package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.entity.Order;
import project.spaceshop.entity.User;
import project.spaceshop.entity.UserAddress;
import project.spaceshop.entity.enums.PaymentMethodEnum;
import project.spaceshop.service.BasketBean;
import project.spaceshop.service.api.BasketProductService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.ProductService;
import project.spaceshop.service.api.UserService;

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

    @Autowired
    public OrderController(OrderService orderService, BasketBean basketBean, ProductService productService,
                           UserService userService, BasketProductService basketProductService) {
        this.orderService = orderService;
        this.basketBean = basketBean;
        this.userService = userService;
        this.basketProductService = basketProductService;
        this.productService = productService;
    }

    @GetMapping(value = "/order")
    public String orderPage(Model model) {
        User user = userService.findUserFromSecurityContextHolder();
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
                           @RequestParam(name = "paymentType") String paymentType) {
        orderService.saveOrder(idAddress, paymentType, (basketBean.getBasket()));
        basketBean.setBasket(new ArrayList<>());
        return "orderSuccess";
    }

    @GetMapping(value = "/account/history")
    public String orderHistory(Model model){
        List<Order> list = orderService.findOrderByUser();
        for (Order order : list){
            model.addAttribute("order", order);
            model.addAttribute("orders", list);
        }
        return "history";
    }

}
