package cn.intellif.serverprovider.controller;

import cn.intellif.serverprovider.kafka.SimpleKafkaProducer;
import cn.intellif.serverprovider.service.ITest;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:39
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ITest test;
    @Autowired
    private SimpleKafkaProducer producer;

    @RequestMapping("/hello")
    public String hello(String word){
        System.out.println("-------------provider hello invoke word:"+word);
        return test.hello(word);
    }

    @RequestMapping("/createMessage")
    public String createMessage(String key,String message){
        System.out.println("------------->provider createMessage invoke");
        for(int i=0;i<40;i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("server-provider-test", key, message+i);
            try {
                producer.getProducer().send(record);
            } catch (Exception e) {
                return "fail send message";
            }
        }
        return "success send message";
    }
}
