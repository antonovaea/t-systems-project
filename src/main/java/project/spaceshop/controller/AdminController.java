package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.dto.CategoryDto;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.Product;
import project.spaceshop.repository.CategoryRepository;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.service.CatalogFilter;
import project.spaceshop.service.api.CategoryService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.ProductService;
import project.spaceshop.service.api.UserService;
import project.spaceshop.util.ImageUtil;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CatalogFilter catalogFilter;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public AdminController(UserService userService, OrderService orderService, ProductService productService, CategoryService categoryService, CatalogFilter catalogFilter, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.catalogFilter = catalogFilter;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/admin")
    public String adminAccount(Model model) {
        return "admins";
    }

    @GetMapping(value = "/admin/order")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.findAllOrder());
        return "adminOrders";
    }

    @GetMapping(value = "admin/order/status")
    public String changeOrderStatus(@RequestParam(name = "orderStatus") String orderStatus,
                                    @RequestParam(name = "orderId") int orderId) {
        orderService.changeOrderStatusById(orderId, orderStatus);
        return "redirect:/admin/order";
    }

    @GetMapping(value = "/admin/product")
    public String adminProductsPage(){
        return "adminProductPage";
    }

    @GetMapping(value = "/admin/category/new")
    public String createCategory(Model model){
        CategoryDto category = new CategoryDto();
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("categoryName", category.getCategoryName());
        return "newCategory";
    }

    @PostMapping(value = "/admin/category/new-process")
    public String createCategoryProcess(CategoryDto category){
        categoryService.saveCategory(category);
        return "redirect:/admin/category/new";
    }

    @PostMapping(value = "/admin/existing/product/{idCategory}")
    public String deleteCategoryById(@PathVariable("idCategory") int id){
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/category/new";
    }

    @GetMapping(value = "/admin/existing/product")
    public String existingProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("imgUtil", new ImageUtil());
        return "existingProducts";
    }

    @GetMapping(value = "/admin/product/new")
    public String createProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("imgUtil", new ImageUtil());
        return "newProduct";
    }

    @PostMapping(value = "/admin/product/new-process")
    public String createProductProcess(Product product){
        productService.saveProduct(product);
        return "redirect:/admin/product/new";
    }




}
