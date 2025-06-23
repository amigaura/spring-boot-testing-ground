package com.testing.ground.controller.society;

import com.testing.ground.entity.society.Complaint;
import com.testing.ground.service.society.ComplaintService;
import com.testing.ground.util.CommonUtil;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.security.PermitAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ComplaintController is responsible for handling all complaint-related operations.
 * *  It provides endpoints to log complaints, retrieve and update complaint statuses,
 * *  delete complaints, list all complaints, search for specific complaints,
 * *  escalate, archive, reopen complaints, assign complaints to users,
 * *  add comments to complaints, and retrieve complaint history.
 */
@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComplaintController.class);

    @Autowired
    private ComplaintService complaintService;

    @PreAuthorize("hasRole('Resident')")
    @PostMapping
    public ResponseEntity<String> logComplaint(@RequestBody Complaint complaint) {
        try {
            if (complaint == null || complaint.getDetails() == null || complaint.getDetails().isEmpty()) {
                return ResponseEntity.badRequest().body("Complaint details cannot be null or empty.");
            }
            if (complaint.getCreatedBy() == null || complaint.getCreatedBy().isEmpty()) {
                return ResponseEntity.badRequest().body("Created by cannot be null or empty.");
            }
            if (complaint.getType() == null || CommonUtil.isValidComplaintType(complaint.getType().toString())) {
                return ResponseEntity.badRequest().body("Invalid complaint type provided.");
            }
            if (complaint.getPriority() == null || CommonUtil.isValidPriority(complaint.getPriority().toString())) {
                return ResponseEntity.badRequest().body("Invalid priority provided.");
            }
            if (complaint.getStatus() == null || CommonUtil.isValidStatus(complaint.getStatus().toString())) {
                return ResponseEntity.badRequest().body("Invalid status provided.");
            }
//            if (complaint.getCreatedDate() == null || !CommonUtil.isValidISODate(complaint.getCreatedDate())) {
//                return ResponseEntity.badRequest().body("Invalid complaint date provided. Must be in ISO format.");
//            }
            LOGGER.info("Logging complaint: {}", complaint);
            return ResponseEntity.ok(complaintService.logComplaint(complaint));
        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        } catch (Exception e) {
            LOGGER.error("Error logging complaint: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while logging the complaint.");
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @GetMapping("/{complaintId}/status")
    public ResponseEntity<String> getComplaintStatus(@PathVariable Long complaintId) {
        try {
            if (complaintId == null || complaintId <= 0) {
                return ResponseEntity.badRequest().body("Invalid complaint ID.");
            }
            LOGGER.info("Fetching status for complaint ID: {}", complaintId);
            return ResponseEntity.ok(complaintService.getComplaintStatus(complaintId));
        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @PutMapping("/{complaintId}/status")
    public ResponseEntity<String> updateComplaintStatus(@PathVariable String complaintId, @RequestBody String newStatus) {
        try {
            if (complaintId == null || complaintId.isEmpty()) {
                return ResponseEntity.badRequest().body("Complaint ID cannot be null or empty.");
            }
            if (newStatus == null || newStatus.isEmpty()) {
                return ResponseEntity.badRequest().body("New status cannot be null or empty.");
            }
            if (CommonUtil.isValidStatus(newStatus)) {
                return ResponseEntity.badRequest().body("Invalid status provided.");
            }
            LOGGER.info("Updating status for complaint ID: {} to {}", complaintId, newStatus);
            return ResponseEntity.ok(complaintService.updateComplaintStatus(complaintId, newStatus));
        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{complaintId}")
    public ResponseEntity<String> deleteComplaint(@PathVariable String complaintId) {
        try {
            if (complaintId == null || complaintId.isEmpty()) {
                return ResponseEntity.badRequest().body("Complaint ID cannot be null or empty.");
            }
            LOGGER.info("Deleting complaint with ID: {}", complaintId);
            return ResponseEntity.ok(complaintService.deleteComplaint(complaintId));
        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        }
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @PermitAll
    public ResponseEntity<List<Complaint>> listAllComplaints() {
        try {
            LOGGER.info("Listing all complaints");
            return ResponseEntity.ok(complaintService.listAllComplaints());
        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @PostMapping("/search")
    public ResponseEntity<List<Complaint>> searchComplaints(@RequestBody Complaint searchComplaint) {
        try {
            if (searchComplaint == null) {
                return ResponseEntity.badRequest().body(null);
            }
            if (searchComplaint.getCreatedBy() == null || searchComplaint.getCreatedBy().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            if (searchComplaint.getType() != null && CommonUtil.isValidComplaintType(searchComplaint.getType().toString())) {
                return ResponseEntity.badRequest().body(null);
            }
            if (searchComplaint.getPriority() != null && CommonUtil.isValidPriority(searchComplaint.getPriority().toString())) {
                return ResponseEntity.badRequest().body(null);
            }
            if (searchComplaint.getStatus() != null && CommonUtil.isValidStatus(searchComplaint.getStatus().toString())) {
                return ResponseEntity.badRequest().body(null);
            }
            LOGGER.info("Searching complaints with criteria: {}", searchComplaint);
            return ResponseEntity.ok(complaintService.searchComplaints(searchComplaint));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body(null);
        }

    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @PostMapping("/{complaintId}/escalate")
    public ResponseEntity<String> escalateComplaint(@PathVariable String complaintId) {
        try {
            if (complaintId == null || complaintId.isEmpty()) {
                return ResponseEntity.badRequest().body("Complaint ID cannot be null or empty.");
            }
            LOGGER.info("Escalating complaint with ID: {}", complaintId);
            return ResponseEntity.ok(complaintService.escalateComplaint(complaintId));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{complaintId}/archive")
    public ResponseEntity<String> archiveComplaint(@PathVariable String complaintId) {
        try {
            if (complaintId == null || complaintId.isEmpty()) {
                return ResponseEntity.badRequest().body("Complaint ID cannot be null or empty.");
            }
            LOGGER.info("Archiving complaint with ID: {}", complaintId);
            return ResponseEntity.ok(complaintService.archiveComplaint(complaintId));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        }
    }

    @PostMapping("/{complaintId}/reopen")
    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    public ResponseEntity<String> reopenComplaint(@PathVariable String complaintId) {
        try {
            if (complaintId == null || complaintId.isEmpty()) {
                return ResponseEntity.badRequest().body("Complaint ID cannot be null or empty.");
            }
            LOGGER.info("Reopening complaint with ID: {}", complaintId);
            return ResponseEntity.ok(complaintService.reopenComplaint(complaintId));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        }
    }

    @PostMapping("/{complaintId}/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> assignComplaint(@PathVariable String complaintId, @RequestParam String assignedTo) {
        try {
            if (complaintId == null || complaintId.isEmpty()) {
                return ResponseEntity.badRequest().body("Complaint ID cannot be null or empty.");
            }
            if (assignedTo == null || assignedTo.isEmpty()) {
                return ResponseEntity.badRequest().body("Assigned user ID cannot be null or empty.");
            }
            LOGGER.info("Assigning complaint with ID: {} to user: {}", complaintId, assignedTo);
            return ResponseEntity.ok(complaintService.assignComplaint(complaintId, assignedTo));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        }
    }

    @GetMapping("/{complaintId}")
    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    public ResponseEntity<Complaint> getComplaintDetails(@PathVariable String complaintId) {
        try {
            if (complaintId == null || complaintId.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            LOGGER.info("Fetching details for complaint ID: {}", complaintId);
            return ResponseEntity.ok(complaintService.getComplaintDetails(complaintId));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @PostMapping("/{complaintId}/comment")
    public ResponseEntity<String> addCommentToComplaint(@PathVariable String complaintId, @RequestBody String comment) {
        try {
            if (complaintId == null || complaintId.isEmpty()) {
                return ResponseEntity.badRequest().body("Complaint ID cannot be null or empty.");
            }
            if (comment == null || comment.isEmpty()) {
                return ResponseEntity.badRequest().body("Comment cannot be null or empty.");
            }
            LOGGER.info("Adding comment to complaint ID: {} - Comment: {}", complaintId, comment);
            return ResponseEntity.ok(complaintService.addCommentToComplaint(complaintId, comment));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @GetMapping("/{complaintId}/history")
    public ResponseEntity<String> getComplaintHistory(@PathVariable String complaintId) {
        try {
            if (complaintId == null || complaintId.isEmpty()) {
                return ResponseEntity.badRequest().body("Complaint ID cannot be null or empty.");
            }
            LOGGER.info("Fetching history for complaint ID: {}", complaintId);
            return ResponseEntity.ok(complaintService.getComplaintHistory(complaintId));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body("Unauthorized: You do not have permission to log a complaint.");
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @GetMapping("/assigned")
    public ResponseEntity<List<Complaint>> getComplaintsAssignedToUser(@RequestParam String userId) {
        try {
            if (userId == null || userId.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            LOGGER.info("Fetching complaints assigned to user ID: {}", userId);
            return ResponseEntity.ok(complaintService.getComplaintsAssignedToUser(userId));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @GetMapping("/created")
    public ResponseEntity<List<Complaint>> getComplaintByCreatedBy(@RequestParam String userId) {
        try {
            if (userId == null || userId.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            LOGGER.info("Fetching complaints created by user ID: {}", userId);
            return ResponseEntity.ok(complaintService.getComplaintByCreatedBy(userId));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @GetMapping("/status")
    public ResponseEntity<List<Complaint>> getComplaintsByStatus(@RequestParam String status) {
        if (status == null || status.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        if (CommonUtil.isValidStatus(status)) {
            return ResponseEntity.badRequest().body(null);
        }
        LOGGER.info("Fetching complaints with status: {}", status);
        try {

            return ResponseEntity.ok(complaintService.getComplaintsByStatus(status));

        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @GetMapping("/priority")
    public ResponseEntity<List<Complaint>> getComplaintsByPriority(@RequestParam String priority) {
        if (priority == null || priority.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        if (CommonUtil.isValidPriority(priority)) {
            return ResponseEntity.badRequest().body(null);
        }
        LOGGER.info("Fetching complaints with priority: {}", priority);
        try {
            return ResponseEntity.ok(complaintService.getComplaintsByPriority(priority));
        } catch (org.springframework.security.access.AccessDeniedException ex) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PreAuthorize("hasAnyRole('Resident','ADMIN')")
    @GetMapping("/type")
    public ResponseEntity<List<Complaint>> getComplaintsByType(@RequestParam String complaintType) {
        try {

            if (complaintType == null || complaintType.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            if (CommonUtil.isValidComplaintType(complaintType)) {
                return ResponseEntity.badRequest().body(null);
            }
            LOGGER.info("Fetching complaints with type: {}", complaintType);
            return ResponseEntity.ok(complaintService.getComplaintsByType(complaintType));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (AccessDeniedException exception) {
            return ResponseEntity.status(401).body(null);
        } catch (Exception e) {
            LOGGER.error("Error fetching complaints by type: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleJwtSignatureException(SignatureException ex) {
        LOGGER.error("Invalid JWT signature: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid or tampered JWT token. Please login again.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        LOGGER.error("Access denied: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("You do not have permission to access this resource.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        LOGGER.error("Invalid argument: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid input provided. Please check your request.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        LOGGER.error("An unexpected error occurred: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred. Please try again later.");
    }

}
