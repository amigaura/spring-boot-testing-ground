package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

//@Entity
//@Table(name = "user_role_permission")
//@Data
public class UserRolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole userRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_by_user_id")
    private AppUser assignedBy;

    @Column(name = "assigned_date")
    private LocalDateTime assignedDate;
}