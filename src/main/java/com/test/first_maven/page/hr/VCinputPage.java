package com.test.first_maven.page.hr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.test.first_maven.base.WebAction;
import com.test.first_maven.base.Tools;

public class VCinputPage {	
	@FindBy(id = "resumeName")
	@CacheLookup
	private WebElement applicantName;	
	
	@FindBy(xpath = "//*[@id='jobDeptName']/following-sibling::*[1]")
	@CacheLookup
	private WebElement jobDeptName;

	@FindBy(xpath = "//*[@id='jobPositionName']/following-sibling::*[1]")
	@CacheLookup
	private WebElement jobPosition;

	@FindBy(id = "reMobile")
	@CacheLookup
	private WebElement cellphone;
	
	@FindBy(id = "reEmail")
	@CacheLookup
	private WebElement email;
	//学历
	@FindBy(id = "reEdu")
	@CacheLookup
	private WebElement qualifications;
	
	@FindBy(id = "reEduSchool")
	@CacheLookup
	private WebElement school;	
	
	@FindBy(id = "reEduMajor")
	@CacheLookup
	private WebElement major;	
	
	@FindBy(id = "workCompany")
	@CacheLookup
	private WebElement company;	
	
	@FindBy(name = "jobDesc")
	@CacheLookup
	private WebElement jobDesc;	
	
	@FindBy(xpath = "//*[@id='attachment']/following-sibling::*[1]")
	@CacheLookup
	private WebElement attachment;	
	
	@FindBy(id = "dataFormSave")
	@CacheLookup
	private WebElement saveBtn;	
	
	private WebDriver driver;
	
	public VCinputPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void inputData(String dataFile) {
		Properties pps = new Properties();
		pps = Tools.readPropertiesFileObj(dataFile);
		jobDeptName.click();		
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");	
		WebAction.enterFrame(driver, "orgFrame");
		driver.findElement(By.xpath("//*[@value='" + pps.getProperty("jobDeptName") + "']/../input")).click();
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");
		driver.findElement(By.xpath("//*[text()='选择']")).click();
		WebAction.enterFirstFrame(driver, "//*[contains(@id,'ligerwindow')]");
		jobPosition.click();
		WebAction.enterFrameFromDef(driver, "frameDialog_");
		WebAction.enterFrame(driver, "dialogFrame");
		driver.findElement(By.xpath("//*[text()='" + pps.getProperty("jobPosition") + "']/../td/input")).click();
		WebAction.defaultFrame(driver);
		driver.findElement(By.xpath("//*[text()='确定']")).click();
		WebAction.enterFirstFrame(driver, "//*[contains(@id,'ligerwindow')]");
		applicantName.sendKeys(pps.getProperty("applicantName"));
		cellphone.sendKeys(pps.getProperty("cellphone"));
		email.sendKeys(pps.getProperty("email"));
		school.sendKeys(pps.getProperty("school"));
		major.sendKeys(pps.getProperty("major"));
		company.sendKeys(pps.getProperty("company"));
		WebAction.selectByValue(qualifications, pps.getProperty("qualifications"));
		attachment.click();
		Tools.wait(2);
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");
		driver.findElement(By.xpath("//*[@class='pull-left']/input")).click();
		Tools.selectFile(pps.getProperty("attachement"));
		WebAction.clickWithWait(driver, "//a[@ng-click='item.upload()']");
		Tools.wait(2);
		WebAction.defaultFrame(driver);
		driver.findElement(By.xpath("//div[text()='确定']")).click();
		WebAction.enterFirstFrame(driver, "//*[contains(@id,'ligerwindow')]");
		jobDesc.sendKeys(pps.getProperty("jobDesc"));
		saveBtn.click();
		WebAction.clickWithWait(driver, "//*[text()='否']");
		WebAction.enterFrameFromDef(driver, "10000004780032");
		WebAction.waitUntilVisible(driver, "//*[@id='reResumeJobItem']/tbody/tr[1]/td[2]/a", 10);
		Boolean flag = WebAction.isAddSuccess(driver.findElement(By.xpath("//*[@id='reResumeJobItem']/tbody/tr[1]/td[2]/a")), pps.getProperty("applicantName"));
		WebAction.defaultFrame(driver);
		Assert.assertTrue(flag);
	}

}
