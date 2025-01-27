<p><a target="_blank" href="https://app.eraser.io/workspace/CWCfygfB1w55luilceE3" id="edit-in-eraser-github-link"><img alt="Edit in Eraser" src="https://firebasestorage.googleapis.com/v0/b/second-petal-295822.appspot.com/o/images%2Fgithub%2FOpen%20in%20Eraser.svg?alt=media&amp;token=968381c8-a7e7-472a-8ed6-4a6626da5501"></a></p>

# Distributed Graph Database Engine
Implementation of a **Distributed Graph Database Engine **designed to efficiently store and query large graph datasets. The system is composed of a C++ based database engine responsible for managing the distributed data storage and a query engine implemented in OCaml that facilitates expressive querying of graph data.

## Features
- Distributed  Architecture: The database is designed to scale horizontally across multiple nodes, distributing graph data efficiently.
- Fault Tolerance: Built-in mechanisms to handle node failures and ensure data consistency.
- Efficient Query Engine: The OCaml query engine allows for complex graph traversal and querying.
- **ACID Compliance: **Supports basic ACID properties for transactions in the graph database.
- **Flexible Graph Models: **Supports directed, undirected and labeled graphs.
- **Optimized Query Execution:** The OCaml query engine includes optimization features such as indexing and caching to improve query performance.
## Architecture Overview
- **Ingestion Layer (Java)**:
    - Kafka consumer to ingest data streams.
    - Ensures efficient partitioning for parallel processing.
- **Processing Layer (C++)**:
    - Implements core processing operations (filtering, transformations, aggregation).
    - Implements leaderless fault tolerance using quorum-based consistency.
    - Supports stream joins and windowing for complex analytics.
- **OLAP Cube Processing (C++)**:
    - Provides multi-dimensional analysis across metrics like latency, node performance, and network utilization.
    - Leverages Amazon S3 for efficient querying and scalability.
- **Monitoring (Java)**:
    - Real-time dashboards for monitoring system health, load distribution, and processing metrics.
    - Integration with Prometheus and Grafana for customizable alerts and visualizations.
## Key Technologies
- **Programming Language**: C++ and Java.
- **Stream Framework**: Kafka.
- **Storage**: Amazon S3 for OLAP cube storage.
- **Coordination**: Zookeeper for distributed coordination.
- **Visualization**: Grafana dashboards for real-time insights and Prometheus for metrics collection.
## Kafka Topics
The Stream Processing Engine uses the following Kafka topics for data ingestion:

### node.health
**Description**: Captures periodic health metrics from each node in the system.

**Fields**:

- `node_id` : Unique identifier for the node in the cluster.
- `cpu_usage`  : Percentage of CPU usage.
- `memory_usage`  : Percentage of memory usage.
- `disk_usage`  : Percentage of disk usage.
- `network_usage`  : Percentage of network bandwidth usage.
- `system_load`  : Overall system load.
### request.traffic
**Description**: Logs every request processed by the system for traffic analysis.

**Fields**:

- `node_id`  : Unique identifier of the node processing the request.
- `request_id`  : Unique identifier for the request.
- `client_ip`  : IP address of the client making the request.
- `request_timestamp`  : Timestamp when the request was received.
- `response_timestamp`  : Timestamp when the response was sent.
- `status_code`  : HTTP status code of the response.
- `protocol`  : Protocol used.
### system.errors
**Description**: Tracks errors occurring within the system for diagnostics.

**Fields**:

- `node_id`  : Unique identifier of the node where the error occurred.
- `timestamp`  : Time of the error occurrence.
- `error_type`  : Type or category of the error (e.g., timeout, connection error, memory overflow).




<!--- Eraser file: https://app.eraser.io/workspace/CWCfygfB1w55luilceE3 --->