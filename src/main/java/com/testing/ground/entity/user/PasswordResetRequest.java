package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PasswordResetRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // request id

    @Column(nullable = false)
    private Long societyId;  // tenant scope

    @Column(nullable = false)
    private Long userCredentialId;  // FK to UserCredential

    @Column(nullable = false, unique = true)
    private String token;  // secure reset token

    @Column(nullable = false)
    private LocalDateTime expiresAt;  // token expiry timestamp

    @Column(nullable = false)
    private Boolean used = false;  // whether token has been used

    @Column(nullable = false)
    private LocalDateTime requestedAt;  // when the reset was requested

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;

    // Constructors
    public PasswordResetRequest() { }

    public PasswordResetRequest(Long societyId,
                                Long userCredentialId,
                                String token,
                                LocalDateTime expiresAt,
                                String createdBy) {
        this.societyId = societyId;
        this.userCredentialId = userCredentialId;
        this.token = token;
        this.expiresAt = expiresAt;
        this.requestedAt = LocalDateTime.now();
        this.createdBy = createdBy;
        this.createdDate = LocalDateTime.now();
    }

}

