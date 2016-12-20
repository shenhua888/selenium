package com.test.first_maven.page.flow;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.test.first_maven.base.helper.JSONObject2;
import com.test.first_maven.base.selenium.WebAction;

public class EntryConfirm {
	@FindBy(xpath = "//input[@value='报到']")
	@CacheLookup
	private WebElement register;
	
	@FindBy(id = "confirDate")
	@CacheLookup
	private WebElement firstDay;

	@FindBy(id = "confirSocialPlace")
	@CacheLookup
	private WebElement place;

	@FindBy(xpath = "//input[@value='合格']")
	@CacheLookup
	private WebElement healthCheck; 

	@FindBy(id = "confirContractType")
	@CacheLookup
	private WebElement contractType; 

	@FindBy(id = "confirConType1")
	@CacheLookup
	private WebElement contractType2;

	@FindBy(id = "confirContractUnit")
	@CacheLookup
	private WebElement contractUnit;

	@FindBy(id = "confirConPeriod")
	@CacheLookup
	private WebElement contractPeriod;

	@FindBy(id = "sfwcNoteIsDo")
	@CacheLookup
	private WebElement selectAll;
	
	private WebDriver driver;
	
	public EntryConfirm (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
		driver.manage().window().maximize();
	}
	
	public void input(String paras) {
		JSONObject json= new JSONObject2(paras);
		register.click();
		WebAction.jsSendKeys(driver, firstDay, (String)json.get("firstDay"));
		WebAction.selectByValue(place, (String)json.get("place"));
		healthCheck.click();
		WebAction.selectByValue(contractType, (String)json.get("contractType"));
		WebAction.selectByValue(contractType2, (String)json.get("contractType2"));
		WebAction.jsSendKeys(driver, contractUnit, (String)json.get("contractUnit"));
		contractPeriod.sendKeys((String)json.get("years"));
		selectAll.click();
	}
}
