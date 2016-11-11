package com.test.first_maven.test;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.first_maven.base.WebAction;
import com.test.first_maven.base.NewWebDriverEventListener;
import com.test.first_maven.base.UITest;
import com.test.first_maven.page.BaiduHome;

public class BaiduTest extends UITest {
	BaiduHome baidupage;

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\jar\\chromedriver.exe");
		// driver = new ChromeDriver();
		driver = new EventFiringWebDriver(new ChromeDriver()).register(new NewWebDriverEventListener());
		// driver.get("http://www.baidu.com");
		String url = "http://www.baidu.com";
		WebAction.get(driver, url, "更多产品");
		driver.manage().window().maximize();
		baidupage = new BaiduHome(driver);		
	}

	@Test
	public void searchTest() {
//		System.out.println("searchTest");
		baidupage.search("selenium").checkKeyword();
	}

	@Test
	public void searchTest2() {
//		System.out.println("searchTest2");
		baidupage.search("eeeeeeeee").checkKeyword();
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
