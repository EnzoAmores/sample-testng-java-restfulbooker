package com.sample.restfulbooker.utilities.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/* Extent Report - Report & System Information
 * Required class for generating Extent Reports. Add/Modify/Remove values only if needed especially the Report Name. */
public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public static synchronized ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./Report.html");

        reporter.config().setReportName("Restful-Booker-Platform");
        reporter.config().setJs("document.getElementsByClassName('col-md-6')[1].style.setProperty('display','none');"); // To remove unnecessary chart shown by default.
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Enzo");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("User", System.getProperty("user.name"));

        return extentReports;
    }
}