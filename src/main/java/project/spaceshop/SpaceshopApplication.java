package project.spaceshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("project.spaceshop.repository")
@EntityScan("project.spaceshop.entity")
@SpringBootApplication(scanBasePackages = "project.spaceshop")
public class SpaceshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceshopApplication.class, args);
    }

}
