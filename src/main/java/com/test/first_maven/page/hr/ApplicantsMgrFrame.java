package com.test.first_maven.page.hr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.test.first_maven.base.ActionUtils;

public class ApplicantsMgrFrame {
	@FindBy(id = "newAdd")
	@CacheLookup
	private WebElement uploadBtn;
	
	@FindBy(xpath = "//a[text()='添加']")
	@CacheLookup
	private WebElement addBtn;	
	
	private WebDriver driver;
	
	public ApplicantsMgrFrame (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void enterUploadCV () {
		uploadBtn.click();	
	}
	
	public void enterAddCV () {
		addBtn.click();	
	}

}
