package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* NOTE: Create a different method for every properties file. Easier this way to maintain if one file is renamed. */
public class PropertiesManager {
    Properties properties = new Properties();

    private Properties getProperties(String fileName) throws IOException {
        InputStream inputStream = null;

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            properties.load(inputStream);
        } catch (IOException e) {
            Log.error(e.getMessage());
        } finally {
            if (inputStream != null)
                inputStream.close();
        }

        return properties;
    }

    public Properties getAPIProperties() throws IOException {
        return getProperties("api.properties");
    }
}