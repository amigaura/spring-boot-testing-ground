package com.testing.ground.controller;

import com.testing.ground.entity.Complaint;
import com.testing.ground.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  ComplaintController is responsible for handling all complaint-related operations.
 *  *  It provides endpoints to log complaints, retrieve and update complaint statuses,
 *  *  delete complaints, list all complaints, search for specific complaints,
 *  *  escalate, archive, reopen complaints, assign complaints to users,
 *  *  add comments to complaints, and retrieve complaint history.
 */
@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<String> logComplaint(@RequestBody Complaint complaint) {
        return ResponseEntity.ok(complaintService.logComplaint(complaint));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{complaintId}/status")
    public ResponseEntity<String> getComplaintStatus(@PathVariable Long complaintId) {
        return ResponseEntity.ok(complaintService.getComplaintStatus(complaintId));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/{complaintId}/status")
    public ResponseEntity<String> updateComplaintStatus(@PathVariable String complaintId, @RequestBody String newStatus) {
        return ResponseEntity.ok(complaintService.updateComplaintStatus(complaintId, newStatus));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{complaintId}")
    public ResponseEntity<String> deleteComplaint(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.deleteComplaint(complaintId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Complaint>> listAllComplaints() {
        return ResponseEntity.ok(complaintService.listAllComplaints());
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/search")
    public ResponseEntity<List<Complaint>> searchComplaints(@RequestBody Complaint searchComplaint) {
        return ResponseEntity.ok(complaintService.searchComplaints(searchComplaint));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{complaintId}/escalate")
    public ResponseEntity<String> escalateComplaint(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.escalateComplaint(complaintId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{complaintId}/archive")
    public ResponseEntity<String> archiveComplaint(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.archiveComplaint(complaintId));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{complaintId}/reopen")
    public ResponseEntity<String> reopenComplaint(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.reopenComplaint(complaintId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{complaintId}/assign")
    public ResponseEntity<String> assignComplaint(@PathVariable String complaintId, @RequestParam String assignedTo) {
        return ResponseEntity.ok(complaintService.assignComplaint(complaintId, assignedTo));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{complaintId}")
    public ResponseEntity<Complaint> getComplaintDetails(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.getComplaintDetails(complaintId));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{complaintId}/comment")
    public ResponseEntity<String> addCommentToComplaint(@PathVariable String complaintId, @RequestBody String comment) {
        return ResponseEntity.ok(complaintService.addCommentToComplaint(complaintId, comment));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{complaintId}/history")
    public ResponseEntity<String> getComplaintHistory(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.getComplaintHistory(complaintId));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/assigned")
    public ResponseEntity<List<Complaint>> getComplaintsAssignedToUser(@RequestParam String userId) {
        return ResponseEntity.ok(complaintService.getComplaintsAssignedToUser(userId));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/created")
    public ResponseEntity<List<Complaint>> getComplaintByCreatedBy(@RequestParam String userId) {
        return ResponseEntity.ok(complaintService.getComplaintByCreatedBy(userId));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/status")
    public ResponseEntity<List<Complaint>> getComplaintsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(complaintService.getComplaintsByStatus(status));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/priority")
    public ResponseEntity<List<Complaint>> getComplaintsByPriority(@RequestParam String priority) {
        return ResponseEntity.ok(complaintService.getComplaintsByPriority(priority));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/type")
    public ResponseEntity<List<Complaint>> getComplaintsByType(@RequestParam String complaintType) {
        return ResponseEntity.ok(complaintService.getComplaintsByType(complaintType));
    }

}
