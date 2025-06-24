package com.testing.ground.entity.vendor;

import com.testing.ground.constant.vendor.DocumentType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VendorDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // document_id

    @Column(nullable = false)
    private Long vendorId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType; // e.g., AGREEMENT, LICENSE

    @Column(nullable = false)
    private String title; // document title

    @Column(length = 1000)
    private String description; // document description

    private String file;
    private String fileUrl;
    private String fileAltText;
    private String fileCaption;
    private String fileFileDescription;

    @Column(nullable = false)
    private LocalDateTime uploadDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

