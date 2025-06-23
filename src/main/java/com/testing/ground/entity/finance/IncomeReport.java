package com.testing.ground.entity.finance;

import com.testing.ground.constant.society.ReportStatus;
import com.testing.ground.constant.society.ReportType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class IncomeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // report_id

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportType reportType; // MONTHLY, YEARLY, etc.

    @Column(nullable = false)
    private Long generatedByUserId;

    @Column(nullable = false)
    private LocalDateTime reportGeneratedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus reportStatus; // PENDING, COMPLETED, etc.

    @Lob
    private String reportDetails; // Could store summary, metrics, or JSON blob

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;

    // Constructors, getters, setters omitted for brevity
}

