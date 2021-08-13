package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageMyAccount extends BaseDafabet {

    public PageMyAccount(WebDriver driver) {
        super(driver);
    }

    // MY ACCOUNT FORM
    By formMyAcc = By.cssSelector("form.my-profile-form.form-two-col.pure-form.mt-70");
    By txtMyAccUsername = By.id("ProfileForm_username");
    By txtMyAccCurrency = By.id("ProfileForm_currency");
    By txtMyAccFirstName = By.id("ProfileForm_first_name");
    By txtMyAccLastName = By.id("ProfileForm_last_name");
    By txtMyAccDateOfBirth = By.id("ProfileForm_birthdate");
    By txtMyAccCountry = By.id("ProfileForm_country");

    By txtMyAccAddress = By.id("ProfileForm_address");
    By txtMyAccCity = By.id("ProfileForm_city");
    By txtMyAccPostalCode = By.id("ProfileForm_postal_code");
    By btnMyAccSaveChange = By.id("ProfileForm_submit");
    By formMyAccConfSave = By.id("profile-update-changes");
    By txtMyAccPassword = By.id("confirm-password-value");
    By btnConfSaveChange = By.id("submit-confirm-password");
    By msgMyAccSuccessUpdate = By.id("update-profile-success");

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean isMyAccPageDisplayed() {
        if (control.isElementDisplayed(formMyAcc, 10)) {
            System.out.println("\nCURRENT PAGE: MyAccount Page is displayed successfully.\n");
            return true;
        }
        else return false;
    }

    public boolean isMyAccSavedSuccessfully() {
        if (control.isElementDisplayed(msgMyAccSuccessUpdate, 10)) {
            System.out.println("\nCURRENT PAGE: MyAccount Page is saved successfully.\n");
            return true;
        }
        else return false;
    }

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

    public void typeMyAccAddress(String address) throws Exception {
        control.type(txtMyAccAddress, address);
    }

    public void typeMyAccCity(String city) throws Exception {
        RegCity = city;
        control.type(txtMyAccCity, city);
    }

    public void typeMyAccPostalCode(String postalCode) throws Exception {
        control.type(txtMyAccPostalCode, postalCode);
    }

    public void typeMyAccPassword(String password) throws Exception {
        control.type(txtMyAccPassword, password);
    }

    public void clickMyAccButton(String btn) throws Exception {
        switch (btn.toUpperCase()) {
            case "SAVE CHANGES": {
                control.click(btnMyAccSaveChange);
                control.waitWhileElementIsNotDisplayed(formMyAccConfSave,10);
                break;
            }
            case "CONFIRM CHANGES": {
                control.click(btnConfSaveChange);
                control.waitForPageComplete();
                break;
            }
            default:
                System.out.println("FAILED :" + btn + "button is not included in the list of buttons.");
        }
    }

    public String getMyAccInfo(String myAccDetails) throws Exception {
        if (myAccDetails.equalsIgnoreCase("username")) {
            return control.getValue(txtMyAccUsername);
        } else if (myAccDetails.equalsIgnoreCase("currency")) {
            return control.getValue(txtMyAccCurrency);
        } else if (myAccDetails.equalsIgnoreCase("first name")) {
            return control.getValue(txtMyAccFirstName);
        } else if (myAccDetails.equalsIgnoreCase("last name")) {
            return control.getValue(txtMyAccLastName);
        } else if (myAccDetails.equalsIgnoreCase("date of birth")) {
            return control.getValue(txtMyAccDateOfBirth);
        } else if (myAccDetails.equalsIgnoreCase("country")) {
            return control.getValue(txtMyAccCountry);
        } else if (myAccDetails.equalsIgnoreCase("address")) {
            return control.getValue(txtMyAccAddress);
        } else if (myAccDetails.equalsIgnoreCase("city")) {
            return control.getValue(txtMyAccCity);
        } else if (myAccDetails.equalsIgnoreCase("postal code")) {
            return control.getValue(txtMyAccPostalCode);
        }
        else return "";
    }
}
