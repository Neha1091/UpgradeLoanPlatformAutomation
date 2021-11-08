package com.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.CommonCrmActions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CreateAccountPage extends CommonCrmActions {

	@FindBy(name = "username")
	WebElement userName;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "agreements")
	WebElement agreementsCheckBox;

	@FindBy(xpath = "//button[contains(text(),'Check Your Rate')]")
	WebElement submitPiiButton;

	@FindBy(xpath = "//h1[contains(text(),'Last step before you get your rate')]")
	WebElement createAccoutLabel;

	// Initializing the Page Objects:
	public CreateAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateLoginPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public boolean validateCreateAccoutLabel() {
		return createAccoutLabel.isDisplayed();
	}

	public OfferPage checkYourLoanOfferRate(WebDriver driver, String userNameInput, String passwordInput)
			throws InterruptedException {
		validateCreateAccoutLabel();
		userName.sendKeys(userNameInput);
		password.sendKeys(passwordInput);
		if (!agreementsCheckBox.isSelected()) {
			log.info("I am seeing checkbox unchecked");
			Actions actions = new Actions(driver);
			actions.moveToElement(agreementsCheckBox).click().perform();
		}
		clickByJavaScript(driver, submitPiiButton);
		return new OfferPage(driver);
	}

}
