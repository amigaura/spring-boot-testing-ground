package com.testing.ground.entity.society;

import com.testing.ground.constant.society.StaffCategory;
import com.testing.ground.constant.society.StaffRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // staff_id

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffCategory category; // SECURITY, MAINTENANCE, HOUSEKEEPING, etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffRole role; // MANAGER, SUPERVISOR, WORKER, etc.

    private String agencyName;
    private String agencyContact;
    private String agencyEmail;
    private String agencyAddress;

    private String phoneNumber;
    private String email;
    private String address;

    // Profile picture metadata
    private String profilePicture;      // file name or path
    private String profilePictureUrl;   // public URL

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}
