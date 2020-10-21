package myProject.practest.test.helper.browserConfig.config;

import myProject.practest.test.helper.browserConfig.BrowserType;

public interface ConfigReader {
	
	public int getImplicitWait();
	public int getExplicitWait();
	public int pageLoadTime();
	public BrowserType getBrowserType();
	public String getUrl();
	public String getUserName();
	public String getPassword();
}
