package com.testing.ground.entity.society;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Committee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // committee_id

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String name;            // committee name

    @Column(columnDefinition = "TEXT")
    private String description;     // committee description

    @Column(nullable = false)
    private LocalDate startDate;    // committee start date

    private LocalDate endDate;      // committee end date

    // Members with their roles
    @OneToMany(mappedBy = "committee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CommitteeMember> members;

    // Audit
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;
}
