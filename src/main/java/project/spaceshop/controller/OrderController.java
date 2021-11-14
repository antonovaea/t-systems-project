package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.dto.converter.ConverterBasketProduct;
import project.spaceshop.entity.*;
import project.spaceshop.entity.enums.PaymentMethodEnum;
import project.spaceshop.mq.RabbitMqSender;
import project.spaceshop.repository.ProductInOrderRepository;
import project.spaceshop.service.BasketBean;
import project.spaceshop.service.api.*;
import project.spaceshop.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/home")
public class OrderController {

    private final TopCategoryService topCategoryService;
    private final RabbitMqSender rabbitMqSender;
    private final OrderService orderService;
    private final BasketBean basketBean;
    private final UserService userService;
    private final BasketProductService basketProductService;
    private final ProductInOrderService productInOrderService;
    private final ProductService productService;
    private final static int PAGE_SIZE = 6;

    @Autowired
    public OrderController(OrderService orderService, BasketBean basketBean, ProductService productService,
                           UserService userService, BasketProductService basketProductService, ProductInOrderRepository productInOrderRepository, ConverterBasketProduct converterBasketProduct, ProductInOrderService productInOrderService, TopCategoryService topCategoryService, RabbitMqSender rabbitMqSender, ProductInOrderService productInOrderService1, ProductService productService1) {
        this.orderService = orderService;
        this.basketBean = basketBean;
        this.userService = userService;
        this.basketProductService = basketProductService;
        this.topCategoryService = topCategoryService;
        this.rabbitMqSender = rabbitMqSender;
        this.productInOrderService = productInOrderService1;
        this.productService = productService1;
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
        if (basketBean.getBasket().isEmpty()){
            return "emptyOrderError";
        } else{
            return "order";
        }

    }

    @GetMapping(value = "/order/pay")
    public String orderPay(@RequestParam(name = "idAddress") int idAddress,
                           @RequestParam(name = "paymentType") String paymentType) {
        orderService.saveOrder(idAddress, paymentType, (basketBean.getBasket()));
        basketBean.setBasket(new ArrayList<>());
        rabbitMqSender.send("update");
        return "orderSuccess";
    }

    @GetMapping(value = "/account/history/page/{pageNo}")
    public String orderHistory(@PathVariable("pageNo") int pageNo, Model model) {
        Page<Order> page = orderService.findPaginatedOrderByUser(pageNo, PAGE_SIZE);
        List<Order> list = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        for (Order order : list) {
            model.addAttribute("order", order);
            model.addAttribute("orders", list);
        }
        return "history";
    }

    @GetMapping(value = "/account/history/{idOrder}")
    public String productsInOrderHistory(Model model, @PathVariable("idOrder") int idOrder){
        List<ProductInOrder> productInOrderList = productInOrderService.findAllByOrderId(idOrder);
        List<Product> products = new ArrayList<>();
        for (ProductInOrder productInOrder : productInOrderList){
            products.add(productService.findProductById(productInOrder.getProduct().getId()));
        }
        model.addAttribute("products", products);
        model.addAttribute("imgUtil", new ImageUtil());
        return "productHistory";
    }

}
