package com.testing.ground.constant.finance;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "income_payments")
public class IncomePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // payment_id

    @Column(nullable = false)
    private Long incomeId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionMethod transactionMethod; // BANK_TRANSFER, CASH, etc.

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus; // COMPLETED, PENDING, FAILED, etc.

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;

    // Constructors, getters, setters omitted for brevity
}

