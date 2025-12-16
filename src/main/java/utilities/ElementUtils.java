package utilities;

import static org.testng.Assert.fail;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseTest;

public class ElementUtils{
	private WebDriver driver;
	private static final Logger log = LogManager.getLogger(ElementUtils.class.getName());
 

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}
	public void clickWhenReady(WebElement element, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	    try {
		element.click();
		log.info("Succesfully clicked on element: " + element);
	    }
	    catch (Exception e) {
	    	log.error("Error: Couldn't click element " + element);
	    	Assert.fail("Couldn't click element " + element);
	    }
	}
	public void type(WebElement element, String Text, int TimeOut) {
		try 
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(Text);
		log.info("typed: " + Text + " In " + element);
		} catch (Exception e) 
		{
	    log.error("Couldn't type in " + element);
	    Assert.fail("Failed to type in " + element);
		}
		
		
	}
	public void jsClick(WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", element);
	}
	public void scrollTo(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		log.info("Scrolled" + pixels + "pixels");
	}
	public void SwitchFrame(WebElement frameElement, int timeOut ) {
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		log.info("Switched to frame: " + frameElement);
		} catch (Exception e) 
		{
		log.error("Couldn't switch to frame: " + frameElement);
		Assert.fail("Failed to switch to frame " + frameElement);
		}
	}
	
	
	

	
}
