package myProject.practest.test.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import myProject.practest.test.helper.logger.LoggerHelper;

public class WaitHelper {

	private WebDriver driver;

	private Logger log = LoggerHelper.getLogger(WaitHelper.class);

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void setImpliitWait(long timeout, TimeUnit unit) {
		log.info("Implicit wait has been set to :" + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}

	@SuppressWarnings("deprecation")
	public WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
			int pollingEveryInMillSecs) {
		log.info("waiting for :" + element.toString() + "--" + timeOutInSeconds + "seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void WaitForElementClickable(WebElement element, int timeOutInSeconds, int pollingEveryInMillSecs) {
		log.info("waiting for :" + element.toString() + "--" + timeOutInSeconds + "seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void WaitForElementInvisible(WebElement element, long timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + "--" + timeOutInSeconds + "seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	public void WaitForFrameVisibilityAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + "--" + timeOutInSeconds + "seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("Frame available and switch to it");

	}

	private Wait<WebDriver> getFluentWait(int timeOutInSecs, int pollingEveryInMillSecs) {
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSecs))
				.pollingEvery(Duration.ofMillis(pollingEveryInMillSecs)).ignoring(NoSuchFrameException.class)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
		return fwait;
	}

	public WebElement waitForFwait(WebElement element, int timeOutInSecs, int pollingEveryInMillSecs) {

		Wait<WebDriver> fwait = getFluentWait(timeOutInSecs, pollingEveryInMillSecs);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	public void pageLoadTime(long timeout, TimeUnit unit) throws InterruptedException
	{
		log.info("waiting for page loading for :"+timeout);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page loaded in :"+timeout);
	}
	
	public void waitForElement(WebElement element, long timeOutInSeconds)
	{
		log.info("waiting for :" + element.toString() + "--" + timeOutInSeconds + "seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
}
