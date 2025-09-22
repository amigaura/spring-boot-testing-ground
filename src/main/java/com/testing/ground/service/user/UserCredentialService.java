package com.testing.ground.service.user;

import com.testing.ground.entity.user.UserCredential;

import java.util.Optional;

public interface UserCredentialService {
    void updatePassword(Long appUserId, String newEncodedPassword, boolean isTemporary);

    Optional<UserCredential> getByUserId(Long appUserId);

    void resetFailedAttempts(Long appUserId);

    void incrementFailedAttempts(Long appUserId);

    void lockUser(Long appUserId, int lockoutMinutes);
}

