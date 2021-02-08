package com.org.kore.mobile.pages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

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
		}
	}

	/**
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void signIn(String userName, String password) throws Exception {
		try {
			waitTillappear(er.kuser, "xpath", "Enter Email ID");
			enterText(er.kuser, userName, "xpath", "Username");
			click(er.kloginnext, "UsrNext");
			/*
			 * waitTillappear(er.ksignwithgoogle, "xpath", "Enter Password");
			 * click(er.ksignwithgoogle, "SigninWithGoogle");
			 * waitTillappear(er.kchoosetext, "xpath", "Enter Password"); String
			 * actualchoosetext= getText(er.kchoosetext);
			 * System.out.println("Text displayed as "+actualchoosetext);
			 * click(er.kselectaccount, "selected account");
			 * waitTillappear(er.kallow, "xpath", "Allow"); click(er.kallow,
			 * "Allow"); waitTillappear(er.kallow, "xpath", "Allow");
			 * Thread.sleep(15000);
			 */
			test.log(LogStatus.INFO, "Kora User logged in successfully as");
			test.log(LogStatus.PASS, "Home screen ", test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			test.log(LogStatus.FAIL, e);
		}

	}

	public void mobileSignIn() throws Exception {
		try {
			waitTillappear("//android.widget.TextView[contains(@text,'Email')]", "xpath", "Enter Email ID");
			appiumDriver.findElement(MobileBy.xpath("//android.widget.EditText"))
					.sendKeys("Jayakrishna.dandru@kore.com");
			test.log(LogStatus.INFO, "KORA V2 Username entered successfully on Android Emulator");
			test.log(LogStatus.PASS, "Home screen ", test.addScreenCapture(takeScreenShot()));
			List<MobileElement> Menulist = appiumDriver.findElements(
					By.xpath("//android.view.ViewGroup//android.widget.TextView[contains(@text,'Next')]"));
			System.out.println(" " + Menulist.size());
			for (MobileElement e : Menulist) {
				System.out.println(e.getText().trim());
				if (e.getText().trim().equalsIgnoreCase("Next")) {
					e.click();
					System.out.println("Next button clicked successfully");
					break;
				}

			}
			waitTillappear(
					"//android.widget.ImageView[contains(@resource-id,'com.google.android.gms:id/og_apd_internal_image_view')]",
					"xpath", "User field");
			test.log(LogStatus.INFO, "User identification screen displayed");
			test.log(LogStatus.PASS, "User identification screen ", test.addScreenCapture(takeScreenShot()));
			appiumDriver
					.findElement(By
							.xpath("//android.widget.ImageView[contains(@resource-id,'com.google.android.gms:id/og_apd_internal_image_view')]"))
					.click();
			waitTillappear("//android.view.ViewGroup//android.widget.Button//android.widget.TextView", "xpath",
					"User field");
			test.log(LogStatus.INFO, "Home page displayed");
			test.log(LogStatus.PASS, "Hme page ", test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Even after clicking on Next, user was not navigated to User identification screen");
			System.out.println(e.getMessage());
		}
	}

	// To Select any option from header options
	public void selectMenuOption(String menuoption) throws Exception {
		boolean flag = false;
		try {
			waitTillappear("//android.view.ViewGroup//android.widget.Button//android.widget.TextView", "xpath",
					"User field");
			List<MobileElement> Menulist = appiumDriver
					.findElements(By.xpath("//android.view.ViewGroup//android.widget.Button//android.widget.TextView"));
			System.out.println("" + Menulist.size());
			for (MobileElement e : Menulist) {
				System.out.println(e.getText().trim());
				if (e.getText().trim().equalsIgnoreCase(menuoption)) {
					flag = true;
					e.click();
					Thread.sleep(1000);
					System.out.println(menuoption + ":" + "clicked successfully");
					test.log(LogStatus.INFO, "Workspace selected");
					test.log(LogStatus.PASS, "Workspace screen", test.addScreenCapture(takeScreenShot()));
					break;
				}

			}
			if (!flag) {
				test.log(LogStatus.FAIL, menuoption + "  option not selected or it is not available in the options");
				test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
				System.out.println("Reached FailXXXXXXXX, Provided option is not available on the Dom");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Not clicked on menu option");
			test.log(LogStatus.FAIL, "Footer menu option element got changed or login was not success");
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			e.printStackTrace();
		}
	}

	public static String dateFormat(String originalDate) throws ParseException {

		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(originalDate);
		String newstr = new SimpleDateFormat("MM/dd/yyyy").format(date);

		return newstr;

	}

	public static List<String> dateConvert(String originalDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(originalDate);
		String newstr = new SimpleDateFormat("MM/dd/yyyy, hh:mm, aa").format(date);
		List<String> dateList = Arrays.asList(newstr.split(","));

		return dateList;

	}

}
