package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCashier extends BaseDafabet {

    // MOBILE CASHIER
    By icnMobCashierLeftMenu = By.id("menubile-anchor");
    By icnMobCashierLeftMenuClose = By.cssSelector("span.menubile-content-close.close-icon");
    By txtMobileCashierUsername = By.cssSelector("nav.nav.menubile-nav > div.navigation > div.block.block-cashier > div.profile > span.username-container");
    By txtPaymentOptionsHeader = By.cssSelector("h2.h2.title");
    By tabPaymentOptionsDeposit = By.cssSelector("a.transaction-type-item.deposit");
    By tabPaymentOptionsWithdraw = By.cssSelector("a.transaction-type-item.withdraw");

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

    public boolean isMobileUsernameCorrect(String username) {
        control.click(icnMobCashierLeftMenu);
        String dispUsername = control.getText(txtMobileCashierUsername);
        control.click(icnMobCashierLeftMenuClose);
        if (dispUsername.equalsIgnoreCase(username)) {
            System.out.println("\nCURRENT PAGE: Player Username: " + dispUsername + " is displayed correctly.");
            return true;
        }
        else return false;
    }

    public boolean isPaymentOptionsPageDisplayed(String transaction) {
        closeMobCashierAnnouncementLightbox();
        String tabTitle = control.getText(txtPaymentOptionsHeader);
        String depositTabStats = control.getAttributeValue(tabPaymentOptionsDeposit, "class",10);
        String withdrawTabStats = control.getAttributeValue(tabPaymentOptionsWithdraw, "class",10);

        if ((tabTitle.contains("Payment Option"))&&(depositTabStats.contains("active"))&&(transaction.equalsIgnoreCase("deposit"))) {
            System.out.println("\nCURRENT PAGE: Payment Options for " + transaction + " transaction is displayed correctly.");
            return true;
        } else if ((tabTitle.contains("Payment Option"))&&(withdrawTabStats.contains("active"))&&(transaction.equalsIgnoreCase("withdraw"))) {
            System.out.println("\nCURRENT PAGE: Payment Options for " + transaction + " transaction is displayed correctly.");
            return true;
        } else {
            System.out.println("\nCURRENT PAGE: Payment Options for " + transaction + " transaction is NOT displayed correctly.");
            return false;
        }
    }

}
