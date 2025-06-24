package com.testing.ground.entity.society;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // suggestion_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long societyId;

    @Column(columnDefinition = "TEXT")
    private String detail;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;

    // Constructors
    public Suggestion() { }

    public Suggestion(Long appUserId, Long societyId, String detail, String createdBy) {
        this.appUserId = appUserId;
        this.societyId = societyId;
        this.detail = detail;
        this.createdBy = createdBy;
        this.createdDate = LocalDateTime.now();
    }
}
