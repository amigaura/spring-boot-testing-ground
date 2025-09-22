package com.testing.ground.entity.society;

import com.testing.ground.constant.society.PassStatus;
import com.testing.ground.constant.society.PassType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VisitorPass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pass_id

    @Column(nullable = false)
    private Long visitorId;

    @Column(nullable = false)
    private Long unitId;

    @Column(nullable = false)
    private Long societyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PassType passType;             // TEMPORARY, PERMANENT

    @Column(nullable = false)
    private Long requestedByUserId;         // who requested the pass

    @Column(nullable = false)
    private LocalDateTime issueDate;        // when the pass was issued

    private LocalDateTime expiryDate;       // when the pass expires

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PassStatus status;              // ACTIVE, EXPIRED, REVOKED

    @Column(length = 500)
    private String details;                 // any extra details about the pass

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

