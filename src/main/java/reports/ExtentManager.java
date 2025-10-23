package reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports createExtentInstance() {
		
		if(extent==null) {
			String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String reportPath = System.getProperty("user.dir")+"/reports/ExtentReport_"+timeStamp+".html";
			
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.config().setReportName("Automation Test Report");
			sparkReporter.config().setDocumentTitle("Extent Report");
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Tester", "Mohammad Junaid");
			extent.setSystemInfo("Browser", "Chrome");
			
		}
		
		return extent;
	}

}
