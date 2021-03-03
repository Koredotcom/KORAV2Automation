package com.org.kore.element.repository;

public class ElementRepository {
	public String userName;
	public String password;

	// Web app Elements initialization
	public String nonSocUname;
	public String nonSocPasswrd;
	public String nonSocSignin;

	public String suserid = null, susernext = null, spassword = null, spasswordnext = null;

	public String footerText = null, ko365 = null, koenteremail = null, kousernext = null, kuser = null,
			kloginnext = null, konext = null, kpwd = null, kosignin = null, kstaysignin = null, ksignwithgoogle = null,
			ksignwithgooglepage = null, knext = null, kchoosetext = null, kselectaccount = null, kallow = null,
			klogout = null, klogoutyes = null, khistory = null, kheadermenu = null, kmplusicon = null,
			kmdiscussion = null, kmchat = null, kmrecent = null, kmenterparticipant = null, kmcloseconversation = null,
			kmsuggestnames = null, kmsuggestmailids = null, kmprofileicon = null, kmcomposebar = null,
			kmgroupchevronicon = null, kmgroupname = null, kmmidgroup = null, kmmidchatdesc = null, km3dots = null,
			km3dotoptions = null, kmtext = null, kmmanageclose = null, kmmembername = null, kmmuteslots = null,
			kmactiveusericon = null, kmrightchaticon = null, kmatmentionusernames = null, kmmessagebubbles = null,
			kmfirstactiveuser = null;

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
		ko365 = "//div[@class='loginButton microsoft']";
		koenteremail = "//input[@name='loginfmt']";
		kousernext = "//input[@value='Next']";
		kuser = "//input[@placeholder='Enter your email']";
		kloginnext = "//button[@class='signInNext']";
		konext = "//input[@id='idSubmit_ProofUp_Redirect']";
		kpwd = "//input[@type='password']";
		kosignin = "//input[@type='submit']";
		kstaysignin = "//input[@value='Yes']";
		konext = "//input[@value='Next']";

		ksignwithgoogle = "//div[@class='pull-left googleLoginIconW']";
		ksignwithgooglepage = "//div[@class='kHn9Lb'][text() = 'Sign in with Google']";
		kchoosetext = "//h1[@class='ahT6S  y77zqe']";
		knext = "//div[@class='VfPpkd-RLmnJb']";
		kselectaccount = "//div[@class='qQWzTd']";
		kallow = "//span[text()='Allow']";

		klogout = "//i[@class='p-icon pi pi-power-off']";
		klogoutyes = "//span[text()='Yes']";
		khistory = "//span[@class='loadHistory']";
		kheadermenu = "//span[@class='menuTabs']/a";
		kmplusicon = "//span[@class='p-icon kr-plus']";
		kmdiscussion = "//div[@class='krDropDownMenu']//*[text()='Create a Discussion Room']";
		kmchat = "//div[@class='krDropDownMenu']//*[text()='Crete a new Chat']";
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
		kmmidchatdesc = "']/../../..//div[@class='userChatDEsc']";
		kmtext = "//*[text()='";
		kmmanageclose = "//button[@aria-label='Close']";
		kmmembername = "//span[text()='Member']/../../..//div[@class='emailUi']";
		km3dots = "//div[@class='_content']/i[@class='icon __i kr-ellipsis  ']";
		km3dotoptions = "//div[@class='krDropDownMenu active  ']//div";
		kmmuteslots = "//div[@class='dorpDownBoxMute msgThreadDD']//li[@class='dorpDownBoxLI']";
		kmactiveusericon = "//div[@class='userDetails active']//span[@class='nameAvatar single']";
		kmrightchaticon = "//span[@class='chatUserIcon']";
		kmatmentionusernames = "//table[@class='mentionDialogBoxTable']//span[@class='mentionFullName']";
		kmmessagebubbles = "//div[@class='sendBubble messageBubble saveMsg']";
		kmfirstactiveuser = "//div[@class='userDetails active']//div[@class='userNameDiv']";

	}

}
