package com.testing.ground.service.user;

import com.testing.ground.entity.user.UserCredential;
import com.testing.ground.repository.user.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public void updatePassword(Long appUserId, String newEncodedPassword, boolean isTemporary) {
        UserCredential credential = userCredentialRepository.findByAppUserId(appUserId)
                .orElseThrow(() -> new RuntimeException("UserCredential not found for AppUser ID: " + appUserId));

        credential.setPasswordHash(newEncodedPassword);
        credential.setTemporary(isTemporary);
        credential.setFailedLoginAttempts(0);
        credential.setLockoutUntil(null);
        userCredentialRepository.save(credential);
    }

    @Override
    public Optional<UserCredential> getByUserId(Long appUserId) {
        return userCredentialRepository.findByAppUserId(appUserId);
    }

    @Override
    public void resetFailedAttempts(Long appUserId) {
        userCredentialRepository.findByAppUserId(appUserId).ifPresent(uc -> {
            uc.setFailedLoginAttempts(0);
            uc.setLockoutUntil(null);
            userCredentialRepository.save(uc);
        });
    }

    @Override
    public void incrementFailedAttempts(Long appUserId) {
        userCredentialRepository.findByAppUserId(appUserId).ifPresent(uc -> {
            uc.setFailedLoginAttempts(uc.getFailedLoginAttempts() + 1);
            userCredentialRepository.save(uc);
        });
    }

    @Override
    public void lockUser(Long appUserId, int lockoutMinutes) {
        userCredentialRepository.findByAppUserId(appUserId).ifPresent(uc -> {
            uc.setLockoutUntil(LocalDateTime.now().plusMinutes(lockoutMinutes));
            userCredentialRepository.save(uc);
        });
    }
}

