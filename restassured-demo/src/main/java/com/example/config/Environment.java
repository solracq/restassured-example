package com.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {
    private static final Properties props = new Properties();

    public static void load(String env) {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/" + env + ".properties");
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load environment file: " + env, e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
