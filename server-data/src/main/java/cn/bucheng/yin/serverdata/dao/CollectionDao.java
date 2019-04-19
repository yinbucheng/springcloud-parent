package cn.bucheng.yin.serverdata.dao;


import com.alibaba.fastjson.JSONArray;

/**
 * @ClassName CollectionDao
 * @Author buchengyin
 * @Date 2019/4/18 17:21
 **/
public interface CollectionDao {
  void createCollection(String collectionName);
  void dropCollection(String collectionName);
  void createSingleIndex(String collectionName,String index);
  void createMixIndex(String collectionName,String ...index);
  void dropIndex(String collectionName,String ...index);
  void batchAddData(String collectionName, JSONArray jsonArray);
}
