package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LocatorsUtil {
	private WebDriver driver;
	private static Properties prop;
	

	public LocatorsUtil(String filename) {
		prop = new Properties();
		try (InputStream loc = getClass().getClassLoader().getResourceAsStream(filename)) {
			prop.load(loc);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load file" + filename);
		}
		
	}
	
	
	public static String get(String text) {
		 return prop.getProperty(text);
	}
}
