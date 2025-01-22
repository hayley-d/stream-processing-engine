<p><a target="_blank" href="https://app.eraser.io/workspace/AQbnNCWOnQ7kP7LTIxlv" id="edit-in-eraser-github-link"><img alt="Edit in Eraser" src="https://firebasestorage.googleapis.com/v0/b/second-petal-295822.appspot.com/o/images%2Fgithub%2FOpen%20in%20Eraser.svg?alt=media&amp;token=968381c8-a7e7-472a-8ed6-4a6626da5501"></a></p>

## Ingestions system
Isolates the Kafka-specific code and keeps the stream ingestion logic modular and maintainable.

### Main.java
- Entry point for the ingestion system. 
- Initializes the kafka consumers and sets up partitioning logic.
### KafkaConsumer.java
- Encapsulates the logic for consuming data streams from kafka
### PartitionManager.java
- Handles assigning streams to partitions for parallel processing
### Config.java
- Manages configuration settings for kafka(broker addresses,topics,consumer groups)
## Setup
.env file that contains the following:

```
KAFKA_TOPIC=<topic_name>
KAFKA_BROKER=<uri>
KAFKA_GROUP_ID=<group_id>
PROCESSING_SERVER_URI=<uri>
SYSTEM_HEALTH_SCHEMA=src/main/resources/avro/system_health.avsc
REQUEST_TRAFFIC_SCHEMA=src/main/resources/avro/request_traffic.avsc
SYSTEM_ERRORS_SCHEMA=src/main/resources/avro/system_errors.avsc
```

<!--- Eraser file: https://app.eraser.io/workspace/AQbnNCWOnQ7kP7LTIxlv --->
