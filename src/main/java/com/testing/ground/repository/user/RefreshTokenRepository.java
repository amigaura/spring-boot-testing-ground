package com.testing.ground.repository.user;

import com.testing.ground.entity.user.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.token = :token")
    Optional<RefreshToken> findByToken(String token);
}
