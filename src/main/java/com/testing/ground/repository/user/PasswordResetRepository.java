package com.testing.ground.repository.user;

import com.testing.ground.entity.user.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {
    Optional<PasswordReset> findByTokenAndSocietyId(String token, Long societyId);

    List<PasswordReset> findByUsedFalseAndExpiresAtBefore(LocalDateTime now);
}
