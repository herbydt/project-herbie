package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import utilities.DateTimeUtil;

import java.util.List;

public class StepRegister extends BaseStep {

    BaseStep base;

    public StepRegister(BaseStep base) {
        this.base = base;
    }

    @When("^the player enters valid values with Username in (lowercase|uppercase|mixed case|leading zeroes)$")
    public void thePlayerEntersValidValues(String usernameCase, DataTable userDetails) throws Throwable {
        List<List<String>> data = userDetails.raw();

        String usernamePrefix = "";
        if (usernameCase.equalsIgnoreCase("lowercase")) {
            usernamePrefix = "auto";
        } else if (usernameCase.equalsIgnoreCase("uppercase")) {
            usernamePrefix = "AUTO";
        } else if (usernameCase.equalsIgnoreCase("mixed case")) {
            usernamePrefix = "AuTo";
        } else if (usernameCase.equalsIgnoreCase("leading zeroes")) {
            usernamePrefix = "0000";
        }

        NewlyRegisteredPlayer = usernamePrefix + DateTimeUtil.getDateTimeToday("yyMMddHHmm").substring(1, 10);
        workflowDafabet.baseDafabet.RegUsername = NewlyRegisteredPlayer;
        workflowDafabet.baseDafabet.RegPassword = data.get(0).get(1);
        workflowDafabet.baseDafabet.RegEmail = NewlyRegisteredPlayer + data.get(1).get(1);
//        workflowDafabet.baseDafabet.RegAreaCode = workflowDafabet.baseDafabet.PageRegister().getAreaCodeValue();
        workflowDafabet.baseDafabet.RegAreaCode = data.get(2).get(1);
        workflowDafabet.baseDafabet.RegMobileNumber = data.get(3).get(1);
        workflowDafabet.baseDafabet.RegFirstName = data.get(4).get(1);
        workflowDafabet.baseDafabet.RegLastName = data.get(5).get(1);
        workflowDafabet.baseDafabet.RegDateOfBirth = data.get(6).get(1);
        workflowDafabet.baseDafabet.RegCurrency = data.get(7).get(1);
        workflowDafabet.baseDafabet.RegCountry = data.get(8).get(1);
        workflowDafabet.baseDafabet.RegLanguage = baseLanguage;
        baseUsername = NewlyRegisteredPlayer;
        basePassword = workflowDafabet.baseDafabet.RegPassword;
    }

    @Then("^a new account is successfully created$")
    public void aNewAccountIsSuccessfullyCreated() throws Throwable {
        workflowDafabet.baseDafabet.closeAnnouncementLightbox();
        Assert.assertTrue(workflowDafabet.baseDafabet.PageRegister().submit(
                workflowDafabet.baseDafabet.RegUsername,
                workflowDafabet.baseDafabet.RegPassword,
                workflowDafabet.baseDafabet.RegEmail,
                workflowDafabet.baseDafabet.RegAreaCode,
                workflowDafabet.baseDafabet.RegMobileNumber,
                workflowDafabet.baseDafabet.RegFirstName,
                workflowDafabet.baseDafabet.RegLastName,
                workflowDafabet.baseDafabet.RegDateOfBirth,
                workflowDafabet.baseDafabet.RegCurrency,
                workflowDafabet.baseDafabet.RegCountry,
                workflowDafabet.baseDafabet.RegLanguage),"FAILED: Registration is not successfully submitted. \n");

        System.out.println("INFO: Newly Registered User = " + NewlyRegisteredPlayer + "\n");
        baseCashierPlayerStatus = "new";
        CurrentState = "Post-Login";
        softAssert.assertTrue(workflowDafabet.baseDafabet.PageMakeDeposit().isUsernameDisplayedCorrect(NewlyRegisteredPlayer), "FAILED: Deposit page is not displayed. \n");
    }
}
