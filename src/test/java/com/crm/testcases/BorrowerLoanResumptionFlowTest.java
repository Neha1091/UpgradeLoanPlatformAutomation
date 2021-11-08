package com.crm.testcases;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.model.BorrowerInfo;
import com.crm.model.Offer;
import com.crm.pages.BorrowerDetailsPage;
import com.crm.pages.BorrowerIncomePage;
import com.crm.pages.BorrowerResumptionPage;
import com.crm.pages.CreateAccountPage;
import com.crm.pages.HomePage;
import com.crm.pages.MenuBar;
import com.crm.pages.OfferPage;
import com.github.javafaker.Faker;
import com.util.DateTimeUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BorrowerLoanResumptionFlowTest extends TestBase {
	Faker faker;
	String loanAmount = "2,000";
	String borrowerAnnualIncome = "120,000";
	String borrowerAdditionalIncome = "5,000";

	public BorrowerLoanResumptionFlowTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		faker = new Faker();
	}

	private BorrowerInfo buildBorrowerInfoObject() throws ParseException {
		BorrowerInfo borrowerInfo = new BorrowerInfo();
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		borrowerInfo.setFirstName(faker.name().firstName());
		borrowerInfo.setLastName(faker.name().lastName());
		log.info("first name: " + borrowerInfo.getFirstName() + " last Name: " + borrowerInfo.getLastName());
		borrowerInfo.setStreet(faker.address().streetAddress());
		borrowerInfo.setCity(faker.address().city());
		// to avoid knockout and state loan amount limit hard coding a valida state
		borrowerInfo.setState("AZ");
		borrowerInfo.setZipCode(faker.address().zipCode());
		borrowerInfo.setDateOfBirth(
				dateTimeUtils.convertDateToString(faker.date().between(dateTimeUtils.converStringToDate("01011930"),
						dateTimeUtils.converStringToDate("01012000")), DateTimeUtils.MM_DD_YYYY));

		return borrowerInfo;
	}

	@Test(priority = 1)
	public void verifyCheckYourRateTest() throws InterruptedException, ParseException, IOException {
		//initialize the driver
		WebDriver driver = initialization(prop.getProperty("phone_lead_url"));
		MenuBar menuBar = new MenuBar(driver);
		HomePage homePage = new HomePage(driver);
		BorrowerResumptionPage borrowerResumptionPage = new BorrowerResumptionPage(driver);
		//apply for a loan in home page
		BorrowerDetailsPage leadInfoPage = homePage.checkYourRate(driver, loanAmount, "Debt Consolidation");
		BorrowerInfo borrowerInfo = buildBorrowerInfoObject();
		//create borrower details
		BorrowerIncomePage borrowerIncomePage = leadInfoPage.enterBorrowerDetails(driver, borrowerInfo);
		//enter total income
		CreateAccountPage createAccountPage = borrowerIncomePage.enterTotalIncome(driver, borrowerAnnualIncome,
				borrowerAdditionalIncome);
		String email = borrowerInfo.getFirstName() + "_" + borrowerInfo.getLastName()
				+ (int) ((Math.random() * (500 - 1)) + 1) + "@upgrade-challenge.com";
		String password = faker.internet().password(12, 13, true, true, true);
		log.info("email: " + email + " password: " + password);
		//enter email & password to create account and get loan offers
		OfferPage offerPage = createAccountPage.checkYourLoanOfferRate(driver, email, password);
		//borrower initial offers
		Offer loanOffer = offerPage.getOfferDetails(driver);
		Thread.sleep(5000);
		//sign out
		menuBar.signOut(driver);
		//welcome back url
		driver.navigate().to(prop.getProperty("resumption_lead_url"));
		//signin
		offerPage = borrowerResumptionPage.signintoAccount(driver, email, password);
		// welcome back loan offers
		Offer resumptionLoanOffer = offerPage.getOfferDetails(driver);
		Assert.assertEquals(loanOffer, resumptionLoanOffer, "both loan offers are same");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
