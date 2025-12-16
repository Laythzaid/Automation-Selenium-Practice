package testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class QaRegisterTest extends BaseTest {

	LoginPage login;

	@BeforeMethod
	public void init() {
		login = new LoginPage(driver);
	}

	@Test
	public void test() throws InterruptedException {

		login.clickNewUser();
		login.writeFirstName("Layth");
		login.writeLastName("Zaid");
		login.writeUserName("LarryZaid");
		login.writePassword("Password@_1234");
		login.handleRecaptcha();
		login.clickRegister();
		login.goToLogin();
		login.writeUserName("LarryZaid");
		login.writePassword("Password@_1234");
		login.clickLogin();
	}
}
