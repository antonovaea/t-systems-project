package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.service.BasketBean;
import project.spaceshop.service.api.BasketProductService;

@Controller
//@Secured({"USER"})
@RequestMapping(value = "/home/basket")
public class BasketController {

    private final BasketProductService basketProductService;
    private final BasketBean basketBean;

    @Autowired
    public BasketController(BasketProductService basketProductService, BasketBean basketBean) {
        this.basketProductService = basketProductService;
        this.basketBean = basketBean;
    }


    @GetMapping(value = "")
    public String basket(Model model) {
        model.addAttribute("basket", basketBean.getBasket());
        return "basket";
    }

    @PostMapping(value = "/add/{id}")
    @ResponseBody
    public boolean addToBasketById(BasketProductDto basketProductDto, @PathVariable("id") int id) {
        return basketProductService.addToBasket(basketProductDto, basketBean.getBasket());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteProductsById(final @PathVariable("id") int id) {
        return basketProductService.deleteFromBasketById(id, basketBean.getBasket());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteProducts() {
        basketBean.setBasket(basketProductService.deleteFromBasket(basketBean.getBasket()));
        return true;
    }

    @RequestMapping(value = "/count/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String countProductsInBasketById(final @PathVariable("id") int id) {
        return basketProductService.countProductsInBasketById(id, basketBean.getBasket()).toString();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countProductsInBasket() {
        return basketProductService.countProductsInBasket(basketBean.getBasket()).toString();
    }
}
