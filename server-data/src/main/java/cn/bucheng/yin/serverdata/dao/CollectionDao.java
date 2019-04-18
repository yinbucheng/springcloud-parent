package cn.bucheng.yin.serverdata.dao;

/**
 * @ClassName CollectionDao
 * @Author buchengyin
 * @Date 2019/4/18 17:21
 **/
public interface CollectionDao {
    int createCollection(String collectionName);
    int deleteCollection(String collectionName);
    int addIndexOnCollection(String collectionName,String ...indexs);
    int deleteIndexOnCollection();
    int addDataToCollection();
    int updateDataOnCollection();
    int deleteDataOnCollection();
    void showCollections();
    void showCollectionIndex(String collectionName);
}
