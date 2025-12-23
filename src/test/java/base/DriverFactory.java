package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
			switch (browser) {
			case "chrome":
				driver.set(new ChromeDriver());
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
