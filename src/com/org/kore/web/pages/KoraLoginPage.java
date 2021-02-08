package com.org.kore.web.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 * @Description : All the functions related login
 *
 */

public class KoraLoginPage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;

	public KoraLoginPage(RemoteWebDriver remoteWebDriver) {
		super(remoteWebDriver);
		cf = new CPCommonFunctions(remoteWebDriver);
		// TODO Auto-generated constructor stub
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
			click(er.ksignwithgoogle, "SigninWithGoogle");
			test.log(LogStatus.INFO, "Sign in to Google screen displayed", test.addScreenCapture(takeScreenShot()));
			click(er.knext, "selected Next");
			enterText(er.kpwd, password, "xpath", "Username");
			click(er.knext, "selected account");
			test.log(LogStatus.INFO, "Password entered successfully", test.addScreenCapture(takeScreenShot()));
			waitTillappear(er.kallow, "xpath", "Allow");
			moveToElement(er.kallow, "xpath");
			jsClick(er.kallow, "xpath");
			test.log(LogStatus.INFO, "Kora User logged in successfully as : " + userName);
			test.log(LogStatus.PASS, "Home screen ", test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			test.log(LogStatus.FAIL, e);
		}

	}

	public void loginToKora(String url, String userName, String password) throws Exception {
		try {
			cf.launchSite(url);
			signIn(userName, password);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			test.log(LogStatus.FAIL, e);

		}

	}
}
