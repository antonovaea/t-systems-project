package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.spaceshop.entity.Order;
import project.spaceshop.service.BasketBean;
import project.spaceshop.service.api.BasketProductService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.ProductService;
import project.spaceshop.service.api.UserService;

import java.util.ArrayList;

@Controller
@Secured({"USER"})
@RequestMapping(value = "/order")
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

    @GetMapping(value = "")
    public String orderPage(Model model) {
        model.addAttribute("totalPriceForPay", basketProductService.totalPrice((basketBean.getBasket())));
        model.addAttribute("user", userService.findUserFromSecurityContextHolder());
        model.addAttribute("basket", basketBean.getBasket());
        return "order";
    }

    @RequestMapping(value = "/pay")
    public String orderPay(@RequestParam(name = "idAddress") int idAddress,
                           @RequestParam(name = "paymentType") String paymentType) {
        Order order = orderService.saveOrder(idAddress, paymentType, (basketBean.getBasket()));
        basketBean.setBasket(new ArrayList<>());
        return "payment";
    }

    @GetMapping(value = "/repeat/{id}")
    public String orderRepeat(final @PathVariable("id") int id) {
        basketBean.setBasket(orderService.repeatOrderById(id));
        return "redirect:/order";
    }
}
