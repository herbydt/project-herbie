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

    @Given("^the player is at ([^\"]*) site in EN language$")
    public void thePlayerIsAtSite(String site) throws Throwable {
        workflowWebsite1.launchApplication(getSiteUrl(site) + "/");
        workflowWebsite1.baseWebsite1.waitForPageToComplete();
    }

    @When("^the player searches for ([^\"]*)$")
    public void thePlayerSearches(String item) throws Throwable {
        System.out.println("Search item: " + item + "\n");
        baseSearchItem = item;
        workflowWebsite1.searchItem(item);
        workflowWebsite1.baseWebsite1.waitForPageToComplete();
    }

    @When("^the user goes to the second page$")
    public void thePlayerGoesToSecondPage() throws Throwable {
        workflowWebsite1.moveSecondPage();
        workflowWebsite1.baseWebsite1.waitForPageToComplete();
    }

    @Then("^the search results are displayed$")
    public void theSearchResultsAreDisplayed() throws Throwable {
//        System.out.println("Search item: " + item + "\n");
        softAssert.assertTrue(workflowWebsite1.baseWebsite1.PageGoogleSearch().verifySearchResultsPage(baseSearchItem), "FAILED: The Google Search failed to display correct results");

    }

}
