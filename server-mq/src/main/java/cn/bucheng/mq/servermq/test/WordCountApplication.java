package cn.bucheng.mq.servermq.test;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Properties;

public class WordCountApplication {

    public static void main(final String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "word-count");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        //从哪个topic中读取数据
        KStream<String, String> textLines = builder.stream("streams-plaintext-input");
        KTable<String, Long> wordCounts = textLines
                .mapValues(textLine -> textLine.toLowerCase())
                //然后针对每行输入用正则表达式查封成各个word。flatMap到word 单词数据流
                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split(" ")))
                .selectKey((key,word)->word)
                //接下来，按照不同的单词进行分组
                .groupByKey()
                .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
        //将结果写入到哪个topic中
        wordCounts.toStream().to("streams-wordcount-output", Produced.with(Serdes.String(), Serdes.Long()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
        Thread.sleep(Integer.MAX_VALUE);
    }

}