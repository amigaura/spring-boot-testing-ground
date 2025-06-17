package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "user_preferences")
//@Data
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String preferredLanguage;
    private Boolean emailNotificationPreferences;
    private Boolean smsNotificationPreferences;
    private Boolean pushNotificationPreferences;
    private Boolean marketingCommunicationPreferences;
    private Boolean dataSharingPreferences;
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

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;

}
