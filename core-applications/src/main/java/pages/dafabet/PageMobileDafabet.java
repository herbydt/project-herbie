package pages.dafabet;

import org.openqa.selenium.WebDriver;

public class PageMobileDafabet extends BaseDafabet {

    public PageMobileDafabet(WebDriver driver) {
        super(driver);
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean isInMobilePreLoginPage() {
        if (control.isDisplayed(btnHeaderLogin)) {
            System.out.println("CURRENT PAGE: Player is in Pre-Login Home Page.");
            return true;
        }
        return false;
    }

    public boolean isInMobilePostLoginPage() {
        closeAnnouncementLightbox();
        if (control.isDisplayed(icnMobileCashier)) {
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
        control.type(txtMobUsername, username);
    }

    public void typePassword(String password) throws Exception {
        control.type(txtMobPassword, password);
    }

    public void clickButton(String btn) throws Exception {
        switch (btn.toUpperCase()) {
            case "HEADER LOGIN": {
                control.click(btnHeaderLogin);
                control.waitWhileElementIsNotDisplayed(lightboxLogin, 10);
                break;
            }
            case "REGISTRATION": {
                waitForMobilePageToComplete();
                control.click(btnHeaderLogin);
                control.waitWhileElementIsNotDisplayed(lightboxLogin, 10);
                control.click(btnMobileRegister);
                waitForPageToComplete();
                break;
            }
            case "LOGIN": {
                control.click(btnMobileLogin);
                break;
            }
            case "DAFABET LOGO": {
                control.click(imgDafabetLogo);
                waitForPageToComplete();
                break;
            }
            case "CASHIER": {
                String cashierLink = control.getAttributeValue(icnMobileCashierLink, "href", 10);
                launchApplication(cashierLink);
                waitForPageToComplete();
                break;
            }
            case "DEPOSIT": {
                control.click(btnDeposit);
                waitForPageToComplete();
                break;
            }
            case "WITHDRAW": {
                control.click(btnWithdraw);
                waitForPageToComplete();
                break;
            }
            case "FUND TRANSFER": {
                control.click(btnFundTransfer);
                waitForPageToComplete();
                break;
            }
            case "CASH POINTS": {
                control.click(btnCashPoints);
                waitForPageToComplete();
                break;
            }
            case "HISTORY": {
                control.click(btnTrxHistory);
                waitForPageToComplete();
                int attempts = 0;
                while(attempts < 30) {
                    if(!control.getAttributeValue(icnTrxHistoryLoaderTop,"style",10).contains("none")) {
                        System.out.println("Loading Transaction History Contents...\n");
                        attempts++;
                    } else {
                        break;
                    }
                }
                break;
            }
            default:
                System.out.println("FAILED :" + btn + "button is not included in the list of buttons.");
        }
    }


}
