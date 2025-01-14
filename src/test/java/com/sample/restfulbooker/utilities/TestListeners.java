package com.sample.restfulbooker.utilities;

import com.aventstack.extentreports.Status;
import com.sample.restfulbooker.utilities.extentreports.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.sample.restfulbooker.utilities.extentreports.ExtentTestManager.getTest;
import static com.sample.restfulbooker.utilities.extentreports.ExtentTestManager.startTest;

/* Test Listeners
 * Mostly for logs right now. No need to change anything here unless needed like for cleanups or something. Supports parallel testing. */
public class TestListeners implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        Loggers.info("Starting Execution of Test Suite: " + context.getCurrentXmlTest().getSuite().getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Loggers.info("Finished Execution of Test Suite: " + context.getCurrentXmlTest().getSuite().getName());
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        Loggers.info("Starting Test: " + getTestSuiteClassMethodName(result));
        startTest(getTestSuiteClassMethodName(result)).assignCategory(result.getTestContext().getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Loggers.info("Test Succeeded: " + getTestSuiteClassMethodName(result));
        getTest().log(Status.PASS, "Successful!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Loggers.info("Test Skipped: " + getTestSuiteClassMethodName(result));
        getTest().log(Status.SKIP, result.getThrowable());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Loggers.info("Test Failed: " + getTestSuiteClassMethodName(result));
        getTest().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    private String getTestSuiteClassMethodName(ITestResult result) {
        return result.getMethod().getMethodName();
    }
}