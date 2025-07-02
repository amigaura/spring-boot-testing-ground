package com.testing.ground.service.misc;

import com.testing.ground.dto.user.PasswordResetEmailDTO;

public interface EmailService {
    void sendPasswordResetEmail(PasswordResetEmailDTO dto);
}
