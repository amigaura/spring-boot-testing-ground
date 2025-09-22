package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // preference_id

    @Column(nullable = false)
    private Long societyId;

    private String preferredLanguage;

    // Notification preferences
    private Boolean emailNotification;
    private Boolean smsNotification;
    private Boolean pushNotification;
    private Boolean marketingCommunication;
    private Boolean dataSharing;

    // Privacy preferences
    private Boolean showPhoneNumber;
    private Boolean showEmail;
    private Boolean showAddress;
    private Boolean showDateOfBirth;
    private Boolean showProfilePicture;
    private Boolean showUserRole;
    private Boolean showUserStatus;
    private Boolean showUserPreferences;
    private Boolean showOccupation;
    private Boolean showEmergencyContactDetails;

    private Boolean receiveVisitorNotifications;

    // Audit
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;

    private String createdBy;
    private LocalDateTime createdDate;
}

