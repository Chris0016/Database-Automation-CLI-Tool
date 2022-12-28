package com.dbautomation;

import java.io.FileInputStream;
import java.util.Properties;

public class PropUtil {

    final static String PATH_TO_CONFIG = "config.properties";

    public static Properties getProp() {
        Properties props = new Properties();
        try (FileInputStream fs = new FileInputStream(PATH_TO_CONFIG)) {
           
        
            props.load(fs);
        
        } catch (Exception e) {
           System.out.println("Error: Unable to find config.properties file.");
           System.exit(1);
        }
        
        return props;
    }
}