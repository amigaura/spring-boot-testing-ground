package com.testing.ground.repository.society;

import com.testing.ground.entity.society.Complaint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
    @Query("SELECT a FROM Complaint a WHERE assignedToUserId = :assignedToUserId")
    List<Complaint> findByAssignedUserId(String assignedToUserId);

    @Query("SELECT a FROM Complaint a WHERE createdBy = :createdBy")
    List<Complaint> findComplaintByCreatedBy(String createdBy);

    @Query("SELECT a FROM Complaint a WHERE status = :status")
    List<Complaint> findByStatus(String status);

    @Query("SELECT a FROM Complaint a WHERE priority = :priority")
    List<Complaint> findByPriority(String priority);

    @Query("SELECT a FROM Complaint a WHERE type = :type")
    List<Complaint> findByType(String type);

    @Query("SELECT c FROM Complaint c WHERE " +
            "(:#{#complaint.id} IS NULL OR c.id = :#{#complaint.id}) AND " +
            "(:#{#complaint.status} IS NULL OR c.status = :#{#complaint.status}) AND " +
            "(:#{#complaint.priority} IS NULL OR c.priority = :#{#complaint.priority}) AND " +
            "(:#{#complaint.type} IS NULL OR c.type = :#{#complaint.type}) AND " +
            "(:#{#complaint.assignedToUserId} IS NULL OR c.assignedToUserId = :#{#complaint.assignedToUserId}) AND " +
            "(:#{#complaint.createdBy} IS NULL OR c.createdBy = :#{#complaint.createdBy})")
    List<Complaint> searchComplaints(Complaint complaint);
}
