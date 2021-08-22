package pages.dafabet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class PageCashierFundTransfer extends BaseDafabet {

    public PageCashierFundTransfer(WebDriver driver) {
        super(driver);
    }

    By ddlTransferFromProduct = By.id("edit-transfer-from");
    By imgTransferFromBalanceLoader = By.cssSelector("div.transfer-from-loading");
    By ddlTransferToProduct = By.id("edit-transfer-to");
    By txtTransferAmount = By.id("edit-amount");
    By btnTransferSubmit = By.id("edit-submit");
    By msgSuccessfulTransaction = By.cssSelector("div[id='cashier-result-container'] > p.blurb.top-blurb");


    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

    public void selectTransferFromProduct(String product) throws Exception {
        String balanceLoader = "";
        int ctr = 0;
        control.selectDropdownOption(ddlTransferFromProduct, product);

        balanceLoader = control.getAttributeValue(imgTransferFromBalanceLoader, "class",10);
        while (!balanceLoader.contains("hidden")) {
            balanceLoader = control.getAttributeValue(imgTransferFromBalanceLoader, "class",10);
            Thread.sleep(1000);
            if (ctr > 10) {
                throw new Exception("ERROR: Prooduct Balance still not loaded after 10secs. Please try again.");
            }
        }
//        control.waitWhileElementIsNotDisplayed(imgTransferFromBalanceLoader, 10, "Loader is not displayed.");
    }

    public void selectTransferToProduct(String product) throws Exception {
        control.selectDropdownOption(ddlTransferToProduct, product);
    }

    public void typeTransferAmount(String amount) throws Exception {
        control.type(txtTransferAmount, amount);
    }

    public void clickSubmitTransfer() throws Exception {
        control.click(btnTransferSubmit);
//        control.waitWhileElementIsDisplayed(imgCashierLoader, 10, "Loader is not displayed.");
        waitForPageToComplete();
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean validateSuccessfulFundTransferTransaction() {
        String dispMessage = control.getText(msgSuccessfulTransaction);
        if ((dispMessage.contains("Fund Transfer")) && (dispMessage.contains("successfully"))) {
            System.out.println("\nPASSED: " + dispMessage + "\n");
            return true;
        } else if ((dispMessage.contains("Please wait")) && (dispMessage.contains("balance will be refreshed"))) {
            System.out.println("\nPASSED: " + dispMessage + "\n");
            return true;
        }
        else return false;
    }

}
