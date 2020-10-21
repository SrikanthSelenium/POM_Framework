package myProject.practest.test.helper.jsScripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import myProject.practest.test.helper.logger.LoggerHelper;

public class JSExecutorHelper {

	private Logger log = LoggerHelper.getLogger(JSExecutorHelper.class);

	private WebDriver driver;

	public JSExecutorHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper has been initialised");
	}

	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script, args);

	}

	public void scrollToElement(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);

	}

	public void scrollToElementClick(WebElement element) {

		scrollToElement(element);
		element.click();
		log.info("Clicked on Element");
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);

	}

	public void scrollIntoViewClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("Clicked on Element");
	}
	
	public void scrollDownVertically()
	{
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
		log.info("Scrolled vertically down");
	}
	
	public void scrollUpVertically()
	{
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
		log.info("Scrolled vertically up");
	}
	
	public void clickElement(WebElement element)
	{
		executeScript("arguments[0].click()",element);
		log.info("Clicked on Element");
	}
	
	

}
