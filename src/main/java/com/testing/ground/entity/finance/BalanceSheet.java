package com.testing.ground.entity.finance;

import com.testing.ground.constant.finance.BalanceSheetType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class BalanceSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // balance_sheet_id

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BalanceSheetType balanceSheetType; // MONTHLY, YEARLY, etc.

    @Column(nullable = false)
    private LocalDateTime periodStartDate;

    @Column(nullable = false)
    private LocalDateTime periodEndDate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalIncome;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalExpenses;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal netBalance;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;

    // Constructors, getters, setters omitted for brevity
}

