package com.testing.ground.entity.society;

import com.testing.ground.constant.society.FacilityAvailabilityStatus;
import com.testing.ground.constant.society.FacilityType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // facility_id

    @Column(nullable = false)
    private Long unitId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FacilityType facilityType; // PARTY_AREA, THEATRE, COMMUNITY_HALL, etc.

    private String description;

    // Image and media metadata
    private String image;
    private String imageUrl;
    private String imageAltText;
    private String imageCaption;
    private String imageDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FacilityAvailabilityStatus availabilityStatus; // AVAILABLE, UNDER_MAINTENANCE

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

