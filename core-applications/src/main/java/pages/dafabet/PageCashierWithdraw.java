package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageCashierWithdraw extends BaseDafabet {

    public PageCashierWithdraw(WebDriver driver) {
        super(driver);
    }

    By ddlPaymentOption = By.cssSelector("div.form-item.form-type-select.form-item-payment-method > span.select > div.newListSelected > div.selectedTxt");
    By ddlWithdrawFromProduct = By.id("edit-product");
    By txtWithdrawAmount = By.id("edit-amount");
    By txtWithdrawPassword = By.id("edit-password");
    By btnWithdrawSubmit = By.id("edit-submit");
//    By btnWithdrawSubmit = By.id("edit-action");
    By msgErrorMessage = By.cssSelector("div.form-item.form-type-select.form-item-product > span.select > span.error.error-message");

    By msgTransactionStatus = By.cssSelector("div[id='cashier-result-container'] > p.blurb.top-blurb");

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

    public void setTextField(By locator, String value) throws Exception {
        if (value != null) {
            control.type(locator, value,10);
            control.findElement(locator).sendKeys(Keys.TAB);
        }
    }

    public void setDropdownFieldBasedOptionAttributeValue(By locator, int value) throws Exception {
        if (value != 0) {
            //fix for value lookup error on blank option
            WebElement element = control.findElement(locator).findElement(By.cssSelector("option[value='" + value + "']"));
            control.click(locator);
            control.click(element);
            control.findElement(locator).sendKeys(Keys.ESCAPE);
        }
    }

    public void selectWithdrawFromProduct (String product) throws Exception {
//        String msgErrorValidation = "";
//        int ctr = 0;
        control.selectDropdownOption(ddlWithdrawFromProduct, product,20);
        control.click(txtWithdrawAmount);
//        msgErrorValidation = control.getAttributeValue(msgErrorMessage, "style",10);
//        while (msgErrorValidation.contains("block")) {
//            control.selectDropdownOption(ddlWithdrawFromProduct, "Select product...",10);
//            control.selectDropdownOption(ddlWithdrawFromProduct, product,20);
//            control.click(txtWithdrawAmount);
//            msgErrorValidation = control.getAttributeValue(msgErrorMessage, "style",10);
//            Thread.sleep(1000);
//            if (ctr > 10) {
//                throw new Exception("ERROR: Error is still loading after 10secs. Please try again.");
//            }
//        }
    }

    public void typeWithdrawAmount (String amount) throws Exception {
        control.type(txtWithdrawAmount, amount,20);
        control.click(txtWithdrawPassword);
    }

    public void typeWithdrawPassword (String password) throws Exception {
        control.type(txtWithdrawPassword, password,20);
    }

    public void clickSubmitWithdraw() throws Exception {
        control.click(btnWithdrawSubmit,20);
        waitForPageToComplete();
        control.waitWhileElementIsNotDisplayed(msgTransactionStatus,10);
    }

    public boolean withdraw(String product, String amount, String password) {
        try {
            control.click(ddlWithdrawFromProduct);
            control.selectDropdownOption(ddlWithdrawFromProduct, product);
            setTextField(txtWithdrawAmount, amount);
            control.scrollToElement(txtWithdrawAmount);
            setTextField(txtWithdrawPassword, password);
            control.scrollToElement(txtWithdrawPassword);
            control.click(btnWithdrawSubmit);
            waitForPageToComplete();
            control.waitWhileElementIsNotDisplayed(msgTransactionStatus,10);
//            waitLoading(loaderSubmit, 30);
            return true;
        } catch(Exception e) {
            return false;
        }
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

    public boolean validateSuccessfulWithdrawTransaction() {
        String dispMessage = control.getText(msgTransactionStatus);
        if ((dispMessage.contains("withdrawal")) && (dispMessage.contains("has been submitted"))) {
            System.out.println("\nPASSED: " + dispMessage + "\n");
            return true;
        } else if ((dispMessage.contains("Please wait")) && (dispMessage.contains("balance will be refreshed"))) {
            System.out.println("\nPASSED: " + dispMessage + "\n");
            return true;
        }
        else return false;
    }
}
