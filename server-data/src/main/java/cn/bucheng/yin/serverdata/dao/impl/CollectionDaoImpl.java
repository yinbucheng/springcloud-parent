package cn.bucheng.yin.serverdata.dao.impl;

import cn.bucheng.yin.serverdata.dao.CollectionDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            if (o instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) o;
                for (Map.Entry entry : jsonObject.entrySet()) {
                    String key = entry.getKey().toString();
                    document.append(key, entry.getValue());
                }
            } else if (o instanceof Map) {
                Map map = (Map) o;
                for (Object temp : map.keySet()) {
                    String key = temp.toString();
                    document.append(key, map.get(temp));
                }
            }
        }
        mongoTemplate.getCollection(collectionName).insertMany(documentList);
    }

    @Override
    public JSONObject ListData(JSONObject jsonObject) {
        String collectionName = jsonObject.getString("collectionName");
        Object param = jsonObject.get("condition");
        JSONObject sortObject = jsonObject.getJSONObject("sort");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageSize = jsonObject.getInteger("pageSize");
        Query query = null;
        if (param != null) {
            String condition = JSON.toJSONString(param);
            query = new BasicQuery(condition);
        } else {
            query = new Query();
        }
        //构造sort
        if (sortObject != null) {
            for (Object o : sortObject.keySet()) {
                String field = (String) o;
                int desc = (int) sortObject.get(o);
                if (desc > 0) {
                    query.with(new Sort(Sort.Direction.DESC, field));
                } else {
                    query.with(new Sort(Sort.Direction.ASC, field));
                }
            }
        }
        long count = mongoTemplate.count(query, collectionName);
        JSONObject result = new JSONObject();
        if (pageNum != null && pageNum >= 0 && pageSize != 0 && pageSize > 0) {
            result.put("pageNum", pageNum);
            result.put("pageSize", pageSize);
        }
        result.put("totalCount", count);
        if (count == 0) {
            return result;
        }
        //这里进行分页显示
        if (pageNum != null && pageNum >= 0 && pageSize != 0 && pageSize > 0) {
            query.limit(pageSize).skip((pageNum - 1) * pageSize);
        }
        List<JSONObject> jsonObjects = mongoTemplate.find(query, JSONObject.class, collectionName);
        result.put("data", jsonObjects);
        return result;
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
