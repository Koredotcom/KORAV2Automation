package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
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
//	PageBase pagebase;

	String korajusername;
	String korajpassword;

	String korahusername;
	String korahpassword;

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
	//	pagebase = new PageBase(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");

	}

	
	@Test(enabled = false, priority = 28)
	public void MDR_TC1_TC2_TC3_TC65_createNewWorkspaceAndDelete() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.drdataMap.get("workspaces");
			String workspacename = DriverSetUp.drdataMap.get("workspacename123");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.createNewWorkspaceAndCheckDefault(workspacename);
			koraworkspacepage.clickOnWorkspace3Dots(workspacename);
			koraworkspacepage.operationsFromWS3Dots(workspacename, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new workspace and validations");
		}
	}

	@Test(enabled = false, priority = 29)
	public void MDR_TC4_TC5_inviteMembersToWorkspaceAndManage() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.drdataMap.get("workspaces");
			String workspaceauto = DriverSetUp.drdataMap.get("workspaceauto");
			String invitemems = DriverSetUp.drdataMap.get("workspacemems");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.createNewWorkspaceAs(workspaceauto);
			koraworkspacepage.workspaceDirectInvite(invitemems);
			koraworkspacepage.clickOnWorkspace3Dots(workspaceauto);
			koraworkspacepage.operationsFromWS3Dots(workspaceauto, "Manage");
			koramessagespage.clickOn("Members", true);
			koraworkspacepage.validateRecentInvites(invitemems);
			koraworkspacepage.clickOnWorkspace3Dots(workspaceauto);
			koraworkspacepage.operationsFromWS3Dots(workspaceauto, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to invite members to workspace");
		}
	}

	@Test(enabled = false, priority = 30)
	public void MDR_TC8_filterByWorkspace() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.drdataMap.get("workspaces");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String workspacename = DriverSetUp.drdataMap.get("workspacename8");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.createNewWorkspaceAs(workspacename);
			korahomepage.selectMenuOption(Messages);
			korahomepage.getActiveOptionFromLeftNav("All Messages");
			koraworkspacepage.selectWorkspace(workspacename);
			koramessagespage.getChatHeaderName();
			String wsname = koramessagespage.enterYourMessageAs("My post in " + workspacename);
			koraworkspacepage.compareActualExpected(wsname, "General", "Default selected workspace is : ");
			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.clickOnWorkspace3Dots(workspacename);
			koraworkspacepage.operationsFromWS3Dots(workspacename, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}

	@Test(enabled = false, priority = 31)
	public void MDR_TC9_TC10_DefaultDRAndTimeline() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.drdataMap.get("workspaces");
			String workspacename = DriverSetUp.drdataMap.get("workspacename910");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.createNewWorkspaceAs(workspacename);
			koraworkspacepage.selectDefaultDR();
			koramessagespage.visibilityOfComposeBar(true);
			koramessagespage.verifyGroupCreationTimeline(korajusername);
			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.clickOnWorkspace3Dots(workspacename);
			koraworkspacepage.operationsFromWS3Dots(workspacename, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate default DR and Timeline (General name might got changed)");
		}
	}



	@Test(enabled = true, priority = 32)
	public void MDR_TC11_TC41_TC42_TC46_TC58_likeCommentToAPost() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			String Messages = DriverSetUp.drdataMap.get("messages");			
			String drcomment = DriverSetUp.drdataMap.get("drcomment");			
			test.log(LogStatus.INFO, "Navigation url :" + url);	
			
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");									
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "NA");
			String drpost="postforLiknComment"+korahomepage.runtimehhmmss();
			koramessagespage.enterYourMessageAs(drpost);						
			
			koraloginpage.logoutAndReLogin(true,url, korahusername, korahpassword);			
			String ReactedUserName=koraloginpage.getUserDetails();
			korahomepage.selectMenuOption(Messages);			
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			koramessagedrpage.perfromreactionsonPost(standarddrname, drpost, "Like", false,"");			
			koramessagedrpage.perfromreactionsonPost(standarddrname, drpost, "", true,drcomment);		

			koraloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);							
			korahomepage.selectMenuOption(Messages);			
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");			
			koramessagedrpage.validatingreactionsandCommentsonPost(standarddrname,drpost,ReactedUserName,drcomment);
			
			koramessagedrpage.perfromreactionsonPost(standarddrname, drpost, "More", false,"");
			koramessagedrpage.messagesreadinPostinfandMsginfo(ReactedUserName);
			
						
			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	} 


	@Test(enabled = true, priority = 33)
	public void MDR_TC12_13_TC30_validating3DotOptionsFromMidAndRightPanels() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddiscroom = DriverSetUp.drdataMap.get("standarddr"); // ??
			String expWDRmiddle3dotoptions = DriverSetUp.drdataMap.get("wexpected3dotoptionsmiddle");
			String expWDRright3dotoptions = DriverSetUp.drdataMap.get("wexpected3dotoptionsright");
			test.log(LogStatus.INFO, "Navigation url :" + url);			
			
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddiscroom, true, "3dots"); // ??
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", expWDRmiddle3dotoptions, "middlePanel");
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", expWDRright3dotoptions, "rightPanel");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}

	@Test(enabled = true, priority = 34)
	public void MDR_TC24_hoveronaDRandverifyoptions() throws Exception {
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
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}
		
	/**
	 * "Create a discussion room from All messages/Discussion room and add user
	 * with > Post only (Default post will be given)"
	 * 
	 * @throws Exception
	 *             Pass
	 */
	@Test(enabled = true, priority = 35)
	public void MDR_TC34_TC37_CreatenewDRwithDifferentAccessTypes() throws Exception {
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
			// To Create DR from All messages and Discussion Room
			// Discussion Rooms || All Messages
			korahomepage.selectTopLeftMenuOption("All Messages"); 
			String exeTimeHMS1 = "random"+korahomepage.runtimehhmmss();
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname, exeTimeHMS1,
					newparticipants, "Comment Only");			
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagedrpage.goToGroupAndPerforminWSDR(exeTimeHMS1, true, "Star");

			// To Create DR From Bottom Left Panel
			String exeTimeHMS2 = "random"+korahomepage.runtimehhmmss();
			korahomepage.selectBottomLeftMenuWorkSpace(standardwsname);
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname, exeTimeHMS2,
					newparticipants, "Full Access");			
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagedrpage.goToGroupAndPerforminWSDR(exeTimeHMS2, true, "Star");
			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}
		
	/**
	 * TC_64Delete Discussion Room with WorkSpace, without WorkSpace from
	 * Messages Updated
	 */
	@Test(enabled = true, priority = 36)
	public void MDR_TC64_DeleteWorkSpacefromMessages() throws Exception {
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
			String exeTimeHMS1 = "random"+korahomepage.runtimehhmmss();
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname, exeTimeHMS1,
					newparticipants, "Comment Only");
			korahomepage.selectTopLeftMenuOption("Discussion Rooms"); // Discussion Rooms || All Messages
			koramessagedrpage.goToGroupAndPerforminWSDR(exeTimeHMS1, true, "3dots");
			koramessagespage.operationsFrom3Dots("Delete Discussion");
			korahomepage.clickOn("Delete", true);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.valdiatedeletedMsgorDR(exeTimeHMS1);
			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}

	/**
	 * Editing a post in Discussion Room Also validates
	 * Edit,Forward,Reminder,Post Info,Delete options displayed in 3 dots to a
	 * post
	 */
	@Test(enabled = true, priority = 37)
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
			String exeTimeHMS1 = korahomepage.runtimehhmmss();
			koramessagedrpage.EditingPostinDiscussionRoom(standarddrname, "Post" + exeTimeHMS1,
					"Editing Post" + exeTimeHMS1, expWDpost3dotoptions);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}

	/**
	 * At Mentions in posts users should display the users who are part of the
	 * room
	 */
	@Test(enabled = true, priority = 38)
	public void MDR_TC59_atmentionUsersinDr() throws Exception {

		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "NA");
			koramessagedrpage.atMentionValidationinDR();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate shuffling of first group icon");
		}
	}

	/**
	 * Forward post to new conversation , Existing and DR
	 */
	@Test(enabled = true, priority = 39)
	public void MDR_TC_54_57_forwardPosttoToGroupdnDrandnewconv() throws Exception {

		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			String expWDpost3dotoptions = DriverSetUp.drdataMap.get("drexpected3dotsforPostinrightpanel");

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
					
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			koramessagedrpage.movetoaPostandClickon3dots(standarddrname, post, true);
			koramessagedrpage.forwardPosttonewconvorexisting(post, "NA", "NA", searchwithEmailAddress);
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate shuffling of first group icon");
		}
	}
	
	@Test(enabled = true, priority = 40)
	public void MDR_TC66_TC17_TC21_CreationofRoomwithoutWS_MangeMembers_ValidatingDRinAllmessages() throws Exception {
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
			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}

	@Test(enabled = true, priority = 41)
	public void MDR_TC25_TC26_StarDrandMuteDrandvaldiation() throws Exception {
		try {
			
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String DNDStarANDOther = DriverSetUp.drdataMap.get("standarddrForOther");			
			test.log(LogStatus.INFO, "Navigation url :" + url);
						
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms"); 				
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
			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}	
}
