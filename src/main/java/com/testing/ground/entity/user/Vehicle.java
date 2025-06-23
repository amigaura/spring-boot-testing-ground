package com.testing.ground.entity.user;

import com.testing.ground.constant.user.VehicleType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // vehicle_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long propertyId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType; // CAR, BIKE, TRUCK, etc.

    private String make; // e.g., Honda
    private String model; // e.g., City
    private Integer year; // manufacturing year
    private String color;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Lob
    private String insuranceDetails;

    private String image;
    private String imageUrl;
    private String imageAltText;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}
