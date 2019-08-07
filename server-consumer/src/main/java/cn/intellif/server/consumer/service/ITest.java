package cn.intellif.server.consumer.service;

import cn.intellif.server.consumer.service.fail.TestFail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:23
 **/
@FeignClient(value = "server-provider", fallback = TestFail.class)
public interface ITest {

    @GetMapping("/test/hello")
    String hello(@RequestParam("word") String word);

    @GetMapping("/test/save")
    String save();

    @PostMapping("/test/testFormEncode")
    String testFormEncode(@RequestParam("name") String name, @RequestParam("gender") String gender);

    @PostMapping("/test/testFormFile")
    String testFormFile(@RequestPart("file") MultipartFile file);

    @GetMapping("/test/test")
    String test(@RequestParam("name") String name);

    @GetMapping("/user/listAll")
    Object listAll();

    @PostMapping("/test/testJson")
    String testJson(@RequestBody Map<String, String> params);

    @PostMapping("/test/testFile")
    String testFile(@RequestPart("file") MultipartFile file);

    @PostMapping(value = "/test/testForm",consumes = "multipart/form-data")
    String testForm(@RequestParam("name") String name, @RequestParam("gender") String gender);
}
