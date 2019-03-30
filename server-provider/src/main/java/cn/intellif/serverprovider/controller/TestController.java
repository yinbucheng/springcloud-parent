package cn.intellif.serverprovider.controller;

import cn.intellif.serverprovider.service.ITest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:39
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ITest test;

    @RequestMapping("/hello")
    public String hello(String word){
        System.out.println("-------------provider invoke word:"+word);
        return test.hello(word);
    }
}
