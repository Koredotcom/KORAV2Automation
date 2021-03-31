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
	 * @Description : This login works with kore.com credentials
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void signInWith2FactorAuthentication(String userName, String password) throws Exception {
		// waitTillappear(er.kuser, "xpath", "Enter Email ID");
		waitTillappear(er.ko365, "xpath", "Choose ur account type");
		enterText(er.kuser, userName, "xpath", "Username");
		click(er.kloginnext, "UsrNext");
		click(er.ksignwithgoogle, "SigninWithGoogle");
		test.log(LogStatus.INFO, "Sign in to Google screen displayed", test.addScreenCapture(takeScreenShot()));
		click(er.knext, "selected Next");
		enterText(er.kpwd, password, "xpath", "Password");
		click(er.knext, "selected account");
		test.log(LogStatus.INFO, "Password entered successfully".toString() + test.addScreenCapture(takeScreenShot()));
		waitTillappear(er.kallow, "xpath", "Allow");
		moveToElement(er.kallow, "xpath");
		jsClick(er.kallow, "xpath");
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Kora User logged in successfully as : " + userName + " ".toString()
				+ test.addScreenCapture(takeScreenShot()));

	}

	/**
	 * @Description : This login works with O'365 account
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void signInWithO365(String userName, String password) throws Exception {
		waitTillappear(er.ko365, "xpath", "Choose ur account type");
		click(er.ko365, "Select Microsoft option");
		enterText(er.koenteremail, userName, "xpath", "Enter Email");
		click(er.kousernext, "UsrNext");
		enterText(er.kpwd, password, "xpath", "Password");
		test.log(LogStatus.INFO, "Password entered successfully");
		click(er.kosignin, "Signin");
		click(er.kstaysignin, "Stay Signin");
		test.log(LogStatus.PASS, "logged in successfully with O'365 account as : " + userName + " ".toString()
				+ test.addScreenCapture(takeScreenShot()));

	}

	/*public void logout() throws Exception {
		click(er.klogout, "Logout");
		click(er.klogoutyes, "Logout Confirmation Popup Yes");
		test.log(LogStatus.PASS, "Logged out successfully".toString() + test.addScreenCapture(takeScreenShot()));
		waitTillappear(er.ko365, "xpath", "Choose ur account type");
		click(er.ko365, "Select Microsoft option");

	}*/
	
	public void logoutAndReLogin(boolean relogin,String url, String userName,String password) throws Exception {		
		click(er.kuserprofileicon, "Click on User profile icon");
		click(er.klogout, "Logout");
		click(er.klogoutyes, "Logout Confirmation Popup Yes");
		test.log(LogStatus.PASS, "Logged out successfully".toString() + test.addScreenCapture(takeScreenShot()));
		waitTillappear(er.ko365, "xpath", "Choose ur account type");
		if(relogin){
			clearChromeCache();
			loginToKora(url,userName, password);	
		}
	}

	public void loginToKora(String url, String userName, String password) throws Exception {
		cf.launchSite(url);
		signInWithO365(userName, password);
	}
}
