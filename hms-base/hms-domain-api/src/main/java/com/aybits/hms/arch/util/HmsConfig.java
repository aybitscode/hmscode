package com.aybits.hms.arch.util;

import java.io.InputStream;
import java.util.Properties;

public class HmsConfig {
    private static final Properties hmsProps = new Properties();
    private static final Properties hmsConfigProps = new Properties();
    static {
        try {
            InputStream inputStream = HmsConfig.class.getClassLoader().getResourceAsStream("hms-rest.properties");
            hmsProps.load(inputStream);
            inputStream.close();

            inputStream = HmsConfig.class.getClassLoader().getResourceAsStream("hms-config.properties");
            hmsConfigProps.load(inputStream);
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRestProperty(String key) {
        return hmsProps.getProperty(key);
    }

    public static String getConfigProperty(String key) {
        return hmsConfigProps.getProperty(key);
    }

    public Properties getHmsConfigProps(){
        return hmsConfigProps;
    }


}