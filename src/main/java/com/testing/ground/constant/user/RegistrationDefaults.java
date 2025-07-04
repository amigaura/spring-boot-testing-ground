package com.testing.ground.constant.user;

public enum RegistrationDefaults {
    DEFAULT_ROLE("user"),
    DEFAULT_PERMISSION("Community"),
    DEFAULT_PASSWORD("Password123");

    private final String value;

    RegistrationDefaults(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
