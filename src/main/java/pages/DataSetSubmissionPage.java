package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ElementUtils;
import utilities.LocatorsUtil;

public class DataSetSubmissionPage {
	private WebDriver driver;
	private ElementUtils eleUtils;
//	private By elementNav;
//	private By textBoxNav;

	
	
	public DataSetSubmissionPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtils = new ElementUtils(driver);
//		elementNav = By.xpath(LocatorsUtil.get("nav.elements"));
//		textBoxNav = By.xpath(LocatorsUtil.get("nav.TextBox"));
	}
 
//	public void pageNav() {
//		eleUtils.clickWhenReady(elementNav, 5);
//		eleUtils.clickWhenReady(textBoxNav, 10);
//	}
	
	private By fullName = By.id(LocatorsUtil.get("login.userName.id"));
    
	private By emailField = By.cssSelector(LocatorsUtil.get("login.email.css"));

	private By currentAddress = By.cssSelector(LocatorsUtil.get("login.currentAddress.css"));

	private By permenantAddress = By.cssSelector(LocatorsUtil.get("login.permanentAddress.css"));
	
	private By submitButton = By.cssSelector(LocatorsUtil.get("login.submit.css"));
	
	private By submissionStatus = By.xpath(LocatorsUtil.get("login.submission.successful.xpath"));
	
	public void insertUserDetails(String userName, String email, String UserCurtAddr, String UserPermAdrr, boolean shouldPass) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		eleUtils.type(fullName, userName, 10);
		eleUtils.type(emailField, email, 10);
		eleUtils.type(currentAddress, UserCurtAddr, 10);
		eleUtils.type(permenantAddress, UserPermAdrr, 10);
//		eleUtils.scrollTo(100);
		eleUtils.clickWhenReady(submitButton, 10);

		wait.until(ExpectedConditions.visibilityOfElementLocated(submissionStatus));
	}
	

	private By isFormSubmitted() {
		//output exists (name, email, permaddress or current)
		return By.xpath("//div[@id = 'output']/child::*/child::p");
	}
	
	public boolean isFormSucceed() {
        return eleUtils.isDisplayed(isFormSubmitted(), 10);
    }
	
	public boolean isEmailInvalid() {
		WebElement emailInvalid = driver.findElement(emailField);
        return emailInvalid.getAttribute("class").contains("field-error");
    }
	

}
