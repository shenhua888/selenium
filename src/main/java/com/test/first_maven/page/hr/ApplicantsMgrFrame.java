package com.test.first_maven.page.hr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.test.first_maven.base.Tools;

public class ApplicantsMgrFrame {
	@FindBy(id = "newAdd")
	@CacheLookup
	private WebElement uploadBtn;
	
	@FindBy(xpath = "//a[text()='添加']")
	@CacheLookup
	private WebElement addBtn;	

	@FindBy(id = "sendInterview")
	@CacheLookup
	private WebElement sendInterviewBtn;
	
	@FindBy(name = "Q_resumeName_SL")
	@CacheLookup
	private WebElement qName;

	@FindBy(id = "btnSearch")
	@CacheLookup
	private WebElement searchBtn;
	
	@FindBy(xpath = "//*[@id='reResumeJobItem']/tbody/tr[1]/td[1]/input")
	@CacheLookup
	private WebElement firstAppName;	
	
	private WebDriver driver;
	
	public ApplicantsMgrFrame(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void enterUploadCV() {
		uploadBtn.click();	
	}
	
	public void enterAddCV() {
		addBtn.click();	
	}
	
	public void sendInterview(String name) {
		search(name);
		Tools.wait(1);
		firstAppName.click();
		Tools.wait(1);
		sendInterviewBtn.click();
	}
	
	public void search(String name) {
		qName.sendKeys(name);
		searchBtn.click();		
	}	
	
}
