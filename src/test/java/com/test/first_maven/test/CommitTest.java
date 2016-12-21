package com.test.first_maven.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.first_maven.base.UITest;
import com.test.first_maven.base.helper.Tools;
import com.test.first_maven.base.selenium.NewWebDriverEventListener;
import com.test.first_maven.base.selenium.WebAction;
import com.test.first_maven.page.OALoginPage;
import com.test.first_maven.page.flow.FlowCenter;
import com.test.first_maven.page.flow.MyToDo;
import com.test.first_maven.page.hr.ApplicantsMgr;

public class CommitTest extends UITest {
	OALoginPage oaLogin;
	ApplicantsMgr applicants;
	FlowCenter flowPage;
	MyToDo myToDo;

	@BeforeTest
	public void beforeTest() {
		Tools.deleteDirectory("pic");
		System.setProperty("webdriver.chrome.driver", "D:\\jar\\chromedriver.exe");
	}

	public void login(String userName) {
		driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
		String url = "http://172.16.55.187:8080/J2bpm/login.jsp";
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
	public void pass1() {
		login("J0000051");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}

	@Test
	public void pass2() {
		login("L0314862");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}

	@Test
	public void pass3() {
		login("J4114658");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}

	@Test
	public void pass4() {
		login("J0000051");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}

	@Test
	public void pass5() {
		login("J0000184");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}

	@Test
	public void pass6() {
		login("J410808");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}
	
	@Test
	public void pass7() {
		login("J410808");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}
	
	@Test
	public void pass8() {
		login("J410808");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}
	
	@Test
	public void pass9() {
		login("J410808");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}
	@Test
	public void pass10() {
		login("L0314862");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}
	@Test
	public void pass11() {
		login("J4114658");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}
	@Test
	public void pass12() {
		login("J410808");
		enterMyToDo();
		String flowName = "员工调动V3";
		myToDo.commitAll(flowName);
	}

	@AfterTest
	public void afterTest() {
		// Tools.killChrome();

	}

	@AfterSuite
	public void afterSuite() {

	}
}
