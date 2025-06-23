package com.testing.ground.entity.society;

import com.testing.ground.constant.society.NotificationStatus;
import com.testing.ground.constant.society.NotificationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // notification_id

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type; // ALERT, REMINDER, UPDATE, etc.

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status; // READ, UNREAD, ARCHIVED, etc.

    @Column(nullable = false)
    private LocalDateTime notificationCreatedDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;

    // Constructors
    public Notification() {}

    public Notification(Long societyId,
                        NotificationType type,
                        String title,
                        String message,
                        String createdBy) {
        this.societyId = societyId;
        this.type = type;
        this.title = title;
        this.message = message;
        this.status = NotificationStatus.UNREAD;
        this.notificationCreatedDate = LocalDateTime.now();
        this.createdBy = createdBy;
        this.createdDate = LocalDateTime.now();
    }
}

