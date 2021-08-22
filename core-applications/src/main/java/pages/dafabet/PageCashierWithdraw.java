package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCashierWithdraw extends BaseDafabet {

    public PageCashierWithdraw(WebDriver driver) {
        super(driver);
    }

    By ddlPaymentOption = By.cssSelector("div.form-item.form-type-select.form-item-payment-method > span.select > div.newListSelected > div.selectedTxt");
    By ddlWithdrawFromProduct = By.id("edit-product");
    By txtWithdrawAmount = By.id("edit-amount");
    By txtWithdrawPassword = By.id("edit-password");
    By btnWithdrawSubmit = By.id("edit-submit");
    By msgErrorMessage = By.cssSelector("div.form-item.form-type-select.form-item-product > span.select > span.error.error-message");

    By msgTransactionStatus = By.cssSelector("div[id='cashier-result-container'] > p.blurb.top-blurb");

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

    public void selectWithdrawFromProduct (String product) throws Exception {
        String msgErrorValidation = "";
        int ctr = 0;

        control.selectDropdownOption(ddlWithdrawFromProduct, product,10);
        control.click(txtWithdrawAmount);
        msgErrorValidation = control.getAttributeValue(msgErrorMessage, "style",10);

        while (msgErrorValidation.contains("block")) {
            control.selectDropdownOption(ddlWithdrawFromProduct, "Select product...",10);
            control.selectDropdownOption(ddlWithdrawFromProduct, product,10);
            control.click(txtWithdrawAmount);
            msgErrorValidation = control.getAttributeValue(msgErrorMessage, "style",10);
            Thread.sleep(1000);
            if (ctr > 10) {
                throw new Exception("ERROR: Error is still loading after 10secs. Please try again.");
            }
        }
    }

    public void typeWithdrawAmount (String amount) throws Exception {
        control.type(txtWithdrawAmount, amount,10);
    }

    public void typeWithdrawPassword (String password) throws Exception {
        control.type(txtWithdrawPassword, password,10);
    }

    public void clickSubmitWithdraw() throws Exception {
        control.click(btnWithdrawSubmit,10);
        waitForPageToComplete();
        control.waitWhileElementIsNotDisplayed(msgTransactionStatus,10);
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean isPaymentMethodCorrect(String paymentMethod) throws Exception {
        String dispPaymentOption = control.getText(ddlPaymentOption,10);
        if(dispPaymentOption.contains(paymentMethod)) {
            System.out.print("PASSED: Displayed Payment Method (" + dispPaymentOption + ") is correct.");
            return true;
        } else return false;
    }

    public boolean validateUnsuccessfulWithdrawTransaction(String reason, String wageringReq) throws Exception {
        String dispMessage = control.getText(msgTransactionStatus);
        if ((dispMessage.contains(reason)) && (dispMessage.contains(wageringReq))) {
            System.out.println("\nPASSED: " + dispMessage + "\n");
            return true;
        }
        else return false;
    }
}
