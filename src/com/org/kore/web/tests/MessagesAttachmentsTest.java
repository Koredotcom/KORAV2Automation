package com.org.kore.web.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.kore.testbase.DriverSetUp;
import com.org.kore.web.pages.KoraHomePage;
import com.org.kore.web.pages.KoraLoginPage;
import com.org.kore.web.pages.KoraMessagesChatsPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 *
 */

public class MessagesAttachmentsTest extends DriverSetUp {

	KoraLoginPage koraloginpage;
	KoraHomePage korahomepage;
	KoraMessagesChatsPage koramessagespage;

	String korajusername;
	String korajpassword;
	
	String korahusername;
	String korahpassword;

	static String user = null;
	static String directory =null;

	public MessagesAttachmentsTest() throws Exception {
		super();

	}

	@BeforeMethod
	public void getDriver() throws Exception {
		System.out.println("About to execute 121 test");

		koraloginpage = new KoraLoginPage(remoteDriver);
		korahomepage = new KoraHomePage(remoteDriver);
		koramessagespage = new KoraMessagesChatsPage(remoteDriver);

		korajusername = dr.getValue("KORAV2", "KoraV2james", "Username");
		korajpassword = dr.getValue("KORAV2", "KoraV2james", "Password");
		
		korahusername = dr.getValue("KORAV2", "KoraV2hana", "Username");
		korahpassword = dr.getValue("KORAV2", "KoraV2hana", "Password");
		
		directory = System.getProperty("user.dir");
	}

	/*@Test(enabled = true, priority = 21)
	public void MC_TC11_TC30_uploadFileBelow25MB() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String recepientuser = DriverSetUp.UtilityMap.get("recepientuser");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			
			String insertdoc = DriverSetUp.UtilityMap.get("docexe");
			String doc=directory + insertdoc;
			String insertmp4 = DriverSetUp.UtilityMap.get("mp4exe");
			String mp4=directory + insertmp4;
			String insertpdf = DriverSetUp.UtilityMap.get("pdfexe");
			String pdf=directory + insertpdf;
			String insertxlsx = DriverSetUp.UtilityMap.get("xlsxexe");
			String xlsx=directory + insertxlsx;
			String insertpng = DriverSetUp.UtilityMap.get("pngexe");
			String png=directory + insertpng;
			String insertzip = DriverSetUp.UtilityMap.get("zipexe");
			String zip=directory + insertzip;
			
			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat",recepientuser, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			korahomepage.uploadfilesfromAttachment(doc,true, "doc file");
			korahomepage.uploadfilesfromAttachment(mp4,true, "mp4 file");
			korahomepage.uploadfilesfromAttachment(pdf,true, "pdf file");
			korahomepage.uploadfilesfromAttachment(xlsx,true, "xlsx file");
			korahomepage.uploadfilesfromAttachment(png,true, "png file");
			korahomepage.uploadfilesfromAttachment(zip,true, "zip file");
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to attach different file formats for 1to1 conversation");
		}
	}*/
	
	@Test(enabled = true, priority = 22)
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
			String allfiles=directory + allfileexe;
			
			String insertdoc = DriverSetUp.UtilityMap.get("docexe");
			String doc=directory + insertdoc;
			
			test.log(LogStatus.INFO, "Navigation url :" + url);
			koraloginpage.loginToKora(url, korajusername, korajpassword);
			korahomepage.selectMenuOption(Messages);
			korahomepage.selectTopLeftMenuOption("All Messages");
			koramessagespage.startNewConversationWith("chat",recepientuser, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			
			korahomepage.uploadfilesfromAttachment(doc,true, "doc file");
			korahomepage.uploadfilesfromAttachment(allfiles,true, "Multiple file formats");
			koramessagespage.selectOptionFromRightNav3Dots(user, "View Files");
			extent.endTest(test);
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to attach multiple files for 1to1 conversation");
		}
	}

	@Test(enabled = false)
	public void MC_uploadFileabove25MB() throws Exception {
		try {
			test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName())
					.assignCategory("WorkAssist_Messages_Chats");
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			
			String url = DriverSetUp.propsMap.get("weburl");
			String Messages = DriverSetUp.testdataMap.get("messages");
			String recepientuser = DriverSetUp.UtilityMap.get("recepientuser");
			String onetoonetext = DriverSetUp.testdataMap.get("onetoonechat");
			
			// need to add file which is having more than 25 mb
			String insertdoc = DriverSetUp.UtilityMap.get("docexe");
			String allfiles=directory + insertdoc;
			
			test.log(LogStatus.INFO, "Navigation url :" + url);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith("chat",recepientuser, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			korahomepage.uploadfilesfromAttachment(allfiles,true, "Beyond 25 MB file");
			
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to insert image for 1to1 conversation");
		}
	}
	
}
