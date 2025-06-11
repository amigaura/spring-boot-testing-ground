package com.testing.ground.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
//    private String SECRET_KEY = "mysecretkey";

    SecretKey SECRET_KEY = null;
    public JwtUtil() {
        // Generate a secure random key (or inject this key)
        this.SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

//    public JwtUtil() {
//        String base64EncodedKey = "your_very_long_base64_encoded_secret_key_here";
//        byte[] keyBytes = Base64.getDecoder().decode(base64EncodedKey);
//        SecretKey SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
//    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token, String username) {
        System.out.println("Validating token for user: " + username);
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody().getExpiration();
        return expiration.before(new Date());
    }
}

