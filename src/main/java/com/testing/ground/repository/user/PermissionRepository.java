package com.testing.ground.repository.user;

import com.testing.ground.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    // Get a permission by its name
    Optional<Permission> findByName(String name);

    Optional<Permission> findBySocietyIdAndName(Long societyId, String name);
}
