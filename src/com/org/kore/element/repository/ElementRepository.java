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
			kleftactiveoption = null, kattachment = null, kuserprofileicon = null,kloading=null, kmctopleftmenu = null,
			kmbottonleftmenu = null, kmcplusicon = null, kmconv = null, kmcomposebar = null, kmcrecent = null,
			kmremoveparticipantpopup = null, kmcenterparticipant = null, kmcloseconversation = null,
			kmcsuggestnames = null, kmcsuggestmailids = null, kmcprofileicon = null, kmcgroupchevronicon = null,
			kmcgroupname = null, kmchatheadername = null, kmcidgroup = null, kmcidchatdesc = null, kmc3dots = null,
			kmc3dotoptions = null, kmcmanageclose = null, kmcmembername = null, kmcmuteslots = null,
			kmcactiveusericon = null, kmcrightchaticon = null, kmcatmentionusernames = null, kmcmessagebubbles = null,
			kmcfirstactiveuser = null, kmcactivebackground = null, kmdiscussion = null, kmplaceholder = null,
			kmsearchsuggestions = null, kmattachfromplus = null, kmmessages = null, kmreplybubble = null,
			kmactivemsg = null, kmmessagehoveroptiontitles = null, kmmessagerighthover = null, kmchatname0 = null,
			kmchatname1 = null, kmmessagehovermoreoptions = null, kmlongtextreadmore = null, kmreadmore = null,
			kmreadless = null, kmemoji = null, kmsmiley = null, kmright3dotoptions = null, kmviewfiles = null,
			kmreminderslots = null, kmmessagedeleted = null, kmforwardmsgpopup = null, kmsendbutton = null,
			kmmessagecopied = null, kmmessageforwarded = null, kdrcidgroup = null, kdrc3dotoptionsRightPanel = null,
			kdrc3dotoptionsRightPanelOptions = null, kdrpostname0 = null, kdrpostname1 = null, kdrsettings = null,
			kdeveryoneAtnoWorkspace = null, kdselectworkspace = null, kdtoggleicontoselectWS = null,
			kddiscussionTitle = null, kddefaultAccessto = null, kdSearchboxinmsgnDR = null, kuserProfileUserName = null,
			kmdmsgordiscroom = null, kdeditpost = null, kdeditpostcomposebar = null, kdforwardpost = null,
			kdfowradpostWindow = null, kdfrwrdpostConversationname = null, kdfowradpostWindowclose = null,
			kdstartnewconversation = null, kdselectpeopleinnewconv = null, kdemailaddresstoselect = null,
			kdcreatenforwardpost = null, kdsearchbarinforwardpost = null, kdsearchresultsforwardpost = null,
			kdrsearchresultsfirstsenditem = null, kdrManageRoom3dots0 = null, kdrManageRoom3dots1 = null,
			kwmanagedrMemebrs = null, kwaddpeopleplacehilder = null, kwmemebrsinaDreamilaccess0 = null,
			kwmemebrsinaDreamilaccess1 = null, kwremovingmemebrindr = null, kwremovingmemebrindrconfirm = null,
			kwaddpeopleadinmember = null, kwpostinfonMessageinfofrom3dots = null, kdpostinfonmsginfoTitle = null,
			kwclosecommentreadpopup = null, kwstarfilledstatus = null, kwatmentionuserslist = null, kdrRoomname = null,
			kdrleaveDR = null, kdrleaveDRconfirm = null, kdrdeleteDR = null, kdrdeleteDRconfirm = null,
			kdrclosenewdrcreation = null, kdrdiscardmsg = null, kdpostviaemailtoggle = null, kdrtoggleemaillink = null,
			kdrtoggleedit = null, kdrtogglecopy = null, kdrmembers = null, kdrsettingallmembers = null,
			kdrcreatenewWsplus = null, kdrnewWorkspacename = null, kdrnewwsdonebtn = null,
			kdrcountofmembersrightpanel = null, kdradduserfromrightpanel = null, kdruaccesstype = null,
			kdrtypecommentbar = null, kwfilterbyws = null, kwcreatenew = null, kwhomeaccordions = null,
			kwjoinexisting = null, kwdefaulworkspace = null, kwdrheader = null, kworkspaces = null, kwdrgeneral = null,
			kworkspacename = null, kwleft3dots = null, kwsleft3dotoptions = null, kwyesDeletepopup = null,
			kwproceed2delete = null, kwdeletewsname = null, kwproceedDelete = null, kwcomposebar = null,
			kwinvite = null, kwsearchandaddpeople = null, kwsuggestedmailids = null, kwinviteclose = null,
			kwsendinvitation = null, kwmemslist = null;

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
		koenteremail = "//input[@name='loginfmt'] | //div[@role='heading'][text() = 'Pick an account']";
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
		kloading ="//div[@class='lds-ring']";

		kmremoveparticipantpopup = "//span[@class='p-button-text p-c'][text() = 'Remove']";
		kmctopleftmenu = "//span[@class='p-menuitem-text']";
		kmbottonleftmenu = "//div[contains(@class,'p-menuitem-link')]//span[@class='p-menuitem-text ws-name-ellipsis']";
		kmcplusicon = "//span[@class='p-icon kr-plus']";
		kmconv = "//div[@class='krDropDownMenu']//span[text() = 'Chat']";
		kmcomposebar = "//div[@placeholder='Type your message']";
		kmcrecent = "//ul[@class='p-autocomplete-items p-autocomplete-list p-component p-reset']//li[@class='p-autocomplete-list-item']";
		kmcenterparticipant = "//input[@placeholder='Enter participant name'] | //input[@placeholder='Add Participants'] | //input[@placeholder='Add people']| //input[@placeholder='Type and select people']";
		kmcloseconversation = "//span[@class='kr-closeBox']";
		kmcsuggestnames = "//div[@class='p-clearfix userDetailBox']/div[@class='userDetail']";
		kmcsuggestmailids = "//div[@class='p-clearfix userDetailBox']//div[@class='userEmailId']";
		kmcprofileicon = "//div[@class='p-clearfix userDetailBox']/div[@class='userDetail']/../div[@class='circle']";
		kmcgroupchevronicon = "//span[@class='kr-down_arrowBox']";
		kmcgroupname = "//input[@placeholder='Group Name'] | //input[@placeholder='Discussion Room Name']";
		kmchatheadername = "//div[@class='chatHeader']//span";
		kmcidgroup = "//div[@class='userNameDiv'][text()='";
		kmcidchatdesc = "']/../../..//div[@class='userChatDEsc']";
		kmc3dots = "//div[contains(@class,'_content')]/i[contains(@class, 'icon __i kr-ellipsis')]";
		kmc3dotoptions = "//div[contains(@class, 'krDropDownMenu')]//div";
		kmcmanageclose = "//button[@aria-label='Close']";
		kmcmembername = "//span[text()='Member']/../../..//div[@class='emailUi']";
		kmcmuteslots = "//div[@class='dorpDownBoxMute msgThreadDD']//li[@class='dorpDownBoxLI']";
		kmcactiveusericon = "//div[@class='userDetails active chat']//span[@class='nameAvatar single']";
		kmcrightchaticon = "//span[@class='chatUserIcon']";
		kmcatmentionusernames = "//table[@class='mentionDialogBoxTable']//span[@class='mentionFullName']";
		kmcmessagebubbles = "//div[@class='sendBubble messageBubble saveMsg']";
		kmcfirstactiveuser = "//div[@class='userDetails active chat']//div[@class='userNameDiv']";
		kmcactivebackground = "//div[@class='userDetails active chat']";
		kmplaceholder = "//input[@class='searchInput fullWidth']";
		kmsearchsuggestions = "//div[@class='heroText'][text() = '";
		kmattachfromplus = "//span[@class='addIcon kr-plus']";
		kmmessages = "//span[@class='msgText  ']//div[@class='send-message'][text()='";
		kmreplybubble = "//div[@class='replyMessage replyMessageBubble']//div[@class='replayBubbleText'][text()='";
		kmactivemsg = "//span[@class='msgText  activenavigateMsg']/..//div[@class='send-message'][text()='";
		kmmessagehoveroptiontitles = "//div[@class='msgCntrlBarParent hoverOptionsBar ']//i[@title='";
		kmmessagerighthover = "//div[@class='msgCntrlBarParent hoverOptionsBar ']";
		kmchatname0 = "//p[@class='chatUserTitle']/span[text()='";
		kmchatname1 = "']/../../../../../..//div[@class='send-message' and text()='";
		kmmessagehovermoreoptions = "//div[@class='krDropDownMenu msgOptDD active overflow']//div[text()='";
		kmlongtextreadmore = "//span[@class='readMoreText'][text()='Read more']/..//span[@class='truncateText']";
		kmreadmore = "//span[@class='readMoreText'][text()='Read more'][contains(@style,'cursor: pointer;')]";
		kmreadless = "//span[@class='readMoreText'][text()='Read less'][contains(@style,'cursor: pointer;')]";
		kmemoji = "//div[@class='emojiIconCntr']";
		kmsmiley = "//ul[@class='icons']//li[3]";
		kmright3dotoptions = "//div[@class='krDropDownMenu chatHeaderOptions active']//div";
		kmviewfiles = "//div[@class='modalHeaderSec'][text() = 'View Files']";
		kmreminderslots = "//div[@class='krDropDownMenuWithoutIcon msgRemDD active overflow']//li";
		kmmessagedeleted = "//i[@class='markdownItalic'][text() = 'This message was deleted']";
		kmforwardmsgpopup = "//span[text() = 'Forward Message']";
		kmsendbutton = "/../../../..//button[@class='sendBtn']";
		kmmessagecopied = "//span[@class='p-messages-detail'][text() = 'Message copied successfully !']";
		kmmessageforwarded = "//span[@class='p-messages-detail'][text() = 'Message forwarded successfully.']";
		kmdiscussion = "//div[@class='krDropDownMenu']//span[text() = 'Discussion Room']";

		kdrcidgroup = "//div[@class='userNameDiv drUserNameDiv'][text()='";
		kdrc3dotoptionsRightPanel = "//div[@class='chatHeader']//span[text()='";
		kdrc3dotoptionsRightPanelOptions = "//div[contains(@class, 'krDropDownMenu chatHeaderOptions')]//div";
		kdrpostname0 = "//p[@class='chatUserTitle']/span[text()='";
		kdrpostname1 = "']/../../../../../..//div[@class='dicussionPostMessage']/span[text()='";
		kdrsettings = "//span[@id='my']//i[contains(@class,'icon kr-settings')]";
		kdeveryoneAtnoWorkspace = "//span[@class='ddNoteMsg']";
		kdselectworkspace = "//span[text()='Select a Workspace']";
		kdtoggleicontoselectWS = "//i[@class='toggleIcon kr-ptburgerMenu']";
		kddiscussionTitle = "//input[@id='discussionTitle']";
		kddefaultAccessto = "//i[@class='icon kr-tick']/..//span[@class='Name']";
		kdSearchboxinmsgnDR = "//input[@class='searchInput']";
		kuserProfileUserName = "//div[@class='userProfile']/.//span[@class='userName']";
		kmdmsgordiscroom = "//div[contains(@class,'userNameDiv') and text()='";
		kdeditpost = "//i[@class='icon kr-amend-edit']";
		kdeditpostcomposebar = "//div[@class='p-dialog-titlebar']/following::div[@id='discInput'][1]";
		kdforwardpost = "//i[@class='icon kr-return']";
		kdfowradpostWindow = "//span[@class='p-dialog-title']/span[text()='Forward Post']";
		kdfrwrdpostConversationname = "//div[@class='discussionRoomTitle']/div[@class='heroText' and text()='";
		kdfowradpostWindowclose = "//span[@class='p-dialog-titlebar-close-icon pi pi-times']";
		kdstartnewconversation = "//span[text()='Start a new Chat']";
		kdselectpeopleinnewconv = "//input[contains(@placeholder,'Type and select people')]";
		kdemailaddresstoselect = "//div[@class='userEmailId' and text()='";
		kdcreatenforwardpost = "//button[@class='p-button kr-primary-btn' and text()='Create & Forward']";
		kdsearchbarinforwardpost = "//input[@class='searchInput' and @placeholder='Search people, chats & rooms']";
		kdsearchresultsforwardpost = "//div[@class='existingThreadList']/ul";
		kdrsearchresultsfirstsenditem = "//div[@class='existingThreadList']/ul[1]/li[1]/div[2]/button";
		kdrManageRoom3dots0 = "//p[@class='chatUserTitle']/span[text()='";
		kdrManageRoom3dots1 = "']/../../../../..//i[@class='p-icon _choI kr-ellipsis']";
		kwmanagedrMemebrs = "//ul[@class='nav tab-header']/li[2]/div";
		kwaddpeopleplacehilder = "//input[contains(@placeholder,'Type and select people')]";
		kwmemebrsinaDreamilaccess0 = "//div[@class='memberDetails']/div[1]//div[contains(text(),'";
		kwmemebrsinaDreamilaccess1 = "')]/..//../div[@class='right']/div";
		kwremovingmemebrindr = "//div[contains(@class,'userRoleDropDownMenu')]/ul/li[@class='remove']/span";
		kwremovingmemebrindrconfirm = "//span[@class='p-button-text p-c' and text()='Remove']";
		kwaddpeopleadinmember = "//span[@class='p-button-text p-c' and text()='Add people']";
		kwpostinfonMessageinfofrom3dots = "//*[text()='Post Info'] | //*[text()='Message Info']";
		kdpostinfonmsginfoTitle = "//span[@class='p-dialog-title']";
		kwclosecommentreadpopup = "//span[@class='kr-close']";
		kwstarfilledstatus = "//div[contains(@class,'krDropDownMenu chatHeaderOptions')]/div[1]//i[contains(@class,'icon kr-starred')]";
		kwatmentionuserslist = "//table[@class='mentionDialogBoxTable']/tbody/tr";
		kdrRoomname = "//input[@class='wsNameInput krInputText']";
		kdrleaveDR = "//span[contains(text(),'Leave Discussion Room')]";
		kdrleaveDRconfirm = "//span[@class='p-button-text p-c'][text() = 'Leave']";
		kdrdeleteDR = "//span[contains(text(),'Delete Discussion Room')]";
		kdrdeleteDRconfirm = "//span[@class='p-button-text p-c'][text() = 'Delete']";
		kdrclosenewdrcreation = "//i[@class='p-icon kr-close']";
		kdrdiscardmsg = "//span[@class='p-button-text p-c' and text()='Yes']";
		kdpostviaemailtoggle = "//div[contains(@class,'p-inputswitch p-component')]";
		kdrtoggleemaillink = "//div[@class='emailLink']";
		kdrtoggleedit = "//i[@class='icon kr-amend-edit']";
		kdrtogglecopy = "//i[@class='icon kr-copy-clone']";
		kdrmembers = "//div[text()='Members']";
		kdrsettingallmembers = "//u[text()='All Members']";
		kdrcreatenewWsplus = "//div[@class='wsImgIconCreate']/i[1]";
		kdrnewWorkspacename = "//div[@class='wsNameLabel']//following-sibling::input";
		kdrnewwsdonebtn = "//*[text()='Done']";
		kdrcountofmembersrightpanel = "//i[@class='p-icon _choI kr-members usersCountBox ']/span";
		kdradduserfromrightpanel = "//i[@class='p-icon kr-add_user_male']";
		kdruaccesstype = "//div[@class='input-group']/div[1]";
		kdrtypecommentbar = "//div[@id='discInput' and @placeholder='Type your comment']";

		kwfilterbyws = "//div[text()='Filter By Workspaces']";
		kwcreatenew = "//*[text()='Create new workspace']";
		kwhomeaccordions = "//div[@class='acc-title']";
		kwjoinexisting = "//div[@id='ws-plus-cont']//li[text()='Join Existing']";
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
