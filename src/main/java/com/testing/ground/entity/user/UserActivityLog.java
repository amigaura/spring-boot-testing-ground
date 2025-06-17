package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "user_activity_log")
//@Data
public class UserActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    private Long userId;
    private String activityType;
    private LocalDateTime activityTimestamp;
    private String activityDetails;

}
