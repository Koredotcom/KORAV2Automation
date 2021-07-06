package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.KoraHomePage;
import com.org.kore.web.pages.KoraLoginPage;
import com.org.kore.web.pages.KoraMessagesChatsPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class MessagesChatGroups extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesChatsPage koramessagespage;

	String korajusername;
	String korajpassword;

	String korahusername;
	String korahpassword;

	public MessagesChatGroups() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute Group test");

		koraloginpage = new KoraLoginPage(remoteDriver);
		korahomepage = new KoraHomePage(remoteDriver);
		koramessagespage = new KoraMessagesChatsPage(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");

	}

	@Test(enabled = true, priority = 24)
	public void MC_TC13_createAndDeleteGroupWithNoName() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String groupname, groupcount = null;
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("groupparticipants");
			String grouptext = DriverSetUp.testdataMap.get("groupchat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("Chat", newparticipants, true);
			groupname = koramessagespage.enterYourMessageAs(grouptext);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			
			koramessagespage.goToGroupAndPerform(groupname, false, "NA");
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Chat");
			koramessagespage.removeParticipantsAndClose();
			groupname = koramessagespage.enterYourMessageAs("Removed participants");
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Clear Chat History");
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Leave Chat");
			koramessagespage.clickOn("Leave", true);
			korahomepage.selectTopLeftMenuOption("All Messages");
			groupname = koramessagespage.getChatHeaderNameorCount(true);
			koramessagespage.goToGroupAndPerform(groupname, true, "DeleteGroup");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Failed to validate group creation with no name and delete the same conversation flow");
		}
	}

	@Test(enabled = true, priority = 25)
	public void MC_TC14_TC15_TC16_TC20_TC21_createNewGroupWithName() throws Exception {
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

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			koramessagespage.createGroupAs(groupname);
			koramessagespage.enterYourMessageAs(grouptext);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(groupname, false, "NA");
			koramessagespage.verifyGroupCreationTimeline(korajusername);
			koramessagespage.getGroupTimestamp(groupname);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new group conversation flow");
		}
	}

	@Test(enabled = true, priority = 26)
	public void MC_TC17_TC18_TC19_addMemberToExistingGroup() throws Exception {
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
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Chat");
			koramessagespage.manageConversationValidations();
			koramessagespage.AddParticipantsFromManage(updatedgroupmems, false);
			koramessagespage.verifyGroupUpdateTimelines("added");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Add members to group flow");
		}
	}

	@Test(enabled = true, priority = 27)
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
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Chat");
			updatedname = koramessagespage.renameGroupAndClose(renameto);
			koramessagespage.goToGroupAndPerform(updatedname, false, "NA");
			koramessagespage.verifyGroupUpdateTimelines("updated");
			koramessagespage.enterYourMessageAs("Sending text message after updating the Group");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate rename existing group flow");
		}
	}

	@Test(enabled = true, priority = 28)
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
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(renamedgroup, true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Chat");
			koramessagespage.removeParticipantsAndClose();
			koramessagespage.enterYourMessageAs("Removed participants");
			koramessagespage.verifyGroupUpdateTimelines("removed");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate remove participants flow");
		}
	}

	@Test(enabled = true, priority = 39)
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
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(renamedgroup, true, "3dots");
			koramessagespage.operationsFrom3Dots("Clear Chat History");
			koramessagespage.verifyGroupUpdateTimelines("cleared");
			koramessagespage.checkEmptyScreen(renamedgroup, "Removed participants");
			koramessagespage.visibilityOfComposeBar(true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate clear conversation history flow");
		}
	}

	@Test(enabled = true, priority = 30)
	public void MC_TC_27_deleteChatsGroup() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String renamedgroup = DriverSetUp.testdataMap.get("renamegroupto");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(renamedgroup, true, "3dots");
			koramessagespage.operationsFrom3Dots("Leave Chat");
			koramessagespage.clickOn("Leave", true);
			korahomepage.selectTopLeftMenuOption("All Messages");
			renamedgroup = koramessagespage.getChatHeaderNameorCount(true);
			koramessagespage.goToGroupAndPerform(renamedgroup, true, "DeleteGroup");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate delete conversation flow");
		}
	}

}
