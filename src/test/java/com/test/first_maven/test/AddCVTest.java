package com.test.first_maven.test;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.first_maven.base.UITest;
import com.test.first_maven.base.selenium.NewWebDriverEventListener;
import com.test.first_maven.base.selenium.WebAction;
import com.test.first_maven.page.OALoginPage;
import com.test.first_maven.page.hr.ApplicantsMgr;

public class AddCVTest extends UITest {
	OALoginPage oaLogin;
	ApplicantsMgr applicants;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "D:\\jar\\chromedriver.exe");
		driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
		String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
		WebAction.get(driver, url, "//*[@name='username']");
		driver.manage().window().maximize();
		oaLogin = new OALoginPage(driver);
		
		driver.findElement(By.xpath("//input[starts-with(@id,'check')]")).click(); //chrome不支持方法ends-with
	}

	@Test
	public void login() {
		oaLogin.login("admin", "jtlhrpsd");
	}

	@Test
	public void enterCatalog() {
		applicants = new ApplicantsMgr(driver);
		applicants.enterApplicantsMgr();
	}
	

	@Test
	public void addCV() {
		String dataFile = "dataFile/applicants_add.properties";
		applicants.addCV(dataFile);
		dataFile = "dataFile/applicants_add2.properties";
		applicants.addCV(dataFile);
		dataFile = "dataFile/applicants_add3.properties";
		applicants.addCV(dataFile);
		dataFile = "dataFile/applicants_add4.properties";
		applicants.addCV(dataFile);
		dataFile = "dataFile/applicants_add5.properties";
		applicants.addCV(dataFile);
		dataFile = "dataFile/applicants_add6.properties";
		applicants.addCV(dataFile);		
	}
	
	@AfterTest
	public void afterTest() throws InterruptedException {
		driver.quit();
		// driver.close();
		
	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {

	}
}
