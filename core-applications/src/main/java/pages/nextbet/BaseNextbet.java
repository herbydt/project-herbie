package pages.nextbet;

import libraries.PageObjectBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseNextbet extends PageObjectBase {

    public BaseNextbet(WebDriver driver) {
        super(driver);
    }

    // LIGHTBOX
    By announcementLightboxCloseButton = By.cssSelector("div.modal-content > span.modal-close.modal-close-button");

    // PRE LOOGIN
    By sectionLoginForm = By.cssSelector("div.loginform-textfield-wrapper");
    By txtUsername = By.cssSelector("span.username-field-wrapper > input.field");
    By txtPassword = By.id("login-password");
    By btnLogin = By.cssSelector("div.loginform-button-wrapper > input.btn.btn-gray.btn-small.login-submit");

    // POST LOGIN
    By icnCashier = By.cssSelector("a.cashier-label");

    //==================================================================================================================
    // Class Methods
    //==================================================================================================================

    public void waitForPageToComplete() {
        control.waitForPageComplete();
    }

    public void closeAnnouncementLightbox() {
        try {
            if (control.isDisplayed(announcementLightboxCloseButton)) {
                control.click(announcementLightboxCloseButton);
                control.waitForPageComplete();
            }
        } catch (NullPointerException npe) {
            System.out.println("Announcement popup is not displayed.");
        }
    }

    //==================================================================================================================
    // Page Navigation
    //==================================================================================================================

    public PageNextbet PageNextbet() {
        return new PageNextbet(driver);
    }

}
