package pages.dafabet;

import libraries.PageObjectBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.dafabet.PageDafabet;

public class BaseDafabet extends PageObjectBase {

    public BaseDafabet(WebDriver driver) {
        super(driver);
    }

    By txtUsername = By.id("LoginForm_username");
    By txtPassword = By.id("LoginForm_password");
    By btnLogin = By.id("LoginForm_submit");

    // LIGHTBOX
    By lightboxAnnouncement = By.cssSelector("div.modal.announcement.modal-active");
//    By announcementLightboxCloseButton = By.cssSelector("#announcement-lightbox > div.modal-content > span.modal-close.modal-close-button.lazy-load.lazy-loaded");
    By announcementLightboxCloseButton = By.cssSelector("div.modal-content > span.modal-close.modal-close-button.lazy-load.lazy-loaded");
    By announcementRegLightboxCloseButton = By.cssSelector("#announcementLightbox > div.modal-content > span.modal-close.modal-close-button");


    //==================================================================================================================
    // Page Navigation
    //==================================================================================================================

    public PageDafabet PageDafabet() {
        return new PageDafabet(driver);
    }

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
}
