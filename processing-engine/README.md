## Processing engine
Core data processing happens in this system.
C++ chosen for low-level control and performance, critical for real-time stream processing and fault-tolerance mechanisms.

### StreamProcessor
- Implements core operations(filtering,tranformations, aggregations)

### QuorumManager
- Implements leaderless quorum-based fault tolerance

