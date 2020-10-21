package myProject.practest.test.testScripts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import myProject.practest.test.helper.assertion.AssertionHelper;
import myProject.practest.test.helper.assertion.VerificationHelper;
import myProject.practest.test.helper.browserConfig.config.ObjectReader;
import myProject.practest.test.helper.excel.ExcelHelper;
import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.helper.resource.ResourceHelper;
import myProject.practest.test.pageObject.LoginPage;
import myProject.practest.test.testbase.TestBase;

public class LoginTest extends TestBase {

	private Logger log = LoggerHelper.getLogger(LoginTest.class);
	VerificationHelper verifyHelper;
	static String path = ResourceHelper.getResourcePath("src/main/resources/configfile/testData.xlsx");

	@Test(dataProvider = "testData")
	public void testLoginToApplication(String testCase, String runMode, String userName, String Password)
			throws Exception {
		if (testCase.equalsIgnoreCase("Login") && runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Test not set for Run");
		}
		// getApplicationUrl(ObjectReader.reader.getUrl());
		LoginPage loginpage = new LoginPage(driver);
		verifyHelper = new VerificationHelper(driver);
		loginpage.loginToApplication(userName, Password);
		boolean status = loginpage.verifySuccessLoginMsg();
		//AssertionHelper.updateTestStatus(status);
		if (status) {
			TestBase.logStatus("Pass", "Login Succesful..." + loginpage.LoginSuccessMsg());
			loginpage.logout();
		}
	}

	@DataProvider(name = "testData")

	public Object[][] testData() throws IOException {
		Object[][] data = ExcelHelper.getExcelData(path, "loginData");
		return data;
	}
}
