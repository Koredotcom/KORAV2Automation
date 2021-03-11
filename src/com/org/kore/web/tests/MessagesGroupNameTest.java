package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.KoraHomePage;
import com.org.kore.web.pages.KoraLoginPage;
import com.org.kore.web.pages.KoraManageConversationPage;
import com.org.kore.web.pages.KoraMessagesPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class MessagesGroupNameTest extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesPage koramessagespage;
	KoraManageConversationPage koramananeconvpage;

	String korausername;
	String korapassword;

	public MessagesGroupNameTest() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute Group test");

		koraloginpage = new KoraLoginPage(remoteDriver);
		korahomepage = new KoraHomePage(remoteDriver);
		koramessagespage = new KoraMessagesPage(remoteDriver);
		koramananeconvpage = new KoraManageConversationPage(remoteDriver);

		korausername = dr.getValue("KORAV2", "KoraV2Web", "Username");
		korapassword = dr.getValue("KORAV2", "KoraV2Web", "Password");

	}

	@Test(enabled = true, priority = 5)
	public void MC_TC13_createAndDeleteGroupWithNoName() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String groupname = null;
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("groupparticipants");
			String grouptext = DriverSetUp.testdataMap.get("groupchat");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korausername, korapassword);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith(newparticipants, true);
			groupname = koramessagespage.enterYourMessageAs(grouptext);
			koramessagespage.verifyGroupCreationTimeline(korausername);
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Conversation");
			koramananeconvpage.removeParticipantsAndClose();
			koramessagespage.enterYourMessageAs("Removed participants");
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Clear Conversation History");
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Leave Conversation");
			koramananeconvpage.clickOn("Leave Conversation", true);
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Delete Conversation");
			koramananeconvpage.clickOn("Delete", true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new group conversation flow");
		}
	}

	@Test(enabled = true, priority = 6)
	public void MC_TC14_TC15_TC16_TC20_TC21_createNewGroupConversation() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("groupparticipants");
			String groupname = DriverSetUp.testdataMap.get("groupname");
			String grouptext = DriverSetUp.testdataMap.get("groupchat");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith(newparticipants, true);
			koramessagespage.createGroupAs(groupname);
			koramessagespage.enterYourMessageAs(grouptext);
			koramessagespage.verifyGroupCreationTimeline(korausername);
			koramessagespage.getGroupTimestamp(groupname);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new group conversation flow");
		}
	}

	@Test(enabled = true, priority = 7)
	public void MC_TC17_TC18_TC19_addMemberToGroup() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("groupname");
			String updatedgroupmems = DriverSetUp.testdataMap.get("updateparticipant");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Conversation");
			koramananeconvpage.manageConversationValidations();
			koramananeconvpage.AddParticipants(updatedgroupmems, false);
			koramessagespage.verifyGroupUpdateTimelines("added");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Add members to group flow");
		}
	}

	@Test(enabled = true, priority = 8)
	public void MC_TC17_renameExistingGroup() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String updatedname;
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String renameto = DriverSetUp.testdataMap.get("renamegroupto");
			String groupname = DriverSetUp.testdataMap.get("groupname");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Conversation");
			updatedname = koramananeconvpage.renameGroupAndClose(renameto);
			koramessagespage.goToGroupAndPerform(updatedname, false, "NA");
			koramessagespage.verifyGroupUpdateTimelines("updated");
			koramessagespage.enterYourMessageAs("Sending text message after updating the Group");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate rename existing group flow");
		}
	}

	@Test(enabled = true, priority = 9)
	public void MC_TC17_removeParticipantsFromGroup() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String renamedgroup = DriverSetUp.testdataMap.get("renamegroupto");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.goToGroupAndPerform(renamedgroup, true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Conversation");
			koramananeconvpage.removeParticipantsAndClose();
			koramessagespage.enterYourMessageAs("Removed participants");
			koramessagespage.verifyGroupUpdateTimelines("removed");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate remove participants flow");
		}
	}

	@Test(enabled = true, priority = 10)
	public void MC_TC25_TC26_clearChatHistory() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String renamedgroup = DriverSetUp.testdataMap.get("renamegroupto");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.goToGroupAndPerform(renamedgroup, true, "3dots");
			koramessagespage.operationsFrom3Dots("Clear Conversation History");
			koramessagespage.checkEmptyScreen();
			koramessagespage.visibilityOfComposeBar(true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate clear conversation history flow");
		}
	}

	@Test(enabled = true, priority = 11)
	public void MC_TC_27_deleteConversation() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String renamedgroup = DriverSetUp.testdataMap.get("renamegroupto");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.goToGroupAndPerform(renamedgroup, true, "3dots");
			koramessagespage.operationsFrom3Dots("Leave Conversation");
			koramananeconvpage.clickOn("Leave Conversation", true);
			koramessagespage.goToGroupAndPerform(renamedgroup, true, "3dots");
			koramessagespage.operationsFrom3Dots("Delete Conversation");
			koramananeconvpage.clickOn("Delete", true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate delete conversation flow");
		}
	}

}
