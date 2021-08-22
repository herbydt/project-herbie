package workflow;

import cucumber.api.DataTable;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.dafabet.BaseDafabet;

import java.util.List;

public class WorkflowDafabet extends BaseWorkflow {

    public WorkflowDafabet(WebDriver driver) {
        super(driver);
        baseDafabet = new BaseDafabet(driver);
    }

    public BaseDafabet baseDafabet;

    public static String parentHandle;

    static Logger log = LoggerFactory.getLogger(WorkflowDafabet.class);

    //==================================================================================================================
    // Local Declarations
    //==================================================================================================================
//    private void trackPage(String page) {
//        log.info("Page requested: " + page + "\n");
//    }

    private enum State {
        PRE_LOGIN("Pre-Login"),
        POST_LOGIN("Post-Login");
        private String state;

        State(String state) {
            this.state = state;
        }

        public String value() {
            return this.state;
        }
    }

    private State toState(String state) {
        State[] states = State.values();
        for (State status : states) {
            if (status.value().equalsIgnoreCase(state)) {
                return status;
            }
        }
        return null;
    }

    //==================================================================================================================
    // Common Methods
    //==================================================================================================================

    public void launchApplication(String url) {
        baseDafabet.launchApplication(url);
        waitForPageToComplete();
        parentHandle = driver.getWindowHandle();
    }

    public void waitForPageToComplete() {
        baseDafabet.waitForPageToComplete();
    }

    public void clickButton (String button) throws Exception {
        baseDafabet.PageDafabet().clickButton(button);
    }

    public void closeCurrentWindow() {
        driver.close();
        driver.switchTo().window(parentHandle);
    }

    public void clickMobileButton (String button) throws Exception {
        baseDafabet.PageMobileDafabet().clickButton(button);
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean validatePage(String page, String username) {
        if (page.equalsIgnoreCase("cashier")) {
            return baseDafabet.PageCashier().isUsernameCorrect(username);
        } else if (page.equalsIgnoreCase("registration")) {
            return baseDafabet.PageRegister().isRegPageDisplayed();
        } else if (page.equalsIgnoreCase("myaccount")) {
            return baseDafabet.PageMyAccount().isMyAccPageDisplayed(username);
        } else if (page.equalsIgnoreCase("change password")) {
            return baseDafabet.PageMyAccount().isMyAccChangePasswordDisplayed(username);
        } else if (page.equalsIgnoreCase("forgot password")) {
            return baseDafabet.PageMyAccount().isForgotPasswordPageDisplayed();
        } else if (page.equalsIgnoreCase("forgot username")) {
            return baseDafabet.PageMyAccount().isForgotUsernamePageDisplayed();
        } else
            return false;
    }

    public boolean validateMobilePage(String page, String username, String transaction) {
        if (page.equalsIgnoreCase("cashier")) {
            return baseDafabet.PageCashier().isMobileUsernameCorrect(username);
        } else if (page.equalsIgnoreCase("payment options")) {
            return baseDafabet.PageCashier().isPaymentOptionsPageDisplayed(transaction);
        } else if (page.equalsIgnoreCase("fund transfer")) {
            return baseDafabet.PageCashier().isFundTransferPageDisplayed();
        } else if (page.equalsIgnoreCase("bank enrollment")) {
            return baseDafabet.PageCashier().isBankEnrollmentPageDisplayed();
        } else
            return false;
    }

    //==================================================================================================================
    // Dafabet Login
    //==================================================================================================================

    public void loginPlayer(String username, String password) throws Exception {
        baseDafabet.PageDafabet().typeUsername(username);
        baseDafabet.PageDafabet().typePassword(password);
        baseDafabet.PageDafabet().clickButton("Login");
        waitForPageToComplete();
    }

    public void loginMobilePlayer(String username, String password) throws Exception {
        baseDafabet.PageMobileDafabet().clickButton("Header Login");
        baseDafabet.PageMobileDafabet().typeUsername(username);
        baseDafabet.PageMobileDafabet().typePassword(password);
        baseDafabet.PageMobileDafabet().clickButton("Login");
        waitForPageToComplete();
    }

    public boolean isInPage(String state) throws Exception {
        baseDafabet.closeAnnouncementLightbox();

        State s = toState(state);

        if (s == WorkflowDafabet.State.PRE_LOGIN) {
            System.out.println("\nCURRENT PAGE: Player is in PRE-LOGIN" + "\n");
            return baseDafabet.PageDafabet().isInPreLoginPage();
        } else if (s == WorkflowDafabet.State.POST_LOGIN) {
            System.out.println("\nCURRENT PAGE: Player is in POST-LOGIN" + "\n");
            return baseDafabet.PageDafabet().isInPostLoginPage();
        }
        return false;
    }

    public boolean isInMobilePage(String state) throws Exception {
        baseDafabet.closeAnnouncementLightbox();

        State s = toState(state);

        if (s == WorkflowDafabet.State.PRE_LOGIN) {
            System.out.println("\nCURRENT PAGE: Player is in PRE-LOGIN" + "\n");
            return baseDafabet.PageMobileDafabet().isInMobilePreLoginPage();
        } else if (s == WorkflowDafabet.State.POST_LOGIN) {
            System.out.println("\nCURRENT PAGE: Player is in POST-LOGIN" + "\n");
            return baseDafabet.PageMobileDafabet().isInMobilePostLoginPage();
        }
        return false;
    }

    public void logout() throws Exception {
        int windowCount = driver.getWindowHandles().size();
        if (windowCount > 1) {
            baseDafabet.switchToWindow(0);
        }
        baseDafabet.refreshPage();
        waitForPageToComplete();
        baseDafabet.PageDafabet().logout();
        waitForPageToComplete();
    }

    //==================================================================================================================
    // Dafabet Cashier
    //==================================================================================================================

    public void depositOtherPaymentMethod (String product, String bank, String depositAmount) throws Exception {
        System.out.println("Product:" + product + "\n");
        System.out.println("Bank:" + bank + "\n");
        System.out.println("Deposit Amount:" + depositAmount + "\n");
        baseDafabet.PageCashierDeposit().selectDepositProduct(product);
        baseDafabet.PageCashierDeposit().selectDepositBank(bank);
        baseDafabet.PageCashierDeposit().typeDepositAmount(depositAmount);
        baseDafabet.PageCashierDeposit().clickDepositSubmit();
    }

    public void fundTransfer (String productFrom, String productTo, String transferAmount) throws Exception {
        System.out.println("From Product:" + productFrom + "\n");
        System.out.println("To Product:" + productTo + "\n");
        System.out.println("Transfer Amount:" + transferAmount + "\n");
        baseDafabet.PageCashierFundTransfer().selectTransferFromProduct(productFrom);
        baseDafabet.PageCashierFundTransfer().selectTransferToProduct(productTo);
        baseDafabet.PageCashierFundTransfer().typeTransferAmount(transferAmount);
        baseDafabet.PageCashierFundTransfer().clickSubmitTransfer();
    }

    public void validateSuccessfulTransactionMessage () throws Exception {
        Assert.assertTrue(baseDafabet.PageCashierFundTransfer().validateSuccessfulFundTransferTransaction());
    }

    public void enrollWithdrawBankAccount(String paymentMethod, String dispName, String bankName, String branchName, String branchAddress, String acctNumber) throws Exception {
        Assert.assertTrue(baseDafabet.PageCashierHistory().isTransactionAmountCorrect(paymentMethod));
    }

    public void openLatestTransactionDetails() throws Exception {
        baseDafabet.PageCashierHistory().clickLatestTrxInHistory();
    }

    public void validateTransactionDetailsInHistory (String date, String amount, String from, String to) throws Exception {
        baseDafabet.PageCashierHistory().getTransactionDetails();
//        Assert.assertTrue(baseDafabet.PageCashierDeposit().isTransactionDateCorrect(date));
        Assert.assertTrue(baseDafabet.PageCashierHistory().isTransactionAmountCorrect(amount));
        Assert.assertTrue(baseDafabet.PageCashierHistory().isTransactionFromCorrect(from));
        Assert.assertTrue(baseDafabet.PageCashierHistory().isTransactionToCorrect(to));
        Assert.assertTrue(baseDafabet.PageCashierHistory().validateSuccessfulTransaction());
    }


    //==================================================================================================================
    // Dafabet MyAccount
    //==================================================================================================================

    public boolean validateCorrectMyAccountInfo(String userDetail) throws Exception {
        String actual = "";
        String expected = "";

        switch (userDetail.toUpperCase()) {
            case "USERNAME":
                actual = baseDafabet.PageMyAccount().getMyAccInfo(userDetail).toUpperCase();
                expected = baseDafabet.RegUsername.toUpperCase();
                break;
            case "CURRENCY":
                actual = baseDafabet.PageMyAccount().getMyAccInfo(userDetail);
                String curExp = baseDafabet.RegCurrency;
                if (curExp.contains("RMB")) {
                    curExp = "人民币";
                }
                expected = curExp;
                break;
            case "FIRST NAME":
                actual = baseDafabet.PageMyAccount().getMyAccInfo(userDetail);
                expected = baseDafabet.RegFirstName;
                break;
            case "LAST NAME":
                actual = baseDafabet.PageMyAccount().getMyAccInfo(userDetail);
                expected = baseDafabet.RegLastName;
                break;
            case "DATE OF BIRTH":
                actual = baseDafabet.PageMyAccount().getMyAccInfo(userDetail);
                expected = baseDafabet.RegDateOfBirth;
                break;
            case "COUNTRY":
                actual = baseDafabet.PageMyAccount().getMyAccInfo(userDetail);
                expected = baseDafabet.RegCountry;
                break;
            case "ADDRESS":
                actual = baseDafabet.PageMyAccount().getMyAccInfo(userDetail);
                expected = baseDafabet.RegAddress;
                break;
            case "CITY":
                actual = baseDafabet.PageMyAccount().getMyAccInfo(userDetail);
                expected = baseDafabet.RegCity;
                break;
            case "POSTAL CODE":
                actual = baseDafabet.PageMyAccount().getMyAccInfo(userDetail);
                expected = baseDafabet.RegPostalCode;
                break;
            default:
                throw new Exception("\nERROR: No mapping available for the field name '" + userDetail + "' \n");
        }

        if (actual.equals(expected)) {
            System.out.println("\nPASSED: '" + actual + "' is displayed in My Account Page. \n");
            return true;
        }
        else {
            System.out.println("\nFAILED: ACTUAL '" + actual + "' is displayed | EXPECTED '" + expected + "' is displayed.");
            return false;
        }
    }

    public void updateMyAccount(String address, String city, String postalCode) throws Exception {
        baseDafabet.PageMyAccount().typeMyAccAddress(address);
        baseDafabet.PageMyAccount().typeMyAccCity(city);
        baseDafabet.PageMyAccount().typeMyAccPostalCode(postalCode);
    }

    public void saveMyAccount() throws Exception {
        baseDafabet.PageMyAccount().clickMyAccButton("Save Changes");
        baseDafabet.PageMyAccount().typeMyAccPassword(baseDafabet.RegPassword);
        baseDafabet.PageMyAccount().clickMyAccButton("Confirm Changes");
    }

    public boolean validateSuccessMyAccountUpdate() {
        return baseDafabet.PageMyAccount().isMyAccSavedSuccessfully();
    }

    public void changePassword(String newPassword, String confNewPassword) throws Exception {
        baseDafabet.PageMyAccount().typeMyAccOldPassword(baseDafabet.RegPassword);
        baseDafabet.PageMyAccount().typeMyAccNewPassword(newPassword);
        baseDafabet.PageMyAccount().typeMyAccConfNewPassword(confNewPassword);
    }

    public void saveChangePassword() throws Exception {
        baseDafabet.PageMyAccount().clickMyAccButton("Change Password");
    }

    public boolean validateSuccessChangePassword() {
        return baseDafabet.PageMyAccount().isMyAccChangePasswordSuccessful();
    }

    public void inputCantLoginValues(String item, String username, String email) throws Exception {
        if(item.equalsIgnoreCase("username")) {
            System.out.print("\nUsername: " + username + "\n");
            baseDafabet.PageMyAccount().typeForgotUsernameEmail(email);
        }
        if(item.equalsIgnoreCase("password")) {
            System.out.print("\nUsername: " + username);
            System.out.print("\nEmail: " + email + "\n");
            baseDafabet.PageMyAccount().typeForgotPasswordUsername(username);
            baseDafabet.PageMyAccount().typeForgotPasswordEmail(email);
        }
    }

    public void submitCantLoginForm(String item) throws Exception {
        if(item.equalsIgnoreCase("username")) {
            baseDafabet.PageMyAccount().clickSubmitForgotUsername();
        }
        if(item.equalsIgnoreCase("password")) {
            baseDafabet.PageMyAccount().clickSubmitForgotPassword();
        }
    }

    public void submitResetPasswordForm(String password, String confPassword) throws Exception {
        System.out.print("\nNew Password: " + password);
        baseDafabet.PageMyAccount().typeResetPasswordNewPassword(password);
        baseDafabet.PageMyAccount().typeResetPasswordConfNewPassword(confPassword);
        baseDafabet.PageMyAccount().submitResetPassword();
    }

    public boolean validateCorrectCantLoginTab(String item) {
        return baseDafabet.PageMyAccount().isCorrectCantLoginTabOpened(item);
    }

    public boolean validateSuccessCantLoginMessage(String item) {
        return baseDafabet.PageMyAccount().isSuccessfulCantLoginMessageDisplayed(item);
    }

    public void searchYopmailEmail(String email) throws Exception {
        baseDafabet.PageMyAccount().typeYopmailEmail(email);
        baseDafabet.PageMyAccount().clickYopmailEmailSearch();
    }

    public boolean validateCorrectUsernameInYopmail(String username) {
        return baseDafabet.PageMyAccount().isCorrectUsernameDisplayedInYopmail(username);
    }

    public boolean validatePasswordResetEmail(String username) {
        return baseDafabet.PageMyAccount().isCorrectUsernameDisplayedInYopmailForgotPW(username);
    }

    public boolean validatePasswordResetContent() {
        return baseDafabet.PageMyAccount().isResetPasswordBtnDisplayedForgotPW();
    }

    public boolean validateSuccessResetPasswordMessage() {
        return baseDafabet.PageMyAccount().isSuccessfulResetPasswordMessageDisplayed();
    }
}
