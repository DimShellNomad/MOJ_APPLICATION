package com.framework.base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.framework.utils.ExtentManager;
import com.framework.utils.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener {
    // ThreadLocal ensures reports don't get mixed up during parallel execution
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getReportInstance().createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // 1. Log the error details
        extentTest.get().fail(result.getThrowable());

        // 2. Capture and attach screenshot
        String base64Code = ScreenshotUtils.getBase64Screenshot();
        extentTest.get().fail("Test Failed Screenshot",
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed Successfully");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        ExtentManager.getReportInstance().flush();
    }
}