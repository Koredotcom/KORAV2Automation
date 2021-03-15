package com.org.kore.testbase;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
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

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class DriverSetUp {

	public static ExtentTest test;
	public static String s;
	public static String reportFolder = "";
	static Map extentTestMap = new HashMap();

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
			System.out.println(this.getClass().getName());
			String browser = ctx.getCurrentXmlTest().getParameter("browser");
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
			drdataMap= fu.jsonRead("WSTESTDATA");

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

	// @AfterMethod
	@AfterTest
	public void flush() {
		System.out.println("Executes only once after all the TC's");
		extent.flush();
	}
	
	/*@AfterMethod
	public void afterMethod(){
		System.out.println("Executes after every test case");
	}*/
	

	@AfterSuite
	public void closeConnections() throws Exception{
		File htmlFile = null;
		try{
		Path result = null;

		String dir= System.getProperty("user.dir");
		
		htmlFile = new File(ExtentReportUtility.s);
		System.out.println("____ This is my original path :____ " + htmlFile);
		String test=htmlFile.toString();
		
		String finalpath;
		finalpath = new File(dir+"/JenkinsReport/TestReport.html").getPath();
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
		
	}catch(Exception e){
		Desktop.getDesktop().browse(htmlFile.toURI());
	}
	}
	
	/*@AfterSuite  // This is working original before moving the report to other location
	public void closeConnections() throws IOException {
		// stopServer();
		File htmlFile = new File(ExtentReportUtility.s);
		System.out.println("_______________This is my latest report_________________" + htmlFile);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}*/

}
