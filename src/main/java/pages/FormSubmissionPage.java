package pages;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.testng.internal.BaseTestMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.ElementUtils;
import utilities.LocatorsUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtils eleUtils;
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtils = new ElementUtils(driver);
		
	}
	
	
	private By fullName = By.cssSelector(LocatorsUtil.get("login.userName.css"));
    
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
