package io.cocoon.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cocoon.mongodb.bean.Person;
import io.cocoon.mongodb.dao.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MongodbSpringBootDataApplication {
    public static void main(String[] args) throws JsonProcessingException {
        ConfigurableApplicationContext context = SpringApplication.run(MongodbSpringBootDataApplication.class, args);
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        long count = personRepository.count();
        System.out.println("count = " + count);

        personRepository.deleteAll();

        String jsonStr2 = "[{\"uid\": 4, \"name\": \"张三爹\", \"age\": 68},\n" +
                "        {\"uid\": 5, \"name\": \"李四爹\", \"age\": 69},\n" +
                "        {\"uid\": 6, \"name\": \"王二爹\", \"age\": 70}]";

        Person[] persons = new ObjectMapper().readValue(jsonStr2, Person[].class);
        personRepository.insert(Arrays.asList(persons));

        List<Person> all = personRepository.findAll();
        all.forEach(item -> System.out.println("findAll item = " + item.toString()));

        count = personRepository.count();
        System.out.println("count = " + count);
    }
}