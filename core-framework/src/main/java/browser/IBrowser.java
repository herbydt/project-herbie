package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public interface IBrowser {

    public WebDriver launchLocal(String device, WebDriver driver, boolean extensionEnabled, String extensionName, boolean devToolEnabled) throws Exception;

    public WebDriver launchDocker(String hubUrl, String device, WebDriver driver) throws Exception;

    public DesiredCapabilities getCapabilities(WebDriver driver);

    public void setDriverPath();
}
