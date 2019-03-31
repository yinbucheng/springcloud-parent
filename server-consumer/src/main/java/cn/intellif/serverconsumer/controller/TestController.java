package cn.intellif.serverconsumer.controller;

import cn.intellif.servercommon.ServerResult;
import cn.intellif.serverconsumer.service.ITest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:26
 **/
@RestController
@RequestMapping("/test")
public class TestController {
     @Autowired
    private ITest test;

     @RequestMapping("/hello")
     public Object hello(String word){
         String test = this.test.hello(word);
         System.out.println("----------->consumer hello invoke ");
         return ServerResult.successWithData(test);
     }

     @RequestMapping("/sendMessage")
     public Object sendMessage(String key,String value){
         System.out.println("------------>consumer sendMessage invoke");
       return test.sendMessage(key,value);
     }
}
