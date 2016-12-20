package com.test.first_maven.page.flow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.test.first_maven.base.selenium.WebAction;

public class SelectVCPage {
	@FindBy(name = "Q_re_name_S")
	@CacheLookup
	private WebElement quryName;	
	
	@FindBy(name = "Q_re_mobile_S")
	@CacheLookup
	private WebElement quryPhone;	
	
	@FindBy(xpath = "//*[@id='bpmFormDialogTable']/tbody/tr/td/input")
	@CacheLookup
	private WebElement firstRadio;
	
	@FindBy(id = "btnSearch")
	@CacheLookup
	private WebElement btnSearch;
	
	private WebDriver driver;
	
	public SelectVCPage(WebDriver driver) {
		WebAction.enterFrameFromDef(driver, "frameDialog_");
		WebAction.enterFrame(driver, "dialogFrame");
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void search(String name) {
		quryName.sendKeys(name);
		btnSearch.click();
	}
	
	public void selectFirst() {
		firstRadio.click();
		clickSubmit();
	}	
	
	public void clickSubmit() {
		WebAction.defaultFrame(driver);
		driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[text()='确定']")).click();
	}		
}
