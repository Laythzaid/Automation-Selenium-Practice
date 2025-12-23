package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.ElementUtils;
import utilities.LocatorsUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtils eleUtils;
	private static final Logger log = LogManager.getLogger(LoginPage.class);

//	private BaseTest js;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtils = new ElementUtils(driver);

	}

	private By NewUserBtn() {
		return By.id(LocatorsUtil.get("NewUser"));

	}

	public void clickNewUser() {
		eleUtils.scrollTo(500);
		eleUtils.clickWhenReady(NewUserBtn(), 5);
		eleUtils.scrollTo(500);
	}

	private By firstNameField() {
		return By.xpath(LocatorsUtil.get("firstName"));
	}

	public void writeFirstName(String yourFirstName) {
		WebElement firstName = driver.findElement(firstNameField());
		eleUtils.type(firstName, yourFirstName, 5);
	}

	private By lastNameField() {
		return By.xpath(LocatorsUtil.get("lastName"));
	}

	public void writeLastName(String yourLastName) {
		WebElement lastName = driver.findElement(lastNameField());
		eleUtils.type(lastName, yourLastName, 5);
	}

	private By userNameField() {
		return By.xpath(LocatorsUtil.get("userName"));
	}

	public void writeUserName(String yourUserName) {
		WebElement userName = driver.findElement(userNameField());
		eleUtils.type(userName, yourUserName, 5);
	}

	private By password() {
		return By.xpath(LocatorsUtil.get("password"));
	}

	public void writePassword(String yourPassword) {
		WebElement passwordField = driver.findElement(password());
		eleUtils.type(passwordField, yourPassword, 3);
	}

	private By registerClick() {
		return By.xpath(LocatorsUtil.get("register"));
	}

	public void clickRegister() {
		WebElement register = driver.findElement(registerClick());
		eleUtils.jsClick(register, 3);
	}

	// handle Recaptcha Locators
	private By recaptchaFrame() {
		return By.xpath(LocatorsUtil.get("recaptchaFrame"));
	}

	private By recaptcha() {
		return By.xpath(LocatorsUtil.get("recaptcha"));
	}

	private By verifyRecFrame() {
		return By.xpath(LocatorsUtil.get("verifyRecaptchaFrame"));

	}

	private By verifyRec() {
		return By.id(LocatorsUtil.get("verifyRecaptcha"));
	}

	// NOTE: CAPTCHA cannot be automated.
	// Human interaction required for this step in demo environment.
	public void handleRecaptcha() {
		log.warn("CAPTCHA requires human interaction. Test is paused.");
		eleUtils.scrollTo(100);

		eleUtils.logCurrentFrame();
		eleUtils.SwitchFrame(recaptchaFrame(), 10);

		eleUtils.clickWhenReady(recaptcha(), 5);

		while (true) {
			String captchaResult = driver.findElement(recaptcha()).getAttribute("aria-checked");

			if ("true".equals(captchaResult)) {
				log.info("CAPTCHA solved");
				eleUtils.logCurrentFrame();
				break;
			}

		}

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		eleUtils.logCurrentFrame();

		try {
			eleUtils.SwitchFrame(verifyRecFrame(), 5);
			eleUtils.clickWhenReady(verifyRec(), 5);
		} catch (Exception ignore) {

		}
		eleUtils.logCurrentFrame();

	}

	private By backToLoginBtn() {
		return By.xpath(LocatorsUtil.get("backToLogin"));
	}

	public void goToLogin() {
		eleUtils.clickWhenReady(backToLoginBtn(), 5);
	}

	private By loginBtn() {
		return By.xpath(LocatorsUtil.get("loginBtn"));
	}

	public void clickLogin() {
		eleUtils.clickWhenReady(loginBtn(), 5);
	}

}
