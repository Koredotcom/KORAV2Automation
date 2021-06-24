package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.KoraHomePage;
import com.org.kore.web.pages.KoraLoginPage;
import com.org.kore.web.pages.KoraMessagesChatsPage;
import com.org.kore.web.pages.KoraMessagesDRPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class Messages121Test extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesChatsPage koramessagespage;
	KoraMessagesDRPage koramessagedrpage;

	String korajusername;
	String korajpassword;

	String korahusername;
	String korahpassword;

	static String user = null;

	public Messages121Test() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute 121 test");

		koraloginpage = new KoraLoginPage(remoteDriver);
		korahomepage = new KoraHomePage(remoteDriver);
		koramessagespage = new KoraMessagesChatsPage(remoteDriver);
		koramessagedrpage = new KoraMessagesDRPage(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");
	}

	@Test(enabled = true, priority = 1)
	public void MC_TC2_loginToWorkAssist() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			// koraloginpage.launchw3(url, korajusername, korajpassword);
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate recent contact suggestions");
		}
	}
	
	@Test(enabled = true, priority = 2)
	public void MC_TC3_TC4_TC5_verifyRecentSuggestions() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.checkDefaultFocus_Recents();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate recent contact suggestions");
		}
	}
	
	@Test(enabled = true, priority = 3)
	public void MC_TC6_searchSuggestionValidation() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String checkmatch = DriverSetUp.testdataMap.get("checkmatchwith");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption("WorkSpaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.checkMatchesWith(checkmatch);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate user suggestion validation");
		}
	}

	@Test(enabled = true, priority = 4)
	public void MC_TC8_profileIConValidationFor121() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption("WorkSpaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.userProfileIconValidation(user);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Profile Icon for One to One conversaton");
		}
	}
			
	
	@Test(enabled = true, priority = 5)
	public void MC_TC9_validate3dotOptionsFor121() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String expected3dotoptions = DriverSetUp.testdataMap.get("expectedoptionsfor121");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption("WorkSpaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.goToGroupAndPerform(user, false, "NA");
			koramessagespage.goToGroupAndPerform(user, true, "3dots");
			koramessagespage.optionsDisplayedOn3Dots("One to One", expected3dotoptions, "middle");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate one to one conversaton 3 dot options");
		}
	}
	
		@Test(enabled = true, priority = 6)
	public void MC_TC46_verifyEditMessage() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption("WorkSpaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.goToGroupAndPerform(user, false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Edit");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Edit message functionality");
		}
	}
	
	@Test(enabled = true, priority = 7)
	public void MC_TC24_activeThreadOnFocusColor() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String expbgclr = DriverSetUp.testdataMap.get("expectedlabelbackground");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption("WorkSpaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.getActiveLabelBackgroundColor(expbgclr);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Active thread focus colour");
		}
	}

	@Test(enabled = true, priority = 8)
	public void MC_TC12_checkActiveParticipant() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption("WorkSpaces");
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.getFirstActiveUser(user, true);
			koramessagespage.goToGroupAndPerform(user, true, "3dots");
			koramessagespage.operationsFrom3Dots("Delete Chat");
			korahomepage.clickOn("Delete", true);
			koramessagespage.getFirstActiveUser(user, false);
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr1 = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr1);
			koramessagespage.getFirstActiveUser(user, true);
		//	koramessagespage.goToMessageAndPerformActionsAs(user,updatedstr, "More", "Copy");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Active participant after deleting the conversation");
		}
	}
	
	@Test(enabled = true, priority = 9)
	public void MC_TC28_verifyChevronIcon() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String updatedlongstr = onetoonetext + korahomepage.runtimehhmmss();
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			koramessagespage.verifyDisplayOfChevronIcon(false);
			user = koramessagespage.enterYourMessageAs(updatedlongstr);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Chevron icon for Single participant");
		}
	}
	
	@Test(enabled = true, priority = 10)
	public void MC_TC29_TC43_sendLongText() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonlongtext = "Not that our leaders seemed to notice. Last month the world’s nations, meeting in Rio for the 20th-anniversary reprise of a massive 1992 environmental summit, accomplished nothing. Unlike George H.W. Bush, who flew in for the first conclave, Barack Obama didn’t even attend Not that our leaders seemed to notice. Last month the world’s nations, meeting in Rio for the 20th-anniversary reprise of a massive 1992 environmental summit, accomplished nothing. Unlike George H.W. Bush, who flew in for the first conclave, Barack Obama didn’t even attend Not that our leaders seemed to notice. Last month the world’s nations, meeting in Rio for the 20th-anniversary reprise of a massive 1992 environmental summit, accomplished nothing. Unlike George H.W. Bush, who flew in for the first conclave, Barack Obama didn’t even attend";
			String updatedlongstr = onetoonlongtext + korahomepage.runtimehhmmss();
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			user = koramessagespage.enterYourMessageAs(updatedlongstr);
			koramessagespage.validateLongTextReadMoreTruncation();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate long text");
		}
	}
	
	@Test(enabled = true, priority = 11)
	public void MC_TC38_TC57_validateTopLeftMenuChats() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.validateChatsAndDRS(true, false);
			koramessagespage.validateDirectChatOrDR("chat");
			korahomepage.selectMenuOption("Workspaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.validateChatsAndDRS(true, false);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate top left menu Chats");
		}
	}
	
	@Test(enabled = true, priority = 12)
	public void MC_TC55_TC58_validateTopLeftMenuDR() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagespage.validateDirectChatOrDR("DR");
			korahomepage.selectMenuOption("Workspaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.validateChatsAndDRS(false, true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Top left menu DR's sections");
		}
	}

	@Test(enabled = true, priority = 13)
	public void MC_TC56_validateTopLeftMenuAllMessages() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			korahomepage.selectMenuOption("Workspaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.validateChatsAndDRS(true, true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Top left menu All Messages sections");
		}
	}
	
	@Test(enabled = true, priority = 14)
	public void MC_TC10_enterTextWithEmoji() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.enterYourEmojiWithText(true, " Hi Sending with emoji ");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Emoji's with text");
		}
	}

	@Test(enabled = true, priority = 15)
	public void MC_TC60_validateGroupMembersCount() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);

			String groupcount = koramessagespage.getChatHeaderNameorCount(false);
			koramessagespage.goToGroupAndPerform("QA Pride", true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Chat");
			int managepartcount = koramessagespage.searchFromManageChat(false,"hana@koraqa1.com");
			koramessagespage.compareGroupCount(groupcount, managepartcount);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate and compare Group members count from Header and Manage chat");
		}
	}

	@Test(enabled = true, priority = 16)
	public void MC_TC66_searchMemberFromManageChat() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);

			koramessagespage.goToGroupAndPerform("QA Pride", true, "3dots");
			koramessagespage.operationsFrom3Dots("Manage Chat");
			koramessagespage.searchFromManageChat(true,"hana@koraqa1.com");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Search functionality from manage chat");
		}
	}
	
	@Test(enabled = true, priority = 17)
	public void MC_TC35_TC47_TC67_validateReplyback() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String replymsg = "It is Reply";
			test.log(LogStatus.INFO, "Navigation url :" + url);

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "Reply Back", replymsg);

			koramessagespage.verifyJumbBackTomessage(updatedstr);

			koraloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform("James Middleton", false, "NA");
			koramessagespage.validateFromRecepientEnd(updatedstr, replymsg);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate reply back functionality");
		}
	}

	@Test(enabled = true, priority = 18)
	public void MC_TC52_validateDirectForward() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.goToGroupAndPerform(user, false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Forward");
			koramessagespage.forwardPostOrValidation(true, true, user, updatedstr, newparticipants);

			koraloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			String hostuser = "James Middleton";
			koramessagespage.goToGroupAndPerform(hostuser, false, "NA");
			koramessagespage.forwardPostOrValidation(false, false, hostuser, updatedstr, newparticipants);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate direct Forward functionality");
		}
	}

	@Test(enabled = true, priority = 19)
	public void MC_TC59_validateSelectAndForward() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String qapridegroup = DriverSetUp.testdataMap.get("standardgroupname");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform(qapridegroup, false, "NA");
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);

			koramessagespage.selectOptionFromRightNav3Dots(user, "Select Messages");
			koramessagespage.selectMessages(qapridegroup, updatedstr);
			koramessagespage.forwardPostOrValidation(true, false, user, updatedstr, qapridegroup);

			koraloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");
			String updatedstr1 = onetoonetext + korahomepage.runtimehhmmss();
			koramessagespage.enterYourMessageAs(updatedstr1);

			koramessagespage.forwardPostOrValidation(false, false, qapridegroup, updatedstr, "NA");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate select and Forward functionality");
		}
	}

	@Test(enabled = true, priority = 20)
	public void MC_TC48_TC51_validateReactionsAndMessageInfo() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
		//	String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);

			koraloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			korahomepage.selectMenuOption(Messages);
			String ReactedUserName = koraloginpage.getUserDetails();
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs("QA Pride", updatedstr, "Reactions", "Like");

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs("QA Pride", updatedstr, "More", "Message Info");

			koramessagedrpage.messagesreadinPostinfandMsginfo(ReactedUserName, false);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Reactions and message info functionality");
		}
	}

	@Test(enabled = true, priority = 21)
	public void MC_TC61_verifyReminderSlots() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String expreminderslots = DriverSetUp.testdataMap.get("expectedreminderslots");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.goToGroupAndPerform(user, false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Reminder");

			koramessagespage.validateAndSelectMuteSlots("reminder", expreminderslots, false);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Reminder slots");
		}
	}

	@Test(enabled = true, priority = 22)
	public void MC_TC62_TC63_TC64_DeleteMessage() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String qapridegroup = DriverSetUp.testdataMap.get("standardgroupname");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(qapridegroup, false, "NA");
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);

			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Delete");
			koramessagespage.clickOn("Cancel", true);
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Delete");
			koramessagespage.clickOn("Delete for everyone", true);
			koramessagespage.validateDeleteMessageFromSelfUser(true, user, updatedstr);
			String updatedstr1 = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr1);
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr1, "More", "Delete");
			koramessagespage.clickOn("Delete for myself", true);
			koramessagespage.validateDeleteMessageforMyself(true, user, updatedstr1);

			koraloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.goToGroupAndPerform(qapridegroup, false, "NA");
			koramessagespage.validateDeleteMessageFromSelfUser(false, user, updatedstr);
			koramessagespage.validateDeleteMessageforMyself(false, user, updatedstr1);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Delete functionality");
		}
	}

	/*@Test(enabled = true, priority = 23)
	public void MC_TC50_MiddlepanePagination() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption("Workspaces");
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.middlePanePaginationValidation();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Middle pane pagination");
		}
	}*/
}
