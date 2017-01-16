package com.test.first_maven.page.hr;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.test.first_maven.base.helper.JSONObject2;
import com.test.first_maven.base.selenium.WebAction;

public class UserMgrFrame {

	@FindBy(xpath = "//*[@class='drop']/a")
	@CacheLookup
	private WebElement adQuery;	

	@FindBy(xpath = "//*[@name='Q_jobNumber_SL']")
	@CacheLookup
	private WebElement jobNumber;

	@FindBy(id = "btnSearch")
	@CacheLookup
	private WebElement searchBtn;
	
	private WebDriver driver;
	
	public UserMgrFrame(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}

	public void imitateUser(String paras) {
		adQuery.click();
		JSONObject json= new JSONObject2(paras);
		WebAction.jsSendKeys(driver, jobNumber, (String)json.get("jobNumber"));
		searchBtn.click();
		WebAction.jsClick(driver, driver.findElement(By.xpath("//*[@id='sysUserItem']/tbody/tr/td[last()]//a[text()='切换用户']")));
	}
	

}
