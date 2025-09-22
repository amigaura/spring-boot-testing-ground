package com.testing.ground.entity.society;

import com.testing.ground.constant.society.BookingStatus;
import com.testing.ground.constant.society.BookingType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class FacilityBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // booking_id

    @Column(nullable = false)
    private Long facilityId;

    @Column(nullable = false)
    private Long bookedByUserId;

    @Column(nullable = false)
    private Long bookedForUserId;

    private BigDecimal advancePayment;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingType bookingType; // SINGLE_USE, RECURRING

    @Column(nullable = false)
    private LocalDateTime bookingFrom;

    @Column(nullable = false)
    private LocalDateTime bookingTo;

    /** Duration in minutes */
    private Long bookingDuration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus; // CONFIRMED, PENDING, CANCELLED

    @Column(columnDefinition = "TEXT")
    private String details; // booking details

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

