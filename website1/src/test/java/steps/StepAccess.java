package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepAccess extends BaseStep {

    BaseStep base;

    public StepAccess(BaseStep base) {
        this.base = base;
    }

    @Given("^the player is at Ghana desktop site in EN language$")
    public void thePlayerIsAtGhanaDesktopSiteInENLanguage() throws Throwable {
        workflowWebsite1.launchApplication("https://www.google.com");
        workflowWebsite1.baseWebsite1.waitForPageToComplete();
    }
}
