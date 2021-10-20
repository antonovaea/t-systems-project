package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.service.CatalogFilter;
import project.spaceshop.service.api.CategoryService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.ProductService;
import project.spaceshop.service.api.UserService;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CatalogFilter catalogFilter;

    @Autowired
    public AdminController(UserService userService, OrderService orderService, ProductService productService, CategoryService categoryService, CatalogFilter catalogFilter) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.catalogFilter = catalogFilter;
    }

    @GetMapping(value = "/admins")
    public String adminAccount(Model model) {
        return "admins";
    }

    @GetMapping(value = "/admins/orders")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.findAllOrder());
        return "adminOrders";
    }

    @GetMapping(value = "admins/orders/status")
    public String changeOrderStatus(@RequestParam(name = "orderStatus") String orderStatus,
                                    @RequestParam(name = "orderId") int orderId) {
        orderService.changeOrderStatusById(orderId, orderStatus);
        return "redirect:/admins/orders";
    }

    @GetMapping(value = "admins/products")
    public String adminProductsPage(){
        return "adminProductPage";
    }


}
