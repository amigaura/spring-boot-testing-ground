package com.testing.ground.entity.society;

import com.testing.ground.constant.society.AccessLevel;
import com.testing.ground.constant.society.DocumentCategory;
import com.testing.ground.constant.society.DocumentType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // document_id

    @Column(nullable = false)
    private Long unitId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;                 // LEASE_AGREEMENT, OWNERSHIP_PROOF, etc.

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentCategory category;                  // LEGAL, FINANCIAL, MAINTENANCE, etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessLevel accessLevel;                    // PUBLIC, PRIVATE, RESTRICTED

    @Column(nullable = false)
    private Boolean emailNotificationRequired;

    // File metadata (could store filename or identifier)
    private String fileName;
    private String fileUrl;
    private String fileAltText;
    private String fileCaption;

    @Column(length = 1000)
    private String fileDescription;

    @Column(nullable = false)
    private LocalDate uploadDate;

    private LocalDate expiryDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}
