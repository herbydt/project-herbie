package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.DateTimeUtil;

import java.util.List;

public class StepMyAccount extends BaseStep {

    BaseStep base;

    public StepMyAccount(BaseStep base) {
        this.base = base;
    }

    @Then("^the correct info are displayed$")
    public void theCorrectInfoAreDisplayed(DataTable userDetails) throws Throwable {
        List<String> details = userDetails.asList(String.class);
        for (String detail : details) {
            System.out.println("Detail to check: " + detail + "\n");
            softAssert.assertTrue(workflowDafabet.validateCorrectMyAccountInfo(detail), "FAILED: '" + detail + "' value is not correct in MyAccount Page. \n");
        }
    }

    @When("^the player enters valid values in MyAccount Form$")
    public void thePlayerEntersValidValues(DataTable userDetails) throws Throwable {
        List<List<String>> data = userDetails.raw();
        String address = data.get(0).get(1);
        String city = data.get(1).get(1);
        String postalCode = data.get(2).get(1);
        workflowDafabet.baseDafabet.RegAddress = data.get(0).get(1);
        workflowDafabet.baseDafabet.RegCity = data.get(1).get(1);
        workflowDafabet.baseDafabet.RegPostalCode = data.get(2).get(1);
        workflowDafabet.updateMyAccount(address, city, postalCode);
    }

    @When("^the player saves the MyAccount Form$")
    public void thePlayerSavesTheMyAccountForm() throws Throwable {
        workflowDafabet.saveMyAccount();
    }

    @Then("^the player updates the MyAccount Form successfully$")
    public void thePlayerUpdatesTheMyAccountFormSuccessfully() throws Throwable {
        softAssert.assertTrue(workflowDafabet.validateSuccessMyAccountUpdate(), "FAILED: MyAccount Form is not saved successfully.");
    }

    @When("^the player enters valid values in Change Password Form$")
    public void thePlayerEntersValidValuesInChangePasswordForm(DataTable userDetails) throws Throwable {
        List<List<String>> data = userDetails.raw();
        String newPassword = data.get(0).get(1);
        baseNewPassword = newPassword;
        workflowDafabet.baseDafabet.MyAccNewPassword = data.get(0).get(1);
        workflowDafabet.baseDafabet.MyAccConfNewPassword = newPassword;
        workflowDafabet.changePassword(newPassword, newPassword);
    }

    @When("^the player saves the Change Password Form$")
    public void thePlayerSavesTheChangePasswordForm() throws Throwable {
        workflowDafabet.saveChangePassword();
    }

    @Then("^the player changes the password successfully$")
    public void thePlayerChangesThePasswordSuccessfully() throws Throwable {
        softAssert.assertTrue(workflowDafabet.validateSuccessChangePassword(), "FAILED: Change Password is NOT successful.");
    }

    @When("^the player submits valid values in Forgot (Username|Password) Form$")
    public void thePlayerEntersValidValuesInCantLoginForm(String item) throws Throwable {
        workflowDafabet.inputCantLoginValues(item, workflowDafabet.baseDafabet.RegUsername, workflowDafabet.baseDafabet.RegEmail);
        workflowDafabet.submitCantLoginForm(item);
    }

    @Then("^the Forgot (Username|Password) Form is submitted successfully$")
    public void theCantLoginFormIsSubmittedSuccessfully(String item) throws Throwable {
        softAssert.assertTrue(workflowDafabet.validateCorrectCantLoginTab(item), "FAILED: Can't Login - Forgot" + item + " Form display has error.");
        softAssert.assertTrue(workflowDafabet.validateSuccessCantLoginMessage(item), "FAILED: Can't Login - Forgot" + item + " Form is NOT submitted successfully.");
    }

    @When("^the player closes the ([^\"]*) window$")
    public void thePlayerClosesTheWindow(String window) throws Throwable {
        workflowDafabet.closeCurrentWindow();
    }

    @When("^the player search for the email address$")
    public void thePlayerSearchForTheEmailAddress() throws Throwable {
        workflowDafabet.searchYopmailEmail(workflowDafabet.baseDafabet.RegEmail);
    }

    @Then("^the correct username is displayed in email$")
    public void theCorrectUsernameIsDisplayedInEmail() throws Throwable {
        softAssert.assertTrue(workflowDafabet.validateCorrectUsernameInYopmail(workflowDafabet.baseDafabet.RegUsername), "FAILED: Incorrect Username is displayed.");
    }

    @Then("^the player received the Password Reset email$")
    public void thePlayerReceivedThePasswordResetEmail() throws Throwable {
        softAssert.assertTrue(workflowDafabet.validatePasswordResetEmail(workflowDafabet.baseDafabet.RegUsername), "FAILED: Incorrect Username is displayed.");
        softAssert.assertTrue(workflowDafabet.validatePasswordResetContent(), "FAILED: Reset Password button is NOT displayed.");
    }

    @When("^the player opens Reset Password link$")
    public void thePlayerOpensResetPasswordLink()throws Throwable {
        workflowDafabet.launchApplication(workflowDafabet.baseDafabet.PageMyAccount().getResetPasswordLink());
        workflowDafabet.baseDafabet.waitForPageToComplete();
    }

    @When("^the player submits valid values in Reset Password Form$")
    public void thePlayerEntersValidValuesInResetPasswordForm() throws Throwable {
        workflowDafabet.baseDafabet.MyAccNewPassword = "qa456123";
        workflowDafabet.baseDafabet.MyAccConfNewPassword = "qa456123";
        baseNewPassword = workflowDafabet.baseDafabet.MyAccNewPassword;
        workflowDafabet.submitResetPasswordForm(workflowDafabet.baseDafabet.MyAccNewPassword, workflowDafabet.baseDafabet.MyAccConfNewPassword);
    }

    @Then("^the Reset Password Form is submitted successfully$")
    public void theResetPasswordFormIsSubmittedSuccessfully() throws Throwable {
        softAssert.assertTrue(workflowDafabet.validateSuccessResetPasswordMessage(), "FAILED: Reset Password Form is NOT submitted successfully.");
    }
}
