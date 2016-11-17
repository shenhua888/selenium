package com.test.first_maven.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class NewWebDriverEventListener implements WebDriverEventListener {
	
	private Logger log = LogManager.getFormatterLogger(this.getClass());
	private By lastFindBy;
	private String originalValue;
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		 log.info("WebDriver navigating to:‘"+url+"‘");

	}

	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		lastFindBy = by;
		log.info("beforeFindBy:‘"+lastFindBy.toString()+"‘");
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		log.info("beforeClickOn:‘"+lastFindBy.toString()+"‘");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		originalValue = element.getAttribute("value");

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		 log.info("WebDriver changing value in element found "+lastFindBy+" from ‘"+originalValue+"‘ to ‘"+element.getAttribute("value")+"‘");

	}

	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		log.info("onException:‘"+throwable.getMessage()+"‘");
		takeScreenShot(driver);
	}
	
	 /**
     * @author Young
     */
    public void takeScreenShot(WebDriver driver) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String dateStr = sf.format(date);
        String path = "Exception_" + dateStr + ".png";
        takeScreenShot((TakesScreenshot)driver, path);
    }

    /**
     * @author Young
     * @param drivername
     * @param path
     */
    public void takeScreenShot(TakesScreenshot drivername, String path) {
        // this method will take screen shot ,require two parameters ,one is
        // driver name, another is file name
        String currentPath = System.getProperty("user.dir"); // get current work
        File file = new File(currentPath + "\\" + "pic");
        file.mkdir();
        String picPath = file.getPath();
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy
        try {
            log.info("onException save snapshot path is:" + picPath + "\\" + path);
            FileUtils.copyFile(scrFile, new File(picPath + "\\" + path));
        } catch (Exception e) {
            log.error("Can't save screenshot");
            e.printStackTrace();
        } finally {
            log.info("screen shot finished");
        }
    }

}
