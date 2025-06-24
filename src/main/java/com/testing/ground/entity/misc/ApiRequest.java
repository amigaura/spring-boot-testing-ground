package com.testing.ground.entity.misc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class ApiRequest {
    @Id
    private String requestId;

    @Column(columnDefinition = "TEXT")
    private String requestData; // Store JSON or serialized items
    private String status; // PENDING, IN_PROGRESS, COMPLETED, FAILED
    private Date createdAt;
    private Date updatedAt;
}

