package com.test.first_maven.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.first_maven.base.WebAction;
import com.test.first_maven.base.NewWebDriverEventListener;
import com.test.first_maven.base.UITest;
import com.test.first_maven.page.OALoginPage;
import com.test.first_maven.page.hr.ApplicantsMgr;

public class OATest extends UITest {
	OALoginPage oaLogin;
	ApplicantsMgr applicants;
	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\jar\\chromedriver.exe");
		driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
//		String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
		String url ="http://vote.dichan.com/CompanyList.aspx?subId=29&subName=%E8%A3%85%E9%A5%B0%E8%AE%BE%E8%AE%A1&from=singlemessage&isappinstalled=0";
//		WebAction.get(driver, url, "登录");
		driver.get(url);
		driver.manage().window().maximize();
//		oaLogin = new OALoginPage(driver);
		WebAction.isExist(driver, "//*[@id='company2219']");
		WebElement ele = driver.findElement(By.id("company2219"));
		while (true) {
			WebAction.jsClick(driver, ele);
			WebAction.alertAccept(driver);
		}
		
		
	}

	@Test
	public void login() throws InterruptedException {
//		System.out.println("searchTest");
//		oaLogin.login("admin", "1");
	}

	@Test
	public void enterCatalog() {
//		applicants = new ApplicantsMgr(driver);
//		applicants.enterApplicantsMgr();
	}
	
	@Test
	public void sendInterview() {
//		String dataFile = "D:\\workSpace\\first_maven\\dataFile\\sendInterview.properties";
//		applicants.sendInterview(dataFile);;
	}
	
//	@Test
//	public void clickUploadCV() {
//		applicants.clickUploadCV();
//	}
//
//	@Test
//	public void upLoadCV() {
//		String dataFile = "D:\\workSpace\\first_maven\\dataFile\\applicants.properties";
//		applicants.upLoadCV(dataFile);
//	}
	
	@AfterTest
	public void afterTest() throws InterruptedException {
//		driver.quit();
		// driver.close();
		
	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {

	}
}
