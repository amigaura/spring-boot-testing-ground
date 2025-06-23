package com.testing.ground.entity.vendor;

import com.testing.ground.constant.vendor.ContractStatus;
import com.testing.ground.constant.vendor.ContractType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VendorContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // contract_id

    @Column(nullable = false)
    private Long vendorId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractType contractType; // SERVICE, SUPPLY, etc.

    @Column(nullable = false)
    private LocalDateTime contractStartDate;

    @Column(nullable = false)
    private LocalDateTime contractEndDate;

    @Lob
    private String contractTermsAndConditions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStatus contractStatus; // ACTIVE, EXPIRED, TERMINATED

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}
