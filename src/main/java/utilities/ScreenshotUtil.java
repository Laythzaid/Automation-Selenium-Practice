package utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.apache.commons.io.file.*;

public class ScreenshotUtil {
	private WebDriver driver;

	private ScreenshotUtil() {
	}

	public static String getScreenshot(WebDriver driver, String testName) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
		String dirPath = System.getProperty("user.dir") + "/screenshots";
		String scrPath =  dirPath + "/" + testName + "_" + timestamp + ".png";
	
		try {
			File dir = new File(scrPath);
			
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(scrPath));
		} catch (IOException e) {
			throw new RuntimeException("Couldn't capture screenshot", e);
		}
		return "/screenshots" + testName + "_" + ".png";
	}
}
