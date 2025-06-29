package com.testing.ground.repository.user;

import com.testing.ground.entity.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT u FROM AppUser u WHERE u.username = :username")
    public Optional<AppUser> findByUsername(String username);

    @Query("SELECT u FROM AppUser u WHERE u.societyId = :societyId AND u.username = :username")
    public Optional<AppUser> findBySocietyIdAndUsername(Long societyId, String username);

    boolean existsByUsername(String username);

    boolean existsBySocietyIdAndUsername(Long societyId, String username);
}
