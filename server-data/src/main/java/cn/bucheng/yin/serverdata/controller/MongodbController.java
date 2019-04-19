package cn.bucheng.yin.serverdata.controller;

import cn.bucheng.yin.serverdata.dao.CollectionDao;
import cn.intellif.server.common.LogUtils;
import cn.intellif.server.common.ServerResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName MongodbController
 * @Author buchengyin
 * @Date 2019/4/18 17:18
 **/
@Api(value = "mongodbController",tags = "mongodb工具类")
@RestController
@RequestMapping("/mongodb")
public class MongodbController {
    @Autowired
    private CollectionDao collectionDao;
    @Autowired
    private MongoTemplate template;

    @PostMapping("/createCollection")
    public Object createCollection(@RequestBody JSONObject jsonObject) {
        LogUtils.info(this, "-------------->" + jsonObject.getString("collectionName"));
        collectionDao.createCollection(jsonObject.getString("collectionName"));
        return ServerResult.success();
    }

    @ApiOperation(value = "batchAddData",notes = "批量添加数据")
    @ApiImplicitParam(name = "json字符串",value = "json字符串")
    @PostMapping("/batchAdd")
    public Object batchAddDatat(@RequestBody JSONObject jsonObject) {
        String collectionName = jsonObject.getString("collectionName");
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        collectionDao.batchAddData(collectionName, jsonArray);
        return ServerResult.success();
    }

    @PostMapping("/addSimpleIndex")
    public Object addSimpleIndex(@RequestBody JSONObject jsonObject) {
        collectionDao.createSingleIndex(jsonObject.getString("collectionName"), jsonObject.getString("index"));
        return ServerResult.success();
    }

    @PostMapping("/addMixIndex")
    public Object addMixIndex(@RequestBody JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        int len = jsonArray.size();
        String[] indexs = new String[len];
        for (int i = 0; i < len; i++) {
            String content = jsonArray.getString(i);
            indexs[i] = content;
        }
        collectionDao.createMixIndex(jsonObject.getString("collectionName"), indexs);
        return ServerResult.success();
    }

    @PostMapping("/deleteIndex")
    public Object deleteIndex(@RequestBody JSONObject jsonObject) {
        collectionDao.dropIndex(jsonObject.getString("collectionName"), jsonObject.getString("index"));
        return ServerResult.success();
    }

    @PostMapping("/dropCollection")
    public Object dropCollection(@RequestBody JSONObject jsonObject) {
        LogUtils.info(this, jsonObject.getString("collectionName"));
        collectionDao.dropCollection(jsonObject.getString("collectionName"));
        return ServerResult.success();
    }

    @ApiOperation(value = "queryData",notes = "查询数据")
    @ApiImplicitParam(name = "jsonObject",value = "字符串",required = true,paramType = "JSONObject",dataType = "JSONObject",defaultValue = "{\"collectionName\": \"yinchong\",\"pageNum\": 1,\"pageSize\": 100,\"condition\": {\"name\": \"yinchong2\"},\"sort\": [{\"name\": 1}]}")
    @PostMapping("/findData")
    public Object findData(@RequestBody JSONObject jsonObject) {
        return collectionDao.ListData(jsonObject);
    }

    @PostMapping("/test")
    public Object testBatchSave(Integer position){
        List<Document> documents = new LinkedList<>();
        for (int i = position; i < 1000000+position; i++) {
            Document document = new Document();
            document.append("name","yinchong"+i).append("age",(1000000+position)-i).append("gender","nan"+i);
            documents.add(document);
        }
        template.getCollection("yinchong").insertMany(documents);
        return ServerResult.success();
    }
}
