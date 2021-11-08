package com.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.CommonCrmActions;

public class BorrowerResumptionPage extends CommonCrmActions {

	@FindBy(xpath = "//h2[contains(text(),'Welcome Back!')]")
	WebElement welcomeBackPageLabel;

	@FindBy(name = "username")
	WebElement userName;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[contains(text(),'Sign in to your account')]")
	WebElement signIn;

	// Initializing the Page Objects:
	public BorrowerResumptionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateHomePageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public boolean validateWelcomeBackPageLabel() {
		return welcomeBackPageLabel.isDisplayed();
	}

	public OfferPage signintoAccount(WebDriver driver, String emailInput, String passwordInput)
			throws InterruptedException {
		validateWelcomeBackPageLabel();
		userName.sendKeys(emailInput);
		password.sendKeys(passwordInput);
		clickByJavaScript(driver, signIn);
		return new OfferPage(driver);
	}

}
