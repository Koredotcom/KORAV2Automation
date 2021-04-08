package com.org.kore.web.pages;

import java.util.List;

import org.openqa.selenium.By;
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

	public KoraMessagesDRPage(RemoteWebDriver remoteWebDriver) {
		super(remoteWebDriver);
		cf = new CPCommonFunctions(remoteWebDriver);
		koramessagespage= new KoraMessagesChatsPage(remoteWebDriver);
		korahomepage = new KoraHomePage(remoteWebDriver);
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

	public void validateDROnhoverOptions(){
		String validationelmenets[]={"Star","Mute", "Read"};			
		for(String value: validationelmenets )
		{
			//	koramessagedrpage.verifytheoptionsonDRandperfromAction("DoNotDeleteDRAuto",value,"SelectNOT");					
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
	public void perfromreactionsonPost(String discRoom, String post, String reaction, boolean comment, String commenttext) throws Exception {
		try {
			System.out.println("-----------method ::--perfromreactionsonPost-------------------------------");
			if(!comment)
			{
				moveToElement(er.kdrpostname0+discRoom+er.kdrpostname1+post+er.ksinglquote,"xpath");
				click(er.kdrpostname0+ discRoom + er.kdrpostname1+ post+ er.ksinglquote, "Click on post ");
				Thread.sleep(3000);
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
					System.out.println("performing Angry reaction");
					moveToElement(
							er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[5]",	"xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//div[@class='reactionIcons']/i[5]",
							"Angry");
					Thread.sleep(1000);
					test.log(LogStatus.PASS, post + " post was reacted with ANGRY ".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "MORE":
					System.out.println("performing More reaction");
					moveToElement(
							er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[contains(@class,'icon __i kr-ellipsis')]","xpath");
					Thread.sleep(1000);
					click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[contains(@class,'icon __i kr-ellipsis')]",
							"Comment on a post");								
					test.log(LogStatus.PASS, post + "Clicking on more options on post  ".toString()  + test.addScreenCapture(takeScreenShot()));


				default:
					test.log(LogStatus.FAIL,
							"Please provided valid on hover action i.e. , should be match with case value");
				}	
				Thread.sleep(2000);
			}

			if(comment) {
				System.out.println("performing Comments on POST "+post);
				moveToElement(er.kdrpostname0+discRoom+er.kdrpostname1+post+er.ksinglquote,"xpath");
				//				click(er.kdrpostname0+ discRoom + er.kdrpostname1+ post+ er.ksinglquote, "Click on post ");
				Thread.sleep(3000);
				moveToElement(
						er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[@class='icon __i kr-comment']",	"xpath");
				Thread.sleep(1000);
				click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[@class='icon __i kr-comment']",
						"Comment on a post");
				Thread.sleep(1000);
				enterText(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../../..//div[@id='discInput']", commenttext+"\n", "xpath", "Comment on post");
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


	/**
	 * When user want to Create DR from messages either from All messages or Discussion room.  
	 * @param workspacename 
	 * @param NewDRname
	 * @param participantlist
	 * @param AccessType
	 * @throws Exception
	 */

	public void createDRwithAccessTypefromMessages(String workspacename,String NewDRname, String participantlist,String AccessType) throws Exception {
		try {			
			click(er.kmcplusicon, "Plus icon to start new Dsicussion Room");
			if(getAttributeValue(er.kdSearchboxinmsgnDR, "placeholder").equalsIgnoreCase("Search Messages")) 
				click(er.kmdiscussion, "Create a Discussion Room");

			if(cf.elementIsDisplayed(er.kdselectworkspace, "xpath"))
			{
				click(er.kdselectworkspace, "Clicking on Select workspace ");
				click(er.kdtoggleicontoselectWS , "Clicking on  breadcrumb");
				click("//span[@class='hamMenuWSName' and text()='"+workspacename+"']", "Selecting Workspace  "+workspacename);
			}
			click(er.kddiscussionTitle, "Clicking on Discussion Room title ");
			enterText(er.kddiscussionTitle, NewDRname, "Discussion Room Title as "+NewDRname);
			moveToElement(er.kmcenterparticipant, "xpath");
			click(er.kmcenterparticipant, "Enter participant name");
			System.out.println("participantlist-----"+participantlist);
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
			//Validating  Everyone at No workspace is displayed and its on or Off
			if(cf.elementIsDisplayed(er.kdeveryoneAtnoWorkspace, "xpath"))
			{	
				test.log(LogStatus.INFO,  " Everyone at No workspace option dispalyed");
			}			
			// Validating whether by default participants are having Post only Access by default			
			if(cf.getText(er.kddefaultAccessto).trim().equals("Post Only"))
			{
				test.log(LogStatus.PASS,  " By Default Discussion Room participants will have Post Only access");
			}else {
				test.log(LogStatus.FAIL,  " By Default Discussion Room participants are having "+cf.getText("//i[@class='icon kr-tick']/..//span[@class='Name']").trim() + " Instead of Post Only access");
			}
			if(!AccessType.equals("Post Only"))
			{
				if(AccessType.equals("Full Access"))
				{
					click("//span[@class='Name' and text()='"+AccessType+"']", "Clicking on Access Type "+AccessType);
				}else if(AccessType.equals("Comment Only"))
				{
					click("//span[@class='Name' and text()='"+AccessType+"']", "Clicking on Access Type "+AccessType);
				}else {
					test.log(LogStatus.FAIL, " User must provide access Type while creating a DR other than Default value ");	
				}
			}		
			click(er.kdrsettings, "Clicking on Setting icon in Right Side panel After creating DR and Setting Access Type");
			System.out.println("----- Entering Data to Disucssion Room ---");
			click(er.kcomposebar, "Clicking on Compose Bar");		
			koramessagespage.enterYourMessageAs("New Created Discusion Room By me");											

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to select the mentioned participant");

		}
	}
	/*** 
	 * @param discRoom ::On Which discussion room we are doing operations 
	 * @param post :: post where comments or Reactions took place
	 * @param Whoreacted :: User who reacted/ commented to post
	 * @param commenttext :: commented text  
	 * @throws Exception
	 */
	public void validatingreactionsandCommentsonPost(String discRoom, String post, String Whoreacted, String commenttext) throws Exception {
		try {

			String element= er.kdrpostname0+discRoom+er.kdrpostname1;
			moveToElement(element+post+er.ksinglquote,"xpath");						
			if(!commenttext.equals("") || !commenttext.equals(null))
			{
				click(element+post+er.ksinglquote+"/../..//*[contains(@class,'icon kr-comment')]", "Click on post ");
				Thread.sleep(2000);				
				String actualuserwhocommented =getText(element+commenttext+ er.ksinglquote+"/../..//span[@class='name']");
				if(Whoreacted.equalsIgnoreCase(actualuserwhocommented))
				{
					test.log(LogStatus.PASS,  "Comment on post successfully applied with right User".toString() +test.addScreenCapture(takeScreenShot()));				
				}else {
					test.log(LogStatus.FAIL,  "Comment on post NOT Applied".toString() +test.addScreenCapture(takeScreenShot()));
				}
			}
			if(!Whoreacted.equals("") || !Whoreacted.equals(null))
			{
				moveToElement(element+post+er.ksinglquote+"/../..//div[@class='postReaction']/div[1]","xpath");
				click(element+post+er.ksinglquote+"/../..//div[@class='postReaction']/div[1]","Clicking on Ractions");
				Thread.sleep(2000);
				String actualuserwhoreacted=getText(element+post+er.ksinglquote+"/../..//div[@class='postReaction']/div[3]/div[2]/div[1]/div[2]/span[1]");
				if(Whoreacted.equalsIgnoreCase(actualuserwhoreacted))
				{
					test.log(LogStatus.PASS,  "Reaction on post successfully applied with right User".toString() +test.addScreenCapture(takeScreenShot()));				
				}else {
					test.log(LogStatus.FAIL,  "Reaction on post NOT Applied".toString() +test.addScreenCapture(takeScreenShot()));
				}
			}			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate reactions and Comments on a Post");

		}	
	}

	public void valdiatedeletedMsgorDR(String chatordiscroomname) throws InterruptedException, Exception	
	{
		try {
			System.out.println("------------ Moving To Element ----------");			
			moveToElement(er.kmdmsgordiscroom+chatordiscroomname+er.ksinglquote,"xpath");
			click(er.kmdmsgordiscroom+chatordiscroomname+er.ksinglquote, "chatordiscroomname");			
			test.log(LogStatus.FAIL,  "Even after Deleteing Discussion room still we are able to see the Discussion Room in Messages / DR Room ".toString() +test.addScreenCapture(takeScreenShot()));
		}
		catch(Exception e)
		{
			test.log(LogStatus.PASS,  "After Deleting Discussion room We are not able to see the DR in messages/ DR Room ".toString() +test.addScreenCapture(takeScreenShot()));
		}
	}

	public void EditingPostinDiscussionRoom(String discRoom, String post, String editingPost, String dotOptions) throws Exception	
	{		
		try {					
			System.out.println("------------Eneter data to post---------------");
			WebElement compose = remoteDriver.findElement(By.xpath(er.kcomposebar));
			compose.sendKeys(post, Keys.ENTER);
			Thread.sleep(4000);						
			moveToElement(er.kdrpostname0+discRoom+er.kdrpostname1+post+er.ksinglquote,"xpath");
			click(er.kdrpostname0+ discRoom + er.kdrpostname1+ post+ er.ksinglquote, "Click on post ");
			Thread.sleep(4000);
			moveToElement(
					er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[contains(@class,'icon __i kr-ellipsis')]",	"xpath");			
			click(er.kdrpostname0 + discRoom+er.kdrpostname1+post+er.ksinglquote+"/../..//i[contains(@class,'icon __i kr-ellipsis')]",
					"Click on 3dits options to ");								
			test.log(LogStatus.PASS, post + "Clicking on more options on post  ".toString()  + test.addScreenCapture(takeScreenShot()));			
			korahomepage.waittillpageload();
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", dotOptions,"post3dots");
			System.out.println("------------------- Edit a post ------------");
			click(er.kdeditpost, "Editing post");	
			korahomepage.waittillpageload();
			enterText(er.kdeditpostcomposebar, editingPost, post+" post is Editing with "+editingPost);
			korahomepage.clickOn("Save", true);
			korahomepage.waittillpageload();				
			moveToElement(er.kdrpostname0+discRoom+er.kdrpostname1+editingPost+er.ksinglquote,"xpath");
			click(er.kdrpostname0+ discRoom + er.kdrpostname1+ editingPost+ er.ksinglquote, "Click on post ");
			test.log(LogStatus.PASS, post + " Post Edited Successfully  with ".toString()+ editingPost + test.addScreenCapture(takeScreenShot()));
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, post + " FAILED to Edit Post ".toString()  + test.addScreenCapture(takeScreenShot()));
		}
	}
	
	public void atMentionValidationinDR() throws Exception
	{
		moveToElement(er.kwcomposebar, "xpath");
		Thread.sleep(2000);
		enterText(er.kwcomposebar, "@", "xpath", "Type your message");
		Thread.sleep(2000);
		List<WebElement> atmentionusers = remoteDriver.findElements(By.xpath(er.kmcatmentionusernames));		
		if (atmentionusers.size()>0) {
			test.log(LogStatus.WARNING,
					"@ mention showing the particiapants in list "
							.toString() + test.addScreenCapture(takeScreenShot()));
		} else {			
			test.log(LogStatus.FAIL,  " @ Mention not Showing any Participants ".toString() + test.addScreenCapture(takeScreenShot()));
		}	
	}
	

	
}