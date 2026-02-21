package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import dataProviders.UsersInfoDataProvider;
import pages.DataSetSubmissionPage;

public class UsersSubmissionDataProv extends BaseTest {
	
	DataSetSubmissionPage login;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		login = new DataSetSubmissionPage(driver);
		
	}

	@Test(dataProvider = "UsersInfoDataProvider", dataProviderClass = UsersInfoDataProvider.class)
	public void InsertUserData( String name, String email, String UserCurtAddr, String UserPermAddr, boolean shouldPass) {
		login.pageNav();
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
	


