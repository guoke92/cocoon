package io.cocoon.mongodb.dao;

import io.cocoon.mongodb.bean.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PersonRepository extends MongoRepository<Person, String> {
}
