package com.testing.ground.repository.user;

import com.testing.ground.entity.society.PasswordPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordPolicyRepository extends JpaRepository<PasswordPolicy, Long> {
    Optional<PasswordPolicy> findBySocietyId(Long societyId);
}
