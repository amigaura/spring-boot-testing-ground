package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PasswordHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // history record id

    @Column(nullable = false)
    private Long societyId;  // tenant scope

    @Column(nullable = false)
    private Long userCredentialId;  // FK to UserCredential

    @Column(nullable = false)
    private String passwordHash;  // previous password hash

    @Column(nullable = false)
    private LocalDateTime changedAt;  // when the password was changed

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;

    // Constructors
    public PasswordHistory() {}

    public PasswordHistory(Long societyId,
                           Long userCredentialId,
                           String passwordHash,
                           LocalDateTime changedAt,
                           String createdBy) {
        this.societyId = societyId;
        this.userCredentialId = userCredentialId;
        this.passwordHash = passwordHash;
        this.changedAt = changedAt;
        this.createdBy = createdBy;
        this.createdDate = LocalDateTime.now();
    }

}

