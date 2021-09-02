package pages.nextbet;

import org.openqa.selenium.WebDriver;

public class PageNextbet extends BaseNextbet {

    public PageNextbet(WebDriver driver) {
        super(driver);
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean isInPreLoginPage() {
        if (control.isDisplayed(txtUsername) && control.isDisplayed(txtPassword)) {
            System.out.println("CURRENT PAGE: Player is in Pre-Login Home Page.");
            return true;
        }
        return false;
    }

    public boolean isInPostLoginPage() {
        closeAnnouncementLightbox();
        if (control.isDisplayed(icnCashier)) {
            System.out.println("Post Login current URL is: " + driver.getCurrentUrl() + "\n");
            System.out.println("CURRENT PAGE: Player is in Post-Login Home Page.");
            return true;
        }
        return false;
    }

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

    public void waitForNextbetPageToLoad() throws Exception {
        control.waitWhileElementIsNotDisplayed(sectionLoginForm,10);
    }

    public void typeUsername(String username) throws Exception {
        control.type(txtUsername, username);
    }

    public void typePassword(String password) throws Exception {
        control.type(txtPassword, password);
    }

    public void clickButton(String btn) throws Exception {
        switch (btn.toUpperCase()) {
            case "LOGIN" : {
                control.click(btnLogin);
                break;
            }
            default:
                System.out.println("FAILED :" + btn + "button is not included in the list of buttons.");
        }
    }


}
