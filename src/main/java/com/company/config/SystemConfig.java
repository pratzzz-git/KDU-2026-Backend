package com.company.config;

public class SystemConfig {

    // maxThreads must be between 1 and 16
    @RangeCheck(min = 1, max = 16)
    private int maxThreads = 8;

    // timeoutSeconds must be between 100 and 5000
    @RangeCheck(min = 100, max = 5000)
    private int timeoutSeconds = 2500;

    public SystemConfig(int maxThreads, int timeoutSeconds) {
        this.maxThreads = maxThreads;
        this.timeoutSeconds = timeoutSeconds;
    }

    // Utility logger
    public static void logSuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }
}