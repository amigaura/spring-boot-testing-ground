package com.testing.ground.entity.finance;

import com.testing.ground.constant.finance.TransactionMethod;
import com.testing.ground.constant.finance.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class VendorPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // payment_id

    @Column(nullable = false)
    private Long vendorId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionMethod transactionMethod; // e.g., BANK_TRANSFER, CASH

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus; // COMPLETED, PENDING, FAILED

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

