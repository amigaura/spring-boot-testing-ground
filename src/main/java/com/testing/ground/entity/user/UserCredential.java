package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@ToString(exclude = "appUser")
public class UserCredential {
    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    private String passwordHash;
    private int failedLoginAttempts;
    private LocalDateTime lockoutUntil;
    private boolean isTemporary;
}

