package org.example;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("config.properties file not found in the classpath.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading config.properties: " + e.getMessage());
        }
    }

    public String getChromeDriverPath() {
        return properties.getProperty("chromedriver.path");
    }

    public String getWebsiteUrl() {
        return properties.getProperty("amazon");
    }
    public String getUserName() {
        return properties.getProperty("user");
    }
    public String getPassWord() {
        return properties.getProperty("password");
    }
}
