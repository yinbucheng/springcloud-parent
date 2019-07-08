package cn.intellif.server.consumer.controller;

import cn.intellif.server.common.LogUtils;
import cn.intellif.server.common.ServerResult;
import cn.intellif.server.consumer.service.ITest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:26
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DiscoveryClient client;

     @Autowired
    private ITest test;

     @RequestMapping("/hello")
     public Object hello(String word){
         String test = this.test.hello(word);
         LogUtils.info(this,"----------->consumer hello method invoke get result:"+test);
         return ServerResult.successWithData(test);
     }

     @RequestMapping("/test")
     public Object save(){
         LogUtils.info(this,"---------->consumer save method invoke ");
         return ServerResult.successWithData(test.save());
     }

     @RequestMapping("/listAll")
     public Object listAll(){
         LogUtils.info(this,"--------->consumer listAll method invoke");
         return ServerResult.successWithData(test.listAll());
     }

     @RequestMapping("/uploadFile")
     public Object uploadFile(MultipartFile file){
         if(file==null||file.isEmpty()){
             return ServerResult.fail("请选择文件");
         }
         String name = file.getOriginalFilename();
         LogUtils.info(this,"-------------->filename:"+name+" filesize:"+file.getSize());
         return ServerResult.success("upload ok");
     }

     @RequestMapping("/listServer")
     public Object listServer(){
         System.out.println("==========================================");
         List<String> services = client.getServices();
         for(String service:services){
             System.out.println("get service:"+service);
             List<ServiceInstance> instances = client.getInstances(service);
             for (ServiceInstance instance:instances){
                 System.out.println(instance.getHost());
                 System.out.println(instance.getPort());
                 System.out.println(instance.getServiceId());
                 System.out.println(instance.getUri());
             }
         }
         return ServerResult.successWithData(services);
     }
}
