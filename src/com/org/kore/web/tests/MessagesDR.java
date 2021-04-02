package com.org.kore.web.tests;

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

	}

	@Test(enabled = true, priority = 23)
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

	@Test(enabled = true, priority = 24)
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

	@Test(enabled = true, priority = 25)
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

	@Test(enabled = true, priority = 26)
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


	@Test(enabled = true, priority = 27)
	public void MDR_TC11_likeCommentToAPost() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String drpost = DriverSetUp.drdataMap.get("drpost");
			
			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			String postingUserName=koraloginpage.getUserDetails();
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectBottomLeftMenuWorkSpace(standardwsname);									
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "NA");								
			koramessagespage.enterYourMessageAs(drpost);

			koraloginpage.logoutAndReLogin(true,url, korahusername, korahpassword);			
			String ReactingUserName=koraloginpage.getUserDetails();
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectBottomLeftMenuWorkSpace(standardwsname);			
//			koramessagedrpage.perfromreactionsonPost(standarddrname, drpost, "Like", false);			
			koramessagedrpage.perfromreactionsonPost(standarddrname, drpost, "", true);		
			
			//re login  with User1 and validate comment and Reaction is appiled /effected  of User 2 
			//  //span[@class='truncateText' and text()='Hi']/../..//span[@class='name']
			
			
			
			// Reaction
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}

	
	@Test(enabled = true, priority = 28)
	public void MDR_TC12_13_TC30_validating3DotOptionsFromMidAndRightPanels() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String expWDRmiddle3dotoptions = DriverSetUp.drdataMap
					.get("wexpected3dotoptionsmiddle");
			String expWDRright3dotoptions = DriverSetUp.drdataMap
					.get("wexpected3dotoptionsright");
			
			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.goToGroupAndPerforminWSDR("Sample24", true, "3dots"); 
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", expWDRmiddle3dotoptions,
					"middlePanel");
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", expWDRright3dotoptions,
					"rightPanel");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}
	
	

	@Test(enabled = true, priority = 29)
	public void MDR_TC24_Posthoveroptions() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");			
			String Messages = DriverSetUp.drdataMap.get("messages");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			String standarddrname = DriverSetUp.drdataMap.get("standarddr");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectBottomLeftMenuWorkSpace(standardwsname);			
			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "");
			String validationelmenets[]={"Star","Mute", "Read"};			
			for(String value: validationelmenets )
			{
				koramessagedrpage.verifytheoptionsonDRandperfromAction(standarddrname,value,"SelectNOT");					
			}																			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}
	
	/**
	 * "Create a discussion room from All messages/Discussion room and add user with > Post only (Default post will be given)"
	 * @throws Exception
	 */
	@Test(enabled = true, priority = 29)
	public void MDR_TC37_CreatenewDRwithDifferentAccessTypes() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.drdataMap.get("messages");	
			String newparticipants = DriverSetUp.drdataMap.get("oneparticipant");
			String standardwsname = DriverSetUp.drdataMap.get("standardworkspace");
			String randomDRName= DriverSetUp.drdataMap.get("randomDRname");
						
			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);					
			korahomepage.selectTopLeftMenuOption("All Messages");
//			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagedrpage.createDRwithAccessTypefromMessages(standardwsname,randomDRName,newparticipants,"Comment Only");			
			korahomepage.selectBottomLeftMenuWorkSpace("DoNotDeleteWSAuto");
			koramessagedrpage.goToGroupAndPerforminWSDR(randomDRName, true, "Star");			
			extent.endTest(test);
			
//	?? Discussion Room from Let side Workspace
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
		}
	}

}
