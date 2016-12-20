package com.test.first_maven.page.flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.test.first_maven.base.selenium.WebAction;

public class FlowCenter {
		@FindBy(id = "10000004810038")
		@CacheLookup
		private WebElement flowCenter;
		
		@FindBy(id = "leftTree_2_a")
		@CacheLookup
		private WebElement newFlow;		
		
		private WebDriver driver;
		
		public FlowCenter (WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
		}
		
		public void enterFlowCenter() {
			flowCenter.click();
			newFlow.click();			
			Boolean flag = null;
			flag = WebAction.waitUntilVisible(driver, "//*[@id='10000004810043']", 10);
			Assert.assertTrue(flag);
		}
		
		public void hireEmployee(String dataFile) {
			AllFlowPage allFlow = new AllFlowPage(driver);
			allFlow.hire(dataFile);			
		}
		
		
}
