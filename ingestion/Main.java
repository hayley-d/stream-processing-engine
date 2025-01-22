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
  static void runConsumer(Consumer<Long, String> consumer) {
    final int giveUpThreshold = 100;
    int noRecordCount = 0;
    Dotenv dotenv = Dotenv.load();
    String processingServerUri = dotenv.get("PROCESSING_SERVER_URI");
    System.out.println("Kafka consumer is running...");
    try {
      while (true) {
        ConsumerRecords<Long, String> records =
            consumer.poll(Duration.ofMillis(1000));
        if (records.isEmpty()) {
          noRecordCount++;
          if (noRecordCount > giveUpThreshold) {
            System.out.println(
                "No messages received for a while. Shutting down...");
            break;
          }
          continue;
        }
        noRecordCount = 0;
        records.forEach(record -> {
          System.out.printf(
              "Received message: Key=%d, Value=%s, Partition=%d, Offset=%d%n",
              record.key(), record.value(), record.partition(),
              record.offset());
          // Process the message using Avro serialization
          processMessage(record.key(), record.value(), processingServerUri);
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
    System.out.printf("Processing message: %s (Simulated pass to C++ system)%n",
                      message);
    // Replace with real implementation, e.g., via gRPC, REST API, or IPC
  }

  private static final String AVRO_SCHEMA = "" "
  {
    "type" : "record",
               "name" : "Message",
                          "namespace" : "com.example.avro",
                                          "fields"
        : [
          {"name" : "key", "type" : "long"},
          {"name" : "value", "type" : "string"}
        ]
  }
  "" ";

      private static void
      processMessage(long key, String value, String processingServerUri) {
    try {
      // Parse the schema
      Schema schema = new Schema.Parser().parse(AVRO_SCHEMA);

      // Create a generic record for the message
      GenericRecord record = new GenericData.Record(schema);
      record.put("key", key);
      record.put("value", value);

      // Serialize the record to Avro binary format
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      DatumWriter<GenericRecord> writer = new SpecificDatumWriter<>(schema);
      Encoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
      writer.write(record, encoder);
      encoder.flush();
      byte[] avroData = outputStream.toByteArray();

      // Send the Avro-encoded data to the processing server
      sendToProcessingServer(avroData, processingServerUri);
    } catch (Exception e) {
      System.err.printf("Error processing message with Avro: %s%n",
                        e.getMessage());
      e.printStackTrace();
    }
  }

  private static void sendToProcessingServer(byte[] avroData,
                                             String processingServerUri) {
    try {
      // Create a connection to the processing server
      URL url = new URL(processingServerUri);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();

      // Configure the request
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/avro-binary");
      connection.setDoOutput(true);

      // Send the Avro data
      try (OutputStream outputStream = connection.getOutputStream()) {
        outputStream.write(avroData);
        outputStream.flush();
      }

      // Check the response
      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        System.out.println("Message processed successfully by the server.");
      } else {
        System.err.printf("Failed to process message. Server returned: %d%n",
                          responseCode);
      }
    } catch (Exception e) {
      System.err.printf("Error sending Avro data to processing server: %s%n",
                        e.getMessage());
      e.printStackTrace();
    }
  }
}
