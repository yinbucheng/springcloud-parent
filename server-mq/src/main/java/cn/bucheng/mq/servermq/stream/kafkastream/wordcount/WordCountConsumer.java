package cn.bucheng.mq.servermq.stream.kafkastream.wordcount;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @ClassName WordCountConsumer
 * @Author buchengyin
 * @Date 2019/4/23 11:17
 **/
public class WordCountConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 50);
        properties.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 100);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        Consumer<String, Long> consumer = new KafkaConsumer<String, Long>(properties);
        consumer.subscribe(Arrays.asList("streams-wordcount-output"));
        while (true) {
            ConsumerRecords<String, Long> records = consumer.poll(Duration.ofSeconds(2));
            if (!records.isEmpty()) {
                records.forEach(record -> {
                            String key = record.key();
                            long count = record.value();
                            System.out.println(key + "    " + count);
                        }
                );
                consumer.commitAsync();
            }
        }
    }
}
