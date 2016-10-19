package com.test.first_maven.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BaiduHome {
	
	@FindBy(id = "kw")
	@CacheLookup
	private WebElement inputArea;
	
	@FindBy(id = "su")
	@CacheLookup
	private WebElement submitBtn;
	
	private WebDriver driver;
	
	public BaiduHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
//	public static WebDriver getDriver() {
//		return driver;
//	}
//	
	public ResultPage search(String keyWord) {
		inputArea.clear();
		inputArea.sendKeys(keyWord);
		submitBtn.click();
		return new ResultPage(driver);
	}
}
