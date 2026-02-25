package utilities;


import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties = new Properties();
	static {
		try (InputStream is = ConfigReader.class.getClassLoader()
				.getResourceAsStream("configfiles/config.properties")) {
			if (is == null) {
				throw new RuntimeException("Couldn't find file config.properties");
			}
			properties.load(is);
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load", e);
		}
	}

	public static String get(String configKey) {
		return properties.getProperty(configKey);
	}

}
