package com.testing.ground.entity.misc;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmailTemplate {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;               // e.g. WELCOME, PASSWORD_RESET, DUES_REMINDER

    @Column(nullable = false)
    private String subjectTemplate;    // Thymeleaf expression

//    @Lob
//    @Column(nullable = false, columnDefinition = "text")
    @jakarta.persistence.Column(length = 65535)
    private String bodyTemplate; // HTML with Thymeleaf placeholders

    private boolean active = true;
}
