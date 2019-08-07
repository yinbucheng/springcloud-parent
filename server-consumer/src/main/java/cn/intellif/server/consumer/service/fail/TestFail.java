package cn.intellif.server.consumer.service.fail;

import cn.intellif.server.consumer.service.ITest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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
    public String testFormEncode(String name, String gender) {
        return "fail";
    }

    @Override
    public String testFormFile(MultipartFile file) {
        return "fail";
    }

    @Override
    public String test(String name) {
        return "fail";
    }

    @Override
    public Object listAll() {
        return "fail callback";
    }

    @Override
    public String testJson(Map<String, String> params) {
        return "fail";
    }

    @Override
    public String testFile(MultipartFile file) {
        return "fail";
    }

    @Override
    public String testForm(String name, String gender) {
        return "fail";
    }
}
