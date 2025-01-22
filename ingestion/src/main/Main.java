package com.cloudurable.kafka;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();

    try (Consumer<Long, String> consumer = createConsumer(dotenv)) {
        addShutdownHook(consumer);
        runConsumer(consumer);
    } catch (Exception e) {
        logger.error("Error running Kafka consumer", e);
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
     * Adds a shutdown hook to gracefully close the Kafka consumer.
     *
     * @param consumer Kafka consumer instance.
     */
    private static void addShutdownHook(Consumer<Long, String> consumer) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down consumer...");
            consumer.wakeup();
        }));
    }

  /**
   * Runs the Kafka consumer loop to process messages.
   *
   * @param consumer Kafka consumer instance.
   */
    static void runConsumer(Consumer<Long, String> consumer) {
        final int giveUpThreshold = 100;
        int noRecordCount = 0;
        Dotenv dotenv = Dotenv.load();
        String processingServerUri = dotenv.get("PROCESSING_SERVER_URI");
        logger.info("Kafka consumer is running...");

        try {
          while (true) {
            ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(1000));
            if (records.isEmpty()) {
                noRecordCount++;
                if (noRecordCount > giveUpThreshold) {
                    logger.warn("No messages received for a while. Shutting down...");
                    break;
                }
                continue;
            }

            noRecordCount = 0;
            records.forEach(record -> {
              logger.info("Received message: Key={}, Value={}, Partition={}, Offset={}",record.key(), record.value(), record.partition(),record.offset());
              processMessage(record.key(), record.value(), processingServerUri,dotenv.get("KAFKA_TOPIC"));
            });
          }
        } catch (Exception e) {
            logger.error("Error in consumer loop: " + e.getMessage());
        } finally {
            logger.info("Closing Kafka consumer...");
            consumer.close()
        }
    }

  
      /**
       * Processes a single message.       
       * 
       * @param key The message key.
       * @param value The message vlaue.
       * @param processingServerUri The uri for the server to process the event.
       * @param topic The topic the consumer is subscribed to.
       */
      private static void processMessage(long key, String value, String processingServerUri,String topic) {
        try {
            String schemaPath = getSchemaPath(topic);
            Schema schema = loadSchema(schemaPath);
            GenericRecord record = new GenericData.Record(schema);
            record.put("key", key);
            record.put("value", value);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DatumWriter<GenericRecord> writer = new SpecificDatumWriter<>(schema);
            Encoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
            writer.write(record, encoder);
            encoder.flush();
            byte[] avroData = outputStream.toByteArray();
            sendToProcessingServer(avroData, processingServerUri);
        } catch (Exception e) {
            logger.error("Error processing message with Avro: {}",e.getMessage());
            e.printStackTrace();
        }
  }

    
    /**
     * Function to send the event to the processing server for futhur processing.
     *
     * @param avroData The avro data consumed from the Kafka broker.
     * @param processingServerUri The uri for the processing server to send the data to.
     */
    private static void sendToProcessingServer(byte[] avroData,String processingServerUri) {
        try {
          URL url = new URL(processingServerUri);
          HttpURLConnection connection = (HttpURLConnection)url.openConnection();

          connection.setRequestMethod("POST");
          connection.setRequestProperty("Content-Type", "application/avro-binary");
          connection.setDoOutput(true);

          // Send the Avro data
          try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(avroData);
            outputStream.flush();
          }

          int responseCode = connection.getResponseCode();
          if (responseCode == HttpURLConnection.HTTP_OK) {
            logger.info("Message processed successfully by the server.");
          } else {
            logger.error("Failed to process message. Server returned: {}",responseCode);
          }
        } catch (Exception e) {
            logger.error("Error sending Avro data to processing server");
        }
  }


    /**
     * Utility function to load the Avro schema from a file.
     * @param The path to the correct schema file.
     */
    private static Schema loadSchema(String schemaPath) throws Exception {
        return schemaCache.computeIfAbsent(schemaPath, path -> {
            try {
                String schemaString = new String(Files.readAllBytes(Paths.get(path)));
                return new Schema.Parser().parse(schemaString);
            } catch (Exception e) {
                throw new RuntimeException("Error loading schema: " + path, e);
            }
        });
    }

    /**
     * Utility function to dynamically determine what schema to load based on the topic defined in the .env
     */
    private static String getSchemaPath(String topic) {
        Dotenv dotenv = Dotenv.load();
        switch (topic) {
            case "system.health":
                return dotenv.get("SYSTEM_HEALTH_SCHEMA");
            case "request.traffic":
                return dotenv.get("REQUEST_TRAFFIC_SCHEMA");
            case "system.errors":
                return dotenv.get("SYSTEM_ERRORS_SCHEMA");
            default:
                throw new IllegalArgumentException("Unknown topic: " + topic);
        }
    }
}


}
