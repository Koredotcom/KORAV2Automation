package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.WAHomePage;
import com.org.kore.web.pages.WALoginPage;
import com.org.kore.web.pages.WAMessagesChatsPage;
import com.org.kore.web.pages.WAMessagesDRPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class MessagesChat121 extends DriverSetUp {

	WALoginPage waloginpage;
	WAHomePage wahomepage;
	WAMessagesChatsPage wamessageschatspage;
	WAMessagesDRPage wamessagesdrpage;

	String korajusername;
	String korajpassword;

	String korahusername;
	String korahpassword;

	static String user = null;

	public MessagesChat121() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute 121 test");

		waloginpage = new WALoginPage(remoteDriver);
		wahomepage = new WAHomePage(remoteDriver);
		wamessageschatspage = new WAMessagesChatsPage(remoteDriver);
		wamessagesdrpage = new WAMessagesDRPage(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");
	}

	@Test(enabled = true, priority = 1)
	public void MC_TC2_loginToWorkAssist() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

		//	waloginpage.launchw3(url, korajusername, korajpassword);
			waloginpage.loginToKora(url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate recent contact suggestions");
			waloginpage.backToHomePage(url);
		}
	}
	
	/*@Test(enabled = true, priority = 2)
	public void MC_TC3_TC4_TC5_verifyRecentSuggestions() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.checkDefaultFocus_Recents();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate recent contact suggestions");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 3)
	public void MC_TC6_searchSuggestionValidation() throws Exception {
			String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String checkmatch = DriverSetUp.testdataMap.get("checkmatchwith");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption("WorkSpaces");
			wahomepage.selectMenuOption(Messages);
			wamessageschatspage.checkMatchesWith(checkmatch);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate user suggestion validation");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 4)
	public void MC_TC8_profileIConValidationFor121() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption("WorkSpaces");
			wahomepage.selectMenuOption(Messages);
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.userProfileIconValidation(user);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Profile Icon for One to One conversaton");
			waloginpage.backToHomePage(url);
		}
	}
			
	
		@Test(enabled = true, priority = 5)
	public void MC_TC9_validate3dotOptionsFor121() throws Exception {
			String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String expected3dotoptions = DriverSetUp.testdataMap.get("expectedoptionsfor121");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			wahomepage.selectMenuOption("WorkSpaces");
			wahomepage.selectMenuOption(Messages);
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.goToGroupAndPerform(user, false, "NA");
			wamessageschatspage.goToGroupAndPerform(user, true, "3dots");
			wamessageschatspage.optionsDisplayedOn3Dots("One to One", expected3dotoptions, "middle");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate one to one conversaton 3 dot options");
			waloginpage.backToHomePage(url);
		}
	}
	
		@Test(enabled = true, priority = 6)
	public void MC_TC46_verifyEditMessage() throws Exception {
			String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption("WorkSpaces");
			wahomepage.selectMenuOption(Messages);
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.goToGroupAndPerform(user, false, "NA");
			wamessageschatspage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Edit");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Edit message functionality");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 7)
	public void MC_TC24_activeThreadOnFocusColor() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String expbgclr = DriverSetUp.testdataMap.get("expectedlabelbackground");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption("WorkSpaces");
			wahomepage.selectMenuOption(Messages);
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.getActiveLabelBackgroundColor(expbgclr);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Active thread focus colour");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 8)
	public void MC_TC12_checkActiveParticipant() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption("WorkSpaces");
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.getFirstActiveUser(user, true);
			wamessageschatspage.goToGroupAndPerform(user, true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Delete Chat");
			wahomepage.clickOn("Delete", true);
			wamessageschatspage.getFirstActiveUser(user, false);
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr1 = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr1);
			wamessageschatspage.getFirstActiveUser(user, true);
		//	wamessageschatspage.goToMessageAndPerformActionsAs(user,updatedstr, "More", "Copy");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Active participant after deleting the conversation");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 9)
	public void MC_TC28_verifyChevronIcon() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String updatedlongstr = onetoonetext + wahomepage.runtimehhmmss();
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			wamessageschatspage.verifyDisplayOfChevronIcon(false);
			user = wamessageschatspage.enterYourMessageAs(updatedlongstr);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Chevron icon for Single participant");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 10)
	public void MC_TC29_TC43_sendLongText() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonlongtext = "Not that our leaders seemed to notice. Last month the world’s nations, meeting in Rio for the 20th-anniversary reprise of a massive 1992 environmental summit, accomplished nothing. Unlike George H.W. Bush, who flew in for the first conclave, Barack Obama didn’t even attend Not that our leaders seemed to notice. Last month the world’s nations, meeting in Rio for the 20th-anniversary reprise of a massive 1992 environmental summit, accomplished nothing. Unlike George H.W. Bush, who flew in for the first conclave, Barack Obama didn’t even attend Not that our leaders seemed to notice. Last month the world’s nations, meeting in Rio for the 20th-anniversary reprise of a massive 1992 environmental summit, accomplished nothing. Unlike George H.W. Bush, who flew in for the first conclave, Barack Obama didn’t even attend";
			String updatedlongstr = onetoonlongtext + wahomepage.runtimehhmmss();
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			user = wamessageschatspage.enterYourMessageAs(updatedlongstr);
			wamessageschatspage.validateLongTextReadMoreTruncation();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate long text");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 11)
	public void MC_TC38_TC57_validateTopLeftMenuChats() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Chats");
			wamessageschatspage.validateChatsAndDRS(true, false);
			wamessageschatspage.validateDirectChatOrDR("chat");
			wahomepage.selectMenuOption("Workspaces");
			wahomepage.selectMenuOption(Messages);
			wamessageschatspage.validateChatsAndDRS(true, false);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate top left menu Chats");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 12)
	public void MC_TC55_TC58_validateTopLeftMenuDR() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Discussion Rooms");
			wamessageschatspage.validateDirectChatOrDR("DR");
			wahomepage.selectMenuOption("Workspaces");
			wahomepage.selectMenuOption(Messages);
			wamessageschatspage.validateChatsAndDRS(false, true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Top left menu DR's sections");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 13)
	public void MC_TC56_validateTopLeftMenuAllMessages() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wahomepage.selectMenuOption("Workspaces");
			wahomepage.selectMenuOption(Messages);
			wamessageschatspage.validateChatsAndDRS(true, true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Top left menu All Messages sections");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 14)
	public void MC_TC10_enterTextWithEmoji() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String textmsg = onetoonetext + wahomepage.runtimehhmmss();
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.enterYourEmojiWithText(true, textmsg);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Emoji's with text");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 15)
	public void MC_TC60_validateGroupMembersCount() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Chats");
			wamessageschatspage.goToGroupAndPerform("QA Pride", false, "NA");
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);

			String groupcount = wamessageschatspage.getChatHeaderNameorCount(false);
			wamessageschatspage.goToGroupAndPerform("QA Pride", true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Manage Chat");
			int managepartcount = wamessageschatspage.searchFromManageChat(false,"hana@koraqa1.com");
			wamessageschatspage.compareGroupCount(groupcount, managepartcount);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate and compare Group members count from Header and Manage chat");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 16)
	public void MC_TC66_searchMemberFromManageChat() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Chats");
			wamessageschatspage.goToGroupAndPerform("QA Pride", false, "NA");
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);

			wamessageschatspage.goToGroupAndPerform("QA Pride", true, "3dots");
			wamessageschatspage.operationsFrom3Dots("Manage Chat");
			wamessageschatspage.searchFromManageChat(true,"hana@koraqa1.com");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Search functionality from manage chat");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 17)
	public void MC_TC61_verifyReminderSlots() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String expreminderslots = DriverSetUp.testdataMap.get("expectedreminderslots");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption("Workspaces");
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.goToGroupAndPerform(user, false, "NA");
			wamessageschatspage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Reminder");

			wamessageschatspage.validateAndSelectMuteSlots("reminder", expreminderslots, false);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Reminder slots");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 18)
	public void MC_TC35_TC47_TC67_validateReplyback() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String replymsg = "It is Reply";
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.goToMessageAndPerformActionsAs(user, updatedstr, "Reply Back", replymsg);

			wamessageschatspage.verifyJumbBackTomessage(updatedstr);

			waloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform("James Middleton", false, "NA");
			wamessageschatspage.validateFromRecepientEnd(updatedstr, replymsg);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate reply back functionality");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 19)
	public void MC_TC52_validateForwardFromStartNewChat() throws Exception {
		String url = null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.goToGroupAndPerform(user, false, "NA");
			wamessageschatspage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Forward");
			wamessageschatspage.forwardPostOrValidation(true, true, user, updatedstr, newparticipants);

			waloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			String hostuser = "James Middleton";
			wamessageschatspage.goToGroupAndPerform(hostuser, false, "NA");
			wamessageschatspage.forwardPostOrValidation(false, false, hostuser, updatedstr, newparticipants);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate direct Forward functionality");
			waloginpage.backToHomePage(url);
			
		}
	}

	@Test(enabled = true, priority = 20)
	public void MC_TC59_validateSelectAndForward() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String qapridegroup = DriverSetUp.testdataMap.get("standardgroupname");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Chats");
			wamessageschatspage.goToGroupAndPerform(qapridegroup, false, "NA");
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);

			wamessageschatspage.selectOptionFromRightNav3Dots(user, "Select Messages");
			wamessageschatspage.selectMessages(qapridegroup, updatedstr);
			wamessageschatspage.forwardPostOrValidation(true, false, user, updatedstr, qapridegroup);

			waloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform("QA Pride", false, "NA");
			String updatedstr1 = onetoonetext + wahomepage.runtimehhmmss();
			wamessageschatspage.enterYourMessageAs(updatedstr1);

			wamessageschatspage.forwardPostOrValidation(false, false, qapridegroup, updatedstr, "NA");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate select and Forward functionality");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 21)
	public void MC_TC48_TC51_validateReactionsAndMessageInfo() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
		//	String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Chats");
			wamessageschatspage.goToGroupAndPerform("QA Pride", false, "NA");
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);

			waloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			wahomepage.selectMenuOption(Messages);
			String ReactedUserName = waloginpage.getUserDetails();
			wahomepage.selectTopLeftMenuOption("Chats");
			wamessageschatspage.goToGroupAndPerform("QA Pride", false, "NA");
			wamessageschatspage.goToMessageAndPerformActionsAs("QA Pride", updatedstr, "Reactions", "Like");

			waloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("Chats");
			wamessageschatspage.goToGroupAndPerform("QA Pride", false, "NA");
			wamessageschatspage.goToMessageAndPerformActionsAs("QA Pride", updatedstr, "More", "Message Info");

			wamessagesdrpage.messagesreadinPostinfandMsginfo(ReactedUserName, false);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Reactions and message info functionality");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 22)
	public void MC_TC62_TC63_TC64_DeleteMessage() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String qapridegroup = DriverSetUp.testdataMap.get("standardgroupname");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(qapridegroup, false, "NA");
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);

			wamessageschatspage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Delete");
			wamessageschatspage.clickOn("Cancel", true);
			wamessageschatspage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Delete");
			wamessageschatspage.clickOn("Delete for everyone", true);
			wamessageschatspage.validateDeleteMessageFromSelfUser(true, user, updatedstr);
			String updatedstr1 = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr1);
			wamessageschatspage.goToMessageAndPerformActionsAs(user, updatedstr1, "More", "Delete");
			wamessageschatspage.clickOn("Delete for myself", true);
			wamessageschatspage.validateDeleteMessageforMyself(true, user, updatedstr1);

			waloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			wahomepage.selectMenuOption(Messages);
			wamessageschatspage.goToGroupAndPerform(qapridegroup, false, "NA");
			wamessageschatspage.validateDeleteMessageFromSelfUser(false, user, updatedstr);
			wamessageschatspage.validateDeleteMessageforMyself(false, user, updatedstr1);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Delete functionality");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = false, priority = 23)
	public void MC_TC50_MiddlepanePagination() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			wahomepage.selectMenuOption("Workspaces");
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.middlePanePaginationValidation();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Middle pane pagination");
		}
	} */
}
