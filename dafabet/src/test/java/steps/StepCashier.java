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
//        NewlyRegisteredPlayer = "qaprodrmbht960";
//        workflowDafabet.baseDafabet.RegUsername = "qaprodrmbht960";
//        workflowDafabet.baseDafabet.RegEmail = "qaprodrmbht960@yopmail.com";
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
        softAssert.assertTrue(workflowDafabet.validateMobilePage(page, baseUsername, baseTransaction), "FAILED: ");
        if (page.equalsIgnoreCase("cashier")) {
            baseHeaderOldBalance = Float.parseFloat(workflowDafabet.baseDafabet.PageCashier().getTotalBalanceInMobileHeader().replace(",", ""));
        }
    }

    @When("^the mobile player goes back to Mobile Cashier Dashboard$")
    public void theMobilePlayerGoesBackToMobileCashierDashboard() throws Throwable {
        workflowDafabet.clickMobileButton("dafabet logo");
    }

    @When("^the mobile player performs deposit using ([^\"]*)$")
    public void thePlayerPerformsDepositUsingLocalBankTransfer(String paymentMethod, DataTable details) throws Throwable {
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

    @Then("^the mobile (Deposit|Withdraw|Fund Transfer) transaction is successful$")
    public void theMobileDepositTransactionIsSuccessful(String transaction) throws Throwable {
        String language = "EN";
        String trxFrom = "";
        String trxTo = "";
        workflowDafabet.launchApplication(getSiteUrl("MDafabetRMB")+ "/" + language.toLowerCase() + "/");
        workflowDafabet.baseDafabet.waitForPageToComplete();
        workflowDafabet.baseDafabet.closeAnnouncementLightbox();
        workflowDafabet.clickMobileButton("cashier");
        workflowDafabet.clickMobileButton("history");
        workflowDafabet.openLatestTransactionDetails();

        if (transaction.equalsIgnoreCase("deposit")) {
            trxFrom = basePaymentMethod;
            trxTo = baseDepositToProduct;
        } else if (transaction.equalsIgnoreCase("withdraw")) {
            trxFrom = baseDepositToProduct;
            trxTo = "Adjustment";
        } else if (transaction.equalsIgnoreCase("fund transfer")) {
            trxFrom = baseDepositToProduct;
            trxTo = baseDepositToProduct;
        }

        workflowDafabet.validateTransactionDetailsInHistory(transaction, baseDepositAmount, trxFrom, trxTo);

//        softAssert.assertTrue(workflowDafabet.validatePage(page, baseUsername), "FAILED: The correct username is not reflected in the Cashier Page.");
    }

}
