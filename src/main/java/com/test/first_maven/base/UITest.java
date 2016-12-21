package com.test.first_maven.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author shenhua
 *
 */
public class UITest {
	public WebDriver driver;
	Logger log = LogManager.getFormatterLogger(this.getClass());

	public WebDriver getDriver() {
		return driver;
	}


    /**
     * 截图
     * 
     * @param 无
     *            
     * @return 无
     * 
     * @author shenhua
     */
	public void takeScreenShot() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String dateStr = sf.format(date);
		String path = this.getClass().getSimpleName() + "_" + dateStr + ".png";
		takeScreenShot((TakesScreenshot) this.getDriver(), path);
	}

    /**
     * 截图
     * 
     * @param driver 驱动
     *        path   图片保存目录
     *            
     * @return 无
     * 
     * @author shenhua
     */
	private void takeScreenShot(TakesScreenshot driver, String path) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name
		String currentPath = System.getProperty("user.dir"); // get current work
		File file = new File(currentPath + "\\" + "pic");
		file.mkdir();
		String picPath = file.getPath();
		File scrFile = driver.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		try {
			log.info("save snapshot path is:" + picPath + "\\" + path);
			FileUtils.copyFile(scrFile, new File(picPath + "\\" + path));
		} catch (Exception e) {
			log.error("Can't save screenshot");
			e.printStackTrace();
		} finally {
			log.info("screen shot finished");
		}
	}

}