package project.spaceshop.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.spaceshop.dto.TopCategoryDto;
import project.spaceshop.mq.RabbitMqSender;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.TopCategoryService;

import java.util.List;

@RestController
public class AdvertisingRestController {

    private final TopCategoryService topCategoryService;

    private final OrderService orderService;

    private final RabbitMqSender rabbitMqSender;

    @Autowired
    public AdvertisingRestController(TopCategoryService topCategoryService, OrderService orderService, RabbitMqSender rabbitMqSender) {
        this.topCategoryService = topCategoryService;
        this.orderService = orderService;
        this.rabbitMqSender = rabbitMqSender;
    }

    @GetMapping(value = "/home/advertising/top")
    @ResponseBody
    public List<TopCategoryDto> getTopCategories(){
//        rabbitMqSender.send(topCategoryService.findAllTop());
        return topCategoryService.findAllTop();
    }

}
