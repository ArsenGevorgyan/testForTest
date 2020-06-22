package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info("## " + getTestMethodName(iTestResult) + " test method started.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info("## " + getTestMethodName(iTestResult) + " test method succeed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info("## " + getTestMethodName(iTestResult) + " test method failed.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info("## " + getTestMethodName(iTestResult) + " test method skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("## " + getTestMethodName(iTestResult) + " test method failed within success %.");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("#### " + iTestContext.getName() + " test classes started.");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("#### " + iTestContext.getName() + " test classes finished.");
    }

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

//    @Attachment(value = "Page screenshot", type = "image/png")
//    public byte[] saveScreenshotPNG(WebDriver webDriver) {
//        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
//    }
//
//    public void takeScreenshot(ITestResult iTestResult, String strTestPhase) {
//        Object testClass = iTestResult.getInstance();
//        Object webDriver = ((BaseTest) testClass).driver.get();
//        if (webDriver instanceof WebDriver) {
//            saveScreenshotPNG((WebDriver) webDriver);
//        } else {
//            Log.error("TL: ## " + getTestMethodName(iTestResult) + " " + strTestPhase + ", something went wrong during get of the WebDriver from Test Result");
//        }
//    }
}
