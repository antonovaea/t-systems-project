package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.entity.Product;
import project.spaceshop.service.BasketBean;
import project.spaceshop.service.api.BasketProductService;
import project.spaceshop.service.api.ProductService;
import project.spaceshop.util.ImageDtoUtil;
import project.spaceshop.util.ImageUtil;

@Controller
//@Secured({"USER"})
@RequestMapping(value = "/home/basket")
public class BasketController {

    private final ProductService productService;
    private final BasketProductService basketProductService;
    private final BasketBean basketBean;

    @Autowired
    public BasketController(ProductService productService, BasketProductService basketProductService, BasketBean basketBean) {
        this.productService = productService;
        this.basketProductService = basketProductService;
        this.basketBean = basketBean;
    }


    @GetMapping(value = "")
    public String basket(Model model) {
        model.addAttribute("basket", basketBean.getBasket());
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("count", basketProductService.countProductsInBasket(basketBean.getBasket()));
        model.addAttribute("totalPrice", basketProductService.totalPrice(basketBean.getBasket()));
        return "basket";
    }

    @PostMapping(value = "/add/{id}")
    public String addToBasketById(@PathVariable("id") int id) {
        Product product = productService.findProductById(id);
        BasketProductDto basketProductDto = basketProductService.createBasketProductFromProduct(product);
        basketProductService.addToBasket(basketProductDto, basketBean.getBasket());
        return "redirect:/home/basket";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteProductsById(@PathVariable("id") int id) {
        basketProductService.deleteFromBasketById(id, basketBean.getBasket());
        return "redirect:/home/basket";
    }

    @PostMapping(value = "/delete")
    public String deleteProducts() {
        basketBean.setBasket(basketProductService.deleteFromBasket(basketBean.getBasket()));
        return "redirect:/home/basket";
    }

    @GetMapping(value = "/count/{id}")
    public String countProductsInBasketById(final @PathVariable("id") int id) {
        return basketProductService.countProductsInBasketById(id, basketBean.getBasket()).toString();
    }

    @GetMapping(value = "/count")
    public String countProductsInBasket() {
        return basketProductService.countProductsInBasket(basketBean.getBasket()).toString();
    }
}
