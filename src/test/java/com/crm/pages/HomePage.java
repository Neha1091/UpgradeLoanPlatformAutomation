package com.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.CommonCrmActions;

public class HomePage extends CommonCrmActions {
	@FindBy(name = "desiredAmount")
	WebElement loanAmount;

	@FindBy(name = "loan-purpose")
	WebElement loanPurpose;

	@FindBy(xpath = "//h1[contains(text(),'Personal Loans up to $50,000')]")
	WebElement getStartedLabel;

	@FindBy(xpath = "//button[contains(text(),'Check your rate')]")
	WebElement checkYourRateButton;

	// Initializing the Page Objects:
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateHomePageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public boolean validateHomePageImage() {
		return getStartedLabel.isDisplayed();
	}

	public BorrowerDetailsPage checkYourRate(WebDriver driver, String loanAmountInput, String loanPurposeInput)
			throws InterruptedException {
		validateHomePageImage();
		loanAmount.sendKeys(loanAmountInput);
		loanPurpose.sendKeys(loanPurposeInput);
		clickByJavaScript(driver, checkYourRateButton);
		return new BorrowerDetailsPage(driver);
	}

}
