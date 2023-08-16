package io.cocoon.mongodb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MongoJdbcService {

    public static void main(String[] args) throws JsonProcessingException {
        Logger logger = LoggerFactory.getLogger(MongoJdbcService.class);

        ConnectionString connectionString = new ConnectionString("mongodb://localhost");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase testdb = mongoClient.getDatabase("testdb");

        testdb.listCollectionNames().forEach((Block<? super String>) item -> logger.error("collection name: {}" , item));

        // 获取指定collection， 若不存在则在第一次存储数据时自动创建
        MongoCollection<Document> crud = testdb.getCollection("crud");

        crud.drop();
        // 创建指定collection, 可通过CreateCollectionOptions 类对collection进行配置
        testdb.createCollection("crud");

        String jsonStr = "[{\"uid\": 1, \"name\": \"张三\", \"age\": 28},\n" +
                "        {\"uid\": 2, \"name\": \"李四\", \"age\": 29},\n" +
                "        {\"uid\": 3, \"name\": \"王二\", \"age\": 30}]";
        BasicDBList basicDBList = (BasicDBList) JSON.parse(jsonStr);

        List<Document> documents = new ArrayList<>();
        for (Object object : basicDBList) {
            BasicDBObject basicDBObject = (BasicDBObject) object;
            Object uid = basicDBObject.get("uid");
            Object name = basicDBObject.get("name");
            Object age = basicDBObject.get("age");

            Document document = new Document();
            document.append("uid", uid).append("name", name).append("age", age);
            documents.add(document);
        }
        crud.insertMany(documents);

        String jsonStr2 = "[{\"uid\": 4, \"name\": \"张三爹\", \"age\": 68},\n" +
                "        {\"uid\": 5, \"name\": \"李四爹\", \"age\": 69},\n" +
                "        {\"uid\": 6, \"name\": \"王二爹\", \"age\": 70}]";

        Person[] persons = new ObjectMapper().readValue(jsonStr2, Person[].class);
        crud.insertMany(Stream.of(persons).map(Person::toDocument).collect(Collectors.toList()));

        for (Document document : crud.find()) {
            logger.error("after init, find crud, {}" , document.toJson());
        }

        BasicDBObject basicDBObject = new BasicDBObject("age", new BasicDBObject("$gt", 29));
        for (Document document : crud.find(basicDBObject)) {
            logger.error("query age > 29 , {}" , document.toJson());
        }


        BasicDBObject condition = new BasicDBObject("name", "张三");
        Bson update = Updates.set("age", 17);
        long modifiedCount = crud.updateOne(condition, update).getModifiedCount();
        System.out.println("modifiedCount = " + modifiedCount);
        for (Document document : crud.find()) {
            logger.error("after update, find crud, {}" , document.toJson());
        }

        Bson textIndex = Indexes.text("name");
        crud.createIndex(textIndex);
        Bson hashedIndex = Indexes.hashed("age");
        crud.createIndex(hashedIndex);

        for (Document listIndex : crud.listIndexes()) {
            logger.error("当前存在索引: {}" , listIndex);
        }

        BasicDBObject deleteCondition = new BasicDBObject("age", new BasicDBObject("$gt", 28));
        long deletedCount = crud.deleteMany(deleteCondition).getDeletedCount();
        System.out.println("deletedCount = " + deletedCount);
        for (Document document : crud.find()) {
            logger.error("after delete, find crud, {}" , document.toJson());
        }
    }


}

class Person{

    public Document toDocument(){
        Document document = new Document();
        document.append("uid", uid);
        document.append("name", name);
        document.append("age", age);
        return document;
    }


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private Integer uid;
    private String name;
    private Integer age;

}