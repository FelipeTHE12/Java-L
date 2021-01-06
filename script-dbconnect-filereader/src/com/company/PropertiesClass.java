package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesClass {
    //LOAD AUTHENTICATION FILE(config.properties)
    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("");
        props.load(file);
        return props;
    }
}
