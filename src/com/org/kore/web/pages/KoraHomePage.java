package com.org.kore.web.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		boolean hmenu = false;
		waittillpageload();
		waitTillappear(er.klogo, "xpath", "Top left menu");
		System.out.println("Post login screen displayed");
		hmenu = remoteDriver.findElements(By.xpath("//span[@class='menuTabs']/a")).size() > 0;
		if (hmenu) {
			List<WebElement> Menulist = remoteDriver.findElements(By.xpath("//span[@class='menuTabs']/a"));
			for (WebElement e : Menulist) {
				if (e.getText().trim().equalsIgnoreCase(menuoption)) {
					flag = true;
					e.click();
					System.out.println(menuoption + " option got selected");
					Thread.sleep(1000);
					waitTillappear(er.klogo, "xpath", "Top left menu");
					test.log(LogStatus.PASS, menuoption + " selected");
					/*
					 * .toString() + test.addScreenCapture(takeScreenShot()));
					 */
					break;
				}
			}
			if (!flag) {
				System.out.println(menuoption + " option was not selected");
				test.log(LogStatus.FAIL,
						menuoption + "  option not selected or it is not available in the options".toString()
								+ test.addScreenCapture(takeScreenShot()));
				System.out.println(
						"Reached FailXXXXXXXX " + menuoption + " is not available on the Dom for top header menu");
			}
		} else {
			test.log(LogStatus.INFO, "There is no top header here");
		}
	}

	/**
	 * @param menuoption
	 *            To select left menu options by passing menu text
	 * @throws Exception
	 */

	public void selectTopLeftMenuOption(String menuoption) throws Exception {
		boolean flag = false;
		waitTillappear(er.kmctopleftmenu, "xpath", "Top header");
		WebElement ele= remoteDriver.findElement(By.xpath(er.kmctopleftmenu));
		List<WebElement> Menulist = remoteDriver.findElements(By.xpath(er.kmctopleftmenu));
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
		/*if (menuoption.equals("All Messages"))
			menuoption = "Search Messages";
		if (elementIsDisplayed("//*[contains (@placeholder, 'Search')]", "xpath")) {
			test.log(LogStatus.PASS, "Searchbox place holder contains Search");
	//	if (elementIsDisplayed("//*[contains (@placeholder, '" + menuoption + "')]", "xpath")) {
	//		test.log(LogStatus.PASS, "Searchbox place holder contains " + menuoption);
		} else {
			test.log(LogStatus.FAIL, "Searchbox place holder doesnot contains " + menuoption);
		}*/
		if (!flag) {
			System.out.println(menuoption + " option was not selected");
			test.log(LogStatus.FAIL,
					menuoption + "  option not selected or it is not available in the left nav options".toString()
							+ test.addScreenCapture(takeScreenShot()));
			System.out.println("Reached FailXXXXXXXX " + menuoption + " is not available on the Dom for left nav");
		}
	}
	
	/**
	 * @param menuoption
	 *            To select bottom left menu workspace
	 */

	public void selectBottomLeftMenuWorkSpace(String leftmenuworkspace) throws Exception {
		boolean flag = false;
		waitTillappear(er.kwfilterbyws, "xpath", "Left bottom header");
		List<WebElement> Menulist = remoteDriver.findElements(By.xpath(er.kmbottonleftmenu));
		for (WebElement e : Menulist) {
			if (e.getText().trim().equalsIgnoreCase(leftmenuworkspace)) {
				flag = true;
				e.click();
				System.out.println(leftmenuworkspace + " Workspace got selected");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, "Selected <b>" + leftmenuworkspace + " </b>option from left menu".toString()
						+ test.addScreenCapture(takeScreenShot()));
				break;
			}
		}
		if (!flag) {
			System.out.println(leftmenuworkspace + " Workspace was not selected");
			test.log(LogStatus.FAIL,
					leftmenuworkspace + "  Workspace not selected or it is not available from the workspaces list".toString()
							+ test.addScreenCapture(takeScreenShot()));
			System.out
					.println("Reached FailXXXXXXXX " + leftmenuworkspace + " workspace is not available on the Dom for top header menu");
		}
	}
		

	public void getActiveOptionFromLeftNav(String menuoption) throws Exception {
		try {
			String selectedoption = getText(er.kleftactiveoption);
			test.log(LogStatus.WARNING, "Current active left nav is <b>" + selectedoption + "</b>".toString()
					+ test.addScreenCapture(takeScreenShot()));
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
	 * @param imagewithtext
	 *            : if it is true, attachment will go with a message, else only
	 *            attachment will be sent alone
	 * @param message
	 *            : THis message will be used for reporting purpose as well as
	 *            for message with attachment
	 * @throws Exception
	 */
	public void uploadfilesfromAttachment(String autoitfilepath, boolean imagewithtext, String message)
			throws Exception {
		boolean loading = false;
		int counter = 0;
		try {
			click(er.kattachment, "Attachment");
			Thread.sleep(2000);
			System.out.println("About to run auto it to upload : " + message);
			Runtime.getRuntime().exec(autoitfilepath);
			Thread.sleep(3000);
			do {
				loading = remoteDriver.findElements(By.xpath("//div[@class='small-Loader loading-screen']")).size() > 0;
				if (loading) {
					Thread.sleep(1000);
					counter++;
				}
			} while ((loading) || (counter > 30));
			Thread.sleep(1000);
			moveToElement(er.kcomposebar, "xpath");
			WebElement compose = remoteDriver.findElement(By.xpath(er.kcomposebar));
			if (imagewithtext)
				compose.sendKeys(message, Keys.ENTER);
			compose.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			if (counter >= 30)
				test.log(LogStatus.FAIL,
						"Unable to uploaded <b>" + message
								+ "</b> or it is taking more than a minute to upload".toString()
								+ test.addScreenCapture(takeScreenShot()));
			test.log(LogStatus.WARNING,
					"Below screenshot requires human eye to check user interface hence TC marked as Warning");
			test.log(LogStatus.WARNING, "Uploaded <b>" + message + "</b> successfully".toString()
					+ test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Failed to upload " + message
							+ ". It is either because of attachment element or Auto IT ".toString()
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
			test.log(LogStatus.PASS, "Clicked on " + option+" Popup".toString(), test.addScreenCapture(takeScreenShot()));

	}
	
	public void refreshpage() throws Exception {
		try {
		remoteDriver.navigate().refresh();		
		JavascriptExecutor js = (JavascriptExecutor) remoteDriver;
		String result = js.executeScript("return document.readyState").toString();
		int waitincreamental=1;
		doloop: do {
			Thread.sleep(6000);
			waitincreamental++;
			if(result.equals("complete"))
				break doloop;

		}while(waitincreamental <10||!result.equals("complete"));
		}catch(Exception e)
		{
			System.out.println("---------- Refresh Page -----");
			e.printStackTrace();
		}
	}
  
  public String runtimehhmmss()
	{
		String TimeinHHMMSS = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
		TimeinHHMMSS= formatter.format(date).replaceAll(":", "").substring(11).trim();
		return TimeinHHMMSS;
	}
  
  public void waittillpageload()
	{
		JavascriptExecutor js = (JavascriptExecutor) remoteDriver;
		String result = js.executeScript("return document.readyState").toString();
		int waitincreamental=1;
		doloop: do {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			waitincreamental++;
			if(result.equals("complete"))
				break doloop;
		}while(waitincreamental <15||!result.equals("complete"));
	}

}
