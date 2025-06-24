package com.testing.ground.entity.vendor;
import com.testing.ground.constant.vendor.BankAccountType;
import com.testing.ground.constant.vendor.VendorCategory;
import com.testing.ground.constant.vendor.VendorStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // vendor_id

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String vendorName;

    private String vendorContactNumber;
    private String vendorContactPerson;
    private String vendorContactPersonPhone;
    private String vendorContactPersonEmail;
    private String vendorWebsite;

    private String vendorLogo;
    private String vendorLogoUrl;

    private String vendorEmail;
    private String vendorAddress;

    @Column(length = 1000)
    private String vendorServicesOffered;

    @Enumerated(EnumType.STRING)
    private VendorCategory vendorCategory;

    private String vendorPanNumber;
    private String vendorGstNumber;

    @Column(length = 1000)
    private String vendorBankAccountDetails;

    private String vendorBankAccountNumber;
    private String vendorBankIfscCode;
    private String vendorBankName;
    private String vendorBankBranch;

    @Enumerated(EnumType.STRING)
    private BankAccountType vendorBankAccountType;

    private String vendorPaymentTerms; // e.g., Net 30, Net 60

    private Boolean vendorTdsChargeApplicable;
    private BigDecimal vendorTdsPercentage;

    private String vendorTdsExemptionCertificate;
    private String vendorTdsExemptionCertificateUrl;

    private Integer vendorRating; // 1–5 scale or percentage

    @Enumerated(EnumType.STRING)
    private VendorStatus vendorStatus;

    @Column(length = 1000)
    private String vendorNotes;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}
