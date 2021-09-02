package steps;

import libraries.TestBase;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import workflow.WorkflowNextbet;

import java.io.IOException;
import java.util.List;

public class BaseStep extends TestBase{

    public static WorkflowNextbet workflowNextbet;

    public static SoftAssert softAssert;

    // Variables
    public static String baseCurrentPage;
    public static String baseUsername;
    public static String basePassword;
    public static String baseNewPassword;
    public static String baseLanguage;
    public static String CurrentState = "Pre-Login";
    public static String NewlyRegisteredPlayer;



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

    public String getSiteUrl(String site) throws IOException {
        baseCurrentPage = site.toUpperCase();
        return getApplicationProperties(site + "." + getEnvironment() + ".url");
    }


}
