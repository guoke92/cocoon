package io.cocoon.mongodb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cocoon.mongodb.bean.Person;
import io.cocoon.mongodb.dao.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class MongodbService {

    @Autowired
    private PersonRepository personRepository;

    public void test() throws JsonProcessingException {
        log.info("------start----");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("----1-----");
        long count = personRepository.count();
        log.info("count = {}", count);
        stopWatch.stop();

        stopWatch.start("----2-----");
        personRepository.deleteAll();
        stopWatch.stop();


        stopWatch.start("----3-----");
        String jsonStr2 = "[{\"uid\": 4, \"name\": \"张三爹\", \"age\": 68},\n" +
                "        {\"uid\": 5, \"name\": \"李四爹\", \"age\": 69},\n" +
                "        {\"uid\": 6, \"name\": \"王二爹\", \"age\": 70}]";

        Person[] persons = new ObjectMapper().readValue(jsonStr2, Person[].class);
        personRepository.insert(Arrays.asList(persons));
        stopWatch.stop();

        stopWatch.start("----4-----");
        List<Person> all = personRepository.findAll();
        all.forEach(item -> System.out.println("findAll item = " + item.toString()));
        stopWatch.stop();

        count = personRepository.count();
        log.info("count = {}", count);

        log.info("stopWatch = {}", stopWatch.prettyPrint());
    }
}
