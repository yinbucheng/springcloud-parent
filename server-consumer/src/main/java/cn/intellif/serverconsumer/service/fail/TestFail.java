package cn.intellif.serverconsumer.service.fail;

import cn.intellif.serverconsumer.service.ITest;
import org.springframework.stereotype.Component;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:25
 **/
@Component
public class TestFail implements ITest {
    @Override
    public String hello(String word) {
        return "fail callback";
    }
}
