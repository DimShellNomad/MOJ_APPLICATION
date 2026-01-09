package com.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            // Path where report will be saved
            File path = new File("target");
            if (!path.exists()) {
                path.mkdirs();
            }
            ExtentSparkReporter spark = new ExtentSparkReporter("/app/target/ExtentReport.html");
            spark.config().setReportName("MOJ Project Test");
            spark.config().setDocumentTitle("Test Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}