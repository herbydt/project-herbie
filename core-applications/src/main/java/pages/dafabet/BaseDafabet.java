package pages.dafabet;

import libraries.PageObjectBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.dafabet.PageDafabet;

public class BaseDafabet extends PageObjectBase {

    public BaseDafabet(WebDriver driver) {
        super(driver);
    }

    // REGISTRATION FIELDS
    public String RegUsername;
    public String RegPassword;
    public String RegEmail;
    public String RegAreaCode;
    public String RegMobileNumber;
    public String RegFirstName;
    public String RegLastName;
    public String RegDateOfBirth;
    public String RegCurrency;
    public String RegCountry;
    public String RegLanguage;

    // PRE LOOGIN
    By txtUsername = By.id("LoginForm_username");
    By txtPassword = By.id("LoginForm_password");
    By btnLogin = By.id("LoginForm_submit");
    By btnRegister = By.cssSelector("a.btn.btn-yellow.btn-small.join-now");
    By formRegister = By.cssSelector("form.registration-form.pure-form.form-vertical");

    // POST LOGIN
    By iconCashier = By.cssSelector("a.cashier-label");
    By btnCashier = By.cssSelector("li.cashier-tooltip.tooltip.last");

    // LIGHTBOX
    By lightboxAnnouncement = By.cssSelector("div.modal.announcement.modal-active");
//    By announcementLightboxCloseButton = By.cssSelector("#announcement-lightbox > div.modal-content > span.modal-close.modal-close-button.lazy-load.lazy-loaded");
    By announcementLightboxCloseButton = By.cssSelector("div.modal-content > span.modal-close.modal-close-button.lazy-load.lazy-loaded");
    By announcementRegLightboxCloseButton = By.cssSelector("#announcementLightbox > div.modal-content > span.modal-close.modal-close-button");
    By icnMinimzeChat = By.cssSelector("section.livechat > h1 > span");


    //==================================================================================================================
    // Page Navigation
    //==================================================================================================================

    public PageDafabet PageDafabet() {
        return new PageDafabet(driver);
    }
    public PageCashier PageCashier() {
        return new PageCashier(driver);
    }
    public PageRegister PageRegister() {
        return new PageRegister(driver);
    }
    public PageMakeDeposit PageMakeDeposit() {
        return new PageMakeDeposit(driver);
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

    public void minimizeLiveChat() {
        try {
            if (control.isDisplayed(icnMinimzeChat)) {
                control.click(icnMinimzeChat);
            }
        } catch (NullPointerException npe) {
            System.out.println("Live chat is not displayed.");
        }
    }
}
