package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.entity.Product;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.service.CatalogFilter;
import project.spaceshop.service.api.CategoryService;
import project.spaceshop.service.api.ProductService;
import project.spaceshop.util.ImageUtil;

@Controller
@RequestMapping(value = "/home")
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductService productService;

    private final CategoryService categoryService;

    private final CatalogFilter catalogFilter;


    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService, CategoryService categoryService, CatalogFilter catalogFilter) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.categoryService = categoryService;
        this.catalogFilter = catalogFilter;
    }

    @GetMapping(value = "/catalog")
    public String getProductList(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        model.addAttribute("products", productRepository.findAll(PageRequest.of(page, 6)).getContent());
        model.addAttribute("imgUtil", new ImageUtil());
        return "main";
    }

    @GetMapping(value = "/catalog/filter")
    public String filter(Model model, @RequestParam(name = "idCategory", required = false) Integer idCategory, int id){
        Product product = productService.findProductById(id);
        catalogFilter.setIdCategory(idCategory);
        model.addAttribute("product", product);
        model.addAttribute("products", productService.filter(catalogFilter.getIdCategory()));
        model.addAttribute("imgUtil", new ImageUtil());
        return "productsByCategory";
    }

    @GetMapping(value = "/catalog/{id}")
    public String getProduct(Model model, @PathVariable("id") int id) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("imgUtil", new ImageUtil());
        return "details";
    }

}
