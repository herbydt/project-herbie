package steps;

import libraries.PageObjectBase;
import libraries.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import workflow.WorkflowDafabet;

import java.io.IOException;
import java.util.List;

public class BaseStep extends TestBase{

    public static WorkflowDafabet workflowDafabet;

    public static SoftAssert softAssert;

    // Variables
    public static String baseCurrentPage;
    public static String baseUsername;
    public static String basePassword;
    public static String baseNewPassword;
    public static String baseLanguage;
    public static String CurrentState = "Pre-Login";
    public static String NewlyRegisteredPlayer;

    // Cashier Variables
    public static String baseTransaction;
    public static String baseCashierPlayerStatus;
//    public static String baseTransactionType;
    public static List<String> baseAllProductsOldBalance;
    public static List<String> baseAllProductNewBalance;
    public static List<String> baseSubAccounts;
    public static String baseOldCashierRealMoneyBalance;
    public static String baseNewCashierRealMoneyBalance;
    public static String baseOldCashierBonusBalance;
    public static String baseNewCashierBonusBalance;
    public static String baseDepositStatus;
    public static String baseWithdrawStatus;
    public static String baseTransfertype;
    public static String baseDepositor;
    public static String baseDepositorAddress;
    public static String baseBankAccountSelection;
    public static String baseBankNameBankingDetails;
    public static String baseBankaccountHolderNameBankingDetails;
    public static String baseBankAccountNumberBankingDetails;
    public static float baseHeaderOldBalance = 0;
    public static float baseHeaderNewBalance = 0;
    public static String basePaymentMethod;
    public static String baseDepositToProduct;
    public static String baseDepositBank;
    public static String baseDepositAmount;
    public static List<String> baseProductOldBalance;
    public static List<String> baseProductNewBalance;
    public static String baseTransactionDateAndTime;
    public static String baseTaurusDateAndTime;
    public static String baseWithdrawFromProduct;
    public static String baseWithdrawAmount;
    public static String baseCashierTransactionAmount;
    public static String baseOldTransferFromProductBalance;
    public static String baseOldTransferToProductBalance;
    public static String baseNewTransferFromProductBalance;
    public static String baseNewTransferToProductBalance;
    public static String baseTransferFromProduct;
    public static String baseTransferToProduct;
    public static String baseTransferAmount;
    public static String baseTransferStatus;

    // Cashier - Withdraw - Bank Enrollment
    public static String baseEnrolledBankName;
    public static String baseEnrolledBankBranchName;
    public static String baseEnrolledBankAddress;
    public static String baseEnrolledBankAccountNumber;
    public static String baseEnrolledBankIFSC;

    // Cashier - Dashboard
    public static String baseBalance;
    public static String baseBonus;
    public static String baseWageringReq;


    //==================================================================================================================
    // Test Utility Methods
    //==================================================================================================================
    public boolean isRealDevice() throws IOException {
        if (getDevice().equalsIgnoreCase("android") || getDevice().equalsIgnoreCase("ios")) {
            System.out.println("DEBUG: Device selected is - " + getDevice() + ". \n");
            return true;
        }
        System.out.println("DEBUG: NOT a real device. Selected is - " + getDevice() + ". \n");
        return false;
    }

    public String getJSErrosLog() {
        // Capture all JSerrors and print in console.
        LogEntries jserrors = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry error : jserrors) {
            System.out.println("FAILED: Javascript ERROR! " + error.getMessage());
            return "Browser Console Error: \n" + error.getMessage();
        }
        return "NO console error. \n";
    }

    public String getSiteUrl(String site) throws IOException {
        baseCurrentPage = site.toUpperCase();
        return getApplicationProperties(site + "." + getEnvironment() + ".url");
    }

}

