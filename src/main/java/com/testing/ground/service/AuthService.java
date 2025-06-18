package com.testing.ground.service;

import com.testing.ground.entity.user.AppUser;
import com.testing.ground.entity.user.RefreshToken;
import com.testing.ground.repository.RefreshTokenRepository;
import com.testing.ground.repository.UserRepository;
import com.testing.ground.response.AuthResponse;
import com.testing.ground.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    public Map<String, String> login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String jwt = jwtUtil.generateToken(username);

            AppUser user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setUser(user);
            String refreshTokenStr = UUID.randomUUID().toString();
            refreshToken.setToken(refreshTokenStr);
            refreshToken.setExpiryDate(Instant.now().plus(Duration.ofDays(7)));

            refreshTokenRepository.save(refreshToken);

            return Map.of(
                    "accessToken", jwt,
                    "refreshToken", refreshTokenStr
            );

        } catch (BadCredentialsException ex) {
            throw new RuntimeException("Invalid username or password", ex);
        }
    }

    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }

}
