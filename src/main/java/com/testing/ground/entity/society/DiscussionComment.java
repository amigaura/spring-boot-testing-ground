package com.testing.ground.entity.society;

import com.testing.ground.constant.society.CommentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class DiscussionComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // comment_id

    @Column(nullable = false)
    private Long discussionId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private Long appUserId;

    @Lob
    @Column(nullable = false)
    private String commentText;

    @Column(nullable = false)
    private LocalDateTime commentCreatedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentStatus status; // VISIBLE, HIDDEN

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

