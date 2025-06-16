package com.testing.ground.util;

import com.testing.ground.constant.ComplaintType;
import com.testing.ground.constant.Priority;
import com.testing.ground.constant.Status;

public class CommonUtil {
    /**
     * Validates if the given string is a valid ISO date format.
     *
     * @param date The date string to validate.
     * @return true if the date is in ISO format, false otherwise.
     */
    public static boolean isValidISODate(String date) {
        // A simple regex to check for ISO 8601 date format (YYYY-MM-DD)
        return date != null && date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public static boolean isValidStatus(String status) {
        if (status == null) return false;
        try {
            Status.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidPriority(String priority) {
        if (priority == null) return false;
        try {
            Priority.valueOf(priority.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidComplaintType(String complaintType) {
        if (complaintType == null || complaintType.trim().isEmpty()) return false;
        try {
            ComplaintType.valueOf(complaintType.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
