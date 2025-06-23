package com.testing.ground.entity.society;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // tenant_id

    @Column(nullable = false)
    private Long unitId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String name; // tenant name

    private String phoneNumber;
    private String email;
    private String address;

    // Profile picture metadata
    private String profilePicture;        // file name or storage key
    private String profilePictureUrl;
    private String profilePictureAltText;
    private String profilePictureCaption;
    private String profilePictureDescription;

    @Column(nullable = false)
    private LocalDate leaseStartDate;

    @Column(nullable = false)
    private LocalDate leaseEndDate;

    @Column(nullable = false)
    private BigDecimal leaseAmount;

    @Column(nullable = false)
    private BigDecimal securityDeposit;

    // Audit fields
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;

}
