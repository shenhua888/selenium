package com.test.first_maven.page.hr;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.test.first_maven.base.helper.Tools;
import com.test.first_maven.base.selenium.WebAction;
import com.test.first_maven.page.flow.SelectUserPage;

public class SendInterviewPage {
	@FindBy(xpath = "//*[@id='ivAdminName']/following-sibling::*[1]")
	@CacheLookup
	private WebElement selectAdmin;	
	
	@FindBy(name = "interviewDesc")
	@CacheLookup
	private WebElement remark;
	
	@FindBy(id = "dataFormSave")
	@CacheLookup
	private WebElement submitBtn;		
	
	private WebDriver driver;
	
	public SendInterviewPage (WebDriver driver) {
		this.driver = driver;
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void inputData(Properties pps) {
		selectAdmin.click();
		SelectUserPage uPage = new SelectUserPage(driver);
		uPage.search(pps.getProperty("admin"));
		Tools.wait(1);
		uPage.selectFirst();
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");
		remark.sendKeys(pps.getProperty("remark"));
//		WebAction.jsSendKeys(driver, remark, pps.getProperty("remark"));
		submit();
	}
	
	public void submit() {
		submitBtn.click();
		WebAction.enterFrameFromDef(driver, "10000004780032");
		WebAction.waitUntilVisible(driver, "//div[text()='发送面试 成功!']", 15);
		driver.findElement(By.xpath("//div[text()='确定']")).click();
	}
	
}
