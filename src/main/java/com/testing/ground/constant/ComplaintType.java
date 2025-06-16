package com.testing.ground.constant;

public enum ComplaintType {
    NOISE,
    MAINTENANCE,
    SECURITY,
    PARKING,
    NEIGHBORHOOD_DISPUTE,
    OTHER;

    public static boolean isValid(String value) {
        for (ComplaintType type : ComplaintType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
