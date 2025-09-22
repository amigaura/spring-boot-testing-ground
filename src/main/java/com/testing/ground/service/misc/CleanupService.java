package com.testing.ground.service.misc;

import com.testing.ground.entity.misc.AuditLog;
import com.testing.ground.entity.user.PasswordReset;
import com.testing.ground.repository.misc.AuditLogRepository;
import com.testing.ground.repository.user.PasswordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CleanupService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    // Run every day at 2 AM
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanOldAuditLogs() {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(90); // keep 90 days
        List<AuditLog> oldLogs = auditLogRepository.findByCreatedAtBefore(cutoff);
        auditLogRepository.deleteAll(oldLogs);
    }

    // Run every hour
    @Scheduled(cron = "0 0 * * * ?")
    public void cleanExpiredResetTokens() {
        LocalDateTime now = LocalDateTime.now();
        List<PasswordReset> expired = passwordResetRepository
                .findByUsedFalseAndExpiresAtBefore(now);
        passwordResetRepository.deleteAll(expired);
    }
}

