package com.org.kore.web.tests;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.WAHomePage;
import com.org.kore.web.pages.WALoginPage;
import com.org.kore.web.pages.WAMessagesChatsPage;
import com.org.kore.web.pages.WAMessagesDRPage;
import com.org.kore.web.pages.WAWorkspacesPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class MessagesDR extends DriverSetUp {

	WALoginPage waloginpage;
	WAHomePage wahomepage;
	WAMessagesChatsPage wamessageschatspage;
	WAMessagesDRPage wamessagesdrpage;
	WAWorkspacesPage waworkspacepage;

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

		waloginpage = new WALoginPage(remoteDriver);
		wahomepage = new WAHomePage(remoteDriver);
		wamessageschatspage = new WAMessagesChatsPage(remoteDriver);
		waworkspacepage = new WAWorkspacesPage(remoteDriver);
		wamessagesdrpage = new WAMessagesDRPage(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");

		directory = System.getProperty("user.dir");

	}


	/*@Test(enabled = true, priority = 41)  
	public void MDR_TC12_TC13_TC30_validating3DotOptionsFromMidAndRightPanels() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddiscroom = DriverSetUp.drdataMap.get("standarddr"); 
			String expWDRmiddle3dotoptions = DriverSetUp.drdataMap.get("wexpected3dotoptionsmiddle");
			String expWDRright3dotoptions = DriverSetUp.drdataMap.get("wexpected3dotoptionsright");
			test.log(LogStatus.INFO, "Navigation url :" + url);			

			waloginpage.loginToKora(url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.goToGroupAndPerforminWSDR("DRDelete", true, "3dots"); 
			wamessageschatspage.optionsDisplayedOn3Dots("GroupConversation", expWDRmiddle3dotoptions, "middlePanel");
			wamessageschatspage.optionsDisplayedOn3Dots("GroupConversation", expWDRright3dotoptions, "rightPanel");
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate 3DotOptions From MidAndRight Panel");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 42) 
	public void MDR_TC24_DROnhoverOptionsVerification() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			String validationelmenets[] = { "Star", "Mute", "UnRead" };
			for (String value : validationelmenets) {
				wamessagesdrpage.verifytheoptionsonDRandperfromAction(standarddrname, value, "SelectNOT");
			}
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate  On hover Options Verification");
			waloginpage.backToHomePage(url);
		}
	}*/

	/**
	 * "Create a discussion room from All messages/Discussion room and add user
	 * with > Post only (Default post will be given)"
	 * 
	 * @throws Exception
	 *             Pass
	 */
	@Test(enabled = true, priority = 43)  
	public void MDR_TC32_TC33_TC34_TC35_TC36_TC37_createNewDRwithDifferentAccessTypes() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.loginToKora(url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages"); 
			String newRandomDr = "random"+wahomepage.runtimehhmmss();
			wamessagesdrpage.createDRwithAccessTypefromMessages(standardwsname, newRandomDr,
					newparticipants, "Comment Only");			
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessagesdrpage.goToGroupAndPerforminWSDR(newRandomDr, true, "Star");

			// To Create DR From Bottom Left Panel
			String newRandomDr2 = "random"+wahomepage.runtimehhmmss();
			wahomepage.selectBottomLeftMenuWorkSpace(standardwsname);
			wamessagesdrpage.createDRwithAccessTypefromMessages(standardwsname, newRandomDr2,
					newparticipants, "Full Access");			

			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessagesdrpage.goToGroupAndPerforminWSDR(newRandomDr2, true, "Star");

			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.goToGroupAndPerforminWSDR(newRandomDr, true, "3dots");
			wamessagesdrpage.deletDiscRoomandWorkSpace(newRandomDr,"NA");

			wamessagesdrpage.goToGroupAndPerforminWSDR(newRandomDr2, true, "3dots");
			wamessagesdrpage.deletDiscRoomandWorkSpace(newRandomDr2,"NA");

			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create New DR with Different AccessTypes");
			waloginpage.backToHomePage(url);
		}
	}

	/**
	 * TC_64Delete Discussion Room with WorkSpace, without WorkSpace from
	 * Messages Updated
	 */
/*	@Test(enabled = true, priority = 44)  
	public void MDR_TC64_deleteWorkSpacefromMessages() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms"); 
			String newDrrandom = "random"+wahomepage.runtimehhmmss();
			wamessagesdrpage.createDRwithAccessTypefromMessages(standardwsname, newDrrandom,
					newparticipants, "Comment Only");
			wahomepage.selectTopLeftMenuOption("Discussion Rooms"); 
			wamessagesdrpage.goToGroupAndPerforminWSDR(newDrrandom, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Delete Discussion");
			wahomepage.clickOn("Delete", true);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.valdiatedeletedMsgorDR(newDrrandom);			
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate delete WorkSpace from Messages");
			waloginpage.backToHomePage(url);
		}
	}

	*//**
	 * Editing a post in Discussion Room Also validates
	 * Edit,Forward,Reminder,Post Info,Delete options displayed in 3 dots to a
	 * post
	 *//*
	@Test(enabled = true, priority = 45)  
	public void MDR_TC53_TC56_EditaPost() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			String expWDpost3dotoptions = DriverSetUp.drdataMap.get("drexpected3dotsforPostinrightpanel");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			String newDrrandom = wahomepage.runtimehhmmss();
			wamessagesdrpage.EditingPostinDiscussionRoom(standarddrname, "Post" + newDrrandom,
					"Editing Post" + newDrrandom, expWDpost3dotoptions);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Edit post");
			waloginpage.backToHomePage(url);
		}
	}

	*//**
	 * Forward post to new conversation , Existing and DR
	 *//*
	@Test(enabled = true, priority = 46) 
	public void MDR_TC49_TC54_TC55_TC57_forwardPostToGroupDrAndNewconv() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");			
			test.log(LogStatus.INFO, "Navigation url :" + url);			

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			String forwardtomemebers = "hana@koraqa1.com";
			String searchwithEmailAddress="dileep@koraqa1.com";
			wamessagesdrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			String post = "forwardingpost" + wahomepage.runtimehhmmss();
			wamessageschatspage.enterYourMessageAs(post);
			wamessagesdrpage.movetoaPostandClickon3dots(standarddrname, post, true);
			wamessagesdrpage.forwardPosttonewconvorexisting(post, forwardtomemebers, "NA", "NA");

			wamessagesdrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			wamessagesdrpage.movetoaPostandClickon3dots(standarddrname, post, true);			
			wamessagesdrpage.forwardPosttonewconvorexisting(post, "NA", "DRDelete", "NA");

			*//** when we search with Email address application not showing results temporarily .
			wamessagesdrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			wamessagesdrpage.movetoaPostandClickon3dots(standarddrname, post, true);
			wamessagesdrpage.forwardPosttonewconvorexisting(post, "NA", "NA", searchwithEmailAddress);
			 *//*
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate forward Post To Group DR And New  Conversation");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 47)
	public void MDR_TC25_TC26_TC28_TC29_starDrandMuteDrandvaldiation() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");			
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");			
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);			
			wahomepage.selectTopLeftMenuOption("Discussion Rooms"); 
			String DNDStarANDOther = "randomDR" +wahomepage.runtimehhmmss();

			wamessagesdrpage.createDRwithAccessTypefromMessages("NA",DNDStarANDOther, newparticipants, "Post Only");			
			wamessagesdrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, false, "");
			wamessagesdrpage.selectoptionsfrom3dotsinRightPanelinDR(DNDStarANDOther, "Star","N/A");
			wahomepage.selectTopLeftMenuOption("Starred");
			wamessagesdrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, false, "");

			wamessagesdrpage.selectoptionsfrom3dotsinRightPanelinDR(DNDStarANDOther, "Unstar","N/A");
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wahomepage.selectTopLeftMenuOption("Starred");
			wamessagesdrpage.valdiatedeletedMsgorDR(DNDStarANDOther);

			wahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			wamessagesdrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, false, "");
			wamessagesdrpage.selectoptionsfrom3dotsinRightPanelinDR(DNDStarANDOther, "Mute","4 hours");
			wahomepage.selectTopLeftMenuOption("Muted");
			wamessagesdrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, false, "");			
			wamessagesdrpage.selectoptionsfrom3dotsinRightPanelinDR(DNDStarANDOther, "Unmute","");			
			wahomepage.selectTopLeftMenuOption("Muted"); 
			wamessagesdrpage.valdiatedeletedMsgorDR(DNDStarANDOther);	

			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.goToGroupAndPerforminWSDR(DNDStarANDOther, true, "3dots");
			wamessagesdrpage.deletDiscRoomandWorkSpace(DNDStarANDOther,"NA");
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate star Discussion Room and Mute DR ");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 48) 
	public void MDR_TC14_TC15_TC71_LeaveandDeleteDRfromManageRoom() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms"); 
			String randomDrwithTime = "randomDR" +wahomepage.runtimehhmmss();
			wamessagesdrpage.createDRwithAccessTypefromMessages(standardwsname,randomDrwithTime, newparticipants, "Post Only");								
			wamessagesdrpage.goToGroupAndPerforminWSDR(randomDrwithTime, false, "");						
			wamessagesdrpage.selectoptionsfrom3dotsinRightPanelinDR(randomDrwithTime, "Manage Room","N/A");						
			wamessagesdrpage.rename_LeaveRoom_DeleteRoom("Rename","Leave");
			wamessagesdrpage.valdiatedeletedMsgorDR("Rename"+randomDrwithTime);

			String randomDrwithTime2 = "randomDR" +wahomepage.runtimehhmmss();
			wamessagesdrpage.createDRwithAccessTypefromMessages(standardwsname,randomDrwithTime2, newparticipants, "Post Only");								
			wamessagesdrpage.goToGroupAndPerforminWSDR(randomDrwithTime2, false, "");						
			wamessagesdrpage.selectoptionsfrom3dotsinRightPanelinDR(randomDrwithTime2, "Manage Room","N/A");						
			wamessagesdrpage.rename_LeaveRoom_DeleteRoom("N/A","Delete");
			wamessagesdrpage.valdiatedeletedMsgorDR(randomDrwithTime2);			

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to  Leave and Delete DR from ManageRoom");
			waloginpage.backToHomePage(url);
		}
	}

	*//**
	 * Editing a post in Discussion Room Also validates
	 * Edit,Forward,Reminder,Post Info,Delete options displayed in 3 dots to a
	 * post
	 *//*
	@Test(enabled = true, priority = 49)  
	public void MDR_TC45_TC52_TC77_DeletePostandCancelnewDR() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");			
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");			
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");						
			String newDRrandom = "random"+wahomepage.runtimehhmmss();						
			wamessagesdrpage.cancledrcreationanddiscardmsg(newDRrandom,newparticipants);		
			String drpost="postforLiknComment"+wahomepage.runtimehhmmss();
			wamessagesdrpage.goToGroupAndPerforminWSDR("DRDelete", false, "");
			wamessageschatspage.enterYourMessageAs(drpost);
			wamessagesdrpage.deletepostandreactcount("DRDelete", drpost);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Delete Post and Cancel new DR");
			waloginpage.backToHomePage(url);
		}
	}

	*//**
	 * At Mentions in posts users should display the users who are part of the
	 * room
	 *//*
	@Test(enabled = true, priority = 50) 
	public void MDR_TC7_TC59_TC60_TC61_atmentionUsersinDr() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");			
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.goToGroupAndPerforminWSDR(standarddrname, false, "NA");
			wamessagesdrpage.selectoptionsfrom3dotsinRightPanelinDR(standarddrname, "Manage Room","N/A");
			List<String> membersingroup=wamessagesdrpage.addandremovepeoplefromdiscussionRoom(standarddrname,"N/A","N/A");
			String atmentionmsg = "at_mention_Automation_post"+wahomepage.runtimehhmmss();
			wamessagesdrpage.atMentionValidationinDR(membersingroup,"james@koraqa1.com", atmentionmsg,"post");

			String drpost="postforComment"+wahomepage.runtimehhmmss();
			wamessageschatspage.enterYourMessageAs(drpost);
			wamessagesdrpage.perfromreactionsonPost(standarddrname, drpost, "", true,"NA");
			wamessagesdrpage.atMentionsinComments(standarddrname, drpost, "@");		
			String atmentionincomment = "atMentioninComment"+wahomepage.runtimehhmmss();
			wamessagesdrpage.atMentionValidationinDR(membersingroup,"hana@koraqa1.com", atmentionincomment,"comment");

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate atmention Users in Discussion Room");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 51) 
	public void MDR_TC39_TC72_PostviaEmailToggle() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			test.log(LogStatus.INFO, "Navigation url :" + url);	

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms"); 			
			wamessagesdrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");								
			wamessagesdrpage.selectoptionsfrom3dotsinRightPanelinDR(standarddrname, "Manage Room","N/A");
			wamessagesdrpage.validatePostviaemailandAllmemebrsemail();						
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to  validate Postvia Email Toggle");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 52) 
	public void MDR_TC66_TC17_TC21_TC70_creationofRoomwithoutWS() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages"); 
			String randomDrwithTime = "randomDR" +wahomepage.runtimehhmmss();
			wamessagesdrpage.createDRwithAccessTypefromMessages(standardwsname,randomDrwithTime, newparticipants, "Post Only");			
			wahomepage.selectTopLeftMenuOption("All Messages");
			Thread.sleep(4000);
			wamessagesdrpage.goToGroupAndPerforminWSDR(randomDrwithTime, false, "");						
			wamessagesdrpage.selectoptionsfrom3dotsinRightPanelinDR(randomDrwithTime, "Manage Room","N/A");
			wamessagesdrpage.addandremovepeoplefromdiscussionRoom(randomDrwithTime,"alexander@koraqa1.com",newparticipants);

			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.goToGroupAndPerforminWSDR(randomDrwithTime, true, "3dots");
			wamessagesdrpage.deletDiscRoomandWorkSpace(randomDrwithTime,"NA");

			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to creation of Room without Work Space");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 53)  
	public void MDR_TC23_TC69_TC76_TC78_createnewWSfromDRandaddDRs() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");			
			test.log(LogStatus.INFO, "Navigation url :" + url);						

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			String randomWS = "WS" +wahomepage.runtimehhmmss();
			String randomDrwithTime = "randomDR" +wahomepage.runtimehhmmss();
			String randomDrwithTime2 = "randomDR" +wahomepage.runtimehhmmss()+"_2";

			wamessagesdrpage.createDRwithWS(randomWS,randomDrwithTime, newparticipants);
			wamessagesdrpage.selectDRBasedonWSname(randomWS,randomDrwithTime);
			wamessagesdrpage.createDRwithAccessTypefromMessages(randomWS, randomDrwithTime2,newparticipants, "Comment Only");
			waworkspacepage.selectWorkspace(randomWS);
			
			wamessagesdrpage.goToGroupAndPerforminWSDR(randomDrwithTime, true, "3dots");
			wamessagesdrpage.deletDiscRoomandWorkSpace(randomDrwithTime,"NA");

			wamessagesdrpage.goToGroupAndPerforminWSDR(randomDrwithTime2, true, "3dots");
			wamessagesdrpage.deletDiscRoomandWorkSpace(randomDrwithTime2,"NA");

			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.deletDiscRoomandWorkSpace("NA",randomWS);
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Create new Work Spae from DR and add more Discussion Rooms");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 54) 
	public void MDR_TC62_TC73_TC74_TC75_addnewparticiapantstoexistingDr() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			String aditionalmember1="alexander@koraqa1.com";
			String aditionalmember2="dileep@koraqa1.com";			

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			String randomDrwithTime = "randomDR" +wahomepage.runtimehhmmss();				
			wamessagesdrpage.createDRwithAccessTypefromMessages(standardwsname, randomDrwithTime,
					newparticipants, "Comment Only");	

			wamessagesdrpage.addnewmemebrswithaccesstype(randomDrwithTime,aditionalmember1,aditionalmember2,"Comment only");			
			wamessagesdrpage.addnewmemebrswithaccesstypeinManageRoom(randomDrwithTime,aditionalmember2,"Full Access");

			wamessagesdrpage.goToGroupAndPerforminWSDR(randomDrwithTime, true, "3dots");
			wamessagesdrpage.deletDiscRoomandWorkSpace(randomDrwithTime,"NA");					
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Create new Work Spae from DR and add more Discussion Rooms");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 55)
	public void MDR_TC20_TC79_TC80_createNEWDRandvalidatefrompartcipantsend() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String newparticipants = "hana@koraqa1.com";
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages"); 
			String randomDrwithTime = "randomDR" +wahomepage.runtimehhmmss();
			wamessagesdrpage.createDRwithAccessTypefromMessages(standardwsname,randomDrwithTime, newparticipants, "Post Only");			
			wahomepage.selectTopLeftMenuOption("All Messages");						
			wamessagesdrpage.searchforDRorchatfromallmsgDRnChat(randomDrwithTime,"NA");	
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessagesdrpage.searchforDRorchatfromallmsgDRnChat("NA","QA Pride");
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessagesdrpage.searchforDRorchatfromallmsgDRnChat("NA","Hana Yori");

			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.goToGroupAndPerforminWSDR(randomDrwithTime, true, "3dots");
			wamessagesdrpage.deletDiscRoomandWorkSpace(randomDrwithTime,"NA");

			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new Dr and valdiate from partipates end");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 56)
	public void MDR_TC48_SearchforfileinChatandDR() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");			
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages"); 											
			wamessagesdrpage.searchforattachmentinmsgandDR("Hypertext.html");
			wahomepage.selectTopLeftMenuOption("Chats");
			wamessagesdrpage.searchforattachmentinmsgandDR("Hypertext.html");

			wahomepage.selectTopLeftMenuOption("All Messages"); 											
			wamessagesdrpage.searchforattachmentinmsgandDR("ZipperZip.zip");
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessagesdrpage.searchforattachmentinmsgandDR("ZipperZip.zip");
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages"); 

			extent.endTest(test);
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Search for file in Chat and DR");
			waloginpage.backToHomePage(url);
		}
	}
	

	@Test(enabled = true, priority = 57) 
	public void MDR_TC11_TC40_TC41_TC42_TC46_TC58_likeCommentToAPost() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");

			String Messages = DriverSetUp.drdataMap.get("messages");			
			String drcomment = DriverSetUp.drdataMap.get("drcomment");
			String newparticipants = DriverSetUp.drdataMap.get("multipleparticipants");
			test.log(LogStatus.INFO, "Navigation url :" + url);	

			waloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);	
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");						
			String newDrrandom = "randomDR"+wahomepage.runtimehhmmss();
			wamessagesdrpage.createDRwithAccessTypefromMessages
			               ("NA", newDrrandom,newparticipants,"Comment Only");						
			wamessagesdrpage.goToGroupAndPerforminWSDR(newDrrandom, false, "NA");
			String drpost="postforLiknComment"+wahomepage.runtimehhmmss();
			wamessageschatspage.enterYourMessageAs(drpost);					

			waloginpage.logoutAndReLogin(true,url, korahusername, korahpassword);			
			String ReactedUserName=waloginpage.getUserDetails();
			wahomepage.selectMenuOption(Messages);			
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			wamessagesdrpage.perfromreactionsonPost(newDrrandom, drpost, "Like", false,"NA");			
			wamessagesdrpage.perfromreactionsonPost(newDrrandom, drpost, "", true,drcomment);  
						
			waloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);							
			wahomepage.selectMenuOption(Messages);			
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			wamessagesdrpage.validatingreactionsandCommentsonPost(newDrrandom,drpost,ReactedUserName,drcomment);

			wamessagesdrpage.goToGroupAndPerforminWSDR(newDrrandom, true, "3dots");
			wamessagesdrpage.deletDiscRoomandWorkSpace(newDrrandom,"NA");
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages"); 

			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed at like Comment To A Post");
			waloginpage.backToHomePage(url);		}
	} 
	
	@Test(enabled = true, priority = 58) 
	public void MDR_TC67_messageInfo() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");								
			test.log(LogStatus.INFO, "Navigation url :" + url);	

			waloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);			
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");						
			wamessagesdrpage.goToGroupAndPerforminWSDR("DRDelete", false, "");			
			String drpost="postforLiknComment"+wahomepage.runtimehhmmss();
			wamessageschatspage.enterYourMessageAs(drpost);					
			
			waloginpage.logoutAndReLogin(true,url, korahusername, korahpassword);			
			String ReactedUserName=waloginpage.getUserDetails();
			wahomepage.selectMenuOption(Messages);			
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			wamessagesdrpage.goToGroupAndPerforminWSDR("DRDelete", false, "");
			wamessagesdrpage.perfromreactionsonPost("DRDelete", drpost, "Like", false,"NA");			
			
			waloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);							
			wahomepage.selectMenuOption(Messages);			
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");					
			wamessagesdrpage.perfromreactionsonPost("DRDelete", drpost, "More", false,"NA");
			wamessagesdrpage.messagesreadinPostinfandMsginfo(ReactedUserName, true);			
		
			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate messageInfo");
			waloginpage.backToHomePage(url);
		}
	}*/
}
