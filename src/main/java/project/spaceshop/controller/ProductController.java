package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.entity.Product;
import project.spaceshop.service.api.CategoryService;
import project.spaceshop.service.api.ProductService;

@Controller
@RequestMapping(value = "/home")
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/catalog")
    public String getProductList(Model model){
        model.addAttribute("products", productService.findAllProducts());
        return "products";
    }

    @GetMapping(value = "/catalog/{id}")
    public String getProduct(Model model, @PathVariable("id") int id){
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "product";
    }

}
