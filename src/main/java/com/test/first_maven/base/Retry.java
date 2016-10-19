package com.test.first_maven.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int retryCount         = 0;
    private int maxRetryCount     = 10;   // retry a failed test 2 additional times

    public boolean retry(ITestResult result) {
        if (retryCount <maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
