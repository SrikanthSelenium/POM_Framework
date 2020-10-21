package myProject.practest.test.testScripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import myProject.practest.test.helper.assertion.AssertionHelper;
import myProject.practest.test.helper.assertion.VerificationHelper;
import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.testbase.TestBase;

public class TestScreenshot2 extends TestBase{
	Logger log = LoggerHelper.getLogger(TestScreenshot2.class);
	VerificationHelper vh;
	AssertionHelper ah;
	
	@Test
	public void testScreen()
	{
		vh=new VerificationHelper(driver);
		ah=new AssertionHelper(driver);
		driver.get("https://www.google.com");
		String title = vh.getTitle();
		String tit = "Goog";
		ah.verifyText(title, tit);
		//captureScreen(title);
	}
	
}
