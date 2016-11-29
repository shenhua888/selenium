package com.test.first_maven.page.flow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.test.first_maven.base.WebAction;
import com.test.first_maven.base.Tools;

public class MyToDo {
		@FindBy(id = "10000004810038")
		@CacheLookup
		private WebElement flowCenter;
		
		@FindBy(id = "leftTree_8_span")
		@CacheLookup
		private WebElement myToDo;		
		
		private WebDriver driver;
		private String currentWin;
		
		public MyToDo (WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
		}
		
		public void enterMyToDo() {
			currentWin = driver.getWindowHandle();
			flowCenter.click();
			myToDo.click();			
			Boolean flag = null;
			flag = WebAction.waitUntilVisible(driver, "//*[@id='10000004810059']", 10);
			Assert.assertTrue(flag);
		}
		
		public Boolean commitOne(String flowName) {
			WebAction.enterFrameFromDef(driver, "10000004810059");
			WebAction.enterFrame(driver, "defFrame");
			String path = "//*[@id='taskItem']//*[text()='" + flowName + "']/../td[3]/a";
			if (WebAction.isVisible(driver, path)) {
				driver.findElement(By.xpath(path)).click();
				WebAction.switchToNewWin(driver, currentWin);
				Boolean flag = null;
				flag = WebAction.waitUntilVisible(driver, "//*[@id='btnAgree']", 10);
				Assert.assertTrue(flag);
				driver.findElement(By.xpath("//*[@id='btnAgree']")).click();
				WebAction.enterFrameFromDef(driver, driver.findElement(By.xpath("//*[contains(@id,'ligerwindow')]")));
				driver.findElement(By.id("opinion")).sendKeys("同意");
				driver.findElement(By.id("dataFormSave")).click();
				WebAction.waitUntilVisible(driver, "//div[text()='执行任务成功!']", 15);
				driver.findElement(By.xpath("//div[text()='确定']")).click();
				driver.switchTo().window(currentWin);
				return true;
			} else {
				return false;
			}
		}
		
		public void commitAll(String flowName) {
			while(true) {
				Boolean flag = commitOne(flowName);
				if (flag.equals(false)) {
					break;
				}
				
			}
		}
}
