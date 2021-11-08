package com.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.CommonCrmActions;

public class BorrowerIncomePage extends CommonCrmActions {

	@FindBy(xpath = "//h1[contains(text(),'How much money do you make in a year?')]")
	WebElement borrowerIncomeLabel;

	@FindBy(name = "borrowerIncome")
	WebElement borrowerIncome;

	@FindBy(name = "borrowerAdditionalIncome")
	WebElement borrowerAdditionalIncome;

	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	WebElement continueButtonToCreateAccountPage;

	// Initializing the Page Objects:
	public BorrowerIncomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyBorrowerIncomeLabel() {
		return borrowerIncomeLabel.isDisplayed();
	}

	public CreateAccountPage enterTotalIncome(WebDriver driver, String borrowerIncomeInput,
			String borrowerAdditionalIncomeInput) throws InterruptedException {
		verifyBorrowerIncomeLabel();
		borrowerIncome.sendKeys(borrowerIncomeInput);
		borrowerAdditionalIncome.sendKeys(borrowerAdditionalIncomeInput);
		clickByJavaScript(driver, continueButtonToCreateAccountPage);
		return new CreateAccountPage(driver);
	}

}
