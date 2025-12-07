package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	public static String browser = "chrome";
	public static WebDriver driver;
	
	@BeforeSuite
	public void initDriver() {
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if (browser.equals("FireFox")) {
			driver = new FirefoxDriver();
		}  
		else if (browser.equals("Edge")) {
			driver = new EdgeDriver();
		}
	}
	
	@AfterSuite
	public void closeDriver() {
		driver.close();
	}
	

}
