package com.testing.ground.constant.vendor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_feedbacks")
public class VendorFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // feedback_id

    @Column(nullable = false)
    private Long vendorId;

    @Column(nullable = false)
    private Long societyId;

    @Lob
    @Column(nullable = false)
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

    // Constructors, getters, setters omitted for brevity
}

