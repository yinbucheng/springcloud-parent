package cn.intellif.serverconsumer.service;

import cn.intellif.serverconsumer.service.fail.TestFail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:23
 **/
@FeignClient(value = "server-provider",fallback = TestFail.class)
public interface ITest {

    @GetMapping("/test/hello")
     String hello(@RequestParam("word") String word);

    @PostMapping("/test/createMessage")
    String sendMessage(@RequestParam("key") String key,@RequestParam("message") String value);
}
