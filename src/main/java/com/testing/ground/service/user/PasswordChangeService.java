package com.testing.ground.service.user;

import com.testing.ground.entity.society.PasswordPolicy;
import com.testing.ground.entity.user.PasswordHistory;
import com.testing.ground.repository.user.PasswordHistoryRepository;
import com.testing.ground.repository.user.PasswordPolicyRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PasswordChangeService {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PasswordChangeService.class);

    @Autowired
    private PasswordHistoryRepository historyRepository;

    @Autowired
    private PasswordPolicyRepository passwordPolicyRepository;

    @Autowired
    UserCredentialService userCredentialService;

    public void validateAndChangePassword(Long societyId,
                                          Long userCredentialId,
                                          String newRawPassword,
                                          String newEncodedPassword,
                                          String updatedBy) {

        int historyLimit = passwordPolicyRepository.findBySocietyId(societyId)
                .map(PasswordPolicy::getHistoryLimit)
                .orElse(3); // Default fallback
        LOGGER.debug("Password history limit for society {}: {}", societyId, historyLimit);

        // Fetch last N passwords
        List<PasswordHistory> recentPasswords = historyRepository
                .findTopByUserCredentialIdAndSocietyId(userCredentialId, societyId, PageRequest.of(0, historyLimit));
        LOGGER.debug("Recent passwords for user {}: {}", userCredentialId, recentPasswords);

        // Check if new password matches any of the recent passwords
        for (PasswordHistory past : recentPasswords) {
            if (BCrypt.checkpw(newRawPassword, past.getPasswordHash())) {
                throw new RuntimeException("New password must not match the last " + historyLimit + " passwords.");
            }
        }
        LOGGER.debug("New password is valid and does not match recent passwords.");

        // Update password in your UserCredential table
        userCredentialService.updatePassword(userCredentialId, newEncodedPassword, Boolean.FALSE);

        // Save to history
        PasswordHistory history = new PasswordHistory(
                societyId,
                userCredentialId,
                newEncodedPassword,
                LocalDateTime.now(),
                updatedBy
        );
        historyRepository.save(history);
    }
}

