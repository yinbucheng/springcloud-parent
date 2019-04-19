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
        SpringApplication.run(ServerDataApplication.class, args);
    }

}
