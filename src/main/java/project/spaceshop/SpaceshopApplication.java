package project.spaceshop;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@EnableJpaRepositories("project.spaceshop.repository")
@EnableScheduling
@EntityScan("project.spaceshop.entity")
@SpringBootApplication(scanBasePackages = "project.spaceshop")
public class SpaceshopApplication {
    @Bean
    public Queue advertisingQueue() {
        return new Queue("new");
    }

    public static final String EXCHANGE_NAME = "exchange1";
    public static final String DEFAULT_PARSING_QUEUE = "advertising.queue";
    public static final String ROUTING_KEY = "routKey";

    public static void main(String[] args) throws IOException, TimeoutException {
        SpringApplication.run(SpaceshopApplication.class, args);
    }


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue defaultQueue(){
        return new Queue(DEFAULT_PARSING_QUEUE);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(defaultQueue()).to(topicExchange()).with(ROUTING_KEY);
    }


}
