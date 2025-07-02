package com.testing.ground.response.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PasswordResetResponse {
    private Long id;
    private Long societyId;
    private Long userCredentialId;
    private String token;
    private LocalDateTime expiresAt;
    private Boolean used;
    private LocalDateTime requestedAt;
    private LocalDateTime createdDate;
}
