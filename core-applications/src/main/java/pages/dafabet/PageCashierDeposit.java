package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCashierDeposit extends BaseDafabet {

    public PageCashierDeposit(WebDriver driver) {
        super(driver);
    }


    By imgFormLoader = By.className("form-loader-gif");
    By imgLBTPaymentMethod = By.cssSelector("a[href$='bank'] > div.group-payment-option-logo > img");
    By imgEasyPayPaymentMethod = By.cssSelector("a[href$='easypay168'] > div.group-payment-option-logo > img");
    By imgTWUPIPaymentMethod = By.cssSelector("a[href$='twupi'] > div.group-payment-option-logo > img");
    By imgAkontoPayPaymentMethod = By.cssSelector("a[href$='akontopaynetbanking'] > div.group-payment-option-logo > img");
    By imgCashierLoader = By.cssSelector("div.loading-block");

    // Deposit Form  Fields
    By lblSelectedPaymentOption = By.cssSelector("div.form-item-payment-method div.selectedTxt");
    By ddlDepositProduct = By.id("edit-product");
    By ddlDepositBank = By.id("edit-bank");
    By txtDepositAmount = By.id("edit-amount");
    By btnDepositSubmit = By.id("edit-submit");

    // Transaction History Details
    By lblLatestTrx = By.xpath("/html/body/div[1]/main/div/div/div[1]/div[3]/div[4]/div[1]/ul/li[1]");
    By sectionLatestTrxPopup = By.cssSelector("div.details-popup.active");
    By txtTransactionDate = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[1]/td[2]");
    By txtTransactionAmount = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]");
    By txtTransactionFrom = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[3]/td[2]");
    By txtTransactionTo = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[4]/td[2]");
    By txtTransactionStatus = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[5]/td[2]");

    public void  selectPaymentMethod(String paymentMethod) throws Exception{
        switch (paymentMethod.toUpperCase()) {
            case "LOCAL BANK TRANSFER" : {
                control.waitWhileElementIsNotDisplayed(imgLBTPaymentMethod, 30);
                control.focusElement(imgLBTPaymentMethod, 3);
                control.click(imgLBTPaymentMethod);
                control.waitForPageComplete();
                break;
            }
            case "EASYPAY" : {
                control.waitWhileElementIsNotDisplayed(imgEasyPayPaymentMethod, 30);
                control.focusElement(imgEasyPayPaymentMethod, 3);
                control.click(imgEasyPayPaymentMethod);
                control.waitForPageComplete();
                break;
            }
            case "TWUPI" : {
                control.waitWhileElementIsNotDisplayed(imgTWUPIPaymentMethod, 30);
                control.focusElement(imgTWUPIPaymentMethod, 3);
                control.click(imgTWUPIPaymentMethod);
                control.waitForPageComplete();
                break;
            }
            case "AKONTOPAY" : {
                control.waitWhileElementIsNotDisplayed(imgAkontoPayPaymentMethod, 30);
                control.focusElement(imgAkontoPayPaymentMethod, 3);
                control.click(imgAkontoPayPaymentMethod);
                control.waitForPageComplete();
                break;
            }
        }
    }

    public void depositLocalBankTransferBasic(String product, String bank, String transferType, String depositAmount, String hour, String minute, String depositorName, String depositorAddress) throws Exception {
        control.waitForPageComplete();
        System.out.println("Product:" + product + "\n");
        System.out.println("Bank:" + bank + "\n");
        System.out.println("Transfer Type:" + transferType + "\n");
        System.out.println("Deposit Amount:" + depositAmount + "\n");
        System.out.println("Hour:" + hour + "\n");
        System.out.println("Minute:" + minute + "\n");
        System.out.println("Depositor's Name:" + depositorName + "\n");
        System.out.println("Depositor's Address:" + depositorAddress + "\n");

        switch (product.toUpperCase()) {
            case "COMMON WALLET":
                product = "Common Wallet (Live Dealer, Games, Arcade, Virtuals, & Lottery)";
                break;
        }
    }

    public void selectDepositProduct(String product) throws Exception {
        control.selectDropdownOption(ddlDepositProduct, product);
    }

    public void selectDepositBank(String bank) throws Exception {
        control.selectDropdownOption(ddlDepositBank, bank);
    }

    public void typeDepositAmount(String amount) throws Exception {
        control.type(txtDepositAmount, amount);
    }

    public void clickDepositSubmit() throws Exception {
        control.click(btnDepositSubmit);
        control.waitWhileElementIsDisplayed(imgCashierLoader, 10, "Loader is not displayed.");
        waitForPageToComplete();
    }

    public void clickLatestTrxInHistory() throws Exception {
        control.click(lblLatestTrx);
        control.waitWhileElementIsNotDisplayed(sectionLatestTrxPopup, 10, "Transaction Details Popup is not displayed.");
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean isTransactionDateCorrect(String trxDate) {
        String dispTrxDate = control.getText(txtTransactionDate);
        if (dispTrxDate.equalsIgnoreCase(trxDate)) {
            System.out.println("\nPASSED: Displayed Transaction Date (" + dispTrxDate + ") is correct.\n");
            return true;
        }
        else return false;
    }

    public boolean isTransactionAmountCorrect(String trxAmount) {
        String editAmount;
        if (trxAmount.contains(".")) {
            editAmount = trxAmount;
        } else {
            editAmount = trxAmount + ".00";
        }

        String dispAmount = control.getText(txtTransactionAmount);
        if (dispAmount.equalsIgnoreCase(editAmount)) {
            System.out.println("\nPASSED: Displayed Transaction Amount (" + dispAmount + ") is correct.\n");
            return true;
        }
        else return false;
    }

    public boolean isTransactionFromCorrect(String trxFrom) {
        String dispFrom = control.getText(txtTransactionFrom);
        if (dispFrom.contains(trxFrom)) {
            System.out.println("\nPASSED: Displayed Transaction From (" + dispFrom + ") is correct.\n");
            return true;
        }
        else return false;
    }

    public boolean isTransactionToCorrect(String trxTo) {
        String dispTo = control.getText(txtTransactionTo);
        if (dispTo.contains(trxTo)) {
            System.out.println("\nPASSED: Displayed Transaction To (" + dispTo + ") is correct.\n");
            return true;
        }
        else return false;
    }

    public boolean validateSuccessfulTransaction() {
        String dispStatus = control.getText(txtTransactionStatus);
        if (!dispStatus.equalsIgnoreCase("Failed")) {
            System.out.println("\nPASSED: Displayed Transaction Status (" + dispStatus + ") is correct.\n");
            return true;
        }
        else return false;
    }

    public void getTransactionDetails() {
        System.out.println("Transaction Date: " + control.getText(txtTransactionDate));
        System.out.println("Transaction Amount: " + control.getText(txtTransactionAmount));
        System.out.println("Transaction From: " + control.getText(txtTransactionFrom));
        System.out.println("Transaction To: " + control.getText(txtTransactionTo));
        System.out.println("Transaction Status: " + control.getText(txtTransactionStatus) + "\n");
    }

}
