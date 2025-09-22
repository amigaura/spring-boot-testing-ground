package com.testing.ground.entity.society;

import com.testing.ground.constant.society.EventStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // event_id

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String name; // event name

    @Column(columnDefinition = "TEXT")
    private String description; // event description

    @Column(nullable = false)
    private LocalDateTime eventDateTime; // event date and time

    private String location; // event location

    // Image and media metadata
    private String image;
    private String imageUrl;
    private String imageAltText;
    private String imageCaption;
    private String imageDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status; // UPCOMING, ONGOING, COMPLETED

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

