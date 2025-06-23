package com.testing.ground.entity.vendor;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VendorRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // rating_id

    @Column(nullable = false)
    private Long vendorId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private Integer ratingScore; // 1 to 5 stars

    @Lob
    private String ratingComment;

    @Column(nullable = false)
    private LocalDateTime ratingDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

