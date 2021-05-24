package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.KoraHomePage;
import com.org.kore.web.pages.KoraLoginPage;
import com.org.kore.web.pages.KoraMessagesDRPage;
import com.org.kore.web.pages.KoraMessagesChatsPage;
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
		koramessagedrpage= new KoraMessagesDRPage(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");
	}

	@Test(enabled = true, priority = 1)
	public void MC_TC2_TC3_TC4_TC5_LoginRecentValidation() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			test.log(LogStatus.INFO, "Navigation url :" + url);

		//	koraloginpage.launchw3(url, korajusername, korajpassword);
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.checkDefaultFocus_Recents();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate recent contact suggestions");
		}
	}

	@Test(enabled = true, priority = 2)
	public void MC_TC6_UserSuggestionValidation() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String checkmatch = DriverSetUp.testdataMap.get("checkmatchwith");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			koramessagespage.checkMatchesWith(checkmatch);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate user suggestion validation");
		}
	}

	@Test(enabled = true, priority = 3)
	public void MC_TC8_TC9_TC24_TC46_OneToOneConvEdit3dotOptions() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String expected3dotoptions = DriverSetUp.testdataMap.get("expectedoptionsfor121");
			String expbgclr = DriverSetUp.testdataMap.get("expectedlabelbackground");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.getActiveLabelBackgroundColor(expbgclr);
			koramessagespage.userProfileIconValidation(user);
			koramessagespage.goToGroupAndPerform(user, true, "3dots");
			koramessagespage.optionsDisplayedOn3Dots("One to One", expected3dotoptions, "middle");
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Edit");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate one to one conversaton validation");
		}
	}

	// False due to KV2-2089
	@Test(enabled = false, priority = 4)
	public void MC_TC12_TC34_CheckActiveParticipantAndCopy() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

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
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr1, "More", "Copy");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Active participant after deleting the conversation");
		}
	}

		@Test(enabled = true, priority = 5)
	public void MC_TC28_TC29_TC43_ValidateSendLongText() throws Exception {
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
			koramessagespage.verifyDisplayOfChevronIcon(false);
			user = koramessagespage.enterYourMessageAs(updatedlongstr);
			koramessagespage.validateLongTextReadMoreTruncation();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate long text");
		}
	}

	@Test(enabled = true, priority = 6)
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
			test.log(LogStatus.FAIL, "Failed to validate Emojis with text");
		}
	}

	@Test(enabled = true, priority = 7)
	public void MC_TC38_TC55_TC56_TC57_TC58_validateTopLeftMenu() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipant = DriverSetUp.testdataMap.get("oneparticipant");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.validateChatsAndDRS(true, false);
			koramessagespage.validateDirectChatOrDR("chat");
			korahomepage.selectMenuOption("Workspaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.validateChatsAndDRS(true, false);
			
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");
			koramessagespage.validateDirectChatOrDR("DR");
			korahomepage.selectMenuOption("Workspaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.validateChatsAndDRS(false, true);
			
			korahomepage.selectTopLeftMenuOption("All Messages");
			korahomepage.selectMenuOption("Workspaces");
			korahomepage.selectMenuOption(Messages);
			koramessagespage.validateChatsAndDRS(true, true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Chats, DR's and All Message sections");
		}
	}

	@Test(enabled = true, priority = 8)
	public void MC_TC35_TC47_validateReplyback() throws Exception {
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

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "Reply Back", replymsg);

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

	@Test(enabled = true, priority = 9)
	public void MC_TC52_validateForward() throws Exception {
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
			koramessagespage.forwardPostOrValidation(true,true, user, updatedstr, newparticipants);

			koraloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			String hostuser = "James Middleton";
			koramessagespage.goToGroupAndPerform(hostuser, false, "NA");
			koramessagespage.forwardPostOrValidation(false,false, hostuser, updatedstr, newparticipants);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Forward functionality");
		}
	}
	
	@Test(enabled = true, priority = 10) //////////////////////////////////////////////////////////////////////////////////////////
	public void MC_TC59_selectAndForward() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			koraloginpage.loginToKora(url, korajusername, korajpassword);
		//	koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");			
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			
			koramessagespage.selectOptionFromRightNav3Dots(user, "Select Messages");
			koramessagespage.forwardPostOrValidation(true,false, user, updatedstr, "QA Pride");

			koraloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");
			String updatedstr1 = onetoonetext + korahomepage.runtimehhmmss();
			koramessagespage.enterYourMessageAs(updatedstr1);
			
			String hostuser = "James Middleton";
			koramessagespage.forwardPostOrValidation(false,false, hostuser, updatedstr, "NA");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Forward functionality");
		}
	}

	@Test(enabled = true, priority = 11)
	public void MC_TC48_validateReactions() throws Exception {
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
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");			
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);

			koraloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			korahomepage.selectMenuOption(Messages);
			String ReactedUserName=koraloginpage.getUserDetails();
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs("QA Pride", updatedstr, "Reactions", "Like");

			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);			
			korahomepage.selectTopLeftMenuOption("Chats");
			koramessagespage.goToGroupAndPerform("QA Pride", false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs("QA Pride", updatedstr, "More", "Message Info");
						
			koramessagedrpage.messagesreadinPostinfandMsginfo(ReactedUserName,false);
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Reactionas functionality");
		}
	}
	
	@Test(enabled = true, priority = 12)
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

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.goToGroupAndPerform(user, false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Reminder");

			koramessagespage.validateAndSelectMuteSlots("reminder",expreminderslots, false);
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Forward functionality");
		}
	}

	@Test(enabled = true, priority = 13)
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

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(qapridegroup, false, "NA");			
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Delete");
			koramessagespage.clickOn("Cancel", true);
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr, "More", "Delete");
			koramessagespage.clickOn("Delete for everyone", true);
			koramessagespage.validateDeleteMessageFromSelfUser(true,user,updatedstr);
			String updatedstr1 = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr1);
			koramessagespage.goToMessageAndPerformActionsAs(user, updatedstr1, "More", "Delete");
			koramessagespage.clickOn("Delete for myself", true);
			koramessagespage.validateDeleteMessageforMyself(true,user,updatedstr1);
			
			koraloginpage.logoutAndReLogin(true, url, korahusername, korahpassword);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.goToGroupAndPerform(qapridegroup, false, "NA");	
			koramessagespage.validateDeleteMessageFromSelfUser(false,user,updatedstr);
			koramessagespage.validateDeleteMessageforMyself(false,user,updatedstr1);
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Delete functionality");
		}
	}
}
