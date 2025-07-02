package com.testing.ground.repository.user;

import com.testing.ground.entity.user.PasswordHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {

    @Query("SELECT ph FROM PasswordHistory ph WHERE ph.userCredentialId = :userId AND ph.societyId = :societyId ORDER BY ph.changedAt DESC")
    List<PasswordHistory> findTopByUserCredentialIdAndSocietyId(@Param("userId") Long userCredentialId,
                                                                @Param("societyId") Long societyId,
                                                                Pageable pageable);
}

