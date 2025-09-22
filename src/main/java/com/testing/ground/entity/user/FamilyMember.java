package com.testing.ground.entity.user;

import com.testing.ground.constant.user.Gender;
import com.testing.ground.constant.user.Relationship;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // family_member_id

    @Column(nullable = false)
    private Long appUserId;

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Relationship relationship; // SPOUSE, CHILD, PARENT, etc.

    private LocalDate dateOfBirth;

    private String email;
    private String phone;

    @Column(nullable = false)
    private Boolean currentlyResiding;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

