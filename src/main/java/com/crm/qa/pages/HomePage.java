package com.crm.qa.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase{

	
	@FindBy(xpath = "//td[contains(text(),'User: Demo User')]") 
	WebElement userNameLabel;
	
	@FindBy(xpath= "//a[contains (text(),'Contacts')]") 
	WebElement contactslink;
	
	@FindBy(xpath= "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	//Initializing the PageObjects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
		
	}
	
	public boolean verifyCorrectUsername() {
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink() {
		
		contactslink.click();
		return new ContactsPage();
	}
	
	public void clickOnNewContactLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactslink).build().perform();
		newContactLink.click();
	}
	
	
}
