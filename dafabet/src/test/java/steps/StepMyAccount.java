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


}
