package com.org.kore.mobile.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.kore.mobile.pages.KoraV2AndroidPage;
import com.org.kore.testbase.DriverSetUp;

/**
 * 
 * @author Jay
 * @Description : All TC's related to Android
 *
 */

public class KoraV2AndroidTest extends DriverSetUp {

	KoraV2AndroidPage kandroidpage;

	String userName;
	String passWord;

	public KoraV2AndroidTest() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("I am in Before method");

		kandroidpage = new KoraV2AndroidPage(appiumDriver);

		System.out.println("End ofBefore method");

		userName = dr.getValue("KORA", "KoraV1", "Username");
		passWord = dr.getValue("KORA", "KoraV1", "Password");

	}

	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("In before test");
		System.out.println("Do nothing");
		System.out.println("End od before test");
	}

	@Test(priority = 1, enabled = true)
	public void koraV2Login() throws Exception {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
				.assignCategory("KORAV2AndroidApp");
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		kandroidpage.loginToKoraandroid();

		KoraV2AndroidPage.extent.endTest(test);

	}

	@Test(priority = 2, enabled = true)
	public void koraV2SelectMenuOptions() throws Exception {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
				.assignCategory("KORAV2AndroidApp");
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		kandroidpage.footerMenuSelection("Workspaces");

		KoraV2AndroidPage.extent.endTest(test);

	}

}
