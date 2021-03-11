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

public class MessagesOnHoverGroupIcons extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesChatsPage koramessagespage;

	String korausername;
	String korapassword;

	public MessagesOnHoverGroupIcons() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute Group test");

		koraloginpage = new KoraLoginPage(remoteDriver);
		korahomepage = new KoraHomePage(remoteDriver);
		koramessagespage = new KoraMessagesChatsPage(remoteDriver);

		korausername = dr.getValue("KORAV2", "KoraV2Web", "Username");
		korapassword = dr.getValue("KORAV2", "KoraV2Web", "Password");

	}

	@Test(enabled = true, priority = 12)
	public void MC_TC41_koraFirstGroupIconValidation() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korausername, korapassword);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.getAndValidateGroupIcons(groupname, true, korausername);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate shuffling of first group icon");
		}
	}

	@Test(enabled = true, priority = 13)
	public void MC_TC35_koraOnHoverParticipantsValidation() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.getOnHoverParticipantsCount(groupname, korausername);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate on hover participants count");
		}
	}

	@Test(enabled = true, priority = 14)
	public void MC_TC23_koraGroupChat3dotOptions() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");
			String expected3dotoptions = DriverSetUp.testdataMap.get("expectedoptionsforgroup");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", expected3dotoptions);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate 3 dot options for a group");
		}
	}
	
	@Test(enabled = true, priority = 15)
	public void MC_TC_22_TC_39_TC_40_koraMuteSlotsVerification() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");
			String muteslots = DriverSetUp.testdataMap.get("expectedmuteslots");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(groupname, true, "Mute");
			koramessagespage.validateAndSelectMuteSlots(muteslots, true);
			korahomepage.selectLeftMenuOption("Muted");
			koramessagespage.searchAndSelectFrom("Muted", groupname, true);
			koramessagespage.goToGroupAndPerform(groupname, true, "UnMute");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate/select mute slots");
		}
	}
	
	@Test(enabled = true, priority = 16)
	public void MC_TC_22_TC_36_validateStarredChats() throws Exception {
		
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String Starred = DriverSetUp.testdataMap.get("kmstarred");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String user="null";

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith(newparticipants, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			koramessagespage.goToGroupAndPerform(user, true, "Star");
			korahomepage.selectLeftMenuOption(Starred);
			koramessagespage.searchAndSelectFrom(Starred, user, true);
			koramessagespage.goToGroupAndPerform(user, true, "Unstar");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate on hover star/unstar actions");
		}
	}
	
	@Test(enabled = true, priority = 17)
	public void MC_TC_22_TC_38_validateUnreadChats() throws Exception {
		
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String user="null";
			
			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith(newparticipants, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			koramessagespage.goToGroupAndPerform(user, true, "Unread");
			korahomepage.selectLeftMenuOption("Unread");
			koramessagespage.goToGroupAndPerform(user, false, "na");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate on hover actions");
		}
	}
	
	@Test(enabled = true, priority = 18)
	public void MC_TC44_koraAtmentionUsers() throws Exception {
		
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.goToGroupAndPerform(groupname, false, "NA");
			int totalparticipants=koramessagespage.profileAvtarCount();
			koramessagespage.atMentionValidation(totalparticipants,false,"NA");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate shuffling of first group icon");
		}
	}
}
