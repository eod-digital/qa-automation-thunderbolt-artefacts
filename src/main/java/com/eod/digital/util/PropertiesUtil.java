package com.eod.digital.util;

import java.util.HashMap;
import java.util.Map;

public final class PropertiesUtil {
    private static Map properties = new HashMap<>();

    public static String getProperty(String key) {
        return (String)properties.get(key);
    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }
    
    public static String getDesiredCapabilities() {
    return getProperty("desired.capabilities");
}
    public static String getSeleniumDriverLocation() {
        return getProperty("selenium.driver.location");
    }
    public static String getDefaultWaitTime() {
        return getProperty("default.wait.time");
    }
    public static String getSeleniumGridUrl() {
        return getProperty("selenium.grid.url");
    }
    public static String getEnvironment() {
        return getProperty("environment");
    }
}
