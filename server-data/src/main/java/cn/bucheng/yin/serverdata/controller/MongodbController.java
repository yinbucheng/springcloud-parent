package cn.bucheng.yin.serverdata.controller;

import cn.bucheng.yin.serverdata.dao.CollectionDao;
import cn.intellif.server.common.LogUtils;
import cn.intellif.server.common.ServerResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MongodbController
 * @Author buchengyin
 * @Date 2019/4/18 17:18
 **/
@RestController
@RequestMapping("/mongodb")
public class MongodbController {
    @Autowired
    private CollectionDao collectionDao;

    @PostMapping("/createCollection")
    public Object createCollection(@RequestBody JSONObject jsonObject){
        LogUtils.info(this,"-------------->"+jsonObject.getString("collectionName"));
        collectionDao.createCollection(jsonObject.getString("collectionName"));
        return ServerResult.success();
    }

    @PostMapping("/batchAdd")
    public Object batchAddDatat(@RequestBody JSONObject jsonObject){
        String collectionName = jsonObject.getString("collectionName");
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        collectionDao.batchAddData(collectionName,jsonArray);
        return ServerResult.success();
    }

    @PostMapping("/addSimpleIndex")
    public Object addSimpleIndex(@RequestBody  JSONObject jsonObject){
        collectionDao.createSingleIndex(jsonObject.getString("collectionName"),jsonObject.getString("index"));
        return ServerResult.success();
    }

    @PostMapping("/addMixIndex")
    public Object addMixIndex(@RequestBody JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        int len = jsonArray.size();
        String[] indexs = new String[len];
        for(int i=0;i<len;i++){
            String content = jsonArray.getString(i);
            indexs[i]=content;
        }
        collectionDao.createMixIndex(jsonObject.getString("collectionName"),indexs);
        return ServerResult.success();
    }

    @PostMapping("/deleteIndex")
    public Object deleteIndex(@RequestBody JSONObject jsonObject){
        collectionDao.dropIndex(jsonObject.getString("collectionName"),jsonObject.getString("index"));
        return ServerResult.success();
    }

    @PostMapping("/dropCollection")
    public Object dropCollection(@RequestBody JSONObject jsonObject){
        LogUtils.info(this,jsonObject.getString("collectionName"));
        collectionDao.dropCollection(jsonObject.getString("collectionName"));
        return ServerResult.success();
    }
}
