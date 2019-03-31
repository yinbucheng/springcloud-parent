package cn.intellif.serverprovider.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author buchengyin
 * @Date 2019/3/31 8:49
 **/
@Component
public class SimpleKafkaProducer implements InitializingBean {

    private KafkaProducer<String,String> producer;

    public KafkaProducer<String,String> getProducer(){
        return producer;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        properties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG,500);
        properties.put(ProducerConfig.RETRIES_CONFIG,3);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,10*1024*1024);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,1024*1024);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,3000);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        producer = new KafkaProducer<String, String>(properties);
    }
}
