package com.org.kore.element.repository;

public class ElementRepository {
	public String userName;
	public String password;

	// Web app Elements initialization
	public String nonSocUname;
	public String nonSocPasswrd;
	public String nonSocSignin;

	public String suserid = null, susernext = null, spassword = null, spasswordnext = null;

	public String ko365 = null, koenteremail = null, kousernext = null, kuser = null, kloginnext = null, konext = null,
			kpwd = null, kosignin = null, kstaysignin = null, ksignwithgoogle = null, ksignwithgooglepage = null,
			knext = null, kchoosetext = null, kselectaccount = null, kallow = null, klogout = null, klogoutyes = null,
			khistory = null, ktext = null, kheadermenu = null, ksinglquote = null, klogo = null, kcomposebar = null,
			kleftactiveoption = null, kattachment = null, kmcleftmenu = null, kmcplusicon = null, kmchat = null,
			kmcomposebar = null, kmcrecent = null, kmcenterparticipant = null, kmcloseconversation = null,
			kmcsuggestnames = null, kmcsuggestmailids = null, kmcprofileicon = null, kmcgroupchevronicon = null,
			kmcgroupname = null, kmcidgroup = null, kmcidchatdesc = null, kmc3dots = null, kmc3dotoptions = null,
			kmcmanageclose = null, kmcmembername = null, kmcmuteslots = null, kmcactiveusericon = null,
			kmcrightchaticon = null, kmcatmentionusernames = null, kmcmessagebubbles = null, kmcfirstactiveuser = null,
			kmcactivebackground = null, kmdiscussion = null, kmplaceholder = null, kmsearchsuggestions = null,
			kmattachfromplus = null, kwplusicon = null, kwcreatenew = null, kwjoinexisting = null,
			kworkplaceholder = null, kwdefaulworkspace = null, kwdrheader = null, kworkspaces = null,
			kwdrgeneral = null, kworkspacename = null, kwleft3dots = null, kwsleft3dotoptions = null,
			kwyesDeletepopup = null, kwproceed2delete = null, kwdeletewsname = null, kwproceedDelete = null,
			kwcomposebar = null, kwinvite = null, kwsearchandaddpeople = null, kwsuggestedmailids = null,
			kwinviteclose = null, kwsendinvitation = null, kwmemslist = null;

	public void repoIOS() {

	}

	public void repoAnd() {

	}

	public void selWeb() {

		suserid = "//*[@id='username']";
		susernext = "//*[@id='usernameSubmitButton']";
		spassword = "//*[@id='password']";
		spasswordnext = "//*[@id='submitButton']";

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
		ktext = "//*[text()='";
		kheadermenu = "//span[@class='menuTabs']/a";
		ksinglquote = "']";
		klogo = "//span[@class='logoSpan']";
		kcomposebar = "//div[contains(@placeholder,'Type your')]";
		kleftactiveoption = "//li[@class='active p-menuitem']//span[@class='p-menuitem-text']";
		kattachment = "//i[@class='p-icon kr-discussion-attachment emojiBtn']";

		kmcleftmenu = "//span[@class='p-menuitem-text']";
		kmcplusicon = "//span[@class='p-icon kr-plus']";
		kmchat = "//div[@class='krDropDownMenu']//*[text()='Crete a new Chat']";
		kmcomposebar = "//div[@placeholder='Type your message']";
		kmcrecent = "//span[@class='recenText']";
		kmcenterparticipant = "//input[@placeholder='Enter participant name']";
		kmcloseconversation = "//span[@class='kr-closeBox']";
		kmcsuggestnames = "//div[@class='p-clearfix userDetailBox']/div[@class='userDetail']";
		kmcsuggestmailids = "//div[@class='p-clearfix userDetailBox']//div[@class='userEmailId']";
		kmcprofileicon = "//div[@class='p-clearfix userDetailBox']/div[@class='userDetail']/../div[@class='circle']";
		kmcgroupchevronicon = "//span[@class='kr-down_arrowBox']";
		kmcgroupname = "//input[@placeholder='Group Name']";
		kmcidgroup = "//div[@class='userNameDiv'][text()='";
		kmcidchatdesc = "']/../../..//div[@class='userChatDEsc']";
		kmc3dots = "//div[@class='_content']/i[@class='icon __i kr-ellipsis  ']";
		kmc3dotoptions = "//div[@class='krDropDownMenu active  ']//div";
		kmcmanageclose = "//button[@aria-label='Close']";
		kmcmembername = "//span[text()='Member']/../../..//div[@class='emailUi']";
		kmcmuteslots = "//div[@class='dorpDownBoxMute msgThreadDD overflow']//li[@class='dorpDownBoxLI']";
		kmcactiveusericon = "//div[@class='userDetails active']//span[@class='nameAvatar single']";
		kmcrightchaticon = "//span[@class='chatUserIcon']";
		kmcatmentionusernames = "//table[@class='mentionDialogBoxTable']//span[@class='mentionFullName']";
		kmcmessagebubbles = "//div[@class='sendBubble messageBubble saveMsg']";
		kmcfirstactiveuser = "//div[@class='userDetails active']//div[@class='userNameDiv']";
		kmcactivebackground = "//div[@class='userDetails active']";
		kmplaceholder = "//input[@class='searchInput fullWidth']";
		kmsearchsuggestions = "//div[@class='p-menuitem-link']//div[@class='heroText']//*[text()='";
		kmattachfromplus = "//span[@class='addIcon kr-plus']";
		kmdiscussion = "//div[@class='krDropDownMenu']//*[text()='Create a Discussion Room']";

		kwplusicon = "//span[@id='ws-plus-button']";
		kwcreatenew = "//div[@id='ws-plus-cont']//li[text()='Create New']";
		kwjoinexisting = "//div[@id='ws-plus-cont']//li[text()='Join Existing']";
		kworkplaceholder = "//input[@placeholder='Workspace or board']";
		kwdefaulworkspace = "//span[@id='ws-header-name']";
		kwdrheader = "//div[@class='_header']//*[text()='Discussion Rooms']";
		kwdrgeneral = "//div[@class='userNameDiv'][text()='General']";
		kworkspaces = "//ul[@class='p-menu-list p-reset']//div[text() = 'Filter By Workspaces']/../..//span[@class='p-menuitem-text ws-name-ellipsis']";
		kworkspacename = "//span[@class='p-menuitem-text ws-name-ellipsis'][text()='";
		kwleft3dots = "/../..//span[@class='p-icon right kr-ellipsis']";
		kwsleft3dotoptions = "//div[@class='ws-plus-cont']//li[text()='";
		kwyesDeletepopup = "//*[text()='Yes, Delete Workspace']";
		kwproceed2delete = "kwproceed2delete";
		kwdeletewsname = "//input[@class='deleteCnfInput ']";
		kwproceedDelete = "//*[text()='Proceed to Delete']";
		kwcomposebar = "//div[@placeholder='Type your post']";
		kwinvite = "//div[@class='zoomValue invite'][text() = 'Invite']";
		kwsearchandaddpeople = "//input[@placeholder='Search and add people']";
		kwsuggestedmailids = "//div[@class='member-item-main']//div[@class='member-item-email']";
		kwinviteclose = "//button[@aria-label='Close']";
		kwsendinvitation = "//*[text()='Send Invitation']";
		kwmemslist = "//div[@class='userEmail']";

	}

}
