package workflow;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static config.Config.getConfigProperties;

public class BaseWorkflow {

    public WebDriver driver;

    public BaseWorkflow(WebDriver driver) {this.driver = driver;    }

    public String getEnvironment() throws IOException {
        if (System.getProperty("environment") != null) {
            return System.getProperty("environment");
        } else {
            return getConfigProperties("environment");
        }
    }
}
