package com.testing.ground.util;

public class UsernameValidator {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String PHONE_REGEX =
            "^[6-9]\\d{9}$"; // Indian mobile format: 10 digits starting with 6-9

    public static boolean isEmail(String username) {
        return username != null && username.matches(EMAIL_REGEX);
    }

    public static boolean isPhone(String username) {
        return username != null && username.matches(PHONE_REGEX);
    }

    public static String getUsernameType(String username) {
        if (isEmail(username)) {
            return "EMAIL";
        } else if (isPhone(username)) {
            return "PHONE";
        } else {
            return "UNKNOWN";
        }
    }
}

