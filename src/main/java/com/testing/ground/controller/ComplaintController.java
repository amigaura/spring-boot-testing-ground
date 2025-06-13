package com.testing.ground.controller;

import com.testing.ground.entity.Complaint;
import com.testing.ground.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<String> logComplaint(@RequestBody Complaint complaint) {
        return ResponseEntity.ok(complaintService.logComplaint(complaint));
    }

    @GetMapping("/{complaintId}/status")
    public ResponseEntity<String> getComplaintStatus(@PathVariable Long complaintId) {
        return ResponseEntity.ok(complaintService.getComplaintStatus(complaintId));
    }

    @PutMapping("/{complaintId}/status")
    public ResponseEntity<String> updateComplaintStatus(@PathVariable String complaintId, @RequestBody String newStatus) {
        return ResponseEntity.ok(complaintService.updateComplaintStatus(complaintId, newStatus));
    }

    @DeleteMapping("/{complaintId}")
    public ResponseEntity<String> deleteComplaint(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.deleteComplaint(complaintId));
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> listAllComplaints() {
        return ResponseEntity.ok(complaintService.listAllComplaints());
    }

    @PostMapping("/search")
    public ResponseEntity<List<Complaint>> searchComplaints(@RequestBody Complaint searchComplaint) {
        return ResponseEntity.ok(complaintService.searchComplaints(searchComplaint));
    }

    @PostMapping("/{complaintId}/escalate")
    public ResponseEntity<String> escalateComplaint(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.escalateComplaint(complaintId));
    }

    @PostMapping("/{complaintId}/archive")
    public ResponseEntity<String> archiveComplaint(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.archiveComplaint(complaintId));
    }

    @PostMapping("/{complaintId}/reopen")
    public ResponseEntity<String> reopenComplaint(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.reopenComplaint(complaintId));
    }

    @PostMapping("/{complaintId}/assign")
    public ResponseEntity<String> assignComplaint(@PathVariable String complaintId, @RequestParam String assignedTo) {
        return ResponseEntity.ok(complaintService.assignComplaint(complaintId, assignedTo));
    }

    @GetMapping("/{complaintId}")
    public ResponseEntity<Complaint> getComplaintDetails(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.getComplaintDetails(complaintId));
    }

    @PostMapping("/{complaintId}/comment")
    public ResponseEntity<String> addCommentToComplaint(@PathVariable String complaintId, @RequestBody String comment) {
        return ResponseEntity.ok(complaintService.addCommentToComplaint(complaintId, comment));
    }

    @GetMapping("/{complaintId}/history")
    public ResponseEntity<String> getComplaintHistory(@PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.getComplaintHistory(complaintId));
    }

    @GetMapping("/assigned")
    public ResponseEntity<List<Complaint>> getComplaintsAssignedToUser(@RequestParam String userId) {
        return ResponseEntity.ok(complaintService.getComplaintsAssignedToUser(userId));
    }

    @GetMapping("/created")
    public ResponseEntity<List<Complaint>> getComplaintByCreatedBy(@RequestParam String userId) {
        return ResponseEntity.ok(complaintService.getComplaintByCreatedBy(userId));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Complaint>> getComplaintsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(complaintService.getComplaintsByStatus(status));
    }

    @GetMapping("/priority")
    public ResponseEntity<List<Complaint>> getComplaintsByPriority(@RequestParam String priority) {
        return ResponseEntity.ok(complaintService.getComplaintsByPriority(priority));
    }

    @GetMapping("/type")
    public ResponseEntity<List<Complaint>> getComplaintsByType(@RequestParam String complaintType) {
        return ResponseEntity.ok(complaintService.getComplaintsByType(complaintType));
    }

}
