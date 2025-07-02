package com.testing.ground.request.user;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private Long societyId;
    private Long userCredentialId;
    private String createdBy;
}

