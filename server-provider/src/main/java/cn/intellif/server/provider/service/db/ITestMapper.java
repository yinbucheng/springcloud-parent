package cn.intellif.server.provider.service.db;

import cn.intellif.server.provider.entity.TestEntity;

import java.util.List;

/**
 * @ClassName ITestMapper
 * @Author buchengyin
 * @Date 2019/4/20 16:26
 **/
public interface ITestMapper {
    void batchSave(List<TestEntity> entityList);
}
