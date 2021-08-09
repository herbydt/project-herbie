package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageDafabet extends BaseDafabet {

    public PageDafabet(WebDriver driver) {
        super(driver);
    }

    By iconCashier = By.cssSelector("a.cashier-label");

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
//        String currentURL[] = driver.getCurrentUrl().split("/");
//        String lastWord = currentURL[currentURL.length - 1];

        closeAnnouncementLightbox();
        if (control.isDisplayed(iconCashier)) {
            System.out.println("Post Login current URL is: " + driver.getCurrentUrl() + "\n");
            System.out.println("CURRENT PAGE: Player is in Post-Login Home Page.");
            return true;
        }
        return false;
    }

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

    public void typeUsername(String username) throws Exception {
        control.type(txtUsername, username);
    }

    public void typePassword(String password) throws Exception {
        control.type(txtPassword, password);
    }

    public void clickLoginBtn() throws Exception {
        control.click(btnLogin);
    }

}
