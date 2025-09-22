package com.testing.ground.entity.society;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PasswordPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // policy id

    @Column(nullable = false)
    private Long societyId; // tenant scope

    @Column(nullable = false)
    private Integer minLength;

    private Integer maxLength;

    @Column(nullable = false)
    private Boolean requireUppercase;

    @Column(nullable = false)
    private Boolean requireDigit;

    @Column(nullable = false)
    private Boolean requireSpecial;

    @Column(nullable = false)
    private Integer passwordExpiryDays;

    @Column(nullable = false)
    private Integer historyLimit;

    @Column(nullable = false)
    private Integer lockoutThreshold;

    @Column(nullable = false)
    private Integer lockoutDurationMinutes;

    // Audit fields
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;

    // Constructors
    public PasswordPolicy() {}

    public PasswordPolicy(Long societyId,
                          Integer minLength,
                          Integer maxLength,
                          Boolean requireUppercase,
                          Boolean requireDigit,
                          Boolean requireSpecial,
                          Integer passwordExpiryDays,
                          Integer historyLimit,
                          Integer lockoutThreshold,
                          Integer lockoutDurationMinutes,
                          String createdBy) {
        this.societyId = societyId;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.requireUppercase = requireUppercase;
        this.requireDigit = requireDigit;
        this.requireSpecial = requireSpecial;
        this.passwordExpiryDays = passwordExpiryDays;
        this.historyLimit = historyLimit;
        this.lockoutThreshold = lockoutThreshold;
        this.lockoutDurationMinutes = lockoutDurationMinutes;
        this.createdBy = createdBy;
        this.createdDate = LocalDateTime.now();
    }
}
