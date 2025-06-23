package com.testing.ground.util;

public class InputSanitizer {

    // Basic example: you can expand with OWASP Java HTML Sanitizer or Apache Commons
    public static String sanitize(String input) {
        if (input == null) return null;
        return input
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
//                .replaceAll("\"", "&quot;")
//                .replaceAll("'", "&#x27;")
//                .replaceAll("&", "&amp;")
                ;
    }

    public static String sanitizeJson(String json) {
        // You can use a streaming parser for complex logic
        return sanitize(json);
    }
}

