package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_DataDrivenLoginTest extends BaseClass{

	@Test(dataProvider = "getLoginData", dataProviderClass = DataProviders.class, groups = {"Master","DataDriven"})
	void login(String username,String password,String res) {
		try {
			logger.info("----Test case Started----");
			HomePage homePage = new HomePage(driver);
			homePage.clickMyaccount();
			homePage.clickLogin();

			logger.info("Entering user credentials");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(username);
			logger.info("Email id entered");
			loginPage.setPassword(password);
			logger.info("Password entered");
			loginPage.clickLogin();
			logger.info("Clicked Login");

			logger.info("Verifying account");
			MyAccountPage myAccountPage = new MyAccountPage(driver);
			boolean status = myAccountPage.verifyAccount();
			if(res.equalsIgnoreCase("Valid")) {
				Assert.assertTrue(status, "Test case passed");
			}
			else if(res.equalsIgnoreCase("Invalid")) {
				Assert.assertFalse(status, "Test case passed");
			}
			else {
				logger.info("Invalid result");
				Assert.fail();
			}
			if(status) {
				myAccountPage.clickLogout();
			}				
		} catch (Exception e) {
			logger.error("----Test case Failed----");
			Assert.fail();
		}
	}
	
}
