package myProject.practest.test.testScripts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myProject.practest.test.helper.assertion.AssertionHelper;
import myProject.practest.test.helper.assertion.VerificationHelper;
import myProject.practest.test.helper.browserConfig.config.ObjectReader;
import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.pageObject.LoginPage;
import myProject.practest.test.pageObject.MyAccountPage;
import myProject.practest.test.pageObject.RegistrationPage;
import myProject.practest.test.testbase.TestBase;

public class RegistrationTest extends TestBase {
	private Logger log = LoggerHelper.getLogger(RegistrationTest.class);

	LoginPage loginPage;
	RegistrationPage register;
	MyAccountPage myAccountPage;
	VerificationHelper verifyHelper;

	@Test
	public void testApplicationRegistration() throws Exception {

		//getApplicationUrl(ObjectReader.reader.getUrl());

		loginPage = new LoginPage(driver);
		loginPage.clickOnSignInLink();
		loginPage.enterRegistrationEmail();
		verifyHelper = new VerificationHelper(driver);

		register = loginPage.clickOnCreateAnAccount();

		// enter registration data
		register.setMrRadioButton();
		register.setFirstName("firstName");
		register.setLastname("lastname");
		register.setPassword("password");
		register.setAddress("address");
		register.setDay("5");
		register.setMonth("June");
		register.setYear("2017");
		register.setYourAddressFirstName("yourAddressFirstName");
		register.setYourAddressLastName("yourAddressLastName");
		register.setYourAddressCompany("yourAddressCompany");
		register.setYourAddressCity("yourAddressCity");
		register.setYourAddressState("Alaska");
		register.setYourAddressPostCode("99501");
		register.setMobilePhone("1111111111");
		register.setAddressAlias("addressAlias");
		register.clickRegistration();
		boolean status = register.verifyRegisterSuccessMsg();
		if (status) {
			TestBase.logStatus("Pass", "Registration Succesful..." + register.RegisterSuccessMsg());
			loginPage.logout();
		}
		// AssertionHelper.updateTestStatus(status);

	}

}
