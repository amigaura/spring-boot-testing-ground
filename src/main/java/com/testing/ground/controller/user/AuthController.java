package com.testing.ground.controller.user;

import com.testing.ground.entity.user.AppUserSocietyMapping;
import com.testing.ground.request.user.AuthRequest;
import com.testing.ground.request.user.LogoutRequest;
import com.testing.ground.request.user.SocietySelectionRequest;
import com.testing.ground.response.user.AuthResponse;
import com.testing.ground.service.user.AppUserSocietyMappingService;
import com.testing.ground.service.user.AuthService;
import com.testing.ground.service.user.JwtService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @Autowired
    AppUserSocietyMappingService mappingService;

    @Autowired
    private JwtService jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
        AppUserSocietyMapping mapping = authService.registerUser(authRequest.getSocietyId(),
                authRequest.getUsername(), authRequest.getPassword());
        String accessToken = jwtUtil.generateToken(mapping.getAppUser(), mapping);
        String refreshToken = authService.generateRefreshToken(mapping.getAppUser());
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return authService.authenticateUser(request.getUsername(), request.getPassword());
    }

    @PostMapping("/select-society")
    public ResponseEntity<?> selectSociety(@RequestBody SocietySelectionRequest request) {
        AppUserSocietyMapping mapping = mappingService.getMappingById(request.getMappingId());
        String accessToken = jwtUtil.generateToken(mapping.getAppUser(), mapping);
        String refreshToken = authService.generateRefreshToken(mapping.getAppUser());
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(jwtUtil.refreshToken(body.get("refreshToken")));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        jwtUtil.revokeRefreshToken(request.getRefreshToken());
        return ResponseEntity.ok("Logged out successfully");
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
