package pages.dafabet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageMakeDeposit extends BaseDafabet {

    public PageMakeDeposit(WebDriver driver) {
        super(driver);
    }

    By lblWelcomeHeader = By.cssSelector("p.banner-header strong");
    By tabDeposit = By.cssSelector(".button-deposit-now");
    By lnkSkip = By.cssSelector("span.skip-link");

    //==================================================================================================================
    // Class Methods
    //==================================================================================================================

    public boolean isUsernameDisplayedCorrect(String expectedUsername) throws Exception {
        control.waitForPageComplete();
        //jcabangon: Enclosed script in try-catch to ensure that execution will not stop in case Make Deposit page was not displayed
        try{
            control.waitWhileElementIsNotDisplayed(lblWelcomeHeader, 30, "ERROR: Make Deposit page is not displayed. \n");
            String welcomeUsername = getUsername();
            System.out.println("Welcome message is: " + welcomeUsername + ". \n");
            if(welcomeUsername.toUpperCase().contains(expectedUsername.toUpperCase())) {
                return true;
            }
        } catch (Exception missingDepositPage){
            return false;
        }
        return false;
    }

    public String getUsername() throws Exception{
        String username = control.getText(lblWelcomeHeader);
        int ctr = 0;
        while(username.equalsIgnoreCase("") || username.isEmpty()) {
            username = control.getText(lblWelcomeHeader);
            Thread.sleep(1000);
            ctr++;
            if(ctr > 10) {
                throw new Exception("ERROR: Username is not displayed. Please try again. ");
            }
        }
        return username;
    }

    public boolean isStep2PageDisplayed() {
        return control.isElementDisplayed(tabDeposit, 15);
    }

    public void clickSkipButton(){
        control.click(lnkSkip);
    }
}
