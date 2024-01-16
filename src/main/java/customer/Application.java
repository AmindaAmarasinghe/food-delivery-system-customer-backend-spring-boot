package customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;


@EntityScan("customer.entity")

@EnableJpaRepositories("customer.repository")

@ComponentScan(basePackages = {"customer"})

@SpringBootApplication

@EnableKafka

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
