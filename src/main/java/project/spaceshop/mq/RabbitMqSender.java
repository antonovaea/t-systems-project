package project.spaceshop.mq;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.SpaceshopApplication;
import project.spaceshop.dto.TopCategoryDto;

import java.util.List;


@Service
public class RabbitMqSender {
    private static final Logger log = LoggerFactory.getLogger(RabbitMqSender.class);
    private RabbitTemplate rabbitTemplate;
    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String msg){
        rabbitTemplate.convertAndSend(SpaceshopApplication.EXCHANGE_NAME, SpaceshopApplication.ROUTING_KEY, msg);
        log.info("message with update info sent");
    }

}
