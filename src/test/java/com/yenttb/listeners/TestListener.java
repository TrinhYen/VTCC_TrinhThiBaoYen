package com.yenttb.listeners;

import com.yenttb.helpers.CaptureHelper;
import com.yenttb.helpers.PropertiesHelper;
import com.yenttb.reports.AllureManager;
import com.yenttb.reports.ExtentReportManager;
import com.yenttb.reports.ExtentTestManager;
import com.yenttb.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LogUtils.info("onStart: " + iTestContext.getStartDate());
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LogUtils.info("onFinish: " + iTestContext.getEndDate());
        //Gửi mail

        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info("Start test case " + iTestResult.getName());
        CaptureHelper.startRecord(iTestResult.getName());

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(iTestResult), getTestDescription(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("PASSED!! Test case " + iTestResult.getName());
        CaptureHelper.stopRecord();

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, iTestResult.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.info("FAILED!! Test case " + iTestResult.getName());
        CaptureHelper.stopRecord();
        //CaptureHelper.captureScreenshot(iTestResult.getName());

        LogUtils.error(iTestResult.getThrowable());

        //Extent Report
        ExtentTestManager.addScreenshot(iTestResult.getName());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getName() + " is failed.");

        //Allure Report
        AllureManager.saveTextLog(iTestResult.getThrowable().toString());
        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        CaptureHelper.stopRecord();

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, iTestResult.getThrowable().toString());
    }
}