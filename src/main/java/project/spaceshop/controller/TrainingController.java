package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.spaceshop.entity.Product;
import project.spaceshop.service.api.ProductService;
import project.spaceshop.util.ImageUtil;

import java.util.List;

@Controller
public class TrainingController {

    ProductService productService;

    @Autowired
    public TrainingController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/home/categorytraining/{idCategory}")
    public String getProductsByCategory(Model model, @PathVariable int idCategory){
        model.addAttribute("products", productService.findProductByCategory(idCategory));
        return "categorytraining";
    }
}
