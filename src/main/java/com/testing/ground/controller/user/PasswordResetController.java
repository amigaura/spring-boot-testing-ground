package com.testing.ground.controller.user;

import com.testing.ground.request.user.ChangePassword;
import com.testing.ground.request.user.PasswordResetRequest;
import com.testing.ground.response.user.PasswordResetResponse;
import com.testing.ground.service.user.PasswordChangeService;
import com.testing.ground.service.user.PasswordResetService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PasswordResetController.class);

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private PasswordChangeService passwordChangeService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePasswordRequest) {
        String encoded = passwordEncoder.encode(changePasswordRequest.getNewPassword());

        passwordChangeService.validateAndChangePassword(
                changePasswordRequest.getSocietyId(),
                changePasswordRequest.getUserCredentialId(),
                changePasswordRequest.getNewPassword(),
                encoded,
                changePasswordRequest.getUpdatedBy()
        );
        return ResponseEntity.ok("Password changed successfully");
    }


    @PostMapping("/request")
    public ResponseEntity<PasswordResetResponse> createResetRequest(@RequestBody PasswordResetRequest request) {
        return ResponseEntity.ok(passwordResetService.createResetRequest(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<PasswordResetResponse> validateToken(
            @RequestParam String token,
            @RequestParam Long societyId) {
        return passwordResetService.validateToken(token, societyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/consume")
    public ResponseEntity<Void> consumeToken(
            @RequestParam String token,
            @RequestParam Long societyId,
            @RequestParam String updatedBy) {
        passwordResetService.markTokenAsUsed(token, societyId, updatedBy);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        LOGGER.error("An unexpected error occurred: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(Exception ex) {
        LOGGER.error("An unexpected runtime error occurred: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }
}

