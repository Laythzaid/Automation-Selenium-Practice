package Listeners;

import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import base.DriverFactory;
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
		WebDriver driver = DriverFactory.getDriver();

		String screenshotPath = ScreenshotUtil.getScreenshot(driver, result.getName());

		Reporter.log("Screenshot saved at > " + screenshotPath);
		log.error("!!! TEST FAILED:" + result.getMethod().getMethodName() + "!!!");
		log.error("!!! TEST FAILED:" + result.getThrowable().getMessage() + "!!!");
		Reporter.log("<br>");
		Reporter.log("<a href='" + screenshotPath + "' target='_blank'>");
		Reporter.log("<img src='" + screenshotPath + "' height='400' width='400'/>");
		Reporter.log("</a>");
	}

}
