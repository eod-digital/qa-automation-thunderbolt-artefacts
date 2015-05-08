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
}
