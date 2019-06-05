package cn.intellif.server.provider.service;

import cn.intellif.server.provider.model.po.TestPO;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:37
 **/
public interface ITestService extends IService<TestPO>{
    String hello(String word);
    void batchSaveTest(int position);
}
