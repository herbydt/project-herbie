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


    //==================================================================================================================
    // Payment Method Selection
    //==================================================================================================================

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

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

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

}
