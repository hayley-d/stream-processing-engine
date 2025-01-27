<p><a target="_blank" href="https://app.eraser.io/workspace/CWCfygfB1w55luilceE3" id="edit-in-eraser-github-link"><img alt="Edit in Eraser" src="https://firebasestorage.googleapis.com/v0/b/second-petal-295822.appspot.com/o/images%2Fgithub%2FOpen%20in%20Eraser.svg?alt=media&amp;token=968381c8-a7e7-472a-8ed6-4a6626da5501"></a></p>

# Distributed Graph Database Engine
Implementation of a **Distributed Graph Database Engine **designed to efficiently store and query large graph datasets. The system is composed of a C++ based database engine responsible for managing the distributed data storage and a query engine implemented in OCaml that facilitates expressive querying of graph data.

## Features
- **Distributed  Architecture: **The database is designed to scale horizontally across multiple nodes, distributing graph data efficiently.
- **Fault Tolerance:** Built-in mechanisms to handle node failures and ensure data consistency.
- **Efficient Query Engine: **The OCaml query engine allows for complex graph traversal and querying.
- **ACID Compliance: **Supports basic ACID properties for transactions in the graph database.
- **Flexible Graph Models: **Supports directed, undirected and labeled graphs.
- **Optimized Query Execution:** The OCaml query engine includes optimization features such as indexing and caching to improve query performance.
## System Overview
### 1. Database Engine (C++)
- **Data Storage:** Uses a distributed key-value store.
- **Data Distribution: **Implements sharding to distribute data across multiple machines for scalability.
- **Fault Tolerance:** Leverages replication to ensure data availability and recovery in case of failure.
- **Data Consistency:** Implements a Strong consistency model leveraging Paxos for the consensus algorithm.
- Serialization: Graph data is serialized/deserialized to/from a binary format for efficient transmission between nodes.


### 2. Query Engine (OCaml)
- **Query Parsing: **Supports **Gremlin** query language to allow users to define graph traversals and conditions.
- **Query Execution:** Optimizes queries through indexing, pruning and caching of frequently used paths/nodes.
- **Distributed Query Processing:** Supports parallel query execution across multiple nodes in the distributed system.


## Setup and Installation
### Prerequisites
- C++ compiler: Ensure you have a C++ 14 or later compiler.
- OCaml version 4.12+ installed.
- CMake: Required to build the C++ components.








<!--- Eraser file: https://app.eraser.io/workspace/CWCfygfB1w55luilceE3 --->