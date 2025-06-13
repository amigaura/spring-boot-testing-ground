package com.testing.ground.service;

import com.testing.ground.entity.Complaint;
import com.testing.ground.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Override
    public String logComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
        return "Complaint logged with ID: " + complaint.getId();
    }

    @Override
    public String getComplaintStatus(Long complaintId) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(complaintId);
        return complaintOpt.map(c -> "Status for " + complaintId + ": " + c.getStatus())
                .orElse("Complaint not found");
    }

    @Override
    public String updateComplaintStatus(String complaintId, String newStatus) {
        try {
            Long id = Long.parseLong(complaintId);
            Optional<Complaint> complaintOpt = complaintRepository.findById(id);
            if (complaintOpt.isPresent()) {
                Complaint complaint = complaintOpt.get();
                complaint.setStatus(newStatus);
                complaintRepository.save(complaint);
                return "Status updated for complaint " + complaintId;
            }
        } catch (NumberFormatException e) {
            return "Invalid complaint ID format";
        }
        return "Complaint not found";
    }

    @Override
    public String deleteComplaint(String complaintId) {
        try {
            Long id = Long.parseLong(complaintId);
            if (complaintRepository.existsById(id)) {
                complaintRepository.deleteById(id);
                return "Complaint deleted";
            } else {
                return "Complaint not found";
            }
        } catch (NumberFormatException e) {
            return "Invalid complaint ID format";
        }
    }

    @Override
    public List<Complaint> listAllComplaints() {
        return (List<Complaint>) complaintRepository.findAll();
//        return allComplaints.isEmpty() ? Collections.emptyList() : allComplaints;
    }

    @Override
    public List<Complaint> searchComplaints(Complaint searchComplaint) {
        return complaintRepository.searchComplaints(searchComplaint);
    }

    public String escalateComplaint(String complaintId) {
        try {
            Long id = Long.parseLong(complaintId);
            Optional<Complaint> complaintOpt = complaintRepository.findById(id);
            if (complaintOpt.isPresent()) {
                Complaint complaint = complaintOpt.get();
                complaint.setStatus("Escalated");
                complaintRepository.save(complaint);
                return "Complaint escalated";
            } else {
                return "Complaint not found";
            }
        } catch (NumberFormatException e) {
            return "Invalid complaint ID format";
        }
    }

    @Override
    public String archiveComplaint(String complaintId) {
        try {
            Long id = Long.parseLong(complaintId);
            Optional<Complaint> complaintOpt = complaintRepository.findById(id);
            if (complaintOpt.isPresent()) {
                Complaint complaint = complaintOpt.get();
                complaint.setStatus("Archived");
                complaintRepository.save(complaint);
                return "Complaint archived";
            } else {
                return "Complaint not found";
            }
        } catch (NumberFormatException e) {
            return "Invalid complaint ID format";
        }
    }

    @Override
    public String reopenComplaint(String complaintId) {
        try {
            Long id = Long.parseLong(complaintId);
            Optional<Complaint> complaintOpt = complaintRepository.findById(id);
            if (complaintOpt.isPresent()) {
                Complaint complaint = complaintOpt.get();
                complaint.setStatus("Reopened");
                complaintRepository.save(complaint);
                return "Complaint reopened";
            } else {
                return "Complaint not found";
            }
        } catch (NumberFormatException e) {
            return "Invalid complaint ID format";
        }
    }

    @Override
    public String assignComplaint(String complaintId, String assignTo) {
        try {
            Long id = Long.parseLong(complaintId);
            Optional<Complaint> complaintOpt = complaintRepository.findById(id);
            if (complaintOpt.isPresent()) {
                Complaint complaint = complaintOpt.get();
                complaint.setAssignedTo(assignTo); // Ensure Complaint entity has this field and setter
                complaintRepository.save(complaint);
                return "Complaint assigned to user " + assignTo;
            } else {
                return "Complaint not found";
            }
        } catch (NumberFormatException e) {
            return "Invalid complaint ID format";
        }
    }

    @Override
    public Complaint getComplaintDetails(String complaintId) {
        try {
            Long id = Long.parseLong(complaintId);
            Optional<Complaint> complaintOpt = complaintRepository.findById(id);
            return complaintOpt.orElse(null); // Return null if not found
        } catch (NumberFormatException e) {
            return  null; // Invalid ID format
        }
    }

    @Override
    public String addCommentToComplaint(String complaintId, String comment) {
        try {
            Long id = Long.parseLong(complaintId);
            Optional<Complaint> complaintOpt = complaintRepository.findById(id);
            if (complaintOpt.isPresent()) {
                Complaint complaint = complaintOpt.get();
                // Assuming Complaint entity has a method addComment(String comment)
                complaint.addComment(comment);
                complaintRepository.save(complaint);
                return "Comment added";
            } else {
                return "Complaint not found";
            }
        } catch (NumberFormatException e) {
            return "Invalid complaint ID format";
        }
    }

    @Override
    public String getComplaintHistory(String complaintId) {
        try {
            Long id = Long.parseLong(complaintId);
            Optional<Complaint> complaintOpt = complaintRepository.findById(id);
            if (complaintOpt.isPresent()) {
                Complaint complaint = complaintOpt.get();
                // Assuming Complaint entity has a getHistory() method returning a String or List
                return "History for complaint " + complaintId + ": " + complaint.getHistory();
            } else {
                return "Complaint not found";
            }
        } catch (NumberFormatException e) {
            return "Invalid complaint ID format";
        }
    }

    @Override
    public List<Complaint> getComplaintsAssignedToUser(String userId) {
        return  complaintRepository.findByAssignedUserId(userId);
    }

    @Override
    public List<Complaint> getComplaintByCreatedBy(String createdBy) {
        return complaintRepository.findComplaintByCreatedBy(createdBy);
    }

    @Override
    public List<Complaint> getComplaintsByStatus(String status) {
        return complaintRepository.findByStatus(status);
    }

    @Override
    public List<Complaint> getComplaintsByPriority(String priority) {
        return complaintRepository.findByPriority(priority);
    }

    @Override
    public List<Complaint> getComplaintsByType(String complaintType) {
        return complaintRepository.findByType(complaintType);
    }
}
