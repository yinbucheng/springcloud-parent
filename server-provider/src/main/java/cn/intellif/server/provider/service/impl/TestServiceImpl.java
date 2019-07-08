package cn.intellif.server.provider.service.impl;

import cn.intellif.server.provider.model.po.TestPO;
import cn.intellif.server.provider.service.ITestService;
import cn.intellif.server.provider.dao.ITestMapper;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:38
 **/
@Service
public class TestServiceImpl extends ServiceImpl<ITestMapper,TestPO> implements ITestService {
    @Autowired
    private ITestMapper testMapper;


    @SentinelResource(value ="test_service_hello",entryType = EntryType.IN)
    @Override
    public String hello(String word)throws Exception {
        return "hello client,you are ok,the word:"+word;
    }

    @Override
    public void batchSaveTest(int position) {
        List<TestPO> list = new LinkedList<>();
        for(int j=0;j<1000;j++) {
            for (int i = position; i < position + 10000; i++) {
                TestPO entity = new TestPO();
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
