package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "user_token")
//@Data
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private Long userId;
    private String tokenType;
    private String tokenValue;
    private LocalDateTime tokenExpiryDate;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;

}
