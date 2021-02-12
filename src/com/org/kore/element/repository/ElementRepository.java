package com.org.kore.element.repository;

public class ElementRepository {
	public String userName;
	public String password;

	// Web app Elements initialization
	public String nonSocUname;
	public String nonSocPasswrd;
	public String nonSocSignin;

	public String suserid = null, susernext = null, spassword = null, spasswordnext = null;

	public String footerText = null, kuser = null, kpwd = null, kloginnext = null, ksignwithgoogle = null,
			ksignwithgooglepage = null, knext = null, kchoosetext = null, kselectaccount = null, kallow = null,
			khistory = null, kheadermenu = null, kmplusicon = null, kmrecent = null, kmenterparticipant = null,
			kmcloseconversation = null, kmsuggestnames = null, kmsuggestmailids = null, kmprofileicon = null,
			kmcomposebar = null, kmgroupchevronicon = null, kmgroupname = null, kmmidgroup = null,kmmidchatdesc=null, km3dotoptions = null,
			km3dotoptionssize = null, kmtext = null, kmmanageclose = null, kmmembername = null;

	public void repoIOS() {
		footerText = "//*[@placeholder='Username']";

	}

	public void repoAnd() {

		footerText = "//*[@class='row footerStripe']/div[1]";
	}

	public void selWeb() {

		suserid = "//*[@id='username']";
		susernext = "//*[@id='usernameSubmitButton']";
		spassword = "//*[@id='password']";
		spasswordnext = "//*[@id='submitButton']";

		footerText = "//*[@class='row footerStripe']/div[1]";
		kuser = "//input[@placeholder='Enter your email']";
		kpwd = "//input[@type='password']";
		kloginnext = "//button[@class='signInNext']";
		ksignwithgoogle = "//div[@class='pull-left googleLoginIconW']";
		ksignwithgooglepage = "//div[@class='kHn9Lb'][text() = 'Sign in with Google']";
		kchoosetext = "//h1[@class='ahT6S  y77zqe']";
		knext = "//div[@class='VfPpkd-RLmnJb']";
		kselectaccount = "//div[@class='qQWzTd']";
		kallow = "//span[text()='Allow']";
		khistory = "//span[@class='loadHistory']";
		kheadermenu = "//span[@class='menuTabs']/a";
		kmplusicon = "//span[@class='p-icon kr-plus']";
		kmrecent = "//span[@class='recenText']";
		kmenterparticipant = "//input[@placeholder='Enter participant name']";
		kmcloseconversation = "//span[@class='kr-closeBox']";
		kmsuggestnames = "//div[@class='p-clearfix userDetailBox']/div[@class='userDetail']";
		kmsuggestmailids = "//div[@class='p-clearfix userDetailBox']//div[@class='userEmailId']";
		kmprofileicon = "//div[@class='p-clearfix userDetailBox']/div[@class='userDetail']/../div[@class='circle']";
		kmgroupchevronicon = "//span[@class='kr-down_arrowBox']";
		kmgroupname = "//input[@placeholder='Group Name']";
		kmcomposebar = "//div[@placeholder='Type your message']";
		kmmidgroup = "//div[@class='userNameDiv'][text()='";
		kmmidchatdesc="']/../../..//div[@class='userChatDEsc']";
		km3dotoptions = "//div[@class='krDropDownMenu active']/div[text()='";
		km3dotoptionssize = "//div[@class='krDropDownMenu active']/div/i";
		kmtext = "//*[text()='";
		kmmanageclose = "//button[@aria-label='Close']";
		kmmembername = "//span[text()='Member']/../../..//div[@class='emailUi']";

	}

}
