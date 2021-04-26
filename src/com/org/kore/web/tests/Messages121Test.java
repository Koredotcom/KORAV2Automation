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

public class Messages121Test extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesChatsPage koramessagespage;

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

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");
	}

	/*	@Test(enabled = true, priority = 1)
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
	public void MC_TC8_TC9_TC24_TC46_OneToOneConvEdit_3dotOptions() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String expected3dotoptions = DriverSetUp.testdataMap.get("expectedoptionsfor121");
			String expbgclr= DriverSetUp.testdataMap.get("expectedlabelbackground");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith("chat",newparticipants, true);
			String updatedstr=onetoonetext+korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.getActiveLabelBackgroundColor(expbgclr);
			koramessagespage.userProfileIconValidation(user);
			koramessagespage.goToGroupAndPerform(user, true, "3dots");
			koramessagespage.optionsDisplayedOn3Dots("One to One", expected3dotoptions,"middle");
			koramessagespage.goToMessageAndPerformActionsAs(user,updatedstr, "More", "Edit");
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate one to one conversaton validation");
		}
	}
		@Test(enabled = true, priority = 4)
	public void MC_TC12_TC34_TC35_DeleteAndCheckActiveParticipant_Copy() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String msgtocopy = DriverSetUp.testdataMap.get("msgforcopy");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat",newparticipants, true);
			String updatedstr=onetoonetext+korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.getFirstActiveUser(user, true);
			koramessagespage.goToGroupAndPerform(user, true, "3dots");
			koramessagespage.operationsFrom3Dots("Delete Conversation");
			korahomepage.clickOn("Delete", true);
			koramessagespage.getFirstActiveUser(user, false);
			koramessagespage.startNewConversationWith("chat",newparticipants, true);
			String updatedstr1=onetoonetext+korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr1);
			koramessagespage.getFirstActiveUser(user, true);
			koramessagespage.goToMessageAndPerformActionsAs(user,updatedstr1, "More", "Copy");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Active participant after deleting the conversation");
		}
	}

		@Test(enabled = true, priority = 5)
	public void MC_TC28_TC29_TC43_ValidateChevronIconFor1ParticipantAndSendLongText() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonlongtext = "Not that our leaders seemed to notice. Last month the world’s nations, meeting in Rio for the 20th-anniversary reprise of a massive 1992 environmental summit, accomplished nothing. Unlike George H.W. Bush, who flew in for the first conclave, Barack Obama didn’t even attend Not that our leaders seemed to notice. Last month the world’s nations, meeting in Rio for the 20th-anniversary reprise of a massive 1992 environmental summit, accomplished nothing. Unlike George H.W. Bush, who flew in for the first conclave, Barack Obama didn’t even attend Not that our leaders seemed to notice. Last month the world’s nations, meeting in Rio for the 20th-anniversary reprise of a massive 1992 environmental summit, accomplished nothing. Unlike George H.W. Bush, who flew in for the first conclave, Barack Obama didn’t even attend";

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat",newparticipants, true);
			koramessagespage.verifyDisplayOfChevronIcon(false);
			user = koramessagespage.enterYourMessageAs(onetoonlongtext);
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
	public void MC_TC38_checkChatsDRSAndAllMessagesSections() throws Exception {
			try {
				test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
						.assignCategory("WorkAssist_Messages_Chats");
				System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

				String url = DriverSetUp.propsMap.get("weburl");
				String Messages = DriverSetUp.testdataMap.get("messages");

				test.log(LogStatus.INFO, "Navigation url :" + url);
				korahomepage.selectMenuOption(Messages);
				korahomepage.selectTopLeftMenuOption("Chats");
				koramessagespage.validateChatsAndDRS(true,false);
				korahomepage.selectTopLeftMenuOption("Discussion Rooms");
				koramessagespage.validateChatsAndDRS(false,true);
				korahomepage.selectTopLeftMenuOption("All Messages");
				koramessagespage.validateChatsAndDRS(true,true);
				extent.endTest(test);
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Failed to validate Chats, DR's and All Message sections");
			}
		}
		
		@Test(enabled = true, priority = 8)
		public void MC_TC47_Replyback() throws Exception {
			try {
				test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
						.assignCategory("WorkAssist_Messages_Chats");
				System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
				String url = DriverSetUp.propsMap.get("weburl");
				String Messages = DriverSetUp.testdataMap.get("messages");
				String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
				String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
				String msgtocopy = DriverSetUp.testdataMap.get("msgforcopy");
				String replymsg="It is Reply";
				test.log(LogStatus.INFO, "Navigation url :" + url);
				
				koraloginpage.loginToKora(url, korajusername, korajpassword);	
				korahomepage.selectMenuOption(Messages);
				korahomepage.selectTopLeftMenuOption("All Messages");
				koramessagespage.startNewConversationWith("chat",newparticipants, true);
				String updatedstr=onetoonetext+korahomepage.runtimehhmmss();
				user = koramessagespage.enterYourMessageAs(updatedstr);
				koramessagespage.goToMessageAndPerformActionsAs(user,updatedstr, "Reply Back", replymsg);
				koraloginpage.logoutAndReLogin(true,url, korahusername, korahpassword);	
				korahomepage.selectTopLeftMenuOption("All Messages");
				koramessagespage.goToGroupAndPerform("James Middleton", false, "NA");
				koramessagespage.validateFromRecepientEnd(updatedstr,replymsg);
				
				extent.endTest(test);
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Failed to validate Active participant after deleting the conversation");
			}
		}*/
	
	@Test(enabled = true, priority = 9)
	public void MC_TC48_Reactions() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			koraloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);	
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat",newparticipants, true);
			String updatedstr=onetoonetext+korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			
			koraloginpage.logoutAndReLogin(true,url, korahusername, korahpassword);	
			koramessagespage.goToGroupAndPerform("James Middleton", false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs(user,updatedstr, "Reactions", "Like");
			
			koraloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);	
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(user, false, "NA");
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Active participant after deleting the conversation");
		}
	}
	
	@Test(enabled = true, priority = 10)
	public void MC_TC52_Forward() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			koraloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);	
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat",newparticipants, true);
			String updatedstr=onetoonetext+korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			
			koraloginpage.logoutAndReLogin(true,url, korahusername, korahpassword);	
			koramessagespage.goToGroupAndPerform("James Middleton", false, "NA");
			koramessagespage.goToMessageAndPerformActionsAs(user,updatedstr, "Reactions", "Like");
			
			koraloginpage.logoutAndReLogin(true,url, korajusername, korajpassword);	
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(user, false, "NA");
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Active participant after deleting the conversation");
		}
	}
}
