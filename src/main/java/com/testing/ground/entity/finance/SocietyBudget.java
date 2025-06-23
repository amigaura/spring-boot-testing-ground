package com.testing.ground.entity.finance;

import com.testing.ground.constant.finance.BudgetStatus;
import com.testing.ground.constant.finance.BudgetType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class SocietyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // budget_id

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BudgetType budgetType; // ANNUAL, QUARTERLY, etc.

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal budgetAmount;

    @Column(nullable = false)
    private LocalDateTime budgetPeriodStartDate;

    @Column(nullable = false)
    private LocalDateTime budgetPeriodEndDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BudgetStatus budgetStatus; // APPROVED, PENDING, etc.

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

