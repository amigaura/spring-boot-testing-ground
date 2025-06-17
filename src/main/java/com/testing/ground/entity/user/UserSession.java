package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "user_session")
//@Data
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    private Long userId;
    private LocalDateTime sessionStartTime;
    private LocalDateTime sessionEndTime;
    private String sessionDeviceType;
    private String sessionIpAddress;
    private String sessionStatus;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;

}
