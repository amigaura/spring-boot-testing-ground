package com.testing.ground.constant;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    public static boolean isValid(String value) {
        for (Priority p : Priority.values()) {
            if (p.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
