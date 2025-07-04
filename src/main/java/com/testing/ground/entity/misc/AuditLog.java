package com.testing.ground.entity.misc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;

    @jakarta.persistence.Column(length = 65535)
    private String description;
    private String actor;
    private Long societyId;
    private LocalDateTime createdAt;

    public AuditLog() {}

    public AuditLog(String eventType, String description, String actor, Long societyId) {
        this.eventType = eventType;
        this.description = description;
        this.actor = actor;
        this.societyId = societyId;
        this.createdAt = LocalDateTime.now();
    }
}

