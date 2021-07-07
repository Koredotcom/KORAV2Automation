package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.WAHomePage;
import com.org.kore.web.pages.WALoginPage;
import com.org.kore.web.pages.WAMessagesChatsPage;
import com.org.kore.web.pages.WAMessagesDRPage;
import com.org.kore.web.pages.WAWorkspacesPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class Workspaces extends DriverSetUp {

	WALoginPage waloginpage;
	WAHomePage wahomepage;
	WAMessagesChatsPage wamessageschatspage;
	WAMessagesDRPage wamessagesdrpage;
	WAWorkspacesPage waworkspacepage;

	String korajusername;
	String korajpassword;

	String korahusername;
	String korahpassword;

	static String directory = null;

	public Workspaces() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute Group test");

		waloginpage = new WALoginPage(remoteDriver);
		wahomepage = new WAHomePage(remoteDriver);
		wamessageschatspage = new WAMessagesChatsPage(remoteDriver);
		waworkspacepage = new WAWorkspacesPage(remoteDriver);
		wamessagesdrpage = new WAMessagesDRPage(remoteDriver);
		// pagebase = new PageBase(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");

		directory = System.getProperty("user.dir");

	}

	@Test(enabled = false, priority = 61)
	public void MDR_createNewWorkspaceAndDelete() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Workspaces");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.drdataMap.get("workspaces");
			String workspacename = DriverSetUp.drdataMap.get("workspacename123");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			// waloginpage.launchw3(url, korajusername, korajpassword);
			waloginpage.loginToKora(url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Workspaces);
			waworkspacepage.createNewWorkspaceAndCheckDefault(workspacename);
			waworkspacepage.clickOnWorkspace3Dots(workspacename);
			waworkspacepage.operationsFromWS3Dots(workspacename, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new workspace and validations");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = false, priority = 62)
	public void MDR_inviteMembersToWorkspaceAndManage() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.drdataMap.get("workspaces");
			String workspaceauto = DriverSetUp.drdataMap.get("workspaceauto");
			String invitemems = DriverSetUp.drdataMap.get("workspacemems");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Workspaces);
			waworkspacepage.createNewWorkspaceAs(workspaceauto);
			waworkspacepage.workspaceDirectInvite(invitemems);
			waworkspacepage.clickOnWorkspace3Dots(workspaceauto);
			waworkspacepage.operationsFromWS3Dots(workspaceauto, "Manage");
			wamessageschatspage.clickOn("Members", true);
			waworkspacepage.validateRecentInvites(invitemems);
			waworkspacepage.clickOnWorkspace3Dots(workspaceauto);
			waworkspacepage.operationsFromWS3Dots(workspaceauto, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to invite members to workspace");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = false, priority = 63)
	public void MDR_filterByWorkspace() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.drdataMap.get("workspaces");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String workspacename = DriverSetUp.drdataMap.get("workspacename8");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Workspaces);
			waworkspacepage.createNewWorkspaceAs(workspacename);
			wahomepage.selectMenuOption(Messages);
			wahomepage.getActiveOptionFromLeftNav("All Messages");
			waworkspacepage.selectWorkspace(workspacename);
			// wamessageschatspage.getChatHeaderName();
			String wsname = wamessageschatspage.enterYourMessageAs("My post in " + workspacename);
			waworkspacepage.compareActualExpected(wsname, "General", "Default selected workspace is : ");
			wahomepage.selectMenuOption(Workspaces);
			waworkspacepage.clickOnWorkspace3Dots(workspacename);
			waworkspacepage.operationsFromWS3Dots(workspacename, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
			waloginpage.backToHomePage(url);
		}
	}

	@Test(enabled = false, priority = 64)
	public void MDR_defaultDRAndTimeline() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.drdataMap.get("workspaces");
			String workspacename = DriverSetUp.drdataMap.get("workspacename910");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Workspaces);
			waworkspacepage.createNewWorkspaceAs(workspacename);
			waworkspacepage.selectDefaultDR();
			wamessageschatspage.visibilityOfComposeBar(true);
			wamessageschatspage.verifyGroupCreationTimeline(korajusername);
			wahomepage.selectMenuOption(Workspaces);
			waworkspacepage.clickOnWorkspace3Dots(workspacename);
			waworkspacepage.operationsFromWS3Dots(workspacename, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate default DR and Timeline (General name might got changed)");
			waloginpage.backToHomePage(url);
		}
	}
}
