package com.org.kore.web.tests;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.KoraHomePage;
import com.org.kore.web.pages.KoraLoginPage;
import com.org.kore.web.pages.KoraMessagesChatsPage;
import com.org.kore.web.pages.KoraMessagesDRPage;
import com.org.kore.web.pages.KoraWorkspacesPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class MessagesDR extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesChatsPage koramessagespage;
	KoraWorkspacesPage koraworkspacepage;
	KoraMessagesDRPage koramessagedrpage;

	String korajusername;
	String korajpassword;

	String korahusername;
	String korahpassword;

	static String directory =null;

	public MessagesDR() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute Group test");

		koraloginpage = new KoraLoginPage(remoteDriver);
		korahomepage = new KoraHomePage(remoteDriver);
		koramessagespage = new KoraMessagesChatsPage(remoteDriver);
		koraworkspacepage = new KoraWorkspacesPage(remoteDriver);
		koramessagedrpage = new KoraMessagesDRPage(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");

		directory = System.getProperty("user.dir");

	}


	@Test(enabled = true, priority = 41)  
	public void MDR_TC12_TC13_TC30_validating3DotOptionsFromMidAndRightPanels() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddiscroom = DriverSetUp.drdataMap.get("standarddr"); 
			String expWDRmiddle3dotoptions = DriverSetUp.drdataMap.get("wexpected3dotoptionsmiddle");
			String expWDRright3dotoptions = DriverSetUp.drdataMap.get("wexpected3dotoptionsright");
			test.log(LogStatus.INFO, "Navigation url :" + url);			

			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR("DRDelete", true, "3dots"); 
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", expWDRmiddle3dotoptions, "middlePanel");
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", expWDRright3dotoptions, "rightPanel");
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate 3DotOptions From MidAndRight Panel");
		}
	}

	@Test(enabled = true, priority = 42) 
	public void MDR_TC24_DROnhoverOptionsVerification() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			String validationelmenets[] = { "Star", "Mute", "UnRead" };
			for (String value : validationelmenets) {
				koramessagedrpage.verifytheoptionsonDRandperfromAction(standarddrname, value, "SelectNOT");
			}
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate  On hover Options Verification");
		}
	}

	/**
	 * "Create a discussion room from All messages/Discussion room and add user
	 * with > Post only (Default post will be given)"
	 * 
	 * @throws Exception
	 *             Pass
	 */
	@Test(enabled = true, priority = 43)  
	public void MDR_TC32_TC33_TC34_TC35_TC36_TC37_createNewDRwithDifferentAccessTypes() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages"); 
			String newRandomDr = "random"+korahomepage.runtimehhmmss();
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname, newRandomDr,
					newparticipants, "Comment Only");			
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagedrpage.goToGroupAndPerforminWSDR(newRandomDr, true, "Star");

			// To Create DR From Bottom Left Panel
			String newRandomDr2 = "random"+korahomepage.runtimehhmmss();
			korahomepage.selectBottomLeftMenuWorkSpace(standardwsname);
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname, newRandomDr2,
					newparticipants, "Full Access");			

			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagedrpage.goToGroupAndPerforminWSDR(newRandomDr2, true, "Star");

			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR(newRandomDr, true, "3dots");
			koramessagedrpage.deletDiscRoomandWorkSpace(newRandomDr,"NA");

			koramessagedrpage.goToGroupAndPerforminWSDR(newRandomDr2, true, "3dots");
			koramessagedrpage.deletDiscRoomandWorkSpace(newRandomDr2,"NA");

			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create New DR with Different AccessTypes");
		}
	}

	/**
	 * TC_64Delete Discussion Room with WorkSpace, without WorkSpace from
	 * Messages Updated
	 */
	@Test(enabled = true, priority = 44)  
	public void MDR_TC64_deleteWorkSpacefromMessages() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms"); 
			String newDrrandom = "random"+korahomepage.runtimehhmmss();
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname, newDrrandom,
					newparticipants, "Comment Only");
			korahomepage.selectTopLeftMenuOption("Discussion Rooms"); 
			koramessagedrpage.goToGroupAndPerforminWSDR(newDrrandom, true, "3dots");
			koramessagespage.operationsFrom3Dots("Delete Discussion");
			korahomepage.clickOn("Delete", true);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.valdiatedeletedMsgorDR(newDrrandom);			
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate delete WorkSpace from Messages");
		}
	}

	/**
	 * Editing a post in Discussion Room Also validates
	 * Edit,Forward,Reminder,Post Info,Delete options displayed in 3 dots to a
	 * post
	 */
	@Test(enabled = true, priority = 45)  
	public void MDR_TC53_TC56_EditaPost() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			String expWDpost3dotoptions = DriverSetUp.drdataMap.get("drexpected3dotsforPostinrightpanel");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			String newDrrandom = korahomepage.runtimehhmmss();
			koramessagedrpage.EditingPostinDiscussionRoom(standarddrname, "Post" + newDrrandom,
					"Editing Post" + newDrrandom, expWDpost3dotoptions);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Edit post");
		}
	}

	/**
	 * Forward post to new conversation , Existing and DR
	 */
	@Test(enabled = true, priority = 46) 
	public void MDR_TC49_TC54_TC55_TC57_forwardPostToGroupDrAndNewconv() throws Exception {

		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");			
			test.log(LogStatus.INFO, "Navigation url :" + url);			

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			String forwardtomemebers = "hana@koraqa1.com";
			String searchwithEmailAddress="dileep@koraqa1.com";
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			String post = "forwardingpost" + korahomepage.runtimehhmmss();
			koramessagespage.enterYourMessageAs(post);
			koramessagedrpage.movetoaPostandClickon3dots(standarddrname, post, true);
			koramessagedrpage.forwardPosttonewconvorexisting(post, forwardtomemebers, "NA", "NA");

			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			koramessagedrpage.movetoaPostandClickon3dots(standarddrname, post, true);			
			koramessagedrpage.forwardPosttonewconvorexisting(post, "NA", "DRDelete", "NA");

			/** when we search with Email address application not showing results temporarily .
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			koramessagedrpage.movetoaPostandClickon3dots(standarddrname, post, true);
			koramessagedrpage.forwardPosttonewconvorexisting(post, "NA", "NA", searchwithEmailAddress);
			 */
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate forward Post To Group DR And New  Conversation");
		}
	}

	@Test(enabled = true, priority = 47)
	public void MDR_TC25_TC26_TC28_TC29_starDrandMuteDrandvaldiation() throws Exception {
		try {

			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");			
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");			
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);			
			korahomepage.selectTopLeftMenuOption("Discussion Rooms"); 
			String DNDStarANDOther = "randomDR" +korahomepage.runtimehhmmss();

			koramessagedrpage.createDRwithAccessTypefromMessages("NA",DNDStarANDOther, newparticipants, "Post Only");			
			koramessagedrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, false, "");
			koramessagedrpage.selectoptionsfrom3dotsinRightPanelinDR(DNDStarANDOther, "Star","N/A");
			korahomepage.selectTopLeftMenuOption("Starred");
			koramessagedrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, false, "");

			koramessagedrpage.selectoptionsfrom3dotsinRightPanelinDR(DNDStarANDOther, "Unstar","N/A");
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			korahomepage.selectTopLeftMenuOption("Starred");
			koramessagedrpage.valdiatedeletedMsgorDR(DNDStarANDOther);

			korahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			koramessagedrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, false, "");
			koramessagedrpage.selectoptionsfrom3dotsinRightPanelinDR(DNDStarANDOther, "Mute","4 hours");
			korahomepage.selectTopLeftMenuOption("Muted");
			koramessagedrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, false, "");			
			koramessagedrpage.selectoptionsfrom3dotsinRightPanelinDR(DNDStarANDOther, "Unmute","");			
			korahomepage.selectTopLeftMenuOption("Muted"); 
			koramessagedrpage.valdiatedeletedMsgorDR(DNDStarANDOther);	

			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, true, "3dots");
			koramessagedrpage.deletDiscRoomandWorkSpace(DNDStarANDOther,"NA");
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate star Discussion Room and Mute DR ");
		}
	}
	
	@Test(enabled = true, priority = 48) 
	public void MDR_TC14_TC15_TC71_LeaveandDeleteDRfromManageRoom() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms"); 
			String randomDrwithTime = "randomDR" +korahomepage.runtimehhmmss();
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname,randomDrwithTime, newparticipants, "Post Only");								
			koramessagedrpage.goToGroupAndPerforminWSDR(randomDrwithTime, false, "");						
			koramessagedrpage.selectoptionsfrom3dotsinRightPanelinDR(randomDrwithTime, "Manage Room","N/A");						
			koramessagedrpage.rename_LeaveRoom_DeleteRoom("Rename","Leave");
			koramessagedrpage.valdiatedeletedMsgorDR("Rename"+randomDrwithTime);

			String randomDrwithTime2 = "randomDR" +korahomepage.runtimehhmmss();
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname,randomDrwithTime2, newparticipants, "Post Only");								
			koramessagedrpage.goToGroupAndPerforminWSDR(randomDrwithTime2, false, "");						
			koramessagedrpage.selectoptionsfrom3dotsinRightPanelinDR(randomDrwithTime2, "Manage Room","N/A");						
			koramessagedrpage.rename_LeaveRoom_DeleteRoom("N/A","Delete");
			koramessagedrpage.valdiatedeletedMsgorDR(randomDrwithTime2);			

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to  Leave and Delete DR from ManageRoom");
		}
	}

	/**
	 * Editing a post in Discussion Room Also validates
	 * Edit,Forward,Reminder,Post Info,Delete options displayed in 3 dots to a
	 * post
	 */
	@Test(enabled = true, priority = 49)  
	public void MDR_TC45_TC52_TC77_DeletePostandCancelnewDR() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");			
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");			
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");						
			String newDRrandom = "random"+korahomepage.runtimehhmmss();						
			koramessagedrpage.cancledrcreationanddiscardmsg(newDRrandom,newparticipants);		
			String drpost="postforLiknComment"+korahomepage.runtimehhmmss();
			koramessagedrpage.goToGroupAndPerforminWSDR("DRDelete", false, "");
			koramessagespage.enterYourMessageAs(drpost);
			koramessagedrpage.deletepostandreactcount("DRDelete", drpost);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Delete Post and Cancel new DR");
		}
	}

	/**
	 * At Mentions in posts users should display the users who are part of the
	 * room
	 */
	@Test(enabled = true, priority = 50) 
	public void MDR_TC7_TC59_TC60_TC61_atmentionUsersinDr() throws Exception {

		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");			
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "NA");
			koramessagedrpage.selectoptionsfrom3dotsinRightPanelinDR(standarddrname, "Manage Room","N/A");
			List<String> membersingroup=koramessagedrpage.addandremovepeoplefromdiscussionRoom(standarddrname,"N/A","N/A");
			String atmentionmsg = "at_mention_Automation_post"+korahomepage.runtimehhmmss();
			koramessagedrpage.atMentionValidationinDR(membersingroup,"james@koraqa1.com", atmentionmsg,"post");

			String drpost="postforComment"+korahomepage.runtimehhmmss();
			koramessagespage.enterYourMessageAs(drpost);
			koramessagedrpage.perfromreactionsonPost(standarddrname, drpost, "", true,"NA");
			koramessagedrpage.atMentionsinComments(standarddrname, drpost, "@");		
			String atmentionincomment = "atMentioninComment"+korahomepage.runtimehhmmss();
			koramessagedrpage.atMentionValidationinDR(membersingroup,"hana@koraqa1.com", atmentionincomment,"comment");

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate atmention Users in Discussion Room");
		}
	}

	@Test(enabled = true, priority = 51) 
	public void MDR_TC39_TC72_PostviaEmailToggle() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			test.log(LogStatus.INFO, "Navigation url :" + url);	

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms"); 			
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");								
			koramessagedrpage.selectoptionsfrom3dotsinRightPanelinDR(standarddrname, "Manage Room","N/A");
			koramessagedrpage.validatePostviaemailandAllmemebrsemail();						
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to  validate Postvia Email Toggle");
		}
	}

	@Test(enabled = true, priority = 52) 
	public void MDR_TC66_TC17_TC21_TC70_creationofRoomwithoutWS() throws Exception {
		try {

			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages"); 
			String randomDrwithTime = "randomDR" +korahomepage.runtimehhmmss();
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname,randomDrwithTime, newparticipants, "Post Only");			
			korahomepage.selectTopLeftMenuOption("All Messages");
			Thread.sleep(4000);
			koramessagedrpage.goToGroupAndPerforminWSDR(randomDrwithTime, false, "");						
			koramessagedrpage.selectoptionsfrom3dotsinRightPanelinDR(randomDrwithTime, "Manage Room","N/A");
			koramessagedrpage.addandremovepeoplefromdiscussionRoom(randomDrwithTime,"alexander@koraqa1.com",newparticipants);

			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR(randomDrwithTime, true, "3dots");
			koramessagedrpage.deletDiscRoomandWorkSpace(randomDrwithTime,"NA");

			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to creation of Room without Work Space");
		}
	}

	@Test(enabled = true, priority = 53)  
	public void MDR_TC23_TC69_TC76_TC78_createnewWSfromDRandaddDRs() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");			
			test.log(LogStatus.INFO, "Navigation url :" + url);						

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			String randomWS = "WS" +korahomepage.runtimehhmmss();
			String randomDrwithTime = "randomDR" +korahomepage.runtimehhmmss();
			String randomDrwithTime2 = "randomDR" +korahomepage.runtimehhmmss()+"_2";

			koramessagedrpage.createDRwithWS(randomWS,randomDrwithTime, newparticipants);
			koramessagedrpage.selectDRBasedonWSname(randomWS,randomDrwithTime);
			koramessagedrpage.createDRwithAccessTypefromMessages(randomWS, randomDrwithTime2,newparticipants, "Comment Only");
			koraworkspacepage.selectWorkspace(randomWS);
			
			koramessagedrpage.goToGroupAndPerforminWSDR(randomDrwithTime, true, "3dots");
			koramessagedrpage.deletDiscRoomandWorkSpace(randomDrwithTime,"NA");

			koramessagedrpage.goToGroupAndPerforminWSDR(randomDrwithTime2, true, "3dots");
			koramessagedrpage.deletDiscRoomandWorkSpace(randomDrwithTime2,"NA");

			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.deletDiscRoomandWorkSpace("NA",randomWS);
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Create new Work Spae from DR and add more Discussion Rooms");
		}
	}

	@Test(enabled = true, priority = 54) 
	public void MDR_TC62_TC73_TC74_TC75_addnewparticiapantstoexistingDr() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			String aditionalmember1="alexander@koraqa1.com";
			String aditionalmember2="dileep@koraqa1.com";			

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			String randomDrwithTime = "randomDR" +korahomepage.runtimehhmmss();				
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname, randomDrwithTime,
					newparticipants, "Comment Only");	

			koramessagedrpage.addnewmemebrswithaccesstype(randomDrwithTime,aditionalmember1,aditionalmember2,"Comment only");			
			koramessagedrpage.addnewmemebrswithaccesstypeinManageRoom(randomDrwithTime,aditionalmember2,"Full Access");

			koramessagedrpage.goToGroupAndPerforminWSDR(randomDrwithTime, true, "3dots");
			koramessagedrpage.deletDiscRoomandWorkSpace(randomDrwithTime,"NA");					
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Create new Work Spae from DR and add more Discussion Rooms");
		}
	}

	@Test(enabled = true, priority = 55)
	public void MDR_TC20_TC79_TC80_createNEWDRandvalidatefrompartcipantsend() throws Exception {
		try {

			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = "hana@koraqa1.com";
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages"); 
			String randomDrwithTime = "randomDR" +korahomepage.runtimehhmmss();
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname,randomDrwithTime, newparticipants, "Post Only");			
			korahomepage.selectTopLeftMenuOption("All Messages");						
			koramessagedrpage.searchforDRorchatfromallmsgDRnChat(randomDrwithTime,"NA");	
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagedrpage.searchforDRorchatfromallmsgDRnChat("NA","QA Pride");
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagedrpage.searchforDRorchatfromallmsgDRnChat("NA","Hana Yori");

			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR(randomDrwithTime, true, "3dots");
			koramessagedrpage.deletDiscRoomandWorkSpace(randomDrwithTime,"NA");

			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new Dr and valdiate from partipates end");
		}
	}

	/*@Test(enabled = true, priority = 56)
	public void MDR_TC48_SearchforfileinChatandDR() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");			
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages"); 											
			koramessagedrpage.searchforattachmentinmsgandDR("Hypertext.html");
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagedrpage.searchforattachmentinmsgandDR("Hypertext.html");

			korahomepage.selectTopLeftMenuOption("All Messages"); 											
			koramessagedrpage.searchforattachmentinmsgandDR("ZipperZip.zip");
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.searchforattachmentinmsgandDR("ZipperZip.zip");
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages"); 

			extent.endTest(test);
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Search for file in Chat and DR");
			korahomepage.selectMenuOption("Messages");
			korahomepage.selectTopLeftMenuOption("All Messages"); 
		}
	}
	

	@Test(enabled = true, priority = 57) 
	public void MDR_TC11_TC40_TC41_TC42_TC46_TC58_likeCommentToAPost() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");

			String Messages = DriverSetUp.drdataMap.get("messages");			
			String drcomment = DriverSetUp.drdataMap.get("drcomment");
			String newparticipants = DriverSetUp.drdataMap.get("multipleparticipants");
			test.log(LogStatus.INFO, "Navigation url :" + url);	

			koraloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);	
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");						
			String newDrrandom = "randomDR"+korahomepage.runtimehhmmss();
			koramessagedrpage.createDRwithAccessTypefromMessages
			               ("NA", newDrrandom,newparticipants,"Comment Only");						
			koramessagedrpage.goToGroupAndPerforminWSDR(newDrrandom, false, "NA");
			String drpost="postforLiknComment"+korahomepage.runtimehhmmss();
			koramessagespage.enterYourMessageAs(drpost);					

			koraloginpage.logoutAndReLogin(true,url, korahusername, korahpassword);			
			String ReactedUserName=koraloginpage.getUserDetails();
			korahomepage.selectMenuOption(Messages);			
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			koramessagedrpage.perfromreactionsonPost(newDrrandom, drpost, "Like", false,"NA");			
			koramessagedrpage.perfromreactionsonPost(newDrrandom, drpost, "", true,drcomment);  
						
			koraloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);							
			korahomepage.selectMenuOption(Messages);			
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			koramessagedrpage.validatingreactionsandCommentsonPost(newDrrandom,drpost,ReactedUserName,drcomment);

			koramessagedrpage.goToGroupAndPerforminWSDR(newDrrandom, true, "3dots");
			koramessagedrpage.deletDiscRoomandWorkSpace(newDrrandom,"NA");
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages"); 

			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed at like Comment To A Post");
			korahomepage.selectMenuOption("Messages");
			korahomepage.selectTopLeftMenuOption("All Messages"); 
		}
	} 
	
	@Test(enabled = true, priority = 58) 
	public void MDR_TC67_messageInfo() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");								
			test.log(LogStatus.INFO, "Navigation url :" + url);	

			koraloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);			
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");						
			koramessagedrpage.goToGroupAndPerforminWSDR("DRDelete", false, "");			
			String drpost="postforLiknComment"+korahomepage.runtimehhmmss();
			koramessagespage.enterYourMessageAs(drpost);					
			
			koraloginpage.logoutAndReLogin(true,url, korahusername, korahpassword);			
			String ReactedUserName=koraloginpage.getUserDetails();
			korahomepage.selectMenuOption(Messages);			
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			koramessagedrpage.goToGroupAndPerforminWSDR("DRDelete", false, "");
			koramessagedrpage.perfromreactionsonPost("DRDelete", drpost, "Like", false,"NA");			
			
			koraloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);							
			korahomepage.selectMenuOption(Messages);			
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");					
			koramessagedrpage.perfromreactionsonPost("DRDelete", drpost, "More", false,"NA");
			koramessagedrpage.messagesreadinPostinfandMsginfo(ReactedUserName, true);			
		
			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate messageInfo");
		}
	}*/
}
