package cn.intellif.serverconsumer.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author buchengyin
 * @Date 2019/3/31 8:59
 **/
@Component
public class SimpleKafkaConsumer implements InitializingBean {
    private KafkaConsumer<String, String> consumer;


    public KafkaConsumer<String, String> getConsumer() {
        return consumer;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 1024 * 1024);
        properties.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1024 * 10);
        properties.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 1000);
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 100);
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test_23");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"consumer_yinchong4");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 100);
        consumer = new KafkaConsumer<String, String>(properties);
    }
}
