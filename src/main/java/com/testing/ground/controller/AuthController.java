package com.testing.ground.controller;

import com.testing.ground.request.AuthRequest;
import com.testing.ground.request.LogoutRequest;
import com.testing.ground.service.AuthService;
import com.testing.ground.service.TokenService;
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
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest.getUsername(), authRequest.getPassword()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(tokenService.refreshToken(body.get("refreshToken")));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        tokenService.revokeRefreshToken(request.getRefreshToken());
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
