package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LocatorsUtil {
	private WebDriver driver;
	private static Properties properties = new Properties();

	static {
		try (InputStream locators = LocatorsUtil.class.getClassLoader()
				.getResourceAsStream("configfiles/locators.properties")) {
			if (locators == null) {
				throw new RuntimeException("Couldn't find file config.properties");
			}
			properties.load(locators);
		}

		catch (IOException e) {
			throw new RuntimeException("Couldn't load file");
		}

	}

	public static String get(String locatorKey) {
		String value = properties.getProperty(locatorKey);
		if (locatorKey == null) {
			throw new RuntimeException("locator not found for key" + locatorKey);
		}
		return value;

	}
}
