package com.testing.ground.repository;

import com.testing.ground.entity.Complaint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
    @Query("SELECT a FROM Complaint a WHERE assignedTo = :assignedTo")
    List<Complaint> findByAssignedUserId(String assignedTo);

    @Query("SELECT a FROM Complaint a WHERE createdBy = :createdBy")
    List<Complaint> findComplaintByCreatedBy(String createdBy);

    @Query("SELECT a FROM Complaint a WHERE status = :status")
    List<Complaint> findByStatus(String status);

    @Query("SELECT a FROM Complaint a WHERE priority = :priority")
    List<Complaint> findByPriority(String priority);

    @Query("SELECT a FROM Complaint a WHERE complaintType = :complaintType")
    List<Complaint> findByType(String complaintType);

    @Query("SELECT c FROM Complaint c WHERE " +
            "(:#{#complaint.id} IS NULL OR c.id = :#{#complaint.id}) AND " +
            "(:#{#complaint.status} IS NULL OR c.status = :#{#complaint.status}) AND " +
            "(:#{#complaint.priority} IS NULL OR c.priority = :#{#complaint.priority}) AND " +
            "(:#{#complaint.complaintType} IS NULL OR c.complaintType = :#{#complaint.complaintType}) AND " +
            "(:#{#complaint.assignedTo} IS NULL OR c.assignedTo = :#{#complaint.assignedTo}) AND " +
            "(:#{#complaint.createdBy} IS NULL OR c.createdBy = :#{#complaint.createdBy})")
    List<Complaint> searchComplaints(Complaint complaint);
}
