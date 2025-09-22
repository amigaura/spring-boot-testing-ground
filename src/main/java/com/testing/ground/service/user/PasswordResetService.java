package com.testing.ground.service.user;

import com.testing.ground.request.user.PasswordResetRequest;
import com.testing.ground.response.user.PasswordResetResponse;

import java.util.Optional;

public interface PasswordResetService {
    PasswordResetResponse createResetRequest(PasswordResetRequest request);
    Optional<PasswordResetResponse> validateToken(String token, Long societyId);
    void markTokenAsUsed(String token, Long societyId, String updatedBy);
}
