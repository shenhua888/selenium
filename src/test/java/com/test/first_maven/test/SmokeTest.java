package com.test.first_maven.test;

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
import com.test.first_maven.page.flow.FlowCenter;
import com.test.first_maven.page.flow.MyToDo;
import com.test.first_maven.page.hr.ApplicantsMgr;

public class SmokeTest extends UITest {
	OALoginPage oaLogin;
	ApplicantsMgr applicants;
	FlowCenter flowPage;
	MyToDo myToDo;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "D:\\jar\\chromedriver.exe");				
	}

	public void login(String userName) {
		driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
		String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
		WebAction.get(driver, url, "登录");
		driver.manage().window().maximize();
		oaLogin = new OALoginPage(driver);
		oaLogin.login(userName, "1");
	}

	public void enterMyToDo() {
		myToDo = new MyToDo(driver);
		myToDo.enterMyToDo();
	}
	
	public void enterApplicantsMgr() {
		applicants = new ApplicantsMgr(driver);
		applicants.enterApplicantsMgr();
	}

	public void enterFlowCenter() {
		flowPage = new FlowCenter(driver);
		flowPage.enterFlowCenter();
	}
	
	@Test
	public void addCV() {
		login("admin");
		enterApplicantsMgr();
		String dataFile = "D:\\workSpace\\first_maven\\dataFile\\applicants_add.properties";
		applicants.addCV(dataFile);
		driver.quit();
	}
	
	@Test
	public void hire() {
		login("admin");
		enterFlowCenter();
		String dataFile = "D:\\workSpace\\first_maven\\dataFile\\hire.properties";
		flowPage.hireEmployee(dataFile);
		driver.quit();
	}
	
	@Test
	public void employ1() {
		login("J160389");
		enterMyToDo();
		String flowName = "录用申请V1";
		myToDo.commitAll(flowName);	
		driver.quit();
	}
	
	@Test
	public void employ2() {
		login("L0314862");
		enterMyToDo();
		String flowName = "录用申请V1";
		myToDo.commitAll(flowName);	
		driver.quit();
	}
	
	@Test
	public void entryPre1() {
		login("000023408");
		enterMyToDo();
		String flowName = "入职准备";
		String paras="test";  
		myToDo.commitAll(flowName, paras);
		driver.quit();
	}	
	
	@Test
	public void entryPre2() {
		login("L0314862");
		enterMyToDo();
		String flowName = "入职准备";
		myToDo.commitAll(flowName);
		driver.quit();
	}	
	
	@Test
	public void entryConfirm1() {
		login("000023408");
		enterMyToDo();
		String flowName = "入职确认";
		String paras="{\"firstDay\":\"2017-01-01\","
				  + "\"place\":\"苏州园区\","
				  + "\"contractType\":\"劳动合同\","
				  + "\"contractType2\":\"固定期限\","
				  + "\"contractUnit\":\"金螳螂\","
				  + "\"years\":\"3\"}"; 
		myToDo.commitAll(flowName, paras);
		driver.quit();
	}	
	
	@Test
	public void entryConfirm2() {
		login("L0314862");
		enterMyToDo();
		String flowName = "入职确认";
		myToDo.commitAll(flowName);
		driver.quit();
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
//		Tools.killChrome();
		
	}

	@AfterSuite
	public void afterSuite() {

	}
}
