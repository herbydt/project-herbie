package steps;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepAccess extends BaseStep {
    @Given("^the player is at Ghana desktop site in EN language$")
    public void thePlayerIsAtGhanaDesktopSiteInENLanguage() throws Throwable {
        workflowDafabetGhanaDesktop.baseDafabetGhanaDesktop.waitForPageToComplete();
        workflowDafabetGhanaDesktop.baseDafabetGhanaDesktop.closeAnnouncementLightbox();
//        workflowDafabetGhanaDesktop.launchApplication(getDafabetROWUrl(page) + "/" + (baseLanguageCode = language.toLowerCase()));
        workflowDafabetGhanaDesktop.launchApplication(getDafabetROWUrl(page) + "/");
        baseSelectedProduct = page;
        switch (page) {
            case "Ghana": {
                baseCurrentPage = "GHANA";
                baseState = "Pre-Login";
                baseLanguage = "English (Ghana)";
                break;
            }
            case "Kenya": {
                baseCurrentPage = "KENYA";
                baseState = "Pre-Login";
                baseLanguage = "Swahili (Kenya)";
                break;
            }
            default:
                throw new Exception("COUNTRY IN THE SELECTION");
        }
        workflowDafabetGhanaDesktop.baseDafabetGhanaDesktop.currentDafabetUrl = base.driver.getCurrentUrl();
    }
}
