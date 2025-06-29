package com.testing.ground.service.user;

import com.testing.ground.constant.misc.AppConstant;
import com.testing.ground.entity.user.AppUser;
import com.testing.ground.entity.user.AppUserSocietyMapping;
import com.testing.ground.entity.user.RefreshToken;
import com.testing.ground.repository.user.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {

    SecretKey SECRET_KEY = null;

    public JwtService() {
        // Generate a secure random key (or inject this key)
        this.SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + AppConstant.JWT_EXPIRATION_MS)) // 1 hour
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }

//    private final RefreshTokenRepository refreshTokenRepository;
//    private final JwtService jwtUtil;
//
//    public TokenService(RefreshTokenRepository repo, JwtService jwtUtil) {
//        this.refreshTokenRepository = repo;
//        this.jwtUtil = jwtUtil;
//    }

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AppUserSocietyMappingService mappingService;

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
        List<AppUserSocietyMapping> mappings = mappingService.getMappingsForUser(stored.getUser());
        if (mappings.isEmpty()) {
            throw new RuntimeException("No society mappings found for user");
        }
        AppUserSocietyMapping mapping = mappings.get(0);
        String newJwt = generateToken(stored.getUser(), mapping);

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

    public String generateToken(AppUser user, AppUserSocietyMapping mapping) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .claim("mappingId", mapping.getId())
                .claim("societyId", mapping.getSociety().getId())
                .setExpiration(new Date(System.currentTimeMillis() + AppConstant.JWT_EXPIRATION_MS)) // 1 hour
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token, String username) {
        System.out.println("Validating token for user: " + username);
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody().getExpiration();
        return expiration.before(new Date());
    }

    public Long extractSocietyId(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody();
        return claims.get("societyId", Long.class);
    }
}

