package com.test.first_maven.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.first_maven.base.UITest;
import com.test.first_maven.base.helper.Tools;
import com.test.first_maven.base.selenium.NewWebDriverEventListener;
import com.test.first_maven.base.selenium.WebAction;
import com.test.first_maven.page.OALoginPage;
import com.test.first_maven.page.UserMgr;
import com.test.first_maven.page.flow.FlowCenter;
import com.test.first_maven.page.flow.MyToDo;
import com.test.first_maven.page.hr.ApplicantsMgr;

public class ImitateUserTest extends UITest {
		OALoginPage oaLogin;
//		ApplicantsMgr applicants;
//		FlowCenter flowPage;
//		MyToDo myToDo;
//		UserMgr uMgr;
		
		@BeforeTest
		public void beforeTest() {
			Tools.deleteDirectory("pic");
			System.setProperty("webdriver.chrome.driver", "D:\\jar\\chromedriver.exe");
		}

		public void login(String userName) {
			driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
			String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
			WebAction.get(driver, url, "//*[@name='username']");
			driver.manage().window().maximize();
			oaLogin = new OALoginPage(driver);
			oaLogin.login(userName, "jtlhrpsd");
		}

//		public void enterMyToDo() {
//			myToDo = new MyToDo(driver);
//			myToDo.enterMyToDo();
//		}

//		public void enterApplicantsMgr() {
//			applicants = new ApplicantsMgr(driver);
//			applicants.enterApplicantsMgr();
//		}

//		public void enterFlowCenter() {
//			flowPage = new FlowCenter(driver);
//			flowPage.enterFlowCenter();
//		}
		
//		public void enterUserMgr() {
//			uMgr = new UserMgr(driver);
//			uMgr.enterUserMgr();
//		}

		@Test
		public void pass1() {
			WebDriver driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
			String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
			WebAction.get(driver, url, "//*[@name='username']");
			driver.manage().window().maximize();
			OALoginPage oaLogin = new OALoginPage(driver);
			oaLogin.login("admin", "jtlhrpsd");
			UserMgr uMgr = new UserMgr(driver);
			uMgr.enterUserMgr();
			String paras = "{\"jobNumber\":\"J161361\"}";
			uMgr.imitateUser(paras);
			MyToDo myToDo = new MyToDo(driver);
			String flowName = "入职确认";
			String paras1 = "{\"firstDay\":\"2017-02-01\"," 
					+ "\"place\":\"苏州园区\"," 
					+ "\"contractType\":\"劳动合同\","
					+ "\"contractType2\":\"固定期限\"," 
					+ "\"contractUnit\":\"金螳螂\"," 
					+ "\"years\":\"3\"}";
			myToDo.enterMyToDo();
//			myToDo.commitAll(flowName, paras1);
			myToDo.commitAll();
		}

		@Test
		public void pass2() {
			WebDriver driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
			String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
			WebAction.get(driver, url, "//*[@name='username']");
			driver.manage().window().maximize();
			OALoginPage oaLogin = new OALoginPage(driver);
			oaLogin.login("admin", "jtlhrpsd");
			UserMgr uMgr = new UserMgr(driver);
			uMgr.enterUserMgr();
			String paras = "{\"jobNumber\":\"00029611\"}";
			uMgr.imitateUser(paras);
			MyToDo myToDo = new MyToDo(driver);
			String flowName = "入职确认";
			String paras1 = "{\"firstDay\":\"2017-02-01\"," 
					+ "\"place\":\"苏州园区\"," 
					+ "\"contractType\":\"劳动合同\","
					+ "\"contractType2\":\"固定期限\"," 
					+ "\"contractUnit\":\"金螳螂\"," 
					+ "\"years\":\"3\"}";
			myToDo.enterMyToDo();
//			myToDo.commitAll(flowName, paras1);
			myToDo.commitAll();
		}
		@Test
		public void pass3() {
			WebDriver driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
			String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
			WebAction.get(driver, url, "//*[@name='username']");
			driver.manage().window().maximize();
			OALoginPage oaLogin = new OALoginPage(driver);
			oaLogin.login("admin", "jtlhrpsd");
			UserMgr uMgr = new UserMgr(driver);
			uMgr.enterUserMgr();
			String paras = "{\"jobNumber\":\"J410746\"}";
			uMgr.imitateUser(paras);
			MyToDo myToDo = new MyToDo(driver);
			String flowName = "入职确认";
			String paras1 = "{\"firstDay\":\"2017-02-01\"," 
					+ "\"place\":\"苏州园区\"," 
					+ "\"contractType\":\"劳动合同\","
					+ "\"contractType2\":\"固定期限\"," 
					+ "\"contractUnit\":\"金螳螂\"," 
					+ "\"years\":\"3\"}";
			myToDo.enterMyToDo();
//			myToDo.commitAll(flowName, paras1);
			myToDo.commitAll();
		}
		@Test
		public void pass4() {
			WebDriver driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
			String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
			WebAction.get(driver, url, "//*[@name='username']");
			driver.manage().window().maximize();
			OALoginPage oaLogin = new OALoginPage(driver);
			oaLogin.login("admin", "jtlhrpsd");
			UserMgr uMgr = new UserMgr(driver);
			uMgr.enterUserMgr();
			String paras = "{\"jobNumber\":\"J171927\"}";
			uMgr.imitateUser(paras);
			MyToDo myToDo = new MyToDo(driver);
			String flowName = "入职确认";
			String paras1 = "{\"firstDay\":\"2017-02-01\"," 
					+ "\"place\":\"苏州园区\"," 
					+ "\"contractType\":\"劳动合同\","
					+ "\"contractType2\":\"固定期限\"," 
					+ "\"contractUnit\":\"金螳螂\"," 
					+ "\"years\":\"3\"}";
			myToDo.enterMyToDo();
//			myToDo.commitAll(flowName, paras1);
			myToDo.commitAll();
		}
		@Test
		public void pass5() {
			WebDriver driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
			String url = "http://172.21.204.23:8080/J2bpm/login.jsp";
			WebAction.get(driver, url, "//*[@name='username']");
			driver.manage().window().maximize();
			OALoginPage oaLogin = new OALoginPage(driver);
			oaLogin.login("admin", "jtlhrpsd");
			UserMgr uMgr = new UserMgr(driver);
			uMgr.enterUserMgr();
			String paras = "{\"jobNumber\":\"00020842\"}";
			uMgr.imitateUser(paras);
			MyToDo myToDo = new MyToDo(driver);
			String flowName = "入职确认";
			String paras1 = "{\"firstDay\":\"2017-02-01\"," 
					+ "\"place\":\"苏州园区\"," 
					+ "\"contractType\":\"劳动合同\","
					+ "\"contractType2\":\"固定期限\"," 
					+ "\"contractUnit\":\"金螳螂\"," 
					+ "\"years\":\"3\"}";
			myToDo.enterMyToDo();
//			myToDo.commitAll(flowName, paras1);
			myToDo.commitAll();
		}

}
