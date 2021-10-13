package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.spaceshop.entity.Product;
import project.spaceshop.service.api.ProductService;
import project.spaceshop.util.ImageUtil;

@Controller
public class TrainingController {

    ProductService productService;

    @Autowired
    public TrainingController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/main")
    public String getProduct(Model model, int id){
        Product product = productService.findProductById(id);
        model.addAttribute("products", productService.findAllProducts());
        model.addAttribute("product", product);
        model.addAttribute("imgUtil", new ImageUtil());
        return "main";
    }
}
