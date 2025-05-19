package com.testing.ground.sync.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ItemRecord {
    @Id
    @GeneratedValue
    private Long id;
    private String item;
    private String status;

    public ItemRecord(String item, String status) {
        this.item = item;
        this.status = status;
    }

    // Default constructor, getters, setters
}
