package workflow;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.dafabet.BaseDafabet;

public class WorkflowDafabet extends BaseWorkflow {

    public WorkflowDafabet(WebDriver driver) {
        super(driver);
        baseDafabet = new BaseDafabet(driver);
    }

    public BaseDafabet baseDafabet;

    public static String parentHandle;

    static Logger log = LoggerFactory.getLogger(WorkflowDafabet.class);

    //==================================================================================================================
    // Local Declarations
    //==================================================================================================================
//    private void trackPage(String page) {
//        log.info("Page requested: " + page + "\n");
//    }

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

    private State toState(String state) {
        State[] states = State.values();
        for (State status : states) {
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
        baseDafabet.launchApplication(url);
        waitForPageToComplete();
        parentHandle = driver.getWindowHandle();
    }

    public void waitForPageToComplete() {
        baseDafabet.waitForPageToComplete();
    }

    public void clickButton (String button) throws Exception {
        baseDafabet.PageDafabet().clickButton(button);
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean validatePage(String page, String username) {
        if (page.equalsIgnoreCase("cashier")) {
            return baseDafabet.PageCashier().isUsernameCorrect(username);
        } else if (page.equalsIgnoreCase("registration")) {
            return baseDafabet.PageRegister().isRegPageDisplayed();
        } else
            return false;
    }

    //==================================================================================================================
    // Dafabet Login
    //==================================================================================================================

    public void loginPlayer (String username, String password) throws Exception {
//        baseDafabet.closeAnnouncementLightbox();
        baseDafabet.PageDafabet().typeUsername(username);
        baseDafabet.PageDafabet().typePassword(password);
        baseDafabet.PageDafabet().clickButton("Login");
        waitForPageToComplete();
    }

    public boolean isInPage(String state) throws Exception {
        baseDafabet.closeAnnouncementLightbox();

        State s = toState(state);

        if (s == WorkflowDafabet.State.PRE_LOGIN) {
            System.out.println("\nCURRENT PAGE: Player is in PRE-LOGIN" + "\n");
            return baseDafabet.PageDafabet().isInPreLoginPage();
        } else if (s == WorkflowDafabet.State.POST_LOGIN) {
            System.out.println("\nCURRENT PAGE: Player is in POST-LOGIN" + "\n");
            return baseDafabet.PageDafabet().isInPostLoginPage();
        }
        return false;
    }

    //==================================================================================================================
    // Dafabet Access Cashier
    //==================================================================================================================



}
