package com.testing.ground.async;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ItemProcessing {
    @Id
    @GeneratedValue
    private Long id;

    private String requestId;
    private String item;
    private String status; // SUCCESS, FAILED
    private String message;

    private LocalDateTime processedAt;
}

