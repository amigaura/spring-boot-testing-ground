package com.testing.ground.repository.misc;

import com.testing.ground.entity.misc.EmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRequestRepository extends JpaRepository<EmailRequest, Long> {
    List<EmailRequest> findByStatus(EmailRequest.Status status);
}

