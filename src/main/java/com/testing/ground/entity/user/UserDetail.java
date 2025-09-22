package com.testing.ground.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String usernameType; // EMAIL or PHONE

    private Boolean emailVerified;
    private Boolean phoneVerified;

    private String occupation;
    private String alternateEmail;
    private String alternatePhone;

    private Boolean alternatePhoneVerified;
    private Boolean alternateEmailVerified;

    private String alternatePhone2;
    private String alternateEmail2;
    private String alternatePhone3;
    private String alternateEmail3;

    private String emergencyContactName;
    private String emergencyContactPhone;
    private String emergencyContactRelationship;
    private String emergencyContactEmail;
    private String emergencyContactAddress;
    private Boolean emergencyContactPhoneVerified;
    private Boolean emergencyContactEmailVerified;
    private Boolean emergencyContactAddressVerified;

    private LocalDate dateOfBirth;
    private String profilePicture;
    private String profilePictureUrl;

    private LocalDate residingFromDate;
    private String gender;
    private String maritalStatus;

    private String bloodGroup;
    private Long defaultPropertyId;

    private Boolean pets;
    private String petsDetails;

    private String aadharNumber;
    private String panNumber;
    private String passportNumber;
    private String drivingLicenseNumber;
    private String voterIdNumber;

    private Boolean clubMembership;
    private String clubMembershipDetails;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;

}
