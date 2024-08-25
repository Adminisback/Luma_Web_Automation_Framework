package Luma_Test.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Luma_Test.resources.Luma_Extentreportss;

public class Luma_Listners extends Luma_BaseClass implements ITestListener {

	public ExtentTest test;
	ExtentReports extent;

	ThreadLocal<ExtentTest> ExtentTest1;
	//public WebDriver driver;

	public Luma_Listners() {
	
		extent = Luma_Extentreportss.getextentReportsObjects();
		ExtentTest1 = new ThreadLocal<>(); // Unique thread id -> maps Test
	}

	@Override
	public void onTestStart(ITestResult result) {

		ITestListener.super.onTestStart(result);
		test = extent.createTest(result.getMethod().getMethodName());
		ExtentTest1.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		ITestListener.super.onTestSuccess(result);
		test.log(Status.PASS, "Required Test Case has been passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		ITestListener.super.onTestFailure(result);
		ExtentTest1.get().fail(result.getThrowable());

		// We will get driver life from below line....
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}

		// Take screen shot here on failure of test case..
		String FilePath = null;
		try {
			FilePath = takeScreenShotForFailedTC(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		ExtentTest1.get().addScreenCaptureFromPath(FilePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {

		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {

		ITestListener.super.onFinish(context);
		extent.flush();
	}

}
