package myProject.practest.test.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import myProject.practest.test.helper.logger.LoggerHelper;

public class WindowHelper {

	private WebDriver driver;

	private Logger log = LoggerHelper.getLogger(WindowHelper.class);

	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void switchToParentWindow() {
		log.info("switching to parent window");
		driver.switchTo().defaultContent();

	}

	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}

		log.info("switching to window" + index);

	}

	public void closeAllTabsSwitchToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String parentWindow = driver.getWindowHandle();

		for (String window : windows) {
			if (!window.equals(parentWindow)) {
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		log.info("switching to main window");
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void navigateForward() {
		driver.navigate().forward();
	}
}
