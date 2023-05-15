package io.cocoon.mongodb.service;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MongoJdbcService {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("org.mongo.driver");
        logger.setLevel(Level.ALL);

        ConnectionString connectionString = new ConnectionString("mongodb://localhost");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase testdb = mongoClient.getDatabase("testdb");

        testdb.listCollectionNames().forEach((Block<? super String>) item -> logger.info("collection name: " + item.toString()));

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

        List<Person> personList = com.alibaba.fastjson.JSON.parseArray(jsonStr2, Person.class);
        crud.insertMany(personList.stream().map(Person::toDocument).collect(Collectors.toList()));

        for (Document document : crud.find()) {
            logger.info("after init, find crud, " + document.toJson());
        }

        BasicDBObject basicDBObject = new BasicDBObject("age", new BasicDBObject("$gt", 29));
        for (Document document : crud.find(basicDBObject)) {
            logger.info("query age > 29 , " + document.toJson());
        }


        BasicDBObject condition = new BasicDBObject("name", "张三");
        Bson update = Updates.set("age", 17);
        long modifiedCount = crud.updateOne(condition, update).getModifiedCount();
        System.out.println("modifiedCount = " + modifiedCount);
        for (Document document : crud.find()) {
            logger.info("after update, find crud, " + document.toJson());
        }


        BasicDBObject deleteCondition = new BasicDBObject("age", new BasicDBObject("$gt", 28));
        long deletedCount = crud.deleteMany(deleteCondition).getDeletedCount();
        System.out.println("deletedCount = " + deletedCount);
        for (Document document : crud.find()) {
            logger.info("after delete, find crud, " + document.toJson());
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
