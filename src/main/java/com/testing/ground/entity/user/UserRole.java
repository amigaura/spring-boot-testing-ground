package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long societyId;

    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(name = "user_role_permissions", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "permission_id")
    private Set<Long> permissionIds;

    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
}

