package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.ElementUtils;
import utilities.LocatorsUtil;

public class DataSetSubmissionPage {
	private WebDriver driver;
	private ElementUtils eleUtils;
	private By elementNav;
	private By textBoxNav;

	
	
	public DataSetSubmissionPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtils = new ElementUtils(driver);
		elementNav = By.xpath(LocatorsUtil.get("nav.elements"));
		textBoxNav = By.xpath(LocatorsUtil.get("nav.TextBox"));
	}
 
	public void pageNav() {
		eleUtils.clickWhenReady(elementNav, 5);
		eleUtils.clickWhenReady(textBoxNav, 10);
	}
	private By fullName = By.id(LocatorsUtil.get("login.userName.id"));
    
	private By emailField = By.cssSelector(LocatorsUtil.get("login.email.css"));

	private By currentAddress = By.cssSelector(LocatorsUtil.get("login.currentAddress.css"));

	private By permenantAddress = By.cssSelector(LocatorsUtil.get("login.permanentAddress.css"));
	
	private By submitButton = By.cssSelector(LocatorsUtil.get("login.submit.css"));
	
	public void insertUserDetails(String userName, String email, String UserCurtAddr, String UserPermAdrr, boolean shouldPass) {
		eleUtils.type(fullName, userName, 5);
		eleUtils.type(emailField, email, 5);
		eleUtils.type(currentAddress, UserCurtAddr, 5);
		eleUtils.type(permenantAddress, UserPermAdrr, 5);
		eleUtils.scrollTo(100);
		eleUtils.clickWhenReady(submitButton, 5);
	}
	

	private By isFormSubmitted() {
		//output exists (name, email, permaddress or current)
		return By.xpath("//div[@id = 'output']/child::*/child::p");
	}
	
	public boolean isFormSucceed() {
        return eleUtils.isDisplayed(isFormSubmitted(), 5);
    }
	
	public boolean isEmailInvalid() {
		WebElement emailInvalid = driver.findElement(emailField);
        return emailInvalid.getAttribute("class").contains("field-error");
    }
	

}
