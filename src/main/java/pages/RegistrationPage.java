package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;
import utilities.ElementUtils;
import utilities.LocatorsUtil;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtils eleUtils;
	private static final Logger log = LogManager.getLogger(RegistrationPage.class);
	private By elementNav;
    private By bookStoreGenNav;
    private By bookStoreLoginNav;
	
	
//	private BaseTest js;
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtils = new ElementUtils(driver);
		elementNav = By.xpath(LocatorsUtil.get("nav.elements"));
		bookStoreGenNav = By.xpath(LocatorsUtil.get("nav.bookStore.gen"));
		bookStoreLoginNav = By.xpath(LocatorsUtil.get("nav.bookStore.login"));

	}

	public void pageNav(){
		eleUtils.clickWhenReady(elementNav, 5);
		eleUtils.scrollTo(500);
		eleUtils.clickWhenReady(bookStoreGenNav, 10);
		eleUtils.clickWhenReady(bookStoreLoginNav, 5);
	}
	
	private By NewUserBtn = By.id(LocatorsUtil.get("register.newUser.id"));
	

	public void clickNewUser() {
		eleUtils.scrollTo(500);
		eleUtils.clickWhenReady(NewUserBtn, 5);
		eleUtils.scrollTo(500);
	}

	private By firstNameField = By.cssSelector(LocatorsUtil.get("register.firstname.css"));
	

	public void writeFirstName(String yourFirstName) {
		eleUtils.type(firstNameField, yourFirstName, 5);
	}

	private By lastNameField = By.cssSelector(LocatorsUtil.get("register.lastname.css"));
	

	public void writeLastName(String yourLastName) {
		eleUtils.type(lastNameField, yourLastName, 5);
	}

	private By userNameField = By.cssSelector(LocatorsUtil.get("register.username.css"));
	

	public void writeUserName(String yourUserName) {
		eleUtils.type(userNameField, yourUserName, 5);
	}

	private By password = By.cssSelector(LocatorsUtil.get("register.password.css"));

	public void writePassword(String yourPassword) {
		eleUtils.type(password, yourPassword, 3);
	}

	private By registerClick = By.cssSelector(LocatorsUtil.get("register.register.css"));
	

	public void clickRegister() {
		eleUtils.jsClick(registerClick, 3);
	}

	// handle Recaptcha Locators
	private By recaptchaFrame = By.xpath(LocatorsUtil.get("register.recaptchaFrame.xpath"));
	

	private By recaptcha = By.cssSelector(LocatorsUtil.get("register.recaptcha.css"));

	// NOTE: CAPTCHA cannot be automated.
	// Human interaction required for this step in demo environment.
	
	public boolean WaitForCaptchaOrFail(){
		int timeout;
		try {
		timeout = Integer.parseInt(ConfigReader.get("captcha.timeout.seconds"));
		} catch (NumberFormatException e) {
			throw new RuntimeException("Invalid captcha timeout in config file");
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(driver -> {
				String captchaResult = driver.findElement(recaptcha).getAttribute("aria-checked"); 
				return "true".equals(captchaResult);
				});
			log.info("Captcha solved");
			return true;
		} catch (Exception e) {

			log.warn("Captcha not solved within timeout");
			return false;
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
		eleUtils.SwitchFrame(recaptchaFrame, 15);

		try {
		
		eleUtils.clickWhenReady(recaptcha, 15);
		WaitForCaptchaOrFail();
		} catch(Exception e){
			log.warn("Captcha interaction failed. Continuing test");
			
		}
		 
		eleUtils.logCurrentFrame();

	}

	private By backToLoginBtn = By.cssSelector(LocatorsUtil.get("register.back.to.login.css"));
	

	public void goToLogin() {
		eleUtils.clickWhenReady(backToLoginBtn, 10);
	}

	private By loginBtn = By.cssSelector(LocatorsUtil.get("register.login.button.css"));
	

	public void clickLogin() {
		eleUtils.scrollTo(200);
		eleUtils.clickWhenReady(loginBtn, 10);
	}

}
