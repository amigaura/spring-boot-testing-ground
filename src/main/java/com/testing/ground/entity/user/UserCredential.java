package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserCredential {
    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(nullable = false)
    private Long societyId;

    private String passwordHash;
    private int failedLoginAttempts;
    private LocalDateTime lockoutUntil;
    private boolean isTemporary;
}

