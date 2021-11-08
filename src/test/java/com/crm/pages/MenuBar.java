package com.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.CommonCrmActions;

public class MenuBar extends CommonCrmActions{
	
	@FindBy(xpath="//label[contains(text(),'Menu')]")
	WebElement menuToggle;
	
	@FindBy(xpath="//a[contains(text(),'Sign Out')]")
	WebElement signOutAnchor;
	
	//Initializing the Page Objects:
		public MenuBar(WebDriver driver){
			PageFactory.initElements(driver, this);
		}
		
		public void signOut(WebDriver driver){
			waitForElementThenClick(driver,menuToggle);
			waitForElementThenClick(driver,signOutAnchor);

		}

	
		
	}

