package com.testing.ground.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Complaint {

    @Id
    @GeneratedValue
    private Long id;

    private Long propertyId; // ID of the property associated with the complaint
    private Long buildingId;
    private Long flatId;

    private String createdBy; // User who created the complaint
    private String defaulter;
    @Column(length = 2000)
    private String details;

    private String complaintType; // e.g., NOISE, MAINTENANCE, SECURITY
    private String complaintDate; // Date in ISO format
    private String priority; // LOW, MEDIUM, HIGH

    private String assignedTo; // User to whom the complaint is currently assigned
    private String assignedBy; // User who assigned the complaint
    private String assignedDate; // Date in ISO format

    private String attendedBy; // User who attended the complaint
    private String attendedDate; // Date in ISO format
    private String resolutionDetails;
    private String resolutionDate; // Date in ISO format

    private String lastUpdated; // Date in ISO format
    private String lastUpdatedBy; // User who last updated the complaint

    private Long reopeningCount; // Number of times the complaint has been reopened
    private String escalationLevel; // e.g., LEVEL_1, LEVEL_2, LEVEL_3
    private String status; // OPEN, IN_PROGRESS, RESOLVED, CLOSED

    private List<String> attachments; // List of attachment URLs or IDs
    private List<String> comments = new ArrayList<String>(); // List of comments on the complaint

    public void addComment(String comment) {
        if (comment != null && !comment.trim().isEmpty()) {
            this.comments.add(comment);
        } else {
            throw new IllegalArgumentException("Comment cannot be null or empty");
        }
    }

    public String getHistory() {
        StringBuilder history = new StringBuilder();
        history.append("Complaint ID: ").append(id).append("\n")
               .append("Created By: ").append(createdBy).append("\n")
               .append("Complaint Type: ").append(complaintType).append("\n")
               .append("Details: ").append(details).append("\n")
               .append("Status: ").append(status).append("\n")
               .append("Priority: ").append(priority).append("\n")
               .append("Assigned To: ").append(assignedTo).append("\n")
               .append("Comments:\n");
        for (String comment : comments) {
            history.append("- ").append(comment).append("\n");
        }
        return history.toString();
    }
}
