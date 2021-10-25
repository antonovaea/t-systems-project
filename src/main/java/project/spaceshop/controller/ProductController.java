package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.entity.Product;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.service.CatalogFilter;
import project.spaceshop.service.api.CategoryService;
import project.spaceshop.service.api.ProductService;
import project.spaceshop.util.ImageUtil;

import java.util.List;

@Controller
@RequestMapping(value = "/home")
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductService productService;

    private final CategoryService categoryService;

    private final CatalogFilter catalogFilter;

    private final static int PAGE_SIZE = 6;


    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService, CategoryService categoryService, CatalogFilter catalogFilter) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.categoryService = categoryService;
        this.catalogFilter = catalogFilter;
    }

    @GetMapping(value = "/catalog/page/{pageNo}")
    public String getProductList(@PathVariable("pageNo") int pageNo, Model model) {
        Page<Product> page = productService.findPaginated(pageNo, PAGE_SIZE);
        List<Product> list = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("products", list);
        model.addAttribute("imgUtil", new ImageUtil());
        return "main";
    }

    @GetMapping(value = "/catalog/filter")
    public String filter(Model model, @RequestParam(name = "idCategory", required = false) Integer idCategory){
        catalogFilter.setIdCategory(idCategory);
        model.addAttribute("products", productService.filter(catalogFilter.getIdCategory()));
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("categories", categoryService.findAll());
        return "main";
    }

    @GetMapping(value = "/catalog/{id}")
    public String getProduct(Model model, @PathVariable("id") int id) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("imgUtil", new ImageUtil());
        return "details";
    }

}
