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

	String korausername;
	String korapassword;

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

		korausername = dr.getValue("KORAV2", "KoraV2Web", "Username");
		korapassword = dr.getValue("KORAV2", "KoraV2Web", "Password");
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
			koraloginpage.loginToKora(url, korausername, korapassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectLeftMenuOption("All Messages");
			koramessagespage.messagesScreenValidations();
			koramessagespage.checkDefaultFocus_Recents();
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate recent contact suggestions");
		}
	}

	/*@Test(enabled = true, priority = 2)
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
	public void MC_TC8_TC9_TC24_OneToOneConv_3dotOptions() throws Exception {
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
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith(newparticipants, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			koramessagespage.getActiveLabelBackgroundColor(expbgclr);
			koramessagespage.userProfileIconValidation(user);
			koramessagespage.goToGroupAndPerform(user, true, "3dots");
			koramessagespage.optionsDisplayedOn3Dots("One to One", expected3dotoptions);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate one to one conversaton validation");
		}
	}

	@Test(enabled = true, priority = 4)
	public void MC_TC12_DeleteAndCheckActiveParticipant() throws Exception {
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
			koramessagespage.startNewConversationWith(newparticipants, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			koramessagespage.getFirstActiveUser(user, true);
			koramessagespage.goToGroupAndPerform(user, true, "3dots");
			koramessagespage.operationsFrom3Dots("Delete Conversation");
			korahomepage.clickOn("Delete", true);
			koramessagespage.getFirstActiveUser(user, false);
			koramessagespage.startNewConversationWith(newparticipants, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			koramessagespage.getFirstActiveUser(user, true);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Active participant after deleting the conversation");
		}
	}*/
	
}
