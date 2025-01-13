package utilities.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

/* Extent Report - Logging Functions
 * Required class for generating Extent Reports. No need to modify the code here. Works even with parallel execution. */
public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.createExtentReports();

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = extent.createTest(testName);

        extentTestMap.put((int) Thread.currentThread().threadId(), test);

        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().threadId());
    }
}