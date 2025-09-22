package com.testing.ground.entity.society;

import com.testing.ground.constant.society.PollStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // poll_id

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String question;

    @ElementCollection
    @CollectionTable(name = "poll_options", joinColumns = @JoinColumn(name = "poll_id"))
    @Column(name = "option_text")
    private List<String> options;

    @Column(nullable = false)
    private Long createdByUserId;    // poll created by user id

    @Column(nullable = false)
    private LocalDateTime pollCreatedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PollStatus status;       // ACTIVE, CLOSED, etc.

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;         // audit: user or system
    private LocalDateTime createdDate;
}
