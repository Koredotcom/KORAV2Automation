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
			kleftactiveoption = null, kattachment = null, kuserprofileicon = null, kmctopleftmenu = null,
			kmbottonleftmenu = null, kmcplusicon = null, kmconv = null, kmcomposebar = null, kmcrecent = null,
			kmremoveparticipantpopup = null, kmcenterparticipant = null, kmcloseconversation = null,
			kmcsuggestnames = null, kmcsuggestmailids = null, kmcprofileicon = null, kmcgroupchevronicon = null,
			kmcgroupname = null, kmcidgroup = null, kmcidchatdesc = null, kmc3dots = null, kmc3dotoptions = null,
			kmcmanageclose = null, kmcmembername = null, kmcmuteslots = null, kmcactiveusericon = null,
			kmcrightchaticon = null, kmcatmentionusernames = null, kmcmessagebubbles = null, kmcfirstactiveuser = null,
			kmcactivebackground = null, kmdiscussion = null, kmplaceholder = null, kmsearchsuggestions = null,
			kmattachfromplus = null, kmmessages = null, kmmessagehoveroptiontitles = null, kmmessagehover3dots = null,
			kmmessagehoverreplyback = null, kmmessagehovermorecopy = null, kmlongtextreadmore = null, kmreadmore = null,
			kmreadless = null, kmemoji = null, kmsmiley = null, kwfilterbyws = null, kwplusicon = null,
			kwcreatenew = null, kwjoinexisting = null, kworkplaceholder = null, kwdefaulworkspace = null,
			kwdrheader = null, kworkspaces = null, kwdrgeneral = null, kworkspacename = null, kwleft3dots = null,
			kwsleft3dotoptions = null, kwyesDeletepopup = null, kwproceed2delete = null, kwdeletewsname = null,
			kwproceedDelete = null, kwcomposebar = null, kwinvite = null, kwsearchandaddpeople = null,
			kwsuggestedmailids = null, kwinviteclose = null, kwsendinvitation = null, kwmemslist = null,
			kdrcidgroup = null, kdrc3dotoptionsRightPanel = null, kdrc3dotoptionsRightPanelOptions = null,
			kdrpostname0 = null, kdrpostname1 = null, kdrsettings = null, kdeveryoneAtnoWorkspace = null,
			kdselectworkspace = null, kdtoggleicontoselectWS = null, kddiscussionTitle = null, kddefaultAccessto = null,
			kdSearchboxinmsgnDR = null, kuserProfileUserName = null, kmdmsgordiscroom = null, kdeditpost = null,
			kdeditpostcomposebar = null, kdforwardpost = null, kdfowradpostWindow = null,
			kdfrwrdpostConversationname = null, kdfowradpostWindowclose = null, kdstartnewconversation = null,
			kdselectpeopleinnewconv = null, kdemailaddresstoselect = null, kdcreatenforwardpost = null,
			kdsearchbarinforwardpost = null, kdsearchresultsforwardpost = null, kdrsearchresultsfirstsenditem = null;;

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
		klogout = "//i[@class='p-icon pi pi-power-off'] | //i[@class='icon kr-leaveDiscussion'] |//i[@class='icon kr-shutdown']";
		klogoutyes = "//span[text()='Yes']";
		khistory = "//span[@class='loadHistory']";
		ktext = "//*[text()='";
		kheadermenu = "//span[@class='menuTabs']/a";
		ksinglquote = "']";
		klogo = "//span[@class='logoSpan']";
		kcomposebar = "//div[contains(@placeholder,'Type your')]";
		kleftactiveoption = "//li[@class='active p-menuitem']//span[@class='p-menuitem-text']";
		kattachment = "//i[@class='p-icon kr-discussion-attachment emojiBtn']";
		kuserprofileicon = "//div[@class='userIntial']";

		kmremoveparticipantpopup = "//span[@class='p-button-text p-c'][text() = 'Remove']";
		kmctopleftmenu = "//span[@class='p-menuitem-text']";
		kmbottonleftmenu = "//div[contains(@class,'p-menuitem-link')]//span[@class='p-menuitem-text ws-name-ellipsis']";
		kmcplusicon = "//span[@class='p-icon kr-plus']";
		kmconv = "//div[@class='krDropDownMenu']//div[text() = 'Conversation']";
		kmcomposebar = "//div[@placeholder='Type your message']";
		kmcrecent = "//ul[@class='p-autocomplete-items p-autocomplete-list p-component p-reset']//li[@class='p-autocomplete-list-item']";
		kmcenterparticipant = "//input[@placeholder='Enter participant name'] | //input[@placeholder='Add Participants'] | //input[@placeholder='Add people']";
		kmcloseconversation = "//span[@class='kr-closeBox']";
		kmcsuggestnames = "//div[@class='p-clearfix userDetailBox']/div[@class='userDetail']";
		kmcsuggestmailids = "//div[@class='p-clearfix userDetailBox']//div[@class='userEmailId']";
		kmcprofileicon = "//div[@class='p-clearfix userDetailBox']/div[@class='userDetail']/../div[@class='circle']";
		kmcgroupchevronicon = "//span[@class='kr-down_arrowBox']";
		kmcgroupname = "//input[@placeholder='Group Name'] | //input[@placeholder='Discussion Room Name']";
		kmcidgroup = "//div[@class='userNameDiv'][text()='";
		kmcidchatdesc = "']/../../..//div[@class='userChatDEsc']";
		kmc3dots = "//div[contains(@class,'_content')]/i[contains(@class, 'icon __i kr-ellipsis')]";
		kmc3dotoptions = "//div[contains(@class, 'krDropDownMenu')]//div";
		kmcmanageclose = "//button[@aria-label='Close']";
		kmcmembername = "//span[text()='Member']/../../..//div[@class='emailUi']";
		kmcmuteslots = "//div[@class='dorpDownBoxMute msgThreadDD']//li[@class='dorpDownBoxLI']";
		kmcactiveusericon = "//div[@class='userDetails active']//span[@class='nameAvatar single']";
		kmcrightchaticon = "//span[@class='chatUserIcon']";
		kmcatmentionusernames = "//table[@class='mentionDialogBoxTable']//span[@class='mentionFullName']";
		kmcmessagebubbles = "//div[@class='sendBubble messageBubble saveMsg']";
		kmcfirstactiveuser = "//div[@class='userDetails active']//div[@class='userNameDiv']";
		kmcactivebackground = "//div[@class='userDetails active']";
		kmplaceholder = "//input[@class='searchInput fullWidth']";
		kmsearchsuggestions = "//div[@class='p-menuitem-link']//div[@class='heroText']//*[text()='";
		kmattachfromplus = "//span[@class='addIcon kr-plus']";
		kmmessages = "//span[@class='msgText ']//div[@class='send-message'][text()='";
		kmmessagehoveroptiontitles = "//div[@class='msgCntrlBar _content']/i[@title='";
		kmmessagehover3dots = "//div[@class='msgCntrlBar _content']//i[contains(@class,'icon __i kr-ellipsis')]";
		kmmessagehoverreplyback = "//div[@class='msgCntrlBar _content']//i[@class='icon __i kr-return replyButton']";
		kmmessagehovermorecopy = "//div[@class='msgCntrlBar _content']//div[text()='Copy']";
		kmlongtextreadmore = "//span[@class='readMoreText'][text()='Read more']/..//span[@class='truncateText']";
		kmreadmore = "//span[@class='readMoreText'][text()='Read more'][contains(@style,'cursor: pointer;')]";
		kmreadless = "//span[@class='readMoreText'][text()='Read less'][contains(@style,'cursor: pointer;')]";
		kmemoji = "//div[@class='emojiIconCntr']";
		kmsmiley = "//ul[@class='icons']//li[3]";
		kmdiscussion = "//div[@class='krDropDownMenu']//div[text() = 'Discussion Room']";

		kwfilterbyws = "//div[text()='Filter By Workspaces']";
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

		kdrcidgroup = "//div[@class='userNameDiv drUserNameDiv'][text()='";
		kdrc3dotoptionsRightPanel = "//div[@class='chatHeader']//span[text()='";
		kdrc3dotoptionsRightPanelOptions = "//div[contains(@class, 'krDropDownMenu chatHeaderOptions')]//div";
		kdrpostname0 = "//p[@class='chatUserTitle']/span[text()='";
		kdrpostname1 = "']/../../../../../..//div[@class='dicussionPostMessage']/span[@class='truncateText' and text()='";
		kdrsettings = "//span[@id='my']//i[contains(@class,'icon kr-settings')]";
		kdeveryoneAtnoWorkspace = "//span[@class='ddNoteMsg']";
		kdselectworkspace = "//span[text()='Select Workspace']";
		kdtoggleicontoselectWS = "//i[@class='toggleIcon kr-ptburgerMenu']";
		kddiscussionTitle = "//input[@id='discussionTitle']";
		kddefaultAccessto = "//i[@class='icon kr-tick']/..//span[@class='Name']";
		kdSearchboxinmsgnDR = "//input[@class='searchInput']";
		kuserProfileUserName = "//div[@class='userProfile']/.//span[@class='userName']";
		kmdmsgordiscroom = "//div[contains(@class,'userNameDiv') and text()='";
		kdeditpost = "//i[@class='icon kr-amend-edit']";
		kdeditpostcomposebar = "//div[@class='p-dialog-titlebar']/following::div[@id='discInput'][1]";
		kdforwardpost = "//i[@class='icon kr-return']";
		kdfowradpostWindow = "//div[@class='p-dialog p-component ForwardPostAndMsgModal p-dialog-enter-done']/div[1]/span[1]/span[text()='Forward Post']";
		kdfrwrdpostConversationname = "//div[@class='discussionRoomTitle']/div[@class='heroText' and text()='";
		kdfowradpostWindowclose = "//span[@class='p-dialog-titlebar-close-icon pi pi-times']";
		kdstartnewconversation = "//span[text()='Start a new conversation']";
		kdselectpeopleinnewconv = "//input[contains(@placeholder,'Type and select people')]";
		kdemailaddresstoselect = "//div[@class='userEmailId' and text()='";
		kdcreatenforwardpost = "//button[@class='p-button kr-primary-btn' and text()='Create & Forward']";
		kdsearchbarinforwardpost = "//input[@class='searchInput' and @placeholder='Search people, chats & rooms']";
		kdsearchresultsforwardpost = "//div[@class='existingThreadList']/ul";
		kdrsearchresultsfirstsenditem = "//div[@class='existingThreadList']/ul[1]/li[1]/div[2]/button";

	}

}
