package com.test.first_maven.page.flow;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.test.first_maven.base.WebAction;
import com.test.first_maven.base.Tools;

public class HireEmpPage {
	@FindBy(xpath = "//*[@id='empReName']/following-sibling::*[1]")
	@CacheLookup
	private WebElement selectAptName;
	
	@FindBy(id = "empIvUser")
	@CacheLookup
	private WebElement empIvUser;
	
	@FindBy(xpath = "//*[@id='empIvUserName']/following-sibling::*[1]")
	@CacheLookup
	private WebElement selectInterviewer;
	
	@FindBy(xpath = "//*[@id='empHelpUserName']/following-sibling::*[1]")
	@CacheLookup
	private WebElement selectHelpUser;

	@FindBy(xpath = "//*[@id='fileIds1']/following-sibling::*[1]")
	@CacheLookup
	private WebElement citizenCard;
	
	@FindBy(xpath = "//*[@id='fileIds2']/following-sibling::*[1]")
	@CacheLookup
	private WebElement idCard;	
	
	@FindBy(xpath = "//*[@id='fileIds3']/following-sibling::*[1]")
	@CacheLookup
	private WebElement education;
		
	@FindBy(id = "empType")
	@CacheLookup
	private WebElement empType;
	
	@FindBy(id = "empDemandSource")
	@CacheLookup
	private WebElement empDemandSource;
	
	@FindBy(name = "v_empJobCategory_S")
	@CacheLookup
	private WebElement empJobCategory;
	
	@FindBy(id = "empPosGrade")
	@CacheLookup
	private WebElement empPosGrade;
	
	@FindBy(xpath = "//*[@name='v_empIsOntrial_S']")
	@CacheLookup
	private WebElement probation;
	
	@FindBy(xpath = "//*[@name='v_empIsOntrial_S']/../input[2]")
	@CacheLookup
	private WebElement noProbation;

	@FindBy(id = "empUserType1")
	@CacheLookup
	private WebElement empUserType;

	@FindBy(id = "empApproach")
	@CacheLookup
	private WebElement empApproach;

	@FindBy(id = "empEntryDate")
	@CacheLookup
	private WebElement empEntryDate;
	
	@FindBy(id = "empProbationPeriod")
	@CacheLookup
	private WebElement empProbationPeriod;
	
	@FindBy(id = "empSalarym")
	@CacheLookup
	private WebElement empSalarym;	
	
	@FindBy(id = "empProbationSalarym")
	@CacheLookup
	private WebElement empProbationSalarym;
	
	@FindBy(id = "empProbationSalaryy")
	@CacheLookup
	private WebElement empProbationSalaryy;

	@FindBy(id = "empTargetProduct")
	@CacheLookup
	private WebElement empTargetProduct;
	
	@FindBy(id = "empCompletedProduct")
	@CacheLookup
	private WebElement empCompletedProduct;
	
	@FindBy(xpath = "//*[@name='v_empIsNeedCard_S']")
	@CacheLookup
	private WebElement needCard;
	
	@FindBy(xpath = "//*[@name='v_empIsNeedCard_S']/../input[2]")
	@CacheLookup
	private WebElement noNeedCard;
	
	@FindBy(id = "empIvDesc")
	@CacheLookup
	private WebElement empIvDesc;	

	@FindBy(id = "empDesc")
	@CacheLookup
	private WebElement empDesc;
		
	@FindBy(id = "empCardBegin")
	@CacheLookup
	private WebElement empCardBegin;
	
	@FindBy(id = "empCardEnd")
	@CacheLookup
	private WebElement empCardEnd;
	
	@FindBy(xpath = "//*[@id='empApproachUserTd']/a")
	@CacheLookup
	private WebElement empApproachUser;	

	@FindBy(id = "empApproachRelation")
	@CacheLookup
	private WebElement empApproachRelation;
	
	@FindBy(xpath = "//*[text()='提交']")
	@CacheLookup
	private WebElement submitBtn;
	
	private WebDriver driver;
	
	public HireEmpPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
		driver.manage().window().maximize();
	}
	
	public void hire(String dataFile) {
		Properties pps = new Properties();
		pps = Tools.readPropertiesFileObj(dataFile);
		selectAptName.click();
		SelectVCPage vcPage = new SelectVCPage(driver);
		vcPage.search(pps.getProperty("applicantName"));
		Tools.wait(1);
		vcPage.selectFirst();
		Tools.wait(1);
		
		String interviewerValue = empIvUser.getAttribute("value");
		if ("".equals(interviewerValue)) {
			selectInterviewer.click();
			SelectUserPage uPage = new SelectUserPage(driver);
			uPage.search(pps.getProperty("interviewer"));
			Tools.wait(1);
			uPage.selectFirst();
			empIvDesc.sendKeys(pps.getProperty("empIvDesc"));
		} 

		selectHelpUser.click();
		SelectUserPage uPage2 = new SelectUserPage(driver);
		uPage2.search(pps.getProperty("helper"));
		Tools.wait(1);
		uPage2.selectFirst();
		idCard.click();
		SelectFilePage selectFile = new SelectFilePage(driver);
		selectFile.selectFile(pps.getProperty("idCardPath"));
		education.click();
		SelectFilePage selectFile2 = new SelectFilePage(driver);
		selectFile2.selectFile(pps.getProperty("education"));
		citizenCard.click();
		SelectFilePage selectFile3 = new SelectFilePage(driver);
		selectFile3.selectFile(pps.getProperty("citizenCard"));		
		WebAction.selectByValue(empType, pps.getProperty("empType"));
		WebAction.selectByValue(empDemandSource, pps.getProperty("empDemandSource"));
		WebAction.selectByVisibleText(empJobCategory, pps.getProperty("empJobCategory"));
		WebAction.selectByValue(empPosGrade, pps.getProperty("empPosGrade"));
		if ("是".equals(pps.getProperty("probation"))) {
			probation.click();
			WebAction.selectByVisibleText(empUserType, pps.getProperty("empUserType"));
		} else {
			noProbation.click();
		}
		WebAction.selectByValue(empApproach, pps.getProperty("empApproach"));
		Tools.wait(1);
		if (empApproachUser.isDisplayed()) {
			empApproachUser.click();
			SelectUserPage uPage = new SelectUserPage(driver);
			uPage.search(pps.getProperty("empApproachUser"));
			Tools.wait(1);
			uPage.selectFirst();
			if (empApproachRelation.isDisplayed()) {
				WebAction.selectByValue(empApproachRelation, pps.getProperty("empApproachRelation"));
			}
		}
		WebAction.jsSendKeys(driver, empEntryDate, pps.getProperty("empEntryDate"));
		empProbationPeriod.sendKeys(pps.getProperty("empProbationPeriod"));		
		empSalarym.sendKeys(pps.getProperty("empSalarym"));
		empProbationSalarym.sendKeys(pps.getProperty("empProbationSalarym"));
		empProbationSalaryy.sendKeys(pps.getProperty("empProbationSalaryy"));
		if (empTargetProduct.isDisplayed()) {
			empTargetProduct.sendKeys(pps.getProperty("empTargetProduct"));
		}
		if (empCompletedProduct.isDisplayed()) {
			empCompletedProduct.sendKeys(pps.getProperty("empCompletedProduct"));
		}
//		if ("是".equals(pps.getProperty("needCard"))) {
//			needCard.click();
//		} else {
//			noNeedCard.click();
//		}
		
		empDesc.sendKeys(pps.getProperty("empDesc"));
		WebAction.jsSendKeys(driver, empCardBegin, pps.getProperty("empCardBegin"));
		WebAction.jsSendKeys(driver, empCardEnd, pps.getProperty("empCardEnd"));
		submitBtn.click();	
		Boolean flag = WebAction.waitUntilVisible(driver, "//div[text()='启动流程成功!']", 15);
		Assert.assertTrue(flag);		
		driver.findElement(By.xpath("//div[text()='确定']")).click();
	}
}
