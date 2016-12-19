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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebAction {
	private final static Logger log = LogManager.getFormatterLogger(WebAction.class);
    /**
     * 打开网址(如果title为“无法访问”，则等待一秒后重试)
     * 
     * @param driver 浏览器驱动 	
     *        url    网址
     *            
     * @return 无
     * 
     * @author shenhua
     */
	public static void get(WebDriver driver, String url) throws InterruptedException {
		Boolean flag = false;
		for (int i = 0; i < 3; i++) {
			driver.get(url);
			String title = driver.getTitle();
			if (title.contains("无法访问")) {
				log.info("title is 无法访问, so wait 1s");
				Tools.wait(1);
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
    /**
     * 打开网址(直到预期某个元素可见)
     * 
     * @param driver 			浏览器驱动 	
     *        url    			网址
     *        idOrXpath 		元素的id或xpath
     *            
     * @return 无
     * 
     * @author shenhua
     */
	public static void get(WebDriver driver, String url, String idOrXpath) {
		Boolean flag = false;
		for (int i = 0; i < 30; i++) {
			driver.get(url);
			Boolean isVisible = isVisible(driver, idOrXpath);
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
    /**
     * 判断元素是否存在
     * 
     * @param driver 			浏览器驱动 	
     *        idOrXpath 		元素的id或xpath
     *            
     * @return true:可见/false:不存在或存在不可见
     * 
     * @author shenhua
     */
	public static Boolean isExist(WebDriver driver, String idOrXpath) {
		WebElement ele;
		if (!idOrXpath.startsWith("/")) {
			idOrXpath = "//*[@id='" + idOrXpath +"']";
		}
		try {
			ele = driver.findElement(By.xpath(idOrXpath));
		} catch (NoSuchElementException e) {
			log.info(idOrXpath + ": not exist!");
			return false;
		}
		log.info(idOrXpath + ": exist!");
		return true;

	}
    /**
     * 判断元素是否可见
     * 
     * @param driver 			浏览器驱动 	
     *        idOrXpath 		元素的id或xpath
     *            
     * @return true:可见/false:不存在或存在不可见
     * 
     * @author shenhua
     */
	public static Boolean isVisible(WebDriver driver, String idOrXpath) {
		WebElement ele;
		if (!idOrXpath.startsWith("/")) {
			idOrXpath = "//*[@id='" + idOrXpath +"']";
		}
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
    /**
     * 判断元素是否可见(最长等待n秒)
     * 
     * @param driver 			浏览器驱动 	
     *        idOrXpath 		元素的id或xpath
     *        sencond			最长等待时间，秒
     *            
     * @return true:可见/false:不存在或存在不可见
     * 
     * @author shenhua
     */		
	public static Boolean waitUntilVisible (WebDriver driver, String idOrXpath, int sencond) {
		if (!idOrXpath.startsWith("/")) {
			idOrXpath = "//*[@id='" + idOrXpath +"']";
		}
		Wait<WebDriver> wait =new WebDriverWait(driver, sencond);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(idOrXpath)));
		} catch(TimeoutException e) {
			log.info(idOrXpath + ": not visible!");
			return false;
		}		
		log.info(idOrXpath + ": visible!");
		return true;
	}
    /**
     * 先回到默认frame,再进入指定frame
     * 
     * @param driver 			浏览器驱动 	
     *        idOrName 			frame元素的id或name
     *            
     * @return 无
     * 
     * @author shenhua
     */
	public static void enterFrameFromDef (WebDriver driver, String idOrName) {
		defaultFrame(driver);
		enterFrame(driver, idOrName);
	}
    /**
     * 先回到默认frame,再进入指定frame
     * 
     * @param driver 			浏览器驱动 	
     *        ele 				frame元素
     *            
     * @return 无
     * 
     * @author shenhua
     */	
	public static void enterFrameFromDef (WebDriver driver, WebElement ele) {
		driver.switchTo().defaultContent();
		enterFrame(driver, ele);
	}
    /**
     * 进入指定frame(带等待，最多大概10秒)
     * 
     * @param driver 			浏览器驱动 	
     *        idOrName 			frame元素的id或name
     *            
     * @return 无
     * 
     * @author shenhua
     */	
	public static void enterFrame (WebDriver driver, String idOrName) {
		for (int i=0;i<=5;i++) {
			try {
				driver.switchTo().frame(idOrName);
			} catch(NoSuchFrameException e) {
				if (i==5) {
					log.error("enter frame " + idOrName + " fail!");
				} else {
					Tools.wait(2);
					continue;
				}
				
			}
			break;
		}	
		log.info("enter frame " + idOrName + " success!");
	}
    /**
     * 进入指定frame(带等待，最多大概10秒)
     * 
     * @param driver 			浏览器驱动 	
     *        ele 				frame元素
     *            
     * @return 无
     * 
     * @author shenhua
     */	
	public static void enterFrame (WebDriver driver, WebElement ele) {
		for (int i=0;i<=5;i++) {
			try {
				driver.switchTo().frame(ele);
			} catch(NoSuchFrameException e) {
				if (i==5) {
					log.error("enter frame " + ele.toString() + " fail!");
				} else {
					Tools.wait(2);
					continue;
				}				
			}
			break;
		}	
		log.info("enter frame " + ele.toString() + " success!");
	}
    /**
     * 回到默认frame
     * 
     * @param driver 			浏览器驱动 	
     *            
     * @return 无
     * 
     * @author shenhua
     */	
	public static void defaultFrame (WebDriver driver) {
		driver.switchTo().defaultContent();
		log.info("enter default content success!");
	}	
    /**
     * 进入最新的frame（idOrXpath能查到多个frame的时候）
     * 
     * @param driver 			浏览器驱动 	
     *            
     * @return 无
     * 
     * @author shenhua
     */		
	public static void enterRecentFrame (WebDriver driver, String idOrXpath) {	
		if (!idOrXpath.startsWith("/")) {
			idOrXpath = "//*[@id='" + idOrXpath +"']";
		}
		WebAction.defaultFrame(driver);
		List<WebElement> eles = driver.findElements(By.xpath(idOrXpath));
//		WebAction.enterFrameFromDef(driver, eles.get(eles.size()-1));
		WebAction.enterFrame(driver, eles.get(eles.size()-1));
	}
    /**
     * 进入第一个frame（idOrXpath能查到多个frame的时候）
     * 
     * @param driver 			浏览器驱动 	
     *            
     * @return 无
     * 
     * @author shenhua
     */	
	public static void enterFirstFrame (WebDriver driver, String idOrXpath) {
		if (!idOrXpath.startsWith("/")) {
			idOrXpath = "//*[@id='" + idOrXpath +"']";
		}
		WebAction.defaultFrame(driver);
		List<WebElement> eles = driver.findElements(By.xpath(idOrXpath));
//		WebAction.enterFrameFromDef(driver, eles.get(0));
		WebAction.enterFrame(driver, eles.get(0));
	}
    /**
     * js方式点击
     * 
     * @param driver 			浏览器驱动 	
     * 		  ele 				元素
     *            
     * @return 无
     * 
     * @author shenhua
     */
	public static void jsClick (WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", ele);
	}
    /**
     * js方式输入
     * 
     * @param driver 			浏览器驱动 	
     * 		  ele 				元素
     *        value				输入的值
     *            
     * @return 无
     * 
     * @author shenhua
     */
	public static void jsSendKeys (WebDriver driver, WebElement ele, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		log.info("jsSendKeys:" + value);
        js.executeScript("arguments[0].value = \"" + value + "\";", ele);
	}
    /**
     * 先滚动到指定元素，再点击
     * 
     * @param driver 			浏览器驱动 	
     * 		  ele 				元素
     *            
     * @return 无
     * 
     * @author shenhua
     */	
	public static void clickWithScroll (WebDriver driver, WebElement ele) {
		scrollTo(driver, ele);
		ele.click();
	}
    /**
     * 点击(等待元素出现，最长5秒)
     * 
     * @param driver 			浏览器驱动 	
     * 		  idOrXpath 		元素id或xpath
     *            
     * @return 无
     * 
     * @author shenhua
     */		
	public static void clickWithWait (WebDriver driver, String idOrXpath) {
		clickWithWait(driver, idOrXpath, 5);
	}
    /**
     * 点击(等待元素出现，最长n秒)
     * 
     * @param driver 			浏览器驱动 	
     * 		  idOrXpath 		元素id或xpath
     * 		  second			指定等待的秒数
     *            
     * @return 无
     * 
     * @author shenhua
     */		
	public static void clickWithWait (WebDriver driver, String idOrXpath, int second) {
		Boolean flag = waitUntilVisible(driver, idOrXpath, second);
		if (!idOrXpath.startsWith("/")) {
			idOrXpath = "//*[@id='" + idOrXpath +"']";
		}
		if (flag.equals(true)) {
			driver.findElement(By.xpath(idOrXpath)).click();
		} else {
			log.error(idOrXpath + " not visible! so can not click!");
		}
	}	
    /**
     * 滚动到指定元素
     * 
     * @param driver 			浏览器驱动 	
     * 		  ele 				元素
     *            
     * @return 无
     * 
     * @author shenhua
     */	
	public static void scrollTo (WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		log.info("scrollTo:" + ele);
        js.executeScript("arguments[0].scrollIntoView();", ele);
	}
    /**
     * 下拉框选择，根据value
     * 
     * @param ele 			元素	
     * 		  value 		value属性值
     *            
     * @return 无
     * 
     * @author shenhua
     */	
	public static void selectByValue (WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByValue(value);
	}
    /**
     * 下拉框选择，根据显示文本
     * 
     * @param ele 			元素	
     * 		  value 		text()显示的文本
     *            
     * @return 无
     * 
     * @author shenhua
     */	
	public static void selectByVisibleText (WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(value);
	}
    /**
     * 判断是否增加成功
     * 
     * @param ele 			元素	
     * 		  expect 		预期值
     *            
     * @return true:成功；false:失败
     * 
     * @author shenhua
     */	
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
    /**
     * 切换到新打开的窗口
     * 
     * @param driver 			驱动
     * 		  oldHanle 		              旧窗口的handle
     *            
     * @return 无
     * 
     * @author shenhua
     */		
	public static void switchToNewWin (WebDriver driver, String oldHanle) {
		WebAction.defaultFrame(driver);
		Set<String> handles = driver.getWindowHandles();
		for (String winHandle : handles) {
			if (!winHandle.equals(oldHanle)) {
				driver.switchTo().window(winHandle);
			}
		}
	}
    /**
     * alert处理，确定（等待出现，最长10秒）
     * 
     * @param driver 			驱动
     *            
     * @return 无
     * 
     * @author shenhua
     */		
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
    /**
     * alert处理，取消（等待出现，最长10秒）
     * 
     * @param driver 			驱动
     *            
     * @return 无
     * 
     * @author shenhua
     */	
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
