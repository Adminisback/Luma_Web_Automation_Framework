package Luma_Test.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Luma_Extentreportss {

	public static ExtentReports getextentReportsObjects() {
		//ExtentReport  ExtentSparkReporter
				String path = System.getProperty("user.dir")+"\\reports\\Luma_Extent_Reports.html";
				ExtentSparkReporter report = new ExtentSparkReporter(path);
				report.config().setReportName("Automation Report - LUMA_SHOPPING");
				report.config().setDocumentTitle("Luma_Shopping");

				ExtentReports extent = new ExtentReports();
				extent.attachReporter(report);
				extent.setSystemInfo("Tester","Shirish Mistari");
				return extent;
	}
}
