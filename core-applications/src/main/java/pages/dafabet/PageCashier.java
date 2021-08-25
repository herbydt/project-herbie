package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCashier extends BaseDafabet {

    // MOBILE CASHIER
    By icnMobCashierLeftMenu = By.id("menubile-anchor");
    By icnMobCashierLeftMenuClose = By.cssSelector("span.menubile-content-close.close-icon");
    By txtMobileCashierUsername = By.cssSelector("nav.nav.menubile-nav > div.navigation > div.block.block-cashier > div.profile > span.username-container");
    By lblMobileHeaderTotalBalance = By.cssSelector("span.player-total-balance");
    By lblInnerPageTitle = By.cssSelector("h2.h2.title.mb-tablet-40.mb-desktop-40");

    //DASHBOARD
    By sectionCasinoBlock = By.cssSelector("a.dashboard-balance-item.product.casino");
    By txtCasinoBalance = By.cssSelector("div.product-balance-collapsible.casino > div.balance-breakdown > div.real-money > span.balance");
    By txtCasinoBonus = By.cssSelector("div.product-balance-collapsible.casino > div.balance-breakdown > div.bonus > span.balance");
    By txtCasinoWageringReq = By.cssSelector("div.product-balance-collapsible.casino > div.balance-breakdown > div.wagering-block > div.locked-amount-money > span.balance");
    By sectionOWSportsBlock = By.cssSelector("a.dashboard-balance-item.product.sports");
    By txtOWSportsBalance = By.cssSelector("div.product-balance-collapsible.sports > div.balance-breakdown > div.real-money > span.balance");
    By txtOWSportsBonus = By.cssSelector("div.product-balance-collapsible.sports > div.balance-breakdown > div.locked-amount-money > span.balance");
    By txtOWSportsWageringReq = By.cssSelector("div.product-balance-collapsible.sports > div.balance-breakdown > div.wagering-block > div.locked-amount-money > span.balance");


    // PAYMENT OPTIONS
    By txtPaymentOptionsHeader = By.cssSelector("h2.h2.title");
    By tabPaymentOptionsDeposit = By.cssSelector("a.transaction-type-item.deposit");
    By tabPaymentOptionsWithdraw = By.cssSelector("a.transaction-type-item.withdraw");

    // WITHDRAW
    By lblWithdrawBankEnrollmentStatus = By.cssSelector("div.player-verification-form");


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


    ////////////////
    //   MOBILE   //
    ////////////////

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

    public boolean isFundTransferPageDisplayed() {
//        closeMobCashierAnnouncementLightbox();
        String pageTitle = control.getText(lblInnerPageTitle);
        if (pageTitle.contains("Transfer")) {
            System.out.println("\nCURRENT PAGE: Fund Transfer Transaction page is displayed correctly.");
            return true;
        } else return false;
    }

    public boolean isBankEnrollmentPageDisplayed() {
        String enrollmentStatus = control.getAttributeValue(lblWithdrawBankEnrollmentStatus, "class",10);
        if (!enrollmentStatus.contains("player-verification-form-processed")) {
            System.out.println("\nCURRENT PAGE: Player has not yet enrolled his/her bank account for withdrawal.");
            return true;
        } else return false;
    }

    public boolean isWithdrawPageDisplayed() {
        String enrollmentStatus = control.getAttributeValue(lblWithdrawBankEnrollmentStatus, "class",10);
        String pageTitle = control.getText(lblInnerPageTitle);
        if ((pageTitle.contains("Withdraw")) && (enrollmentStatus.contains("player-verification-form-processed"))) {
            System.out.println("\nCURRENT PAGE: Withdraw Transaction page is displayed correctly.");
            return true;
        } else return false;
    }

    public String getTotalBalanceInMobileHeader() throws Exception {
        String total = "";
        int ctr = 0;
        total = control.getText(lblMobileHeaderTotalBalance, 10);
        while (!total.contains(".")) {
            total = control.getText(lblMobileHeaderTotalBalance, 10);
            Thread.sleep(1000);
            if (ctr > 10) {
                throw new Exception("ERROR: Balance header still not loaded after 10secs. Please try again.");
            }
        }
        System.out.println("Total balance in header: " + total + "\n");
        return total.replace(",", ""); //this is to remove "," on values that are more than 3 disgits
    }

    public String getProductBalance (String product) throws Exception {
        switch (product.toUpperCase()) {
            case "CASINO" : {
                control.click(sectionCasinoBlock);
                return control.getText(txtCasinoBalance);
            }
            case "OW SPORTS" : {
                control.click(sectionOWSportsBlock);
                return control.getText(txtOWSportsBalance);
            }
            default : return "";
        }
    }

    public String getProductBonus (String product) throws Exception {
        switch (product.toUpperCase()) {
            case "CASINO" : {
                return control.getText(txtCasinoBonus);
            }
            case "OW SPORTS" : {
                return control.getText(txtOWSportsBonus);
            }
            default : return "";
        }
    }

    public String getProductWageringRequirements (String product) throws Exception {
        switch (product.toUpperCase()) {
            case "CASINO" : {
                return control.getText(txtCasinoWageringReq);
            }
            case "OW SPORTS" : {
                return control.getText(txtOWSportsWageringReq);
            }
            default : return "";
        }
    }

}
