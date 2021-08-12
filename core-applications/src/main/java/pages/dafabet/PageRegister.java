package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageRegister extends BaseDafabet {

    public PageRegister(WebDriver driver) {
        super(driver);
    }

    By txtUsername = By.id("RegistrationForm_username");
    By txtPassword = By.id("RegistrationForm_password");
    By txtEmail = By.id("RegistrationForm_email");
    By ddlAreaCode = By.cssSelector("div.form-field.country-area-code span.trigger");
    By ddlAreaCodeSelection = By.cssSelector("div.country-dropdown.active");
//    By ddlAreaCodeSelected = By.cssSelector("div.form-field.country-area-code > span");
    By txtMobileNumber = By.id("RegistrationForm_mobile_number");
    By txtFirstName = By.id("RegistrationForm_first_name");
    By txtLastName = By.id("RegistrationForm_last_name");
    By txtDateOfBirth = By.id("RegistrationForm_birthdate");
    By ddlCurrency = By.id("RegistrationForm_currency");
    By ddlCountry = By.id("RegistrationForm_country");
    By btnSubmit = By.id("RegistrationForm_submit");

    By loaderSubmit = By.cssSelector("span.loading-icon-submit");

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean isRegPageDisplayed() {
        if (control.isElementDisplayed(formRegister, 10)) {
            System.out.println("\nCURRENT PAGE: Registration Form is displayed correctly.\n");
            return true;
        }
        else return false;
    }

    //==================================================================================================================
    // Class Methods
    //==================================================================================================================
    public String getAreaCodeValue() {
        return control.getText(ddlAreaCode);
    }

    public boolean submit(String username, String password, String email, String areaCode, String mobileNumber,
                          String firstName, String lastName, String dateOfBirth, String currency,
                          String country, String language) {

        try {
            setTextField(txtUsername, username);
            control.waitWhileElementIsDisplayed(By.cssSelector("span.icon-pending"), 5, "ERROR: Pending icon takes too long to load");
            setTextField(txtPassword, password);
            setTextField(txtEmail, email);
            control.waitWhileElementIsDisplayed(By.cssSelector("span.icon-pending"), 5, "ERROR: Pending icon takes too long to load");
            control.scrollToElement(txtEmail);
            control.click(ddlAreaCode);
            control.selectDropdownItem(ddlAreaCodeSelection,areaCode);
            setTextField(txtMobileNumber, mobileNumber);
            setTextField(txtFirstName, firstName);
            setTextField(txtLastName, lastName);
            control.scrollToElement(txtLastName);
            setTextField(txtDateOfBirth, dateOfBirth);
            setDropdownFieldBasedOptionAttributeValue(ddlCurrency, getCurrencyValueInRegPage(currency));
            setDropdownFieldBasedOptionAttributeValue(ddlCountry, getCountryValueInRegPage(country));

            //driver.manage().window().maximize();
            if (!language.equalsIgnoreCase("kr")){
                minimizeLiveChat();
            }
            control.scrollToElement(ddlCountry);
            control.click(btnSubmit);
            waitLoading(loaderSubmit, 30);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================
    public void setTextField(By locator, String value) throws Exception {
        if (value != null) {
            control.type(locator, value);
            control.findElement(locator).sendKeys(Keys.TAB);
        }
    }

    public void setDropdownFieldBasedOptionAttributeValue(By locator, int value) throws Exception {
        if (value != 0) {
            //fix for value lookup error on blank option
            WebElement element = control.findElement(locator).findElement(By.cssSelector("option[value='" + value + "']"));
            control.click(locator);
            control.click(element);
            control.findElement(locator).sendKeys(Keys.ESCAPE);
        }
    }


    //==================================================================================================================
    // Mappings
    //==================================================================================================================

    public int getCurrencyValueInRegPage(String curr) throws Exception {
        switch (curr.toUpperCase()) {
            case "RMB/CNY":
                return 117;
            case "人民币":
                return 117;
            case "USD":
                return 2;
            case "EUR":
                return 1;
            case "GBP":
                return 3;
            case "MYR":
                return 102;
            case "THB":
                return 103;
            case "VND":
                return 105;
            case "IDR":
                return 111;
            case "INR":
                return 112;
            case "KRW":
                return 104;
            case "RUB":
                return 116;
            case "PLN":
                return 114;
            case "MXN":
                return 120;
            case "BRL":
                return 119;
            case "CLP":
                return 121;
            case "COP":
                return 122;
            case "PEN":
                return 123;
            case "VES":
                return 125;
            case "MBTC": // Carlo Added 03/30/2020
                return 128;
            case "LAK": // Marquee Added 07/02/2020
                return 131;
            case "MMK": // Marquee Added 07/02/2020
                return 132;
            case "KHR": // Marquee Added 07/02/2020
                return 133;
            default:
                return 0;
        }
    }

    public int getCountryValueInRegPage(String country) throws Exception {
        switch (country.toUpperCase()) {
            case "CHINA":       // RMB
                return 48;
            case "中国":       // RMB
                return 48;
            case "INDONESIA":   // IDR
                return 96;
            case "KOREA":       // KRW
                return 117;
            case "MALAYSIA":    // MYR
                return 153;
            case "THAILAND":    // THB
                return 208;
            case "VIETNAM":     // VND
                return 231;
            case "INDIA":       // INR
                return 100;
            case "MALTA":       // EUR
                return 148;
            case "POLAND":      // PLN
                return 174;
            case "UNITED STATES":         // USD
                return 223;
            case "GUERNSEY":    // GBP
                return 78;
            case "RUSSIA":      // RUB
                return 186;
            case "BRAZIL":      //BRL
                return 32;
            case "MEXICO":      //MXN
                return 152;
            case "CHILE":       //CLP
                return 46;
            case "COLOMBIA":    //COP
                return 49;
            case "PERU":        //PEN
                return 169;
            case "BOLIVIA":     //VES
                return 31;
            case "HAITI":       //USD
                return 94;
            case "LAO PEOPLE'S DEMOCRATIC REPUBLIC":       //LAK // Marquee Added 07/02/2020
                return 121;
            case "MYANMAR":       //MMK // Marquee Added 07/02/2020
                return 141;
            case "CAMBODIA":       //KHR // Marquee Added 07/02/2020
                return 112;
            default:
                return 0;
        }
    }

}
