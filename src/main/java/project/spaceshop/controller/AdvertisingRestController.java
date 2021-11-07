package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.spaceshop.dto.TopCategoryDto;
import project.spaceshop.mqactive.ActiveMQProducer;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.TopCategoryService;

import java.util.List;

@RestController
public class AdvertisingRestController {

    private final TopCategoryService topCategoryService;

    private final OrderService orderService;

    private final ActiveMQProducer activeMQProducer;

    @Autowired
    public AdvertisingRestController(TopCategoryService topCategoryService, OrderService orderService, ActiveMQProducer activeMQProducer) {
        this.topCategoryService = topCategoryService;
        this.orderService = orderService;
        this.activeMQProducer = activeMQProducer;
    }

    @GetMapping(value = "/home/advertising/top")
    @ResponseBody
    public List<TopCategoryDto> getTopCategories(){
//        activeMQProducer.send(topCategoryService.findAllTop().toString());
        return topCategoryService.findAllTop();
    }

}
