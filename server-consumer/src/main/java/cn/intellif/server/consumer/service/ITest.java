package cn.intellif.server.consumer.service;

import cn.intellif.server.consumer.service.fail.TestFail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:23
 **/
@FeignClient(value = "server-provider",fallback = TestFail.class)
public interface ITest {

    @GetMapping("/test/hello")
     String hello(@RequestParam("word") String word);

    @GetMapping("/user/save")
    String save();

    @GetMapping("/user/listAll")
    Object listAll();

    @PostMapping("/test/testJson")
    String testJson(@RequestBody Map<String,String> param);

    @GetMapping("/test/testGet")
    String testGet(@RequestParam("name") String name);

    @PostMapping("/test/testFormData")
    String testFormData(@RequestParam("name") String name,@RequestParam("gender") String gender);
}
