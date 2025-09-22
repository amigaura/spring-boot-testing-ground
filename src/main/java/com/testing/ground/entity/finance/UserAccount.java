package com.testing.ground.entity.finance;

import com.testing.ground.constant.finance.AccountStatus;
import com.testing.ground.constant.finance.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // account_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long propertyId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType; // PERSONAL, BUSINESS, etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus; // ACTIVE, SUSPENDED, CLOSED

    @Column(nullable = false)
    private LocalDateTime accountCreationDate;

    @Column(precision = 15, scale = 2)
    private BigDecimal dueAmount;

    @Column(length = 1000)
    private String transactionHistory; // Consider linking to a separate Transaction entity for normalization

    @Column(precision = 15, scale = 2)
    private BigDecimal accountBalance;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}
