package cn.bucheng.yin.serverdata.dao.impl;

import cn.bucheng.yin.serverdata.dao.CollectionDao;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CollectionDaoImpl
 * @Author buchengyin
 * @Date 2019/4/18 17:23
 **/
@Repository
public class CollectionDaoImpl implements CollectionDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int createCollection(String collectionName) {
        if (mongoTemplate.createCollection(collectionName).count() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int deleteCollection(String collectionName) {
        mongoTemplate.dropCollection(collectionName);
        return 1;
    }

    @Override
    public int addIndexOnCollection(String collectionName, String... indexs) {
        for(String index:indexs) {
            MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);
            Document indexDoc = new Document();
            IndexOptions indexOptions = new IndexOptions().background(true);
            indexDoc.append(index, 1);
            collection.createIndex(indexDoc, indexOptions);
        }
        return 1;
    }


    @Override
    public int deleteIndexOnCollection() {
        return 0;
    }

    @Override
    public int addDataToCollection() {
        return 0;
    }

    @Override
    public int updateDataOnCollection() {
        return 0;
    }

    @Override
    public int deleteDataOnCollection() {
        return 0;
    }

    @Override
    public void showCollections() {

    }

    @Override
    public void showCollectionIndex(String collectionName) {
        ListIndexesIterable<Document> documents = mongoTemplate.getCollection(collectionName).listIndexes();
        System.out.println(documents);
    }
}
