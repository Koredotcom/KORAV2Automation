package com.org.kore.web.pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;

public class CPCommonFunctions extends PageBase {
	public CPCommonFunctions(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
		// TODO Auto-generated constructor stub
	}

	public CPCommonFunctions(AppiumDriver appiumDriver) {
		super(appiumDriver);
		// TODO Auto-generated constructor stub
	}

	ElementRepository er = DriverSetUp.er;
	public static LinkedHashMap<Integer, Object> dateNewstSQMap;
	public static LinkedHashMap<String, String> IncidentMap;
	public static Connection conn = null;
	public static Statement sta;
	public static ResultSet rs;

	public void launchSite(String url) {
		if (DriverSetUp.propsMap.get("tool").equalsIgnoreCase("Appium")) {
			appiumDriver.get(url);
		} else if (DriverSetUp.propsMap.get("tool").equalsIgnoreCase("Selenium")) {
			remoteDriver.get(url);
			System.out.println("Launched");
		}

	}

	// Home //Customer
	public void getAllWindowsAndSwitchTo(String windowcontains) throws Exception {
		try {
			Thread.sleep(5000);
			String maincontent = null;
			// String currentWindow = remoteDriver.getWindowHandle();
			String mainttl = remoteDriver.getTitle();
			System.out.println("$Current Windowtitle is$ " + mainttl);
			Set<String> availableWindows = remoteDriver.getWindowHandles();
			if (!availableWindows.isEmpty()) {
				for (String windowId : availableWindows) {
					String switchedWindowTitle = remoteDriver.switchTo().window(windowId).getTitle();
					System.out.println(switchedWindowTitle);
					String[] actual = switchedWindowTitle.split(" ");
					maincontent = actual[0].trim();
					System.out.println("$ In loop content window is $ " + maincontent);
					if (maincontent.equalsIgnoreCase(windowcontains)) {
						System.out.println("Current URL of " + windowcontains + " is" + remoteDriver.getCurrentUrl());
						remoteDriver.switchTo().window(windowId);
						test.log(LogStatus.INFO, "Switched to new window successfully");
						System.out.println("$Switched window title is $ " + remoteDriver.getTitle());
						test.log(LogStatus.PASS, test.addScreenCapture(takeScreenShot()));
						break;
					} else {
						System.out.println("Do nothing");
					}
				}
				System.out.println("Win handles done");
				// remoteDriver.switchTo().window(maincontent);
			}
		} catch (Exception e) {
			System.out.println("Failed to window to :" + windowcontains);
		}
	}

	public void switchBackToParentWindow() throws Exception {
		// String currentWindow = remoteDriver.getWindowHandle();
		String mainttl = remoteDriver.getTitle();
		System.out.println("@@" + mainttl);
		Set<String> availableWindows = remoteDriver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				String switchedWindowTitle = remoteDriver.switchTo().window(windowId).getTitle();
				System.out.println("@@ Window title is @@ " + switchedWindowTitle);
				if (switchedWindowTitle.equalsIgnoreCase("Home | ServiceNow Developers")
						|| (mainttl.equalsIgnoreCase("Home | ServiceNow Developers"))) {
					remoteDriver.switchTo().window(windowId);
					test.log(LogStatus.INFO, " Switched back to main Window");
					test.log(LogStatus.PASS, test.addScreenCapture(takeScreenShot()));
					break;
				} else {
					System.out.println("Do nothing");
				}
			}
			System.out.println("Win handles done");
			// remoteDriver.switchTo().window(maincontent);
		} else {
			System.out.println("It has only one window");
		}
	}
	
	public String getFirstChar(String input) {
		char first = input.charAt(0);
		String myStr = Character.toString(first);
		return myStr;
	}
	
	public String[] convertStringstoArray(String actual){
	String str[] = actual.split(",");
	return str;
	}
	
	//Yet to implement based on the need
	public List<String> generateListFromStrings(String... input){
		 List<String> list = new ArrayList<String>();
		 list.add(input[1]);
		 list.add(input[2]);
		return list;
		
	}
}
