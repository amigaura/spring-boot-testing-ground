package com.testing.ground.entity.user;


import jakarta.persistence.*;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "user_setting")
public class UserSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;

    private Long userId;
    private String settingType;
    private String settingValue;
    private String settingDescription;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;

    // Getters, Setters, Constructors
}
