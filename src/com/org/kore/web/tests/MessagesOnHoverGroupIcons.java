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
 * @Description : All TC's related to Web page
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

	@Test(enabled = true, priority = 1)
	public void koraFirstGroupIconValidation() throws Exception {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
				.assignCategory("KORAV2Messages");
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		String url = DriverSetUp.propsMap.get("weburl");

		test.log(LogStatus.INFO, "Navigation url :" + url);
		koraloginpage.loginToKora(url, korausername, korapassword);
		korahomepage.selectMenuOption("Messages");
		koramessagespage.getAndValidateGroupIcons("QA Pride", true, korausername);

		// String[] expectedval={"Leave Conversation","Manage
		// Conversation","Clear Chat History"};
		// koramessagespage.optionsDisplayedOn3Dots(expectedval);

		extent.endTest(test);
	}

	@Test(enabled = true, priority = 2)
	public void koraOnHoverParticipantsValidation() throws Exception {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
				.assignCategory("KORAV2Messages");
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		String url = DriverSetUp.propsMap.get("weburl");

		test.log(LogStatus.INFO, "Navigation url :" + url);
		korahomepage.selectMenuOption("Messages");
		koramessagespage.getOnHoverParticipantsCount("QA Pride", korausername);

		koramessagespage.getAndValidateGroupIcons("TimeLines here", true, korausername);
		koramessagespage.getOnHoverParticipantsCount("TimeLines here", korausername);

		extent.endTest(test);
	}

}
