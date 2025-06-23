package com.testing.ground.constant.finance;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_invoices")
public class VendorInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // invoice_id

    @Column(nullable = false)
    private Long vendorId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    @Column(nullable = false)
    private LocalDateTime invoiceDate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal invoiceAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceStatus invoiceStatus; // PAID, UNPAID, OVERDUE, etc.

    @Column(nullable = false)
    private LocalDateTime invoiceDueDate;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

