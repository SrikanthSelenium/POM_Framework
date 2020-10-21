package myProject.practest.test.helper.select;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import myProject.practest.test.helper.alert.AlertHelper;

public class DropDownHelper {

	private WebDriver driver;

	private Logger log = Logger.getLogger(AlertHelper.class);

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectByVisibleText(WebElement element, String value) {
		Select select = new Select(element);
		log.info("selectUsingValue and value is:" + value);
		select.selectByVisibleText(value);

	}
	
	
	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("selectUsingValue and value is:" + value);
		select.selectByValue(value);

	}

	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("selectUsingValue and index is:" + index);
		select.selectByIndex(index);

	}

	public void selectUsingOption(WebElement element, String option) {
		Select select = new Select(element);
		log.info("selectUsingValue and option is:" + option);
		select.selectByVisibleText(option);

	}

	public void deselectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("deselectUsingValue and value is:" + value);
		select.deselectByValue(value);

	}

	public void deselectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("deselectUsingValue and index is:" + index);
		select.deselectByIndex(index);

	}

	public void deselectUsingOption(WebElement element, String option) {
		Select select = new Select(element);
		log.info("deselectUsingValue and option is:" + option);
		select.deselectByVisibleText(option);

	}

	public List<String> getAllDropdownOptions(WebElement element) {
		Select select = new Select(element);
		List<WebElement> elements = select.getOptions();
		List<String> valueList = new ArrayList<String>();
		for (WebElement ele : elements) {
			log.info("option :"+ele.getText());
			valueList.add(ele.getText());
		}

		return valueList;

	}

}
