package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppUser user;

    @Column(nullable = false, unique = true)
    private String token;

    private Instant expiryDate;

    private boolean revoked = false;
}
