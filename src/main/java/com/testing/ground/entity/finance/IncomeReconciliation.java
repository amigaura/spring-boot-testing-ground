package com.testing.ground.entity.finance;

import com.testing.ground.constant.finance.ReconciliationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class IncomeReconciliation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // reconciliation_id

    @Column(nullable = false)
    private Long incomeId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private LocalDateTime reconciliationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReconciliationStatus reconciliationStatus; // RECONCILED, PENDING

    @Lob
    private String reconciliationDetails;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

