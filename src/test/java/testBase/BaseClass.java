package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;	

	@BeforeClass(groups = {"Master","Sanity","Regression","DataDriven"})
	@Parameters({"OS","Browser"})
	public void setup(String os,String br) throws IOException {
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		switch(br.toLowerCase()) {
		case "chrome": driver = new ChromeDriver();break;
		case "firefox": driver = new FirefoxDriver();break;
		default : System.out.println("Invalid browser name");return;
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL"));
	}

	@AfterClass(groups = {"Master","Sanity","Regression","DataDriven"})
	public void tearDown() {
		driver.quit();
	}

	public String generateRandomString(int val) {
		String randomString = RandomStringUtils.randomAlphabetic(val);
		return randomString;
	}

	public String generateRandomNumber(int val) {
		String randomNumber = RandomStringUtils.randomNumeric(val);
		return randomNumber;
	}

	public String generateRandomAlphaNumeric() {
		return generateRandomString(3)+"@"+generateRandomNumber(3);
	}
	
	public String captureScreenShot(String tName) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String targetPath = System.getProperty("user.dir")+"\\Screenshots\\"+tName+"_"+timeStamp+".png";
		File targetFile = new File(targetPath);
		sourceFile.renameTo(targetFile);
		
		return targetPath;
	}

}
