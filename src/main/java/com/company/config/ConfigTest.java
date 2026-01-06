package com.company.config;

public class ConfigTest {

    public static void main(String[] args) {

        // Valid configuration
        SystemConfig config =
                new SystemConfig(8, 2500);

        ConfigValidator.validate(config);

        // Uncomment to test failure
        /*
        SystemConfig badConfig =
                new SystemConfig(50, 10);
        ConfigValidator.validate(badConfig);
        */
    }
}