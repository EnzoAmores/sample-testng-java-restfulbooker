package utilities;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.extentreports.ExtentManager;
import static utilities.extentreports.ExtentTestManager.getTest;
import static utilities.extentreports.ExtentTestManager.startTest;

public class TestListeners implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        Log.info("Starting Execution of Test Suite: " + context.getCurrentXmlTest().getSuite().getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("Finished Execution of Test Suite: " + context.getCurrentXmlTest().getSuite().getName());
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("Starting Test: " + getTestSuiteClassMethodName(result));
        startTest(getTestSuiteClassMethodName(result)).assignCategory(result.getTestContext().getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Test Succeeded: " + getTestSuiteClassMethodName(result));
        getTest().log(Status.PASS, "Successful!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info("Test Skipped: " + getTestSuiteClassMethodName(result));
        getTest().log(Status.SKIP, result.getThrowable());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info("Test Failed: " + getTestSuiteClassMethodName(result));
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