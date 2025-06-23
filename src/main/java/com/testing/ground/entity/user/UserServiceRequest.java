package com.testing.ground.entity.user;

import com.testing.ground.constant.user.RequestPriority;
import com.testing.ground.constant.user.RequestStatus;
import com.testing.ground.constant.user.RequestType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // user_service_request_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long propertyId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String title; // request title

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestType requestType; // MAINTENANCE, COMPLAINT, SUGGESTION, etc.

    @Lob
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus requestStatus; // OPEN, IN_PROGRESS, RESOLVED, CLOSED

    @Enumerated(EnumType.STRING)
    private RequestPriority requestPriority; // LOW, MEDIUM, HIGH

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

