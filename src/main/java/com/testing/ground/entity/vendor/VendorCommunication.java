package com.testing.ground.entity.vendor;

import com.testing.ground.constant.vendor.CommunicationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VendorCommunication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // communication_id

    @Column(nullable = false)
    private Long vendorId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationType communicationType; // EMAIL, PHONE, IN_PERSON, etc.

    @Column(nullable = false)
    private LocalDateTime communicationDate;

    @Lob
    private String communicationDetails;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

