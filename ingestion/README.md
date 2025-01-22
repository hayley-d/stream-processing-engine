<p><a target="_blank" href="https://app.eraser.io/workspace/AQbnNCWOnQ7kP7LTIxlv" id="edit-in-eraser-github-link"><img alt="Edit in Eraser" src="https://firebasestorage.googleapis.com/v0/b/second-petal-295822.appspot.com/o/images%2Fgithub%2FOpen%20in%20Eraser.svg?alt=media&amp;token=968381c8-a7e7-472a-8ed6-4a6626da5501"></a></p>

## Kafka Avro Ingestion System
This is a Kafka-based ingestion system designed to consume messages for Kafka topics, serialize them using Avro schemas, and forward the serialized data to a processing server.

### Features
#### 1. Kafka Consumer
- Configured via `.env` file to dynamically load the Kafka broker, topic, group ID and other settings.
- Polls messages from the Kafka topics and processes them efficiently.

#### 2. Dynamic Schema Handling
- Loads Avro schemas dynamically based on the specified topic.
- Supports flexible schema configuration through `.env`.

#### 3. Message Processing
- Converts messages into Avro format for serialization.
- Sends the serialized data to a processing server over HTTP.

#### 4. Error Handling
- Implements robust error handling during schema loading, message processing and HTTP transmission.

#### 5. Graceful Shutdown
- Stops polling after a threshold of no messages received.

### Prerequisites 
1. Java 17 or later.
2. Maven for dependency management.
3. Kafka broker running with the required topics.
4. `.env` file configured with appropriate environment variables.
5. Avro schema files located in the project directory.

Add the .env file to the root of the project: 
```
KAFKA_TOPIC=<topic_name>
KAFKA_BROKER=<uri>
KAFKA_GROUP_ID=<group_id>
PROCESSING_SERVER_URI=<uri>
SYSTEM_HEALTH_SCHEMA=src/main/resources/avro/system_health.avsc
REQUEST_TRAFFIC_SCHEMA=src/main/resources/avro/request_traffic.avsc
SYSTEM_ERRORS_SCHEMA=src/main/resources/avro/system_errors.avsc
```
- `KAFKA_TOPIC`: Kafka topic to consume the messages from.
- `KAFKA_BROKER`: Address of the Kafka broker.
- `KAFKA_GROUP_ID`: Consumer group ID for the Kafka consumer.
- `PROCESSING_SERVER_URI`: The URI of the processing server to send the Avro data.

<!--- Eraser file: https://app.eraser.io/workspace/AQbnNCWOnQ7kP7LTIxlv --->
