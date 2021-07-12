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
		System.out.println("About to execute Before Methos");

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

	@Test(enabled = true, priority = 61)
	public void WS_TC1_verifyWorkspaceAccordions() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Workspaces");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.wsdataMap.get("workspaces");
			String wsactaccordions = DriverSetUp.wsdataMap.get("wsccordions");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waloginpage.loginToKora(url, korajusername, korajpassword);
			wahomepage.selectMenuOption(Workspaces);
			waworkspacepage.verifyDisplayOfAccordions(er.kwhomeaccordions,wsactaccordions);
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate create new workspace and validations");
			waloginpage.backToHomePage(url);
		}
	}
	
	@Test(enabled = true, priority = 61)
	public void WS_TC2_createWorkspaceAnddeleteWorkspace() throws Exception {
		String url =null;
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Workspaces");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.wsdataMap.get("messages");
			String wsname = DriverSetUp.wsdataMap.get("workspaceauto");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			waworkspacepage.createNewWorkspaceAndCheckDefault(wsname);
			wahomepage.selectMenuOption(Messages);
			waworkspacepage.clickOnWorkspace3Dots(wsname);
			waworkspacepage.operationsFromWS3Dots(wsname, "Delete");
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
					.assignCategory("WorkAssist_Workspaces");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.wsdataMap.get("workspaces");
			String workspaceauto = DriverSetUp.wsdataMap.get("workspaceauto");
			String invitemems = DriverSetUp.wsdataMap.get("workspacemems");
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
					.assignCategory("WorkAssist_Workspaces");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.wsdataMap.get("workspaces");
			String Messages = DriverSetUp.wsdataMap.get("messages");
			String wsname = DriverSetUp.wsdataMap.get("workspaceauto");
			test.log(LogStatus.INFO, "Navigation url :" + url);

			wahomepage.selectMenuOption(Workspaces);
			waworkspacepage.createNewWorkspaceAs(wsname);
			wahomepage.selectMenuOption(Messages);
			wahomepage.getActiveOptionFromLeftNav("All Messages");
			waworkspacepage.selectWorkspace(wsname);
			// wamessageschatspage.getChatHeaderName();
		//	String wsname = wamessageschatspage.enterYourMessageAs("My post in " + wsname);
			waworkspacepage.compareActualExpected(wsname, "General", "Default selected workspace is : ");
			wahomepage.selectMenuOption(Workspaces);
			waworkspacepage.clickOnWorkspace3Dots(wsname);
			waworkspacepage.operationsFromWS3Dots(wsname, "Delete");
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
					.assignCategory("WorkAssist_Workspaces");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			url = DriverSetUp.propsMap.get("weburl");
			String Workspaces = DriverSetUp.wsdataMap.get("workspaces");
			String workspacename = DriverSetUp.wsdataMap.get("workspacename910");
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
