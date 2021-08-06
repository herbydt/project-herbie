package steps;

import libraries.PageObjectBase;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class BaseStep extends PageObjectBase{

    public BaseStep(WebDriver driver) {
        super(driver);
    }

    public static SoftAssert softAssert;

    public String getDafabetROWCMSUrl(String product) throws IOException {
        return getApplicationProperties(product + "." + getEnvironment() + ".drupal.url");
    }

}

