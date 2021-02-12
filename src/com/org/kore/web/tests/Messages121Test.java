package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.KoraHomePage;
import com.org.kore.web.pages.KoraLoginPage;
import com.org.kore.web.pages.KoraMessagesPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 * @Description : All TC's related to Web page
 *
 */

public class Messages121Test extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesPage koramessagespage;

	String korausername;
	String korapassword;

	public Messages121Test() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute 121 test");

		koraloginpage = new KoraLoginPage(remoteDriver);
		korahomepage = new KoraHomePage(remoteDriver);
		koramessagespage = new KoraMessagesPage(remoteDriver);

		korausername = dr.getValue("KORAV2", "KoraV2Web", "Username");
		korapassword = dr.getValue("KORAV2", "KoraV2Web", "Password");
	}

	@Test(enabled = true)
	public void koraOneToOneConversation() throws Exception {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
				.assignCategory("KORAV2Messages");
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		String url = DriverSetUp.propsMap.get("weburl");
		String checkmatch = DriverSetUp.testdataMap.get("checkmatchwith");
		String newparticipants = DriverSetUp.testdataMap.get("oneparticipant");
		String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");

		test.log(LogStatus.INFO, "Navigation url :" + url);
		koraloginpage.loginToKora(url, korausername, korapassword);
		korahomepage.selectMenuOption("Messages");
		koramessagespage.messagesScreenValidations();
		koramessagespage.checkMatchesWith(checkmatch);
		koramessagespage.startNewConversationWith(newparticipants, true);
		koramessagespage.enterYourMessageAs(onetoonetext);
		extent.endTest(test);
	}

}
