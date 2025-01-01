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
