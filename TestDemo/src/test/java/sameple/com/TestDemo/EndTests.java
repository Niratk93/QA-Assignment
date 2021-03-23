package sameple.com.TestDemo;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndTests {
	ExtentReports extent =null;
	ExtentTest test;
	WebDriver driver;
	@BeforeTest
	public void launchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	@AfterTest
	public void quitBrowser()
	{
		driver.quit();
	}
	@BeforeSuite
	public void generateReports()
	{
		String name="./Reports/ExtentReport_"+RandomStringUtils.randomAlphanumeric(10)+".html";
		extent = new ExtentReports(name);
	}
	@AfterSuite
	public void closeReports()
	{
		extent.flush();
	}
	@Test
	public void LoginTest() {
		test = extent.startTest("LoginTest");
		PageObject pageObject = new PageObject(driver, test);
		pageObject.login("standard_user", "secret_sauce");
		Assert.assertTrue(pageObject.verifyPageTitle());
		pageObject.verifyLogout();
		test.log(LogStatus.PASS, "LoginTest passed");
		extent.endTest(test);
	}

	@Test
	public void LogoutTest() {
		test = extent.startTest("logoutTest");
		PageObject pageObject = new PageObject(driver, test);
		pageObject.login("standard_user", "secret_sauce");
		Assert.assertTrue(pageObject.verifyLogout());
		test.log(LogStatus.PASS, "LogoutTest Pass");
		extent.endTest(test);
	}

	@Test
	public void LockUserTest() {
		test = extent.startTest("LockTest");
		PageObject pageObject = new PageObject(driver, test);
		boolean result = pageObject.verifyLockedUser("locked_out_user",
				"secret_sauce");
		Assert.assertTrue(result);
		test.log(LogStatus.PASS, "LockUser Pass");
		extent.endTest(test);
	}
	@Test
	public void CartTest() {
		test = extent.startTest("CartTest");
		PageObject pageObject = new PageObject(driver, test);
		pageObject.login("standard_user", "secret_sauce");
		Assert.assertTrue(pageObject.validateCart());
		pageObject.verifyLogout();
		test.log(LogStatus.PASS, "CartTest pass");
		extent.endTest(test);
	}
	@Test
	public void RemoveCart() {
		test = extent.startTest("RemoveCart");
		PageObject pageObject = new PageObject(driver, test);
		 pageObject.login("standard_user", "secret_sauce");
		 Assert.assertTrue(pageObject.validateRemoveCart());
		 pageObject.verifyLogout();
		test.log(LogStatus.PASS, "RemoveCart pass");
		extent.endTest(test);
	}
}
