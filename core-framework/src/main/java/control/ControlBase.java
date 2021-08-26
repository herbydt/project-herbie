package control;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.math.BigDecimal;
import java.security.Key;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Methods in Control Base still uses the Selenium WebElement methods with customizations like addition of logs, wait
 * and other catch implementations.
 */
public class ControlBase {

    Logger log = LoggerFactory.getLogger(ControlBase.class);

    WebDriver driver;
    public int DEFAULT_WAIT_TIME = 5;

    public ControlBase(WebDriver driver) {
        this.driver = driver;
    }

//    public HtmlTable HTMLTable() {
//        return new HtmlTable(driver);
//    }
//
//    public DivTable DivTable() {
//        return new DivTable(driver);
//    }


    //==================================================================================================================
    // Primary/Common Actions - with DEFAULT_WAIT_TIME
    //==================================================================================================================
    /**
     * This control simulates creation of new blank tab.
     */
    public void createNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
    }

    /**
     * Highlighter control displays a yellow highlight around the active locator where action is performed.
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

    public void unhighLighter(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        boolean isClickSuccess = false;
        int ctr = 0;
        while (!isClickSuccess) {
            ctr++;
            if (ctr < 10) {
                try {
                    js.executeScript("arguments[0].setAttribute('style', 'border: none');", element);
                } catch (StaleElementReferenceException s) {
                    element = driver.findElement(locator);
                    js.executeScript("arguments[0].setAttribute('style', 'border: none');", element);
                }
                isClickSuccess = true;
            } else {
                System.out.println("FAILED: Error clicking the element due to StaleElementReferenceException. \n");
                break;
            }
        }
    }

    /**
     * This control clears text in the field element.
     * @param locator The locating mechanism.
     */
    public void clear(By locator) {
        highLighter(locator);
        log.debug("type: " + locator.toString());
        WebElement element = findElement(locator, DEFAULT_WAIT_TIME);
        element.clear();
    }

    /**
     * This control clears text in the field element by click backspace key.
     * @param locator The locating mechanism.
     */
    public void clearByBackSpace(By locator) {
        highLighter(locator);
        log.debug("type: " + locator.toString());
        WebElement element = findElement(locator, DEFAULT_WAIT_TIME);
        while(!element.getText().equals("")){
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    /**
     * This control simulates typing into a locator.
     * @param locator The locating mechanism.
     * @param inputText The value to be entered.
     */
    public void type(By locator, String inputText) {
        highLighter(locator);
        log.debug("type: " + locator.toString() + " " + inputText);
        type(locator, inputText, DEFAULT_WAIT_TIME);
    }

    /**
     * This control simulates typing into a locator using Actions from Selenium interaction.
     * @param locator The locating mechanism.
     * @param inputText The value to be entered.
     */
    public void actionType(By locator, String inputText) throws InterruptedException {
        log.debug("type: " + locator.toString() + " " + inputText);
        WebElement element = driver.findElement(locator);

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.doubleClick(element);
        actions.build().perform();
        actions.sendKeys(inputText);
        actions.build().perform();
    }

    /**
     * This control simulates clicking a locator using Actions from Selenium interaction.
     * @param locator The locating mechanism.
     * @throws InterruptedException
     */
    public void actionClick(By locator) throws Exception {
        log.debug("click: " + locator.toString());
        WebElement element = driver.findElement(locator);
        boolean error = true;
        int counter = 0;
        Actions actions = new Actions(driver);
        while (error) {
            Thread.sleep(1000);
            try {
                element = driver.findElement(locator);
                actions.moveToElement(element);
                actions.click();
                actions.build().perform();
                error = false;
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Action click stale.. \n");
                error = true;
                counter++;
                if(counter>10){
                    throw new Exception("ERROR: Stale Element Reference Exception with 10 tries");
                }
            }
        }

    }

    /**
     * This control simulates clicking a locator using Actions from Selenium interaction.
     * @param element The locating mechanism.
     * @throws InterruptedException
     */
    public void actionClick(WebElement element) throws Exception {
        log.debug("click: " + element.toString());
        boolean error = true;
        int counter = 0;
        Actions actions = new Actions(driver);
        while (error) {
            Thread.sleep(1000);
            try {
                actions.moveToElement(element);
                actions.click();
                actions.build().perform();
                error = false;
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Action click stale.. \n");
                error = true;
                counter++;
                if(counter>10){
                    throw new Exception("ERROR: Stale Element Reference Exception with 10 tries");
                }
            }
        }

    }

    /**
     * This control simulates click into a locator.
     * @param locator The locating mechanism.
     */
    public void click(By locator) {
        highLighter(locator);
        log.debug("click: " + locator.toString());
        click(locator, DEFAULT_WAIT_TIME);
    }

    /**
     * This control gets the visible text of the locator.
     * @param locator The locating mechanism.
     * @return The String text value retrieved from the locator.
     */
    public String getText(By locator) {
        log.debug("getText: " + locator.toString());
        return getText(locator, DEFAULT_WAIT_TIME);
    }

    /**
     * This control gets the visible text of the element.
     * @param element The locating mechanism.
     * @return The String text value retrieved from the element.
     */
    public String getText(WebElement element) {
        log.debug("getText: " + element.toString());
        return element.getText();
    }

    /**
     * This control gets the value of the attribute "value" from the locator.
     * @param locator The locating mechanism.
     * @return The String text value retrieved from the locator.
     */
    public String getValue(By locator) {
        log.debug("getValue: " + locator.toString());
        return getValue(locator, 20);
    }

    /**
     * This control selects the dropdown value based on select - option locator.
     * @param locator The locating mechanism.
     * @param text The text value on the locator to be selected.
     * @throws Exception
     */
    public void selectDropdownOption(By locator, String text) throws Exception {
        highLighter(locator);
        log.debug("selectDropdownOption: " + locator.toString() + " " + text);
        selectDropdownOption(locator, text, DEFAULT_WAIT_TIME);
    }

    /**
     * This control returns true after it checks if the locator is displayed in the page.
     * @param locator The locating mechanism.
     * @return Returns true if the element is displayed in the page.
     */
    public void selectDropdownContains(By locator, String text) throws Exception {
        highLighter(locator);
        log.debug("selectDropdownOption: " + locator.toString() + " " + text);
        selectDropdownContains(locator, text, DEFAULT_WAIT_TIME);
    }

    /**
     * This control returns true after it checks if the locator is displayed in the page.
     * @param locator The locating mechanism.
     * @return Returns true if the element is displayed in the page.
     */
    public boolean isDisplayed(By locator) {
        log.debug("isDisplayed: " + locator.toString());
        return isElementDisplayed(locator, DEFAULT_WAIT_TIME);
    }

    /**
     * This control returns true after it checks if the locator is displayed in the page.
     * @param element The web element
     * @return Returns true if the element is displayed in the page.
     */
    public boolean isDisplayed(WebElement element) {
        log.debug("isDisplayed: " + element.toString());
        try {
            return element.isDisplayed();
        }catch (java.util.NoSuchElementException e){
            return false;
        }
    }

    /**
     * This control returns true after it checks if the locator is not displayed in the page.
     * @param locator The locating mechanism.
     * @return Returns true if the element is not displayed in the page.
     */
    public boolean isNotDisplayed(By locator) {
        log.debug("isNotDisplayed: " + locator.toString());
        return !isElementDisplayed(locator, 5);
    }

    /**
     * This control returns true after it checks if the locator is present in the page.
     * @param locator The locating mechanism.
     * @return Returns true if the element is present in the page.
     */
    public boolean isPresent(By locator) {
        log.debug("isPresent: " + locator.toString());
        return isElementPresent(locator, DEFAULT_WAIT_TIME);
    }

    /**
     * This control returns true after it checks if the locator is enabled in the page.
     * @param locator The locating mechanism.
     * @return Returns true if the element is enabled and clickable in the page.
     */
    public boolean isEnabled(By locator) {
        log.debug("isEnabled: " + locator.toString());
        return isElementClickableWithinWait(locator, DEFAULT_WAIT_TIME);
    }

    /**
     * This control clicks the locator of the text which contains the text parameter.
     * @param locator The locating mechanism.
     * @param text The text on the locator that will be clicked.
     */
    public void clickTextInside(By locator, String text) {
        highLighter(locator);
        log.debug("clickTextInside: " + locator.toString() + " " + text);
        clickTextInside(locator, text, DEFAULT_WAIT_TIME);
    }

    /**
     * This control clicks the locator of the text which contains the text parameter using java executor.
     * @param locator The locating mechanism.
     * @param text The text on the locator that will be clicked.
     */
    public void jsClickTextInside(By locator, String text) {
        highLighter(locator);
        log.debug("jsClickTextInside: " + locator.toString() + " " + text);
        jsClickTextInside(locator, text, DEFAULT_WAIT_TIME);
    }

    /**
     * This control clicks the locator of the link text which contains the text parameter.
     * @param locator The locating mechanism.
     * @param text The text on the linktext locator that will be clicked.
     */
    public void clickLinkTextInside(By locator, String text) {
        highLighter(locator);
        log.debug("clickLinkTextInside: " + locator.toString() + " " + text);
        clickLinkTextInside(locator, text, DEFAULT_WAIT_TIME);
    }

    //==================================================================================================================
    // Type/EnterText
    //==================================================================================================================
    /**
     * This control simulates typing on the locator.
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


    //==================================================================================================================
    // Click Elements
    //==================================================================================================================
    /**
     * This control simulates click into a locator within the max wait time.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     */
    public void click(By locator, int maxWaitTime) {
        highLighter(locator);
        log.debug("click: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElement(locator, maxWaitTime);
        if (element == null) {
            log.error(locator.toString() + " is null." + "\n");
        }
        boolean isClickSuccess = false;
        int ctr = 0;
        while (!isClickSuccess) {
            ctr++;
            if (ctr < 10) {
                try {
                    element.click();
                } catch (StaleElementReferenceException s) {
                    element = findElement(locator, maxWaitTime);
                    element.click();
                }
                isClickSuccess = true;
            } else {
                System.out.println("FAILED: Error clicking the element due to StaleElementReferenceException. \n");
                break;
            }
        }
    }

    /**
     * This control simulates click into an element.
     * @param element The locating mechanism.
     */
    public void click(WebElement element) {
        log.debug("click: " + element.toString());
        if (element == null) {
            log.error(element.toString() + " is null." + "\n");
            return;
        }
        element.click();
    }

    /**
     * This control simulates click into a locator using javascript executor.
     * @param locator The locating mechanism.
     */
    public void jsClick(By locator) {
        highLighter(locator);
        log.debug("jsClick: " + locator.toString());
        try {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException e) {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * This control simulates click into an element using javascript executor.
     * @param element The locating mechanism.
     */
    public void jsClick(WebElement element) {
        log.debug("jsClick: " + element.toString());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    //==================================================================================================================
    // Dropdown
    //==================================================================================================================
    /**
     * This control simulates dropdown selection of the value in a dropdown option locator.
     * @param locator The locating mechanism.
     * @param text The text to be selected on a the option locator.
     * @param maxWaitTime The configurable max wait counter.
     * @throws Exception
     */
    public void selectDropdownOption(By locator, String text, int maxWaitTime) throws Exception {
        highLighter(locator);
        log.debug("selectDropdownOption: " + locator.toString() + " " + text + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElement(locator, maxWaitTime);
        boolean isElementFound = false;
        Select select = new Select(element);

        List<WebElement> allOptions = select.getOptions();
        for (WebElement option : allOptions) {
            if (option.getText().trim().replace("<br> ", "").replace("<br>", "").equalsIgnoreCase(text)) {
                isElementFound = true;
                select.selectByVisibleText(option.getText());
                break;
            }
        }

        if (!isElementFound) {
            log.error(locator.toString() + " - Select option [" + text + "] is not found." + "\n");
            throw new Exception("ERROR:: Select option [" + text + "] is not found.");
        }
    }

    /**
     * This control simulates dropdown selection containing the text in a dropdown option locator.
     * @param locator The locating mechanism.
     * @param text The text to be selected on a the option locator.
     * @throws Exception
     */
    public void selectDropdownContains(By locator, String text, int maxWaitTime) throws Exception {
        highLighter(locator);
        log.debug("selectDropdownOption: " + locator.toString() + " " + text + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElement(locator, maxWaitTime);
        boolean isElementFound = false;
        Select select = new Select(element);

        List<WebElement> allOptions = select.getOptions();
        for (WebElement option : allOptions) {
            if (option.getText().trim().replace("<br> ", "").replace("<br>", "").contains(text)) {
                isElementFound = true;
                select.selectByVisibleText(option.getText());
                break;
            }
        }

        if (!isElementFound) {
            log.error(locator.toString() + " - Select option [" + text + "] is not found." + "\n");
            throw new Exception("ERROR:: Select option [" + text + "] is not found.");
        }
    }

    /**
     * This control selects from dropdown option based on the value attribute.
     * @param locator The locating mechanism.
     * @param value The value to be selected on a the option locator.
     * @throws Exception
     */
    public void selectDropdownOptionValue(By locator, String value) throws Exception {
        highLighter(locator);
        log.debug("selectDropdownOptionValue: " + locator.toString() + " " + value + " maxWaitTime: " + DEFAULT_WAIT_TIME);
        WebElement element = findElement(locator, DEFAULT_WAIT_TIME);
        boolean isElementFound = false;
        Select select = new Select(element);
        select.selectByValue(value);
    }

    /**
     * This control retrieves the value of the selected option locator.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return The String value of the selected dropdown option.
     */
    public String getDropdownOptionSelected(By locator, int maxWaitTime) {
        highLighter(locator);
        log.debug("getDropdownOptionSelected: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElement(locator, maxWaitTime);
        List<WebElement> options = element.findElements(By.tagName("option"));

        for (WebElement option : options) {
            if (!option.getAttribute("selected").isEmpty()) {
                return option.getText();
            }
        }
        return null;
    }

    /**
     * This control retrieves the value of the selected text of the locator.
     * @param locator The locating mechanism.
     * @return The String value of the selected dropdown text.
     */
    public String getDropdownSelectedText(By locator) {
        highLighter(locator);
        log.debug("getDropdownSelectedText: " + locator.toString());
        WebElement element = findElement(locator);
        Select select = new Select(element);
        WebElement selectedText = select.getFirstSelectedOption();

        return selectedText.getText();
    }

    /**
     * This control selects the value in a dropdown selection locator based on the text.
     * @param locator The locating mechanism.
     * @param text The value item value to be clicked.
     */
    public void selectDropdownItem(By locator, String text) {
        highLighter(locator);
        log.debug("selectDropdownItem: " + locator.toString() + " " + text);
        WebElement element = findElement(locator).findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        click(element);
    }

    /**
     * This control selects the value in a dropdown selection locator based on the linkText.
     * @param locator The locating mechanism.
     * @param linkText
     * @param maxWaitTime The configurable max wait counter.
     */
    public void selectDropdownItemLink(By locator, String linkText, int maxWaitTime) throws Exception {
        highLighter(locator);
        log.debug("selectDropdownItemLink: " + locator.toString() + " " + linkText + " maxWaitTime: " + maxWaitTime);
        click(locator, maxWaitTime);
        Thread.sleep(500);
        this.waitForTextToBePresent(locator, linkText, 1);
        findElement(By.linkText(linkText)).click();
    }


    //==================================================================================================================
    // Checkboxes
    //==================================================================================================================
    /**
     * This control clicks the checkbox based on the text value provided.
     * @param text
     * @param maxWaitTime The configurable max wait counter.
     */
    public void clickCheckboxByTextValue(String text, int maxWaitTime) throws Exception {
        log.debug("clickCheckboxByTextValue: " + text + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElement(By.xpath("//label[contains(text(),'" + text + "')]//preceding::input[@type='checkbox'][1]"), maxWaitTime);
        click(element);
    }

    /**
     * This control checks if a checkbox locator is marked/ticked.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return True or False based on attribute value "checked".
     */
    public boolean isCheckboxChecked(By locator, int maxWaitTime) {
        log.debug("isCheckboxChecked: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        if (isAttributePresent(locator, "checked", maxWaitTime)) {
            if (getAttributeValue(locator, "checked", maxWaitTime).equalsIgnoreCase("true")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (findElementDisplayed(locator, maxWaitTime).isSelected()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * This control ticks all checkboxes of the locator.
     * @param locator The locating mechanism.
     * @param loader The loader locator.
     * @param maxWaitTime The configurable max wait counter.
     */
    public void clickAllCheckboxes(By locator, By loader, int maxWaitTime) {
        log.debug("clickAllCheckboxes: " + locator.toString() + " " + loader.toString() + " maxWaitTime: " + maxWaitTime);
        WebElement container = findElement(locator, maxWaitTime);
        List<WebElement> elements = container.findElements(By.xpath("//input[@type='checkbox']"));

        for (WebElement element : elements) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 15);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
            } catch (Exception e) {
            }
            if (!element.isSelected()) {
                element.click();
            }
        }
    }

    //==================================================================================================================
    // Common for Elements
    //==================================================================================================================
    /**
     * This control sets focus on the locator.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     */
    public void focusElement(By locator, int maxWaitTime) throws Exception {
        highLighter(locator);
        try {
            WebElement container = findElementDirect(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -200);", container);
        } catch (NoSuchElementException e) {
            throw new Exception(new NoSuchElementException("Cannot focus element. Element is not found\n"));
        } catch (StaleElementReferenceException se) {
            WebElement container = findElementDirect(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -200);", container);
        }
        unhighLighter(locator);
    }

    /**
     * This control sets focus on the locator.
     * @param element The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     */
    public void focusElement(WebElement element, int maxWaitTime) throws Exception {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -200);", element);
        } catch (NoSuchElementException e) {
            throw new Exception(new NoSuchElementException("Cannot focus element. Element is not found\n"));
        }

    }

    /**
     * This control checks if the element is present within DOM.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return True if the element is present, otherwise False.
     */
    public boolean isElementPresent(By locator, int maxWaitTime) {
        log.debug("isElementPresent: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        if (findElementPresent(locator, maxWaitTime) == null) {
            return false;
        }
        return true;
    }

    /**
     * This control checks if the element is displayed within DOM.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return True if the element is present and displayed, otherwise False.
     */
    public boolean isElementDisplayed(By locator, int maxWaitTime) {
        log.debug("isElementDisplayed: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        if (findElementDisplayed(locator, maxWaitTime) == null) {
            return false;
        }
        return true;
    }

    /**
     * This control checks if the element is displayed within DOM.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @param errorMessage The message to be displayed if element is not displayed.
     * @return True if the element is present and displayed, otherwise False.
     */
    public boolean isElementDisplayed(By locator, int maxWaitTime, String errorMessage) {
        log.debug("isElementDisplayed: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        if (findElementDisplayed(locator, maxWaitTime) == null) {
            System.out.println(errorMessage);
            return false;
        }
        return true;
    }

    /**
     * This control retrieves the text in locator.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return The String text value retrieved from the locator.
     */
    public String getText(By locator, int maxWaitTime) {
        log.debug("getText: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        try {
            WebElement element = findElementPresent(locator, maxWaitTime);
            //TODO
            if (element == null) {
                log.error(locator.toString() + " is null." + "\n");
                return null;
            }
            return element.getText();
        } catch (StaleElementReferenceException s) {
            WebElement element = findElementPresent(locator, maxWaitTime);
            return element.getText();
        }
    }

    /**
     * This control gets the value of the attribute "value" from the locator.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return The String value retrieved from the attribute of locator.
     */
    public String getValue(By locator, int maxWaitTime) {
        return getAttributeValue(locator, "value", maxWaitTime);
    }

    /**
     * This control retrieves the element value of the locator based on the text.
     * @param locator The locating mechanism.
     * @param text The text being compared to.
     * @return The WebElement retrieved from the locator equal to the String text parameter.
     */
    public WebElement getElement(By locator, String text) throws Exception {
        try {
            for (WebElement element : driver.findElements(locator)) {
                if (element.getText().trim().equalsIgnoreCase(text)) {
                    return element;
                }
            }
        } catch (StaleElementReferenceException e) {
            this.getElement(locator, text);
        }
        throw new Exception("[EXCEPTION]: No element found in the WebElement list with text of " + text + ".");
    }


    //==================================================================================================================
    // Finding Elements
    //==================================================================================================================
    /**
     * This control finds the first matching element on the page.
     * @param locator The locating mechanism.
     * @return WebElement matching the locator.
     */
    public WebElement findElement(By locator) {
        return findElement(locator, 0);
    }

    /**
     * This control finds and returns the first matching element on the page.
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
     * This control finds and returns the element present and visible in the DOM of a page.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return WebElement displayed matching the locator.
     */
    public WebElement findElementDisplayed(By locator, int maxWaitTime) {
        log.debug("findElementDisplayed: " + locator.toString() + " maxWaitTime: " + maxWaitTime);

        WebElement element = null;
        if (driver == null) {
            log.error("driver is null." + "\n");
            throw new NullPointerException("ERROR: driver is null.");
        }
        if (isElementDisplayedWithinWait(locator, maxWaitTime)) {
            element = driver.findElement(locator);
        }
        return element;
    }

    /**
     * This control finds and returns the element present in the DOM of a page even if it is not visible.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return WebElement present matching the locator.
     */
    public WebElement findElementPresent(By locator, int maxWaitTime) {
        log.debug("findElementPresent: " + locator.toString() + "maxWaitTime: " + maxWaitTime);

        WebElement element = null;
        if (driver == null) {
            log.error("driver is null." + "\n");
            throw new NullPointerException("ERROR: driver is null.");
        }
        if (isElementPresentWithinWait(locator, maxWaitTime)) {
            element = driver.findElement(locator);
        }
        return element;
    }

    /**
     * This control finds and returns the complete WebElement based on the locator.
     * @param locator The locating mechanism.
     * @return WebElement matching the locator.
     */
    public WebElement findElementDirect(By locator) {

        if (driver == null) {
            log.error("driver is null." + "\n");
            throw new NullPointerException("ERROR: driver is null.");
        }
        return driver.findElement(locator);
    }


    //==================================================================================================================
    // Find Element by Locator
    //==================================================================================================================
    /**
     * This control finds and returns the element by CSS present in the DOM of a page.
     * @param elementName The locator value.
     * @param maxWaitTime The configurable max wait counter.
     * @return WebElement matching the CSS locator.
     */
    public WebElement findElementByCss(final String elementName, int maxWaitTime) {
        log.debug("findElementByCss: " + elementName + " maxWaitTime: " + maxWaitTime);
        return findElement(By.cssSelector(elementName), maxWaitTime);
    }

    /**
     * This control finds and returns the element by ID present in the DOM of a page.
     * @param elementName The locator value.
     * @param maxWaitTime The configurable max wait counter.
     * @return WebElement matching the ID locator.
     */
    public WebElement findElementById(final String elementName, int maxWaitTime) {
        log.debug("findElementById: " + elementName + " maxWaitTime: " + maxWaitTime);
        return findElement(By.id(elementName), maxWaitTime);
    }

    /**
     * This control finds and returns the element by link text present in the DOM of a page.
     * @param elementName The locator value.
     * @param maxWaitTime The configurable max wait counter.
     * @return WebElement matching the Link Text locator.
     */
    public WebElement findElementByLinkText(final String elementName, int maxWaitTime) {
        log.debug("findElementByLinkText: " + elementName + " maxWaitTime: " + maxWaitTime);
        return findElement(By.linkText(elementName), maxWaitTime);
    }

    /**
     * This control finds and returns the element by containing the text present in the DOM of a page.
     * @param container The locator container.
     * @param text The text to be searched.
     * @return WebElement matching the text within the element container.
     */
    public WebElement findElementByText(WebElement container, String text) {
        log.debug("findElementByText: " + container.toString() + " " + text);
        return container.findElement(By.xpath(".//*[contains(text(),'" + text + "')]"));
    }

    //==================================================================================================================
    // Wait, Synchronization
    //==================================================================================================================
    /**
     * This control waits to satisfy the specified condition within the max wait counter set.
     * @param condition The condition to satisfy before max wait counter lapses.
     * @param maxWaitTime The configurable max wait counter.
     */
    private void waitFor(ExpectedCondition<WebElement> condition, int maxWaitTime) {
        log.debug("waitFor: " + condition + " maxWaitTime: " + maxWaitTime);
        WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
        wait.until(condition);
    }

    /**
     * This control waits to satisfy the specified boolean condition within the max wait counter set.
     * @param condition The boolean condition to satisfy before max wait counter lapses.
     * @param maxWaitTime The configurable max wait counter.
     */
    private void waitForBooleanCondition(ExpectedCondition<Boolean> condition, int maxWaitTime) {
        log.debug("waitForBooleanCondition: " + condition + " maxWaitTime: " + maxWaitTime);
        WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
        wait.until(condition);
    }

    /**
     * This control waits until the locator becomes clickable within the max wait counter set.
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

    /**
     * This control waits until the element becomes clickable within the max wait counter set.
     * @param element The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return True if the element is clickable otherwise, False.
     */
    public boolean isElementClickableWithinWait(WebElement element, int maxWaitTime) {
        log.debug("isElementClickableWithinWait: " + element.toString() + " maxWaitTime: " + maxWaitTime);
        try {
            waitFor(ExpectedConditions.elementToBeClickable(element), maxWaitTime);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    /**
     * This control waits until the locator gets displayed within the max wait counter set.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return True if the element is displayed otherwise, False.
     */
    public boolean isElementDisplayedWithinWait(By locator, int maxWaitTime) {
        log.debug("isElementDisplayedWithinWait: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator), maxWaitTime);
        } catch (TimeoutException e) {
//            log.debug("Timeout Exception: " + locator "\n");
            return false;
        }
        return true;
    }

    /**
     * This control waits until the locator becomes present within the max wait counter set.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max wait counter.
     * @return True if the element is present otherwise, False.
     */
    public boolean isElementPresentWithinWait(By locator, int maxWaitTime) {
        log.debug("isElementPresentWithinWait: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        try {
            waitFor(ExpectedConditions.presenceOfElementLocated(locator), maxWaitTime);
        } catch (TimeoutException e) {
//            log.debug("Timeout Exception: " + locator + "\n");
            return false;
        }
        return true;
    }

    /**
     * This control waits until the return document.readyState is complete.
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

    /**
     * This control waits until the locator becomes present within the max wait counter set and displays the error message set once max counter ends.
     * @param locator The locating mechanism.
     * @param counter The configurable max counter.
     * @param errorMessage The message displayed once max wait counter lapses.
     * @throws Exception
     */
    public void waitWhileElementIsNotPresent(By locator, Integer counter, String errorMessage) throws Exception {
        int x = 0;
        while (!this.isElementPresent(locator, 1)) {
            System.out.println("DEBUG: Waiting - element \"" + locator + "\" is still not displayed... \n");
            Thread.sleep(1000);
            x++;
            if (x > counter) {
                throw new Exception(errorMessage);
            }
        }
    }

    /**
     * This control waits until the locator gets displayed within the max wait counter set and displays the error message set once max counter ends.
     * @param locator The locating mechanism.
     * @param counter The configurable max counter.
     * @param errorMessage The message displayed once max wait counter lapses.
     * @throws Exception
     */
    public void waitWhileElementIsNotDisplayed(By locator, Integer counter, String errorMessage) throws Exception {
        int x = 0;
        while (!this.isElementDisplayed(locator, 1)) {
            System.out.println("DEBUG: Waiting - element \"" + locator + "\" is still not displayed... \n");
            Thread.sleep(1000);
            x++;
            if (x > counter) {
                throw new Exception(errorMessage);
            }
        }
    }

    /**
     * This control waits until the locator gets displayed within the max wait counter set.
     * @param locator The locating mechanism.
     * @param counter The configurable max counter.
     * @throws Exception
     */
    public void waitWhileElementIsNotDisplayed(By locator, Integer counter) throws Exception {
        int x = 0;
        while (!this.isElementDisplayed(locator, 1)) {
            System.out.println("DEBUG: Waiting - element \"" + locator + "\" is still not displayed... \n");
            Thread.sleep(1000);
            x++;
            if (x > counter) {
                System.out.println("ERROR: Element takes too long to load.. \n");
                return;
            }
        }
    }

    /**
     * This control waits while the locator is displayed within the max wait counter set.
     * @param locator The locating mechanism.
     * @param counter The configurable max counter.
     * @param errorMessage The message displayed once max wait counter lapses.
     * @throws Exception
     */
    public void waitWhileElementIsDisplayed(By locator, Integer counter, String errorMessage) throws Exception {
        try {
            int x = 0;
            while (this.isElementDisplayed(locator, 1)) {
                Thread.sleep(1000);
                System.out.println("DEBUG: Waiting - element \"" + locator + "\" is still displayed... \n");
                x++;
                if (x > counter) {
                    throw new Exception(errorMessage);
                }
            }
        } catch (NoSuchElementException e) {

        }

    }

    /**
     * This control waits while the locator is displayed within the max wait counter set.
     * @param locator The locating mechanism.
     * @param counter The configurable max counter.
     * @throws Exception
     */
    public void waitWhileElementIsDisplayed(By locator, Integer counter) throws Exception {
        try {
            int x = 0;
            while (this.isElementDisplayed(locator, 1)) {
                Thread.sleep(1000);
                System.out.println("DEBUG: Waiting - element \"" + locator + "\" is still displayed... \n");
                x++;
                if (x > counter) {
                    System.out.println("ERROR: Element takes too long to load");
                    return;
                }
            }
        } catch (NoSuchElementException e) {

        }

    }

    /**
     * This control waits while the locator is not clickable within the max wait counter set.
     * @param locator The locating mechanism.
     * @param counter The configurable max counter.
     * @throws Exception
     */
    public void waitWhileElementIsNotClickable(By locator, Integer counter) throws Exception {
        int x = 0;
        while (!this.isElementClickableWithinWait(locator, 3)) {
            System.out.println("DEBUG: Waiting - element \"" + locator + "\" is still not clickable... \n");
            Thread.sleep(1000);
            x++;
            if (x > counter) {
                System.out.println("ERROR: Element is not clickable. \n");
                return;
            }
        }
    }

    /**
     * This control waits while the window size is not equal to the size set within the max wait counter set.
     * @param size The expected size of the window.
     * @param counter The configurable max counter.
     * @throws Exception
     */
    public void waitWindowSize(int size, int counter) throws Exception {
        int x = 0;
        while (driver.getWindowHandles().size() != size) {
            Thread.sleep(1000);
            System.out.println("Waiting for window size to become: " + size + "...");
            x++;
            if (x > counter) {
                throw new Exception("ERROR: Window size is not " + size);
            }
        }
    }

    /**
     * This control returns the window size
     * @throws Exception
     */
    public int getWindowSize() throws Exception {
        return driver.getWindowHandles().size();
    }

    /**
     * This control waits until the element becomes invisible within the max wait counter set.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max counter.
     * @return True if the element is invisible otherwise, False.
     */
    public boolean isElementInvisibleWithinWait(By locator, int maxWaitTime) {
        try {
            log.debug("isElementInvisibleWithinWait: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
            waitForBooleanCondition(ExpectedConditions.invisibilityOfElementLocated(locator), maxWaitTime);
        } catch (TimeoutException e) {
//            log.debug("Timeout Exception: " + locator + "\n");
            return false;
        }
        return true;
    }

    //==================================================================================================================
    // Text Present: Wait, Synchronization
    //==================================================================================================================
    /**
     * This control waits until the element becomes present within the max wait counter set.
     * @param element The locating mechanism.
     * @param text The text being evaluated.
     * @param maxWaitTime The configurable max counter.
     * @return True if the text becomes present otherwise, False.
     */
    public boolean waitForTextToBePresent(WebElement element, String text, int maxWaitTime) {
        log.debug("waitForTextToBePresent: " + element.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * This control waits until the element becomes present within the max wait counter set.
     * @param locator The locating mechanism.
     * @param text The text being evaluated.
     * @param maxWaitTime The configurable max counter.
     * @return True if the text becomes present otherwise, False.
     */
    public boolean waitForTextToBePresent(By locator, String text, int maxWaitTime) {
        log.debug("waitForTextToBePresent: " + locator.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    //==================================================================================================================
    // Attributes
    //==================================================================================================================

    /**
     * This control retrieves the attribute value.
     * @param locator The locating mechanism.
     * @param attributeName The attribute name.
     * @param maxWaitTime The configurable max counter.
     * @return The String value of the attribute.
     */


    public String getAttributeValue(By locator, String attributeName, int maxWaitTime) {
        log.debug("getAttributeValue: " + locator.toString() + "attributeName: " + attributeName + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElementPresent(locator, maxWaitTime);
        try {
            if (element == null) {
                log.error(locator.toString() + " is null." + "\n");
                return null;
            }
            return element.getAttribute(attributeName);
        } catch (StaleElementReferenceException s) {
            element = findElementPresent(locator, maxWaitTime);
            if (element == null) {
                log.error(locator.toString() + " is null." + "\n");
                return null;
            }
            return element.getAttribute(attributeName);
        }

    }

    /**
     * This control checks if the attribute is present based on the locator.
     * @param locator The locating mechanism.
     * @param attributeName The attribute name.
     * @param maxWaitTime The configurable max counter.
     * @return True if the attribute is present otherwise, False.
     */
    public boolean isAttributePresent(By locator, String attributeName, int maxWaitTime) {
        log.debug("isAttributePresent: " + locator.toString() + " attributeName: " + attributeName + " maxWaitTime: " + maxWaitTime);
        boolean result = false;
        try {
            String value = getAttributeValue(locator, attributeName, maxWaitTime);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    /**
     * This control retrieves the CSS value based on the attribute of the locator.
     * @param locator The locating mechanism.
     * @param attributeName The attribute name.
     * @param maxWaitTime The configurable max counter.
     * @return The String CSS value.
     */
    public String getCSSValue(By locator, String attributeName, int maxWaitTime) {
        log.debug("getCSSValue: " + locator.toString() + "attributeName: " + attributeName + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElementPresent(locator, maxWaitTime);
        if (element == null) {
            log.error(locator.toString() + " is null." + "\n");
            return null;
        }
        return element.getCssValue(attributeName);
    }

    /**
     * This control retrieves the tag name value based on the locator.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max counter.
     * @return The String tag name value.
     */
    public String getTagName(By locator, int maxWaitTime) {
        log.debug("getTagName: " + locator.toString() + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElementPresent(locator, maxWaitTime);
        if (element == null) {
            log.error(locator.toString() + " is null." + "\n");
            return null;
        }
        return element.getTagName();
    }

    /**
     * This control retrieves the InnerHTML value from the attribute value.
     * @param locator The locating mechanism.
     * @param maxWaitTime The configurable max counter.
     * @return The String value of the attribute.
     */

    public String getAttributeInnerHTMLByCSSSelector(By locator,int maxWaitTime){
        log.debug("locator: " + locator + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElementPresent(locator, maxWaitTime);
        try {
            if (element == null) {
                log.error(locator.toString() + " is null." + "\n");
                return null;
            }
        } catch (StaleElementReferenceException s) {
            element = findElementPresent(locator, maxWaitTime);
            if (element == null) {
                log.error(locator.toString() + " is null." + "\n");
                return null;
            }
        }
        String innerHTMLText = driver.findElement(By.cssSelector(getStringFromByVariable(locator))).getAttribute("innerHTML");
        return innerHTMLText;
    }

    /**
     * Helper method in order to get selector from full String.
     */

    public String getStringFromByVariable(By givenByVariable) {
        String stringToReturn = "";
        Matcher m = Pattern.compile(" .*$").matcher(givenByVariable.toString());
        while(m.find()) {
            stringToReturn = stringToReturn.concat(m.group(0).trim());
        }
        return stringToReturn;
    }
    //==================================================================================================================
    // Sub-Elements
    //==================================================================================================================
    /**
     * This control retrieves the Sub Element based on the class name of the locator.
     * @param locator The locating mechanism.
     * @param className The class name to be search.
     * @param maxWaitTime The configurable max counter.
     * @return WebElement value based on the locator class name
     */
    public WebElement findSubElementByClassName(By locator, String className, int maxWaitTime) {
        log.debug("findSubElementByClassName: " + locator.toString() + " className: " + className + " maxWaitTime: " + maxWaitTime);
        WebElement container = findElement(locator, maxWaitTime);
        return container.findElement(By.xpath(".//*[contains(@class,'" + className + "')]"));
    }

    /**
     * This control retrieves the Sub Element based on the class name of the locator.
     * @param element The locating mechanism.
     * @param className The class name to be search.
     * @param maxWaitTime The configurable max counter.
     * @return WebElement value based on the locator class name
     */
    public WebElement findSubElementByClassName(WebElement element, String className, int maxWaitTime) {
        log.debug("findSubElementByClassName: " + element.toString() + " className: " + className + " maxWaitTime: " + maxWaitTime);
        return element.findElement(By.xpath(".//*[contains(@class,'" + className + "')]"));
    }

    /**
     * This control clicks the Sub Element based on the class name of the locator.
     * @param locator The locating mechanism.
     * @param className The class name to be search.
     * @param maxWaitTime The configurable max counter.
     */
    public void clickSubElementByClassName(By locator, String className, int maxWaitTime) {
        log.debug("clickSubElementByClassName: " + locator.toString() + " className: " + className + " maxWaitTime: " + maxWaitTime);
        findSubElementByClassName(locator, className, maxWaitTime).click();
    }


    //==================================================================================================================
    // Sub-Elements: click
    //==================================================================================================================
    /**
     * This control clicks the text inside the locator container.
     * @param locator The locating mechanism.
     * @param text The text to be clicked.
     * @param maxWaitTime The configurable max counter.
     */
    public void clickTextInside(By locator, String text, int maxWaitTime) {
        highLighter(locator);
        log.debug("clickTextInside: " + locator.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebElement container = findElement(locator, maxWaitTime);
        container.findElement(By.xpath(".//*[contains(text(),'" + text + "')]")).click();
    }

    /**
     * This control clicks the text inside the locator container using Javascript Executor.
     * @param locator The locating mechanism.
     * @param text The text to be clicked.
     * @param maxWaitTime The configurable max counter.
     */
    public void jsClickTextInside(By locator, String text, int maxWaitTime) {
        highLighter(locator);
        log.debug("jsClickTextInside: " + locator.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebElement container = findElement(locator, maxWaitTime);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", container.findElement(By.xpath(".//*[contains(text(),'" + text + "')]")));
    }

    /**
     * This control clicks the text inside the locator container.
     * @param locator The locating mechanism.
     * @param text The text to be clicked.
     * @param maxWaitTime The configurable max counter.
     */
    public void actionClickTextInside(By locator, String text, int maxWaitTime) throws Exception {
        highLighter(locator);
        log.debug("clickTextInside: " + locator.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebElement container = findElement(locator, maxWaitTime);
        actionClick(By.xpath(".//*[contains(text(),'" + text + "')]"));
    }


    /**
     * This control clicks the exact text inside the locator.
     * @param locator The locating mechanism.
     * @param text The text to be clicked.
     */
    public void clickExactTextInside(By locator, String text) {
        highLighter(locator);
        log.debug("clickTextInside: " + locator.toString() + " " + text);
        clickExactTextInside(locator, text, DEFAULT_WAIT_TIME);
    }

    /**
     * This control clicks the exact text inside the locator within max wait counter.
     * @param locator The locating mechanism.
     * @param text The text to be clicked.
     */
    public void clickExactTextInside(By locator, String text, int maxWaitTime) {
        highLighter(locator);
        log.debug("clickTextInside: " + locator.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebElement container = findElement(locator, maxWaitTime);
        container.findElement(By.xpath(".//*[text()='" + text + "']")).click();
    }

    /**
     * This control clicks the text inside the locator container ignoring case sensitivity.
     * @param locator The locating mechanism.
     * @param text The text to be clicked.
     * @param maxWaitTime The configurable max counter.
     */
    public void clickTextInsideIgnoreCase(By locator, String text, int maxWaitTime) {
        highLighter(locator);
        log.debug("clickTextInsideIgnoreCase: " + locator.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebElement container = findElement(locator, maxWaitTime);
        //container.findElement(By.xpath(".//*[contains(lower-case(text()),'" + text + "')]")).click();
        container.findElement(By.xpath(".//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + text + "')]")).click();
    }

    /**
     * This control clicks the link text inside the locator container.
     * @param locator The locating mechanism.
     * @param text The text to be clicked.
     * @param maxWaitTime The configurable max counter.
     */
    public void clickLinkTextInside(By locator, String text, int maxWaitTime) {
        highLighter(locator);
        log.debug("clickLinkTextInside: " + locator.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebElement container = findElement(locator, maxWaitTime);
        container.findElement(By.linkText(text)).click();
    }

    /**
     * This control clicks the link text inside the locator container.
     * @param element The locating mechanism.
     * @param text The text to be clicked.
     */
    public void clickLinkTextInside(WebElement element, String text) {
        log.debug("clickLinkTextInside: " + element.toString() + " text: " + text);
        element.findElement(By.linkText(text)).click();
    }

    /**
     * This control retrieves the text inside the locator container.
     * @param locator The locating mechanism.
     * @param text The text to be clicked.
     * @param maxWaitTime The configurable max counter.
     */
    public WebElement getTextInside(By locator, String text, int maxWaitTime) {
        highLighter(locator);
        log.debug("getTextInside: " + locator.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebElement container = findElement(locator, maxWaitTime);
        return container.findElement(By.xpath(".//*[contains(text(),'" + text + "')]"));
    }
    //==================================================================================================================
    // Container methods
    //==================================================================================================================
    /**
     * This control checks if the locator container has the text.
     * @param locator The locating mechanism.
     * @param text The text to be checked.
     * @param maxWaitTime The configurable max counter.
     */
    public boolean hasText(By locator, String text, int maxWaitTime) {
        highLighter(locator);
        log.debug("hasText: " + locator.toString() + " text: " + text + " maxWaitTime: " + maxWaitTime);
        WebElement element = findElementDisplayed(locator, maxWaitTime);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return element.getText().contains(text);
    }

    /**
     * This control checks if the locator container has the text.
     * @param locator The locating mechanism.
     * @param text The text to be checked.
     */
    public boolean hasText(By locator, String text) {
        highLighter(locator);
        log.debug("hasText: " + locator.toString() + " text: " + text + " maxWaitTime: " + DEFAULT_WAIT_TIME);
        WebElement element = findElementDisplayed(locator, DEFAULT_WAIT_TIME);
        return element.getText().contains(text);
    }

    //==================================================================================================================
    // Hover / Mouse Over methods
    //==================================================================================================================
    /**
     * This control simulates mouse over the element.
     * @param locator The locating mechanism.
     */
    public void hoverToElement(By locator) {
        highLighter(locator);
        log.debug("hoverToElement: " + locator.toString());
        WebElement element = findElement(locator);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    /**
     * This control simulates mouse over the element.
     * @param locator The locating mechanism.
     */
    public void hoverToElement(WebElement locator) {
        log.debug("hoverToElement: " + locator);
        Actions action = new Actions(driver);
        action.moveToElement(locator).build().perform();
    }

    /**
     * This control simulates mouse over the specifc text in an element.
     * @param locator The locating mechanism.
     * @param text The text to focus hover.
     */
    public void hoverToText(By locator, String text) {
        highLighter(locator);
        log.debug("hovertoText: " + locator + " text: " + text);
        WebElement container = findElement(locator);
        WebElement element = findElementByText(container, text);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    //==================================================================================================================
    // iFrame methods
    //==================================================================================================================
    /**
     * This control switches from one iframe to another.
     * @param locator The locating mechanism.
     */
    public void switchToIframe(By locator) {
        log.debug("switchToIframe: " + locator.toString());
        WebElement iFrame = findElement(locator);
        driver.switchTo().frame(iFrame);
    }

    /**
     * This control switches back to default iframe.
     */
    public void switchToDefaultFrame() {
        log.debug("switchToDefaultFrame");
        driver.switchTo().defaultContent();
    }

    /**
     * This control retrieves the parent xpath including the current locator set
     * @param childElement The child locating mechanism.
     * @param current The current xpath locating mechanism.
     * @return String value based on the complete xpath excerpt including the parent, child and current xpath.
     */
    public String generateXPATH(WebElement childElement, String current) {
        String childTag = childElement.getTagName();
        if (childTag.equals("html")) {
            return "/html[1]" + current;
        }
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for (int i = 0; i < childrenElements.size(); i++) {
            WebElement childrenElement = childrenElements.get(i);
            String childrenElementTag = childrenElement.getTagName();
            if (childTag.equals(childrenElementTag)) {
                count++;
            }
            if (childElement.equals(childrenElement)) {
                return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
            }
        }
        return null;
    }

    /**
     * This control checks if the current URL matches the expected URL parameter.
     * @param url The expected matching URL.
     * @return True if the url matches the retrieved URL otherwise, False.
     */
    public boolean isUrlCorrect(String url) {
        return driver.getCurrentUrl().toLowerCase().equals(url.toLowerCase());
    }

    public boolean isUrlEqual(String url) {
        return driver.getCurrentUrl().equals(url);
    }

    public boolean urlContains(String url, String product) {
        if (product.toUpperCase().contains("FANTASY")) {
            return driver.getCurrentUrl().contains(url);
        } else if (product.toUpperCase().contains("ESPORTS")) {
            return driver.getCurrentUrl().contains(url);
        }
        return driver.getCurrentUrl().contains(url);
    }

    /**
     * This control retrieves the current date/time with the format provided.
     * @param format The format of the captured date/time.
     * @return String value of the retrieved date/time
     */
    public String getCurrentDateTime(String format) {
        // sample format "yyyyMMdd-HHmm"
        // sample format "yyyyMMdd HH:mm"
        // sample format "ddMMyyyy HH:mm:ss"
        String curDateTime = "";
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        curDateTime = dateFormat.format(date);
        System.out.println("Current date time: " + curDateTime + "\n");
        return curDateTime;
    }

    /**
     * This control formats formats current Date Time and has option to add value on current date time.
     * @param format The current format of the date/time value.
     * @param option The part of the date time to be added value from.
     * Options:
     * *YEAR
     * *MONTH
     * *DATE
     * *HOUR
     * *MINUTE
     * *SECOND
     * @param amountToBeAddedDeducted The positive / negative number value.
     * @return String value of the formatted date/time.
     */
    public String addMinusToCurrentDateTime(String format, String option, int amountToBeAddedDeducted) throws Exception {

        DateFormat dateFormat = new SimpleDateFormat(format);

        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        switch (option.toUpperCase()) {
            case "YEAR":
                calendar.add(Calendar.YEAR, amountToBeAddedDeducted);
                break;
            case "MONTH":
                calendar.add(Calendar.MONTH, amountToBeAddedDeducted);
                break;
            case "DATE":
                calendar.add(Calendar.DATE, amountToBeAddedDeducted); //same with c.add(Calendar.DAY_OF_MONTH, 1);
                break;
            case "HOUR":
                calendar.add(Calendar.HOUR, amountToBeAddedDeducted);
                break;
            case "MINUTE":
                calendar.add(Calendar.MINUTE, amountToBeAddedDeducted);
                break;
            case "SECOND":
                calendar.add(Calendar.SECOND, amountToBeAddedDeducted);
                break;
            case "TODAY":
                return dateFormat.format(currentDate);
            default: throw new Exception("ERROR: Mapping for " + option + " date option is not available.\n");
        }

        Date currentDatePlusMinus= calendar.getTime();
        return dateFormat.format(currentDatePlusMinus);
    }

    /**
     * This control formats date/time.
     * @param dateTimeValue The date/time value to be formatted.
     * @param fromFormat The current format of the date/time value.
     * @param toFormat The expected format.
     * @return String value of the formatted date/time.
     */
    public String convertDateTime(String dateTimeValue, String fromFormat, String toFormat) throws Exception {

        //***** toFormat should be the current format of the dateTimeValue *****

        SimpleDateFormat parseFormat = new SimpleDateFormat(fromFormat);
        SimpleDateFormat displayFormat = new SimpleDateFormat(toFormat);
        Date date = parseFormat.parse(dateTimeValue);
        System.out.println("DEBUG: " + parseFormat.format(date) + " > " + displayFormat.format(date));

        return displayFormat.format(date);
    }

    //==================================================================================================================
    // Format Decimal
    //==================================================================================================================
    /**
     * This control formats float values to decimal.
     * @param value The value to be formatted.
     * @param decimalFormat The expected format.
     * @return String value of the formatted decimal value.
     */
    public String formatDecimal(float value, String decimalFormat) {
        DecimalFormat decimal = new DecimalFormat(decimalFormat);
        return decimal.format(value);
    }

    public String formatDecimal(double value, String decimalFormat) {
        DecimalFormat decimal = new DecimalFormat(decimalFormat);
        return decimal.format(value);
    }

    //==================================================================================================================
    // Scroll
    //==================================================================================================================
    /**
     * This control scrolls to specific element.
     * @param locator The locating mechanism.
     */
    public void scrollToElement(By locator) {
        WebElement element = findElementPresent(locator, 5);
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    public void scrollToElement(WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    //==================================================================================================================
    // Sorting
    //==================================================================================================================
    /**
     * This control checks if the list is sorted as expected.
     * @param arraylist The list to be checked.
     * @return True if the list is correctly sorted otherwise, False.
     */
    public boolean checkSorting(ArrayList<String> arraylist) {
        boolean isSorted = true;
        for (int i = 1; i < arraylist.size(); i++) {
            if (arraylist.get(i - 1).compareTo(arraylist.get(i)) > 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    //==================================================================================================================
    // Shadow Root
    //==================================================================================================================
    /**
     * This control expands the root elements.
     * @param element The locating mechanism.
     * @return The expanded root element.
     */
    public WebElement expandRootElement(WebElement element) {
        WebElement ele = (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }
    //==================================================================================================================
    // Add to Local Storage
    //==================================================================================================================
    /**
     * This control adds the specific value to the Applications Local Storage under Developer Tool in browser (F12).
     * @param key The key to value to be added.
     * @param value The value of the key to be added.
     */
    public void addToApplicationsLocalStorage(String key, String value) throws InterruptedException {
        JavascriptExecutor js;
        js = (JavascriptExecutor) driver;
        js.executeScript(String.format(
                "window.localStorage.setItem('%s','%s');", key, value));
        Thread.sleep(1000);
        js.executeScript("location.reload(true);");
    }

    /**
     * This control simulates click and drag captcha slider action.
     * @param locator The locating mechanism.
     * @param maxWaitTime The max wait counter value.
     */
    public void clickAndDragSliderCaptcha(By locator, int maxWaitTime) throws InterruptedException {
        if (isElementPresentWithinWait(locator, DEFAULT_WAIT_TIME)) {
            highLighter(locator);
            WebElement element = findElement(locator, maxWaitTime);
            System.out.println(element.getLocation());
            Actions action = new Actions(driver);
            action.moveToElement(element);
            // Javascript code for going to bottom of the page
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            //System.out.println(element.getLocation());
            //action.contextClick(element).perform();
            //action.clickAndHold(element).dragAndDropBy(element,650 ,0).release();
            //action.build().perform()
            // Javascript code for drag and drop
            ((JavascriptExecutor) driver)
                    .executeScript("function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
                                    "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
                            element, 1100, 835);
        }
    }

    /**
     * This control returns true if a String has a number.
     * @param s The String to be checked
     */
    public boolean isStringContainsNumber(String s){
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }

    public void waitForElementToHaveAValue(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

        while(element.getText().equalsIgnoreCase(""))
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // convert to Big Decimal into 2 decimal places
    public BigDecimal covertToBigDecimalTwoDecimal(String value) {
        value = value.replace(",", "");
        System.out.println("DEBUG: Convert to two decimal places - " + value + "\n");
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

//    public void launchURL(String url) {
//        //setting the driver executable
//        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\drivers\\chromedriver.exe");
//
//        //Initiating your chromedriver
//        WebDriver driver = new ChromeDriver();
//
//        //Applied wait time
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        //maximize window
//        driver.manage().window().maximize();
//
//        //open browser with desried URL
//        driver.get(url);
//
//        //closing the browser
//        driver.close();
//    }

}
