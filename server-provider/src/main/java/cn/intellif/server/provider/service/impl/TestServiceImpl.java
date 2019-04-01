package cn.intellif.server.provider.service.impl;

import cn.intellif.server.provider.service.ITest;
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
