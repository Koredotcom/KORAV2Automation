package com.org.kore.testbase;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
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
			System.out.println("I am in Before class");
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
			System.out.println("End of Before class");
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}

	@AfterClass
	public void tearDown(ITestContext ctx) throws Exception {
		System.out.println("I am in After class");
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
		System.out.println("End of After class");

	}

	// @AfterMethod
	@AfterTest
	public void flush() {
		extent.flush();
	}

	@AfterSuite
	public void closeConnections() throws IOException {
		// stopServer();
		File htmlFile = new File(ExtentReportUtility.s);
		System.out.println("_______________This is my latest report_________________" + htmlFile);
		Desktop.getDesktop().browse(htmlFile.toURI());

	}

}
