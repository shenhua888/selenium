package com.test.first_maven.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.test.first_maven.base.selenium.WebAction;
import com.test.first_maven.page.hr.ApplicantsMgrFrame;
import com.test.first_maven.page.hr.UserMgrFrame;

public class UserMgr {

	@FindBy(xpath = "//*[@class='nav_button']/a/img")
	@CacheLookup
	private WebElement moreBtn;
	
//	@FindBy(xpath = "//*[@title='组织管理']/../span")
//	@CacheLookup
//	private WebElement orgMgr;
	
	@FindBy(id = "leftTree_1_span")
	@CacheLookup
	private WebElement userMgr;	
	
	private WebDriver driver;
	
	public UserMgr (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void enterUserMgr() {
		moreBtn.click();
		WebAction.jsClick(driver, driver.findElement(By.xpath("//*[@title='组织管理']/../span")));
		userMgr.click();
		WebAction.enterFrameFromDef(driver, "46");
		Boolean flag = null;
		flag = WebAction.waitUntilVisible(driver, "//*[@id='sysUserItem']", 10);
		Assert.assertTrue(flag);
	}
	
	public void imitateUser(String paras) {	
		UserMgrFrame userFrame = new UserMgrFrame(driver);
		userFrame.imitateUser(paras);	
		WebAction.defaultFrame(driver);
	}

}
