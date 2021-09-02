package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepLogin extends BaseStep {

    BaseStep base;

    public StepLogin(BaseStep base) {
        this.base = base;
    }

    @Given("^the player is at ([^\"]*) - ([^\"]*) site in ([^\"]*) language$")
    public void thePlayerIsAtSite(String site, String product, String language) throws Throwable {
        if (product.equalsIgnoreCase("entry")) {
            product = "";
        }
        workflowNextbet.launchApplication(getSiteUrl(site)+ "/" + language.toLowerCase() + "/" + product.toLowerCase());
        workflowNextbet.baseNextbet.waitForPageToComplete();
        workflowNextbet.baseNextbet.PageNextbet().waitForNextbetPageToLoad();
        workflowNextbet.baseNextbet.closeAnnouncementLightbox();
        baseLanguage = language;
    }

    @When("^the player logs in using (valid|new) - ([^\"]*) desktop credentials$")
    public void thePlayerLogsInUsingValidCredentials(String player, String currency) throws Throwable {

        switch (player.toUpperCase()) {
            case "VALID": {
                baseUsername = getApplicationProperties(baseCurrentPage + "." + currency.toUpperCase() + "." + getEnvironment() + ".username");
                basePassword = getApplicationProperties(baseCurrentPage + "." + currency.toUpperCase() + "." + getEnvironment() + ".password");
                break;
            }
            case "NEW": {
                basePassword = baseNewPassword;
                break;
            }
            default: {
                baseUsername = getApplicationProperties(baseCurrentPage + "." + getEnvironment() + ".username");
                basePassword = getApplicationProperties(baseCurrentPage + "." + getEnvironment() + ".password");
            }
        }
        System.out.println("Username: " + baseUsername + "\n");
        System.out.println("Password: " + basePassword + "\n");
        workflowNextbet.loginPlayer(baseUsername, basePassword);
        CurrentState = "Post-Login";
    }

    @Then("^the player is logged in successfully$")
    public void thePlayerIsLoggedInSuccessfully() throws Throwable {
        softAssert.assertTrue(workflowNextbet.isInPage(CurrentState), "FAILED: Page validation failed for " + CurrentState + ". \n");
    }

}
