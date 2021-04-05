package com.org.kore.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;

import com.org.kore.testbase.DriverSetUp;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportUtility extends DriverSetUp {

public synchronized static ExtentReports getReporter(ExtentReports extent) throws Exception {
		
		if (extent == null) {
			try {
				String dir = System.getProperty("user.dir");
				File index = new File(dir+"/ReportGenerator");
				String ind= index.toString();
				FileUtils.forceDelete(new File(ind));
				reportFolder = "WorkAssistReport";
			} catch (Exception e) {
				System.out.println(" In folder to Remove here $$$$ Yet to concentrate more in this area to avoid failures here $$$$");
			}
			s = new File("ReportGenerator/" + reportFolder + "/TestReport.html").getPath();
			extent = new ExtentReports(s, true, Locale.ENGLISH);
			extent.addSystemInfo("Environment", "UAT");
			extent.addSystemInfo("Application", "KORA WORK ASSIST");
			extent.assignProject("KORA WORK ASSIST");
		}

		return extent;
	}

}

// To get the report with date and time
/*public synchronized static ExtentReports getReporter(ExtentReports extent) {
	if (extent == null) {
		SimpleDateFormat sdfDateReport = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");// dd/MM/yyyy
		Date now = new Date();
		reportFolder = "HtmlReport_" + sdfDateReport.format(now);
		s = new File("ReportGenerator/" + reportFolder + "/TestReport.html").getPath();
		extent = new ExtentReports(s, true, Locale.ENGLISH);
		extent.addSystemInfo("Environment", "UAT");
		extent.assignProject("Kore Application");

	}

	return extent;
}*/
