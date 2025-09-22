package com.testing.ground.entity.society;

import com.testing.ground.constant.society.ParkingAvailabilityStatus;
import com.testing.ground.constant.society.ParkingType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // parking_id

    @Column(nullable = false)
    private Long unitId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingType parkingType; // COVERED, UNCOVERED

    @Column(nullable = false)
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingAvailabilityStatus availabilityStatus; // AVAILABLE, OCCUPIED

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

