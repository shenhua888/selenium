package com.test.first_maven.page.flow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.test.first_maven.base.WebAction;

public class SelectUserPage {
	@FindBy(name = "Q_fullname_SL")
	@CacheLookup
	private WebElement quryName;	
	
	@FindBy(id = "searchForm")
	@CacheLookup
	private WebElement searchForm;	
	
	@FindBy(xpath = "//*[@id='sysUserItem']/tbody/tr/td/input")
	@CacheLookup
	private WebElement firstOne;
	
	@FindBy(id = "btnSearch")
	@CacheLookup
	private WebElement btnSearch;
	
	private WebDriver driver;
	
	public SelectUserPage(WebDriver driver) {
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");
		WebAction.enterFrame(driver, "userListFrame");
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void search(String name) {
		quryName.sendKeys(name);
		searchForm.submit();
	}
	
	public void selectFirst() {
		firstOne.click();
		clickSubmit();
	}	
	
//	public void selectRadio() {
//		driver.findElement(By.xpath("//*[@id='sysUserItem']/tbody/tr/td/input")).click();
//		clickSubmit();
//	}
	
	public void clickSubmit() {
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");
		driver.findElement(By.xpath("//span[text()='选择']")).click();
		WebAction.defaultFrame(driver);
	}	
}
