package com.testing.ground.entity.society;
import com.testing.ground.constant.society.UnitOccupancyRole;
import com.testing.ground.entity.user.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AppUserUnitMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(nullable = false)
    private Long societyId; // for tenant isolation

    @Enumerated(EnumType.STRING)
    private UnitOccupancyRole role; // OWNER, TENANT, RESIDENT

    private Boolean isPrimaryUnit = false;

    private LocalDateTime joinedAt;
    private LocalDateTime vacatedAt;

    private Boolean active = true;

    private String createdBy;
    private String lastUpdatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;
}

