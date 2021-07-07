package com.org.kore.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.WAHomePage;
import com.org.kore.web.pages.WALoginPage;
import com.org.kore.web.pages.WAMessagesChatsPage;
import com.org.kore.web.pages.WAMessagesDRPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class MessagesAttachments extends DriverSetUp {

	WALoginPage koraloginpage;
	WAHomePage korahomepage;
	WAMessagesChatsPage koramessagespage;
	WAMessagesDRPage koramessagedrpage;

	String korajusername;
	String korajpassword;

	String korahusername;
	String korahpassword;

	static String user = null;
	static String directory = null;

	public MessagesAttachments() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute 121 test");

		koraloginpage = new WALoginPage(remoteDriver);
		korahomepage = new WAHomePage(remoteDriver);
		koramessagespage = new WAMessagesChatsPage(remoteDriver);
		koramessagedrpage = new WAMessagesDRPage(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");

		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");

		directory = System.getProperty("user.dir");
	}

	@Test(enabled = true, priority = 37)
	public void MC_TC11_TC30_uploadAllIndividualFilesBelow25MB() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String recepientuser = DriverSetUp.UtilityMap.get("recepientuser");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String insertdoc = DriverSetUp.UtilityMap.get("docexe");
			String doc = directory + insertdoc;
			String insertmp4 = DriverSetUp.UtilityMap.get("mp4exe");
			String mp4 = directory + insertmp4;
			String insertpdf = DriverSetUp.UtilityMap.get("pdfexe");
			String pdf = directory + insertpdf;
			String insertxlsx = DriverSetUp.UtilityMap.get("xlsxexe");
			String xlsx = directory + insertxlsx;
			String insertpng = DriverSetUp.UtilityMap.get("pngexe");
			String png = directory + insertpng;
			String insertzip = DriverSetUp.UtilityMap.get("zipexe");
			String zip = directory + insertzip;

			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", recepientuser, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			korahomepage.uploadfilesfromChats(doc, true, "doc file", false);
			korahomepage.uploadfilesfromChats(mp4, true, "mp4 file", false);
			korahomepage.uploadfilesfromChats(pdf, true, "pdf file", false);
			korahomepage.uploadfilesfromChats(xlsx, true, "xlsx file", false);
			korahomepage.uploadfilesfromChats(png, true, "png file", false);
			korahomepage.uploadfilesfromChats(zip, true, "zip file", false);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to attach different file formats for 1to1 conversation");
		}
	}

	@Test(enabled = true, priority = 38)
	public void MC_TC31_TC49_uploadMultipleFilesAndView() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String recepientuser = DriverSetUp.UtilityMap.get("recepientuser");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			String allfileexe = DriverSetUp.UtilityMap.get("allfilesexe");
			String allfiles = directory + allfileexe;
			String insertdoc = DriverSetUp.UtilityMap.get("docexe");
			String doc = directory + insertdoc;
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat", recepientuser, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);

			korahomepage.uploadfilesfromChats(doc, true, "doc file", false);
			korahomepage.uploadfilesfromChats(allfiles, true, "Multiple file formats", true);
			koramessagespage.selectOptionFromRightNav3Dots(user, "View Files");
			koramessagespage.viewFiles();
			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to attach multiple files for 1to1 conversation");
		}
	}

	@Test(enabled = true, priority = 39)
	public void MC_TC53_uploadFileabove25MBAndCancel() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String recepientuser = DriverSetUp.UtilityMap.get("recepientuser");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			// need to add file which is having more than 25 mb
			String ziptocancel = DriverSetUp.UtilityMap.get("23mbfile");
			String ziptocancel23mb = directory + ziptocancel;
			test.log(LogStatus.INFO, "Navigation url :" + url);

			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith("chat", recepientuser, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			korahomepage.cancelWhileUploadingfiles(ziptocancel23mb, "25 MB", true);

			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to insert image for 1to1 conversation");
		}
	}

	@Test(enabled = true, priority = 40)
	public void MDR_TC6_TC44_postandcommentsWithAttachments() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_DiscussionRooms");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String url = DriverSetUp.propsMap.get("weburl");

			String standarddrname = DriverSetUp.drdataMap.get("standarddr");
			String Messages = DriverSetUp.drdataMap.get("messages");
			String sampleurl = DriverSetUp.drdataMap.get("sampleurl");

			String insertdoc = DriverSetUp.UtilityMap.get("docexe");
			String doc = directory + insertdoc;
			String insertmp4 = DriverSetUp.UtilityMap.get("mp4exe");
			String mp4 = directory + insertmp4;
			String insertpdf = DriverSetUp.UtilityMap.get("pdfexe");
			String pdf = directory + insertpdf;
			String insertxlsx = DriverSetUp.UtilityMap.get("xlsxexe");
			String xlsx = directory + insertxlsx;
			String insertpng = DriverSetUp.UtilityMap.get("pngexe");
			String png = directory + insertpng;
			String insertzip = DriverSetUp.UtilityMap.get("zipexe");
			String zip = directory + insertzip;

			String drpost = "postforattachement" + korahomepage.runtimehhmmss();
			String drpostforcomment = "postforCommentattachment" + korahomepage.runtimehhmmss();
			String drcommentattchment = "Commentattachment" + korahomepage.runtimehhmmss();

			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.logoutAndReLogin(true, url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("Discussion Rooms");

			koramessagedrpage.goToGroupAndPerforminWSDR(standarddrname, false, "NA");
			koramessagespage.enterYourMessageAs(sampleurl);
			koramessagespage.captureScreenShot("Validate UI after entering Url as a post " + sampleurl);

			koramessagespage.enterYourMessageAs(drpost);
			korahomepage.uploadfilesfromDR(doc, true, "doc file", false);
			korahomepage.uploadfilesfromDR(mp4, true, "mp4 file", false);
			korahomepage.uploadfilesfromDR(pdf, true, "pdf file", false);
			korahomepage.uploadfilesfromDR(xlsx, true, "xlsx file", false);
			korahomepage.uploadfilesfromDR(png, true, "png file", false);
			korahomepage.uploadfilesfromDR(zip, true, "zip file", false);

			// post and comment post for attachments
			koramessagespage.enterYourMessageAs(drpostforcomment);
			koramessagedrpage.gotopostandcomment(standarddrname, drpostforcomment);
			korahomepage.uploadfilesfromDR(png, true, "png file", false);
			extent.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate DR post /comment with attachments");
		}
	}

}
