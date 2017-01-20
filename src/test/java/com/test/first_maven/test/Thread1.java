package com.test.first_maven.test;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.first_maven.base.UITest;
import com.test.first_maven.base.helper.MySqlConnect;
import com.test.first_maven.base.helper.Tools;
import com.test.first_maven.base.selenium.NewWebDriverEventListener;
import com.test.first_maven.base.selenium.WebAction;
import com.test.first_maven.page.OALoginPage;
import com.test.first_maven.page.UserMgr;
import com.test.first_maven.page.flow.FlowCenter;
import com.test.first_maven.page.flow.MyToDo;
import com.test.first_maven.page.hr.ApplicantsMgr;

public class Thread1 extends UITest {
	OALoginPage oaLogin;
	ApplicantsMgr applicants;
	FlowCenter flowPage;
	MyToDo myToDo;
	UserMgr uMgr;
	String jobNumber1 = "";
	
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
		//数据库查询
		String sql = "SELECT job_number FROM sys_user WHERE userid IN (SELECT ASSIGNEE_ FROM act_ru_task WHERE proc_inst_id_ IN (SELECT actinstid FROM bpm_pro_run_his WHERE STATUS = 1 AND PROCESSNAME IN ('员工离职')))";
        MySqlConnect db = new MySqlConnect();
        ResultSet result = db.query(sql);
        ArrayList<String> list = db.getResultSet(result);
        System.out.println(list.size());      
        jobNumber1 = list.get(list.size()-1);
        System.out.println(jobNumber1);
        db.closeConnect();
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

	public void enterUserMgr() {
		uMgr = new UserMgr(driver);
		uMgr.enterUserMgr();
	}

	@Test
	public void pass1() {
		login("admin");
		enterUserMgr();
		String paras = "{\"jobNumber\":\""+jobNumber1+"\"}";
		uMgr.imitateUser(paras);
		enterMyToDo();
		String flowName = "入职确认";
		String paras1 = "{\"firstDay\":\"2017-02-01\"," 
				+ "\"place\":\"苏州园区\"," 
				+ "\"contractType\":\"劳动合同\","
				+ "\"contractType2\":\"固定期限\"," 
				+ "\"contractUnit\":\"金螳螂\"," 
				+ "\"years\":\"3\"}";
		myToDo.enterMyToDo();
//		myToDo.commitAll(flowName);
//		myToDo.commitAll(flowName, paras1);
		myToDo.commitAll();
	}

	@AfterTest
	public void afterTest() {
		// Tools.killChrome();

	}

	@AfterSuite
	public void afterSuite() {

	}
}
