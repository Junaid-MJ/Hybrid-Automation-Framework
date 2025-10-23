package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import driver.DriverFactory;
import reports.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
	
	private static ExtentReports extent = ExtentManager.createExtentInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	@Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        // Capture and attach screenshot
        String screenshotPath = ScreenshotUtil.CaptureScreenShot(
                DriverFactory.getDriver(),
                result.getMethod().getMethodName()
        );

        try {
            test.get().fail("Screenshot below:",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
	
	
	
	

}
