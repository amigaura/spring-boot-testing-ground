package com.testing.ground.entity.finance;

import com.testing.ground.constant.finance.ExpenseStatus;
import com.testing.ground.constant.finance.ExpenseType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class SocietyExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // expense_id

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseType expenseType; // MAINTENANCE, UTILITIES, etc.

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal expenseAmount;

    @Column(nullable = false)
    private LocalDateTime expenseDate;

    @Column(length = 1000)
    private String expenseDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseStatus expenseStatus; // PAID, PENDING, etc.

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

