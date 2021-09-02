package workflow;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.nextbet.BaseNextbet;

public class WorkflowNextbet extends BaseWorkflow {

    public WorkflowNextbet(WebDriver driver) {
        super(driver);
        baseNextbet = new BaseNextbet(driver);
    }

    public BaseNextbet baseNextbet;

    public static String parentHandle;

    static Logger log = LoggerFactory.getLogger(WorkflowDafabet.class);

    //==================================================================================================================
    // Local Declarations
    //==================================================================================================================

    private enum State {
        PRE_LOGIN("Pre-Login"),
        POST_LOGIN("Post-Login");
        private String state;

        State(String state) {
            this.state = state;
        }

        public String value() {
            return this.state;
        }
    }

    private WorkflowNextbet.State toState(String state) {
        WorkflowNextbet.State[] states = WorkflowNextbet.State.values();
        for (WorkflowNextbet.State status : states) {
            if (status.value().equalsIgnoreCase(state)) {
                return status;
            }
        }
        return null;
    }

    //==================================================================================================================
    // Common Methods
    //==================================================================================================================

    public void launchApplication(String url) {
        baseNextbet.launchApplication(url);
        waitForPageToComplete();
        parentHandle = driver.getWindowHandle();
    }

    public void waitForPageToComplete() {
        baseNextbet.waitForPageToComplete();
    }

//    public void clickButton (String button) throws Exception {
//        baseNextbet.PageNextbet().clickButton(button);
//    }

    public void closeCurrentWindow() {
        driver.close();
        driver.switchTo().window(parentHandle);
    }

    //==================================================================================================================
    // Dafabet Login
    //==================================================================================================================

    public void loginPlayer (String username, String password) throws Exception {
//        baseNextbet.PageNextbet().waitForNextbetPageToLoad();
        baseNextbet.PageNextbet().typeUsername(username);
        baseNextbet.PageNextbet().typePassword(password);
        baseNextbet.PageNextbet().clickButton("Login");
        waitForPageToComplete();
    }

    public boolean isInPage(String state) throws Exception {
        baseNextbet.closeAnnouncementLightbox();

        WorkflowNextbet.State s = toState(state);

        if (s == WorkflowNextbet.State.PRE_LOGIN) {
            System.out.println("\nCURRENT PAGE: Player is in PRE-LOGIN" + "\n");
            return baseNextbet.PageNextbet().isInPreLoginPage();
        } else if (s == WorkflowNextbet.State.POST_LOGIN) {
            System.out.println("\nCURRENT PAGE: Player is in POST-LOGIN" + "\n");
            return baseNextbet.PageNextbet().isInPostLoginPage();
        }
        return false;
    }

//    public void logout() throws Exception {
//        int windowCount = driver.getWindowHandles().size();
//        if (windowCount > 1) {
//            baseNextbet.switchToWindow(0);
//        }
//        baseNextbet.refreshPage();
//        waitForPageToComplete();
//        baseNextbet.PageNextbet().logout();
//        waitForPageToComplete();
//    }


}
