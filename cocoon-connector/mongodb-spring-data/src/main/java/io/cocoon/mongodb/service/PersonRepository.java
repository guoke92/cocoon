package io.cocoon.mongodb.service;

import io.cocoon.mongodb.bean.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PersonRepository extends MongoRepository<Person, String> {
}
