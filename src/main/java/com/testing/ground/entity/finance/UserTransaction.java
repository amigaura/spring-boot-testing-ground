package com.testing.ground.entity.finance;

import com.testing.ground.constant.finance.TransactionMethod;
import com.testing.ground.constant.finance.TransactionStatus;
import com.testing.ground.constant.finance.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class UserTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // transaction_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long propertyId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType; // PAYMENT, REFUND, etc.

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal transactionAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionMethod transactionMethod; // CREDIT_CARD, BANK_TRANSFER, etc.

    private String transactionReferenceNumber;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus; // COMPLETED, PENDING, FAILED

    @Lob
    private String transactionDetails;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

