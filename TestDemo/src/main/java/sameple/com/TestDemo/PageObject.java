package sameple.com.TestDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;


public class PageObject {
	public ExtentTest test;
	public WebDriver driver;
	@FindBy(how=How.ID,using="user-name")
	WebElement userName;
	@FindBy(how=How.ID,using="password")
	WebElement passWord;
	@FindBy(how=How.XPATH,using="//input[@value='LOGIN']")
	WebElement login;
	@FindBy(how=How.XPATH,using="//h3[@data-test='error']")
	WebElement lockederrormessage;
	@FindBy(how=How.XPATH,using="//div[@class='bm-burger-button']")
	WebElement menu;
	@FindBy(how=How.ID,using="logout_sidebar_link")
	WebElement logout;
	@FindBy(how=How.XPATH,using="//button[text()='ADD TO CART']")
	WebElement firstItem;
	@FindBy(how=How.XPATH,using="//button[text()='REMOVE']")
	WebElement removeItem;
	@FindBy(how=How.XPATH,using="//a[@class='shopping_cart_link fa-layers fa-fw']/span")
	WebElement cartsize;
	public PageObject(WebDriver driver, ExtentTest test) {
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}

	public void login(String unameValue,String pValue){
		userName.clear();
		userName.sendKeys(unameValue);
		passWord.clear();
		passWord.sendKeys(pValue);
		login.click();
		
	}
		
	public boolean verifyLockedUser(String unameValue,String pValue){
	
		userName.clear();
		userName.sendKeys(unameValue);
		passWord.clear();
		passWord.sendKeys(pValue);
		login.click();
		PageFactory.initElements(driver, this);
		return lockederrormessage.getText().equals("Epic sadface: Sorry, this user has been locked out.");
	}
	public boolean verifyPageTitle()
	{
		return driver.getTitle().equals("Swag Labs");
	}
	public boolean verifyLogout(){
		menu.click();
		logout.click();
		return driver.getTitle().equals("Swag Labs");
		}
	public boolean validateCart()
	{
		
		firstItem.click();
		sleep();
		PageFactory.initElements(driver, this);
		int size=Integer.parseInt(cartsize.getText());
		removeItem.click();
		return size==1;
	}
	public boolean validateRemoveCart()
	{
		
		firstItem.click();
		PageFactory.initElements(driver, this);
		boolean result=removeItem.isDisplayed();
		removeItem.click();
		return result;
	}
	public void sleep()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
