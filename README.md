<p><a target="_blank" href="https://app.eraser.io/workspace/PDMLqW8ynWhYWrt5FtcB" id="edit-in-eraser-github-link"><img alt="Edit in Eraser" src="https://firebasestorage.googleapis.com/v0/b/second-petal-295822.appspot.com/o/images%2Fgithub%2FOpen%20in%20Eraser.svg?alt=media&amp;token=968381c8-a7e7-472a-8ed6-4a6626da5501"></a></p>

# stream-processing-engine
A real-time **Stream Processing Engine** to process, aggregate, and analyse data streams in motion. Use C++ for high-performance data processing and Java for configuration, monitoring, and orchestration.

### **Key Features to Include:**
1. **Stream Ingestion:**
    - Data is ingested from a source (Kafka)
    - Support multiple data formats (JSON, YAML, TOML).
2. **Core Stream Processing:**
    - Implement common operations:
        - **Filtering:** Select relevant data (e.g., filter logs with errors).
        - **Transformations:** Map data to new formats or aggregate it.
        - **Windowed Operations:** Time-based or count-based processing (e.g., count events in 5-second windows).
3. **Fault Tolerance:**
    - Ensure at-least-once or exactly-once semantics.
    - Implement checkpointing and replay mechanisms for recovery.
    - Leader/follower pattern with Raft
4. **Scalability and Parallelism:**
    - Partition streams to enable parallel processing.
    - Design for horizontal scaling.
5. **Monitoring and Metrics:**
    - Track throughput, latency, and error rates.
    - Provide real-time dashboards using Java-based web applications (e.g., Spring Boot).
6. **Output Sink:**
    - Write processed data to a storage system (S3, MongoDB) or another Kafka topic.
### **Architecture:**
1. **Stream Ingestion Module (Java):**
    - Ingests data streams from Kafka.
    - Partitions data and assigns processing tasks to nodes.
    - Provides API endpoints for users to submit processing jobs
2. **Processing Nodes (C++):**
    - Implements stream processing logic for filtering, transformations, and aggregations.
    - Handles fault tolerance 
    - Writes processed data to storage layer.
3. Storage 
    - Use S3 for scalability and AWS alignment
4. **Coordinator and Dashboard (Java):**
    - Manages node state, job assignment, and cluster scaling.
    - Provides a real-time dashboard for monitoring metrics (e.g., throughput, latency, failures).


### **Division of Responsibilities Between C++ and Java:**
| **Component** | **Language** | **Reason** |
| ----- | ----- | ----- |
| Stream Processing Logic | C++ | Low-level, high-performance computations. |
| Ingestion and Partitioning | Java | Integration with Kafka and easier APIs. |
| Coordination and Monitoring | Java | Simplifies orchestration and UI creation. |
| Fault-Tolerance Mechanisms | C++ & Java | C++ for checkpoints, Java for orchestration. |
| Dashboard and API | Java | Easier to build web interfaces and REST APIs. |
### **Tools and Libraries:**
1. **Kafka:**
    - For data ingestion and inter-node communication.
2. **C++ Libraries:**
    - **Boost.Asio** for asynchronous I/O.
    - **gRPC** for communication between nodes.
    - **Protobuf** for serializing messages.
3. **Java Frameworks:**
    - **Spring Boot:** For dashboard and APIs.
    - **Kafka Streams API:** To handle ingestion and distribution.
4. **Metrics and Monitoring:**
    - **Prometheus** and **Grafana** for tracking system performance.
### **Challenges and Solutions:**
1. **Concurrency and Parallelism:**
    - Use C++ threads or frameworks like Intel TBB for efficient parallelism.
2. **Fault Tolerance:**
    - Implement replay mechanisms using Kafka’s consumer offsets.
3. **Scalability:**
    - Design processing nodes to handle partitioned data in parallel.


## Core Features
1. Leaderless Architecture 
    - Replicate data across nodes for redundancy
    - use quorum-based consistency for processing results
2. Stream Processing Options
    - Filtering: Drop events based on conditions
    - Transformation: Map data into new formats
    - Aggregations: count, sum or compute rolling averages in time or count windows
    - Windowing: Implement sliding, tumbling and session windows
3. Storage Integration
    1. Amazon S3 using SDKs for high-throughput writes
    2. 
### **5. Milestones:**
#### **Phase 1: Design and Setup (1 Week)**
- Define architecture and key APIs.
- Set up development environments for C++ and Java.
#### **Phase 2: Ingestion Layer (2 Weeks)**
- Implement Kafka consumers in Java.
- Develop APIs for submitting jobs.
- Partition data and distribute to nodes.
#### **Phase 3: Processing Engine (4 Weeks)**
- Build stream processing core in C++.
- Implement basic operations: filtering, transformations, windowing.
- Add fault tolerance (leaderless or Raft-based).
#### **Phase 4: Storage Integration (2 Weeks)**
- Write processed data to S3 or MongoDB.
- Implement efficient serialization (Protobuf or Avro).
#### **Phase 5: Dashboard and Monitoring (2 Weeks)**
- Build a Java-based monitoring dashboard.
- Integrate Prometheus/Grafana for advanced metrics.
#### **Phase 6: Advanced Features (Optional, 3 Weeks)**
- Add stream joins and complex event processing.
- Test dynamic scaling and exactly-once semantics.
### ****


## **Learning Resources:**
#### **Fault Tolerance:**
- [﻿Dynamo: Amazon's Highly Available Key-Value Store](https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf)﻿
#### **Stream Processing:**
- [﻿Kafka Streams Documentation](https://kafka.apache.org/documentation/streams/) 
- [﻿Logs](https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-datas-unifying) 
- [﻿kafka for data engineers﻿](https://richiebachala.medium.com/11-steps-for-understanding-confluent-kafka-for-data-engineers-47ab9a398bed)﻿
#### **C++ Libraries:**
- [﻿Boost.Asio for Networking](https://www.boost.org/doc/libs/1_87_0/doc/html/boost_asio/tutorial.html)﻿
- gRPC for RPC
- [﻿C++ Concurrency](https://www.amazon.com/C-Concurrency-Action-Practical-Multithreading/dp/1933988770)﻿
#### **Storage:**
- [﻿Amazon S3 SDK Documentation](https://docs.aws.amazon.com/sdk-for-cpp/v1/developer-guide/welcome.html) 
- [﻿MongoDB Official Drivers](https://www.mongodb.com/docs/drivers/) 




<!--- Eraser file: https://app.eraser.io/workspace/PDMLqW8ynWhYWrt5FtcB --->