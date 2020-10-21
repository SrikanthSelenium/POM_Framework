package myProject.practest.test.helper.browserConfig.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import myProject.practest.test.helper.browserConfig.BrowserType;
import myProject.practest.test.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {
	private static FileInputStream file;
	public static Properties OR;
	static {
		String filepath = ResourceHelper.getResourcePath("src\\main\\resources\\configfile\\config.properties");
		try {
			file = new FileInputStream(new File(filepath));
			OR = new Properties();
			OR.load(file);
		} catch (Exception e) {
		}

	}

	public int getImplicitWait() {

		return Integer.parseInt(OR.getProperty("impliciwait"));
	
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitwait"));
	}

	public int pageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}
	public String getUrl() {
		if(System.getProperty("url")!=null){
			return System.getProperty("url");
		}
		return OR.getProperty("applicationUrl");
	}

	public String getUserName() {
		if(System.getProperty("userName")!=null){
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}

	public String getPassword() {
		if(System.getProperty("password")!=null){
			return System.getProperty("password");
		}
		return OR.getProperty("password");
	}

}
