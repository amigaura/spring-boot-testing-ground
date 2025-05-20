package com.testing.ground.model;

public class ProcessingResult {
    private String status;
    private String message;

    public ProcessingResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters, setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

