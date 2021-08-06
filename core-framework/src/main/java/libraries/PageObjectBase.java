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
