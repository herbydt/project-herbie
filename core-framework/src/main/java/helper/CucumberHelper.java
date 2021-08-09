package helper;

import cucumber.api.Scenario;

public class CucumberHelper {

    public static Scenario scenario;

    public static void embedText(String text) {
        scenario.write(text);
    }
}
