package com.framework.utils;

import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {

    private static final Properties properties = new Properties();

    private PropertyReader() {}

    static {
        try (InputStream is = PropertyReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (is == null) {
                throw new RuntimeException("Cannot find config.properties!");
            }

            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load configuration: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }
}