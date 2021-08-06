package libraries;

import config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestBase extends SeleniumBase {

    public static String getApplicationProperties(String key) throws IOException
    {
        return getProperties("app", key);
    }

    private static String getProperties(String propertyFileName, String key) throws IOException
    {
        Properties prop = new Properties();
        InputStream input = Config.class.getClassLoader().getResourceAsStream(propertyFileName + ".properties");
        if (input != null) {
            prop.load(input);
        } else {
            //LOGGER.error("Could not open config file. Check that " + propertiesName + " is added to classpath");
        }
        return prop.getProperty(key);
    }

    public String getEnvironment() throws IOException {
        if (System.getProperty("environment") != null) {
            return System.getProperty("environment").toLowerCase();
        } else {
            return getConfigProperties("environment").toLowerCase();
        }
    }

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

    public String getGrid() throws IOException {
        if (System.getProperty("grid") != null) {
            return System.getProperty("grid");
        } else {
            return getConfigProperties("grid");
        }
    }

    public String getDocker() throws IOException {
        if (System.getProperty("docker") != null) {
            return System.getProperty("docker");
        } else {
            return getConfigProperties("docker");
        }
    }

    public String getBrowser() throws IOException {
        if (System.getProperty("browser") != null) {
            return System.getProperty("browser");
        } else {
            return getConfigProperties("browser");
        }
    }

    public String getCI() throws IOException {
        if (System.getProperty("ci") != null) {
            return System.getProperty("ci");
        } else {
            return getConfigProperties("ci");
        }
    }

    public String getDevice() throws IOException {
        if (System.getProperty("device") != null) {
            return System.getProperty("device");
        } else {
            return getConfigProperties("device");
        }
    }

    public String getRemoteUrl() throws IOException {
        if (getCI().equalsIgnoreCase("ansible")) {
            return "http://MSC-QA-SEHUB01:4444/wd/hub";
        } else if (getCI().equalsIgnoreCase("jenkins")) {
            return "http://MSC-QA-SCRIPT01:4444/wd/hub";
        }
        return null;
    }

    public String getExtension() throws IOException {
        if (System.getProperty("browserExtensionEnabled") != null) {
            return System.getProperty("browserExtensionEnabled");
        } else {
            return getConfigProperties("browserExtensionEnabled");
        }
    }

    public String getExtensionName() throws IOException {
        if (System.getProperty("extensionName") != null) {
            return System.getProperty("extensionName");
        } else {
            return getConfigProperties("extensionName");
        }
    }

    public String getDevToolEnabled() throws IOException {
        if (System.getProperty("devToolEnabled") != null) {
            return System.getProperty("devToolEnabled");
        } else {
            return getConfigProperties("devToolEnabled");
        }
    }


}
