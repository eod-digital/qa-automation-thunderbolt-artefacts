package util;

import java.util.HashMap;
import java.util.Map;

public final class PropertiesUtil {
    private Map properties = new HashMap<String, String>();

    public String getProperty(String key) {
        return (String)properties.get(key);
    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }
    
    public static String getDesiredBrowser() {
    return getProperty("desired.browser");
}
public static String getWebDriverLocation() {
    return getProperty("webDriver.location");
}
public static String getDefaultWaitTime() {
    return getProperty("default.wait.time");
}
public static String getWebDriverGridUrl() {
    return getProperty("webDriver.grid.url");
}
public static String getEnvironment() {
    return getProperty("environment");
}
}
