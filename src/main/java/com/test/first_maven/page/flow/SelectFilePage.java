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
		WebAction.clickWithWait(driver, "//span[text()='上传']");
		WebElement ele = driver.findElement(By.xpath("//*[@role='progressbar']"));
		for (int i=0;i<10;i++) {
			if (ele.getAttribute("style").equals("width: 100%;")) {
				break;
			} else {
				Tools.wait(1);
			}
		}		
		WebAction.defaultFrame(driver);
		driver.findElement(By.xpath("//div[text()='确定']")).click();
	}
}
