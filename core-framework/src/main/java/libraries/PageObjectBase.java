package libraries;

import config.Config;
import control.ControlBase;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class PageObjectBase {

    protected WebDriver driver;
    protected ControlBase control;
    protected int DEFAULT_WAIT_TIME = 10;

    public PageObjectBase(WebDriver driver) {
        this.driver = driver;
        this.control = new ControlBase(driver);
    }

    public void launchApplication(String url) {
        boolean retry=true;
        int ctr = 1;

        while (retry) {
            try {
                control.waitForPageComplete();
                driver.get(url);
                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                retry = false;
            } catch (AssertionError e) {
                driver.navigate().refresh();
                driver.get(url);
                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                retry = false;
            } catch (org.openqa.selenium.TimeoutException te) {
                if (ctr < 6) {
                    System.out.println("SEVERE TIMEOUT ENCOUNTERED: Retrying[" + ctr + "]...\n");
                    retry = true;
                    ctr++;
                } else {
                    System.out.println("SEVERE TIMEOUT is still countered after " + ctr + " retries.\n");
                    driver.quit();
                }
            }

            System.out.println("DEBUG: Started chrome...\n");
        }
    }

    //===============
    //  TEST BASE
    //===============
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

    public String getEnvironment() throws IOException {
        if (System.getProperty("environment") != null) {
            return System.getProperty("environment").toLowerCase();
        } else {
            return getConfigProperties("environment").toLowerCase();
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

    //==================================================================================================================
    // Get property methods
    //==================================================================================================================
    public static String getApplicationProperties(String key) throws IOException
    {
        return getProperties("app", key);
    }

    public static String getDBProperties(String key) throws IOException
    {
        return getProperties("db", key);
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

    public static String getServerProperties(String key) throws IOException
    {
        Properties prop = new Properties();
        InputStream input = Config.class.getClassLoader().getResourceAsStream("server.properties");
        if (input != null) {
            prop.load(input);
        } else {
            //LOGGER.error("Could not open config file. Check that " + propertiesName + " is added to classpath");
        }
        return prop.getProperty(key);
    }


}
