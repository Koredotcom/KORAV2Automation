package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.KoraHomePage;
import com.org.kore.web.pages.KoraLoginPage;
import com.org.kore.web.pages.KoraMessagesChatsPage;
import com.org.kore.web.pages.KoraMessagesDRPage;
import com.org.kore.web.pages.KoraWorkspacesPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class WorkspacesTest extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesChatsPage koramessagespage;
	KoraWorkspacesPage koraworkspacepage;
	KoraMessagesDRPage koramessagedrpage;
	// PageBase pagebase;

	String korajusername;
	String korajpassword;

	String korahusername;
	String korahpassword;

	static String directory = null;

	public WorkspacesTest() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute Group test");

		koraloginpage = new KoraLoginPage(remoteDriver);
		korahomepage = new KoraHomePage(remoteDriver);
		koramessagespage = new KoraMessagesChatsPage(remoteDriver);
		koraworkspacepage = new KoraWorkspacesPage(remoteDriver);
		koramessagedrpage = new KoraMessagesDRPage(remoteDriver);
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

			// koraloginpage.launchw3(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.createNewWorkspaceAndCheckDefault(workspacename);
			koraworkspacepage.clickOnWorkspace3Dots(workspacename);
			koraworkspacepage.operationsFromWS3Dots(workspacename, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new workspace and validations");
			koraloginpage.backToHomePage(url);
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

			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.createNewWorkspaceAs(workspaceauto);
			koraworkspacepage.workspaceDirectInvite(invitemems);
			koraworkspacepage.clickOnWorkspace3Dots(workspaceauto);
			koraworkspacepage.operationsFromWS3Dots(workspaceauto, "Manage");
			koramessagespage.clickOn("Members", true);
			koraworkspacepage.validateRecentInvites(invitemems);
			koraworkspacepage.clickOnWorkspace3Dots(workspaceauto);
			koraworkspacepage.operationsFromWS3Dots(workspaceauto, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to invite members to workspace");
			koraloginpage.backToHomePage(url);
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

			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.createNewWorkspaceAs(workspacename);
			korahomepage.selectMenuOption(Messages);
			korahomepage.getActiveOptionFromLeftNav("All Messages");
			koraworkspacepage.selectWorkspace(workspacename);
			// koramessagespage.getChatHeaderName();
			String wsname = koramessagespage.enterYourMessageAs("My post in " + workspacename);
			koraworkspacepage.compareActualExpected(wsname, "General", "Default selected workspace is : ");
			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.clickOnWorkspace3Dots(workspacename);
			koraworkspacepage.operationsFromWS3Dots(workspacename, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate from filter by workspace");
			koraloginpage.backToHomePage(url);
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

			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.createNewWorkspaceAs(workspacename);
			koraworkspacepage.selectDefaultDR();
			koramessagespage.visibilityOfComposeBar(true);
			koramessagespage.verifyGroupCreationTimeline(korajusername);
			korahomepage.selectMenuOption(Workspaces);
			koraworkspacepage.clickOnWorkspace3Dots(workspacename);
			koraworkspacepage.operationsFromWS3Dots(workspacename, "Delete");
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate default DR and Timeline (General name might got changed)");
			koraloginpage.backToHomePage(url);
		}
	}
}
