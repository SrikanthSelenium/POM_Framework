package myProject.practest.test.helper.assertion;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.testbase.TestBase;

public class AssertionHelper {

	private WebDriver driver;

	private static Logger log = LoggerHelper.getLogger(AssertionHelper.class);

	public AssertionHelper(WebDriver driver) {
		this.driver = driver;

	}

	public static void verifyText(String str1, String str2) {
		log.info("Verifying text"+str1+" with text "+str2);
		Assert.assertEquals(str1, str2);
		
		
	}

	public static void makeTrue() {
		Assert.assertTrue(true);
		log.info("Assert true...");
		

	}

	public static void makeTrue(String message) {
		Assert.assertTrue(true, message);
		log.info("Assert true... with "+message);
	}

	public static void makeFalse(String message) {
		log.info("Assert false... with "+message);
		Assert.assertTrue(false, message);
		
		
	}
	
	public static void fail() throws IOException{
		TestBase.logStatus("Assertion failed");
		Assert.assertTrue(false);
		
	}
	
	public static void pass() throws IOException{
		TestBase.logStatus("Assertion passed");
		Assert.assertTrue(true);
		
	}

	public static void makeFalse() {
		log.info("Assert false...");
		Assert.assertTrue(false);
	}
	
	public static void updateTestStatus(boolean status) throws IOException{
		if(status){
			pass();
		}
		else{
			fail();
		}
	}
}
