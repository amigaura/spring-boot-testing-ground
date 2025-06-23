package com.testing.ground.entity.society;

import com.testing.ground.constant.society.DiscussionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // discussion_id

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String topic;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscussionStatus status; // ACTIVE, CLOSED, etc.

    @Column(nullable = false)
    private Long createdByUserId; // user who started the discussion

    @Column(nullable = false)
    private LocalDateTime discussionCreatedDate;

    // Audit fields
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;    // system or user identifier
    private LocalDateTime createdDate;
}

