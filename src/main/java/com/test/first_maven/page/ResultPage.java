package com.test.first_maven.page;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class ResultPage {
	// 定义百度搜索结果界面的输入框
    @FindBy(id = "kw")
    @CacheLookup
    private WebElement inputArea;
 
    public ResultPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }
 
	public void checkKeyword() {
		Random rand = new Random();
		int i = rand.nextInt(5); // int范围类的随机数
//		System.out.println(i);
		if (i == 0) {
			Assert.assertEquals(inputArea.getAttribute("value"), "selenium");
		} else {
			Assert.assertEquals(inputArea.getAttribute("value"), "ssssssss");

		}

	}
}
