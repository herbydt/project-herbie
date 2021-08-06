package libraries;

import browser.BrowserFactory;
import browser.IBrowser;
import browser.extensions.ChromeExtensions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class SeleniumBase {

    public static AppiumBase appium;
    public WebDriver driver;

    public WebDriver setupDriver(boolean grid, boolean docker, String browserName, String remoteUrl, String device, boolean extensionEnabled, String extensionName, boolean devToolEnabled) throws Exception {

        BrowserFactory factory = new BrowserFactory();
        ChromeExtensions chromeExtensions = new ChromeExtensions();
        IBrowser browser = factory.getBrowser(browserName);

        if (driver == null) {
            if (docker) {
                /*rnquizon: '4444' this might be updated to variable due to port assignment for
                 * for parallel execution.
                 */
                driver = browser.launchDocker("http://localhost:4444/wd/hub/", device, driver);
            } else {
                driver = browser.launchLocal(device, driver, extensionEnabled, extensionName, devToolEnabled);
                if (extensionEnabled) {
                    chromeExtensions.extensions(device, driver, extensionName);

                    //Added to reset windows to 1 (main window)
                    ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
                    if (windows.size() > 1) {
                        driver.close();
                        driver.switchTo().window("");
                    }
                }
            }
        }
        return driver;
    }

}
