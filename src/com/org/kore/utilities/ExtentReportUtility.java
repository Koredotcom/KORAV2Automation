package com.org.kore.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.org.kore.testbase.DriverSetUp;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportUtility extends DriverSetUp {

	public synchronized static ExtentReports getReporter(ExtentReports extent) {
		if (extent == null) {
			SimpleDateFormat sdfDateReport = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");// dd/MM/yyyy
			Date now = new Date();
			reportFolder = "HtmlReport_" + sdfDateReport.format(now);
			s = new File("ReportGenerator/" + reportFolder + "/TestReport.html").getPath();

			extent = new ExtentReports(s, true, Locale.ENGLISH);
			// extent.loadConfig(new
			// File("extent-config.xml").getAbsoluteFile());

			extent.addSystemInfo("Environment", "UAT");
			extent.assignProject("Kore Application");

		}

		return extent;
	}

}
