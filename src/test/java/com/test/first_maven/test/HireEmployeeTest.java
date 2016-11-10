package com.test.first_maven.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.first_maven.base.ActionUtils;
import com.test.first_maven.base.NewWebDriverEventListener;
import com.test.first_maven.base.UITest;
import com.test.first_maven.page.OALoginPage;
import com.test.first_maven.page.flow.FlowCenter;
import com.test.first_maven.page.hr.ApplicantsMgr;

public class HireEmployeeTest extends UITest {
	OALoginPage oaLogin;
	FlowCenter flowPage;
	
	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\jar\\chromedriver.exe");
		driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
		String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
		ActionUtils.get(driver, url, "登录");
		driver.manage().window().maximize();
		oaLogin = new OALoginPage(driver);
		
	}

	@Test
	public void login() throws InterruptedException {
		oaLogin.login("admin", "1");
	}

	@Test
	public void enterCatalog() {
		flowPage = new FlowCenter(driver);
		flowPage.enterFlowCenter();
	}
	

	@Test
	public void hireEmployee() {
		String dataFile = "D:\\workSpace\\first_maven\\src\\main\\java\\hire.properties";
		flowPage.hireEmployee(dataFile);
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
