package cn.bucheng.yin.serverdata.controller;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MongodbController
 * @Author buchengyin
 * @Date 2019/4/18 17:18
 **/
@RestController
@RequestMapping("/mongodb")
public class MongodbController {

    @PostMapping("/createCollection")
    public Object createCollection(@RequestBody JsonObject jsonObject){

        return null;
    }
}
