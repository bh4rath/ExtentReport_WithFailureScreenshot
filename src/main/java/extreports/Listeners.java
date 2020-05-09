package extreports;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends base implements ITestListener {

	ExtentReports extent = ExtentReportsNG.extentReportGenerator();
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Case Pased");
	}

	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		test.fail(result.getThrowable());
		Object testObject = result.getInstance();
		Class resultClass = result.getTestClass().getRealClass();
		try {
			driver =(WebDriver)resultClass.getDeclaredField("driver").get(testObject);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1 ) {
			e1.printStackTrace();
		} 
		
		try {
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}