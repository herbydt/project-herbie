package browser;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.Constants;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Chrome implements IBrowser {

    public String device;
    public static boolean extensionToggle = false;
    public static boolean devToolToggle = false;
    public static String extensionName = "";

    @Override
    public WebDriver launchLocal(String device, WebDriver driver, boolean extensionEnabled, String extension, boolean devToolEnabled) throws Exception {
        this.device = device;
        setDriverPath();
        if (this.device.equalsIgnoreCase("android")) {
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities(driver));
        } else if (this.device.equalsIgnoreCase("ios")) {
            return new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities(driver));
        } else {
            extensionToggle = extensionEnabled;
            extensionName = extension;
            devToolToggle = devToolEnabled;
            return new ChromeDriver(getChromeOptions());
        }
    }

    @Override
    public WebDriver launchDocker(String hubUrl, String device, WebDriver driver) throws Exception {
        this.device = device;
        setDriverPath();
        return new RemoteWebDriver(new URL(hubUrl), getChromeOptions());
    }

    public ChromeOptions getChromeOptions() throws Exception {
        ChromeOptions options = new ChromeOptions();

        if (extensionToggle) {
            switch (extensionName.toUpperCase()) {
                case "MOD_HEADER":
                    String projectDirectory = Constants.PROJECT_LOCATION;
//                    int index= projectDirectory.lastIndexOf("/");
//                    projectDirectory = projectDirectory.substring(0, index);
                    options.addExtensions(new File(projectDirectory + "/core-applications/src/main/resources/ModHeader2.5.4.0.crx"));
                    break;
                default: throw new Exception("ERROR: " + extensionName + " extension configuration not available.\n");
            }
        }

        if(devToolToggle) {
            options.addArguments("--auto-open-devtools-for-tabs");
        }

        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        if (this.device.equalsIgnoreCase("desktop")) {

            options.addArguments("disable-infobars");
            options.addArguments("start-maximized");
            options.addArguments("force-device-scale-factor=1.00");
            options.addArguments("high-dpi-support=0.80");
            options.addArguments("window-size=1024,768");
            options.addArguments("no-sandbox");
            options.addArguments("--ignore-certificate-errors");
            // JS logging
            LoggingPreferences pref = new LoggingPreferences();
            pref.enable(LogType.BROWSER, Level.ALL);
            options.setCapability(CapabilityType.LOGGING_PREFS, pref);
            // Network services logging
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            options.setCapability( "goog:loggingPrefs", logPrefs );
        } else if (this.device.equalsIgnoreCase("mobile")) {
            Map<String, Object> mobileEmulation = new HashMap<String, Object>();
            mobileEmulation.put("deviceName", "Galaxy S5");
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-web-security");
        }

        return options;
    }

    @Override
    public DesiredCapabilities getCapabilities(WebDriver driver) {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        if (this.device.equalsIgnoreCase("android")) {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.VERSION, "6.0.1");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S5");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
            capabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        } else if (this.device.equalsIgnoreCase("ios")) {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2.2");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Mac");
        } else {
            System.out.println("FAILED: Device is not defined correctly. \n");
        }

        return capabilities;
    }

    @Override
    public void setDriverPath() {
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Automation\\drivers\\chromedriver.exe");
        } else if (os.contains("Mac")) {
            String username = System.getProperty("user.name");
            System.setProperty("webdriver.chrome.driver", "/Users/" + username + "/Automation/chromedriver");
        } else if (os.contains("Linux")) {
            // fedora / old linux
            System.setProperty("webdriver.chrome.driver", "/opt/Automation/drivers/chromedriver");
            // ubuntu / new linux
//            String username = System.getProperty("user.name");
//            System.setProperty("webdriver.chrome.driver", "/home/" + username + "/Automation/drivers/chromedriver");
        }
    }

}
