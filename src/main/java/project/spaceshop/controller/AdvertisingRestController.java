package project.spaceshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.spaceshop.dto.TopCategoryDto;
import project.spaceshop.entity.TopCategory;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.TopCategoryService;

import java.util.List;

@RestController
public class AdvertisingRestController {

    private final TopCategoryService topCategoryService;

    private final OrderService orderService;

    public AdvertisingRestController(TopCategoryService topCategoryService, OrderService orderService) {
        this.topCategoryService = topCategoryService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/home/advertising/top")
    public List<TopCategoryDto> getTopCategories(){
        return topCategoryService.findAllTop();
    }

}
