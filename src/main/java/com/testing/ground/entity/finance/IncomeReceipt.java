package com.testing.ground.entity.finance;

import com.testing.ground.constant.finance.ReceiptStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class IncomeReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // receipt_id

    @Column(nullable = false)
    private Long incomeId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false, unique = true)
    private String receiptNumber;

    @Column(nullable = false)
    private LocalDateTime receiptDate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal receiptAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReceiptStatus receiptStatus; // ISSUED, PENDING, etc.

    @Column(length = 1000)
    private String receiptDetails;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

