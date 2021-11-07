package project.spaceshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@EnableJpaRepositories("project.spaceshop.repository")
@EnableScheduling
@EntityScan("project.spaceshop.entity")
@SpringBootApplication(scanBasePackages = "project.spaceshop")
public class SpaceshopApplication {
    public static void main(String[] args) throws IOException, TimeoutException {
        SpringApplication.run(SpaceshopApplication.class, args);
    }
}
