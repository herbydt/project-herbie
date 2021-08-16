package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    // CHANGE PASSWORD
    By formMyAccChangePass = By.cssSelector("form.change-password-form");
    By txtMyAccOldPassword = By.id("ChangePassword_current_password");
    By txtMyAccNewPassword = By.id("ChangePassword_new_password");
    By txtMyAccConfNewPassword = By.id("ChangePassword_verify_password");
    By btnChangePassword = By.id("ChangePassword_submit");
    By msgChangePassSuccess = By.cssSelector("div.alert.alert-message.alert-success");

    // FORGOT PASSWORD
    By tabForgotPass = By.id("forgot-password-content");
    By formForgotPass = By.cssSelector("form.pure-form.forgot-password-form");
    By txtForgotPasswordUsername = By.id("ForgotPasswordForm_username");
    By txtForgotPasswordEmail = By.id("ForgotPasswordForm_email");
    By btnForgotPasswordSubmit = By.id("ForgotPasswordForm_submit");
    By txtForgotPWConfMessage = By.cssSelector("div[id='forgot-password-content'] > div.confirmation-message.mt-25 > div > h3 > font");

    //FORGOT USERNAME
    By tabForgotUsername = By.id("forgot-username-content");
    By formForgotUsername = By.cssSelector("form.pure-form.forgot-username-form");
    By txtForgotUsernameEmail = By.id("ForgotUsernameForm_email");
    By btnForgotUsernameSubmit = By.id("ForgotUsernameForm_submit");
    By txtForgotUNConfMessage = By.cssSelector("div[id='forgot-username-content'] > div.confirmation-message.mt-25 > div > h3");


    By txtResetPWNewPassword = By.id("ResetPasswordForm_new_password");
    By txtResetPWConfNewPassword = By.id("ResetPasswordForm_verify_password");
    By btnResetPassword = By.id("ResetPasswordForm_submit");

    // YOPMAIL
    By txtForgotUNEmail =  By.xpath("/html/body/main/div/div/div/table[2]/tbody/tr/td/div/div/span/span");
    By txtForgotPWEmailUsername =  By.xpath("/html/body/main/div/div/div/table[2]/tbody/tr/td/span[1]");
    By btnForgotPWEmailResetPW = By.xpath("/html/body/main/div/div/div/table[2]/tbody/tr/td/div[1]/a");
    By txtResetPWConfMessage = By.cssSelector("div.confirmation-message.mt-25 > h2.text-center.text-semibold.text-red.text-20");

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean isUsernameCorrect(String username) {
        String dispUsername = control.getText(By.id("welcome-username"));
        if (dispUsername.equalsIgnoreCase(username)) {
            System.out.println("\nCURRENT PAGE: Player Username: " + dispUsername + " is displayed correctly.");
            return true;
        }
        else return false;
    }

    public boolean isMyAccPageDisplayed(String username) {
        if (control.isElementDisplayed(formMyAcc, 10)) {
            if (isUsernameCorrect(username)) {
                System.out.println("\nCURRENT PAGE: MyAccount Page is displayed successfully.\n");
                return true;
            } else {
                System.out.println("\nCURRENT PAGE: MyAccount Page is displayed but Username is incorrect.\n");
                return false;
            }
        }
        else return false;
    }

    public boolean isMyAccChangePasswordDisplayed(String username) {
        if (control.isElementDisplayed(formMyAccChangePass, 10)) {
            if (isUsernameCorrect(username)) {
                System.out.println("\nCURRENT PAGE: MyAccount - Change Password Page is displayed successfully.\n");
                return true;
            } else {
                System.out.println("\nCURRENT PAGE: MyAccount Page - Change Password Page is displayed but Username is incorrect.\n");
                return false;
            }
        }
        else return false;
    }

    public boolean isForgotPasswordPageDisplayed() {
        if (control.isElementDisplayed(tabForgotPass, 10)) {
            String classForgotPassword = control.getAttributeValue(tabForgotPass,"class",10);
            if(!classForgotPassword.contains("hidden")) {
                System.out.println("\nCURRENT PAGE: Forgot Password Page is displayed successfully.\n");
                return true;
            } else {
                System.out.println("\nFAILED: Can't Login is loaded but incorrect form is loaded.\n");
                return false;
            }
        }
        else return false;
    }

    public boolean isForgotUsernamePageDisplayed() {
        if (control.isElementDisplayed(tabForgotUsername, 10)) {
            String classForgotUsername = control.getAttributeValue(tabForgotUsername,"class",10);
            if(!classForgotUsername.contains("hidden")) {
                System.out.println("\nCURRENT PAGE: Forgot Username Page is displayed successfully.\n");
                return true;
            } else {
                System.out.println("\nFAILED: Can't Login is loaded but incorrect form is loaded.\n");
                return false;
            }
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

    public boolean isMyAccChangePasswordSuccessful() {
        if (control.isElementDisplayed(msgChangePassSuccess, 10)) {
            System.out.println("\nCURRENT PAGE: Change Password is successful.\n");
            return true;
        }
        else return false;
    }

    public boolean isSuccessfulCantLoginMessageDisplayed(String item) {
        switch (item.toUpperCase()) {
            case "USERNAME" : {
                String confMessage = control.getText(txtForgotUNConfMessage);
                System.out.println("\n"+ confMessage);
                if((confMessage.contains("Retrieve Username"))&&(confMessage.contains("Sent"))) {
                    System.out.println("\nPASSED: Forgot Username Form is submitted successfully.\n");
                    return true;
                } else {
                    System.out.println("\nFAILED: Forgot Username Form is NOT submitted successfully.\n");
                    return false;
                }
            }
            case "PASSWORD" : {
                String confMessage = control.getText(txtForgotPWConfMessage);
                System.out.println("\n"+ confMessage);
                if((confMessage.contains("Reset Password"))&&(confMessage.contains("Sent"))) {
                    System.out.println("\nPASSED: Forgot Password Form is submitted successfully.\n");
                    return true;
                } else {
                    System.out.println("\nFAILED: Forgot Password Form is NOT submitted successfully.\n");
                    return false;
                }
            }
            default: {
                System.out.println("\nFAILED: Form Submission is NOT successful.\n");
                return false;
            }
        }
    }

    public boolean isCorrectCantLoginTabOpened(String item) {
        switch (item.toUpperCase()) {
            case "USERNAME" : {
                String formClassForgotUN = control.getAttributeValue(formForgotUsername,"class",10);
                String tabClassForgotUN = control.getAttributeValue(tabForgotUsername,"class",10);
                if((formClassForgotUN.contains("hidden"))&&(!tabClassForgotUN.contains("hidden"))) {
                    System.out.println("\nPASSED: Forgot Username Form is hidden.\n");
                    return true;
                } else {
                    System.out.println("\nFAILED: Error hiding Forgot Username Form.\n");
                    return false;
                }
            }
            case "PASSWORD" : {
                String formClassForgotPW = control.getAttributeValue(formForgotPass,"class",10);
                String tabClassForgotPW = control.getAttributeValue(tabForgotPass,"class",10);
                if((formClassForgotPW.contains("hidden"))&&(!tabClassForgotPW.contains("hidden"))) {
                    System.out.println("\nPASSED: Forgot Username Form is hidden.\n");
                    return true;
                } else {
                    System.out.println("\nFAILED: Error hiding Forgot Username Form.\n");
                    return false;
                }
            }
            default: {
                System.out.println("\nFAILED: Form Submission is NOT successful.\n");
                return false;
            }
        }
    }

    public boolean isCorrectUsernameDisplayedInYopmail(String username) {
        WebElement iframeMsg = driver.findElement(By.id("ifmail"));
        driver.switchTo().frame(iframeMsg);

        WebElement body = driver.findElement(txtForgotUNEmail);
        String dispUsername = body.getText();

        if (dispUsername.equalsIgnoreCase(username)) {
            System.out.println("\nPASSED: Displayed Username in Email is correct.\n");
            return true;
        } else {
            System.out.println("\nFAILED: Incorrect Username in Email is displayed.\n");
            return false;
        }
    }

    public boolean isCorrectUsernameDisplayedInYopmailForgotPW(String username) {
        WebElement iframeMsg = driver.findElement(By.id("ifmail"));
        driver.switchTo().frame(iframeMsg);

        WebElement body = driver.findElement(txtForgotPWEmailUsername);
        String dispUsername = body.getText();

        if (dispUsername.toLowerCase().contains(username.toLowerCase())) {
            System.out.println("\nPASSED: Displayed Username in Email is correct.\n");
            return true;
        } else {
            System.out.println("\nFAILED: Incorrect Username in Email is displayed.\n");
            return false;
        }
    }

    public boolean isResetPasswordBtnDisplayedForgotPW() {
        WebElement content = driver.findElement(btnForgotPWEmailResetPW);
        String txtResetPWBtn = content.getText();
        String dispLink = content.getAttribute("href");

        if ((txtResetPWBtn.equalsIgnoreCase("reset password"))&&(!dispLink.equals(""))) {
            System.out.println("\nPASSED: Reset Password button is displayed.\n");
            return true;
        } else {
            System.out.println("\nFAILED: Reset Password button is missing.\n");
            return false;
        }
    }

    public boolean isSuccessfulResetPasswordMessageDisplayed() {
        String confMessage = control.getText(txtResetPWConfMessage);
        System.out.println("\n"+ confMessage);
        if((confMessage.contains("Reset Password"))&&(confMessage.contains("Success"))) {
            System.out.println("\nPASSED: Reset Password Form is submitted successfully.\n");
            return true;
        } else {
            System.out.println("\nFAILED: Reset Password Form is NOT submitted successfully.\n");
            return false;
        }
    }

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

    // MY ACCOUNT - VIEW PROFILE
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
            case "CHANGE PASSWORD": {
                control.click(btnChangePassword);
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


    // MY ACCOUNT - CHANGE PASSWORD
    public void typeMyAccOldPassword(String oldPassword) throws Exception {
        control.type(txtMyAccOldPassword, oldPassword);
    }

    public void typeMyAccNewPassword(String newPassword) throws Exception {
        control.type(txtMyAccNewPassword, newPassword);
    }

    public void typeMyAccConfNewPassword(String confNewPassword) throws Exception {
        control.type(txtMyAccConfNewPassword, confNewPassword);
    }

    // CANT LOGIN - FORGOT USERNAME
    public void typeForgotUsernameEmail(String email) throws Exception {
        control.type(txtForgotUsernameEmail, email);
    }

    public void clickSubmitForgotUsername() throws Exception {
        control.click(btnForgotUsernameSubmit);
        control.waitWhileElementIsNotDisplayed(txtForgotUNConfMessage,10);
    }

    // CANT LOGIN - FORGOT PASSWORD
    public void typeForgotPasswordUsername(String username) throws Exception {
        control.type(txtForgotPasswordUsername, username);
    }

    public void typeForgotPasswordEmail(String email) throws Exception {
        control.type(txtForgotPasswordEmail, email);
    }

    public void clickSubmitForgotPassword() throws Exception {
        control.click(btnForgotPasswordSubmit);
        control.waitWhileElementIsNotDisplayed(txtForgotPWConfMessage,10);
    }

    // CANT LOGIN - RESET PASSWORD
    public void typeResetPasswordNewPassword(String password) throws Exception {
        control.type(txtResetPWNewPassword,password);
    }

    public void typeResetPasswordConfNewPassword(String confPassword) throws Exception {
        control.type(txtResetPWConfNewPassword,confPassword);
    }

    public void submitResetPassword() throws Exception {
        control.click(btnResetPassword);
        control.waitWhileElementIsNotDisplayed(txtResetPWConfMessage,10);
    }

    // YOPMAIL

    public void typeYopmailEmail(String email) throws Exception {
        control.type(txtEmailSearch, email);
    }

    public void clickYopmailEmailSearch() throws Exception {
        control.click(btnEmailSearch);
        control.waitWhileElementIsNotDisplayed(txtSearchResultsHeader,10);
        control.waitWhileElementIsNotDisplayed(sectionSearchResults,10);
    }

    public String getResetPasswordLink() {
        WebElement btnLink = driver.findElement(btnForgotPWEmailResetPW);
        return btnLink.getAttribute("href");
    }
}
