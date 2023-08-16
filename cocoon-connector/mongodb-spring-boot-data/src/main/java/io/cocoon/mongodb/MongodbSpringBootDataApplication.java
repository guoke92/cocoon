package io.cocoon.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cocoon.mongodb.service.MongodbService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MongodbSpringBootDataApplication {
    public static void main(String[] args) throws JsonProcessingException {
        ConfigurableApplicationContext context = SpringApplication.run(MongodbSpringBootDataApplication.class, args);

        context.getBean(MongodbService.class).test();
    }
}