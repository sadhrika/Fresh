package com.crm.qa.testcases;

import org.apache.poi.ss.formula.SheetNameFormatter;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactsPage;
	String sheetName = "contacts";
		
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
		Initialization();
		testutil = new TestUtil();
		contactsPage = new ContactsPage();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		testutil.switchToFrame();
		contactsPage = homepage.clickOnContactsLink();
		
			
	}
	
	
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"contact label is missing in page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest() {
		contactsPage.selectContactByName("1234.0 ");
		
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactByName("12345.0 ");
		contactsPage.selectContactByName("aa aaa");
		
	}
	
	@DataProvider
	public Object[][] getCrmTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
		
	}
	
	@Test(priority=4,dataProvider = "getCrmTestData")
	public void validateCreateNewContact(String title,String firstname,String lastname,String company,String com_pos) {
		homepage.clickOnNewContactLink();
		contactsPage.createNewContact(title,firstname,lastname,company,com_pos);
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
