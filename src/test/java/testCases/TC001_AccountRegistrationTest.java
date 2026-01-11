package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterAccountPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups = {"Master","Sanity"})
	void registerAccountTest() {
		try {
			logger.info("----Test case Started----");
			HomePage homePage = new HomePage(driver);
			homePage.clickMyaccount();
			homePage.clickRegister();
			
			logger.info("Entering user details");
			RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
			registerAccountPage.setFirstname(generateRandomString(5));
			registerAccountPage.setLastname(generateRandomString(4));
			registerAccountPage.setEmail(generateRandomString(6)+"@gmail.com");
			registerAccountPage.setTelephone(generateRandomNumber(10));
			String password = generateRandomAlphaNumeric();
			registerAccountPage.setPassword(password);
			registerAccountPage.setConfirmpwd(password);
			registerAccountPage.clickSubscribe();
			registerAccountPage.clickCheckPolicy();
			registerAccountPage.clickSubmit();
			String msg = registerAccountPage.getSuccessMsg();
			logger.info("Validating expected message");
			Assert.assertEquals(msg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("----Testcase Failed!----");
			Assert.fail();
		}
		logger.info("----Test case passed----");
	}
	
}
