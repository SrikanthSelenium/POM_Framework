package myProject.practest.test.helper.alert;
/**
 *
 *  @author srikanth
 *  @parameter driver
 */
 
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHelper {

	private WebDriver driver;

	private Logger log = Logger.getLogger(AlertHelper.class);

	public AlertHelper(WebDriver driver) {
		this.driver = driver;
	}

	public Alert getAlert() {
		log.info("Alert ");
		return driver.switchTo().alert();
	}

	public void acceptAlert() {
		log.info("Alert accepted");
		getAlert().accept();
	}

	public void dismissAlert() {
		log.info("Alert dismissed");
		getAlert().dismiss();
	}

	public String getAlertText() {
		log.info("Alert text " + getAlert().getText());
		return getAlert().getText();
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}

		catch (NoAlertPresentException nap) {
			log.info(nap.getLocalizedMessage());
			return false;
		}
	}

	public void acceptAlertIfPresent() {
		if (isAlertPresent()) {
			acceptAlert();
		} else {
			log.info("Alert is not present");
		}
	}

	public void dismissAlertIfPresent() {
		if (isAlertPresent()) {
			dismissAlert();
		} else {
			log.info("Alert is not present");
		}
	}
	
	public void acceptPrompAlert(String text)
	{
		if(isAlertPresent())
		{
			Alert alert = getAlert();
			alert.sendKeys("text");
			alert.accept();
		}
	}
	

}
