package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserPrivacyPolicyAcceptance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // acceptance_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String policyVersion; // e.g., "v1.0", "2024-05"

    @Column(nullable = false)
    private LocalDateTime acceptanceDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}


