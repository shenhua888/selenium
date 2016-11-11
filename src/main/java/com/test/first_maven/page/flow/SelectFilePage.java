package com.test.first_maven.page.flow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.test.first_maven.base.WebAction;
import com.test.first_maven.base.Tools;

public class SelectFilePage {	
	@FindBy(xpath = "//*[@class='pull-left']/input")
	@CacheLookup
	private WebElement selectBtn;
	
	private WebDriver driver;
	
	public SelectFilePage(WebDriver driver) {
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void selectFile(String filePath) {
		selectBtn.click();
		Tools.selectFile(filePath);
		Tools.wait(1);
		WebAction.clickWithWait(driver, "//a[@ng-click='item.upload()']");
		Tools.wait(2);
		WebAction.defaultFrame(driver);
		driver.findElement(By.xpath("//div[text()='确定']")).click();
	}
}
