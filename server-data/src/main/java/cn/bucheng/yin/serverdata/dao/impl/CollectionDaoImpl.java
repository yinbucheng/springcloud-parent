package cn.bucheng.yin.serverdata.dao.impl;

import cn.bucheng.yin.serverdata.dao.CollectionDao;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public void createCollection(String collectionName) {
        mongoTemplate.createCollection(collectionName);
    }

    @Override
    public void dropCollection(String collectionName) {
        mongoTemplate.dropCollection(collectionName);
    }

    @Override
    public void createSingleIndex(String collectionName, String index) {
        Document document = new Document();
        document.append(index, 1);
        mongoTemplate.getCollection(collectionName).createIndex(document);
    }

    @Override
    public void createMixIndex(String collectionName, String... index) {
        Document document = new Document();
        for (String s : index) {
            document.append(s, 1);
        }
        mongoTemplate.getCollection(collectionName).createIndex(document);
    }

    @Override
    public void dropIndex(String collectionName, String... index) {
        String indexName = createIndexName(index);
        mongoTemplate.getCollection(collectionName).dropIndex(indexName);
    }

    @Override
    public void batchAddData(String collectionName, JSONArray jsonArray) {
        List<Document> documentList = new LinkedList<>();
        for (Object o : jsonArray) {
            Document document = new Document();
            documentList.add(document);
            if (o instanceof JSONObject){
                JSONObject jsonObject = (JSONObject) o;
                for(Map.Entry entry:jsonObject.entrySet()){
                    String key = entry.getKey().toString();
                    document.append(key,entry.getValue());
                }
            }else if(o instanceof Map){
                Map map = (Map) o;
                for(Object temp:map.keySet()){
                    String key = temp.toString();
                    document.append(key,map.get(o));
                }
            }
        }
        mongoTemplate.getCollection(collectionName);
    }


    private String createIndexName(String... indexes) {
        String indexName = "_";
        for (String index : indexes) {
            indexName += index + "_" + 1;
        }
        indexName = indexName.substring(1);
        return indexName;
    }
}
