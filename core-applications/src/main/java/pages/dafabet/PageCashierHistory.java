package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCashierHistory extends BaseDafabet {

    public PageCashierHistory(WebDriver driver) {
        super(driver);
    }

    // Transaction History Details
    By lblLatestTrx = By.xpath("/html/body/div[1]/main/div/div/div[1]/div[3]/div[4]/div[1]/ul/li[1]");
    By sectionLatestTrxPopup = By.cssSelector("div.details-popup.active");
    By txtTransactionDate = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[1]/td[2]");
    By txtTransactionAmount = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]");
    By txtTransactionFrom = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[3]/td[2]");
    By txtTransactionTo = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[4]/td[2]");
    By txtTransactionStatus = By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/table/tbody/tr[5]/td[2]");

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

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