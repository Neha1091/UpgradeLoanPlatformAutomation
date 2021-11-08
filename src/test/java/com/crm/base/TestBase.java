package com.crm.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.util.TestUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestBase {

	public Properties prop;
	public TestUtil testUtil;
	public WebDriver driver;

	public TestBase() {
		try {
			prop = new Properties();
			InputStream inputStreamProp = getClass().getClassLoader().getResourceAsStream("config.properties");
			if (inputStreamProp == null) {
				log.error("Sorry, unable to find config.properties");
				return;
			}
			prop.load(inputStreamProp);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public WebDriver initialization(String url) throws InterruptedException {
		testUtil = new TestUtil();
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/ngupta/Downloads/chromedriver");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(url);
		return driver;

	}

}
