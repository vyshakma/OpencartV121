package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups = {"Master","Regression"})
	void login() {
		try {
			logger.info("----Test case Started----");
			HomePage homePage = new HomePage(driver);
			homePage.clickMyaccount();
			homePage.clickLogin();

			logger.info("Entering user credentials");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(p.getProperty("email"));
			logger.info("Email id entered");
			loginPage.setPassword(p.getProperty("password"));
			logger.info("Password entered");
			loginPage.clickLogin();
			logger.info("Clicked Login");

			logger.info("Verifying account");
			MyAccountPage myAccountPage = new MyAccountPage(driver);
			boolean status = myAccountPage.verifyAccount();
			Assert.assertTrue(status);
			logger.info("Account exist");
			logger.info("----Test case Passed----");
		} catch (Exception e) {
			logger.error("----Test case Failed----");
			Assert.fail();
		}
	}
}
