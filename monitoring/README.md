## Monitoring System
Keeps monitoring seporate and ensures that obervability tools do not interfear with core processing.

## Main.java
- Entry point for the monitoring service

### MetricsDashboard.java
- Implements the java based real-time monitoring UI

### MetricsAggregarot.java
- Collects and aggregates metrics from all components (throughput,latency,errors)

### HealthChecker.java
- Checks the sytsem health and reports issues

### Logger.java
- Log4
- centralized logging for the entire system
