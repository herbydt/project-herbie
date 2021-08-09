package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    Properties properties;

    public static String getConfigProperties(String key) throws IOException
    {
        Properties prop = new Properties();
        InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties");
        if (input != null) {
            prop.load(input);
        } else {
            //LOGGER.error("Could not open config file. Check that " + propertiesName + " is added to classpath");
        }
        return prop.getProperty(key);
    }

}
