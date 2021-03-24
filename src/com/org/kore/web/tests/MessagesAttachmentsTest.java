package com.org.kore.web.tests;

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

	String korausername;
	String korapassword;

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

		korausername = dr.getValue("KORAV2", "KoraV2Web", "Username");
		korapassword = dr.getValue("KORAV2", "KoraV2Web", "Password");
		directory = System.getProperty("user.dir");
	}

	@Test(enabled = true, priority = 19)
	public void MC_uploadFileBelow25MB() throws Exception {
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
			koraloginpage.loginToKora(url, korausername, korapassword);
			korahomepage.selectMenuOption(Messages);
			koramessagespage.startNewConversationWith(recepientuser, true);
			user = koramessagespage.enterYourMessageAs(onetoonetext);
			
			korahomepage.fileUploadfrom(doc,true, "doc file");
			korahomepage.fileUploadfrom(mp4,true, "mp4 file");
			korahomepage.fileUploadfrom(pdf,true, "pdf file");
			korahomepage.fileUploadfrom(xlsx,true, "xlsx file");
			korahomepage.fileUploadfrom(png,true, "png file");
			korahomepage.fileUploadfrom(zip,true, "zip file");
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to insert image for 1to1 conversation");
		}
	}
	
	@Test(enabled = true, priority = 20)
	public void MC_uploadFileabove25MB() throws Exception {
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
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to insert image for 1to1 conversation");
		}
	}
	
	@Test(enabled = true, priority = 21)
	public void MC_uploadMultipleFiles() throws Exception {
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
			
			extent.endTest(test);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to insert image for 1to1 conversation");
		}
	}
	
}
