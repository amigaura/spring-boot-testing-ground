package com.testing.ground.entity.finance;

import com.testing.ground.constant.finance.IncomeStatus;
import com.testing.ground.constant.finance.IncomeType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class SocietyIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // income_id

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncomeType incomeType; // e.g., MAINTENANCE_FEE, PARKING_FEE

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal incomeAmount;

    @Column(nullable = false)
    private LocalDateTime incomeDate;

    @Lob
    private String incomeDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncomeStatus incomeStatus; // RECEIVED, PENDING

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

