package com.testing.ground.monitoring;

public class ProcessingMetrics {
    private long startTime;
    private long endTime;
    private String operationName;

    public ProcessingMetrics(String operationName) {
        this.operationName = operationName;
        this.startTime = System.currentTimeMillis();
    }

    public void end() {
        this.endTime = System.currentTimeMillis();
    }

    public long getDuration() {
        return endTime - startTime;
    }

    public String getOperationName() {
        return operationName;
    }

    @Override
    public String toString() {
        return "ProcessingMetrics{" +
                "operationName='" + operationName + '\'' +
                ", duration=" + getDuration() + " ms" +
                '}';
    }
}
