package com.sample.restfulbooker.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* Properties Manager - .properties File Reader
 * NOTE:Create a different method for every properties file.Easier this way to maintain if one file is renamed. */
public class PropertiesManager {
    Properties properties = new Properties();

    private Properties getProperties(String fileName) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            Loggers.error(e.getMessage());
            throw new IOException(e.getMessage());
        }

        return properties;
    }

    public Properties getAnotherProperties() throws IOException {
        return getProperties("another.properties");
    }

    public Properties getAPIProperties() throws IOException {
        return getProperties("api.properties");
    }
}