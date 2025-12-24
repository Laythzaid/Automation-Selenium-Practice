package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import utilities.ConfigReader;
import utilities.ElementUtils;
import utilities.LocatorsUtil;

public abstract class BaseTest {
	protected WebDriver driver;
	private ElementUtils eleutils;
	protected Logger log = LogManager.getLogger(getClass());

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {
		log.info("---- Starting Browser Setup ----");
		driver = DriverFactory.getDriver();
		driver.get(ConfigReader.get("base.url") + ConfigReader.get("login.url"));
		driver.manage().window().fullscreen();
		eleutils = new ElementUtils(driver);
		eleutils.waitForPageLoad();
	}

	@AfterTest
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
