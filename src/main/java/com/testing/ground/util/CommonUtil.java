package com.testing.ground.util;

import com.testing.ground.constant.society.ComplaintType;
import com.testing.ground.constant.society.Priority;
import com.testing.ground.constant.society.Status;

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
        if (status == null) return true;
        try {
            Status.valueOf(status);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean isValidPriority(String priority) {
        if (priority == null) return true;
        try {
            Priority.valueOf(priority.toUpperCase());
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean isValidComplaintType(String complaintType) {
        if (complaintType == null || complaintType.trim().isEmpty()) return true;
        try {
            ComplaintType.valueOf(complaintType.toUpperCase());
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return true;
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) return true;
        String phoneRegex = "^\\+?[0-9]{10,15}$"; // Adjust regex as per your requirements
        return phoneNumber.matches(phoneRegex);
    }

    public static boolean isValidUrl(String url) {
        if (url == null || url.trim().isEmpty()) return true;
        String urlRegex = "^(https?|ftp)://[^\s/$.?#].[^\s]*$"; // Basic URL validation regex
        return !url.matches(urlRegex);
    }

    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) return true;
        String nameRegex = "^[a-zA-Z\\s]+$"; // Allows only letters and spaces
        return !name.matches(nameRegex);
    }
}
