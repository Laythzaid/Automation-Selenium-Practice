package testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.RegistrationPage;
import utilities.ElementUtils;

public class QaRegisterTest extends BaseTest {

//	private Properties Properties;
	RegistrationPage register;
	ElementUtils eleUtils;

	@BeforeMethod(alwaysRun = true, dependsOnMethods = {"setUp"})
	public void init() {
		register = new RegistrationPage(driver);
		register.pageNav();
	}
	@Test
	public void test() {

		register.clickNewUser();
		register.writeFirstName("Layth");
		register.writeLastName("Zaid");
		register.writeUserName("LarryZaid");
		register.writePassword("Password@_1234");
		register.handleRecaptcha();
		register.clickRegister();
		register.goToLogin();
		register.writeUserName("LarryZaid");
		register.writePassword("Password@_1234");
		register.clickLogin();
	}

}
