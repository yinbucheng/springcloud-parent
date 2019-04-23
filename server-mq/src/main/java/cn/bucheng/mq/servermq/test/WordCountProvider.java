package cn.bucheng.mq.servermq.test;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @ClassName WordCountProvider
 * @Author buchengyin
 * @Date 2019/4/23 11:16
 **/
public class WordCountProvider {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1024 * 1024 * 10);
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 500);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024 );
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        String topic = "yinchong-topic";
        for (int i = 0; i < 200; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "test" + i, "test nice good hello word i am good boy xiexie nice");
            producer.send(record);
//            RecordMetadata recordMetadata = producer.send(record).get();
//            System.out.println(recordMetadata.offset());
        }
        //这里一定要调用close方法否则会导致消息都没有发送就关闭了
        producer.close();
        System.out.println("finish");
    }
}
