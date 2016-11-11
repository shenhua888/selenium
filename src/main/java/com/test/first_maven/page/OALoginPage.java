package com.test.first_maven.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.test.first_maven.base.WebAction;

public class OALoginPage {
	
	@FindBy(name = "username")
	@CacheLookup
	private WebElement userName;
	
	@FindBy(name = "password")
	@CacheLookup
	private WebElement password;
	
	@FindBy(linkText = "登录")
	@CacheLookup
	private WebElement loginBtn;	
	
	private WebDriver driver;
	
	public OALoginPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void login (String uName, String password) throws InterruptedException {
		userName.clear();
		userName.sendKeys(uName);
		this.password.clear();
		this.password.sendKeys(password);
		loginBtn.click();
		Boolean flag = WebAction.waitUntilVisible(driver, "//*[@name='home']", 10);
		Assert.assertTrue(flag);
	}
}
