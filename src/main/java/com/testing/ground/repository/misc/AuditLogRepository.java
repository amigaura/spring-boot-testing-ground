package com.testing.ground.repository.misc;

import com.testing.ground.entity.misc.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByActor(String actor);

    List<AuditLog> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);

    List<AuditLog> findByCreatedAtBefore(LocalDateTime cutoff);

    @Query("SELECT a FROM AuditLog a WHERE (:actor IS NULL OR a.actor = :actor) " +
            "AND (:startDate IS NULL OR a.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR a.createdAt <= :endDate)")
    List<AuditLog> findFiltered(@Param("actor") String actor,
                                @Param("startDate") LocalDateTime startDate,
                                @Param("endDate") LocalDateTime endDate);

}
