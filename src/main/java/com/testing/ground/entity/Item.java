package com.testing.ground.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private String requestId;

    private String status; // SUCCESS, FAILED

    private String message;

    private LocalDateTime processedAt;

}
