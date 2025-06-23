package com.testing.ground.entity.society;

import com.testing.ground.constant.finance.TransactionMethod;
import com.testing.ground.constant.finance.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
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
}

