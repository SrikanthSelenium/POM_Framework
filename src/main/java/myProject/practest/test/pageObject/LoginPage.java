package myProject.practest.test.pageObject;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import myProject.practest.test.helper.assertion.VerificationHelper;
import myProject.practest.test.helper.browserConfig.config.ObjectReader;
import myProject.practest.test.helper.jsScripts.JSExecutorHelper;
import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.helper.wait.WaitHelper;
import myProject.practest.test.testbase.TestBase;

public class LoginPage {

	private WebDriver driver;

	Logger log = LoggerHelper.getLogger(LoginPage.class);

	WaitHelper waitHelper;
	JSExecutorHelper js;
	VerificationHelper verifyHelper;
	String successMsg;

	@FindBy(xpath = "//a[@class='login']")
	WebElement signin;

	@FindBy(xpath = "//*[@id='email']")
	WebElement emailAddress;

	@FindBy(xpath = "//*[@id='passwd']")
	WebElement password;

	@FindBy(xpath = "//*[@id='SubmitLogin']")
	WebElement submitLogin;

	@FindBy(xpath = "//*[@id='email_create']")
	WebElement regEmailId;

	@FindBy(xpath = "//*[@id='SubmitCreate']")
	WebElement createAccount;

	@FindBy(xpath = "//div/h1[text()='Authentication']")
	WebElement Authentication;

	@FindBy(xpath = "//*[@id='center_column']/p")
	WebElement successMsgObject;

	@FindBy(xpath = "//a[@title='Log me out']")
	WebElement logout;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		verifyHelper = new VerificationHelper(driver);
		waitHelper.waitForElement(signin, ObjectReader.reader.getExplicitWait());
		// new TestBase().getNavigationScreen(driver);
		TestBase.logStatus("Login Page Object Created");
	}

	public void clickOnSignInLink() {
		log.info("clicked on sign in link...");
		TestBase.logStatus("clicked on sign in link...");
		signin.click();
	}

	public void enterEmailAddress(String emailAddress) {
		log.info("entering email address...." + emailAddress);
		TestBase.logStatus("entering email address...." + emailAddress);
		this.emailAddress.sendKeys(emailAddress);
	}

	public void enterPassword(String password) {
		log.info("entering password...." + password);
		TestBase.logStatus("entering password...." + password);
		this.password.sendKeys(password);
	}

	public NavigationMenu clickOnSubmitButton() {
		log.info("clicking on submit button...");
		TestBase.logStatus("clicking on submit button...");
		js = new JSExecutorHelper(driver);
		js.scrollToElement(submitLogin);
		// new JavaScriptHelper(driver).scrollDownVertically();
		submitLogin.click();
		return new NavigationMenu(driver);
	}
	public void clickOnSubmitBtn() {
		log.info("clicking on submit button...");
		TestBase.logStatus("clicking on submit button...");
		js = new JSExecutorHelper(driver);
		js.scrollDownVertically();
		// new JavaScriptHelper(driver).scrollDownVertically();
		submitLogin.click();
		
	}
	public boolean verifySuccessLoginMsg() throws Exception {
		return verifyHelper.isDisplayed(successMsgObject);
	}

	public void verifyLoginSuccessMsg() throws Exception {
		boolean status = verifyHelper.isDisplayed(successMsgObject) ? true : false;
		
			if (status == true) {
				TestBase.logStatus("Pass",
						"Login Succesful ...." + verifyHelper.readValueFromElement(successMsgObject));
			} else {
				TestBase.logStatus("Fail", "Login failed");
				driver.close();
			}
		
	}

	public void enterRegistrationEmail() {
		String email = System.currentTimeMillis() + "@gmail.com";
		log.info("entering registration email.." + email);
		regEmailId.sendKeys(email);
	}

	public RegistrationPage clickOnCreateAnAccount() {
		createAccount.click();
		return new RegistrationPage(driver);
	}

	public void loginToApplication(String emailAddress, String password) {
		clickOnSignInLink();
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnSubmitButton();
	}

	public void logout() throws IOException {
		logout.click();
		log.info("clicked on logout link");
		waitHelper.waitForElement(signin, ObjectReader.reader.getExplicitWait());
		TestBase.logStatus("Pass","Logged out successfully..");
	}

	public String LoginSuccessMsg() throws Exception {
		return verifyHelper.readValueFromElement(successMsgObject);
	}
}
