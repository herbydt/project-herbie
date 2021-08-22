package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;


public class StepCashier extends BaseStep {

    BaseStep base;

    public StepCashier(BaseStep base) {
        this.base = base;
    }

    @When("^the player clicks the ([^\"]*) button$")
    public void thePlayerIsAtSite(String button) throws Throwable {
        workflowDafabet.clickButton(button);
    }

    @Then("^the desktop ([^\"]*) page is loaded successfully$")
    public void thePageIsLoadedSuccessfully(String page) throws Throwable {
        softAssert.assertTrue(workflowDafabet.validatePage(page, baseUsername), "FAILED: The correct username is not reflected in the Cashier Page.");
    }

    @When("^the player logs out in Dafabet page$")
    public void thePlayerLogsOutInDafabetPage() throws Throwable {
        workflowDafabet.logout();
        CurrentState = "Pre-Login";
    }

    /////////////////////
    //  MOBILE STEPS   //
    /////////////////////

    @When("^the mobile player clicks the ([^\"]*) button$")
    public void theMobilePlayerIsAtSite(String button) throws Throwable {
        workflowDafabet.clickMobileButton(button);
        baseTransaction = button;
    }

    @Then("^the mobile ([^\"]*) page is loaded successfully$")
    public void theMobilePageIsLoadedSuccessfully(String page) throws Throwable {
        softAssert.assertTrue(workflowDafabet.validateMobilePage(page, baseUsername, baseTransaction), "FAILED: Expected Page didn't load correctly.");
        if (page.equalsIgnoreCase("cashier")) {
            baseHeaderOldBalance = Float.parseFloat(workflowDafabet.baseDafabet.PageCashier().getTotalBalanceInMobileHeader().replace(",", ""));
        }
    }

    @When("^the mobile player goes back to Mobile Cashier Dashboard$")
    public void theMobilePlayerGoesBackToMobileCashierDashboard() throws Throwable {
        workflowDafabet.clickMobileButton("dafabet logo");
    }

    @When("^the player checks the ([^\"]*) balances$")
    public void thePlayerChecksTheCasinoBalances(String product) throws Throwable {
        baseBalance = workflowDafabet.getProductBalance(product);
        baseBonus = workflowDafabet.getProductBonus(product);
        baseWageringReq = workflowDafabet.getProductWageringRequirements(product);
        System.out.print("\n" + product + " Balance: " + baseBalance + "\n");
        System.out.print(product + " Bonus: " + baseBonus + "\n");
        System.out.print(product + " Wagering Requirement: " + baseWageringReq + "\n");
    }

    @When("^the mobile player performs deposit using ([^\"]*)$")
    public void thePlayerPerformsDepositUsingPaymentMethod(String paymentMethod, DataTable details) throws Throwable {
        //Data Table
        List<List<String>> PMDetails = details.raw();
        basePaymentMethod = paymentMethod;
        baseDepositToProduct = PMDetails.get(0).get(1);
        baseDepositBank = PMDetails.get(1).get(1);
        baseDepositAmount = PMDetails.get(2).get(1);
        baseTransfertype = PMDetails.get(3).get(1);
        baseDepositor = PMDetails.get(4).get(1);
        baseDepositorAddress = PMDetails.get(5).get(1);

        workflowDafabet.baseDafabet.PageCashierDeposit().selectPaymentMethod(paymentMethod);

        if (paymentMethod.equalsIgnoreCase("local bank transfer")) {
            workflowDafabet.baseDafabet.PageCashierDeposit().depositLocalBankTransferBasic(baseDepositToProduct, baseDepositBank, baseTransfertype, baseDepositAmount, "00", "00", baseDepositor, baseDepositorAddress);
        } else {
            workflowDafabet.depositOtherPaymentMethod(baseDepositToProduct, baseDepositBank, baseDepositAmount);
        }
        baseDepositStatus = "Successful";
    }

    @When("^the mobile player performs fund transfer$")
    public void thePlayerPerformsFundTransfer(DataTable details) throws Throwable {
        //Data Table
        List<List<String>> FTDetails = details.raw();
        baseTransferFromProduct = FTDetails.get(0).get(1);
        baseTransferToProduct = FTDetails.get(1).get(1);
        baseTransferAmount = FTDetails.get(2).get(1);

        workflowDafabet.fundTransfer(baseTransferFromProduct, baseTransferToProduct, baseTransferAmount);
        baseDepositStatus = "Successful";
    }

    @When("^the mobile player performs withdraw using ([^\"]*)$")
    public void thePlayerPerformsWithdrawUsingLocalBankTransfer(String paymentMethod, DataTable details) throws Throwable {
        //Data Table
        List<List<String>> PMDetails = details.raw();
        basePaymentMethod = paymentMethod;
        baseWithdrawFromProduct = PMDetails.get(0).get(1);
        baseWithdrawAmount = PMDetails.get(1).get(1);
        workflowDafabet.baseDafabet.PageCashierDeposit().selectPaymentMethod(paymentMethod);

        softAssert.assertTrue(workflowDafabet.validateMobilePage("Withdraw", baseUsername, baseTransaction), "FAILED: Expected Page didn't load correctly.");
        softAssert.assertTrue(workflowDafabet.validateWithdrawPaymentOption(paymentMethod), "FAILED: Expected Payment Method is not displayed.");

        workflowDafabet.withdraw(baseWithdrawFromProduct, baseWithdrawAmount, basePassword);
    }

    @When("^the mobile player enrolls withdraw bank account$")
    public void thePlayerEnrollsWithdrawBannkAccount(DataTable details) throws Throwable {
        //Data Table
        List<List<String>> PMDetails = details.raw();
        basePaymentMethod = "Local Bank Transfer";
        String dispName = "";
        baseEnrolledBankName = PMDetails.get(0).get(1);
        baseEnrolledBankBranchName = PMDetails.get(1).get(1);
        baseEnrolledBankAddress = PMDetails.get(2).get(1);
        baseEnrolledBankAccountNumber = PMDetails.get(3).get(1);
        baseEnrolledBankIFSC = PMDetails.get(4).get(1);

        workflowDafabet.baseDafabet.PageCashierDeposit().selectPaymentMethod(basePaymentMethod);

        if ((baseCashierPlayerStatus.equalsIgnoreCase("new"))&&(workflowDafabet.baseDafabet.RegCurrency.equalsIgnoreCase("RMB/CNY"))) {
            dispName = workflowDafabet.baseDafabet.RegLastName+workflowDafabet.baseDafabet.RegFirstName;
            softAssert.assertTrue(workflowDafabet.validateMobilePage("Bank Enrollment", baseUsername, baseTransaction), "FAILED: Expected Page didn't load correctly.");
        } else if ((baseCashierPlayerStatus.equalsIgnoreCase("new"))&&(!workflowDafabet.baseDafabet.RegCurrency.equalsIgnoreCase("RMB/CNY"))) {
            dispName = workflowDafabet.baseDafabet.RegFirstName + " " + workflowDafabet.baseDafabet.RegLastName;
            softAssert.assertTrue(workflowDafabet.validateMobilePage("Bank Enrollment", baseUsername, baseTransaction), "FAILED: Expected Page didn't load correctly.");
        }

        workflowDafabet.enrollWithdrawBankAccount(basePaymentMethod, dispName, baseEnrolledBankName, baseEnrolledBankBranchName,
                workflowDafabet.baseDafabet.RegCurrency, baseEnrolledBankIFSC, baseEnrolledBankAddress, baseEnrolledBankAccountNumber, basePassword);
    }

    @Then("^the mobile (Deposit|Withdraw|Fund Transfer) transaction is successful$")
    public void theMobileDepositTransactionIsSuccessful(String transaction) throws Throwable {
        String language = "EN";
        String trxFrom = "";
        String trxTo = "";
        String trxAmount = "";

        if (transaction.equalsIgnoreCase("deposit")) {
            trxFrom = basePaymentMethod;
            trxTo = baseDepositToProduct;
            trxAmount = baseDepositAmount;
            workflowDafabet.launchApplication(getSiteUrl("MDafabet")+ "/" + language.toLowerCase() + "/");
            workflowDafabet.baseDafabet.waitForPageToComplete();
            workflowDafabet.baseDafabet.closeAnnouncementLightbox();
            workflowDafabet.clickMobileButton("cashier");
        } else if (transaction.equalsIgnoreCase("withdraw")) {
            trxFrom = baseDepositToProduct;
            trxTo = "Adjustment";
        } else if (transaction.equalsIgnoreCase("fund transfer")) {
            workflowDafabet.validateSuccessfulTransactionMessage();
            trxFrom = baseTransferFromProduct;
            trxTo = baseTransferToProduct;
            trxAmount = baseTransferAmount;
            workflowDafabet.clickMobileButton("dafabet logo");
        }

        workflowDafabet.clickMobileButton("history");
        workflowDafabet.openLatestTransactionDetails();
        workflowDafabet.validateTransactionDetailsInHistory(transaction, trxAmount, trxFrom, trxTo);
    }

    @When("^the player goes back to the Mobile Cashier dashboard$")
    public void thePlayerGoesBackToTheMobileCashierDashboard() throws Throwable {
        workflowDafabet.clickMobileButton("dafabet logo");
    }

    @Then("^the mobile ([^\"]*) transaction is not successful due to ([^\"]*)$")
    public void theTransactionIsNotSuccessful(String transaction, String reason) throws Throwable {
        workflowDafabet.validateUnsuccessfulTransactionMessage(transaction, reason.toLowerCase(), baseWageringReq);
    }

}
