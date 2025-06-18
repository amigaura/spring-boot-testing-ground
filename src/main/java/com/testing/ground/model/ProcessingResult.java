package com.testing.ground.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProcessingResult {
    // Getters, setters
    private String status;
    private String message;

    public ProcessingResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

}

