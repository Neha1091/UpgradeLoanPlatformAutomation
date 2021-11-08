package com.crm.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CommonCrmActions {
	public void waitForElementThenClick(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		if (wait.until(ExpectedConditions.elementToBeClickable(webElement)) != null) {
			webElement.click();
		} else {
			Assert.fail("couldn't locate the element: " + webElement);
		}
	}

	public void takeScreenshot(WebDriver driver, String fileName) {
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// now copy the screenshot to desired location using copyFile
		try {
			FileUtils.copyFile(src, new File("../upgrade-loan-platform-automation/screenshots/" + fileName + ".png"));
		} catch (IOException e) {
			log.error("takeScreenshot error" + e);
		}

	}

	public void explicitWaitForTheElement(WebDriver driver, WebElement webElement, Integer seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));

	}

	public void clickByJavaScript(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
		executor.executeScript("arguments[0].click();", element);
	}

}
