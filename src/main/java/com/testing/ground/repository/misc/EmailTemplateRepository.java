package com.testing.ground.repository.misc;

import com.testing.ground.entity.misc.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
    @Query("SELECT e FROM EmailTemplate e WHERE e.code = :code")
    Optional<EmailTemplate> findByCode(String code);
}
