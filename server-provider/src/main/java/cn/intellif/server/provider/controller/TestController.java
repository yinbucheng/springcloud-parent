package cn.intellif.server.provider.controller;

import cn.intellif.server.common.LogUtils;
import cn.intellif.server.provider.service.ITest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:39
 **/
@RestController
@Api(tags = "TestController",description = "测试web项目")
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ITest test;

    @GetMapping("/hello")
    @ApiOperation(value = "说hello",notes = "说hello")
    @ApiImplicitParam(name="word",value = "单词",required = true,dataType = "String")
    public String hello(String word){
        LogUtils.info(this,"-------------provider hello invoke word:"+word);
        return test.hello(word);
    }

    @PostMapping("/hi")
    @ApiOperation(value = "hi",notes = "嗨")
    @ApiImplicitParams({@ApiImplicitParam(name="someThing",value = "某些事件",required = true),@ApiImplicitParam(name = "hahaha",value = "hahaha",required = true)})
    public String hi(String someThing,String hahaha){
        LogUtils.info(this,someThing+hahaha);
        return test.hello(someThing);
    }
}
