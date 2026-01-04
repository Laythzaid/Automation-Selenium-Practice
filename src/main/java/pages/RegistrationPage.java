package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;
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

	// NOTE: CAPTCHA cannot be automated.
	// Human interaction required for this step in demo environment.
	
	public void WaitForCaptchaOrFail(){
		int timeout;
		try {
		timeout = Integer.parseInt(ConfigReader.get("captcha.timeout.seconds"));
		} catch (NumberFormatException e) {
			throw new RuntimeException("Invalid captcha timeout in config file");
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(driver -> {
				String captchaResult = driver.findElement(recaptcha()).getAttribute("aria-checked"); 
				return "true".equals(captchaResult);
				});
			log.info("Captcha solved");
		} catch (Exception e) {
			throw new RuntimeException("Captcha failed to verify");
			
		}
	}
	
	public void handleRecaptcha() {
		log.warn("CAPTCHA requires human interaction. Test is paused.");
		if(Boolean.parseBoolean(System.getenv("IS_CI"))) {
			log.warn("Skipping CAPTCHA in CI environment");
			return;
		}
		eleUtils.scrollTo(100);

		eleUtils.logCurrentFrame();
		eleUtils.SwitchFrame(recaptchaFrame(), 10);

		eleUtils.clickWhenReady(recaptcha(), 5);
		WaitForCaptchaOrFail();

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
		eleUtils.clickWhenReady(loginBtn(), 10);
	}

}
