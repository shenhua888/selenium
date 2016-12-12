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

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info("Test Success");
        super.onTestSuccess(tr);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error("Test Failure");
        super.onTestFailure(tr);
        takeScreenShot(tr);
    }

    private void takeScreenShot(ITestResult tr) {
        UITest b = (UITest) tr.getInstance();
        WebDriver currentDirver = b.getDriver();
        log.info("The title of current page is :" + currentDirver.getTitle());
        b.takeScreenShot();

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.error("Test Skipped");
        super.onTestSkipped(tr);
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Finsh");
        super.onTestStart(result);
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