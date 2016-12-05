package com.test.first_maven.base;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebAction {
	private static Logger log = LogManager.getFormatterLogger(WebAction.class);

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
			log.info(idOrXpath + ": not exist!");
			return false;
		}
		log.info(idOrXpath + ": exist!");
		return true;

	}

	public static Boolean isVisible(WebDriver driver, String idOrXpath) {
		WebElement ele;
		try {
			ele = driver.findElement(By.xpath(idOrXpath));
		} catch (NoSuchElementException e) {
			log.info(idOrXpath + ": not exist!");
			return false;
		}
		if (ele.isDisplayed()) {
			log.info(idOrXpath + ": visible!");
			return true;
		} else {
			log.info(idOrXpath + ": not visible!");
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
					log.info(idOrXpath + ": not exist!");
					return false;
				} else {
					log.info(idOrXpath + ": not exist! Wait 1s!");
					Tools.wait(1);
					continue;
				}
			}
			break;
		}
		for (int i=0;i<=sencond;i++) {
			if (ele.isDisplayed()) {
				log.info(idOrXpath + ": visible!");
				return true;
			} else {
				if (i == sencond) {
					log.info(idOrXpath + ": not visible!");
					return false;
				} else {
					Tools.wait(1);
					continue;
				}
			}
			
		}
		return false;
	}
	
	public static void enterFrameFromDef (WebDriver driver, String idOrName) {
		defaultFrame(driver);
		enterFrame(driver, idOrName);
	}
	public static void enterFrameFromDef (WebDriver driver, WebElement ele) {
		driver.switchTo().defaultContent();
		enterFrame(driver, ele);
	}	
	public static void enterFrame (WebDriver driver, String idOrName) {
		for (int i=0;i<=5;i++) {
			try {
				driver.switchTo().frame(idOrName);
			} catch(NoSuchFrameException e) {
				if (i==5) {
					log.error("enter frame" + idOrName + "fail!");
				} else {
					Tools.wait(2);
					continue;
				}
				
			}
			break;
		}	
		log.info("enter frame" + idOrName + "success!");
	}

	public static void enterFrame (WebDriver driver, WebElement ele) {
		for (int i=0;i<=5;i++) {
			try {
				driver.switchTo().frame(ele);
			} catch(NoSuchFrameException e) {
				if (i==5) {
					log.error("enter frame" + ele.toString() + "fail!");
				} else {
					Tools.wait(2);
					continue;
				}				
			}
			break;
		}	
		log.info("enter frame" + ele.toString() + "success!");
	}
	
	public static void defaultFrame (WebDriver driver) {
		driver.switchTo().defaultContent();
		log.info("enter default content success!");
	}	

	public static void jsClick (WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", ele);
	}
	public static void jsSendKeys (WebDriver driver, WebElement ele, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		log.info("jsSendKeys:" + value);
        js.executeScript("arguments[0].value = \"" + value + "\";", ele);
	}	
	public static void clickWithWait (WebDriver driver, String idOrXpath) {
		clickWithWait(driver, idOrXpath, 5);
	}
	public static void clickWithWait (WebDriver driver, String idOrXpath, int second) {
		Boolean flag = waitUntilVisible(driver, idOrXpath, second);
		if (flag.equals(true)) {
			driver.findElement(By.xpath(idOrXpath)).click();
		} else {
			log.error(idOrXpath + "not visible! so can not click!");
		}
	}	
	public static void enterRecentFrame (WebDriver driver, String idOrXpath) {	
		WebAction.defaultFrame(driver);
		List<WebElement> eles = driver.findElements(By.xpath(idOrXpath));
		WebAction.enterFrameFromDef(driver, eles.get(eles.size()-1));
	}
	public static void enterFirstFrame (WebDriver driver, String idOrXpath) {
		WebAction.defaultFrame(driver);
		List<WebElement> eles = driver.findElements(By.xpath(idOrXpath));
		WebAction.enterFrameFromDef(driver, eles.get(0));
	}
	
	public static void selectByValue (WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByValue(value);
	}
	
	public static void selectByVisibleText (WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(value);
	}
	public static Boolean isAddSuccess (WebElement ele, String expect) {
		Boolean flag = false;
		for (int i=0; i<5; i++) {
			if(ele.getText().equals(expect)) {
				flag = true;
			} else {
				Tools.wait(2);
			}
				
		}
		return flag;
	}
	public static void switchToNewWin (WebDriver driver, String oldHanle) {
		WebAction.defaultFrame(driver);
		Set<String> handles = driver.getWindowHandles();
		for (String winHandle : handles) {
			if (!winHandle.equals(oldHanle)) {
				driver.switchTo().window(winHandle);
			}
		}
	}
	
	public static void alertAccept (WebDriver driver) {
		for (int i=0; i<10; i++) {
			try {
				Alert a = driver.switchTo().alert();
				a.accept();
				break;
			} catch (NoAlertPresentException ee) {
				Tools.wait(1);
				continue;
			}	
		}				
	}
	
	public static void alertDismiss (WebDriver driver) {
		for (int i=0; i<10; i++) {
			try {
				Alert a = driver.switchTo().alert();
				a.dismiss();
				break;
			} catch (NoAlertPresentException ee) {
				Tools.wait(1);
				continue;
			}	
		}				
	}
	
}
