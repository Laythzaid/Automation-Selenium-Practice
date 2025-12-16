package Listeners;

import java.io.IOException;

import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import base.BaseTest;
import testcases.QaRegisterTest;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {
	Logger log = org.apache.logging.log4j.LogManager.getLogger(TestListener.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		log.info("=== STARTING TEST: " + result.getMethod().getMethodName() + " ===");
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("=== TEST PASSED: " + result.getMethod().getMethodName() + " ===");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriver();

	    String screenshotPath = 
	    		ScreenshotUtil.getScreenshot(driver, result.getName());

	    Reporter.log("Screenshot saved at > " + screenshotPath);
		log.error("!!! TEST FAILED:" + result.getMethod().getMethodName() + "!!!");
		log.error("!!! TEST FAILED:" + result.getThrowable().getMessage() + "!!!");

	}

}
