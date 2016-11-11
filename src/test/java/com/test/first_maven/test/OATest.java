package com.test.first_maven.test;

import java.io.FileNotFoundException;
import java.io.IOException;

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
		String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
		WebAction.get(driver, url, "登录");
		driver.manage().window().maximize();
		oaLogin = new OALoginPage(driver);
		
	}

	@Test
	public void loginTest() throws InterruptedException {
//		System.out.println("searchTest");
		oaLogin.login("admin", "1");
	}

	@Test
	public void enterCatalog() {
		applicants = new ApplicantsMgr(driver);
		applicants.enterApplicantsMgr();
	}
	
	@Test
	public void clickUploadCV() {
		applicants.clickUploadCV();
	}

	@Test
	public void upLoadCV() {
		String dataFile = "D:\\workSpace\\first_maven\\src\\main\\java\\applicants.properties";
		applicants.upLoadCV(dataFile);
	}
	
	@AfterTest
	public void afterTest() throws InterruptedException {
//		driver.quit();
		// driver.close();
		
	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {

	}
}
