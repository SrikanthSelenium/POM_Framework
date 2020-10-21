package myProject.practest.test.testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import myProject.practest.test.helper.browserConfig.Browser;
import myProject.practest.test.helper.browserConfig.BrowserType;
import myProject.practest.test.helper.browserConfig.config.ObjectReader;
import myProject.practest.test.helper.browserConfig.config.PropertyReader;
import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.helper.resource.ResourceHelper;
import myProject.practest.test.helper.wait.WaitHelper;
import myProject.practest.test.utils.ExtentManager;

public class TestBase {

	protected static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	private static Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectory;
	private static String screenshotPath;

	@BeforeSuite
	public void beforeSuite() throws Exception {
		extent = ExtentManager.getInstance();

	}

	@BeforeTest
	public void beforeTest() throws Exception {
		reportDirectory = new File(ResourceHelper.getResourcePath("src\\main\\resources\\screenShots"));
		ObjectReader.reader = new PropertyReader();
		test = extent.createTest(getClass().getSimpleName());
		setupBrowser(ObjectReader.reader.getBrowserType());
		getApplicationUrl(ObjectReader.reader.getUrl());
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		// extent = ExtentManager.getInstance();

	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName() + " test started");
		log.info("**************" + method.getName() + "Started***************");
	}

	/**
	 * @author srikanth
	 * @param result
	 * @throws IOException
	 * @throws Exception   if testcase is failed
	 */
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			screenshotPath = TestBase.captureScreen(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " Passed");
			// screenshotPath = TestBase.captureScreen(result.getName());
			// test.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getName() + " Skipped");
//			screenshotPath = TestBase.captureScreen(result.getName());
//			test.addScreenCaptureFromPath(screenshotPath);
		}

	}

	@AfterClass
	public void afterClass() {

	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			driver.quit();
		}

		extent.flush();
	}

	@SuppressWarnings("static-access")
	public WebDriver getBrowser(BrowserType browser) throws Exception {
		try {
			switch (browser) {
			case Chrome:
				Browser chrome = Browser.class.newInstance();
				ChromeOptions options = chrome.getChromeOptions();
				return chrome.getChromeDriver(options);
			default:
				throw new Exception("No browser found");
			}
		} catch (Exception e) {
			log.info(e.getStackTrace());
			throw e;
		}

	}

	public void setupBrowser(BrowserType browser) throws Exception {
		driver = getBrowser(browser);
		log.info("Browser set to :" + browser);
		WaitHelper wait = new WaitHelper(driver);
		wait.setImpliitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.pageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static String captureScreen(String fileName) {
		if (driver == null) {
			log.info("driver is null..");
			return null;
		}
		if (fileName == "") {
			fileName = "blank";
		}

		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(reportDirectory + "/" + fileName + "_" + format.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href ='" + destFile.getAbsolutePath() + "'><img src ='" + destFile.getAbsolutePath()
					+ "'height='100' width='100'/></a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void getApplicationUrl(String url) {
		driver.get(url);
		logStatus("navigating to ..." + url);
	}

	public static void logStatus(String message) {
		test.log(Status.INFO, message);
	}

	public static void logStatus(String flag, String message) throws IOException {
		if (flag.equalsIgnoreCase("Pass")) {
			test.log(Status.PASS, message);
		} else {
			test.log(Status.FAIL, message);
			//screenshotPath = TestBase.captureScreen(message);
			//test.addScreenCaptureFromPath(screenshotPath);
			
		}
	}

}
