package com.quantum.steps;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.utils.DeviceUtils;
import cucumber.api.java.en.Then;

@QAFTestStepProvider
public class GenericApplicationSteps extends WebDriverTestBase {
	@Then("^I clean and launch Android App by ID \"([^\"]*)\"$")
	public static void launchAndoridAppByID(String androidAppID) {
		DeviceUtils.cleanApp(androidAppID, "identifier");
		DeviceUtils.startApp(androidAppID, "identifier");
		DeviceUtils.switchToContext("NATIVE_APP");
	}

	@Then("^I clean and launch Android App by Name \"([^\"]*)\"$")
	public static void launchAndoridAppByName(String androidAppName) {
		DeviceUtils.cleanApp(androidAppName, "name");
		DeviceUtils.startApp(androidAppName, "name");
		DeviceUtils.switchToContext("NATIVE_APP");
	}

	@Then("^I scroll to \"(.*?)\"$")
	public static void scrollTo(String locator) {
		boolean notFound = true;
		while(notFound) {
			if (new QAFExtendedWebElement(locator).isPresent()) {
				notFound = false;
			} else {
				DeviceUtils.swipe("50%,70%", "50%,30%");
			}
		}
	}
	@Then("^I click \"(.*?)\" if present$")
	public static void clickPresent(String locator) {
		QAFExtendedWebElement elm = new QAFExtendedWebElement(locator);
		if(elm.isPresent()){
			elm.click();
		}
	}

	@Then("^I wait \"(.+)\" to click \"(.*?)\" if present$")
	public static void clickPresent(long seconds, String locator) {
		try
		{
			CommonStep.waitForPresent(locator, seconds);
			new QAFExtendedWebElement(locator).click();
		}catch (org.openqa.selenium.TimeoutException E) {
			System.out.println(locator+" wasn't found: Continuing.");
		}
	}
/*  Author: Stephen Musch
    Date: 2/16/2018
    Paramaters: Accepts text string
    Purpose: For use on apps.  This method will check if a string of text appears as an text attribute value
 */
    @Then("^I see text \"(.*?)\" in an app")
    public void betterTextRecognitionApp(String textToFind) {
        String locatorToFind = ("xpath=//*[@*[contains(., '" + textToFind+"')]]");
        QAFExtendedWebElement elm = new QAFExtendedWebElement(locatorToFind);
        elm.verifyPresent();
    }
    /*  Author: Stephen Musch
        Date: 2/16/2018
        Paramaters: Accepts text string
        Purpose: For use on web. This method will check if a string of text appears as an element value
     */
    @Then("^I see text \"(.*?)\" on a website")
    public void betterTextRecognitionWeb(String textToFind) {
        String locatorToFind = ("xpath=//*[contains(text(), '" + textToFind+"')]");
        QAFExtendedWebElement elm = new QAFExtendedWebElement(locatorToFind);
        elm.verifyPresent();
    }
}

