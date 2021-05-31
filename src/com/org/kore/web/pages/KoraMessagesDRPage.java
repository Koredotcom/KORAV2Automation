package com.org.kore.web.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Ramana
 * @Description : All the functions related to Messages discussion room page
 *
 */

public class KoraMessagesDRPage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;
	KoraMessagesChatsPage koramessagespage;
	KoraHomePage korahomepage;
	//	List<String> listofusersinatmention = new ArrayList<String>();
	//	listofusersinatmention=null;
	List<String> totalmemebersinRoom = new ArrayList<String>() ;
	public KoraMessagesDRPage(RemoteWebDriver remoteWebDriver) {
		super(remoteWebDriver);
		cf = new CPCommonFunctions(remoteWebDriver);
		koramessagespage = new KoraMessagesChatsPage(remoteWebDriver);
		korahomepage = new KoraHomePage(remoteWebDriver);

	}

	/**
	 * 
	 * @param workspaceName
	 *            : User have to provide workspace name
	 * @param discussionRoomName
	 *            : User have to provide DR which associated with the above
	 *            workspace
	 * @param check
	 *            : If check is true, it will perform the next parameter action
	 * @param action
	 *            : Specify the exact action to perform on a DR
	 * @throws Exception
	 */

	public void goToGroupAndPerforminWSDR(String discussionRoomName, boolean check, String action) throws Exception {

		System.out.println("----------------------- goToGroupAndPerforminWSDR --------------------");
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
							"Please provided valid on hover action i.e. , should be match with case value in METHOD goToGroupAndPerforminWSDR");
				}
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "For <b>" + discussionRoomName + "</b> Unable to click on <b>" + action
					+ "</b>... Seems element got updated ".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}

	public void validateDROnhoverOptions() {
		String validationelmenets[] = { "Star", "Mute", "Read" };
		for (String value : validationelmenets) {
			// koramessagedrpage.verifytheoptionsonDRandperfromAction("DoNotDeleteDRAuto",value,"SelectNOT");
		}
	}

	/**
	 * @param worksapce
	 *            :: Workspace name
	 * @param discRoom
	 *            :: Discussion Room name
	 * @param posttext
	 *            :: on which post user want to perform actions
	 * @param reaction
	 *            :: reactions like ...like, dislike...., comment, 3dots
	 * @param comment
	 *            :: Comment on post
	 * @param threedots
	 *            :: More options (i.e. edit, forward, reminder,post info,
	 *            delete...
	 * @throws Exception
	 */
	public void perfromreactionsonPost(String discRoom, String post, String reaction, boolean comment,String commenttext) throws Exception {
		try {			
			System.out.println("-----------method ::--perfromreactionsonPost-------------------------------");
			if (!comment) {
				moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote, "xpath");
				click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote, "Click on post ");
				Thread.sleep(3000);
				switch (reaction.trim().toUpperCase()) {
				case "LIKE":
					System.out.println("performing like reaction");
					moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/i[1]", "xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/i[1]", "Like");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, post + " post was  reacted with Liked ".toString()
							+ test.addScreenCapture(takeScreenShot()));
					break;

				case "DISLIKE":
					System.out.println("performing dislike reaction");
					moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/i[2]", "xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/i[2]", "Dislike");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, post + " post was reacted with  disLiked ".toString()
							+ test.addScreenCapture(takeScreenShot()));
					break;

				case "HAPPY":
					System.out.println("performing HAPPY reaction");
					moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/i[3]", "xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/i[3]", "Happy");
					Thread.sleep(1000);
					test.log(LogStatus.PASS,
							post + " post reacted with  HAPPY ".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "SAD":
					System.out.println("performing SAD reaction ");
					moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/[4]", "xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/i[4]", "SAd");
					Thread.sleep(1000);
					test.log(LogStatus.PASS,
							post + " post was reacted with  SAD ".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "ANGRY":
					System.out.println("performing Angry reaction");
					moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/i[5]", "xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//div[@class='reactionIcons']/i[5]", "Angry");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, post + " post was reacted with ANGRY ".toString()
							+ test.addScreenCapture(takeScreenShot()));
					break;

				case "MORE":
					System.out.println("performing More reaction");
					Thread.sleep(1000);
					moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//i[contains(@class,'icon __i kr-ellipsis')]", "xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../..//i[contains(@class,'icon __i kr-ellipsis')]", "Comment on a post");
					test.log(LogStatus.PASS, post + "Clicking on more options on post  ".toString()
							+ test.addScreenCapture(takeScreenShot()));

				default:
					//					test.log(LogStatus.WARNING,
					//							"Please provided valid on hover action i.e. , should be match with case value in METHOD perfromreactionsonPost");
				}
				Thread.sleep(2000);
			}

			if (comment) {
				System.out.println("performing Comments on POST " + post);
				moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote, "xpath");				
				Thread.sleep(3000);
				moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
						+ "/../..//i[@class='icon __i kr-comment']", "xpath");
				Thread.sleep(1000);
				click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
						+ "/../..//i[@class='icon __i kr-comment']", "Comment on a post");
				Thread.sleep(1000);
				if(!commenttext.contains(""))
				{
					enterText(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
							+ "/../../..//div[@id='discInput']", commenttext + "\n", "xpath", "Comment on post");
					test.log(LogStatus.PASS,
							post + " post was commented with  ".toString() + test.addScreenCapture(takeScreenShot()));
				}				
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to select the mentioned participant");

		}
	}

	/**
	 * 
	 * @param discussionRoomName
	 * @param actionon
	 *            on which item it need to be done
	 * @param action
	 *            what is the action click or validate
	 * @throws Exception
	 */
	public void verifytheoptionsonDRandperfromAction(String discussionRoomName, String actionon, String action)
			throws Exception {

		try {

			switch (actionon.trim().toUpperCase()) {
			case "STAR":
				System.out.println("In Star");
				Thread.sleep(1000);
				moveToElement(er.kdrcidgroup + discussionRoomName
						+ "']/../../../..//span[@class='icon __i right kr-starred']", "xpath");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, discussionRoomName + " was displayed with STAR ".toString()
						+ test.addScreenCapture(takeScreenShot()));
				if (action.equalsIgnoreCase("select")) {
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
				if (action.equalsIgnoreCase("select")) {
					click(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//span[@class='icon __i right kr-starred-filled']", "Unstar");
					Thread.sleep(1000);
					test.log(LogStatus.PASS,
							discussionRoomName + " was Unstarred".toString() + test.addScreenCapture(takeScreenShot()));
				}
				break;
				// i[contains(@class,'icon __i kr-audio')]

			case "MUTE":
				System.out.println("In MUTE");
				moveToElement(
						er.kdrcidgroup + discussionRoomName + "']/../../../..//i[contains(@class,'icon __i kr-audio')]",
						"xpath");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, discussionRoomName + " was displayed with mute slots".toString()
						+ test.addScreenCapture(takeScreenShot()));
				if (action.equalsIgnoreCase("select")) {
					click(er.kdrcidgroup + discussionRoomName
							+ "']/../../../..//i[contains(@class,'icon __i kr-audio')]", "Mute");
					test.log(LogStatus.PASS, discussionRoomName + " was displayed with mute slots".toString()
							+ test.addScreenCapture(takeScreenShot()));
				}
				break;

			case "UNMUTE":
				System.out.println("In UNMUTE");

				moveToElement(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-mute']",
						"xpath");
				Thread.sleep(1000);
				if (action.equalsIgnoreCase("select")) {
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
				if (action.equalsIgnoreCase("select")) {
					click(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-eye-open']",
							"Read");
					test.log(LogStatus.PASS, discussionRoomName + " has marked as read".toString()
							+ test.addScreenCapture(takeScreenShot()));
				}
				break;

			case "UNREAD":
				System.out.println("In Unread");
				Thread.sleep(1000);
				moveToElement(er.kdrcidgroup + discussionRoomName + "']/../../../..//i[@class='icon __i kr-eyeLash']",
						"xpath");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, discussionRoomName + " was displayed with UNREAD ".toString()
						+ test.addScreenCapture(takeScreenShot()));
				if (action.equalsIgnoreCase("select")) {
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
						"Please provided valid on hover action i.e. , should be match with case value in verifytheoptionsonDRandperfromAction");
			}
			// }
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "For <b>" + discussionRoomName + "</b> Unable to click on <b>" + action
					+ "</b>... Seems element got updated ".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}

	/**
	 * When user want to Create DR from messages either from All messages or
	 * Discussion room.
	 * 
	 * @param workspacename
	 * @param NewDRname
	 * @param participantlist
	 * @param AccessType
	 * @throws Exception
	 */

	public void createDRwithAccessTypefromMessages(String workspacename, String NewDRname, String participantlist,
			String AccessType) throws Exception {
		try {
			System.out.println("---------------------- createDRwithAccessTypefromMessages -------------");
			click(er.kmcplusicon, "Plus icon to start new Dsicussion Room");
			if (getAttributeValue(er.kdSearchboxinmsgnDR, "placeholder").equalsIgnoreCase("Search Messages"))
				click(er.kmdiscussion, "Create a Discussion Room");
			if (!workspacename.contains("NA")) {
				if (cf.elementIsDisplayed(er.kdselectworkspace, "xpath")) {
					click(er.kdselectworkspace, "Clicking on Select workspace ");
					click(er.kdtoggleicontoselectWS, "Clicking on  breadcrumb");
					click("//span[@class='hamMenuWSName' and text()='" + workspacename + "']",
							"Selecting Workspace  " + workspacename);
				}
			}
			click(er.kddiscussionTitle, "Clicking on Discussion Room title ");
			enterText(er.kddiscussionTitle, NewDRname, "Discussion Room Title as " + NewDRname);
			moveToElement(er.kmcenterparticipant, "xpath");
			click(er.kmcenterparticipant, "Enter participant name");
			System.out.println("participantlist-----" + participantlist);
			if (participantlist.contains(",")) {
				String result[] = participantlist.trim().split("\\s*,\\s*");
				for (String part : result) {
					System.out.println(part);
					koramessagespage.select(part);

				}
			} else {
				koramessagespage.select(participantlist);
			}
			Thread.sleep(2000);
			System.out.println("------------ Selecting Access Type -------");
			click(er.kdrsettings, "Clicking on Setting icon in Right Side panel While creating DR");
			Thread.sleep(2000);
			if (cf.elementIsDisplayed(er.kdrsettingallmembers, "xpath")) {
				test.log(LogStatus.PASS, " All memebrs link Avaiable at particular Worskspace in setting section".toString() + test.addScreenCapture(takeScreenShot()));				
			}			
			
			if (cf.elementIsDisplayed(er.kdeveryoneAtnoWorkspace, "xpath")) {
				test.log(LogStatus.INFO, " Everyone at No workspace option dispalyed");
			}
			// Validating whether by default participants are having Post only
			// Access by default
			if (cf.getText(er.kddefaultAccessto).trim().equals("Post Only")) {
				test.log(LogStatus.PASS, " By Default Discussion Room participants will have Post Only access");
			} else {
				test.log(LogStatus.FAIL,
						" By Default Discussion Room participants are having "
								+ cf.getText("//i[@class='icon kr-tick']/..//span[@class='Name']").trim()
								+ " Instead of Post Only access");
			}
			if (!AccessType.equals("Post Only")) {
				if (AccessType.equals("Full Access")) {
					click("//span[@class='Name' and text()='" + AccessType + "']",
							"Clicking on Access Type " + AccessType);
				} else if (AccessType.equals("Comment Only")) {
					click("//span[@class='Name' and text()='" + AccessType + "']",
							"Clicking on Access Type " + AccessType);
				} else {
					test.log(LogStatus.FAIL,
							" User must provide access Type while creating a DR other than Default value ");
				}
			}
			click(er.kdrsettings,
					"Clicking on Setting icon in Right Side panel After creating DR and Setting Access Type");
			System.out.println("----- Entering Data to Disucssion Room ---");
			moveToElement(er.kmcenterparticipant, "xpath");
			moveToElement(er.kcomposebar, "Moving to composebar");
			click(er.kcomposebar, "Clicking on Compose Bar");
			Thread.sleep(5000);
			koramessagespage.enterYourMessageAs("Newely Created Discusion Room " + NewDRname);
			Thread.sleep(5000);
			korahomepage.waittillpageload();

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to select the mentioned participant");

		}
	}

	/***
	 * @param discRoom
	 *            ::On Which discussion room we are doing operations
	 * @param post
	 *            :: post where comments or Reactions took place
	 * @param Whoreacted
	 *            :: User who reacted/ commented to post
	 * @param commenttext
	 *            :: commented text
	 * @throws Exception
	 */
	public void validatingreactionsandCommentsonPost(String discRoom, String post, String Whoreacted,
			String commenttext) throws Exception {
		try {

			String element = er.kdrpostname0 + discRoom + er.kdrpostname1;
			moveToElement(element + post + er.ksinglquote, "xpath");
			if (!commenttext.equals("") || !commenttext.equals(null)) {
				moveToElement(element + post + er.ksinglquote + "/../..//*[contains(@class,'icon kr-comment')]", "xpath");
				click(element + post + er.ksinglquote + "/../..//*[contains(@class,'icon kr-comment')]",
						"Click on post ");
				Thread.sleep(2000);
				String actualuserwhocommented = getText(
						element + commenttext + er.ksinglquote + "/../..//span[@class='name']");
				if (Whoreacted.equalsIgnoreCase(actualuserwhocommented)) {
					test.log(LogStatus.PASS, "Comment on post successfully applied with right User".toString()
							+ test.addScreenCapture(takeScreenShot()));
				} else {
					test.log(LogStatus.FAIL,
							"Comment on post NOT Applied".toString() + test.addScreenCapture(takeScreenShot()));
				}
			}
			if (!Whoreacted.equals("") || !Whoreacted.equals(null)) {
				moveToElement(element + post + er.ksinglquote + "/../..//div[@class='postReaction']/div[1]/i[1]", "xpath");
				click(element + post + er.ksinglquote + "/../..//div[@class='postReaction']/div[1]/i[1]",
						"Clicking on Ractions");
				Thread.sleep(2000);
				String actualuserwhoreacted = getText(element + post + er.ksinglquote
						+ "/../..//div[@class='postReaction']/div[3]/div[2]/div[1]/div[2]/span[1]");
				if (Whoreacted.equalsIgnoreCase(actualuserwhoreacted)) {
					test.log(LogStatus.PASS, "Reaction on post successfully applied with right User".toString()
							+ test.addScreenCapture(takeScreenShot()));
				} else {
					test.log(LogStatus.FAIL,
							"Reaction on post NOT Applied".toString() + test.addScreenCapture(takeScreenShot()));
				}
			}
			waitTillappear(er.kwclosecommentreadpopup, "xpath", "Close pop up");
			if(remoteDriver.findElement(By.xpath(er.kwclosecommentreadpopup)).isDisplayed())
			{
				click(er.kwclosecommentreadpopup, "Close pop up");
				System.out.println("-------------- Closed popup in validatingreactionsandCommentsonPost----------------");
			}		

		} catch (Exception e) {
			click(er.kwclosecommentreadpopup, "Close pop up");
			test.log(LogStatus.FAIL, "Failed to validate reactions and Comments on a Post");

		}
	}

	public void valdiatedeletedMsgorDR(String chatordiscroomname) throws InterruptedException, Exception {
		Thread.sleep(5000);
		try {
			System.out.println("------------ Moving To Element ----------");
			moveToElement(er.kmdmsgordiscroom + chatordiscroomname + er.ksinglquote, "xpath");
			click(er.kmdmsgordiscroom + chatordiscroomname + er.ksinglquote, "chatordiscroomname");
			test.log(LogStatus.FAIL,
					"Even after After Deleting/Starring/Muting/... Discussion room We are not able to see the Discussion Room in particular Section"
					.toString() + test.addScreenCapture(takeScreenShot()));
		} catch (Exception e) {
			test.log(LogStatus.PASS,
					"After Deleting/Starring/Muting/... Discussion room We are not able to see the Discussion Room "
					.toString() + test.addScreenCapture(takeScreenShot()));
		}
	}

	public void EditingPostinDiscussionRoom(String discRoom, String post, String editingPost, String dotOptions)
			throws Exception {
		try {
			System.out.println("------------Eneter data to post---------------");
			WebElement compose = remoteDriver.findElement(By.xpath(er.kcomposebar));
			compose.sendKeys(post, Keys.ENTER);
			Thread.sleep(4000);
			moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote, "xpath");
			click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote, "Click on post ");
			Thread.sleep(4000);
			moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
					+ "/../..//i[contains(@class,'icon __i kr-ellipsis')]", "xpath");
			click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
					+ "/../..//i[contains(@class,'icon __i kr-ellipsis')]", "Click on 3dits options to ");
			test.log(LogStatus.PASS,
					post + "Clicking on more options on post  ".toString() + test.addScreenCapture(takeScreenShot()));
			korahomepage.waittillpageload();
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", dotOptions, "post3dots");
			System.out.println("------------------- Edit a post ------------");
			click(er.kdeditpost, "Editing post");
			korahomepage.waittillpageload();
			enterText(er.kdeditpostcomposebar, editingPost, post + " post is Editing with " + editingPost);
			korahomepage.clickOn("Save", true);
			korahomepage.waittillpageload();
			moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + editingPost + er.ksinglquote, "xpath");
			click(er.kdrpostname0 + discRoom + er.kdrpostname1 + editingPost + er.ksinglquote, "Click on post ");
			test.log(LogStatus.PASS, post + " Post Edited Successfully  with ".toString() + editingPost
					+ test.addScreenCapture(takeScreenShot()));
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					post + " FAILED to Edit Post ".toString() + test.addScreenCapture(takeScreenShot()));
		}
	}

	/**
	 * @param membersingroup  :: Group memebrs 
	 * @param mentionemailaddress :: at mention email adresses
	 * @param mentionmessage :: message that we mention
	 * @param inpostorcomment :: if comment is there @email address already entered in comment
	 * @throws Exception
	 */
	public void atMentionValidationinDR(List<String> membersingroup, String mentionemailaddress, String mentionmessage, String inpostorcomment) throws Exception {

		List<String> listofusersinatmention = new ArrayList<String>();
		try {						
			if(inpostorcomment.contains("post"))
			{
				moveToElement(er.kwcomposebar, "xpath");
				Thread.sleep(2000);
				enterText(er.kwcomposebar, "@", "xpath", "Type your message");
				Thread.sleep(5000);
			}

			List<WebElement> atmentionusers = remoteDriver.findElements(By.xpath(er.kwatmentionuserslist));			
			if (atmentionusers.size() > 0) {
				for(int i=1;i<=atmentionusers.size();i++)
				{					
					String useremailaddressincompose= remoteDriver.findElement(By.xpath("//table[@class='mentionDialogBoxTable']/tbody/tr["+i+"]/td[2]/div/span[@class='mentionEmailId']")).getText().trim();
					if(!useremailaddressincompose.equalsIgnoreCase("everyone") ||!useremailaddressincompose.equals(""))
						listofusersinatmention.add(useremailaddressincompose);
				}
				listofusersinatmention.removeIf(item -> item.isEmpty());	
				listofusersinatmention.removeIf(item -> item.equalsIgnoreCase("everyone"));
				test.log(LogStatus.PASS, "in Compose Bar @ mention showing the particiapants in list ".toString()+listofusersinatmention
						+ test.addScreenCapture(takeScreenShot()));								
				if(mentionemailaddress.contains("@"))
				{
					String mentionedNAmeicompose=remoteDriver.findElement(By.xpath("//span[@class='mentionEmailId' and text()='"+mentionemailaddress+"']/../span[1]")).getText().toLowerCase().trim();
					remoteDriver.findElement(By.xpath("//span[@class='mentionEmailId' and text()='"+mentionemailaddress+"']")).click();
					Thread.sleep(3000);						
					String arr[]=mentionedNAmeicompose.split(" ");
					mentionedNAmeicompose="";
					for(String x : arr)
					{
						mentionedNAmeicompose+=x;
					}
					if(inpostorcomment.contains("post"))
					{
						enterText(er.kwcomposebar, "@"+mentionedNAmeicompose+" "+mentionmessage+"\n", "xpath","enter  message "+mentionmessage);
					}
					Thread.sleep(3000);	
					enterText("//div[@id='discInput' and @placeholder='Type your comment']", "@"+mentionedNAmeicompose+" "+mentionmessage+"\n", "xpath","enter  message "+mentionmessage);
					Thread.sleep(3000);								
				}else {
					enterText(er.kwcomposebar, "", "xpath", "enter Empty message");
				}
				// Users from 				
				Collections.sort(listofusersinatmention);
				// Users from Manage Group
				Collections.sort(membersingroup);

				boolean boolval = membersingroup.equals(listofusersinatmention);
				listofusersinatmention=null;
				if(boolval)
				{
					test.log(LogStatus.PASS, "@ mention showing the particiapants in list ".toString()
							+ test.addScreenCapture(takeScreenShot()));	
				}else
				{
					test.log(LogStatus.FAIL, "in @menton functinality  Group Memembers are ".toString()+membersingroup+" and in @mention compose bar "
							+ test.addScreenCapture(takeScreenShot()));
				}


			} else {
				test.log(LogStatus.FAIL,
						" @ Mention not Showing any Participants ".toString() + test.addScreenCapture(takeScreenShot()));
				enterText(er.kwcomposebar, "", "xpath", "enter Empty message");
			}
		}catch(Exception e){
			enterText(er.kwcomposebar, "", "xpath", "enter Empty message");
			e.printStackTrace();
			test.log(LogStatus.FAIL,
					" FAILING in atMentionValidationinDR ".toString() + test.addScreenCapture(takeScreenShot()));
		}		
	}

	public void movetoaPostandClickon3dots(String discRoom, String posttoforward, boolean threedots) throws Exception {
		moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + posttoforward + er.ksinglquote, "xpath");
		click(er.kdrpostname0 + discRoom + er.kdrpostname1 + posttoforward + er.ksinglquote, "Click on post ");
		Thread.sleep(3000);
		if (threedots) {
			click(er.kdrpostname0 + discRoom + er.kdrpostname1 + posttoforward + er.ksinglquote
					+ "/../..//i[contains(@class,'icon __i kr-ellipsis')]", "Click on 3dits options to ");
			Thread.sleep(3000);
		}
	}

	/**
	 * @param newconversation
	 *            if it is new conversation we have to provide members list else
	 *            Discussion Room
	 * @param discussionRoom
	 *            Discussion room or Group Conversation
	 * @param Searchwith
	 *            search with either name,groupname or Discussion room name
	 * @throws Exception 
	 */
	public void forwardPosttonewconvorexisting(String post, String newconversation, String discRoomorConversationName,
			String Searchwith) throws Exception {
		try {
			click(er.kdforwardpost, "selecting forwarding  post");
			waitTillappear(er.kdfowradpostWindow, "xpath", "Forward post window");
			/** For New Conversation */
			if (!newconversation.equalsIgnoreCase("NA")) {
				System.out.println("------------------ NEW Conversation -------------------------");
				click(er.kdstartnewconversation, " Click on Start New conversation");
				if (newconversation.contains(",")) {
					String result[] = newconversation.trim().split("\\s*,\\s*");
					for (String part : result) {
						System.out.println("Addinting memebr" + part);
						waitTillappear(er.kdselectpeopleinnewconv, "xpath", "New Conversation Window");
						enterText(er.kdselectpeopleinnewconv, part, "xpath", "Participant name");
						Thread.sleep(5000);
						waitTillappear(er.kmcsuggestmailids, "xpath", "Suggested emails");
						click(er.kdemailaddresstoselect + part + er.ksinglquote, "Participant Email Address");
						test.log(LogStatus.INFO, part + "is selected");
					}
					test.log(LogStatus.INFO, newconversation + "is selected");
				} else {
					System.out.println("Addinting memebr" + newconversation);
					enterText(er.kdselectpeopleinnewconv, newconversation, "xpath", "Participant name");
					Thread.sleep(5000);
					waitTillappear(er.kmcsuggestmailids, "xpath", "Suggested emails");
					click(er.kdemailaddresstoselect + newconversation + er.ksinglquote, "Participant Email Address");
					test.log(LogStatus.INFO, newconversation + "is selected");
				}
				test.log(LogStatus.PASS, "Partcipants are selected in new conversation window"
						+ test.addScreenCapture(takeScreenShot()));
				click(er.kdcreatenforwardpost, "click on  Create & Forward");
				Thread.sleep(2000);
				test.log(LogStatus.PASS, "Created andnforwarded " + test.addScreenCapture(takeScreenShot()));
				korahomepage.selectTopLeftMenuOption("All Messages");
				test.log(LogStatus.PASS,
						"Forwarded post is applied to selected group/emails" + test.addScreenCapture(takeScreenShot()));

			}
			/**
			 * For existing one to one messages or Discussion room or group
			 * conversation
			 */
			else if (!discRoomorConversationName.equalsIgnoreCase("NA")) {
				System.out.println("------------------ Discussion Room Name -------------------------");
				moveToElement(er.kdfrwrdpostConversationname + discRoomorConversationName + er.ksinglquote, "xpath");
				click(er.kdfrwrdpostConversationname + discRoomorConversationName + er.ksinglquote
						+ "/../../../..//button[@class='sendBtn']", "Clicking to Forward a post");
				Thread.sleep(2000);
				if (remoteDriver.getPageSource().contains("Post forwarded successfully.")) {
					test.log(LogStatus.PASS, "Post forwarded successfully. message disaplyed after post being forwarded"
							+ test.addScreenCapture(takeScreenShot()));
				} else {
					test.log(LogStatus.FAIL,
							"Failed to validate Post forwarded successfully. message after post being forwarded");
				}
				click(er.kdfowradpostWindowclose, "Clicking to close forward post window");
				Thread.sleep(2000);
				/** Validate whether post is being forwarded or not */				
				korahomepage.selectTopLeftMenuOption("All Messages");
				Thread.sleep(2000);
				//				moveToElement(er.kmdmsgordiscroom + discRoomorConversationName + er.ksinglquote, "");
				//				movetoaPostandClickon3dots(discRoomorConversationName, post, false);
				//				System.out.println(er.kdrpostname0 + discRoomorConversationName + er.kdrpostname1 + post
				//						+ er.ksinglquote + "/../..//i[@class='p-icon kr-return']");
				//				elementIsDisplayed(er.kdrpostname0 + discRoomorConversationName + er.kdrpostname1 + post
				//						+ er.ksinglquote + "/../..//i[@class='p-icon kr-return']", "xpath");

			} else if (!Searchwith.equalsIgnoreCase("NA")) {
				System.out.println("-------------------- Search people, chats & rooms in Forward post  ----------");
				enterText(er.kdsearchbarinforwardpost, Searchwith, "Search people, chats & rooms in Forward post ");
				Thread.sleep(4000);
				List<WebElement> searchresults = remoteDriver.findElements(By.xpath(er.kdsearchresultsforwardpost));
				if (searchresults.size() > 1) {
					click(er.kdrsearchresultsfirstsenditem, "Clicking to close forward post window");
					Thread.sleep(2000);
					if (remoteDriver.getPageSource().contains("Post forwarded successfully.")) {
						test.log(LogStatus.PASS,
								"Post forwarded successfully. message disaplyed after post being forwarded in Search with Email address"
										+ test.addScreenCapture(takeScreenShot()));
						click(er.kdfowradpostWindowclose, "Clicking to close forward post window");
						Thread.sleep(2000);
						korahomepage.selectTopLeftMenuOption("All Messages");
						Thread.sleep(2000);
						test.log(LogStatus.PASS, "Forwarded post is applied to Searched group/emails"
								+ test.addScreenCapture(takeScreenShot()));
					} else {
						test.log(LogStatus.FAIL,
								"Failed to validate Post forwarded successfully. message after post being forwarded");
					}
				} else {
					test.log(LogStatus.FAIL,
							"In Forward post 'people, chats & rooms' in Forward post didn't fetch any results "
									+ test.addScreenCapture(takeScreenShot()));
					click(er.kdfowradpostWindowclose, "Clicking to close forward post window");
				}
			} else {

				test.log(LogStatus.WARNING,
						"Please select either newconversation or Discussion Room or One one to messages to forward post");
				click(er.kdfowradpostWindowclose, "Clicking to close forward post window");
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Forward post to new conversation or Group or Discussion room ");
			click(er.kdfowradpostWindowclose, "Clicking to close forward post window");
		}
	}

	/**
	 * This Method will select options from 3 dots of Right side panle of
	 * DR..like Star, Mute.....
	 * 
	 * @param discRoom
	 * @param Option
	 * @param Muteminutes
	 *            if User select mute then m
	 * @throws Exception
	 */
	public void selectoptionsfrom3dotsinRightPanelinDR(String discRoom, String Option, String Muteminutes)
			throws Exception {
		try {

			moveToElement(er.kdrManageRoom3dots0 + discRoom + er.kdrManageRoom3dots1, "xpath");
			click(er.kdrManageRoom3dots0 + discRoom + er.kdrManageRoom3dots1, "Click on 3 dots in Right Panel on DR ");
			Thread.sleep(3000);
			/***
			 * Clicking on Options in Right side 3 dots of a
			 * DR.....Star/Mute/Mark as unread/View Files/Copy Email Address/Get
			 * Link/Manage Room
			 ***/

			if (Option.equalsIgnoreCase("Mute")) {
				click("//*[text()='" + Option + "']", "xpath");
				click("//*[text()='" + Muteminutes + "']", "xpath");
				Thread.sleep(3000);
				test.log(LogStatus.PASS, "Mute action performed successfully on DR " + discRoom.toString()
				+ test.addScreenCapture(takeScreenShot()));
			} else {

				if(Option.equalsIgnoreCase("Star")&& remoteDriver.findElement(By.xpath(er.kwstarfilledstatus)).getText().trim().equalsIgnoreCase("Unstar"))
				{
					click(er.kwstarfilledstatus, "Clicking on Unstar");
					Thread.sleep(2000);
				}

				click("//*[text()='" + Option + "']", "xpath");
				Thread.sleep(3000);
				test.log(LogStatus.PASS, Option.toString() + " action performed successfully on DR "
						+ discRoom.toString() + test.addScreenCapture(takeScreenShot()));
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Perfrom 3Dots Actions on Discussion Room " + discRoom.toString()
			+ test.addScreenCapture(takeScreenShot()));
			e.printStackTrace();
		}
	}

	/**
	 * Precondition:: User should be in Manage Room Section This method will add
	 * / remove participants from Discussion Room
	 * 
	 * @param discRoom
	 * @param addpeople
	 * @param removepeople
	 * @throws Exception
	 */

	public List<String> addandremovepeoplefromdiscussionRoom(String discRoom, String addpeople, String removepeople)
			throws Exception {
		try {
			click(er.kwmanagedrMemebrs, "xpath");
			/*
			 * Print the number people who are in Group
			 */			
			int numberofpeople=remoteDriver.findElements(By.xpath("//div[@class='memberList']/div[2]/ul/li")).size();
			for(int i=1;i<=numberofpeople;i++)
			{
				moveToElement("//div[@class='memberList']/div[2]/ul/li[\"+i+\"]//div[1]/div[@class='userEmail']", "xpath");
				String useremailaddress= remoteDriver.findElement(By.xpath("//div[@class='memberList']/div[2]/ul/li["+i+"]//div[1]/div[@class='userEmail']")).getText().trim();
				totalmemebersinRoom.add(useremailaddress);
			}	
			test.log(LogStatus.PASS, "Memebers in Group are"+totalmemebersinRoom
					+ test.addScreenCapture(takeScreenShot()));
			if (!addpeople.contains("N/A")) {
				if (addpeople.contains(",")) {
					String result[] = addpeople.trim().split("\\s*,\\s*");
					for (String part : result) {
						System.out.println(part);
						enterText(er.kwaddpeopleplacehilder, part,
								"Adding " + part + " to discussion Room " + discRoom);
						waitTillappear("//span[text()='" + part + "']", "xpath", "Suggested email addres of " + part);
						click("//span[text()='" + part + "']", "Selecting email address " + part);
						Thread.sleep(2000);
						click(er.kwaddpeopleadinmember, "xpath");
						Thread.sleep(5000);
						test.log(LogStatus.PASS, "Added User to Memebrs list of Discussion Room"
								+ test.addScreenCapture(takeScreenShot()));
					}
				} else {
					enterText(er.kwaddpeopleplacehilder, addpeople,
							"Adding " + addpeople + " to discussion Room " + discRoom);
					waitTillappear("//span[text()='" + addpeople + "']", "xpath",
							"Suggested email addres of " + addpeople);
					click("//span[text()='" + addpeople + "']", "Selecting email address " + addpeople);
					Thread.sleep(2000);
					click(er.kwaddpeopleadinmember, "xpath");
					Thread.sleep(5000);
					test.log(LogStatus.PASS,
							"Added User to Memebrs list of Discussion Room" + test.addScreenCapture(takeScreenShot()));
				}
			}
			if (!removepeople.contains("N/A")) {
				if (removepeople.contains(",")) {
					String result[] = removepeople.trim().split("\\s*,\\s*");
					for (String part : result) {
						moveToElement(er.kwmemebrsinaDreamilaccess0 + part + er.kwmemebrsinaDreamilaccess1, "xpath");
						click(er.kwmemebrsinaDreamilaccess0 + part + er.kwmemebrsinaDreamilaccess1,
								"clicking on Eamil access type");
						Thread.sleep(2000);
						click(er.kwremovingmemebrindr, "removing the particiapant " + part);
						click(er.kwremovingmemebrindrconfirm, "Confirm removing the particiapant " + part);
						Thread.sleep(5000);
						test.log(LogStatus.PASS, "Removing User from Memebrs list of Discussion Room"
								+ test.addScreenCapture(takeScreenShot()));
					}
				} else {
					moveToElement(er.kwmemebrsinaDreamilaccess0 + removepeople + er.kwmemebrsinaDreamilaccess1,
							"xpath");
					click(er.kwmemebrsinaDreamilaccess0 + removepeople + er.kwmemebrsinaDreamilaccess1,
							"clicking on Eamil access type");
					Thread.sleep(2000);
					click(er.kwremovingmemebrindr, "removing the particiapant " + removepeople);
					click(er.kwremovingmemebrindrconfirm, "Confirm removing the particiapant " + removepeople);
					Thread.sleep(5000);
					test.log(LogStatus.PASS, "Removing User from Memebrs list of Discussion Room"
							+ test.addScreenCapture(takeScreenShot()));
				}
			}
			click(er.kdfowradpostWindowclose, "xpath");

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Add/remove  User from Memebrs list of Discussion Room"
					+ test.addScreenCapture(takeScreenShot()));
			e.printStackTrace();
		}
		return totalmemebersinRoom;
	}

	public void messagesreadinPostinfandMsginfo(String userwhoreadthemessage, boolean clicksubaction) throws Exception {
		try {
			System.out.println("------------------- Post/Message info ------------");
			if (clicksubaction)
				click(er.kwpostinfonMessageinfofrom3dots, "Message info");
			Thread.sleep(3000);
			waitTillappear(er.kdpostinfonmsginfoTitle, "xpath", "Post or Message info Title popup");
			Thread.sleep(5000);
			waitTillappear("//div[@class='traceInfoBox']/div[1]", "xpath", "Post or Message info Title popup");
			List<WebElement> noofusersuserswhoreadmessage = remoteDriver
					.findElements(By.xpath("//div[@class='traceInfoBox']/div"));
			noofuser: for (int i = 1; i <= noofusersuserswhoreadmessage.size(); i++) {
				String readorSent = remoteDriver
						.findElement(By.xpath("//div[@class='traceInfoBox']/div[" + i + "]/div[1]/div[3]/div[1]"))
						.getText();
				String readby = remoteDriver
						.findElement(By.xpath("//div[@class='traceInfoBox']/div[" + i + "]/div[1]/div[2]/div[1]"))
						.getText();
				if (readorSent.equals("Read") && readby.equalsIgnoreCase(userwhoreadthemessage)) {
					test.log(LogStatus.PASS, readby + " User read the post " + test.addScreenCapture(takeScreenShot()));
					break noofuser;
				}
			}
			click(er.kdfowradpostWindowclose, "Close post info pop up");
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Error in Read post info /Message Info " + test.addScreenCapture(takeScreenShot()));
			click(er.kdfowradpostWindowclose, "Close post info pop up");
			e.printStackTrace();
		}
	}


	public void rename_LeaveRoom_DeleteRoom(String rename,String leaveroomorDelete) throws InterruptedException, Exception {		
		try {						
			if(!rename.contains("N/A")||!rename.contains(""))
			{
				String beforerename=getAttributeValue(er.kdrRoomname, "value");
				//				enterText(er.kdrRoomname, "", "renaming Room as "+"");
				enterText(er.kdrRoomname, rename, "renaming Room as "+rename);
				click(er.kdrRoomname, "RenameRoom");
				String afterrename=getAttributeValue(er.kdrRoomname, "value");								
				if(!beforerename.equalsIgnoreCase(afterrename)) {
					test.log(LogStatus.PASS,
							"DR rename is successfully done"
							.toString() + test.addScreenCapture(takeScreenShot()));	
				}				
				else {
					test.log(LogStatus.FAIL,
							"FAILED to rename DR name"
							.toString() + test.addScreenCapture(takeScreenShot()));
				}				
			}
			if(leaveroomorDelete.contains("Leave"))
			{		

				click(er.kdrleaveDR," Leavning Room ");				
				click(er.kdrleaveDRconfirm , " Leavning Room confirmation ");								
				test.log(LogStatus.PASS,
						"Leave Discussion Room succesffuly performed"
						.toString() + test.addScreenCapture(takeScreenShot()));
			}else if(leaveroomorDelete.contains("Delete"))
			{				
				click(er.kdrdeleteDR , " Delete Room ");				
				click(er.kdrdeleteDRconfirm , " Confirm Delete Room ");
				test.log(LogStatus.PASS,
						"Delete Discussion Room performed successfully"
						.toString() + test.addScreenCapture(takeScreenShot()));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Failed in rename_LeaveRoom_DeleteRoom page"
					.toString() + test.addScreenCapture(takeScreenShot()));
		}
	}

	public void atMentionsinComments(String discRoom,String post, String commenttext) throws Exception {
		try {						

			enterText(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
					+ "/../../..//div[@id='discInput']", commenttext , "xpath", "Comment on post");				
		}catch(Exception e){
			enterText(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
					+ "/../../..//div[@id='discInput']", "" , "xpath", "Comment on post");
			e.printStackTrace();
			test.log(LogStatus.FAIL,
					" FAILING in atMentionValidationinDR ".toString() + test.addScreenCapture(takeScreenShot()));
		}		
	}


	public void cancledrcreationanddiscardmsg(String NewDRname,String participantlist)
			throws Exception {
		try {

			click(er.kmcplusicon, "Plus icon to start new Dsicussion Room");						
			click(er.kddiscussionTitle, "Clicking on Discussion Room title ");
			enterText(er.kddiscussionTitle, NewDRname, "Discussion Room Title as " + NewDRname);
			moveToElement(er.kmcenterparticipant, "xpath");
			click(er.kmcenterparticipant, "Enter participant name");
			System.out.println("participantlist-----" + participantlist);
			if (participantlist.contains(",")) {
				String result[] = participantlist.trim().split("\\s*,\\s*");
				for (String part : result) {
					System.out.println(part);
					koramessagespage.select(part);

				}
			} else {
				koramessagespage.select(participantlist);
			}						
			enterText(er.kcomposebar,NewDRname , "Comopsbar");
			goToGroupAndPerforminWSDR("DoNotDeleteDRAuto", false, "");		    
			Thread.sleep(5000);			
			waitTillappear(er.kdrdiscardmsg, "xpath", "Are you sure want to discard message :: YES");
			test.log(LogStatus.PASS,
					" Discard Message appeared  ".toString() + test.addScreenCapture(takeScreenShot()));
			click(er.kdrdiscardmsg, "Discard msg of message");			
			//			click(er.kdrclosenewdrcreation, "close Dr creation");			
			Thread.sleep(5000);						
			valdiatedeletedMsgorDR(NewDRname);			

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					" FAILED to Delete Post ".toString() + test.addScreenCapture(takeScreenShot()));
		}
	}

	public void deletepostandreactcount(String discRoom,String post) throws Exception {
		try {						

			moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote, "xpath");
			click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote, "Click on post ");

			System.out.println("performing More reaction");
			Thread.sleep(1000);								
			for(int j=1;j<5;j++)
				moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote+ "/../..//div[@class='reactionIcons']/i["+j+"]", "xpath");			
			test.log(LogStatus.PASS, post + "Validating Reactions  ".toString()
					+ test.addScreenCapture(takeScreenShot()));			
			moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote+ "/../..//i[contains(@class,'icon __i kr-comment')]", "xpath");
			Thread.sleep(1000);
			moveToElement(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote+ "/../..//i[contains(@class,'icon __i kr-ellipsis')]", "xpath");
			Thread.sleep(1000);
			click(er.kdrpostname0 + discRoom + er.kdrpostname1 + post + er.ksinglquote
					+ "/../..//i[contains(@class,'icon __i kr-ellipsis')]", "more options");
			Thread.sleep(1000);			
			click("//i[@class='icon kr-delete']", "more options");					
			test.log(LogStatus.PASS, post + "Deleting post  ".toString()
					+ test.addScreenCapture(takeScreenShot()));
			korahomepage.clickOn("Delete", true);

			if(remoteDriver.getPageSource().contains("Deleted post successfully"))
			{
				test.log(LogStatus.PASS,
						" Post deleted Successfully ".toString() + test.addScreenCapture(takeScreenShot()));
			}else {
				test.log(LogStatus.FAIL,
						" Post deleting failed  ".toString() + test.addScreenCapture(takeScreenShot()));
			}

		}catch(Exception e){

			e.printStackTrace();
			test.log(LogStatus.FAIL,
					" FAILING in atMentionValidationinDR ".toString() + test.addScreenCapture(takeScreenShot()));
		}		
	}

	public void validatePostviaemailandAllmemebrsemail() throws Exception {
		try {	
			moveToElement(er.kdpostviaemailtoggle, "xpath");
			if(!getAttributeValue(er.kdpostviaemailtoggle, "aria-checked").equalsIgnoreCase("true"))
			{
				click(er.kdpostviaemailtoggle, "click on post via email toggle");
				Thread.sleep(3000);
			}											
			if (elementIsDisplayed(er.kdrtoggleemaillink, "xpath")) {
				test.log(LogStatus.PASS, "Post Via Emial Link displayed after toggle turned on");
			}
			if (elementIsDisplayed(er.kdrtoggleedit, "xpath")) {
				test.log(LogStatus.PASS, "Edit option displayed after toggle turned on");
			}
			if (elementIsDisplayed(er.kdrtogglecopy, "xpath")) {
				test.log(LogStatus.PASS, "Copy option displayed after toggle turned on".toString() + test.addScreenCapture(takeScreenShot()));
			}			
			//Turn off toggle after			
			click(er.kdpostviaemailtoggle, "click on post via email toggle");
			Thread.sleep(3000);
			if (!elementIsDisplayed(er.kdrtoggleemaillink, "xpath")) {
				test.log(LogStatus.PASS, "Post Via Emial Link NOT  displayed after toggle turned OFF");
			}
			if (!elementIsDisplayed(er.kdrtoggleedit, "xpath")) {
				test.log(LogStatus.PASS, "Edit option NOT displayed after toggle turned OFF");
			}
			if (!elementIsDisplayed(er.kdrtogglecopy, "xpath")) {
				test.log(LogStatus.PASS, "Copy option NOT displayed after toggle turned OFF".toString() + test.addScreenCapture(takeScreenShot()));
			}
//			click(er.kdrmembers, "Members tab from Manage room");

		}catch(Exception e){

			e.printStackTrace();
			test.log(LogStatus.FAIL,
					" Failed validate Postviaemail options validation ".toString() + test.addScreenCapture(takeScreenShot()));
		}	
	}

	public void createDRwithWS(String workspacename, String NewDRname, String participantlist) throws Exception {
		try {			
			System.out.println("---------------------- createDRwithAccessTypefromMessages -------------");
			click(er.kmcplusicon, "Plus icon to start new Dsicussion Room");
			if (getAttributeValue(er.kdSearchboxinmsgnDR, "placeholder").equalsIgnoreCase("Search Messages"))
				click(er.kmdiscussion, "Create a Discussion Room");
			click(er.kdselectworkspace, "Clicking on Select workspace ");						
			//click on new WS
			click(er.kdrcreatenewWsplus, "Clicking on new WS ");
			//delete existing
			moveToElement(er.kdrnewWorkspacename, "xpath");
			String os = System.getProperty("os.name");
			if (os.equals("WINDOWS")){
			   Keys.chord(Keys.CONTROL, "a");
			}else{
			   Keys.chord(Keys.COMMAND, "a");			  
			}
			enterText(er.kdrnewWorkspacename, workspacename, "Workspace Room Title as " + workspacename);				
			click(er.kdrnewwsdonebtn, "Clicking on Done new Workspace button ");
			Thread.sleep(5000);
			click(er.kddiscussionTitle, "Clicking on Discussion Room title ");
			enterText(er.kddiscussionTitle, NewDRname, "Discussion Room Title as " + NewDRname);
			moveToElement(er.kmcenterparticipant, "xpath");
			click(er.kmcenterparticipant, "Enter participant name");
			
			System.out.println("participantlist-----" + participantlist);
			if (participantlist.contains(",")) {
				String result[] = participantlist.trim().split("\\s*,\\s*");
				for (String part : result) {
					System.out.println(part);
					koramessagespage.select(part);

				}
			} else {
				koramessagespage.select(participantlist);
			}
			Thread.sleep(2000);			
			System.out.println("----- Entering Data to Disucssion Room ---");
			moveToElement(er.kcomposebar, "Moving to composebar");
			click(er.kcomposebar, "Clicking on Compose Bar");
			Thread.sleep(5000);
			koramessagespage.enterYourMessageAs("Newely Created Discusion Room " + NewDRname);
			Thread.sleep(5000);
			korahomepage.waittillpageload();

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to select the mentioned participant");

		}
	}

}