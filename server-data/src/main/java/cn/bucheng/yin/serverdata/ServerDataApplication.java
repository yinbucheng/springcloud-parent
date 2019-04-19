package cn.bucheng.yin.serverdata;

import cn.bucheng.yin.serverdata.dao.CollectionDao;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class ServerDataApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServerDataApplication.class, args);
        MongoTemplate mongoTemplate = applicationContext.getBean(MongoTemplate.class);
//        org.bson.Document indexDoc = new Document();
//        IndexOptions indexOptions = new IndexOptions().background(true);
//        indexDoc.append("name", -1);
//        mongoTemplate.getCollection("test2").createIndex(indexDoc,indexOptions);
//        mongoTemplate.getCollection("test2").dropIndex("name_1");
//        Document document = new Document();
//        document.append("name","yinchong").append("age",20).append("gender","hahaha");
//        mongoTemplate.getCollection("test2").insertOne(document);
        List<Document> documents = new LinkedList<>();
        for (int i = 10000; i < 40000000; i++) {
            Document document = new Document();
            document.append("name","yinbucheng"+i).append("age",i).append("gender","nan"+i);
            documents.add(document);
        }
        mongoTemplate.getCollection("test2").insertMany(documents);

    }

}
