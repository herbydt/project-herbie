package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCashierBankEnrollment extends PageCashier {

    public PageCashierBankEnrollment(WebDriver driver) {
        super(driver);
    }

    // Bank Enrollment Form Fields
    By ddlEnrollmentPaymentMethod = By.cssSelector("div.form-item.form-type-select.form-item-payment-method > span.select > div.newListSelected > div.selectedTxt");
    By txtDispName = By.id("edit-name");
    By ddlEnrollmentBankName = By.id("edit-bank-name");
    By ddlEnrollmentBankNameTHB = By.id("edit-withdraw-bank-name");
    By txtEnrollmentBankBranch = By.id("edit-branch");
    By txtEnrollmentBankIFSC = By.id("edit-ifsc-code");
    By txtEnrollmentBankAddress = By.id("edit-address");
    By txtEnrollmentBankAcctNumber = By.id("edit-account-number");
    By txtEnrollmentPassword = By.id("edit-password");
    By btnEnrollmentSubmit = By.id("edit-submit");
    By imgLoadingBlock = By.cssSelector("form.dafabet-cashier-form.cashier-form-withdrawal > div > div.loading-block");

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

    public void selectEnrollmentBank(String bankName) throws Exception {
        control.selectDropdownOption(ddlEnrollmentBankName, bankName);
    }

    public void selectEnrollmentBankTHB(String bankName) throws Exception {
        control.selectDropdownOption(ddlEnrollmentBankNameTHB, bankName);
    }

    public void typeEnrollmentBankBranch(String bankBranch, String currency, String bankIfsc) throws Exception {
        control.type(txtEnrollmentBankBranch, bankBranch);
        control.click(txtEnrollmentBankAddress);
        if (currency.equalsIgnoreCase("inr")) {
            control.waitWhileElementIsDisplayed(imgLoadingBlock,10);
            typeEnrollmentBankIFSC(bankIfsc);
        }
    }

    public void typeEnrollmentBankIFSC(String bankIfsc) throws Exception {
        control.type(txtEnrollmentBankIFSC, bankIfsc);
    }

    public void typeEnrollmentBankAddress(String bankAddress) throws Exception {
        control.type(txtEnrollmentBankAddress, bankAddress);
    }

    public void typeEnrollmentBankAcctNumber(String bankAcctNumber) throws Exception {
        control.type(txtEnrollmentBankAcctNumber, bankAcctNumber);
    }

    public void typeEnrollmentPassword(String password) throws Exception {
        control.type(txtEnrollmentPassword, password);
    }

    public void clickEnrollmentSubmit() throws Exception {
        int ctr = 0;
        control.click(btnEnrollmentSubmit);
        waitForPageToComplete();
        String enrollmentStatus = control.getAttributeValue(lblWithdrawBankEnrollmentStatus, "class",10);
        while (!enrollmentStatus.contains("player-verification-form-processed")) {
            enrollmentStatus = control.getAttributeValue(lblWithdrawBankEnrollmentStatus, "class",10);
            Thread.sleep(1000);
            if (ctr > 10) {
                throw new Exception("ERROR: Withdraw page has not loaded fully. Please try again.");
            }
        }
    }

    public void clickEnrollmentSubmitTHB() throws Exception {
        control.click(btnEnrollmentSubmit);
        waitForPageToComplete();
        control.waitWhileElementIsNotDisplayed(lblInnerPageTitleTHB,10);
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean isPaymentMethodCorrect(String paymentMethod) {
        String dispPaymentOption = control.getText(ddlEnrollmentPaymentMethod);
        if (dispPaymentOption.contains(paymentMethod)) {
            System.out.println("\nPASSED: Displayed Payment Option (" + dispPaymentOption + ") is correct.\n");
            return true;
        }
        else return false;
    }

    public boolean isDisplayedNameCorrect(String playerName) {
        String dispName = control.getAttributeValue(txtDispName, "value",10);
        if (dispName.equalsIgnoreCase(playerName)) {
            System.out.println("\nPASSED: Displayed Name (" + dispName + ") is correct.\n");
            return true;
        }
        else return false;
    }




}
