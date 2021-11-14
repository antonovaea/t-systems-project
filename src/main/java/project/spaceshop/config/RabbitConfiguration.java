package project.spaceshop.config;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.client.*;
import com.rabbitmq.jms.util.RMQJMSException;
import com.rabbitmq.jms.util.RMQJMSSecurityException;
import com.rabbitmq.jms.util.WhiteListObjectInputStream;
import javax.jms.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitConfiguration {
    private static final Logger log = LoggerFactory.getLogger(RabbitConfiguration.class);
    @Bean
    public ConnectionFactory connectionFactory() {
        try {
            ConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
            return connectionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("fail connection to rabbitMQ");
            return connectionFactory();
        }
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(new MessageConverter() {
            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                messageProperties.setContentType("text/plain");
                messageProperties.setContentEncoding("UTF-8");
                Message message = new Message(JSON.toJSONBytes(object), messageProperties);
                return message;
            }
            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return message;
            }
        });

        return rabbitTemplate;
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("advertising.queue");
    }

}





