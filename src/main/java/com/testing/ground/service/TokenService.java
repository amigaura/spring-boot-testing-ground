package com.testing.ground.service;

import com.testing.ground.entity.user.RefreshToken;
import com.testing.ground.repository.RefreshTokenRepository;
import com.testing.ground.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

//    @Autowired
//    private RefreshTokenRepository refreshTokenRepository;

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public TokenService(RefreshTokenRepository repo, JwtUtil jwtUtil) {
        this.refreshTokenRepository = repo;
        this.jwtUtil = jwtUtil;
    }

    public Map<String, String> refreshToken(String token) {
        RefreshToken stored = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (stored.isRevoked() || stored.getExpiryDate().isBefore(Instant.now())) {
            throw new RuntimeException("Token expired or revoked");
        }

        // Revoke the current token
        stored.setRevoked(true);
        refreshTokenRepository.save(stored);

        // Create and persist a new refresh token
        String newRefreshTokenStr = UUID.randomUUID().toString();
        RefreshToken newToken = new RefreshToken();
        newToken.setUser(stored.getUser());
        newToken.setToken(newRefreshTokenStr);
        newToken.setExpiryDate(Instant.now().plus(Duration.ofDays(7)));
        refreshTokenRepository.save(newToken);

        // Generate new JWT
        String newJwt = jwtUtil.generateToken(stored.getUser().getUsername());

        return Map.of(
                "accessToken", newJwt,
                "refreshToken", newRefreshTokenStr
        );
    }

    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }

}



