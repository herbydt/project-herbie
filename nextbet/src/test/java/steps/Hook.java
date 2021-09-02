package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helper.CucumberHelper;
import libraries.AppiumBase;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.asserts.SoftAssert;
import workflow.WorkflowNextbet;

import java.util.concurrent.TimeUnit;

public class Hook extends BaseStep {

    public BaseStep base;

    public Hook(BaseStep base) {
        this.base = base;
        appium = new AppiumBase();
    }

    @Before
    public void initializeTest(Scenario scenario) throws Exception
    {
        CucumberHelper.scenario = scenario;

        // run appium if android or ios
        if(isRealDevice()) {
            appium.startAppium();
        }

        System.out.println("DEBUG: Initializing test..." + "\n");
        System.out.println("SCENARIO: " + CucumberHelper.scenario.getName() + "\n");

        base.driver = setupDriver(Boolean.valueOf(getGrid()), Boolean.valueOf(getDocker()), getBrowser(), getRemoteUrl(), getDevice(), Boolean.valueOf(getExtension()), getExtensionName(),Boolean.valueOf(getDevToolEnabled()));
        // this is for desktop only
        if(!isRealDevice()) {
            base.driver.manage().window().maximize();
            base.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        }

        softAssert = new SoftAssert();
        workflowNextbet = new WorkflowNextbet(driver);
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
            scenario.write(getJSErrosLog());
        }
        System.out.println("DEBUG: Tear down." + "\n");
        System.out.println(String.format("%s: Execution for scenario %s finished.", StringUtils.upperCase(scenario.getStatus()), CucumberHelper.scenario.getName()) + "\n");

        if(isRealDevice()) {
            base.driver.quit();
            appium.stopAppium();
        } else {
            base.driver.quit();
        }
        softAssert.assertAll();
    }

}
