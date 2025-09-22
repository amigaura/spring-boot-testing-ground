package com.testing.ground.repository.user;

import com.testing.ground.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    // Find a user role by its name
    Optional<UserRole> findByName(String name);

    // Find a user role by society ID and name
    Optional<UserRole> findBySocietyIdAndName(Long societyId, String name);
}
