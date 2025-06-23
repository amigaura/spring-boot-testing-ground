package com.testing.ground.entity.vendor;

import com.testing.ground.constant.vendor.ServiceStatus;
import com.testing.ground.constant.vendor.ServiceType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VendorServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // service_request_id

    @Column(nullable = false)
    private Long vendorId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType; // e.g., MAINTENANCE, CLEANING

    @Lob
    private String serviceDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceStatus serviceStatus; // REQUESTED, IN_PROGRESS, COMPLETED

    @Column(nullable = false)
    private LocalDateTime serviceRequestedDate;

    private LocalDateTime serviceCompletionDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

