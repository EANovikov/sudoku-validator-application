package com.xevgnov.util;

import lombok.extern.java.Log;

import java.io.*;
import java.util.Properties;

@Log
public class PropertyReader {

    public static final String FILE_SEPARATOR_PROPERTY = "file.separator";

    private static final String APPLICATION_PROPERTIES_PATH = "src/main/resources/application.properties";
    private static final PropertyReader instance = new PropertyReader();
    private Properties properties;

    private PropertyReader(){
        loadProperties();
    }

    public static Properties getInstance() {
        return instance.properties;
    }

    private void loadProperties(){
        try (InputStream inputStream = new FileInputStream(APPLICATION_PROPERTIES_PATH)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch(IOException e){
            log.warning("Unable to read property" + e.getMessage());
        }
    }

}
