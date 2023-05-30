package io.cocoon.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.cocoon.mongodb.bean.Person;
import io.cocoon.mongodb.service.PersonRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

import java.util.Arrays;
import java.util.List;

public class MongodbSpringService {

    public static void main(String[] args) throws JsonProcessingException {

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "spring");
        MongoRepositoryFactory mongoRepositoryFactory = new MongoRepositoryFactory(mongoTemplate);
        PersonRepository repository = mongoRepositoryFactory.getRepository(PersonRepository.class);

        repository.deleteAll();

        String jsonStr2 = "[{\"uid\": 4, \"name\": \"张三爹\", \"age\": 68},\n" +
                "        {\"uid\": 5, \"name\": \"李四爹\", \"age\": 69},\n" +
                "        {\"uid\": 6, \"name\": \"王二爹\", \"age\": 70}]";

        Person[] persons = new ObjectMapper().readValue(jsonStr2, Person[].class);
        repository.insert(Arrays.asList(persons));

        List<Person> all = repository.findAll();
        all.forEach(item -> System.out.println("findAll item = " + item.toString()));

        long count = repository.count();
        System.out.println("count = " + count);

    }
}
