package com.testing.ground.entity.society;

import com.testing.ground.constant.society.FeedbackStatus;
import com.testing.ground.constant.society.FeedbackType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class SocietyFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // feedback_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeedbackType feedbackType; // SUGGESTION, COMPLAINT, COMPLIMENT, etc.

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
}
