package com.testing.ground.entity.society;

import com.testing.ground.constant.society.ReportStatus;
import com.testing.ground.constant.society.ReportType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // report_id

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportType reportType; // USER_ACTIVITY, UNIT_STATUS, etc.

    @Column(nullable = false)
    private Long generatedByUserId;

    @Column(nullable = false)
    private LocalDateTime generatedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus status; // PENDING, COMPLETED, FAILED, etc.

    @Column(columnDefinition = "text")
    private String details; // JSON or text details of the report

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}
