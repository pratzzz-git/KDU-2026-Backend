package com.company.config;

// Custom unchecked exception for config validation failures
public class ConfigValidationException extends RuntimeException {

    public ConfigValidationException(String message) {
        super(message);
    }
}