package com.testing.ground.service;

import com.testing.ground.entity.Complaint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ComplaintService {

    /**
     * Log a complaint with the given details.
     *
     */
    public String logComplaint(Complaint complaint);

    /**
     * Retrieve the status of a complaint by its ID.
     *
     * @param complaintId The ID of the complaint to retrieve.
     * @return The status of the complaint.
     */
    public String getComplaintStatus(Long complaintId);

    /**
     * Update the status of a complaint.
     *
     * @param complaintId The ID of the complaint to update.
     * @param newStatus The new status to set for the complaint.
     * @return A message indicating the result of the update operation.
     */
    public String updateComplaintStatus(String complaintId, String newStatus);

    /**
     * Delete a complaint by its ID.
     *
     * @param complaintId The ID of the complaint to delete.
     * @return A message indicating the result of the delete operation.
     */
    public String deleteComplaint(String complaintId);

    /**
     * List all complaints.
     *
     * @return A list of all complaints.
     */
    public List<Complaint> listAllComplaints();

    /**
     * Search for complaints based on specific criteria.
     *
     * @param searchCriteria The criteria to search complaints by.
     * @return A list of complaints matching the search criteria.
     */
    public List<Complaint> searchComplaints(Complaint searchCriteria);

    /**
     * Escalate a complaint to a higher authority.
     *
     * @param complaintId The ID of the complaint to escalate.
     * @return A message indicating the result of the escalation operation.
     */
    public String escalateComplaint(String complaintId);

    /**
     * Archive a complaint by its ID.
     *
     * @param complaintId The ID of the complaint to archive.
     * @return A message indicating the result of the archive operation.
     */
    public String archiveComplaint(String complaintId);

    /**
     * Reopen a closed complaint.
     *
     * @param complaintId The ID of the complaint to reopen.
     * @return A message indicating the result of the reopen operation.
     */
    public String reopenComplaint(String complaintId);

    /**
     * Assign a complaint to a specific user.
     *
     * @param complaintId The ID of the complaint to assign.
     * @param assignTo The ID of the user to whom the complaint is assigned.
     * @return A message indicating the result of the assignment operation.
     */
    public String assignComplaint(String complaintId, String assignTo);

    /**
     * Get the details of a specific complaint.
     *
     * @param complaintId The ID of the complaint to retrieve.
     * @return The details of the complaint.
     */
    public Complaint getComplaintDetails(String complaintId);

    /**
     * Add a comment to a complaint.
     *
     * @param complaintId The ID of the complaint to which the comment is added.
     * @param comment The comment to add.
     * @return A message indicating the result of the add comment operation.
     */
    public String addCommentToComplaint(String complaintId, String comment);

    /**
     * Get the history of a complaint.
     *
     * @param complaintId The ID of the complaint to retrieve history for.
     * @return The history of the complaint.
     */
    public String getComplaintHistory(String complaintId);

    /**
     * Get the list of complaints assigned to a specific user.
     *
     * @param userId The ID of the user whose assigned complaints are to be retrieved.
     * @return A list of complaints assigned to the user.
     */
    public List<Complaint> getComplaintsAssignedToUser(String userId);

    /**
     * Get the list of complaints created by a specific user.
     *
     * @param userId The ID of the user whose created complaints are to be retrieved.
     * @return A list of complaints created by the user.
     */
    public List<Complaint> getComplaintByCreatedBy(String userId);

    /**
     * Get the list of complaints based on their status.
     *
     * @param status The status of the complaints to retrieve (e.g., OPEN, IN_PROGRESS, RESOLVED, CLOSED).
     * @return A list of complaints with the specified status.
     */
    public List<Complaint> getComplaintsByStatus(String status);

    /**
     * Get the list of complaints based on their priority.
     *
     * @param priority The priority of the complaints to retrieve (e.g., LOW, MEDIUM, HIGH).
     * @return A list of complaints with the specified priority.
     */
    public List<Complaint> getComplaintsByPriority(String priority);

    /**
     * Get the list of complaints based on their type.
     *
     * @param complaintType The type of the complaints to retrieve (e.g., NOISE, MAINTENANCE, SECURITY).
     * @return A list of complaints with the specified type.
     */
    public List<Complaint> getComplaintsByType(String complaintType);
}
