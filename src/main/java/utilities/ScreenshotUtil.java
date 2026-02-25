package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenshotUtil {
	private WebDriver driver;

	private ScreenshotUtil() {
	}

	public static String getScreenshot(WebDriver driver, String testName) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
		String baseDir = System.getProperty("user.dir");
		String screenshotDir = baseDir + File.separator + "screenshots";
		String fileName = testName + "_" + timestamp + ".png";
		String fullPath = screenshotDir + File.separator + fileName;
		try {
			File dir = new File(screenshotDir);

			if (!dir.exists()) {
				dir.mkdirs();
			}
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destination = new File(fullPath);
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't capture screenshot", e);
		}
		return "screenshots/" + fileName;
	}
}
