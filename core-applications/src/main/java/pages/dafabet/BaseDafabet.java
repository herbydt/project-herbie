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

    // MY ACCOUNT FIELDS
    public String RegAddress = "";
    public String RegCity = "";
    public String RegPostalCode = "0000";
    public String MyAccNewPassword;
    public String MyAccConfNewPassword;

    // PRE LOOGIN
    By txtUsername = By.id("LoginForm_username");
    By txtPassword = By.id("LoginForm_password");
    By btnLogin = By.id("LoginForm_submit");
    By btnRegister = By.cssSelector("a.btn.btn-yellow.btn-small.join-now");
    By formRegister = By.cssSelector("form.registration-form.pure-form.form-vertical");
    By linkCantLogin = By.cssSelector("a.login-issue.btn-links");
    By tabForgotUsername =  By.cssSelector("ul.tab-menu > li > a[href*='#forgot-username-content']");
    By tabForgotPassword =  By.cssSelector("ul.tab-menu > li > a[href*='#forgot-password-content']");

    // POST LOGIN
    By iconCashier = By.cssSelector("a.cashier-label");
    By btnCashier = By.cssSelector("li.cashier-tooltip.tooltip.last");
    By btnMyAccount = By.cssSelector("li.cashier-tooltip.tooltip.last");
    By toolTipMyAccountContainer = By.cssSelector("li.myaccount-tooltip.tooltip");
    By toolTipMyAccountProfile = By.cssSelector("li.myaccount-tooltip.tooltip a[href*='my-profile']");
    By toolTipMyAccountChangePass = By.cssSelector("li.myaccount-tooltip.tooltip a[href*='change-password']");
    By btnLogout =  By.cssSelector("li.myaccount-tooltip.tooltip a[href*='logout']");

    // LIGHTBOX
    By lightboxAnnouncement = By.cssSelector("div.modal.announcement.modal-active");
//    By announcementLightboxCloseButton = By.cssSelector("#announcement-lightbox > div.modal-content > span.modal-close.modal-close-button.lazy-load.lazy-loaded");
    By announcementLightboxCloseButton = By.cssSelector("div.modal-content > span.modal-close.modal-close-button.lazy-load.lazy-loaded");
    By announcementRegLightboxCloseButton = By.cssSelector("#announcementLightbox > div.modal-content > span.modal-close.modal-close-button");
    By icnMinimzeChat = By.cssSelector("section.livechat > h1 > span");

    // YOPMAIL
    By txtEmailSearch = By.id("login");
    By btnEmailSearch = By.id("refreshbut");
    By txtSearchResultsHeader = By.cssSelector("div.ycptalias");
    By sectionSearchResults = By.id("wminboxmain");

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
    public PageMyAccount PageMyAccount() {
        return new PageMyAccount(driver);
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
