package base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilities.ConfigReader;

public final class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		if (driver.get() == null) {
			String browser = ConfigReader.get("browser").toLowerCase();
			boolean isCI = Boolean.parseBoolean(System.getenv("IS_CI"));
			switch (browser) {
			case "chrome":
				ChromeOptions options = new ChromeOptions();
				
				if(isCI) {
					options.addArguments("--headless=new");
					options.addArguments("--window-size=1920,1080");
					options.addArguments("--disable-gpu");
				}
				driver.set(new ChromeDriver(options));
				break;
			case "firefox":
				driver.set(new FirefoxDriver());
				break;
			case "edge":
				driver.set(new EdgeDriver());
				break;
			default:
				throw new RuntimeException("Unsupported browser: " + browser);
			}
		}
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

}
