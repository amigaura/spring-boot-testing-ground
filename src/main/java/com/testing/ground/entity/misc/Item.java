package com.testing.ground.entity.misc;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String name;

    private String description;

    private double price;

    private String requestId;

    private String status; // SUCCESS, FAILED

    private String message;

    private LocalDateTime processedAt;

}
