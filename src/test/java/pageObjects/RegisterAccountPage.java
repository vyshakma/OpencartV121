package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage {
	
	public RegisterAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "input-firstname")
	WebElement txtFirstname;
	
	@FindBy(id = "input-lastname")
	WebElement txtLastname;
	
	@FindBy(id = "input-email")
	WebElement txtEmail;
	
	@FindBy(id = "input-telephone")
	WebElement txtTelephone;
	
	@FindBy(id = "input-password")
	WebElement txtPassword;
	
	@FindBy(id = "input-confirm")
	WebElement txtConfirmpwd;
	
	@FindBy(xpath = "//input[@name='newsletter' and @value='0']")
	WebElement btnSubscribe;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement checkPolicy;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement btnSubmit;
	
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement successMsg;
	
	public void setFirstname(String firstName) {
		txtFirstname.sendKeys(firstName);
	}
	
	public void setLastname(String lastName) {
		txtLastname.sendKeys(lastName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String phone) {
		txtTelephone.sendKeys(phone);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmpwd(String confirmPwd) {
		txtConfirmpwd.sendKeys(confirmPwd);
	}
	
	public void clickSubscribe() {
		btnSubscribe.click();
	}
	
	public void clickCheckPolicy() {
		checkPolicy.click();
	}
	
	public void clickSubmit() {
		btnSubmit.click();
	}
	
	public String getSuccessMsg() {
		return successMsg.getText();
	}
}
