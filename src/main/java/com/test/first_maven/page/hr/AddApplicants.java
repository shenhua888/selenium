package com.test.first_maven.page.hr;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.test.first_maven.page.flow.SelectFilePage;
import com.test.first_maven.base.helper.Tools;
import com.test.first_maven.base.selenium.WebAction;

public class AddApplicants {
	
	@FindBy(id = "reName")
	@CacheLookup
	private WebElement applicantName;
	
	@FindBy(xpath = "//*[@name='reSex']")
	@CacheLookup
	private WebElement male;
	
	@FindBy(xpath = "//*[@name='reSex']/../input[2]")
	@CacheLookup
	private WebElement feMale;	
	
	@FindBy(id = "reMobile")
	@CacheLookup
	private WebElement cellphone;
	
	@FindBy(id = "reEmail")
	@CacheLookup
	private WebElement email;
	
	@FindBy(id = "reCardNumber")
	@CacheLookup
	private WebElement reCardNumber; 	
	
	@FindBy(id = "reBirthday")
	@CacheLookup
	private WebElement reBirthday;	
	
	@FindBy(id = "reHeight")
	@CacheLookup
	private WebElement reHeight; 
	
	@FindBy(id = "reNation")
	@CacheLookup
	private WebElement reNation; 
	
	@FindBy(id = "reNativePlace")
	@CacheLookup
	private WebElement reNativePlace; 
	
	@FindBy(id = "rePermanentAddress")
	@CacheLookup
	private WebElement rePermanentAddress; 
	
	@FindBy(id = "reCardAddress")
	@CacheLookup
	private WebElement reCardAddress;	 
	
	@FindBy(id = "reFileAddress")
	@CacheLookup
	private WebElement reFileAddress;	
	
	@FindBy(id = "reOtherPhone")
	@CacheLookup
	private WebElement reOtherPhone; 
	
	@FindBy(id = "reWorkCompany")
	@CacheLookup
	private WebElement reWorkCompany;	
	
	@FindBy(id = "reEduSchool")
	@CacheLookup
	private WebElement reEduSchool; 
	
	@FindBy(id = "reEduMajor")
	@CacheLookup
	private WebElement reEduMajor;
	
	@FindBy(id = "reHopeSalarym")
	@CacheLookup
	private WebElement reHopeSalarym; 
	
	@FindBy(id = "reForwordSalaryy")
	@CacheLookup
	private WebElement reForwordSalaryy;	
	
	@FindBy(id = "reAvailableToDate")
	@CacheLookup
	private WebElement reAvailableToDate;	
	
	@FindBy(id = "reCardType")
	@CacheLookup
	private WebElement reCardType;	
	
	@FindBy(id = "reNationality")
	@CacheLookup
	private WebElement reNationality;	
	
	@FindBy(id = "reMarital")
	@CacheLookup
	private WebElement reMarital;		
	
	@FindBy(id = "reBloodtype")
	@CacheLookup
	private WebElement reBloodtype;	 
	
	@FindBy(id = "rePolity")
	@CacheLookup
	private WebElement rePolity;		
	
	@FindBy(id = "reIsConstructor")
	@CacheLookup
	private WebElement reIsConstructor;	
	
	@FindBy(id = "reEdu")
	@CacheLookup
	private WebElement reEdu;	
	
	@FindBy(xpath = "//*[@id='jobDeptName']/following-sibling::*[1]")
	@CacheLookup
	private WebElement jobDeptName;

	@FindBy(xpath = "//*[@id='jobPositionName']/following-sibling::*[1]")
	@CacheLookup
	private WebElement jobPosition;	
	
	@FindBy(xpath = "//a[text()='上传照片']")
	@CacheLookup
	private WebElement picFile;	
		
	@FindBy(xpath = "//*[@tabid='reResumeEdu']//a[@id='btnAdd']")
	@CacheLookup
	private WebElement eduAddBtn;	

	@FindBy(xpath = "//*[@tabid='reResumeEmergencyContact']//a[@id='btnAdd']")
	@CacheLookup
	private WebElement emgConAddBtn;
	
	@FindBy(xpath = "//*[@tabid='reResumeFamily']//a[@id='btnAdd']")
	@CacheLookup
	private WebElement family;	
	
	@FindBy(xpath = "//*[@tabid='reResumeWork']//a[@id='btnAdd']")
	@CacheLookup
	private WebElement workHistory;	
	
	@FindBy(id = "dataFormSave")
	@CacheLookup
	private WebElement saveBtn;	
	
	@FindBy(xpath = "//*[@id='reResumeJobItem']/tbody/tr[1]/td[2]/a")
	@CacheLookup
	private WebElement firstAppName;	
	
	private WebDriver driver;
	
	public AddApplicants (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	final void addCV(String dataFile) {
		Properties pps = new Properties();
		pps = Tools.readPropertiesFileObj(dataFile);
		jobDeptName.click();		
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");	
		WebAction.enterFrame(driver, "orgFrame");
		driver.findElement(By.id("orgName")).sendKeys(pps.getProperty("jobDeptName"));
		driver.findElement(By.id("searchForm")).submit();
		driver.findElement(By.xpath("//*[@id='sysOrgItem']//*[@value='" + pps.getProperty("jobDeptName") + "']/../input")).click();
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");
		driver.findElement(By.xpath("//*[text()='选择']")).click();
		WebAction.enterFrameFromDef(driver, "10000004780032");
		jobPosition.click();
		WebAction.enterFrameFromDef(driver, "frameDialog_");
		WebAction.enterFrame(driver, "dialogFrame");
		driver.findElement(By.xpath("//*[text()='" + pps.getProperty("jobPosition") + "']/../td/input")).click();
		WebAction.defaultFrame(driver);
		driver.findElement(By.xpath("//*[text()='确定']")).click();
		WebAction.enterFrameFromDef(driver, "10000004780032");
		//上传照片
		picFile.click();
		Tools.wait(1);
		SelectFilePage selectFile = new SelectFilePage(driver);
		selectFile.selectFile(pps.getProperty("picFile"));
		WebAction.enterRecentFrame(driver, "//*[contains(@id,'ligerwindow')]");
		driver.findElement(By.id("comCut")).click();
		WebAction.clickWithWait(driver, "//a[text()='完成。']");
		
		WebAction.enterFrameFromDef(driver, "10000004780032");
		applicantName.sendKeys(pps.getProperty("applicantName"));
		cellphone.sendKeys(pps.getProperty("cellphone"));
		email.sendKeys(pps.getProperty("email"));
		WebAction.selectByVisibleText(reCardType, pps.getProperty("reCardType"));
		reCardNumber.sendKeys(pps.getProperty("reCardNumber"));
		reHeight.sendKeys(pps.getProperty("reHeight"));
		reNation.sendKeys(pps.getProperty("reNation"));
		reNativePlace.sendKeys(pps.getProperty("reNativePlace"));
		rePermanentAddress.sendKeys(pps.getProperty("rePermanentAddress"));
		reCardAddress.sendKeys(pps.getProperty("reCardAddress"));
		if (!"".equals(pps.getProperty("reFileAddress"))) {
			reFileAddress.sendKeys(pps.getProperty("reFileAddress"));
		}
		if (!"".equals(pps.getProperty("reOtherPhone"))) {
			reOtherPhone.sendKeys(pps.getProperty("reOtherPhone"));
		}
		if (!"".equals(pps.getProperty("reWorkCompany"))) {
			reWorkCompany.sendKeys(pps.getProperty("reWorkCompany"));
		}
		reEduSchool.sendKeys(pps.getProperty("reEduSchool"));
		reEduMajor.sendKeys(pps.getProperty("reEduMajor"));
		reHopeSalarym.sendKeys(pps.getProperty("reHopeSalarym"));
		reForwordSalaryy.sendKeys(pps.getProperty("reForwordSalaryy"));
		WebAction.jsSendKeys(driver, reAvailableToDate, pps.getProperty("reAvailableToDate"));
		if (!"".equals(pps.getProperty("reBirthday"))) {
			WebAction.jsSendKeys(driver, reBirthday, pps.getProperty("reBirthday"));
		}
		if (!"".equals(pps.getProperty("gender"))) {
			if (pps.getProperty("gender").equals("男")) {
				male.click();
				Assert.assertTrue(male.isSelected());
			} else {
				feMale.click();
				Assert.assertTrue(feMale.isSelected());
			}			
		}		
		WebAction.selectByVisibleText(reMarital, pps.getProperty("reMarital"));
		WebAction.selectByVisibleText(reNationality, pps.getProperty("reNationality"));
		WebAction.selectByVisibleText(reBloodtype, pps.getProperty("reBloodtype"));
		WebAction.selectByVisibleText(rePolity, pps.getProperty("rePolity"));
		WebAction.selectByVisibleText(reIsConstructor, pps.getProperty("reIsConstructor"));
		WebAction.selectByVisibleText(reEdu, pps.getProperty("reEdu"));
		String eduInfo = pps.getProperty("eduInfo");
		if (!"".equals(eduInfo)) {
			String[] arrayCon = eduInfo.split(";");
			for (String mess : arrayCon) {
				WebAction.jsClick(driver, eduAddBtn);
				Tools.wait(1);
				String[] detail = mess.split(",");
				WebElement eduBeginDate = driver.findElement(By.id("eduBeginDate"));
				WebElement eduEndDate = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='eduEndDate']"));
				WebElement eduSchool = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='eduSchool']"));
				WebElement eduEducation = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='eduEducation']"));
				WebElement commit = driver.findElement(By.xpath("//*[text()='确定']"));
				if (!"null".equals(detail[0])) {
					WebAction.jsSendKeys(driver, eduBeginDate, detail[0]);
				}
				if (!"null".equals(detail[1])) {
					WebAction.jsSendKeys(driver, eduEndDate, detail[1]);
				}
				if (!"null".equals(detail[2])) {
					WebAction.jsSendKeys(driver, eduSchool, detail[2]);
				}
				if (!"null".equals(detail[4])) {
					WebAction.selectByValue(eduEducation, detail[4]);
				}
				commit.click();
				Tools.wait(1);
			}
		}

		String emergencyContact = pps.getProperty("emergencyContact");
		if (!"".equals(emergencyContact)) {
			String[] arrayCon = emergencyContact.split(";");
			for (String mess : arrayCon) {
				WebAction.jsClick(driver, emgConAddBtn);
				Tools.wait(1);
				String[] detail = mess.split(",");
				WebElement contactName = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='contactName']"));
				WebElement contactRelation = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='contactRelation']"));
				WebElement contactPhone = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='contactPhone']"));
				WebElement commit = driver.findElement(By.xpath("//*[text()='确定']"));
				if (!"null".equals(detail[0])) {
					WebAction.jsSendKeys(driver, contactName, detail[0]);
				}
				if (!"null".equals(detail[1])) {
					WebAction.selectByValue(contactRelation, detail[1]);
				}
				if (!"null".equals(detail[2])) {
					WebAction.jsSendKeys(driver, contactPhone, detail[2]);
				}
				commit.click();
				Tools.wait(1);
			}
		}
		
		String familyInfo = pps.getProperty("family");
		if (!"".equals(familyInfo)) {
			String[] arrayCon = familyInfo.split(";");
			for (String mess : arrayCon) {
				WebAction.jsClick(driver, family);
				Tools.wait(1);
				String[] detail = mess.split(",");
				WebElement famRelation = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='famRelation']"));
				WebElement famName = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='famName']"));
				WebElement commit = driver.findElement(By.xpath("//*[text()='确定']"));
				if (!"null".equals(detail[0])) {
					WebAction.selectByValue(famRelation, detail[0]);
				}
				if (!"null".equals(detail[1])) {
					WebAction.jsSendKeys(driver, famName, detail[1]);
				}
				commit.click();
				Tools.wait(1);
			}
		}
				
		String workInfo = pps.getProperty("workHistory");
		if (!"".equals(workInfo)) {
			String[] arrayCon = workInfo.split(";");
			for (String mess : arrayCon) {
				WebAction.jsClick(driver, workHistory);
				Tools.wait(1);
				String[] detail = mess.split(",");
				WebElement workBeginDate = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='workBeginDate']"));
				WebElement workEndDate = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='workEndDate']"));
				WebElement workCompany = driver.findElement(By.xpath("//*[@class='l-dialog-cc']//*[@name='workCompany']"));
				WebElement commit = driver.findElement(By.xpath("//*[text()='确定']"));
				if (!"null".equals(detail[0])) {
					WebAction.jsSendKeys(driver, workBeginDate, detail[0]);
				}
				if (!"null".equals(detail[1])) {
					WebAction.jsSendKeys(driver, workEndDate, detail[1]);
				}
				if (!"null".equals(detail[2])) {
					WebAction.jsSendKeys(driver, workCompany, detail[2]);
				}
				commit.click();
				Tools.wait(1);
			}
		}
		saveBtn.click();
		WebAction.clickWithWait(driver, "//*[text()='确定']");
		Boolean flag = WebAction.isAddSuccess(firstAppName, pps.getProperty("applicantName"));
		WebAction.defaultFrame(driver);
		Assert.assertTrue(flag);
	}
}
