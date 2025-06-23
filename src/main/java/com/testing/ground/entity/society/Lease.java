package com.testing.ground.entity.society;

import com.testing.ground.constant.society.LeaseStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // lease_id

    @Column(nullable = false)
    private Long unitId;

    @Column(nullable = false)
    private Long tenantId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private LocalDate leaseStartDate;

    @Column(nullable = false)
    private LocalDate leaseEndDate;

    @Column(nullable = false)
    private BigDecimal leaseAmount;

    @Column(nullable = false)
    private BigDecimal securityDeposit;

    @Lob
    private String termsAndConditions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaseStatus status; // ACTIVE, TERMINATED, PENDING

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

