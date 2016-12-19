package com.test.first_maven.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * 
 * @author ShenHua
 *
 */
public class TestNGListener extends TestListenerAdapter {
    private Logger log = LogManager.getFormatterLogger(this.getClass());
    private UITest ut;
    private WebDriver driver;
    
    private void quit(WebDriver driver) {
    	log.info("qiut chrome");
//    	driver.quit();
    }   
    private void setDriver(ITestResult tr) {
    	ut = (UITest) tr.getInstance();
        driver = ut.getDriver();
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info("Test Success");
        super.onTestSuccess(tr);
        setDriver(tr);
        quit(driver);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error("Test Failure");
        super.onTestFailure(tr);
        setDriver(tr);
        takeScreenShot(tr);
        quit(driver);
    }

    private void takeScreenShot(ITestResult tr) {
        log.info("The title of current page is :" + driver.getTitle());
        ut.takeScreenShot();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.error("Test Skipped");
        super.onTestSkipped(tr);  
        setDriver(tr);
        quit(driver);
    }

    @Override
    public void onTestStart(ITestResult tr) {
        log.info("TestCase Start");
        super.onTestStart(tr);
    }

    @Override
    public void onStart(ITestContext testContext) {
        log.info("Test Start");
        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        log.info("Test Finish");
        super.onFinish(testContext);
    }

}