package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.entity.Product;
import project.spaceshop.service.api.ProductService;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

//    @GetMapping(value = "/index")
//    public String index(){
//        return "index";
//    }

    @GetMapping(value = "/catalog")
    public String getProductList(Model model){
        model.addAttribute("products", productService.findAllProducts());
        return "products";
    }

//    @GetMapping(value = "/products")
//    public List<Product> getProducts(){
//        return productService.findAllProducts();
//    }

    @GetMapping(value = "/catalog/{id}")
    public String getProduct(Model model, @PathVariable("id") int id){
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "product";
    }

//    @GetMapping(value ="/catalog/{id}")
//    public String getProductPage(Model model, @PathVariable("id") int id){
//        model.addAttribute("product", productService.findProductById(id, false));
//        return "product";
//    }



}
