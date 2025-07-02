package com.testing.ground.service.user;

public interface AuditLogger {
    void log(String eventType, String description, String actor, Long societyId);
}
