package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageDafabet extends BaseDafabet {

    public PageDafabet(WebDriver driver) {
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

    public void clickButton(String btn) throws Exception {
        switch (btn.toUpperCase()) {
            case "LOGIN": {
                control.click(btnLogin);
                break;
            }
            case "CASHIER": {
                control.click(btnCashier);
                switchToWindow(1);
                waitForPageToComplete();
                break;
            }
            case "REGISTRATION": {
                control.click(btnRegister);
                break;
            }
            case "MYACCOUNT": {
                control.hoverToElement(toolTipMyAccountContainer);
                control.click(toolTipMyAccountProfile);

                if(driver.getWindowHandles().size() == 3) {
                    switchToWindow(driver.getWindowHandles().size() - 2); //workaround for window navigation; to be updated.
                    switchToWindow(driver.getWindowHandles().size() - 1);
                } else if(!driver.getTitle().contains("View Profile")){
                    switchToWindow(1);
                }
                waitForPageToComplete();
                break;
            }
//            case "SAVE CHANGES": {
//                control.click(btnMyAccSaveChange);
//                break;
//            }
//            case "CONFIRM CHANGES": {
//                control.click(btnConfSaveChange);
//                break;
//            }
            default:
                System.out.println("FAILED :" + btn + "button is not included in the list of buttons.");
        }
    }
}
