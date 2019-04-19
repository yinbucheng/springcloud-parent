package cn.bucheng.yin.serverdata.vo;

/**
 * @ClassName MongoIndex
 * @Author buchengyin
 * @Date 2019/4/19 9:48
 **/
public class MongoIndex {
    private String indexName;
    private int order;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
