package myProject.practest.test.helper.assertion;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.testbase.TestBase;

public class VerificationHelper {

	private WebDriver driver;

	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);

	public VerificationHelper(WebDriver driver) {
	this.driver = driver;
	}

	
	public boolean isDisplayed(WebElement element) throws Exception {
		try {
			element.isDisplayed();
			log.info("Element displayed");
			return true;
		} catch (Exception e) {
			log.error("element is not displayed " + e.getLocalizedMessage());
			//TestBase.logStatus("Fail","element is not displayed " +e.getLocalizedMessage());
			throw new Exception(e.getLocalizedMessage());
			}
	}

	public boolean isEnabled(WebElement element) {
		try {
			element.isEnabled();
			log.info("Element enabled");
			return true;
		} catch (Exception e) {
			log.error("element is not enabled" + e.getLocalizedMessage());
			return false;
		}

	}

	public String readValueFromElement(WebElement element) {
		if (element.isDisplayed() && element != null) {
			log.info("element is displayed with text:" + element.getText());
			return element.getText();
		} else {
			log.info("element is not displayed");
			return null;
		}

	}
	
	public String getTitle()
	{
		log.info("Title of the page : "+driver.getTitle());
		return driver.getTitle();
		
	}

}
