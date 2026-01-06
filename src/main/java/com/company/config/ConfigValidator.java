package com.company.config;

import java.lang.reflect.Field;

public class ConfigValidator {

    public static void validate(Object configObject) {

        // Get runtime class information
        Class<?> clazz = configObject.getClass();

        // Loop through all fields
        for (Field field : clazz.getDeclaredFields()) {

            // Check if RangeCheck annotation is present
            if (field.isAnnotationPresent(RangeCheck.class)) {

                // Read annotation values
                RangeCheck range = field.getAnnotation(RangeCheck.class);
                int min = range.min();
                int max = range.max();

                try {
                    // Allow access to private fields
                    field.setAccessible(true);

                    // Read actual value from object
                    int value = field.getInt(configObject);

                    // Validate range
                    if (value < min || value > max) {
                        throw new ConfigValidationException(
                                field.getName() +
                                        " must be between " + min +
                                        " and " + max +
                                        " but found " + value
                        );
                    }

                    // Log success
                    SystemConfig.logSuccess(
                            field.getName() + " is valid (" + value + ")"
                    );

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}