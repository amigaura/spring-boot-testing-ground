package com.testing.ground.entity.vendor;

import com.testing.ground.constant.vendor.FeedbackStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VendorFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // feedback_id

    @Column(nullable = false)
    private Long vendorId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false, length = 1000)
    private String feedbackMessage;

    @Column(nullable = false)
    private Integer feedbackRating; // 1 to 5 stars

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeedbackStatus feedbackStatus; // NEW, IN_REVIEW, RESOLVED

    @Column(nullable = false)
    private LocalDateTime feedbackCreatedDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

