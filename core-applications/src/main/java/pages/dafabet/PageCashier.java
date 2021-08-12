package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCashier extends BaseDafabet {

    public PageCashier(WebDriver driver) {
        super(driver);
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean isUsernameCorrect(String username) {
        String dispUsername = control.getText(By.cssSelector("span.username.ng-binding"));
        if (dispUsername.equalsIgnoreCase(username)) {
            System.out.println("\nCURRENT PAGE: Player Username: " + dispUsername + " is displayed correctly.");
            return true;
        }
        else return false;
    }

}
