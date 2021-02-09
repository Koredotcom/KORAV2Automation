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

public class MessagesNoGroupNameTest extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesPage koramessagespage;
	KoraManageConversationPage koramananeconvpage;

	String korausername;
	String korapassword;
	

	public MessagesNoGroupNameTest() throws Exception {
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

	@Test(enabled = true, priority =1)
	public void koraGroupConversation() throws Exception {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
				.assignCategory("KORAV2Messages");
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		String url = DriverSetUp.propsMap.get("weburl");
		String newparticipants = DriverSetUp.testdataMap.get("3usersgroup");
		String grouptext = DriverSetUp.testdataMap.get("groupchat");

		test.log(LogStatus.INFO, "Navigation url : " + url);
		koraloginpage.loginToKora(url, korausername, korapassword);
		korahomepage.selectMenuOption("Messages");
		
		koramessagespage.goToGroupAndPerform("", "Click");
		koramessagespage.goToGroupAndPerform("", "3dots");
		
		koramessagespage.startNewConversationWith(newparticipants, true);
		koramessagespage.enterYourMessageAs(grouptext);
		
		//Get first icon
		
		// Login with other user (User B) from the group
		
		// Get first icons
		// Enter message and get first icon
		
		// Now login with first user(User A) and get icons
		
		// Then final validations
		
		// Manage and remove particpants then leave conv and delete conv --> This is already covered
		
		
		
		extent.endTest(test);
	}
	
}
