package com.testing.ground.entity.misc;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class EmailRequest {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String recipient;

    private String cc;
    private String bcc;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String body;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime sentAt;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum Status { PENDING, SENT, FAILED }
}