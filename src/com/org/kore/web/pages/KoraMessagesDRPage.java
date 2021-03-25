package com.org.kore.web.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 * @Description : All the functions related to Messages discussion room page
 *
 */

public class KoraMessagesDRPage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;

	public KoraMessagesDRPage(RemoteWebDriver remoteWebDriver) {
		super(remoteWebDriver);
		cf = new CPCommonFunctions(remoteWebDriver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param workspaceName : User have to provide workspace name 
	 * @param discussionRoomName : User have to provide DR which associated with the above workspace
	 * @param check : If check is true, it will perform the next parameter action
	 * @param action : Specify the exact action to perform on a DR
	 * @throws Exception
	 */
	
	public void goToGroupAndPerforminWSDR(String workspaceName, String discussionRoomName, boolean check, String action)
			throws Exception {

		moveToElement(er.kdrcidgroup + discussionRoomName + "']", "xpath");
		click(er.kdrcidgroup + discussionRoomName + "']", discussionRoomName + " chat");
		try {
			if (check) {
				switch (action.trim()) {
				case "Star":
					System.out.println("In Star");
					Thread.sleep(1000);
					moveToElement(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//span[@class='icon __i right kr-starred']", "xpath");
					Thread.sleep(1000);
					click(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//span[@class='icon __i right kr-starred']", "Star");
					Thread.sleep(1000);
					test.log(LogStatus.PASS,
							discussionRoomName + " was Starred ".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "Unstar":
					System.out.println("In Unstar");
					Thread.sleep(1000);
					moveToElement(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//span[@class='icon __i right kr-starred-filled']", "xpath");
					Thread.sleep(1000);
					click(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//span[@class='icon __i right kr-starred-filled']", "Unstar");
					Thread.sleep(1000);
					test.log(LogStatus.PASS,
							discussionRoomName + " was Unstarred".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "Mute":
					moveToElement(
							er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-audio ']",
							"xpath");
					Thread.sleep(1000);
					click(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-audio ']",
							"Mute");
					test.log(LogStatus.PASS, discussionRoomName + " was displayed with mute slots".toString()
							+ test.addScreenCapture(takeScreenShot()));
					break;

				case "UnMute":
					moveToElement(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-mute']",
							"xpath");
					Thread.sleep(1000);
					click(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-mute']", "Mute");
					test.log(LogStatus.PASS, discussionRoomName + " was displayed with mute slots".toString()
							+ test.addScreenCapture(takeScreenShot()));
					break;

				case "Read":
					moveToElement(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@title='Read']", "xpath");
					Thread.sleep(1000);
					click(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-eye-open']",
							"Read");
					test.log(LogStatus.PASS, discussionRoomName + " has marked as read".toString()
							+ test.addScreenCapture(takeScreenShot()));
					break;

				case "Unread":
					System.out.println("In Unread");
					Thread.sleep(1000);
					moveToElement(
							er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-eyeLash']",
							"xpath");
					Thread.sleep(1000);
					click(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-eyeLash']",
							"Un-Read");
					moveToElement(er.klogo, "Work Assist Logo");
					click(er.klogo, "Work Assist Logo");
					Thread.sleep(2000);
					try {
						String unreadcount = getText(
								er.kdrcidgroup + discussionRoomName + "']/../../..//span[@class='unreadCount']");
						test.log(LogStatus.PASS,
								discussionRoomName + " has marked as Un-Read and the badge count displayed as <b>"
										+ unreadcount + "</b>".toString() + test.addScreenCapture(takeScreenShot()));
					} catch (Exception e) {
						test.log(LogStatus.FAIL,
								discussionRoomName + " Badge count was not displayed for unread chat".toString()
										+ test.addScreenCapture(takeScreenShot()));
					}
					break;
				case "3dots":
					moveToElement(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//div[@class='_content _contentent']/i[3]", "xpath");
					Thread.sleep(1000);
					click(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//div[@class='_content _contentent']/i[3]",
							"Clicking on 3dots in Worksape Discussion room");
					Thread.sleep(1000);
					break;
				default:
					test.log(LogStatus.FAIL,
							"Please provided valid on hover action i.e. , should be match with case value");
				}
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "For <b>" + discussionRoomName + "</b> Unable to click on <b>" + action
					+ "</b>... Seems element got updated ".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}
}
