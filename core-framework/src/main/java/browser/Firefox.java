package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class Firefox implements IBrowser{

    public String device;

//	@Override
//	public WebDriver launchGrid(String hubUrl, String device, WebDriver driver) throws MalformedURLException {
//		return null;
//	}

    @Override
    public WebDriver launchLocal(String device, WebDriver driver, boolean extensionEnabled, String extension, boolean devToolEnabled) throws Exception {
        this.device = device;
        setDriverPath();
        FirefoxProfile fp = new FirefoxProfile();
        fp.setAcceptUntrustedCertificates(true);
        fp.setAssumeUntrustedCertificateIssuer(false);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(fp);
        options.setCapability("marionette", true);

        return new FirefoxDriver(options);
    }

    @Override
    public WebDriver launchDocker(String hubUrl, String device, WebDriver driver) throws MalformedURLException {
        return null;
    }

    @Override
    public DesiredCapabilities getCapabilities(WebDriver driver) {
        return null;
    }

    @Override
    public void setDriverPath() {
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Automation\\drivers\\geckodriver.exe");
        } else if (os.contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", "/opt/selenium-drivers/chromedriver");
        }
    }
}
