package myProject.practest.test.helper.switchFrame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.helper.wait.WaitHelper;

public class FrameClass {
	private WebDriver driver;

	private Logger log = LoggerHelper.getLogger(WaitHelper.class);

	public FrameClass(WebDriver driver) {
		this.driver = driver;
	}
	
	public void switchToFrame(int frameIndex)
	{
		driver.switchTo().frame(frameIndex);
		log.info("Switched to frame with index:"+frameIndex);
	}
	
	public void switchToFrame(String frameName)
	{
		driver.switchTo().frame(frameName);
		log.info("Switched to frame with name:"+frameName);
	}
	public void switchToFrame(WebElement element)
	{
		driver.switchTo().frame(element);
		log.info("Switched to frame with element:"+element.toString());
	}
	
}
