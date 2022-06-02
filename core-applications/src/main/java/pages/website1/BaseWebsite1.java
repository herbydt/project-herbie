package pages.website1;

import libraries.PageObjectBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseWebsite1 extends PageObjectBase {

    public BaseWebsite1(WebDriver driver) {
        super(driver);
    }

    By txtGoogleSearch = By.cssSelector("input.gLFyf.gsfi");
    By btnGoogleSearch = By.cssSelector("input.gNO89b");
    By txtGoogleSearchResults = By.cssSelector("input.gLFyf.gsfi");
    By bdyGoogleSearchResults = By.cssSelector("div.GyAeWb");

    By btnGoogleSearchNextPage = By.id("pnnext");

    //==================================================================================================================
    // Page Navigation
    //==================================================================================================================

    public PageGoogleSearch PageGoogleSearch() {
        return new PageGoogleSearch(driver);
    }



    public void waitForPageToComplete() {
        control.waitForPageComplete();
    }

}
