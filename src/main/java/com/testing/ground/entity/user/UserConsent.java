package com.testing.ground.entity.user;

import com.testing.ground.constant.user.ConsentStatus;
import com.testing.ground.constant.user.ConsentType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserConsent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // consent_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsentType consentType; // MARKETING, DATA_SHARING, etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsentStatus consentStatus; // GRANTED, REVOKED

    @Column(nullable = false)
    private LocalDateTime consentDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

