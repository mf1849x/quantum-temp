package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.utils.DeviceUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Map;
import java.util.HashMap;

import java.util.List;

@QAFTestStepProvider
public class MikeSteps extends WebDriverTestBase {
    @Then("^I should see \"([^\"]*)\"$")
    public void iShouldSeeElement(String locator) {
        QAFExtendedWebElement elm = new QAFExtendedWebElement(locator);
        elm.assertVisible("Element not visible");
    }

    @Given("^I restart Google Maps")
    public void launchMaps() {
        DeviceUtils.switchToContext("NATIVE_APP");
        DeviceUtils.cleanApp("com.google.android.apps.maps", "name");
        DeviceUtils.startApp("com.google.android.apps.maps", "name");

        //DeviceUtils.closeApp("com.android.chrome","name");
    }

    @When("^I want to search map on \"([^\"]*)\"$")
    public void I_click_map_search_field(String searchKey) {
        DeviceUtils.pressKey(searchKey);

    }

    @Then("^I press Enter")
    public void pressBack() {
        Map<String, Object> params = new HashMap<>();
        params.put("keySequence", "KEYBOARD_SEARCH");
        new WebDriverTestBase().getDriver().executeScript("mobile:presskey", params);
    }

    @Then("^I press App Switch")
    public void pressAppSwitch() {
        Map<String, Object> params = new HashMap<>();
        params.put("keySequence", "APP_SWITCH");
        new WebDriverTestBase().getDriver().executeScript("mobile:presskey", params);
    }

    @Then("^Find me this string \"([^\"]*)\"$")
    public void findMeText(String result) {

        Map<String, Object> params = new HashMap<>();
        params.put("content", result);
        params.put("threshold", 80);
        //params.put("imageBounds.needleBound", 30);
        String res = new WebDriverTestBase().getDriver().executeScript("mobile:checkpoint:text", new Object[]{params}).toString();

        if (res.equalsIgnoreCase("true")) {
            System.out.println("Found the string \"" + result + "\"!");
        } else {
            System.out.println("Could not find the string \"" + result + "\"!");
        }
    }

}