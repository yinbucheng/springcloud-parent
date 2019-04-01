package cn.intellif.server.provider.controller;

import cn.intellif.server.provider.service.ITest;
import cn.intellif.server.common.LogUtils;
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
        LogUtils.info(this,"-------------provider hello invoke word:"+word);
        return test.hello(word);
    }
}
