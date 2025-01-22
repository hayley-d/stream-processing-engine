package com.cloudurable.kafka;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // Load environment variables
        Dotenv dotenv = Dotenv.load();

        // Create and run the Kafka consumer
        try (Consumer<Long, String> consumer = createConsumer(dotenv)) {
            runConsumer(consumer);
        } catch (Exception e) {
            System.err.println("Error running Kafka consumer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates and configures the Kafka consumer.
     *
     * @param dotenv Dotenv instance to load environment variables.
     * @return Configured Kafka consumer.
     */
    private static Consumer<Long, String> createConsumer(Dotenv dotenv) {
        String topic = dotenv.get("KAFKA_TOPIC");
        String bootstrapServers = dotenv.get("KAFKA_BROKER");
        String groupId = dotenv.get("KAFKA_GROUP_ID");

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("key.deserializer", LongDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("enable.auto.commit", "true"); // Enables automatic offset commit
        props.put("auto.commit.interval.ms", "1000");

        Consumer<Long, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }

    /**
     * Runs the Kafka consumer loop to process messages.
     *
     * @param consumer Kafka consumer instance.
     */
    private static void runConsumer(Consumer<Long, String> consumer) {
        final int giveUpThreshold = 100; // Maximum polls with no records
        int noRecordCount = 0;

        System.out.println("Kafka consumer is running...");

        try {
            while (true) {
                ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(1000));

                if (records.isEmpty()) {
                    noRecordCount++;
                    if (noRecordCount > giveUpThreshold) {
                        System.out.println("No messages received for a while. Shutting down...");
                        break;
                    }
                    continue;
                }

                noRecordCount = 0; // Reset counter when records are received

                records.forEach(record -> {
                    System.out.printf("Received message: Key=%d, Value=%s, Partition=%d, Offset=%d%n",
                            record.key(), record.value(), record.partition(), record.offset());

                    // Process the message
                    processMessage(record.value());
                });
            }
        } catch (Exception e) {
            System.err.println("Error in consumer loop: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Closing Kafka consumer...");
            consumer.close();
        }
    }

    /**
     * Processes a single message (simulated by passing it to a C++ system).
     *
     * @param message The message to process.
     */
    private static void processMessage(String message) {
        // Simulate sending the message to a C++ system
        System.out.printf("Processing message: %s (Simulated pass to C++ system)%n", message);
        // Replace with real implementation, e.g., via gRPC, REST API, or IPC
    }
}

