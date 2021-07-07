package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.WAHomePage;
import com.org.kore.web.pages.WALoginPage;
import com.org.kore.web.pages.WAMessagesChatsPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class MessagesChatGroups extends DriverSetUp {

	WALoginPage waloginpage;
	WAHomePage wahomepage;
	WAMessagesChatsPage wamessageschatspage;

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

		waloginpage = new WALoginPage(remoteDriver);
		wahomepage = new WAHomePage(remoteDriver);
		wamessageschatspage = new WAMessagesChatsPage(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");

	}

	@Test(enabled = true, priority = 24)
	public void MC_TC13_createAndDeleteGroupWithNoName() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String groupname, groupcount = null;
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("groupparticipants");
			String grouptext = DriverSetUp.testdataMap.get("groupchat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.loginToKora(url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("Chat", newparticipants, true);
			groupname = wamessageschatspage.enterYourMessageAs(grouptext);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			
			wamessageschatspage.goToGroupAndPerform(groupname, false, "NA");
			wamessageschatspage.goToGroupAndPerform(groupname, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Manage Chat");
			wamessageschatspage.removeParticipantsAndClose();
			groupname = wamessageschatspage.enterYourMessageAs("Removed participants");
			wamessageschatspage.goToGroupAndPerform(groupname, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Clear Chat History");
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(groupname, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Leave Chat");
			wamessageschatspage.clickOn("Leave", true);
			wahomepage.selectTopLeftMenuOption("All Messages");
			groupname = wamessageschatspage.getChatHeaderNameorCount(true);
			wamessageschatspage.goToGroupAndPerform(groupname, true, "DeleteGroup");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Failed to validate group creation with no name and delete the same conversation flow");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 25)
	public void MC_TC14_TC15_TC16_TC20_TC21_createNewGroupWithName() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("groupparticipants");
			String groupname = DriverSetUp.testdataMap.get("groupname");
			String grouptext = DriverSetUp.testdataMap.get("groupchat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			wamessageschatspage.createGroupAs(groupname);
			wamessageschatspage.enterYourMessageAs(grouptext);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(groupname, false, "NA");
			wamessageschatspage.verifyGroupCreationTimeline(korajusername);
			wamessageschatspage.getGroupTimestamp(groupname);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new group conversation flow");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 26)
	public void MC_TC17_TC18_TC19_addMemberToExistingGroup() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("groupname");
			String updatedgroupmems = DriverSetUp.testdataMap.get("updateparticipant");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(groupname, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Manage Chat");
			wamessageschatspage.manageConversationValidations();
			wamessageschatspage.AddParticipantsFromManage(updatedgroupmems, false);
			wamessageschatspage.verifyGroupUpdateTimelines("added");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Add members to group flow");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 27)
	public void MC_TC17_renameExistingGroup() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String updatedname;
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String renameto = DriverSetUp.testdataMap.get("renamegroupto");
			String groupname = DriverSetUp.testdataMap.get("groupname");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(groupname, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Manage Chat");
			updatedname = wamessageschatspage.renameGroupAndClose(renameto);
			wamessageschatspage.goToGroupAndPerform(updatedname, false, "NA");
			wamessageschatspage.verifyGroupUpdateTimelines("updated");
			wamessageschatspage.enterYourMessageAs("Sending text message after updating the Group");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate rename existing group flow");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 28)
	public void MC_TC17_removeParticipantsFromGroup() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String renamedgroup = DriverSetUp.testdataMap.get("renamegroupto");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(renamedgroup, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Manage Chat");
			wamessageschatspage.removeParticipantsAndClose();
			wamessageschatspage.enterYourMessageAs("Removed participants");
			wamessageschatspage.verifyGroupUpdateTimelines("removed");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate remove participants flow");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 29)
	public void MC_TC25_TC26_clearChatHistory() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String renamedgroup = DriverSetUp.testdataMap.get("renamegroupto");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(renamedgroup, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Clear Chat History");
			wamessageschatspage.verifyGroupUpdateTimelines("cleared");
			wamessageschatspage.checkEmptyScreen(renamedgroup, "Removed participants");
			wamessageschatspage.visibilityOfComposeBar(true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate clear conversation history flow");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 30)
	public void MC_TC_27_deleteChatsGroup() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String renamedgroup = DriverSetUp.testdataMap.get("renamegroupto");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(renamedgroup, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Leave Chat");
			wamessageschatspage.clickOn("Leave", true);
			wahomepage.selectTopLeftMenuOption("All Messages");
			renamedgroup = wamessageschatspage.getChatHeaderNameorCount(true);
			wamessageschatspage.goToGroupAndPerform(renamedgroup, true, "DeleteGroup");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate delete conversation flow");
			waloginpage.backToHomePage(url);
		}
	}

}
