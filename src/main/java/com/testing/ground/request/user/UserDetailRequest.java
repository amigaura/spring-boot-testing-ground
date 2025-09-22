package com.testing.ground.request.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetailRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String address;

    @Email(message = "Invalid email format")
    @NotBlank
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phoneNumber;

    private String usernameType;
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

    private String createdBy;
}
