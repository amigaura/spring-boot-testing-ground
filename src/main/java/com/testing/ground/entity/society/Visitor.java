package com.testing.ground.entity.society;

import com.testing.ground.constant.society.IdProofType;
import com.testing.ground.constant.society.ScheduleStatus;
import com.testing.ground.constant.society.VisitStatus;
import com.testing.ground.constant.society.VisitorType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // visitor_id

    @Column(nullable = false)
    private Long unitId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String name;

    private String phoneNumber;
    private String email;
    private String address;

    @Column(nullable = false)
    private LocalDateTime visitDateTime;

    private String visitPurpose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitorType visitorType;           // GUEST, SERVICE_PROVIDER, etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdProofType idProofType;           // AADHAR, PASSPORT, DRIVING_LICENSE, etc.

    private String idProofNumber;

    // ID proof image metadata
    private String idProofImage;
    private String idProofImageUrl;

    @Column(nullable = false)
    private LocalDateTime scheduleDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleStatus scheduleStatus;     // CONFIRMED, PENDING, CANCELLED

    @Lob
    private String notes;

    // Visitor photo metadata
    private String photo;
    private String photoUrl;

    // Host details
    private Long hostUserId;
    private String hostUserName;
    private String hostUserPhone;
    private String hostUserEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitStatus visitStatus;           // SCHEDULED, COMPLETED, CANCELLED

    // Audit fields
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

