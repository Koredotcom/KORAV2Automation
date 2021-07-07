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

public class MessagesChatOnHover extends DriverSetUp {

	WALoginPage waloginpage;
	WAHomePage wahomepage;
	WAMessagesChatsPage wamessageschatspage;

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

		waloginpage = new WALoginPage(remoteDriver);
		wahomepage = new WAHomePage(remoteDriver);
		wamessageschatspage = new WAMessagesChatsPage(remoteDriver);

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
			
			waloginpage.loginToKora(url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.getAndValidateGroupIcons(groupname, true, korajusername);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate shuffling of first group icon");
			waloginpage.backToHomePage(url);
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
			
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(groupname, false, "NA");
			wamessageschatspage.enterYourMessageAs("Sending Group Message");
			wamessageschatspage.goToGroupAndPerform(groupname, true, "3dots");
			wamessageschatspage.optionsDisplayedOn3Dots("Group Chat", expected3dotoptions,"middle");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate 3 dot options for a group");
			waloginpage.backToHomePage(url);
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
			
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(groupname, true, "Mute");
			wamessageschatspage.validateAndSelectMuteSlots("mute",muteslots, true);
			wahomepage.selectTopLeftMenuOption("Muted");
			wamessageschatspage.searchAndSelectFrom("Muted", groupname, true);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(groupname, true, "UnMute");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate/select mute slots");
			waloginpage.backToHomePage(url);
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
			
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("chat",newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.goToGroupAndPerform(user, true, "Star");
			wahomepage.selectTopLeftMenuOption(Starred);
			wamessageschatspage.searchAndSelectFrom(Starred, user, true);
			wamessageschatspage.goToGroupAndPerform(user, true, "Unstar");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate on hover star/unstar actions");
			waloginpage.backToHomePage(url);
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
			
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.startNewConversationWith("chat",newparticipants, true);
			String updatedstr = onetoonetext + wahomepage.runtimehhmmss();
			user = wamessageschatspage.enterYourMessageAs(updatedstr);
			wamessageschatspage.goToGroupAndPerform(user, true, "Unread");
			wahomepage.selectTopLeftMenuOption("Unread");
			wamessageschatspage.goToGroupAndPerform(user, false, "na");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate on hover actions");
			waloginpage.backToHomePage(url);
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
			
			wahomepage.selectMenuOption(Messages);
			wahomepage.selectTopLeftMenuOption("All Messages");
			wamessageschatspage.goToGroupAndPerform(groupname, false, "NA");
			int totalparticipants=wamessageschatspage.profileAvtarCount();
			wamessageschatspage.atMentionValidation(totalparticipants,false,"NA");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate shuffling of first group icon");
			waloginpage.backToHomePage(url);
		}
	}
}
