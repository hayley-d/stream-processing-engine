import io.github.cdimascio.dotenv.Dotenv;
package com.cloudurable.kafka;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Collections;
import java.util.Properties;

public class Main {
    public static void main(String [] args) {
        Consumer<Long,String> consumer = createConsumer();
    }

    private static Consumer<Long,String> createConsumer() {
        Dotenv dotenv = Dotenv.load();
        String topic = dotenv.get("KAFKA_TOPIC");

        Properties props = new Properties();
        props.put("bootstrap.servers",dotenv.get("KAFKA_BROKER"));
        props.put("group.id",dotenv.get("KAFKA_GROUP_ID"));
        props.put("key.deserializer",LongDeserializer.class.getName());
        props.put("value.deserializer",StringDeserializer.class.getName());

        final Consumer<Long,String> consumer = new KafkaConsumer<>(props);


        consumer.subscribe(Collections.singletonList(topic));

        return consumer;
    }
}
