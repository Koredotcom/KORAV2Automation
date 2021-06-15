package com.org.kore.web.pages;

import java.util.TreeMap;

import org.openqa.selenium.By;
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
		try{
			System.out.println("Waiting for choose ur account screen");
		waitTillappear(er.ko365, "xpath", "Choose ur account type");
		test.log(LogStatus.PASS,
				"Type of accounts displayed to choose: ".toString() + test.addScreenCapture(takeScreenShot()));
		click(er.ko365, "Select Microsoft option");
		System.out.println("Choosen microsoft account");
		Thread.sleep(2000);
		System.out.println("Waiting for Signin screen or pick ur account screen");
		waitTillClickable("//input[@type='submit'] | //div[@role='heading'][text() = 'Pick an account']", "signin");
		
		boolean pickaccount=false;
		pickaccount= remoteDriver.findElements(By.xpath("//div[@role='heading'][text() = 'Pick an account']")).size()>0;
		if (pickaccount){
			click("//div[@id='otherTileText'][text()='Use another account']", "Use another account");
		}
		waitTillClickable(er.kosignin,"Enter Email or Pick an account");
		System.out.println("Waiting for enter email");
		waitTillappear(er.koenteremail, "xpath", "Enter Email");   //////div[@role='heading'][text() = 'Pick an account'] 
		enterText(er.koenteremail, userName, "xpath", "Enter Email");
		test.log(LogStatus.PASS, "Entered username".toString() + test.addScreenCapture(takeScreenShot()));
		click(er.kousernext, "UsrNext");
		waitTillappear(er.kpwd, "xpath", "Enter Email");
		waitTillappear(er.kosignin, "xpath", "Signin in Password");
		Thread.sleep(5000);
		enterText(er.kpwd, password, "xpath", "Password");
		test.log(LogStatus.PASS, "Entered Password".toString() + test.addScreenCapture(takeScreenShot()));
		click(er.kosignin, "Signin");
		test.log(LogStatus.PASS, "Selected Sign In".toString() + test.addScreenCapture(takeScreenShot()));
		
		/*if(!pickaccount){
		waitTillappear(er.kstaysignin, "xpath", "Stay Signin");
		click(er.kstaysignin, "Stay Signin");
		}*/
		Thread.sleep(15000);
		
		
		boolean staysignin=false;
		staysignin= remoteDriver.findElements(By.xpath(er.kstaysignin)).size()>0;
		if (staysignin){
			click(er.kstaysignin, "Stay Signin");
		}
		
		System.out.println("Sign in success with " + userName + " and waiting for home screen");
		System.out.println("Waiting for loading to disappear");
		waitUntilDissapear("//div[@class='lds-ring']", "Home Loading");
		System.out.println("Loading indicator got disappeared from the screen");
		test.log(LogStatus.PASS, "logged in successfully with O'365 account as : " + userName + " ".toString()
				+ test.addScreenCapture(takeScreenShot()));
		System.out.println("waiting for logo");
		waitTillappear(er.klogo, "xpath", "Top left menu");
		System.out.println("Again waiting for loading to disappear");
		waitUntilDissapear("//div[@class='lds-ring']", "Home Loading");
		waitTillClickable(er.klogo, "logo");
		test.log(LogStatus.PASS,
				"After waiting for logo, post login state:".toString() + test.addScreenCapture(takeScreenShot()));
	}catch (Exception e){
		
		System.out.println("Issue with Login in terms of synchronization or Logout and Relogin flow");
		
		}
	}

	public void logoutAndReLogin(boolean relogin, String url, String userName, String password) throws Exception {
		try {
			boolean loadingflag=false;
			loadingflag=remoteDriver.findElements(By.xpath("//div[@class='lds-ring']")).size()>0;
			if(loadingflag)
			waitUntilDissapear("//div[@class='lds-ring']", "Home Loading");
			
			waitAndContinue(er.klogo, "xpath", "Top left menu");
			clickNIgnoreFail(er.kuserprofileicon, "Click on User profile icon");
			clickNIgnoreFail(er.klogout, "Logout");
			clickNIgnoreFail(er.klogoutyes, "Logout Confirmation Popup Yes");
			test.log(LogStatus.INFO, "Logged out successfully".toString() + test.addScreenCapture(takeScreenShot()));
			waitTillappear(er.ko365, "xpath", "Choose ur account type");
			if (relogin) {
			//	clearChromeCache();
				launchAndLogoutFrom0365("https://www.office.com/");
				loginToKora(url, userName, password);
			}
		} catch (Exception e) {
		//	clearChromeCache();
			launchAndLogoutFrom0365("https://www.office.com/");
			loginToKora(url, userName, password);
		}
	}
	
	public void launchAndLogoutFrom0365(String o365url) throws Exception{
		o365url="https://www.office.com/";
		remoteDriver.get(o365url);	
		boolean logout=false;
		logout=remoteDriver.findElements(By.xpath("//div[@id='O365_MainLink_MePhoto'] | //div[@class='mectrl_topHeader']")).size()>0;
		test.log(LogStatus.INFO, "Office 365 account loaded ".toString() + test.addScreenCapture(takeScreenShot()));
		System.out.println("https://www.office.com/ loaded successfully");
		waitToappearIgnoreFail("//div[@id='O365_MainLink_MePhoto'] | //div[@class='mectrl_topHeader']", "xpath", "profile");
		Thread.sleep(2000);
		boolean extraheader =false;
		extraheader=remoteDriver.findElements(By.xpath("//div[@class='mectrl_topHeader']")).size()>0;
		if (extraheader){
			System.out.println("Had extra header for O 365 logout");
			click("//div[@class='mectrl_topHeader']", "extra header profile");
			waitToappear("//div[@id='O365_MainLink_MePhoto'] | //div[@class='mectrl_topHeader']", "xpath", "profile");
		}
		
		click("//div[@id='O365_MainLink_MePhoto']", "profile");
		Thread.sleep(3000);
		// need to check this
		waitToappearIgnoreFail("//a[@id='mectrl_body_signOut'] | //a[@id='meControlSignoutLink']", "xpath", "wait for signout");
		Thread.sleep(1000);
		clickNIgnoreFail("//a[@id='mectrl_body_signOut'] | //a[@id='meControlSignoutLink']", "click signout");
		Thread.sleep(5000);
		test.log(LogStatus.INFO, "Logged out from office 365".toString() + test.addScreenCapture(takeScreenShot()));
		waitToappearIgnoreFail("//div[@id='switch-account'] | //div[@class='personalization__buttons-container']", "xpath", "logout");
	}

	public String getUserDetails() throws Exception {
		click(er.kuserprofileicon, "Click on User profile icon");
		String username = getText(er.kuserProfileUserName).trim();
		return username;
	}

	public void launchw3(String url, String userName, String password) throws Exception {
		cf.launchOtherSite(url);
	}

	public void loginToKora(String url, String userName, String password) throws Exception {
		cf.launchSite(url);
		signInWithO365(userName, password);
	}
}
