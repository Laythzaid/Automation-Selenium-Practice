package pages;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.util.PropertySource.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.Utils;

import base.BaseTest;
import jdk.internal.org.jline.utils.Log;
import utilities.ElementUtils;
import utilities.LocatorsUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtils eleUtils;
	private LocatorsUtil locators;

//	private BaseTest js;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtils = new ElementUtils(driver);
		this.locators= new LocatorsUtil("locators.properties");
	}

	private By NewUserBtn = By.id(locators.get("NewUser"));

	public void clickNewUser() throws InterruptedException {
		eleUtils.scrollTo(500);
		WebElement NewUser = driver.findElement(NewUserBtn);
		eleUtils.clickWhenReady(NewUser, 5);
		eleUtils.scrollTo(500);
	}

	private By firstNameField = By.xpath(locators.get("firstName"));

	public void writeFirstName(String yourFirstName) {
		WebElement firstName = driver.findElement(firstNameField);
		eleUtils.type(firstName, yourFirstName, 5);
	}

	private By lastNameField = By.xpath(locators.get("lastName"));

	public void writeLastName(String yourLastName) {
		WebElement lastName = driver.findElement(lastNameField);
		eleUtils.type(lastName, yourLastName, 5);
	}

	private By userNameField = By.xpath(locators.get("userName"));

	public void writeUserName(String yourUserName) {
		WebElement userName = driver.findElement(userNameField);
		eleUtils.type(userName, yourUserName, 5);
	}

	private By password = By.xpath(locators.get("password"));

	public void writePassword(String yourPassword) {
		WebElement passwordField = driver.findElement(password);
		eleUtils.type(passwordField, yourPassword, 3);
	}

	private By registerClick = By.xpath(locators.get("register"));

	public void clickRegister() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement register = wait.until(ExpectedConditions.elementToBeClickable(registerClick));
		eleUtils.jsClick(register, 3);
	}

	// handle Recaptcha Locators
	private By recaptchaFrame = By.xpath(locators.get("recaptchaFrame"));
	private By recaptchaBtn = By.xpath(locators.get("recaptcha"));
	private By verifyRecFrame = By.xpath(locators.get("verifyRecaptchaFrame"));
	private By verifyRec = By.id(locators.get("verifyRecaptcha"));

	public void handleRecaptcha() throws InterruptedException {
		eleUtils.scrollTo(100);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement recFrame = driver.findElement(recaptchaFrame);
		eleUtils.SwitchFrame(recFrame, 10);

		WebElement recaptcha = wait.until(ExpectedConditions.visibilityOfElementLocated(recaptchaBtn));
		eleUtils.clickWhenReady(recaptcha, 10);

		String captchaResult = recaptcha.getAttribute("aria-checked");
//		String captchaResult1 = recaptcha.getAttribute("aria-disabled");
		while (captchaResult.equals("false")) {
			try {
				driver.switchTo().defaultContent();
				Thread.sleep(8000);
				WebElement verifyRecaptchaFrame = driver.findElement(verifyRecFrame);
				eleUtils.SwitchFrame(recFrame, 10);
				captchaResult = recaptcha.getAttribute("aria-checked");

				if (captchaResult.equals("true")) {
					System.out.println("Captcha Solved!");
					driver.switchTo().defaultContent();
					break;
				}
				driver.switchTo().defaultContent();
				try {
					eleUtils.SwitchFrame(verifyRecaptchaFrame, 5);
					WebElement verifyRecaptcha = wait.until(ExpectedConditions.elementToBeClickable(verifyRec));
					eleUtils.clickWhenReady(verifyRecaptcha, 5);
					driver.switchTo().defaultContent();
					Thread.sleep(1000);
				} catch (Exception e) {

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private By backToLoginBtn = By.xpath(locators.get("backToLogin"));

	public void goToLogin() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement backToLogin = wait.until(ExpectedConditions.elementToBeClickable(backToLoginBtn));
		eleUtils.clickWhenReady(backToLogin, 5);
	}

	private By loginBtn = By.xpath(locators.get("loginBtn"));

	public void clickLogin() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		eleUtils.clickWhenReady(loginButton, 5);

	}

}
