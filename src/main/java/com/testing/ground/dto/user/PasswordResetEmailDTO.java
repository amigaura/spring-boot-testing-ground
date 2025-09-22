package com.testing.ground.dto.user;

import lombok.Data;

@Data
public class PasswordResetEmailDTO {
    private String recipientEmail;
    private String token;
    private Long societyId;
    private String subject;
    private String resetLink;  // full URL to frontend reset page
}

