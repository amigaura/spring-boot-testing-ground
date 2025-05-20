package com.testing.ground.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class ApiRequest {
    @Id
    private String requestId;

    @Lob
    private String requestData; // Store JSON or serialized items
    private String status; // PENDING, IN_PROGRESS, COMPLETED, FAILED
    private Date createdAt;
    private Date updatedAt;
}

