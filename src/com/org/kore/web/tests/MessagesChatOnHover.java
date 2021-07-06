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

public class MessagesChatOnHover extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesChatsPage koramessagespage;

	String korajusername;
	String korajpassword;
	
	String korahusername;
	String korahpassword;

	public MessagesChatOnHover() throws Exception {
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

	@Test(enabled = true, priority = 31)
	public void MC_TC41_firstGroupIconValidation() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.getAndValidateGroupIcons(groupname, true, korajusername);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate shuffling of first group icon");
			koraloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = true, priority = 32)
	public void MC_TC23_groupChat3dotOptions() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");
			String expected3dotoptions = DriverSetUp.testdataMap.get("expectedoptionsforgroup");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(groupname, false, "NA");
			koramessagespage.enterYourMessageAs("Sending Group Message");
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.optionsDisplayedOn3Dots("Group Chat", expected3dotoptions,"middle");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate 3 dot options for a group");
			koraloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 33)
	public void MC_TC_22_TC_40_TC_41_muteSlotsVerification() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");
			String muteslots = DriverSetUp.testdataMap.get("expectedmuteslots");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(groupname, true, "Mute");
			koramessagespage.validateAndSelectMuteSlots("mute",muteslots, true);
			korahomepage.selectTopLeftMenuOption("Muted");
			koramessagespage.searchAndSelectFrom("Muted", groupname, true);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(groupname, true, "UnMute");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate/select mute slots");
			koraloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 34)
	public void MC_TC_22_TC_37_validateStarredChats() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String Starred = DriverSetUp.testdataMap.get("kmstarred");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String user="null";
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat",newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.goToGroupAndPerform(user, true, "Star");
			korahomepage.selectTopLeftMenuOption(Starred);
			koramessagespage.searchAndSelectFrom(Starred, user, true);
			koramessagespage.goToGroupAndPerform(user, true, "Unstar");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate on hover star/unstar actions");
			koraloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 35)
	public void MC_TC_22_TC_39_validateUnreadChats() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String user="null";
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat",newparticipants, true);
			String updatedstr = onetoonetext + korahomepage.runtimehhmmss();
			user = koramessagespage.enterYourMessageAs(updatedstr);
			koramessagespage.goToGroupAndPerform(user, true, "Unread");
			korahomepage.selectTopLeftMenuOption("Unread");
			koramessagespage.goToGroupAndPerform(user, false, "na");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate on hover actions");
			koraloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 36)
	public void MC_TC44_koraAtmentionUsers() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");
			test.log(LogStatus.INFO, "Navigation url :" + url);
			
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.goToGroupAndPerform(groupname, false, "NA");
			int totalparticipants=koramessagespage.profileAvtarCount();
			koramessagespage.atMentionValidation(totalparticipants,false,"NA");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate shuffling of first group icon");
			koraloginpage.backToHomePage(url);
		}
	}
}
