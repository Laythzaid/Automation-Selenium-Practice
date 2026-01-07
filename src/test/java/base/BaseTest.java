package base;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.databind.cfg.ConfigFeature;

import utilities.ConfigReader;
import utilities.ElementUtils;

public abstract class BaseTest {
	protected WebDriver driver;
	private ElementUtils eleutils;
	protected Logger log = LogManager.getLogger(getClass());
	


	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {
		log.info("---- Starting Browser Setup ----");
		driver = DriverFactory.getDriver();
		driver.get(ConfigReader.get("base.url"));
		driver.manage().window().fullscreen();
		eleutils = new ElementUtils(driver);
		eleutils.waitForPageLoad();
	}
	protected void navigateTo(String urlKey) {
		driver.get(ConfigReader.get("base.url") + ConfigReader.get(urlKey));
		driver.manage().window().maximize();
	}

	@AfterTest
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
