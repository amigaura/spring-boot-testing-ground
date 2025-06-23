package com.testing.ground.entity.society;

import com.testing.ground.constant.society.OccupancyStatus;
import com.testing.ground.constant.society.UnitStatus;
import com.testing.ground.constant.society.UnitType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // unit_id

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private Long blockId;

    @Column(nullable = false)
    private String name; // unit name

    private String address;
    private String addressLine1;
    private String addressLine2;
    private String doorNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pincode;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private Set<AppUserUnitMapping> users;

    @Enumerated(EnumType.STRING)
    private UnitType unitType; // APARTMENT, HOUSE, COMMERCIAL, etc.

    @Enumerated(EnumType.STRING)
    private UnitStatus unitStatus; // ACTIVE, INACTIVE, etc.

    private String description;

    private BigDecimal value; // monetary value of the unit

    private Double area; // unit area in numeric form
    private String areaUnit; // e.g., sqft, sqm

    // Image and media metadata
    private String image;
    private String imageUrl;
    private String imageAltText;
    private String imageCaption;
    private String imageDescription;

    @Enumerated(EnumType.STRING)
    private OccupancyStatus occupancyStatus; // OCCUPIED, VACANT, etc.

    private String intercomNumber;
    private String electricityMeterNumber;
    private String waterMeterNumber;
    private String gasMeterNumber;

    private Boolean availableForSale;
    private Boolean availableForRent;
    private Boolean availableForLease;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;

    // Getters, setters, constructors can be generated as needed
}

