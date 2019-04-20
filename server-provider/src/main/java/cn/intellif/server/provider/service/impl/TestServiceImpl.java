package cn.intellif.server.provider.service.impl;

import cn.intellif.server.provider.entity.TestEntity;
import cn.intellif.server.provider.service.ITest;
import cn.intellif.server.provider.service.db.ITestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:38
 **/
@Service
public class TestServiceImpl implements ITest {
    @Autowired
    private ITestMapper testMapper;
    @Override
    public String hello(String word) {
        return "hello client,you are ok,the word:"+word;
    }

    @Override
    public void batchSaveTest(int position) {
        List<TestEntity> list = new LinkedList<>();
        for(int j=0;j<1000;j++) {
            for (int i = position; i < position + 10000; i++) {
                TestEntity entity = new TestEntity();
                entity.setName("yinchong" + i);
                entity.setAge(i);
                entity.setAddress("wuhang" + i);
                entity.setIdCard(i + "_test");
                entity.setGender("nan");
                list.add(entity);
            }
            testMapper.batchSave(list);
            list.clear();
        }

    }
}
