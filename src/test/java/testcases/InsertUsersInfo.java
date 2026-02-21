package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import dataProviders.UsersInfoDataProvider;
import pages.FormSubmissionPage;

public class InsertUsersInfo extends BaseTest {
	
	FormSubmissionPage login;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		login = new FormSubmissionPage(driver);
		login.pageNav();
	}

	@Test(dataProvider = "UsersInfoDataProvider", dataProviderClass = UsersInfoDataProvider.class)
	public void InsertUserDetails( String name, String email, String UserCurtAddr, String UserPermAddr, boolean shouldPass) {
		login.insertUserDetails(name, email, UserCurtAddr, UserPermAddr, shouldPass);
		
	    if(shouldPass) {
	    	Assert.assertTrue(
	    			login.isFormSucceed(), 
	    			"Expected form submission output but none was shown"
	    			);
	  } else {
	    	Assert.assertTrue(
	    			login.isEmailInvalid(),
	    			"Email not valid, Form failed to submit");	
	    	}
	    	
	    }
}
	


