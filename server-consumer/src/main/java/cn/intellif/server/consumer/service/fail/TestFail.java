package cn.intellif.server.consumer.service.fail;

import cn.intellif.server.consumer.service.ITest;
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

    @Override
    public String save() {
        return "fail callback";
    }

    @Override
    public Object listAll() {
        return "fail callback";
    }
}
