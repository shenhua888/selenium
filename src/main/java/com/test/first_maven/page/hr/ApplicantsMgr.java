package com.test.first_maven.page.hr;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.test.first_maven.base.ActionUtils;
import com.test.first_maven.base.Tools;

public class ApplicantsMgr {
	@FindBy(id = "10000004780011")
	@CacheLookup
	private WebElement personnel_system;
	
	@FindBy(id = "leftTree_6_switch")
	@CacheLookup
	private WebElement span;
	
	@FindBy(id = "leftTree_7_span")
	@CacheLookup
	private WebElement applicantsMgr;	
	
	private WebDriver driver;
	
	public ApplicantsMgr (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void enterApplicantsMgr() {
		personnel_system.click();
		span.click();
		applicantsMgr.click();
		Boolean flag = null;
		flag = ActionUtils.waitUntilVisible(driver, "//*[@id='10000004780032']", 10);
		Assert.assertTrue(flag);
	}
	
	public void clickUploadCV() {
		ActionUtils.enterFrameFromDef(driver, "10000004780032");
		ApplicantsMgrFrame applicantsFrame = new ApplicantsMgrFrame(driver);
		applicantsFrame.enterUploadCV();
		
	}

	public void upLoadCV(String dataFile) {
		ActionUtils.defaultFrame(driver);
		ActionUtils.enterFrameFromDef(driver, driver.findElement(By.xpath("//*[contains(@id,'ligerwindow')]")));
		VCinputPage vcInput = new VCinputPage(driver);
		vcInput.inputData(dataFile);		
	}
	
	public void addCV(String dataFile) {
		ActionUtils.enterFrameFromDef(driver, "10000004780032");
		ApplicantsMgrFrame applicantsFrame = new ApplicantsMgrFrame(driver);
		applicantsFrame.enterAddCV();
		Tools.wait(2);
		AddApplicants addApplicants = new AddApplicants(driver);
		addApplicants.addCV(dataFile);
	}

}
