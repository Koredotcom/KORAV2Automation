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

public class MessagesGroupTest extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesPage koramessagespage;
	KoraManageConversationPage koramananeconvpage;

	String korausername;
	String korapassword;
	

	public MessagesGroupTest() throws Exception {
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
		String newparticipants = DriverSetUp.testdataMap.get("groupparticipants");
		String groupname = DriverSetUp.testdataMap.get("groupname");
		String grouptext = DriverSetUp.testdataMap.get("groupchat");

		test.log(LogStatus.INFO, "Navigation url : " + url);
		koraloginpage.loginToKora(url, korausername, korapassword);
		korahomepage.selectMenuOption("Messages");
		koramessagespage.startNewConversationWith(newparticipants, true);
		koramessagespage.createGroupAs(groupname);
		koramessagespage.enterYourMessageAs(grouptext);
		extent.endTest(test);
	}
	
	@Test(enabled = true, priority =2)
	public void manageGroupConversation() throws Exception {
		String updatedname;
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
				.assignCategory("KORAV2Messages");
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		String url = DriverSetUp.propsMap.get("weburl");
		String renameto =DriverSetUp.testdataMap.get("renamegroupto");
		String groupname = DriverSetUp.testdataMap.get("groupname");
		
		String originalgroupmems = DriverSetUp.testdataMap.get("groupparticipants");
		String updatedgroupmems = DriverSetUp.testdataMap.get("updateparticipant");
		
		test.log(LogStatus.INFO, "Navigation url : " + url);
		koraloginpage.loginToKora(url, korausername, korapassword);
		korahomepage.selectMenuOption("Messages");
		koramessagespage.goToGroupAndPerform(groupname, "Click");
		koramessagespage.goToGroupAndPerform(groupname, "3dots");
		koramessagespage.operationsFrom3Dots("Manage Conversation");
		koramananeconvpage.manageConversationValidations();
		koramananeconvpage.clickOn("Members",false);
		koramananeconvpage.clickOn("Add Participants", true);
		koramessagespage.startNewConversationWith(updatedgroupmems, false);
		koramananeconvpage.clickOn("Done",false);
		koramananeconvpage.validateRecentAddedParticipants(updatedgroupmems);
		koramananeconvpage.clickOn("General",false);
		updatedname =koramananeconvpage.renameGroupAndClose(renameto);
		koramessagespage.goToGroupAndPerform(updatedname, "Click");
		koramessagespage.enterYourMessageAs("Sending text message after updating the Group");
		koramessagespage.goToGroupAndPerform(updatedname, "3dots");
		koramessagespage.operationsFrom3Dots("Manage Conversation");
		koramananeconvpage.clickOn("Members",true); 
		//koramananeconvpage.compareParticipants(originalgroupmems, updatedgroupmems);
		koramananeconvpage.removeParticipantsAndClose();
		koramessagespage.goToGroupAndPerform(updatedname, "Click");
		koramessagespage.goToGroupAndPerform(updatedname, "3dots");
		koramessagespage.operationsFrom3Dots("Clear Chat History");
		koramessagespage.goToGroupAndPerform(updatedname, "Click");
		koramessagespage.goToGroupAndPerform(updatedname, "3dots");
		koramessagespage.operationsFrom3Dots("Delete Conversation");
		koramananeconvpage.clickOn("Delete",false);
		extent.endTest(test);
	}

}
