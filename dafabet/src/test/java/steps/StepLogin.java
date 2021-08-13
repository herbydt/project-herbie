package steps;

import cucumber.api.PendingException;
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
        workflowDafabet.launchApplication(getSiteUrl(site)+ "/" + language.toLowerCase() + "/" + product.toLowerCase());
        workflowDafabet.baseDafabet.waitForPageToComplete();
        workflowDafabet.baseDafabet.closeAnnouncementLightbox();
        baseLanguage = language;
    }


    @When("^the player logs in using (valid|new) credentials$")
    public void thePlayerLogsInUsingValidCredentials(String player) throws Throwable {

        switch (player.toUpperCase()) {
            case "VALID": {
                baseUsername = getApplicationProperties(baseCurrentPage + "." + getEnvironment() + ".username");
                basePassword = getApplicationProperties(baseCurrentPage + "." + getEnvironment() + ".password");
                break;
            }
            case "NEW": {
//                baseUsername = getApplicationProperties(baseCurrentPage + "." + getEnvironment() + "." + "username");
                basePassword = baseNewPassword;
                break;
            }
//            case "NEW PASSWORD": {
//                baseUsername = NewlyRegisteredPlayer;
//                basePassword = baseNewPassword;
//                break;
//            }
            default:
//                if(!baseIsNewPasswordSet)
                {
                    baseUsername = getApplicationProperties(baseCurrentPage + "." + getEnvironment() + ".username");
                    basePassword = getApplicationProperties(baseCurrentPage + "." + getEnvironment() + ".password");
                }
        }
        System.out.println("Username: " + baseUsername + "\n");
        System.out.println("Password: " + basePassword + "\n");
        workflowDafabet.loginPlayer(baseUsername, basePassword);
        CurrentState = "Post-Login";
    }

    @Then("^the player is logged in successfully$")
    public void thePlayerIsLoggedInSuccessfully() throws Throwable {
        softAssert.assertTrue(workflowDafabet.isInPage(CurrentState), "FAILED: Page validation failed for " + CurrentState + ". \n");
    }
}
