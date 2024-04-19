package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReport() {
		String path = System.getProperty("user.dir")+"//reports//index.html" ;
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Web report Name");
		report.config().setDocumentTitle("Document title");
		
	    ExtentReports extent = new ExtentReports() ;
		extent.attachReporter(report);
        extent.setSystemInfo("Tester", "Dirash");
        return extent;
	}

}
