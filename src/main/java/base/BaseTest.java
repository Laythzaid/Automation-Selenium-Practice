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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import utilities.LocatorsUtil;

public abstract class BaseTest {
	protected Properties prop = new Properties();
//	protected Properties loc = new Properties();
	protected WebDriver driver;
	private LocatorsUtil locUtil;
	private Properties loc;
	protected Logger log = LogManager.getLogger(getClass());
	
	public WebDriver getDriver() {
		return driver;
	}
	@BeforeTest
	public void initDriver() throws IOException {

		System.out.println("--- Starting Browser Setup (System.out) ---");
        log.info("--- Starting Browser Setup (Log4j) ---");
		String projectPath = System.getProperty("user.dir"); 
		FileReader configReader = new FileReader( projectPath + "//src//test//resources//configfiles//config.properties");
//		FileReader locatorsReader = new FileReader( projectPath + "//src//test//resources//configfiles//locators.properties");

		loc = new Properties();
		prop.load(configReader);
		loc.load(new FileReader(projectPath + "//src//test//resources//configfiles//locators.properties"));
//		locUtil.init(loc);

		if (prop.getProperty("browser").equals("chrome")) {
			driver = new ChromeDriver();
			driver.get(prop.getProperty("Url"));
		} else if (prop.getProperty("browser").equals("FireFox")) {
			driver = new FirefoxDriver();
			driver.get(prop.getProperty("Url"));
		} else if (prop.getProperty("browser").equals("edge")) {
			driver = new EdgeDriver();
			driver.get(prop.getProperty("Url"));
		}
		driver.manage().window().fullscreen();
		}


	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
