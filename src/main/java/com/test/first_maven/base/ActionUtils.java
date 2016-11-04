package com.test.first_maven.base;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionUtils {
	private static Logger log = LogManager.getFormatterLogger(ActionUtils.class);

	public static void get(WebDriver driver, String url) throws InterruptedException {
		Boolean flag = false;
		for (int i = 0; i < 3; i++) {
			driver.get(url);
			String title = driver.getTitle();
			if (title.contains("无法访问")) {
				log.info("wait 1s");
				Thread.sleep(1000);
				continue;
			} else {
				flag = true;
				log.info("open " + url + " success!");
				break;
			}

		}
		if (flag == false) {
			log.error("open " + url + " fail!");
		}
	}

	public static void get(WebDriver driver, String url, String visibleExpcet) {
		Boolean flag = false;
		for (int i = 0; i < 30; i++) {
			driver.get(url);
			String xpath = "//*[text()='" + visibleExpcet + "']";
			Boolean isVisible = isVisible(driver, xpath);
			if (isVisible == true) {
				flag = true;
				log.info("open " + url + " success!");
				break;
			} else {
				log.info("wait 2s");
				Tools.wait(2);
				continue;
			}

		}
		if (flag == false) {
			log.error("open " + url + " fail!");
		}
	}

	public static Boolean isExist(WebDriver driver, String idOrXpath) {
		try {
			WebElement ele = driver.findElement(By.xpath(idOrXpath));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;

	}

	public static Boolean isVisible(WebDriver driver, String idOrXpath) {
		WebElement ele;
		try {
			ele = driver.findElement(By.xpath(idOrXpath));
		} catch (NoSuchElementException e) {
			return false;
		}
		if (ele.isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}
	
	public static Boolean waitUntilVisible (WebDriver driver, String idOrXpath, int sencond) {
//		WebElement ele = (new WebDriverWait(driver, sencond))
//				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(idOrXpath)));
//		System.out.println("---------------------------------------------------------------");
//		System.out.println(ele);
//		return null;
		WebElement ele = null;
		for (int i=0;i<=sencond;i++) {
			try {
				ele = driver.findElement(By.xpath(idOrXpath));
			} catch (NoSuchElementException e) {
				if (i == sencond) {
					return false;
				} else {
					Tools.wait(1);
					continue;
				}
			}
			break;
		}
		for (int i=0;i<=sencond;i++) {
			if (ele.isDisplayed()) {
				return true;
			} else {
				if (i == sencond) {
					return false;
				} else {
					Tools.wait(1);
					continue;
				}
			}
			
		}
		return null;
	}
	
	public static void enterFrameFromDef (WebDriver driver, String idOrName) {
		driver.switchTo().defaultContent();
		enterFrame(driver, idOrName);
	}
	public static void enterFrame (WebDriver driver, String idOrName) {
		for (int i=0;i<5;i++) {
			try {
				driver.switchTo().frame(idOrName);
			} catch(NoSuchFrameException e) {
				Tools.wait(2);
				continue;
			}
			break;
		}	
	}

	public static void enterFrame (WebDriver driver, WebElement ele) {
		for (int i=0;i<5;i++) {
			try {
				driver.switchTo().frame(ele);
			} catch(NoSuchFrameException e) {
				Tools.wait(2);
				continue;
			}
			break;
		}	
	}
	
	public static void defaultFrame (WebDriver driver) {
		driver.switchTo().defaultContent();
	}	
	public static void enterFrameFromDef (WebDriver driver, WebElement ele) {
		driver.switchTo().defaultContent();
		enterFrame(driver, ele);
	}
	public static void jsClick (WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", ele);
	}	
	public static void clickWithWait (WebDriver driver, String idOrXpath) {
		clickWithWait(driver, idOrXpath, 5);
	}
	public static void clickWithWait (WebDriver driver, String idOrXpath, int second) {
		waitUntilVisible(driver, idOrXpath, second);
		driver.findElement(By.xpath(idOrXpath)).click();
	}	
	public static void enterRecentFrame (WebDriver driver, String idOrXpath) {	
		ActionUtils.defaultFrame(driver);
		List<WebElement> eles = driver.findElements(By.xpath(idOrXpath));
		ActionUtils.enterFrameFromDef(driver, eles.get(eles.size()-1));
	}
	public static void enterFirstFrame (WebDriver driver, String idOrXpath) {
		ActionUtils.defaultFrame(driver);
		List<WebElement> eles = driver.findElements(By.xpath(idOrXpath));
		ActionUtils.enterFrameFromDef(driver, eles.get(0));
	}
	
	public static void selectByValue (WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByValue(value);
	}
}
