package cn.intellif.serverprovider.service.impl;

import cn.intellif.serverprovider.service.ITest;
import org.springframework.stereotype.Service;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:38
 **/
@Service
public class TestServiceImpl implements ITest {
    @Override
    public String hello(String word) {
        return "hello client,you are ok,the word:"+word;
    }
}
