package com.org.kore.web.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class KoraManageConversationPage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;
	KoraMessagesPage koramessagespage;

	public KoraManageConversationPage(RemoteWebDriver remoteWebDriver) {
		super(remoteWebDriver);
		cf = new CPCommonFunctions(remoteWebDriver);
		koramessagespage = new KoraMessagesPage(remoteWebDriver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Description : To check manage conversation screen w.r.t available
	 *              widgets on the screen
	 * @throws Exception
	 */
	public void manageConversationValidations() throws Exception {
		String manageconvttl = getText("//div[@class='dialog-title']");
		int size = getSize("//div[@class='addParticipantsCtr-Btn']");
		if (size > 0)
			test.log(LogStatus.FAIL, "Add Participants option displayed under General Tab");
		clickOn("Members", true);
		String addparticipants = getText("//div[@class='addParticipantsCtr-Btn']");
		if (!manageconvttl.equalsIgnoreCase("Manage Conversation")
				|| (!addparticipants.equalsIgnoreCase("Add Participants")))
			test.log(LogStatus.FAIL, "New conversation text displayed as " + manageconvttl);
		clickOn("General", true);
		test.log(LogStatus.INFO, "Manage Conversation title displayed as : <b>" + manageconvttl + "</b> ");
		test.log(LogStatus.INFO, "Add Participants text displayed as : <b>" + addparticipants + "</b> ");
	}

	/**
	 * 
	 * @param memberstoadd
	 *            : THese members will be added to group
	 * @param plusiconclick
	 *            : If true it will click on plus icon
	 * @throws Exception
	 */
	public void AddParticipants(String memberstoadd, boolean plusiconclick) throws Exception {
		try {
			clickOn("Members", false);
			clickOn("Add Participants", true);
			koramessagespage.startNewConversationWith(memberstoadd, false);
			clickOn("Done", false);
			validateRecentAddedParticipants(memberstoadd);
			click(er.kmcmanageclose, "Close button");

		} catch (Exception e) {
			click(er.kmcmanageclose, "Close button");
			test.log(LogStatus.FAIL, "Failed to add participants ".toString(), test.addScreenCapture(takeScreenShot()));
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

		click(er.ktext + option + "']", option + " tab");
		if (screenshot)
			test.log(LogStatus.PASS, "Clicked on " + option, test.addScreenCapture(takeScreenShot()));

	}

	/**
	 * 
	 * @param participant
	 *            : Will check this participant added to the group or not
	 * @throws Exception
	 */
	public void validateRecentAddedParticipants(String participant) throws Exception {
		boolean flag = false;
		Thread.sleep(1500);
		moveToElement(er.kmcmembername + "[text()='" + participant + "']", "xpath");
		try {
			List<WebElement> Menulist = remoteDriver.findElements(By.xpath("//div[@class='emailUi']"));
			for (WebElement e : Menulist) {
				if (e.getText().trim().equalsIgnoreCase(participant)) {
					flag = true;
					test.log(LogStatus.INFO, participant + " Added to the Group");
					test.log(LogStatus.PASS, test.addScreenCapture(takeScreenShot()));
					break;
				}
			}
			if (!flag) {
				test.log(LogStatus.FAIL, participant + " was not added to the group");
				test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Participant mail id element got changed");
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param renameto
	 *            : This will rename the group to the user provided name as a
	 *            parameter
	 * @return : Return the updated name from the application
	 * @throws Exception
	 */
	public String renameGroupAndClose(String renameto) throws Exception {
		String updatedname = null;
		try {
			click("//input[@placeholder='Group Name']", "xpath");
			enterText("//input[@placeholder='Group Name']", renameto, "xpath");
			updatedname = getAttributeValue("//input[@placeholder='Group Name']", "value");
			System.out.println(updatedname);
			if (renameto.equals(updatedname)) {
				test.log(LogStatus.PASS, "Group name Updated successfully as " + updatedname);
				test.log(LogStatus.PASS, test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL, "Failed to update the group name as expected from Manage Conversation screen");
				test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			}
			click(er.kmcmanageclose, "Close button");

		} catch (Exception e) {
			System.out.println(e);
		}
		return updatedname;

	}

	/*
	 * ToDo // Still need improvements for this validation
	 */
	public void compareParticipants(String first, String second) {
		try {
			Thread.sleep(4000);

			List<String> actual = cf.generateListFromStrings(first, second);
			System.out.println("Actual before sort : " + actual);
			Collections.sort(actual);
			System.out.println("Actual after sort : " + actual);
			List<String> expected = new ArrayList<String>();

			List<WebElement> Menulist = remoteDriver
					.findElements(By.xpath("//span[text()='Member']/../../..//div[@class='emailUi']"));
			for (WebElement e : Menulist) {
				expected.add(e.getText());
			}
			System.out.println("Expected before sort : " + expected);
			Collections.sort(expected);
			System.out.println("Expected after sort : " + expected);
			boolean isEqual = actual.equals(expected);
			System.out.println(isEqual);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * @Description : It will remove all the members from the group
	 * @throws Exception
	 */
	public void removeParticipantsAndClose() throws Exception {
		try {
			boolean memb = false;
			test.log(LogStatus.INFO, "Now will remove all the members from the group");
			clickOn("Members", true);
			do {
				memb = remoteDriver.findElements(By.xpath("//span[text()='Member']")).size() > 0;
				if (memb) {
					clickOn("Member", false);
					String name = getText(er.kmcmembername);
					clickOn("Remove", false);
					test.log(LogStatus.INFO, "Removed : " + name);
					Thread.sleep(1500);
					// clickOn("Members", false);
				}
			} while ((memb));
			test.log(LogStatus.PASS,
					"Removed all the members from the group".toString() + test.addScreenCapture(takeScreenShot()));
			click(er.kmcmanageclose, "Close");
		} catch (Exception e) {
			click(er.kmcmanageclose, "Close");
			test.log(LogStatus.FAIL,
					"Faled to Remove Members of the group".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}

}
