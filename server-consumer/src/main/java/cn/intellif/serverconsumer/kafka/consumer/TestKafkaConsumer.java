package cn.intellif.serverconsumer.kafka.consumer;

import cn.intellif.serverconsumer.kafka.SimpleKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author buchengyin
 * @Date 2019/3/31 9:07
 **/
@Component
public class TestKafkaConsumer implements CommandLineRunner {
    @Autowired
    private SimpleKafkaConsumer consumer;
    private ExecutorService threads = Executors.newSingleThreadExecutor();

    @Override
    public void run(String... args) throws Exception {
        threads.submit(new Runnable() {
            @Override
            public void run() {
                KafkaConsumer<String, String> consumer2 = TestKafkaConsumer.this.consumer.getConsumer();
                consumer2.subscribe(Arrays.asList("server-provider-test"));
                while (true) {
                    ConsumerRecords<String, String> records = consumer2.poll(Duration.ZERO);
                    if (records.count() > 0) {
                        for (ConsumerRecord record : records) {
                            System.out.println(record.topic() + " " + record.partition() + " " + record.offset() + " " + record.key() + " " + record.value());
                        }
                        consumer2.commitSync();
                    }
                }
            }
        });
    }
}
