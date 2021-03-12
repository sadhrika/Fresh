package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


public class HomePageTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
		Initialization();
		loginpage = new LoginPage();
		homepage=loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		testutil = new TestUtil();
		contactsPage = new ContactsPage();
	}
	
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homepagetitle=homepage.verifyHomePageTitle();
		Assert.assertEquals(homepagetitle, "CRMPRO","Home Page title not matched"); 
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		testutil.switchToFrame();
		Assert.assertTrue(homepage.verifyCorrectUsername());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testutil.switchToFrame();
		contactsPage = homepage.clickOnContactsLink();
	}
}
