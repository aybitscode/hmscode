package common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class HmsConfig {
    private static Properties hmsProps = new Properties();

    static {
        try {
            InputStream inputStream = HmsConfig.class.getClassLoader().getResourceAsStream("hms-rest.properties");
            hmsProps.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return hmsProps.getProperty(key);
    }
}