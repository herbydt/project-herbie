package browser.extensions;

import browser.Chrome;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by rnquizon on Mar/24/2020
 */

public class ChromeExtensions extends Chrome {

    public void modHeader(String device, WebDriver driver) {
        driver.get("chrome-extension://idgpnmonknjnojddfkpgkljpfnnfcklj/icon.png");
        ((JavascriptExecutor)driver).executeScript(
                "localStorage.setItem('profiles', JSON.stringify([{                " +
                        "  title: 'Selenium', hideComment: true, appendMode: '',           " +
                        "  headers: [                                                      " +
                        "    {enabled: true, name: 'X-Forwarded-IP', value: '202.151.35.162', comment: ''}, " +
                        "  ],                                                              " +
                        "  respHeaders: [],                                                " +
                        "  filters: []                                                     " +
                        "}]));                                                             " );
    }

    public void extensions(String device, WebDriver driver, String extensionName) throws Exception {
        switch (extensionName.toUpperCase()) {
            case "MOD_HEADER": modHeader(device, driver); break;
            default: throw new Exception("ERROR: " + extensionName + " extension configuration not available.\n");
        }
    }

}
