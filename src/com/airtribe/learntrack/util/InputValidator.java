package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty!");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new InvalidInputException("Invalid email format!");
        }
    }

    public static void validatePositiveNumber(int number, String fieldName) {
        if (number <= 0) {
            throw new InvalidInputException(fieldName + " must be greater than zero!");
        }
    }
}
