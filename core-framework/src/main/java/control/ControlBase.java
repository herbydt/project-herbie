package control;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class ControlBase {

    protected WebDriver driver;
    protected ControlBase control;

    Logger log = LoggerFactory.getLogger(ControlBase.class);

//    WebDriver driver;

    public int DEFAULT_WAIT_TIME = 5;


    public ControlBase(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Highlighter control displays a yellow highlight around the active locator where action is performed.
     *
     * @author By: achung
     * @param locator The locating mechanism. The locating mechanism.
     */
    public void highLighter(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        boolean isClickSuccess = false;
        int ctr = 0;
        while (!isClickSuccess) {
            ctr++;
            if (ctr < 10) {
                try {
                    js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid yellow;');", element);
                } catch (StaleElementReferenceException s) {
                    element = driver.findElement(locator);
                    js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid yellow;');", element);
                }
                isClickSuccess = true;
            } else {
                System.out.println("FAILED: Error clicking the element due to StaleElementReferenceException. \n");
                break;
            }
        }
    }

    /**
     * This control simulates typing into a locator.
     *
     * @author mramos
     * @param locator The locating mechanism.
     * @param inputText The value to be entered.
     */
    public void type(By locator, String inputText) {
        highLighter(locator);
        log.debug("type: " + locator.toString() + " " + inputText);
        type(locator, inputText, DEFAULT_WAIT_TIME);
    }


    //==================================================================================================================
    // Type/EnterText
    //==================================================================================================================
    /**
     * This control simulates typing on the locator.
     *
     * @author mramos
     * @param locator The locating mechanism.
     * @param inputText The text value that will be entered on the locator.
     * @param maxWaitTime The configurable max wait counter.
     */
    public void type(By locator, String inputText, int maxWaitTime) {
        highLighter(locator);
        log.debug("type: " + locator.toString() + " " + inputText + " maxWaitTime: " + maxWaitTime);
        try {
            WebElement element = findElement(locator, maxWaitTime);
            if (element == null) {
                log.error(locator.toString() + " is null." + "\n");
                return;
            }
            element.clear();
            element.sendKeys(inputText);
        } catch (StaleElementReferenceException s) {
            WebElement element = findElement(locator, maxWaitTime);
            element.clear();
            element.sendKeys(inputText);
        }
    }


    /**
     * This control finds and returns the first matching element on the page.
     *
     * @author mramos
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return WebElement matching the locator.
     */
    public WebElement findElement(By locator, int maxWaitTime) {
        log.debug("findElement: " + locator.toString() + " maxWaitTime: " + maxWaitTime);

        WebElement element = null;
        if (driver == null) {
            log.error("driver is null." + "\n");
            throw new NullPointerException("ERROR: driver is null.");
        }
        try{
            if (isElementClickableWithinWait(locator, maxWaitTime)) {
                element = driver.findElement(locator);
            }
        }
        catch(StaleElementReferenceException se){
            element = driver.findElement(locator);
        }
        return element;
    }

    /**
     * This control waits until the locator becomes clickable within the max wait counter set.
     *
     * @author mramos
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return True if the locator is clickable otherwise, False.
     */
    public boolean isElementClickableWithinWait(By locator, int maxWaitTime) {
        log.debug("isElementClickableWithinWait: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        try {
            waitFor(ExpectedConditions.elementToBeClickable(locator), maxWaitTime);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    //==================================================================================================================
    // Wait, Synchronization
    //==================================================================================================================
    /**
     * This control waits to satisfy the specified condition within the max wait counter set.
     *
     * @author mramos
     * @param condition The condition to satisfy before max wait counter lapses.
     * @param maxWaitTime The configurable max wait counter.
     */
    private void waitFor(ExpectedCondition<WebElement> condition, int maxWaitTime) {
        log.debug("waitFor: " + condition + " maxWaitTime: " + maxWaitTime);
        WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
        wait.until(condition);
    }

    /**
     * This control waits until the return document.readyState is complete.
     *
     * @author achung
     */
    public void waitForPageComplete() {
        log.debug("waitForPageComplete");
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        System.out.println("DEBUG: Waiting for page to complete.. \n");
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public void launchURL(String url) {
        //setting the driver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\drivers\\chromedriver.exe");

        //Initiating your chromedriver
        WebDriver driver = new ChromeDriver();

        //Applied wait time
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //maximize window
        driver.manage().window().maximize();

        //open browser with desried URL
        driver.get(url);

        //closing the browser
        driver.close();
    }

}
