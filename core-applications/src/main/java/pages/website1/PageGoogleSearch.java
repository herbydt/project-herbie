package pages.website1;

import org.openqa.selenium.WebDriver;

public class PageGoogleSearch extends BaseWebsite1{

    public PageGoogleSearch(WebDriver driver) {
        super(driver);
    }

    //==================================================================================================================
    // Control Actions
    //==================================================================================================================

    public void typeSearchItem(String item) throws Exception {
        control.type(txtGoogleSearch, item);
    }

    public void clickGoogleSearchButton() throws Exception {
        control.click(btnGoogleSearch);
    }

    //==================================================================================================================
    // Validations
    //==================================================================================================================

    public boolean verifySearchResultsPage(String searchItem) throws Exception {

        if (control.getValue(txtGoogleSearchResults).contains(searchItem)) {
            if (control.isDisplayed(bdyGoogleSearchResults)) {
                System.out.println("PASSED: The Google Search for " + searchItem + " displayed results." + "\n");
                return true;
            }
            else {
                System.out.println("FAILED: The Google Search for " + searchItem + " didn't return any result." + "\n");
                return false;
            }
        }
        System.out.println("FAILED: The Google Search for " + searchItem + " failed to do the search" + "\n");
        return false;
    }

}
