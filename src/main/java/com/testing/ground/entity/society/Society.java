package com.testing.ground.entity.society;

import com.testing.ground.constant.society.SocietyStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Society {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // society_id

    @Column(nullable = false, unique = true)
    private String name; // society name

    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;

    private String contactNumber;
    private String email;
    private String website;
    @Enumerated(EnumType.STRING)
    private SocietyStatus status; // active, suspended, etc.

    // Logo and media info
    private String logo;               // file name or path
    private String logoUrl;           // public-accessible URL
    private String logoAltText;
    private String logoCaption;
    private String logoDescription;

    private String description;       // society description

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;
}


