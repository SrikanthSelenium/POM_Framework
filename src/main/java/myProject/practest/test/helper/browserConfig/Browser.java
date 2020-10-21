package myProject.practest.test.helper.browserConfig;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.helper.resource.ResourceHelper;
import myProject.practest.test.testbase.TestBase;

public class Browser {
	private static Logger log = LoggerHelper.getLogger(Browser.class);

	public static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-notifications");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		return options;
	}

	public static WebDriver getChromeDriver(ChromeOptions cap) {
		if (System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.chrome.driver",
					ResourceHelper.getResourcePath("src/main/resources/drivers/chromedriver.exe"));
			ChromeDriver driver = new ChromeDriver(cap);
			log.info("************Chrome browser launched*****************");
			return driver;
		} else {
			log.info("******************Failed to launch Chrome browser********************");
			return null;
		}

	}
}
