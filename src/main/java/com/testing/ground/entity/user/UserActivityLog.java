package com.testing.ground.entity.user;

import com.testing.ground.constant.user.ActivityType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class UserActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // activity_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType; // LOGIN, LOGOUT, UPDATE_PROFILE, etc.

    @Column(nullable = false)
    private LocalDateTime activityTimestamp;

    @Lob
    private String activityDetails;

    private String affectedEntityType; // e.g., "user", "property", "request"
    private Long affectedEntityId;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

