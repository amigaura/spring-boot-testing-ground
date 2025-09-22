package com.testing.ground.misc;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        String input = null;

        // Using Optional to handle potential null value
        String result = java.util.Optional.ofNullable(input)
                .orElse("Default Value");

        System.out.println("Result: " + result);

        // Example with a non-null value
        String anotherInput = "Hello, World!";
        String anotherResult = java.util.Optional.ofNullable(anotherInput)
                .orElse("Default Value");

        System.out.println("Another Result: " + anotherResult);
    }
}
