package steps;

import libraries.PageObjectBase;
import libraries.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import workflow.WorkflowWebsite1;

import java.io.IOException;

public class BaseStep extends TestBase{

    public static WorkflowWebsite1 workflowWebsite1;

    public static SoftAssert softAssert;

    public String getDafabetROWCMSUrl(String product) throws IOException {
        return getApplicationProperties(product + "." + getEnvironment() + ".drupal.url");
    }

    //==================================================================================================================
    // Test Utility Methods
    //==================================================================================================================
    public boolean isRealDevice() throws IOException {
        if (getDevice().equalsIgnoreCase("android") || getDevice().equalsIgnoreCase("ios")) {
            System.out.println("DEBUG: Device selected is - " + getDevice() + ". \n");
            return true;
        }
        System.out.println("DEBUG: NOT a real device. Selected is - " + getDevice() + ". \n");
        return false;
    }

    public String getJSErrosLog() {
        // Capture all JSerrors and print in console.
        LogEntries jserrors = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry error : jserrors) {
            System.out.println("FAILED: Javascript ERROR! " + error.getMessage());
            return "Browser Console Error: \n" + error.getMessage();
        }
        return "NO console error. \n";
    }

}

