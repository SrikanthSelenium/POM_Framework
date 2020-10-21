package myProject.practest.test.pageObject;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myProject.practest.test.helper.assertion.VerificationHelper;
import myProject.practest.test.helper.browserConfig.config.ObjectReader;
import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.helper.wait.WaitHelper;
import myProject.practest.test.testbase.TestBase;

public class MyAccountPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(MyAccountPage.class);
	
	WaitHelper waitHelper;
	VerificationHelper verfyHelper;
	
	@FindBy(xpath="//*[contains(text(),'Welcome to your account. Here you can manage all of your personal information and orders.')]")
	public static WebElement yourAccountPageMessage;
	
	@FindBy(xpath="//*[contains(text(),'Order history and details')]")
	public WebElement OrderHistoryAndDetails;
	
	@FindBy(xpath="//*[contains(text(),'My personal information')]")
	public WebElement MyPersonalInformation;
	
	@FindBy(xpath="//*[@id='center_column']/div/div[2]/ul/li/a/span")
	public WebElement wishLists;
	
	@FindBy(xpath="//*[@id='center_column']/h1")
	public WebElement myAccount;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		verfyHelper = new VerificationHelper(driver);
		waitHelper.waitForElement(OrderHistoryAndDetails,ObjectReader.reader.getExplicitWait());
		TestBase.logStatus("MyAccountPage object created");
		//new TestBase().getNavigationScreen(driver);
	}
	
	public void clickOnWishLists(){
		wishLists.click();
		log.info("clciked on "+wishLists.getText());
		TestBase.logStatus("clciked on "+wishLists.getText());
	}
	
	public void clickOnOrderHistoryAndDetails(){
		OrderHistoryAndDetails.click();
		log.info("clciked on "+OrderHistoryAndDetails.getText());
		TestBase.logStatus("clciked on "+OrderHistoryAndDetails.getText());
	}
	
	public  boolean isYourAccountPageMessage() throws Exception{
	return  verfyHelper.isDisplayed(yourAccountPageMessage);
	}
	
	public  String logged_ReegisterSucessPageMessage(){
		return  verfyHelper.readValueFromElement(yourAccountPageMessage);
		}

}
