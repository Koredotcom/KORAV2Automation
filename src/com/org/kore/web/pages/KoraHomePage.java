package com.org.kore.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 * @Description : All the functions related Home
 *
 */

public class KoraHomePage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;

	public KoraHomePage(RemoteWebDriver remoteWebDriver) {
		super(remoteWebDriver);
		cf = new CPCommonFunctions(remoteWebDriver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param menuoption
	 *            To select menu option by passing header menu text (Home
	 *            ,Workspaces, Tasks, Messages, Meetings, Knowledge)
	 * @throws Exception
	 */

	public void selectMenuOption(String menuoption) throws Exception {
		boolean flag = false;
		waitTillappear(er.kheadermenu, "xpath", "Top header");
		System.out.println("Header menu displayed");
		Thread.sleep(5000);
		List<WebElement> Menulist = remoteDriver.findElements(By.xpath(er.kheadermenu));
		for (WebElement e : Menulist) {
			if (e.getText().trim().equalsIgnoreCase(menuoption)) {
				flag = true;
				e.click();
				System.out.println(menuoption + " option got selected");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, menuoption + " selected".toString() + test.addScreenCapture(takeScreenShot()));
				break;
			}
		}
		if (!flag) {
			System.out.println(menuoption + " option was not selected");
			test.log(LogStatus.FAIL,
					menuoption + "  option not selected or it is not available in the options".toString()
							+ test.addScreenCapture(takeScreenShot()));
			System.out
					.println("Reached FailXXXXXXXX " + menuoption + " is not available on the Dom for top header menu");
		}
	}

	/**
	 * @param menuoption
	 *            To select left menu options by passing menu text
	 * @throws Exception
	 */

	public void selectLeftMenuOption(String menuoption) throws Exception {
		boolean flag = false;
		waitTillappear(er.kheadermenu, "xpath", "Top header");
		List<WebElement> Menulist = remoteDriver.findElements(By.xpath(er.kmcleftmenu));
		for (WebElement e : Menulist) {
			if (e.getText().trim().equalsIgnoreCase(menuoption)) {
				flag = true;
				e.click();
				System.out.println(menuoption + " option got selected");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, "Selected <b>" + menuoption + " </b>option from left menu".toString()
						+ test.addScreenCapture(takeScreenShot()));
				break;
			}
		}
		if (menuoption.equals("All Messages"))
			menuoption = "Search Messages";
		if (elementIsDisplayed("//*[contains (@placeholder, '" + menuoption + "')]", "xpath")) {
			test.log(LogStatus.PASS, "Searchbox place holder contains " + menuoption);
		} else {
			test.log(LogStatus.FAIL, "Searchbox place holder doesnot contains " + menuoption);
		}
		if (!flag) {
			System.out.println(menuoption + " option was not selected");
			test.log(LogStatus.FAIL,
					menuoption + "  option not selected or it is not available in the left nav options".toString()
							+ test.addScreenCapture(takeScreenShot()));
			System.out.println("Reached FailXXXXXXXX " + menuoption + " is not available on the Dom for left nav");
		}
	}

	public void getActiveOptionFromLeftNav(String menuoption) throws Exception {
		try {
			String selectedoption = getText(er.kleftactiveoption);
			test.log(LogStatus.WARNING, "Current active left nav is <b>" + selectedoption + "</b>".toString()+
					test.addScreenCapture(takeScreenShot()));
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Messages left nav is not available in the current screen, check the flow and validate again to get default selected option"
							.toString() + test.addScreenCapture(takeScreenShot()));
		}
	}
	
	/**
	 * 
	 * @param autoitfilepath
	 *            : Provide the path of compiled code of auto it
	 * @param typeoffile
	 *            : It is just for reporting purpose
	 * @throws Exception
	 */
	public void fileUploadfrom(String autoitfilepath, boolean imagewithtext, String message) throws Exception {
		try {
			click(er.kattachment, "Attachment");
			Thread.sleep(3000);
			System.out.println("About to run auto it to upload : " + message);
			Runtime.getRuntime().exec(autoitfilepath);
			Thread.sleep(2000);
			waitUntilDissapear("//div[@class='small-Loader loading-screen']", "xpath", "Choose your account type");
			moveToElement(er.kcomposebar, "xpath");
			WebElement compose = remoteDriver.findElement(By.xpath(er.kcomposebar));
			if(imagewithtext)
			compose.sendKeys(message,Keys.ENTER);
			compose.sendKeys(Keys.ENTER);
			test.log(LogStatus.PASS,
					"Uploaded " + message + " successfully".toString() + test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Failed to upload " + message
							+ " file. It is either because of attachment element or Auto IT ".toString()
							+ test.addScreenCapture(takeScreenShot()));
		}
	}

	/**
	 * 
	 * @param option
	 *            : It will click on this button based on the user provided text
	 * @param screenshot
	 *            : If this parameter is true , it will capture screenshot
	 * @throws Exception
	 */
	public void clickOn(String option, boolean screenshot) throws Exception {

		click(er.ktext + option + "']", option + " option");
		if (screenshot)
			test.log(LogStatus.PASS, "Clicked on " + option, test.addScreenCapture(takeScreenShot()));

	}

}
