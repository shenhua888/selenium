package com.test.first_maven.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	public void onFinish(ITestContext context) {

		Iterator<ITestResult> listOfskipedTests = context.getSkippedTests().getAllResults().iterator();
		while (listOfskipedTests.hasNext()) {
			ITestResult skipedTest = listOfskipedTests.next();
			ITestNGMethod method = skipedTest.getMethod();
			if (context.getSkippedTests().getResults(method).size() > 1) {
				listOfskipedTests.remove();
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					listOfskipedTests.remove();
				}
				if (context.getFailedTests().getResults(method).size() > 0) {
					listOfskipedTests.remove();
				}
			}
		}
		
		Iterator<ITestResult> listOfFailedTests = context.getFailedTests().getAllResults().iterator();
		while (listOfFailedTests.hasNext()) {
			ITestResult failedTest = listOfFailedTests.next();
			ITestNGMethod method = failedTest.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				listOfFailedTests.remove();
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					listOfFailedTests.remove();
				}
			}
		}
	}

	// Following are all the method stubs that you do not have to implement

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}
} // ends the class