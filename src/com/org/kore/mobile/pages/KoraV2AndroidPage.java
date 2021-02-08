package com.org.kore.mobile.pages;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;

/**
 * 
 * @author Jay
 * @Description : All the functions Android
 *
 */

public class KoraV2AndroidPage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;

	public KoraV2AndroidPage(AppiumDriver appiumDriver) {
		super(appiumDriver);
		cf = new CPCommonFunctions(appiumDriver);
		// TODO Auto-generated constructor stub
	}

	public void loginToKoraandroid() throws Exception {
		try {
			cf.mobileSignIn();
			// cf.selectMenuOption("Workspaces");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			test.log(LogStatus.FAIL, e);

		}

	}

	public void footerMenuSelection(String menuoption) throws Exception {
		try {
			cf.selectMenuOption(menuoption);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			test.log(LogStatus.FAIL, e);

		}

	}
}
