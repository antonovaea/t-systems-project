package project.spaceshop;

//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@EnableJpaRepositories("project.spaceshop.repository")
@EntityScan("project.spaceshop.entity")
@SpringBootApplication(scanBasePackages = "project.spaceshop")
public class SpaceshopApplication {

    public static void main(String[] args) throws IOException, TimeoutException {
        SpringApplication.run(SpaceshopApplication.class, args);
//        ConnectionFactory factory = new ConnectionFactory();
//
//        try (Connection connection = factory.newConnection()) {
//            Channel channel = connection.createChannel();
//            channel.queueDeclare("first-queue", false, false, false, null);
//
//            String message = "is this the matrix?";
//            channel.basicPublish("", "first-queue", false, null, message.getBytes());
//            System.out.println("message has been sent");
//        }
    }

}
