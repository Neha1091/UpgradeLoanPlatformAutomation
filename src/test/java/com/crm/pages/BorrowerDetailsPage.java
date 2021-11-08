package com.crm.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.CommonCrmActions;
import com.crm.model.BorrowerInfo;

public class BorrowerDetailsPage extends CommonCrmActions {

	@FindBy(xpath = "//h1[contains(text(),\"Let's get started with some basic information\")]")
	WebElement borrowerDetailsLabel;

	@FindBy(name = "borrowerFirstName")
	WebElement borrowerFirstName;

	@FindBy(name = "borrowerLastName")
	WebElement borrowerLastName;

	@FindBy(name = "borrowerStreet")
	WebElement borrowerStreet;
	
	@FindBy(name = "borrowerCity")
	WebElement borrowerCity;

	@FindBy(name = "borrowerState")
	WebElement borrowerState;

	@FindBy(name = "borrowerZipCode")
	WebElement borrowerZipCode;

	@FindBy(name = "borrowerDateOfBirth")
	WebElement borrowerDateOfBirth;

	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	WebElement continueButton;

	// Initializing the Page Objects:
	public BorrowerDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyBorrowerDetailsLabelLabel() {
		return borrowerDetailsLabel.isDisplayed();
	}

	public BorrowerIncomePage enterBorrowerDetails(WebDriver driver, BorrowerInfo borrowerInfo)
			throws InterruptedException {
		verifyBorrowerDetailsLabelLabel();
		borrowerFirstName.sendKeys(borrowerInfo.getFirstName());
		borrowerLastName.sendKeys(borrowerInfo.getLastName());
		// TODO:select address from drop down
		borrowerStreet.sendKeys(borrowerInfo.getStreet());
		//wait for geo suggestions list to appear
		Thread.sleep(3000);
		borrowerStreet.sendKeys(Keys.TAB);
		borrowerCity.sendKeys(borrowerInfo.getCity());
		borrowerState.sendKeys(borrowerInfo.getState());
		borrowerZipCode.sendKeys(borrowerInfo.getZipCode());
		// format date of birthday in expected format
		borrowerDateOfBirth.sendKeys(borrowerInfo.getDateOfBirth());
		clickByJavaScript(driver, continueButton);
		return new BorrowerIncomePage(driver);

	}

}
