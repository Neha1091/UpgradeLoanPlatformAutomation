package com.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.CommonCrmActions;
import com.crm.model.Offer;

public class OfferPage extends CommonCrmActions {
	@FindBy(xpath = "//div[text() = 'Your Loan Amount']/following-sibling::div[1]/span[2]")
	WebElement loanAmount;

	@FindBy(xpath = "//h2[contains(text(),'You qualify for a discount on your debt payoff loan!')]")
	WebElement offerLabel;

	// choosed /preceding-sibling to find xpath so that in future if multiple offers
	// are present
	@FindBy(xpath = "//button[text( )='Select']/preceding-sibling::div[1]/ul[1]/li[1]/div")
	WebElement monthlyPayment;

	@FindBy(xpath = "//button[text( )='Select']/preceding-sibling::div[1]/ul[1]/li[2]/div")
	WebElement term;

	@FindBy(xpath = "//button[text( )='Select']/preceding-sibling::div[1]/ul[1]/li[3]/div")
	WebElement interestRate;

	@FindBy(xpath = "//button[text( )='Select']/preceding-sibling::div[1]/ul[1]/li[3]/div")
	WebElement apr;

	@FindBy(xpath = "//button[text( )='Select']/preceding-sibling::div[1]/ul[1]/li[4]/div")
	WebElement paidToDebt;

	// Initializing the Page Objects:
	public OfferPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateOfferPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public boolean validateOfferLabel(WebDriver driver) {
		explicitWaitForTheElement(driver,offerLabel,10);
		return offerLabel.isDisplayed();
	}

	public Offer getOfferDetails(WebDriver driver) {
		validateOfferLabel(driver);
		takeScreenshot(driver, "loanofferrates");
		Offer loanOffer = new Offer();
		loanOffer.setLoanAmount(loanAmount.getText());
		loanOffer.setMonthlyPayment(monthlyPayment.getText());
		loanOffer.setTerm(term.getText());
		loanOffer.setInterestRate(interestRate.getText());
		loanOffer.setApr(apr.getText());
		loanOffer.setPaidToDebt(paidToDebt.getText());
		return loanOffer;
	}

}
