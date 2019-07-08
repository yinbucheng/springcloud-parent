package cn.intellif.server.provider.controller;

import cn.intellif.server.common.LogUtils;
import cn.intellif.server.provider.model.po.UserPO;
import cn.intellif.server.provider.dao.IUserMapper;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author buchengyin
 * @Date 2019/4/1 12:33
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserMapper userMapper;

    @RequestMapping("/save")
    public Object save(){
        LogUtils.info(this,"------------->provider save invoke");
        UserPO entity = new UserPO();
        entity.setAge(10);
        entity.setName("yinchong");
        entity.setGender("nan");
        int save = userMapper.save(entity);
        if(save>0){
            return "save success";
        }else{
            return "save fail";
        }
    }

    @RequestMapping("/listAll")
    public Object listAll(){
        LogUtils.info(this,"----------------listAll method invoke");
       return  userMapper.listAll();
    }
}
