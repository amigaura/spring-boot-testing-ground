package com.testing.ground.entity.society;

import com.testing.ground.constant.society.CommitteeRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CommitteeMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "committee_id", nullable = false)
    private Committee committee;

    @Column(nullable = false)
    private Long appUserId;         // reference to AppUser

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommitteeRole role;     // PRESIDENT, SECRETARY, TREASURER, etc.

    // Audit per membership (optional)
    private LocalDateTime joinedAt;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;
}
