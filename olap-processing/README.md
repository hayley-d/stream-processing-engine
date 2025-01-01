## OLAP Processing engine
Builds on the processing engine structuring data for long term storage and analytics.

### main.cpp
- entry point for the OLAP cube processing

### DataAggregator
- Aggregates data from the processing layer

### S3StorageManager
- Manages communication with S3 including storing cubes in Parquet/ORC formats
