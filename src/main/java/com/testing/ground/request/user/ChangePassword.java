package com.testing.ground.request.user;

import lombok.Data;

@Data
public class ChangePassword {
    private Long societyId;
    private Long userCredentialId;
    private String newPassword;
    private String updatedBy;
}
