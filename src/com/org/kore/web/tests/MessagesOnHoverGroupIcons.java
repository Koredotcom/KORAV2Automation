package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.KoraHomePage;
import com.org.kore.web.pages.KoraLoginPage;
import com.org.kore.web.pages.KoraManageConversationPage;
import com.org.kore.web.pages.KoraMessagesPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class MessagesOnHoverGroupIcons extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesPage koramessagespage;
	KoraManageConversationPage koramananeconvpage;

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
		koramessagespage = new KoraMessagesPage(remoteDriver);
		koramananeconvpage = new KoraManageConversationPage(remoteDriver);

		korausername = dr.getValue("KORAV2", "KoraV2Web", "Username");
		korapassword = dr.getValue("KORAV2", "KoraV2Web", "Password");

	}

	@Test(enabled = true, priority = 11)
	public void koraFirstGroupIconValidation() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("KORAV2Messages");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korausername, korapassword);
			korahomepage.selectMenuOption("Messages");
			koramessagespage.getAndValidateGroupIcons(groupname, true, korausername);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate shuffling of first group icon");
		}
	}

	@Test(enabled = true, priority = 12)
	public void koraOnHoverParticipantsValidation() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("KORAV2Messages");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption("Messages");
			koramessagespage.getOnHoverParticipantsCount(groupname, korausername);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate on hover participants count");
		}
	}

	@Test(enabled = true, priority = 13)
	public void koraGroupChat3dotOptions() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("KORAV2Messages");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String groupname = DriverSetUp.testdataMap.get("standardgroupname");
			String expected3dotoptions = DriverSetUp.testdataMap.get("expectedoptionsforgroup");

			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korausername, korapassword);
			korahomepage.selectMenuOption("Messages");
			koramessagespage.goToGroupAndPerform(groupname, true, "3dots");
			koramessagespage.optionsDisplayedOn3Dots("GroupConversation", expected3dotoptions);
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate 3 dot options for a group");
		}
	}
}
