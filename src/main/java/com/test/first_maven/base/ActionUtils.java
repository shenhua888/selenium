package com.test.first_maven.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public static void get(WebDriver driver, String url, String visibleExpcet) throws InterruptedException {
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
				Thread.sleep(2000);
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
}
