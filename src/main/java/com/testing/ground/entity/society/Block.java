package com.testing.ground.entity.society;

import com.testing.ground.constant.society.BlockStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // block_id

    @Column(nullable = false)
    private Long societyId;

    @Column(nullable = false)
    private String name; // block name

    private String description; // block description

    // Block image metadata
    private String image; // file name or path
    private String imageUrl;
    private String imageAltText;
    private String imageCaption;
    private String imageDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BlockStatus status = BlockStatus.ACTIVE;

    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime createdDate;

}

