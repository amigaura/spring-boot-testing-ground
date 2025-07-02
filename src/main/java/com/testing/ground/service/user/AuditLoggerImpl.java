package com.testing.ground.service.user;

import com.testing.ground.entity.misc.AuditLog;
import com.testing.ground.repository.misc.AuditLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditLoggerImpl implements AuditLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLoggerImpl.class);

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public void log(String eventType, String description, String actor, Long societyId) {
        LOGGER.info("AUDIT | type={} | actor={} | desc={}", eventType, actor, description);
        AuditLog entry = new AuditLog(eventType, description, actor, societyId);
        auditLogRepository.save(entry);
    }
}

