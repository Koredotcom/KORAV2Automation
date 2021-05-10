package com.org.kore.testbase;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.utilities.DataReader;
import com.org.kore.utilities.ExtentReportUtility;
import com.org.kore.utilities.PropertyLoader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class DriverSetUp {

	public static ExtentTest test;
	public static String s;
	public static String reportFolder = "";
	static Map extentTestMap = new HashMap();

	static int passcount = 0;
	static int failcount = 0;
	static int warncount = 0;
	static int totaltc = 0;
	static int skipcount = 0;
	Map<String, String> map = new HashMap<String, String>();
	
	public AppiumDriver appiumDriver;
	public RemoteWebDriver remoteDriver;
	public static String platformName = null, browser = null, appType = null, tool = null, appName = null;
	public static ExtentReports extent;
	public static PropertyLoader fu;
	public static ElementRepository er;
	public static String DBUrl;
	public static String username;
	public static String passWord;
	public static String DBName;
	public static String IntDBName;
	public static String IncidentDBName;
	public static String Environment;
	public static String App;
	public static LinkedHashMap<String, String> propsMap = new LinkedHashMap<>();
	public static LinkedHashMap<String, String> UtilityMap = new LinkedHashMap<>();
	public static LinkedHashMap<String, String> testdataMap = new LinkedHashMap<>();
	public static LinkedHashMap<String, String> drdataMap = new LinkedHashMap<>();
	public static DataReader dr;
	PageBase pb;
	public String workingurl;

	public static AppiumServiceBuilder builder;
	public static AppiumDriverLocalService service;
	
	public DriverSetUp() {

	}

	public void startWindowsAppiumServer() {
		AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
		System.out.println("Started");
		appiumServiceBuilder
				// .usingAnyFreePort()
				.withIPAddress("127.0.0.1")
				// .withLogFile(logFile)
				.withArgument(GeneralServerFlag.STRICT_CAPS).withArgument(GeneralServerFlag.LOG_TIMESTAMP);
		service = AppiumDriverLocalService.buildService(appiumServiceBuilder);
		service.start();
		System.out.println("Server Started");
	}

	public void stopServer() {
		service.stop();
		System.out.println("Server stopped");
	}

	@BeforeSuite
	public void executeSuite() {
		try {
			// startWindowsAppiumServer();
			extent = ExtentReportUtility.getReporter(extent);
			test = DriverSetUp.test;
			extent = DriverSetUp.extent;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void setUp(ITestContext ctx) throws Exception {
		try {
			// System.out.println(System.getProperty("user.dir"));
			pb = new PageBase(remoteDriver);
			browser = ctx.getCurrentXmlTest().getParameter("browser");
			App = ctx.getCurrentXmlTest().getParameter("environment");
			DriverSetUp.dr = new DataReader();
			er = new ElementRepository();
			fu = new PropertyLoader();
			// dr.getSetUpValue("Execution", className, columnHeader)
			// App = fu.getAppProperties("App");
			// App = System.getProperty("app.prop");

			System.out.println("@@@@@@@@@@@@  " + App + "  @@@@@@@@@@@@");
			propsMap = fu.jsonRead(App);

			UtilityMap = fu.jsonRead("UTILITIES");
			testdataMap = fu.jsonRead("TESTDATA");
			drdataMap = fu.jsonRead("WSTESTDATA");

			switch (App) {

			case "QA":
				er.selWeb();
				remoteDriver = pb.startBrowser(browser);
				break;
			case "IOSNATIVE":
				er.repoIOS();
				appiumDriver = pb.startAppiumDriver(App);
				break;
			case "ANDROIDNATIVE":
				er.repoAnd();
				appiumDriver = pb.startAppiumDriver(App);
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}

	@AfterClass
	public void tearDown(ITestContext ctx) throws Exception {
		switch (App) {
		case "QA":
			remoteDriver.quit();
			break;
		case "OBSERVATIONPORTAL":
			remoteDriver.quit();
			break;
		case "IOSNATIVE":
			appiumDriver.quit();
			break;
		case "ANDROIDNATIVE":
			appiumDriver.quit();
			break;
		case "IOSWEB":
			appiumDriver.quit();
			break;
		case "IVALETWEB":
			remoteDriver.quit();
			break;
		}
	}

	@AfterTest
	public void flush() {
		// System.out.println("Executes only once after all the TC's");
		System.out.println("Total executed test cases are " + totaltc);
		extent.flush();
	}

	@AfterMethod // 
	protected void afterMethod(ITestResult result) {
		System.out.println("In After method method name is :"+Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodname=result.getName();
		LogStatus status = test.getRunStatus();
		String mystatus = status.toString();
		
		map.put(methodname, mystatus);
		
		if (mystatus.equalsIgnoreCase("pass")) {
			passcount++;
		} else if (mystatus.equalsIgnoreCase("fail")) {
			failcount++;
		} else if (mystatus.equalsIgnoreCase("warning")) {
			warncount++;
		} else {
			skipcount++;
		}
		totaltc = passcount + failcount + warncount + skipcount;
		System.out.println(methodname+" PASS TC's are :::::: " + passcount);
		System.out.println(methodname+" FAIL TC's are :::::: " + failcount);
		System.out.println(methodname+" WARNING TC's are :::::: " + warncount);
		System.out.println(methodname+" SKIP/WARNING TC's are :::::: " + skipcount);
		System.out.println("Total TC's are :::::: " + totaltc);
		
	}

	static public void zipFolder(String srcFolder, String destZipFile) throws Exception {
		try {
			ZipOutputStream zip = null;
			FileOutputStream fileWriter = null;
			fileWriter = new FileOutputStream(destZipFile);
			zip = new ZipOutputStream(fileWriter);
			addFolderToZip("", srcFolder, zip);
			zip.flush();
			zip.close();
		} catch (Exception e) {
			System.out.println("Fail to zip the report folder");
		}
	}

	static private void addFileToZip(String path, String srcFile, ZipOutputStream zip) throws Exception {
		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
		}
	}

	static private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws Exception {
		File folder = new File(srcFolder);

		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
			} else {
				addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
			}
		}
	}

	public void mitigateHTML(File htmlfilepath) throws Exception {
		workingurl =null;
		
		if (App.equalsIgnoreCase("QA")){
			workingurl="https://workassist-qa.kore.ai/";
		}else if (App.equalsIgnoreCase("Dev")){
			workingurl="https://workassist-dev.kore.ai/";
		}else {
			workingurl="Please check the environment and url";
		}
		
		StringBuilder html = new StringBuilder();
		FileReader freader = new FileReader(htmlfilepath);
		try {
			BufferedReader breader = new BufferedReader(freader);
			String val;
			while ((val = breader.readLine()) != null) {
				html.append(val);
			}
			String htmlasstring = html.toString();
			breader.close();
			System.out.println("_______________ HTML as String B4 Replacing Https _______________");
			String find = "<link href='https:";
			String replaceto = "<link href='http:";
			String hsAfterreplace = htmlasstring.replace(find, replaceto);
			System.out.println("_______________ HTML as String After Replacing https to http _______________");
			File mitigatedhtml = new File("source.html");
			BufferedWriter bw = new BufferedWriter(new FileWriter(mitigatedhtml));
			bw.write(hsAfterreplace);
			bw.close();
		} catch (Exception ex) {
			System.out.println("Html file not found to convert into string");
			System.out.println(ex.getMessage());
		}
	}
	
	public void customReport() throws Exception {
		File f = new File("ConsolidatedReport.html");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(
				"<html><head><style>table {font-family: arial, sans-serif; border-collapse: collapse;width: 30%;}td, th {border: 1px solid #dddddd;text-align: center;padding: 8px;}tr:nth-child(even) {background-color: #dddddd;}</style></head>");
		bw.write("<body><h2>Work Assist Automation Execution Report</h2>");
		bw.write("<table><tr><th>Status</th> <th>Count</th></tr><tr><td>PASS</td><td>" + passcount
				+ "</td></tr><tr><td>FAIL</td><td>" + failcount + "</td></tr><tr><td>WARNING</td><td>" + warncount + "</td></tr><tr><td>TOTAL</td><td>" + totaltc
				+ "</td></tr>");
		bw.write("<table></table>");
		bw.write("</table></body></html>");
		bw.close();
		Desktop.getDesktop().browse(f.toURI());
	}
	
	public void tcTableCreation(Map<String, String> map2) throws IOException {
		String dir = System.getProperty("user.dir");
		BufferedWriter writer;
		File file;
		String tcdescription = null;
		String status = null;
		try {
			file = new File(dir+"/ReportGenerator/TCResults.html");
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(
					"<html><head></head>");
			writer.write("<body><h2>Work Assist Automation Execution Report</h2></body>");
			
			writer.write("<body><table><table border ='1'><tr><th><b>Scope</b></th><td> <a href="+"https://docs.google.com/spreadsheets/d/1Q3aIa9lp_im-4k6athtNH8RVgJ9KfWMfnyhppFIds6A/edit?ts=5fd77590#gid=1327035589"+"> Basic sanity test cases (Spreadsheet Link too)</a></td><tr><th><b>Environment</b></th><td>"+workingurl+"  <b>("+App+"  )</b>"+"</td></tr><tr><th><b>Browser</b></th><td>Chrome</td></tr></body>");
			writer.write("<body><h2> </h2></body>");
			writer.write("<table> </table>");
			
		//	writer.write("<body><h2> </h2>");
			
			writer.write("<body><table><table border ='1'><tr bgcolor="+"#02c8ff"+"><th><font color="+"white"+"><b>Scenarios Status</b></th><th><font color="+"white"+"><b>Scenarios Count</b></th></tr><tr><td><font color="+"green"+"><b>PASS</b></font></td><td style="+"text-align:center"+">" + passcount
					+ "</td></tr><tr><td><font color="+"red"+"><b>FAIL</b></font></td><td style="+"text-align:center"+">" + failcount + "</td></tr><tr><td><font color="+"orange"+"><b>WARNING</b></font></td><td style="+"text-align:center"+">" + warncount + "</td></tr><tr><td><b>TOTAL</b></td><td style="+"text-align:center"+">"+"<b>" + totaltc
					+"</b>"+ "</td></tr></body>");
			writer.write("<body><h2> </h2></body>");
			writer.write("<table> </table>");
			
			writer.write("<body><table><table border ='1'>" + "<tr bgcolor="+"#02c8ff"+">" + "<th><font color="+"white"+"><b>Module</b></th>" +"<th><font color="+"white"+"><b>Scenario</b></th>"+ "<th><font color="+"white"+"><b>TC Number</b></th>"+"<th><font color="+"white"+"><b>Description</b></th>"
                    + "<th><font color="+"white"+"><b>Status</b></th>");
		//	writer.write("<body><h2> </h2></body>");
		//	writer.write("<table> </table>");
			
			// for (int i=0; i<totaltc;i++){
			System.out.println("in map split");
			
			int cnt=1;
			for (Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "=" + entry.getValue());
				tcdescription = entry.getKey();
				status = entry.getValue().toUpperCase();

				if(status.equalsIgnoreCase("PASS")){
					status = "<font color="+"green"+"><b>PASS</b></font>";
				}else if(status.equalsIgnoreCase("FAIL")){
					status = "<font color="+"red"+"><b>FAIL</b></font>";
				}else if(status.equalsIgnoreCase("WARNING")){
					status = "<font color="+"orange"+"><b>WARNING</b></font>";
				}else if(status.equalsIgnoreCase("INFO")){
					status = "<font color="+"gold"+"><b>INFO</b></font>";
				}else if(status.equalsIgnoreCase("SKIP")){
					status = "<font color="+"purple"+"><b>SKIP</b></font>";
				}

				String TCnums="";
				String methodname=tcdescription;
				String[] arr=methodname.split("_");
				String ModuleName=arr[0];
				if(ModuleName.equalsIgnoreCase("MC"))
				{
					ModuleName="Messages_Chats";
				}else if(ModuleName.equalsIgnoreCase("MDR")){
					ModuleName="Messages_DR";
				}
				String TestcaseDesction=arr[arr.length-1];
				for(int i=1;i<arr.length-1;i++)
				{
					TCnums=TCnums+arr[i]+" ";
				}		
				String Testcases=TCnums;

				writer.write("<tr>");
				
				writer.write("<td><font-family:"+"Calibri (Body)>");
				writer.write(ModuleName);
				writer.write("</td> ");
				
				writer.write("<td><font-family:"+"Calibri (Body)>");
				writer.write("Scenario "+cnt++);
				writer.write("</td> ");
				
				writer.write("<td><font-family:"+"Calibri (Body)>");
				writer.write(Testcases);
				writer.write("</td> ");
				
				writer.write("<td><font-family:"+"Calibri (Body)>");
				writer.write(TestcaseDesction);
				writer.write("</td>");
				
				writer.write("<td><font-family:"+"Calibri (Body)>");
				writer.write(status);
				writer.write("</td>");
				
				writer.write("</tr> ");
			}
			// }
			writer.write("</tr></table>" + "</body>" + "</html>");
		//	writer.write("</html>");
			writer.close();
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			System.out.println("IO EXCEPTION-----" + e);
		}

		System.out.println("----------END---------------");
	}
	
	@AfterSuite // This is working, before moving the report to other location
	public void closeConnections() throws Exception {
		try {
			String dir = System.getProperty("user.dir");
			// stopServer();
			File htmlFile = new File(ExtentReportUtility.s);
			System.out.println("_______________ Original report path_______________" + htmlFile);
			mitigateHTML(htmlFile);
			Desktop.getDesktop().browse(htmlFile.toURI());
			zipFolder(dir + "/ReportGenerator/WorkAssistReport", dir + "/ReportGenerator/WorkAssistReport.zip");
		//	customReport();
			tcTableCreation(map);
		} catch (Exception e) {
			System.out.println("End");
		}
	}

	/*@AfterSuite // This is working, before moving the report to other location
	public void closeConnections() throws Exception { //
		String dir = System.getProperty("user.dir");
		// stopServer();
		File htmlFile = new File(ExtentReportUtility.s);
		System.out.println("_______________This is my latest report_________________" + htmlFile);
		Desktop.getDesktop().browse(htmlFile.toURI());
		// To zip the structure and to share it with team via Jenkins Job
		zipFolder(dir + "/ReportGenerator/WorkAssistReport", dir + "/ReportGenerator/WorkAssistReport.zip");
		// High level customized report
		customReport();

	}*/
	
	/*	@AfterSuite
	public void closeConnections() throws Exception {
		File htmlFile = null;
		try {
			Path result = null;
			String dir = System.getProperty("user.dir");
			htmlFile = new File(ExtentReportUtility.s);
			System.out.println("____ This is my original path :____ " + htmlFile);
			String test = htmlFile.toString();
			String finalpath;
			finalpath = new File(dir + "/JenkinsReport/TestReport.html").getPath();
			finalpath.toString();
			File htmlaftercopy = new File(finalpath);
			try {
				result = Files.copy(Paths.get(test), Paths.get(finalpath).resolveSibling("TestReport.html"),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.out.println("Exception while moving file: " + e.getMessage());
			}
			if (result != null) {
				System.out.println("File moved successfully.");
				System.out.println("____ This is my updated path :____ " + htmlaftercopy);

				Desktop.getDesktop().browse(htmlFile.toURI());

			} else {
				System.out.println("File movement failed.");
			}

		} catch (Exception e) {
			Desktop.getDesktop().browse(htmlFile.toURI());
		}
	}*/
}
