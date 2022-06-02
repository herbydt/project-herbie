package workflow;

import org.openqa.selenium.WebDriver;
import pages.website1.BaseWebsite1;

public class WorkflowWebsite1 extends BaseWorkflow {

    public WorkflowWebsite1(WebDriver driver) {
        super(driver);
        baseWebsite1 = new BaseWebsite1(driver);
    }

    public BaseWebsite1 baseWebsite1;

    public static String parentHandle;


    //==================================================================================================================
    // Common Methods
    //==================================================================================================================

    public void launchApplication(String url) {
        baseWebsite1.launchApplication(url);
        waitForPageToComplete();
        parentHandle = driver.getWindowHandle();
    }

    public void waitForPageToComplete() {
        baseWebsite1.waitForPageToComplete();
    }

    public void searchItem(String item) throws Exception {
        baseWebsite1.PageGoogleSearch().typeSearchItem(item);
        baseWebsite1.PageGoogleSearch().clickGoogleSearchButton();
    }

    public void moveSecondPage() throws Exception {
        baseWebsite1.PageGoogleSearch().clickGoogleSearchNextPage();
    }

}
