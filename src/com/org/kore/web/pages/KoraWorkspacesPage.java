package com.org.kore.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 * @Description : All the functions related to Workspaces page
 *
 */

public class KoraWorkspacesPage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;
	SoftAssert softAssertion = new SoftAssert();

	public KoraWorkspacesPage(RemoteWebDriver remoteWebDriver) {
		super(remoteWebDriver);
		cf = new CPCommonFunctions(remoteWebDriver);
		// TODO Auto-generated constructor stub
	}


	public void createNewWorkspaceAndCheckDefault(String createas) throws Exception {
		String workspacename = null;
		try {
			click(er.kwplusicon, "Plus icon in workspace ");
			click(er.kwcreatenew, "Create new workspace ");
			click(er.kworkplaceholder, "Placeholder Workspace or board");
			workspacename = getText(er.kwdefaulworkspace);
			if (workspacename.contains("Workspace")) {
				test.log(LogStatus.PASS, "Default workspace created as " + workspacename + " ".toString()
						+ test.addScreenCapture(takeScreenShot()));
			} else {

				test.log(LogStatus.FAIL, "Default workspace is not as expected i.e. " + workspacename + " ".toString()
						+ test.addScreenCapture(takeScreenShot()));
			}

			clearAndenterText(er.kwdefaulworkspace, createas, " Default workspace name");
			click(er.kworkplaceholder, "Placeholder Workspace or board");
			String updatedworkspace = getText(er.kwdefaulworkspace);
			compareActualExpected(updatedworkspace, createas, "Workspace is : ");
			boolean displayed = elementIsDisplayed(er.kwdrheader, "xpath");
			compareActualExpected(displayed, true, "Discussion Room header");
			displayed = elementIsDisplayed(er.kwdrgeneral, "xpath");
			compareActualExpected(displayed, true, "General Discussion Room");

		} catch (Exception e) {

			test.log(LogStatus.FAIL, "Filed in creation or verification of workspaces".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}
	}
	
	public String createNewWorkspaceAs(String createas) throws Exception {
		String updatedws=null;
			click(er.kwplusicon, "Plus icon in workspace ");
			click(er.kwcreatenew, "Create new workspace ");
			clearAndenterText(er.kwdefaulworkspace, createas, " New Workspace");
			click(er.kworkplaceholder, "Placeholder Workspace or board");
			updatedws = getText(er.kwdefaulworkspace);
			return updatedws;
	}
	
	public void selectDefaultDR() throws Exception {
		click(er.kwdrgeneral, "Default DR i.e. General DR ");
		test.log(LogStatus.PASS,
				"Selected default discussion room (General) from workspaces");
	}
	
	// for now not using
	public void selectWorkspace(String workspacetobeselected) throws Exception {
			boolean flag = false;
			List<WebElement> Menulist = remoteDriver.findElements(By.xpath(er.kworkspaces));
			for (WebElement e : Menulist) {
				if (e.getText().trim().equalsIgnoreCase(workspacetobeselected)) {
					flag = true;
					e.click();
					System.out.println(workspacetobeselected + " option got selected");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, workspacetobeselected + " ws got selected".toString() + test.addScreenCapture(takeScreenShot()));
					break;
				}
			}
			if (!flag) {
				System.out.println(workspacetobeselected + " ws was not selected");
				test.log(LogStatus.FAIL,
						workspacetobeselected + "  ws is not selected or it is not available in the list".toString()
								+ test.addScreenCapture(takeScreenShot()));
				System.out.println("Reached FailXXXXXXXX "+workspacetobeselected+" is not available on the Dom for top header menu");
			}
		}
		
	
	public void clickOnWorkspace3Dots(String workspacename) throws Exception {
		try{
		moveToElement(er.kworkspacename + workspacename + er.ksinglquote, "xpath");
		click(er.kworkspacename + workspacename + er.ksinglquote, workspacename + " Workspace ");
		click(er.kworkspacename + workspacename + er.ksinglquote+er.kwleft3dots,
							"3 dots");
		test.log(LogStatus.PASS,
				workspacename + " --> Clicked on WS 3 dots".toString() + test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "For <b>" + workspacename + ", Unable to click on 3dots ".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}
	
	
	public void operationsFromWS3Dots(String workspacename, String operation) throws Exception {
		try {
			switch (operation.trim()) {
			case "Delete":
				System.out.println("In ws delete");
				click(er.kwsleft3dotoptions + operation + er.ksinglquote,
						"Delete");
				Thread.sleep(1000);
				click(er.kwyesDeletepopup, "Yes Delete Workspace popup");
				String act =getAttributeValue(er.kwdeletewsname, "placeholder");
				compareActualExpected(act, "Workspace Name", "Placeholder for delete textbox is : ");
				clearAndenterText(er.kwdeletewsname, workspacename, "Placeholder to proceed with deletion");
				click(er.kwproceedDelete, "Proceed to Delete Workspace popup");
				test.log(LogStatus.PASS,
						workspacename+" Deleted Successfully".toString() + test.addScreenCapture(takeScreenShot()));
				break;

			default:
				test.log(LogStatus.FAIL,
						"Please provided valid on hover action i.e. , should be match with case value");
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unale to select " + operation
					+ " from 3 dots .. Seems element got updated".toString() + test.addScreenCapture(takeScreenShot()));
		}
	}
	
	/**
	 * 
	 * @param actual : Actual String to compare with expected
	 * @param expected : Expected String to compare with actual
	 * @throws Exception
	 */
	public void compareActualExpected(String actual, String expected, String message) throws Exception{
		if (actual.equalsIgnoreCase(expected)){
		test.log(LogStatus.PASS, "Actual "+message+actual+", Expected "+message+expected+" <b> both are same as expected<b>".toString()
				+ test.addScreenCapture(takeScreenShot()));
		}else {
			test.log(LogStatus.FAIL, "Actual "+message+actual+", Expected "+message+expected+" <b> both are not different <b>".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}
		
	}
	
	/**
	 * 
	 * @param actual : Actual boolean to compare with expected
	 * @param expected : Expected boolean to compare with actual
	 * @throws Exception
	 */
	public void compareActualExpected(boolean actual, boolean expected, String message) throws Exception{
		if (actual==expected){
		test.log(LogStatus.PASS, "Expected "+message+actual+" --> "+message+expected+"".toString()
				+ test.addScreenCapture(takeScreenShot()));
		}else {
			test.log(LogStatus.FAIL,  message+actual+" --> "+message+expected+"".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}
		
	}
	
}
