package com.testing.ground.entity.user;

import com.testing.ground.constant.user.BadgeType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // badge_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BadgeType badgeType; // ACHIEVEMENT, PARTICIPATION, etc.

    private String badgeDescription;

    @Column(nullable = false)
    private LocalDateTime badgeAwardedDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}
