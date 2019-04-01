package cn.intellif.server.consumer.controller;

import cn.intellif.servercommon.LogUtils;
import cn.intellif.servercommon.ServerResult;
import cn.intellif.server.consumer.service.ITest;
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
         LogUtils.info(this,"----------->consumer hello method invoke get result:"+test);
         return ServerResult.successWithData(test);
     }

     @RequestMapping("/save")
     public Object save(){
         LogUtils.info(this,"---------->consumer save method invoke ");
         return ServerResult.successWithData(test.save());
     }

     @RequestMapping("/listAll")
     public Object listAll(){
         LogUtils.info(this,"--------->consumer listAll method invoke");
         return ServerResult.successWithData(test.listAll());
     }
}
