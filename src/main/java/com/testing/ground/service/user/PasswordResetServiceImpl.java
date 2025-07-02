package com.testing.ground.service.user;

import com.testing.ground.dto.user.PasswordResetEmailDTO;
import com.testing.ground.entity.user.AppUser;
import com.testing.ground.entity.user.PasswordReset;
import com.testing.ground.repository.user.AppUserRepository;
import com.testing.ground.repository.user.PasswordPolicyRepository;
import com.testing.ground.repository.user.PasswordResetRepository;
import com.testing.ground.request.user.PasswordResetRequest;
import com.testing.ground.response.user.PasswordResetResponse;
import com.testing.ground.service.misc.EmailService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PasswordResetServiceImpl.class);

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    @Autowired
    private PasswordPolicyRepository passwordPolicyRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppUserRepository appUserRepository; // Or user detail repo to get email

    @Autowired
    private AuditLogger auditLogger;

    @Value("${app.reset-password.base-url}")
    private String resetBaseUrl;

    @Override
    public PasswordResetResponse createResetRequest(PasswordResetRequest dto) {
        try {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiry = calculateExpiry(dto.getSocietyId());
            LOGGER.debug("Creating password reset request for user: {}, token: {}, expires at: {}",
                    dto.getUserCredentialId(), token, expiry);

            PasswordReset entity = new PasswordReset(
                    dto.getSocietyId(),
                    dto.getUserCredentialId(),
                    token,
                    expiry,
                    dto.getCreatedBy()
            );

            auditLogger.log(
                    "PASSWORD_RESET_REQUEST",
                    "Reset token generated for user " + dto.getUserCredentialId(),
                    dto.getCreatedBy(),
                    dto.getSocietyId()
            );

            LOGGER.debug("Saving password reset entity: {}", entity);
            PasswordReset saved = passwordResetRepository.save(entity);

            AppUser user = appUserRepository.findById(dto.getUserCredentialId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String link = resetBaseUrl + "?token=" + token + "&societyId=" + dto.getSocietyId();
            LOGGER.debug("Sending password reset email to: {}, link: {}", user.getUserDetail().getEmail(), link);

            if (user.getUserDetail().getEmail() == null) {
                throw new RuntimeException("User email not found for user: " + dto.getUserCredentialId());
            }
            emailService.sendPasswordResetEmail(new PasswordResetEmailDTO() {{
                setRecipientEmail(user.getUserDetail().getEmail());
                setToken(token);
                setSocietyId(dto.getSocietyId());
                setSubject("Reset Your Password");
                setResetLink(link);
            }});
            auditLogger.log("PASSWORD_RESET_EMAIL", "Reset email sent", dto.getCreatedBy(), dto.getSocietyId());
            return mapToResponseDTO(saved);
        } catch (RuntimeException e) {
            // Log the error and rethrow as a runtime exception
            LOGGER.error("Error creating password reset request for user: {}, error: {}", dto.getUserCredentialId(), e.getMessage());
            LOGGER.error("Exception stack trace:", e);
            throw new RuntimeException(e);
        }
    }

    private LocalDateTime calculateExpiry(Long societyId) {
        // Default to 30 minutes if policy not found or not configured
        int defaultExpiryMinutes = 30;

        return passwordPolicyRepository.findBySocietyId(societyId)
                .map(policy -> {
                    // Option 1: Reuse passwordExpiryDays (less ideal)
                     return LocalDateTime.now().plusDays(policy.getPasswordExpiryDays());

                    // Option 2: Add new field for token expiry if needed:
                    // return LocalDateTime.now().plusMinutes(policy.getResetTokenExpiryMinutes());

//                    return LocalDateTime.now().plusMinutes(defaultExpiryMinutes); // placeholder
                })
                .orElse(LocalDateTime.now().plusMinutes(defaultExpiryMinutes));
    }

    @Override
    public Optional<PasswordResetResponse> validateToken(String token, Long societyId) {
        return passwordResetRepository.findByTokenAndSocietyId(token, societyId)
                .filter(r -> !r.getUsed() && r.getExpiresAt().isAfter(LocalDateTime.now()))
                .map(this::mapToResponseDTO);
    }

    @Override
    public void markTokenAsUsed(String token, Long societyId, String updatedBy) {
        PasswordReset entity = passwordResetRepository.findByTokenAndSocietyId(token, societyId)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (entity.getUsed()) {
            throw new RuntimeException("Token already used");
        }

        entity.setUsed(true);
        entity.setLastUpdated(LocalDateTime.now());
        entity.setLastUpdatedBy(updatedBy);
        passwordResetRepository.save(entity);
    }

    private PasswordResetResponse mapToResponseDTO(PasswordReset entity) {
        PasswordResetResponse dto = new PasswordResetResponse();
        dto.setId(entity.getId());
        dto.setSocietyId(entity.getSocietyId());
        dto.setUserCredentialId(entity.getUserCredentialId());
        dto.setToken(entity.getToken());
        dto.setExpiresAt(entity.getExpiresAt());
        dto.setUsed(entity.getUsed());
        dto.setRequestedAt(entity.getRequestedAt());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
