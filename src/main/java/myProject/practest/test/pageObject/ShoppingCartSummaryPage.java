package myProject.practest.test.pageObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myProject.practest.test.helper.assertion.VerificationHelper;
import myProject.practest.test.helper.browserConfig.config.ObjectReader;
import myProject.practest.test.helper.logger.LoggerHelper;
import myProject.practest.test.helper.wait.WaitHelper;

public class ShoppingCartSummaryPage {
	


		private WebDriver driver;
		private final Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
		WaitHelper waitHelper;
		
		@FindBy(xpath="//*[@id='columns']/div[1]/span[2]")
		WebElement yourShoppingCart;
		
		@FindBy(xpath="//a[@title='Delete']")
		List<WebElement> quantity_delete;
		
		@FindBy(xpath="//*[contains(text(),'Your shopping cart is empty')]")
		WebElement emptyShoppingCartMsg;
		
		public ShoppingCartSummaryPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			waitHelper = new WaitHelper(driver);
			waitHelper.waitForElement(yourShoppingCart,ObjectReader.reader.getExplicitWait());
		}
		
		public boolean verifyProduct(String prod) throws Exception{
			log.info("selecting product.."+prod);
			WebElement product = driver.findElement(By.xpath("//*[contains(text(),'"+prod+"')]"));
			return new VerificationHelper(driver).isDisplayed(product);
		}
		
		public void delectProductFromShoppingCart() throws InterruptedException {
			// Delete all items from cart
			log.info("Deleting all products from cart..");
			List<WebElement> deletes = quantity_delete;
			Iterator<WebElement> itr = deletes.iterator();
			while (itr.hasNext()) {
				itr.next().click();
				Thread.sleep(2000);
			}
		}
		
		public boolean verifyEmptyShoppingCartMesssage() throws Exception{
			return new VerificationHelper(driver).isDisplayed(emptyShoppingCartMsg);
		}
	}


