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

	public void goToGroupAndPerforminWSDR(String discussionRoomName, boolean check, String action)
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

	/**
	 * @param worksapce :: Workspace name
	 * @param discRoom :: Discussion Room name
	 * @param posttext :: on which post user want to perform actions
	 * @param reaction :: reactions like ...like, dislike...., comment, 3dots
	 * @param comment :: Comment on post 
	 * @param threedots :: More options (i.e. edit, forward, reminder,post info, delete...
	 * @throws Exception
	 */	
	public void perfromreactionsonPost(String discRoom, String post, String reaction, boolean comment) throws Exception {
		try {

			moveToElement(er.kdrpostname0+discRoom+er.kdrpostname1+post+er.ksinglquote,"xpath");
			click(er.kdrpostname0+ discRoom + er.kdrpostname1+ post+ er.ksinglquote, "Click on post ");

			if(!comment)
			{
				switch (reaction.trim().toUpperCase()) {
				case "LIKE":
					System.out.println("performing like reaction");
					moveToElement(
							er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[1]",	"xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[1]",
							"Like");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, post + " post was  reacted with Liked ".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "DISLIKE":
					System.out.println("performing dislike reaction");
					moveToElement(
							er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[2]",	"xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[2]",
							"Dislike");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, post + " post was reacted with  disLiked ".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "HAPPY":
					System.out.println("performing HAPPY reaction");
					moveToElement(
							er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[3]",	"xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[3]",
							"Happy");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, post + " post reacted with  HAPPY ".toString() + test.addScreenCapture(takeScreenShot()));
					break;	

				case "SAD":
					System.out.println("performing SAD reaction ");
					moveToElement(
							er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/[4]",	"xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[4]",
							"SAd");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, post + " post was reacted with  SAD ".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "ANGRY":
					System.out.println("performing dislike reaction");
					moveToElement(
							er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[5]",	"xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[5]",
							"Angry");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, post + " post was reacted with ANGRY ".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "MORE":
					System.out.println("performing dislike reaction");
					moveToElement(
							er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[@class='icon __i kr-ellipsis']",	"xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[@class='icon __i kr-ellipsis']",
							"Comment on a post");								
					test.log(LogStatus.PASS, post + "Clicking on more options on post  ".toString()  + test.addScreenCapture(takeScreenShot()));


				default:
					test.log(LogStatus.FAIL,
							"Please provided valid on hover action i.e. , should be match with case value");
				}	
			}

			if(comment) {
				System.out.println("performing dislike reaction");
				moveToElement(
						er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[@class='icon __i kr-comment']",	"xpath");
				Thread.sleep(1000);
				click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[@class='icon __i kr-comment']",
						"Comment on a post");
				Thread.sleep(1000);
				enterText(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../../..//div[@id='discInput']", comment+"\n", "xpath", "Comment on post");
				test.log(LogStatus.PASS, post + " post was commented with  ".toString() + test.addScreenCapture(takeScreenShot()));

			}


		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to select the mentioned participant");

		}	
	}


	/**
	 * 
	 * @param discussionRoomName
	 * @param actionon on which item it need to be done
	 * @param action what is the action click or validate 
	 * @throws Exception
	 */
	public void verifytheoptionsonDRandperfromAction(String discussionRoomName, String actionon, String action)
			throws Exception {

		try {
			//			if (check) {
			switch (actionon.trim().toUpperCase()) {
			case "STAR":
				System.out.println("In Star");
				Thread.sleep(1000);
				moveToElement(er.kdrcidgroup + discussionRoomName
						+ "']/../../../..//span[@class='icon __i right kr-starred']", "xpath");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, discussionRoomName + " was displayed with STAR ".toString()
						+ test.addScreenCapture(takeScreenShot()));
				if(action.equalsIgnoreCase("select"))
				{
					click(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//span[@class='icon __i right kr-starred']", "Star");
					Thread.sleep(1000);
					test.log(LogStatus.PASS,
							discussionRoomName + " was Starred ".toString() + test.addScreenCapture(takeScreenShot()));
				}
				break;

			case "UNSTAR":
				System.out.println("In Unstar");
				Thread.sleep(1000);
				moveToElement(er.kdrcidgroup + discussionRoomName
						+ "']/../../../..//span[@class='icon __i right kr-starred-filled']", "xpath");
				Thread.sleep(1000);
				test.log(LogStatus.PASS,
						discussionRoomName + " was Unstarred" + test.addScreenCapture(takeScreenShot()));
				if(action.equalsIgnoreCase("select"))
				{
					click(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//span[@class='icon __i right kr-starred-filled']", "Unstar");
					Thread.sleep(1000);
					test.log(LogStatus.PASS,
							discussionRoomName + " was Unstarred".toString() + test.addScreenCapture(takeScreenShot()));
				}
				break;
				//i[contains(@class,'icon __i kr-audio')]

				
			case "MUTE":
				System.out.println("In MUTE");
				moveToElement(
						er.kdrcidgroup + discussionRoomName + "']/../../../..//i[contains(@class,'icon __i kr-audio')]",
						"xpath");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, discussionRoomName + " was displayed with mute slots".toString()
						+ test.addScreenCapture(takeScreenShot()));
				if(action.equalsIgnoreCase("select"))
				{
					click(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[contains(@class,'icon __i kr-audio')]",
							"Mute");
					test.log(LogStatus.PASS, discussionRoomName + " was displayed with mute slots".toString()
							+ test.addScreenCapture(takeScreenShot()));
				}
				break;

			case "UNMUTE":
				System.out.println("In UNMUTE");
				
				moveToElement(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-mute']",
						"xpath");
				Thread.sleep(1000);
				if(action.equalsIgnoreCase("select"))
				{
					click(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-mute']", "Mute");
					test.log(LogStatus.PASS, discussionRoomName + " was displayed with mute slots".toString()
							+ test.addScreenCapture(takeScreenShot()));
				}
				break;

			case "READ":
				moveToElement(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@title='Read']", "xpath");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, discussionRoomName + " was displayed with READ ".toString()
						+ test.addScreenCapture(takeScreenShot()));
				if(action.equalsIgnoreCase("select"))
				{
					click(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-eye-open']",
							"Read");
					test.log(LogStatus.PASS, discussionRoomName + " has marked as read".toString()
							+ test.addScreenCapture(takeScreenShot()));
				}
				break;

			case "UNREAD":
				System.out.println("In Unread");
				Thread.sleep(1000);
				moveToElement(
						er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-eyeLash']",
						"xpath");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, discussionRoomName + " was displayed with UNREAD ".toString()
						+ test.addScreenCapture(takeScreenShot()));
				if(action.equalsIgnoreCase("select"))
				{
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
				}
				break;				
			default:
				test.log(LogStatus.FAIL,
						"Please provided valid on hover action i.e. , should be match with case value");
			}
			//			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "For <b>" + discussionRoomName + "</b> Unable to click on <b>" + action
					+ "</b>... Seems element got updated ".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}

}
