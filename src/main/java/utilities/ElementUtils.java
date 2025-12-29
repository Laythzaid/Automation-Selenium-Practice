package utilities;

import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final Logger log = LogManager.getLogger(ElementUtils.class);

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		int timeout = Integer.parseInt(ConfigReader.get("explicit.wait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	}

	public void waitForPageLoad() {
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}

	public void clickWithRetry(WebElement element, int attempts) {
		for (int i = 1; i <= attempts; i++) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				return;
			} catch (Exception e) {
				log.warn("Retrying click attempt " + i);
			}
			throw new RuntimeException("Failed to click after retries");
		}
	}

	public void clickWhenReady(By btnLocator, int TimeOut) {
		wait.until(ExpectedConditions.elementToBeClickable(btnLocator));
		try {
			driver.findElement(btnLocator).click();
			log.info("Succesfully clicked on element: " + btnLocator);
		} catch (Exception e) {
			log.error("Error: Couldn't click element " + btnLocator);
			throw new RuntimeException("Couldn't click button " + btnLocator, e);
			
		}

	}

	public void type(WebElement element, String Text, int TimeOut) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(Text);
			log.info("typed: " + Text + " In " + element);
		} catch (Exception e) {
			log.error("Couldn't type in " + element);
			throw new RuntimeException("Couldn't type text in " + element, e);
		}

	}

	public void jsClick(WebElement element, int timeOut) {
		wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void scrollTo(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		log.info("Scrolled" + pixels + "pixels");
	}

	public void SwitchFrame(By frameLocator, int timeOut) {
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
			log.info("Switched to frame: " + frameLocator);
		} catch (Exception e) {
			log.error("Couldn't switch to frame: " + frameLocator);
		}
	}

	public void logCurrentFrame() {
		try {
			driver.switchTo().defaultContent();
			log.info("switched to default content (main page)");
		} catch (Exception e) {
			log.error("failed to switch to default content");
			throw new RuntimeException("Couldn't switch to default content");

		}
	}

}
