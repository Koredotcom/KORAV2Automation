package com.org.kore.web.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.org.kore.element.repository.ElementRepository;
import com.org.kore.testbase.DriverSetUp;
import com.org.kore.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Jay
 * @Description : All the functions related to Messages page
 *
 */

public class KoraMessagesChatsPage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;

	public KoraMessagesChatsPage(RemoteWebDriver remoteWebDriver) {
		super(remoteWebDriver);
		cf = new CPCommonFunctions(remoteWebDriver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * To validate message screen
	 * 
	 * @throws Exception
	 */
	public void messagesScreenValidations() throws Exception {
		// waitTillappear(er.kmcplusicon, "xpath", "Plus icon");
		String plusicon = null;
		plusicon = getAttributeValue(er.kmcplusicon, "title");
		if (!plusicon.equalsIgnoreCase("New Conversation"))
			test.log(LogStatus.FAIL, "For plus icon, New conversation text is not displaying".toString()
					+ test.addScreenCapture(takeScreenShot()));
		test.log(LogStatus.INFO, "For + icon text displayed as : <b>" + plusicon + "</b>");
	}

	/**
	 * @Description : To select an option after clicking on plus icon
	 * @param userchoice
	 *            : this should be either chat or discussion
	 * @throws Exception
	 */
	public void newChatOrDiscussion(String conversationorDR) throws Exception {
		click(er.kmcplusicon, "Plus icon to start new" + conversationorDR);

		if (conversationorDR.toLowerCase().contains("chat")) {
			click(er.kmconv, "Create a new Chat");
		} else if (conversationorDR.toLowerCase().contains("discussion")) {
			click(er.kmdiscussion, "Create a Discussion Room");
		} else {
			test.log(LogStatus.FAIL, "Please provide valid userchaoice after clicking on + icon".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}
	}

	public void checkDefaultFocus_Recents() throws Exception {
		try {
			newChatOrDiscussion("chat");
			Thread.sleep(2000);
			List<WebElement> recents = remoteDriver.findElements(By.xpath(er.kmcrecent));
			if (recents.size() > 0) {
				test.log(LogStatus.PASS, "For a new chat displayed " + recents.size() + " Recent suggestions".toString()
						+ test.addScreenCapture(takeScreenShot()));
				test.log(LogStatus.PASS, "For a new chat default cursor focus is on Enter Participant name field");
			} else {
				test.log(LogStatus.FAIL, "For a new chat displayed " + recents.size() + " Recent suggestions".toString()
						+ test.addScreenCapture(takeScreenShot()));
				test.log(LogStatus.FAIL, "For a new chat default cursor focus is not on Enter Participant name field");
			}
			click(er.kmcloseconversation, "Close");
		} catch (Exception e) {
			click(er.kmcloseconversation, "Close");
			test.log(LogStatus.FAIL, "Plus icon or close icon elements got updated".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}

	}

	/**
	 * 
	 * @param nameorletter
	 *            : To check the suggestions based on this parameter
	 * @throws Exception
	 */
	public void checkMatchesWith(String nameorletter) throws Exception {
		try {
			int i = 1;
			// click(er.kmplusicon, "New Conversation");
			newChatOrDiscussion("chat");
			click(er.kmcenterparticipant, "Enter participant name");
			enterText(er.kmcenterparticipant, nameorletter, "xpath", "Participant name");
			Thread.sleep(5000);
			waitTillappear(er.kmcsuggestnames, "xpath", "Suggestions");
			test.log(LogStatus.INFO, test.addScreenCapture(takeScreenShot()));
			List<WebElement> msuggestions = remoteDriver.findElements(By.xpath(er.kmcsuggestnames));
			test.log(LogStatus.INFO,
					"Total suggestions displayed with <b>" + nameorletter + "</b> is : " + msuggestions.size());
			System.out.println("Displayed suggestion list is : " + msuggestions.size());
			for (WebElement e : msuggestions) {
				String username = getText("(" + er.kmcsuggestnames + "//div[1])" + "[" + i + "]");
				String firstchar = cf.getFirstChar(username);
				String profile = getText("(" + er.kmcsuggestnames + "/../div[@class='circle'])" + "[" + i + "]");
				if (!firstchar.equalsIgnoreCase(profile))
					test.log(LogStatus.FAIL, "Displayed incorrect profile icon for  <b>" + username + "</b> i.e. : "
							+ "<b>" + profile + "</b>");
				test.log(LogStatus.INFO, "Displayed profile icon for <b>" + e.getText().trim()
						+ "</b> is matching with first character : " + "<b>" + profile + "</b>");
				i++;
			}
			test.log(LogStatus.INFO, test.addScreenCapture(takeScreenShot()));
			click(er.kmcloseconversation, "Close");
			test.log(LogStatus.INFO, "Closed new conversation view");

		} catch (Exception e) {
			click(er.kmcloseconversation, "Close");
			test.log(LogStatus.FAIL, "Failed to check the user suggestions for new conversation");
			test.log(LogStatus.FAIL, "Something went wrong and displayed incorrect screen");
		}
	}

	// Will change this to Do while Logic so that one method would solve our
	// req
	/**
	 * 
	 * @param participantlist
	 *            : Input parameter from Json file to add these participants
	 * @param plusicon
	 *            : If it is true, then it will click on + icon
	 * @throws Exception
	 *             : Fail, when unable to select the provided participant
	 */
	public void startNewConversationWith(String conversationorDR, String participantlist, boolean plusicon)
			throws Exception {

		try {
			if (plusicon)
				newChatOrDiscussion(conversationorDR);
			click(er.kmcenterparticipant, "Enter participant name");
			if (participantlist.contains(",")) {
				String result[] = participantlist.trim().split("\\s*,\\s*");
				for (String part : result) {
					System.out.println(part);
					select(part);
				}
			} else {
				select(participantlist);
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to select the mentioned participant");

		}
	}

	/**
	 * 
	 * @param participant
	 *            : To select the user from the suggestion based on entered text
	 * @throws Exception
	 */
	public void select(String participant) throws Exception {

		enterText(er.kmcenterparticipant, participant, "xpath", "Participant name");
		Thread.sleep(1000);
		waitTillappear(er.kmcsuggestmailids, "xpath", "Suggested emails");
		List<WebElement> mailid = remoteDriver.findElements(By.xpath(er.kmcsuggestmailids));
		for (WebElement e : mailid) {
			e.getText().trim();
			if (e.getText().trim().equalsIgnoreCase(participant)) {
				e.click();
				test.log(LogStatus.INFO, "With search criteria displayed<b> "+participant+" </b> and got selected".toString() + test.addScreenCapture(takeScreenShot()));
				break;
			}
		}
	}

	/**
	 * 
	 * @param groupname
	 *            : This will be your group name for a new group
	 * @throws Exception
	 */
	public void createGroupAs(String groupname) throws Exception {
		try {
			click(er.kmcgroupchevronicon, "Chevron Icon");
			click(er.kmcgroupname, "GroupName");
			Thread.sleep(1000);
			enterText(er.kmcgroupname, groupname, "GroupName");
			test.log(LogStatus.INFO, "Group created successfully as <b>" + groupname + "</b>".toString());
			test.log(LogStatus.INFO, test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to create Groupname");
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
		}
	}

	/**
	 * @param enterthistext
	 *            : Mentioned text from here will be send as a message
	 * @throws Exception
	 */
	public String enterYourMessageAs(String enterthistext) throws Exception {
		String chatheadername = null;
		try {
			WebElement compose = remoteDriver.findElement(By.xpath(er.kcomposebar));
			compose.sendKeys(enterthistext, Keys.ENTER);
			Thread.sleep(4000);
			chatheadername = getText("//div[@class='chatHeader']//span");
			test.log(LogStatus.INFO, "In<b> " + chatheadername + " </b>Entered message as " + enterthistext
					+ " ".toString() + test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Compose bar or Chat header name elements might got updated ".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}

		return chatheadername;
	}

	/**
	 * @param enterthistext
	 *            : Mentioned text from here will be send as a message
	 * @throws Exception
	 */
	public void enterYourEmojiWithText(boolean withtext, String enterthistext) throws Exception {
		try {
			WebElement compose = remoteDriver.findElement(By.xpath(er.kcomposebar));
			click(er.kmemoji, "Emoji");
			test.log(LogStatus.WARNING,
					"Emojis displayed, requires human eye to check the UI from the below screenshot".toString()
							+ test.addScreenCapture(takeScreenShot()));
			click(er.kmsmiley, "Smiley Emoju");
			if (withtext)
				compose.sendKeys(enterthistext, Keys.ENTER);
			compose.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			test.log(LogStatus.PASS, "After sending the emoji emoji popup should get disappear".toString()
					+ test.addScreenCapture(takeScreenShot()));
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Emoji's paths might have changed".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}

	public String getChatHeaderName() throws Exception {
		String chatheadername = null;
		try {
			chatheadername = getText("//div[@class='chatHeader']//span");
			if (chatheadername.equalsIgnoreCase("and NaN others")) {
				test.log(LogStatus.FAIL, "Displayed invalid group name as and NaN others".toString()
						+ test.addScreenCapture(takeScreenShot()));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"After creating workspace when user navigates to Messages, chat header is not displaying".toString()
							+ test.addScreenCapture(takeScreenShot()));
		}
		return chatheadername;
	}

	public String getFirstActiveUser(String expecteduser, boolean check) throws Exception {
		String activeuser = null;
		try {
			activeuser = getText(er.kmcfirstactiveuser);
			test.log(LogStatus.INFO, "Current active user is " + activeuser);
			boolean actuser = remoteDriver
					.findElements(By.xpath(er.kmcfirstactiveuser + "[text()='" + expecteduser + "']")).size() > 0;
			if (actuser == check) {
				test.log(LogStatus.PASS, "Current active user is displayed as expected i.e. <b>" + activeuser
						+ "</b> ".toString() + test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.PASS, "Current active user Actual : " + activeuser + " Expected : " + expecteduser
						+ " ".toString() + test.addScreenCapture(takeScreenShot()));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Failed to get active username".toString() + test.addScreenCapture(takeScreenShot()));
		}
		return activeuser;
	}

	public String getActiveLabelBackgroundColor(String expected) throws Exception {
		String actbckgclr = null;
		try {
			String cted = "rgba(231, 241, 255, 1)";
			actbckgclr = remoteDriver.findElement(By.xpath("//div[@class='userDetails active chat']"))
					.getCssValue("background-color");
			if (cted.equals(actbckgclr)) {
				test.log(LogStatus.PASS, "Active thread highlighted in light color with RGBA values as : " + cted);
			} else {
				test.log(LogStatus.FAIL,
						"Active thread was not highlighted in light color displayed RGBA values are : " + cted);
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "For active thread there is no background color".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}
		return actbckgclr;

	}

	/**
	 * @Description : To get right side profile icons count
	 * @return
	 * @throws Exception
	 */
	public int profileAvtarCount() throws Exception {
		String count = null;
		int i = 0;
		try {
			boolean tripleflag = false;
			boolean countavtarflag = false;
			boolean topavtarflag = false;

			tripleflag = remoteDriver.findElements(By.xpath(er.kmcrightchaticon + "//span[@class='nameAvatar triple']"))
					.size() > 0;
			countavtarflag = remoteDriver.findElements(By.xpath(er.kmcrightchaticon + "//span[@class='countAvatar']"))
					.size() > 0;
			topavtarflag = remoteDriver.findElements(By.xpath(er.kmcrightchaticon + "//span[@class='topAvatar']"))
					.size() > 0;

			if (tripleflag) {

				if (tripleflag && countavtarflag) {
					count = getText(er.kmcrightchaticon + "//span[@class='countAvatar']");
					i = Integer.parseInt(count);
					i = i + 2;
					test.log(LogStatus.PASS, "This group is having <b>" + i + " </b>participants".toString()
							+ test.addScreenCapture(takeScreenShot()));
				} else if ((tripleflag && topavtarflag)) {
					count = getText(er.kmcrightchaticon + "//span[@class='topAvatar']");
					test.log(LogStatus.PASS, "This group is having only 3 participants".toString()
							+ test.addScreenCapture(takeScreenShot()));
					i = 3;
				}

			} else {
				count = getText(er.kmcrightchaticon + "//span[@class='nameAvatar single']");
				test.log(LogStatus.FAIL,
						"Seems it is 1 2 1 Conversation. Try with group conversation to work with @mentions functionality "
								.toString() + test.addScreenCapture(takeScreenShot()));
				i = 1;
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Profile icons are not displaying on chat window or avtar elements got changed".toString()
							+ test.addScreenCapture(takeScreenShot()));

		}
		return i;

	}

	/**
	 * @Description : TO validate @ mention functionality
	 * @param groupcount
	 *            : Group count will be the input parameter
	 * @param select
	 *            : If this is true select user parameter should be mentioned
	 * @param selectuser
	 *            : This is mandatory when select is true
	 * @throws Exception
	 */
	public void atMentionValidation(int groupcount, boolean select, String selectuser) throws Exception {
		int witheveryone = groupcount + 1;
		enterText(er.kmcomposebar, "@", "xpath", "Type your message");
		List<WebElement> atmentionusers = remoteDriver.findElements(By.xpath(er.kmcatmentionusernames));
		int atsize = atmentionusers.size();
		if (witheveryone == atsize) {
			test.log(LogStatus.PASS,
					"Total participants count and @ mention users count is matchng including Everyone option i.e. "
							+ atsize + " ".toString() + test.addScreenCapture(takeScreenShot()));
		} else {
			test.log(LogStatus.FAIL, "Total participants<b> " + groupcount + "</b> count and @ mention users<b> "
					+ atsize + " </b>count got deviated".toString() + test.addScreenCapture(takeScreenShot()));
		}
		if (select)
			for (WebElement e : atmentionusers) {
				System.out.println(e.getText());
				// Here we need to scroll for the element beyond the screen
				// level
				if (e.getText().trim().equalsIgnoreCase(selectuser)) {
					e.click();
					break;
				}
			}
		System.out.println("out of if");
	}

	/**
	 * 
	 * @param groupname
	 *            : Will get the time stamp of the group provided by user
	 * @throws Exception
	 */
	public void getGroupTimestamp(String groupname) throws Exception {
		try {
			String timestamp = getText(er.kmcidgroup + groupname + "']/../..//span[@class='dayTime']");
			test.log(LogStatus.INFO, "For " + groupname + " Timestamp displayed as : <b>" + timestamp + "</b>");
			List<WebElement> ele = remoteDriver.findElements(By.xpath(
					"//div[@class='userDetails active']//div[@class='userNameDiv'][text()='AutomationGroup']/../../..//div[@class='userChatDEsc']//span"));
			for (WebElement e : ele) {
				e.getText();
				test.log(LogStatus.INFO,
						"For " + groupname + " sender or message displayed as : <b>" + e.getText() + "</b>");
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "For " + groupname + " unable to get the Timestamp or user chat descriptions");
		}
	}

	/**
	 * @param groupname
	 *            : Actions will perform on this group
	 * @param check
	 *            : If this flag is true, then followed action will be performed
	 * @param action
	 *            : This parameter is valid only when @check is true
	 * @throws Exception
	 *             : Fail, if it fail to perform action
	 */
	public void goToGroupAndPerform(String groupname, boolean check, String action) throws Exception {
		moveToElement(er.kmcidgroup + groupname + er.kmcidchatdesc, "xpath");
		click(er.kmcidgroup + groupname + er.kmcidchatdesc, groupname + " chat");
		try {
			if (check) {
				switch (action.trim()) {
				case "Star":
					System.out.println("In Star");
					// moveToElement(//div[@class='userNameDiv'][text()=' +
					// groupname +
					// "']/../../../..//span[@title='Star']", "xpath");
					Thread.sleep(1000);
					moveToElement(
							er.kmcidgroup + groupname + "']/../../../..//span[@class='icon __i right kr-starred']",
							"xpath");
					Thread.sleep(1000);
					click(er.kmcidgroup + groupname + "']/../../../..//span[@class='icon __i right kr-starred']",
							"Star");
					Thread.sleep(1000);
					test.log(LogStatus.PASS,
							groupname + " was Starred ".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "Unstar":
					System.out.println("In Unstar");
					Thread.sleep(1000);
					moveToElement(er.kmcidgroup + groupname
							+ "']/../../../..//span[@class='icon __i right kr-starred-filled']", "xpath");
					Thread.sleep(1000);
					click(er.kmcidgroup + groupname + "']/../../../..//span[@class='icon __i right kr-starred-filled']",
							"Unstar");
					Thread.sleep(1000);
					test.log(LogStatus.PASS,
							groupname + " was Unstarred".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "Mute":
					moveToElement(er.kmcidgroup + groupname + "']/../../../..//i[@class='icon __i kr-audio  ']",
							"xpath");
					Thread.sleep(1000);
					click(er.kmcidgroup + groupname + "']/../../../..//i[@class='icon __i kr-audio  ']", "Mute");
					test.log(LogStatus.PASS, groupname + " was displayed with mute slots".toString()
							+ test.addScreenCapture(takeScreenShot()));
					break;

				case "UnMute":
					moveToElement(er.kmcidgroup + groupname + "']/../../../..//i[@class='icon __i kr-mute']", "xpath");
					Thread.sleep(1000);
					click(er.kmcidgroup + groupname + "']/../../../..//i[@class='icon __i kr-mute']", "Mute");
					test.log(LogStatus.PASS, groupname + " was displayed with mute slots".toString()
							+ test.addScreenCapture(takeScreenShot()));
					break;

				case "Read":
					moveToElement(er.kmcidgroup + groupname + "']/../../../..//i[@title='Read']", "xpath");
					Thread.sleep(1000);
					click(er.kmcidgroup + groupname + "']/../../../..//i[@class='icon __i kr-eye-open']", "Read");
					test.log(LogStatus.PASS,
							groupname + " has marked as read".toString() + test.addScreenCapture(takeScreenShot()));
					break;

				case "Unread":
					System.out.println("In Unread");
					Thread.sleep(1000);
					moveToElement(er.kmcidgroup + groupname + "']/../../../..//i[@class='icon __i kr-eyeLash']",
							"xpath");
					Thread.sleep(1000);
					click(er.kmcidgroup + groupname + "']/../../../..//i[@class='icon __i kr-eyeLash']", "Un-Read");
					moveToElement(er.klogo, "Work Assist Logo");
					click(er.klogo, "Work Assist Logo");
					Thread.sleep(2000);
					try {
						String unreadcount = getText(
								er.kmcidgroup + groupname + "']/../../..//span[@class='unreadCount']");
						test.log(LogStatus.PASS,
								groupname + " has marked as Un-Read and the badge count displayed as <b>" + unreadcount
										+ "</b>".toString() + test.addScreenCapture(takeScreenShot()));
					} catch (Exception e) {
						test.log(LogStatus.FAIL, groupname + " Badge count was not displayed for unread chat".toString()
								+ test.addScreenCapture(takeScreenShot()));
					}

					break;
				case "3dots":
					System.out.println("In 3 dots");
					moveToElement(er.kmcidgroup + groupname + "']/../../../../.." + er.kmc3dots, "xpath");
					Thread.sleep(1000);
					click(er.kmcidgroup + groupname + "']/../../../../.." + er.kmc3dots, "3dots");
					Thread.sleep(1000);
					break;
				case "DeleteGroup":
					System.out.println("In Delete");
					click("//div[@class='userDetails active']//div[@class='userNameDiv'][text()='" + groupname
							+ "']/../../..//div[@class='userChatDEsc']", "Delete Group");
					Thread.sleep(1000);
					click("//i[@class='icon __i right kr-delete']", "Delete Group");
					test.log(LogStatus.PASS, groupname + " Deleted Successfully");
					clickOn("Delete", true);
					break;
				default:
					test.log(LogStatus.FAIL,
							"Please provided valid on hover action i.e. , should be match with case value");
				}
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "For <b>" + groupname + "</b> Unable to click on <b>" + action
					+ "</b>... Seems element got updated ".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}

	public void searchAndSelectFrom(String searchfrom, String searchwith, boolean expuserdisplay) throws Exception {
		boolean actuserdisplay = false;
		if (searchfrom.equals("All Messages"))
			searchfrom = "Search Messages";
		enterText("//*[contains (@placeholder, 'Search')]", searchwith, "Search from " + searchfrom);
		// enterText("//*[contains (@placeholder, '" + searchfrom + "')]",
		// searchwith, "Search from " + searchfrom);
		Thread.sleep(1000);
		actuserdisplay = elementIsDisplayed(er.kmsearchsuggestions + searchwith + er.ksinglquote, "xpath");
		if (expuserdisplay) {
			if (actuserdisplay) {
				test.log(LogStatus.PASS,
						"From " + searchfrom + " search, expected visibility of thread i.e. <b>" + searchwith + " is : "
								+ expuserdisplay + "</b> and found --> <b>" + actuserdisplay + "</b>".toString()
								+ test.addScreenCapture(takeScreenShot()));
				click(er.kmsearchsuggestions + searchwith + er.ksinglquote, "Search Suggested user i.e." + searchwith);

			} else {
				test.log(LogStatus.FAIL,
						"From " + searchfrom + " search, expected visibility of thread i.e. <b>" + searchwith + " is : "
								+ expuserdisplay + "</b> But found --> <b>" + actuserdisplay + "</b>".toString()
								+ test.addScreenCapture(takeScreenShot()));
			}
		} else if (!expuserdisplay) {
			if (actuserdisplay) {
				test.log(LogStatus.FAIL,
						"From " + searchfrom + " search, expected visibility of thread i.e. <b>" + searchwith + " is : "
								+ expuserdisplay + "</b> But found --> <b>" + actuserdisplay + "</b>".toString()
								+ test.addScreenCapture(takeScreenShot()));

			} else {
				test.log(LogStatus.PASS,
						"From " + searchfrom + " search, expected visibility of thread i.e. <b>" + searchwith + " is : "
								+ expuserdisplay + "</b> and found --> <b>" + actuserdisplay + "</b>".toString()
								+ test.addScreenCapture(takeScreenShot()));

			}
		}
	}

	/**
	 * 
	 * @param expectedoptions
	 *            : Getting actual options from testdata json file @throws
	 *            IOException @throws
	 */
	public void validateAndSelectMuteSlots(String expectedmuteslots, boolean select) throws Exception {
		String[] exp = cf.convertStringstoArray(expectedmuteslots);
		int i = 0;
		boolean check = false;
		try {
			List<WebElement> options = remoteDriver.findElements(By.xpath(er.kmcmuteslots));
			for (WebElement ele : options) {
				Thread.sleep(500);
				String act = ele.getText();
				check = exp[i].trim().equals(act);
				if (check) {
					test.log(LogStatus.PASS, "Expected option : " + exp[i] + " --> Displayed option : " + act);
				} else {
					test.log(LogStatus.FAIL, "Expected option : " + exp[i] + " --> But, displayed option : " + act);
				}
				i++;
			}
			test.log(LogStatus.INFO, "Mute slots".toString() + test.addScreenCapture(takeScreenShot()));
			if (select) {
				System.out.println("Selecting mute slot i.e. " + options.get(0).getText());
				options.get(0).click();
				test.log(LogStatus.PASS, "Selected a slot and Muted the thread");
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Failed to validate/select mute slots".toString() + test.addScreenCapture(takeScreenShot()));

		}

	}

	/**
	 *
	 * @param typeofConv
	 *            : it says 3dot option validation is for group or one to one
	 * @param expectedoptions
	 *            : Getting actual options from testdata json file
	 * @param Windowpostion
	 *            : Dot operations on middle panel and right panel
	 * @throws Exception
	 */
	public void optionsDisplayedOn3Dots(String typeofConv, String expectedoptions, String windowpanel)
			throws Exception {
		List<WebElement> options = null;
		if (windowpanel.contains("right")) {
			String headername = getChatHeaderName();
			moveToElement(er.kdrc3dotoptionsRightPanel + headername
					+ "']/../../../../..//I[@class='p-icon _choI kr-ellipsis']", "xpath");
			click(er.kdrc3dotoptionsRightPanel + headername + "']/../../../../..//I[@class='p-icon _choI kr-ellipsis']",
					"xpath");

			options = remoteDriver.findElements(By.xpath(er.kdrc3dotoptionsRightPanelOptions));

		} else {
			options = remoteDriver.findElements(By.xpath(er.kmc3dotoptions));
		}
		String[] exp = cf.convertStringstoArray(expectedoptions);
		int i = 0;
		boolean check = false;
		try {
			options = remoteDriver.findElements(By.xpath(er.kmc3dotoptions));
			if (options.size() > 0) {
				for (WebElement ele : options) {
					String act = ele.getText();
					System.out.println("From applicaton:" + act);
					check = exp[i].trim().equals(act);
					if (check) {
						test.log(LogStatus.PASS,
								"Expected option : " + exp[i] + "<b>  --></b>" + " displayed option : " + act);
					} else {
						test.log(LogStatus.FAIL,
								"Expected option : " + exp[i] + "<b>  --></b>" + " displayed option : " + act);
					}
					i++;
				}
				test.log(LogStatus.INFO,
						"For <b>" + typeofConv + "</b> 3 dot options displayed as per the above validation".toString()
								+ test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL, "For <b>" + typeofConv + "</b> 3 dot options are not displayed".toString()
						+ test.addScreenCapture(takeScreenShot()));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Array index out of bounce exception. Issue with test data split".toString()
					+ test.addScreenCapture(takeScreenShot()));

		}
	}

	/**
	 * 
	 * @param operation
	 *            : It should match with the text displayed in App operation
	 *            parameter can be given as follow : Leave Conversation , Manage
	 *            Conversation , Clear Chat History , Delete Conversation
	 * 
	 * @throws Exception
	 */
	public void operationsFrom3Dots(String operation) throws Exception {
		try {
			click(er.kmc3dotoptions + "[text()='" + operation + "']", "From 3 dots " + operation);
			Thread.sleep(3000);
			test.log(LogStatus.PASS,
					"Selected " + operation + " from 3dots ".toString() + test.addScreenCapture(takeScreenShot()));
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to select " + operation
					+ " from 3 dots .. Seems element got updated".toString() + test.addScreenCapture(takeScreenShot()));
		}
	}

	/**
	 * @Description : To check empty state message
	 * @param expected
	 *            : Expected message
	 * @throws Exception
	 */
	public void checkEmptyScreen() throws Exception {
		String expected = "How about, Let�s start with just a hello?";
		try {
			String actual = getText("//div[@class='emptyScreenMsg']").trim();
			if (actual.equals(expected)) {
				test.log(LogStatus.PASS, "Displayed empty screen message as expected".toString()
						+ test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL,
						"Displayed incorrect empty screen message : Expected :<b>" + expected + "</b> But found : <b>"
								+ actual + "</b> ".toString() + test.addScreenCapture(takeScreenShot()));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"How about, Let�s start with just a hello? is not displayed in empty screen validation".toString()
							+ test.addScreenCapture(takeScreenShot()));
		}
	}

	/**
	 * @Description : It will verify the visibility of compose bar
	 * @param display
	 *            : For true, it will check return pass only when compose bar is
	 *            available other wise it returns fail
	 * @throws Exception
	 */
	public void visibilityOfComposeBar(boolean display) throws Exception {
		boolean displayed = cf.elementIsDisplayed(er.kcomposebar, "xpath");
		if (display) {
			if (displayed) {
				test.log(LogStatus.PASS,
						"Expected compose bar is: <b>" + display + "</b> Displayed compose bar status is: <b>"
								+ displayed + "</b>".toString() + test.addScreenCapture(takeScreenShot()));
			} else {

				test.log(LogStatus.FAIL,
						"Expected compose bar is: <b>" + display + "</b> Displayed compose bar status is: <b>"
								+ displayed + "</b>".toString() + test.addScreenCapture(takeScreenShot()));
			}
		} else if (!display) {
			if (displayed) {
				test.log(LogStatus.FAIL,
						"Expected compose bar is: <b>" + display + "</b> Displayed compose bar status is: <b>"
								+ displayed + "</b>".toString() + test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.PASS,
						"Expected compose bar is: <b>" + display + "</b> Displayed compose bar status is: <b>"
								+ displayed + "</b>".toString() + test.addScreenCapture(takeScreenShot()));
			}
		}
	}

	/**
	 * 
	 * @param groupname
	 *            : Will get on hover participants of this group
	 * @param loginuser
	 *            : to check if the last message is from current user
	 * @return : Will return the count of participants of the group name
	 *         provided
	 * @throws Exception
	 */
	public int getOnHoverParticipantsCount(String groupname, String loginuser) throws Exception {
		ArrayList<String> count;
		int groupparticipants = 0;
		try {
			goToGroupAndPerform(groupname, false, "NA");
			count = getAndValidateGroupIcons(groupname, false, loginuser);
			String val = count.get(2);
			int i = Integer.parseInt(val);
			System.out.println(i = i + 2);
			Thread.sleep(2000);
			moveToElement(er.kmcidgroup + groupname
					+ "']/../../../..//div[@class='avatarDiv']//span[@class='nameAvatar chatAvatar']", "xpath");
			List<WebElement> totalparticipants = remoteDriver
					.findElements(By.xpath(er.kmcidgroup + groupname + "']/../../../..//div[@class='userPopupUi']"));
			groupparticipants = totalparticipants.size();
			if (i == totalparticipants.size()) {
				test.log(LogStatus.PASS,
						"Three group icouns count matching with the total participants from group Onhover");
				test.log(LogStatus.PASS, "For<b> " + groupname + "</b> On Hover total participants are " + i
						+ " ".toString() + test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL,
						"On Hover participants size <b> " + groupparticipants
								+ " </b> is not maching with Total Group Participants <b>" + i + " </b> ".toString()
								+ test.addScreenCapture(takeScreenShot()));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate on hover participants");
		}
		return groupparticipants;
	}

	/**
	 * 
	 * @param groupname
	 *            : Group icon validations performs on this group name
	 * @param validation
	 *            : true, will validate first icon from last messaged person
	 * @param loginuser
	 *            : to check if the last message is from current user
	 * @return allicons : return list of group icons i.e. Left, Right and Top
	 * @throws Exception
	 */
	public ArrayList<String> getAndValidateGroupIcons(String groupname, boolean validation, String loginuser)
			throws Exception {
		String value = null;
		goToGroupAndPerform(groupname, false, "NA");
		ArrayList<String> allicons = new ArrayList<>();
		try {
			List<WebElement> profileicons = remoteDriver.findElements(By.xpath(er.kmcidgroup + groupname
					+ "']/../../../..//div[@class='avatarDiv']//span[@class='nameAvatar triple']//span"));
			for (WebElement e : profileicons) {
				value = e.getText();
				allicons.add(value);
			}
			test.log(LogStatus.INFO, "Group profile Icons displayed as <b>" + allicons + "</b>".toString()
					+ test.addScreenCapture(takeScreenShot()));
			if (validation) {
				String lastmessagefrom = getLastMessageParticipant(groupname, loginuser);
				String firstchar = cf.getFirstChar(lastmessagefrom);
				if (firstchar.equalsIgnoreCase(allicons.get(0))) {
					test.log(LogStatus.PASS,
							"Last message participant and the first(Left) group icon letters are matching".toString()
									+ test.addScreenCapture(takeScreenShot()));
					test.log(LogStatus.INFO,
							"Second icon i.e. Right icon displayed as :<b>" + allicons.get(1) + "</b>");
					test.log(LogStatus.INFO, "Third icon i.e. Top icon displayed as :<b>" + allicons.get(2) + "</b>");
				} else {
					test.log(LogStatus.FAIL,
							"Last message participant name starts with <b>" + firstchar
									+ "</b> But the first icon displayed as <b>" + allicons.get(0) + "</b>".toString()
									+ test.addScreenCapture(takeScreenShot()));
				}
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to get Group profile icons of : " + groupname + " ".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}
		return allicons;
	}

	/**
	 * @Description : Will compare one to conversation profile icon
	 * @param username
	 *            : Based this name profile icon will be compared with first
	 *            character
	 * @throws Exception
	 */
	public void userProfileIconValidation(String username) throws Exception {
		String expfirstchar = cf.getFirstChar(username);
		String actfirstchar = getText(er.kmcactiveusericon);
		moveToElement(er.kmcactiveusericon, "xpath");
		Thread.sleep(2000);
		String onhovericon = getText(er.kmcactiveusericon, "xpath");

		if (expfirstchar.equals(actfirstchar) && (expfirstchar.equals(onhovericon))) {
			test.log(LogStatus.PASS,
					"For one to conversation with " + username
							+ ", Profile icon and onhover icons are matching with first character i.e.<b> "
							+ actfirstchar + "</b>".toString() + test.addScreenCapture(takeScreenShot()));
			click(er.kmcomposebar, "Type your message");
		} else {
			test.log(LogStatus.FAIL,
					"For one to conversation with " + username
							+ ", displayed incorrect Profile icon/onhover icons i.e.<b> " + actfirstchar
							+ "</b>".toString() + test.addScreenCapture(takeScreenShot()));
			click(er.kmcomposebar, "Type your message");

		}

	}

	/**
	 * @param groupname
	 *            : TO print this group name in the reports
	 * @param logedinuser
	 *            :If the last message is from logedinuser, will return the same
	 * @return : returns last message username
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public String getLastMessageParticipant(String groupname, String logedinuser)
			throws InterruptedException, Exception {
		String lastmessagefrom = null;
		try {
			List<WebElement> msgs = remoteDriver.findElements(By.xpath("//div[@class='bodyMsg ']"));
			int i = msgs.size();
			try {
				moveToElement("//div[@class='bodyMsg '][" + i + "]", "xpath");
				lastmessagefrom = getText("//div[@class='bodyMsg '][" + i + "]//div[@class='nameTime']");
				test.log(LogStatus.INFO,
						"In <b>" + groupname + " </b> group, last message was from : " + lastmessagefrom);
			} catch (Exception e) {
				lastmessagefrom = logedinuser;
				test.log(LogStatus.INFO, "In <b>" + groupname + " </b> group, last message was from current user i.e. "
						+ lastmessagefrom);
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to get last message person name and is displayed as " + lastmessagefrom);
		}
		return lastmessagefrom;
	}

	/**
	 * @Description : To get group timeline(info) i.e. Who created and when the
	 *              group created info
	 * @throws IOException
	 */
	public void verifyGroupCreationTimeline(String currentuser) throws IOException {
		try {
			moveToElement("//div[@class='threadCreationInfo']//div[@class='userInitial']", "xpath");
			String userinitial = getText("//div[@class='threadCreationInfo']//div[@class='userInitial']");
			String usernameforgroup = getText("//div[@class='threadCreationInfo']//span[@class='userName']");
			String firstchar = cf.getFirstChar(usernameforgroup);

			if (usernameforgroup.startsWith("You"))
				firstchar = cf.getFirstChar(currentuser);
			if (usernameforgroup.contains(currentuser))
				test.log(LogStatus.FAIL,
						"Group timeline name displayed as : " + currentuser + "<b> It should be displayed as you</b>");
			test.log(LogStatus.INFO, "Group timeline name displayed as : " + usernameforgroup);
			if (firstchar.equalsIgnoreCase(userinitial)) {
				test.log(LogStatus.PASS, "User initial matches with the first character of the user".toString()
						+ test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL, "Initial displayed as :" + userinitial + " but, expected inital is :<b>"
						+ firstchar + "</b>".toString() + test.addScreenCapture(takeScreenShot()));
			}
			String creationdate = getText("//div[@class='threadCreationInfo']//span[@class='creationDate']");
			test.log(LogStatus.INFO, "Group timeline date and time displayed as : " + creationdate);

		} catch (Exception e) {
			test.log(LogStatus.WARNING,
					"Group creation time line creation time field got updated from date to Day".toString()
							+ test.addScreenCapture(takeScreenShot()));
		}
	}

	/**
	 * @throws Exception
	 * @Description : To get group updated(info) i.e. if any one added and
	 *              removed from the group @ : Yet to implement better logic for
	 *              this
	 */
	public void verifyGroupUpdateTimelines(String typeofAmend) throws Exception {
		waitTillappear(
				"//div[@class='msgMemberTimeline']//span[@class='timelineCntr']/span[@class='messageOnly'][contains(text(),'"
						+ typeofAmend + "')]",
				"xpath", "Timeline");
		ArrayList<String> timelines = new ArrayList<>();
		try {
			moveToElement(
					"//div[@class='msgMemberTimeline']//span[@class='timelineCntr']/span[@class='messageOnly'][contains(text(),'"
							+ typeofAmend + "')]",
					"xpath");
			List<WebElement> alltimelines = remoteDriver.findElements(By
					.xpath("//div[@class='msgMemberTimeline']//span[@class='timelineCntr']/span[@class='messageOnly'][contains(text(),'"
							+ typeofAmend + "')]/..//span"));
			if (alltimelines.size() > 0) {
				System.out.println(alltimelines.size());
				for (WebElement e : alltimelines) {
					timelines.add(e.getText());
				}
				test.log(LogStatus.INFO, "Timeline displayed as " + timelines);
				test.log(LogStatus.WARNING, "Cross check the time lines from below screenshot".toString()
						+ test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL, typeofAmend + " timeline was not updated in the group".toString()
						+ test.addScreenCapture(takeScreenShot()));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Failed to display timeline for amends".toString() + test.addScreenCapture(takeScreenShot()));
		}
	}

	public void isMessagesAvaiable(boolean check) throws Exception {
		boolean isAvailable = false;
		Thread.sleep(1500);
		isAvailable = remoteDriver.findElements(By.xpath(er.kmcmessagebubbles)).size() > 0;
		List<WebElement> msgs = remoteDriver.findElements(By.xpath(er.kmcmessagebubbles));

		if (isAvailable == check) {
			test.log(LogStatus.PASS, "Displayed " + msgs.size() + " messages count as expected".toString()
					+ test.addScreenCapture(takeScreenShot()));
		} else {
			test.log(LogStatus.FAIL, "Displayed " + msgs.size() + " messages count was not as expected".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}

	}

	// -------- Below methodss are form KoraManageConversationPage 11-03-2021 -
	// 17:00 to 17:30 Commit ---------//
	/**
	 * @Description : To check manage conversation screen w.r.t available
	 *              widgets on the screen
	 * @throws Exception
	 */
	public void manageConversationValidations() throws Exception {
		String manageconvttl = getText("//div[@class='dialog-title']");
		int size = getSize("//div[@class='addParticipantsCtr-Btn']");
		if (size > 0)
			test.log(LogStatus.FAIL, "Add Participants option displayed under General Tab");
		clickOn("Members", true);
		String addparticipants = getText("//div[@class='addParticipantsCtr-Btn']");
		if (!manageconvttl.equalsIgnoreCase("Manage Conversation")
				|| (!addparticipants.equalsIgnoreCase("Add Participants")))
			test.log(LogStatus.FAIL, "New conversation text displayed as " + manageconvttl);
		clickOn("General", true);
		test.log(LogStatus.INFO, "Manage Conversation title displayed as : <b>" + manageconvttl + "</b> ");
		test.log(LogStatus.INFO, "Add Participants text displayed as : <b>" + addparticipants + "</b> ");
	}

	/**
	 * 
	 * @param memberstoadd
	 *            : THese members will be added to group
	 * @param plusiconclick
	 *            : If true it will click on plus icon
	 * @throws Exception
	 */
	public void AddParticipantsFromManage(String memberstoadd, boolean plusiconclick) throws Exception {
		try {
			clickOn("Members", false);
			clickOn("Add Participants", true);
			startNewConversationWith("chat", memberstoadd, false);
			clickOn("Done", false);
			validateRecentAddedParticipants(memberstoadd);
			click(er.kmcmanageclose, "Close button");

		} catch (Exception e) {
			click(er.kmcmanageclose, "Close button");
			test.log(LogStatus.FAIL, "Failed to add participants ".toString(), test.addScreenCapture(takeScreenShot()));
		}
	}

	/**
	 * 
	 * @param option
	 *            : It will click on this button based on the user provided text
	 * @param screenshot
	 *            : If this parameter is true , it will capture screenshot
	 * @throws Exception
	 */
	public void clickOn(String option, boolean screenshot) throws Exception {

		click(er.ktext + option + "']", option + " tab");
		if (screenshot)
			test.log(LogStatus.PASS, "Clicked on " + option, test.addScreenCapture(takeScreenShot()));

	}

	/**
	 * 
	 * @param participant
	 *            : Will check this participant added to the group or not
	 * @throws Exception
	 */
	public void validateRecentAddedParticipants(String participant) throws Exception {
		boolean flag = false;
		Thread.sleep(1500);
		moveToElement(er.kmcmembername + "[text()='" + participant + "']", "xpath");
		try {
			List<WebElement> Menulist = remoteDriver.findElements(By.xpath("//div[@class='emailUi']"));
			for (WebElement e : Menulist) {
				if (e.getText().trim().equalsIgnoreCase(participant)) {
					flag = true;
					test.log(LogStatus.INFO, participant + " Added to the Group");
					test.log(LogStatus.PASS, test.addScreenCapture(takeScreenShot()));
					break;
				}
			}
			if (!flag) {
				test.log(LogStatus.FAIL, participant + " was not added to the group");
				test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Participant mail id element got changed");
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param renameto
	 *            : This will rename the group to the user provided name as a
	 *            parameter
	 * @return : Return the updated name from the application
	 * @throws Exception
	 */
	public String renameGroupAndClose(String renameto) throws Exception {
		String updatedname = null;
		try {
			click("//input[@placeholder='Group Name']", "xpath");
			enterText("//input[@placeholder='Group Name']", renameto, "xpath");
			updatedname = getAttributeValue("//input[@placeholder='Group Name']", "value");
			System.out.println(updatedname);
			if (renameto.equals(updatedname)) {
				test.log(LogStatus.PASS, "Group name Updated successfully as " + updatedname);
				test.log(LogStatus.PASS, test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL, "Failed to update the group name as expected from Manage Conversation screen");
				test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			}
			click(er.kmcmanageclose, "Close button");

		} catch (Exception e) {
			System.out.println(e);
		}
		return updatedname;

	}

	/**
	 * @Description : It will remove all the members from the group
	 * @throws Exception
	 */
	public void removeParticipantsAndClose() throws Exception {
		try {
			boolean memb = false;
			test.log(LogStatus.INFO, "Now will remove all the members from the group");
			clickOn("Members", true);
			do {
				memb = remoteDriver.findElements(By.xpath("//span[text()='Member']")).size() > 0;
				if (memb) {
					clickOn("Member", false);
					String name = getText(er.kmcmembername);
					clickOn("Remove", false);
					click(er.kmremoveparticipantpopup, "Participant Remove from manage pop up");
					test.log(LogStatus.INFO, "Removed : " + name);
					Thread.sleep(8000);
				}
			} while ((memb));
			test.log(LogStatus.PASS,
					"Removed all the members from the group".toString() + test.addScreenCapture(takeScreenShot()));
			click(er.kmcmanageclose, "Close");
		} catch (Exception e) {
			click(er.kmcmanageclose, "Close");
			test.log(LogStatus.FAIL,
					"Faled to Remove Members of the group".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}

	/**
	 * 
	 * @param message
	 *            : On hover actions will perform on this particular message
	 * @param action
	 *            : Action to be performed after on hover
	 * @throws Exception
	 *             : Throws exception if fails
	 */
	// span[@class='msgText ']//div[@class='send-message'][text()='Hello Copy
	// Me']
	// span[@class='msgText ']//div[@class='send-message'][text()='Hello Copy
	// Me']/..//div[@class='msgCntrlBar _content']/i[@title='
	public void goToMessageAndPerformActionsAs(String user, String message, String action, String subaction)
			throws Exception {
		WebElement compose = remoteDriver.findElement(By.xpath(er.kcomposebar));
		boolean messagemarkafteraction = false;
		try {
			Thread.sleep(3000);
			moveToElement(er.kmmessages + message + er.ksinglquote, "xpath");
			Thread.sleep(1000);
			moveToElement(er.kmmessages + message + er.ksinglquote + "/.." + er.kmmessagehoveroptiontitles + action
					+ er.ksinglquote, "xpath");
			test.log(LogStatus.PASS, "For <b>" + message + "</b> on hover options displayed and focus moved to <b>"
					+ action + "</b> as per below screenshot ".toString() + test.addScreenCapture(takeScreenShot()));
			Thread.sleep(1000);
			switch (action.trim()) {
			case "Reactions":
				System.out.println("In Reactions");

				click("//p[@class='chatUserTitle']/span[text()='" + user
						+ "']/../../../../../..//div[@class='send-message' and text()='" + message
						+ "']/..//div[@class='msgCntrlBar _content']//i[@title='" + subaction + "']",
						action + " on message hover");

				String reactioncount = getText("//p[@class='chatUserTitle']/span[text()='" + user
						+ "']/../../../../../..//div[@class='send-message' and text()='" + message
						+ "']/..//div[@class='msgCntrlBar _content']/../../../..//div[@class='count']");
				if (reactioncount.equals("1")) {
					test.log(LogStatus.WARNING,
							"Reaction count was 1".toString() + test.addScreenCapture(takeScreenShot()));
				} else {
					test.log(LogStatus.WARNING,
							"Reaction count was not reflected".toString() + test.addScreenCapture(takeScreenShot()));
				}

				Thread.sleep(1000);
				break;

			case "Reply Back":
				System.out.println("In Reply back");
				// click(er.kmmessagehoverreplyback, action + " on message
				// hover");
				click("//p[@class='chatUserTitle']/span[text()='" + user
						+ "']/../../../../../..//div[@class='send-message' and text()='" + message
						+ "']/..//div[@class='msgCntrlBar _content']//i[@class='icon __i kr-return replyButton']",
						action + " on message hover");
				Thread.sleep(1000);
				compose.click();
				test.log(LogStatus.PASS, "Selected Reply back option from the hover".toString()
						+ test.addScreenCapture(takeScreenShot()));

				if (subaction.contains("It is Reply")) {
					compose.sendKeys(subaction, Keys.ENTER);
					test.log(LogStatus.WARNING, "Replied successfully. Please check the UI with human eye".toString()
							+ test.addScreenCapture(takeScreenShot()));
				}
				break;

			case "Reminder":
				System.out.println("In Reminder");
				click(er.kmmessagehoveroptiontitles + action + er.ksinglquote, action + " on message hover");
				Thread.sleep(1000);
				break;

			case "More":
				System.out.println("In messge on hover 3dots ");
				click("//p[@class='chatUserTitle']/span[text()='" + user
						+ "']/../../../../../..//div[@class='send-message' and text()='" + message
						+ "']/..//i[contains(@class,'icon __i kr-ellipsis')]", action + " on message hover");
				Thread.sleep(1000);
				click(er.kmmessagehovermoreoptions + subaction + er.ksinglquote, "xpath");
				test.log(LogStatus.PASS, "Selected<b> " + subaction + " </b> option from message onhover 3 dots".toString()
						+ test.addScreenCapture(takeScreenShot()));
				if (subaction.equalsIgnoreCase("Copy")) {
					compose.click();
					click("//span[text()='Paste']", "Paste option on compose bar");
					compose.sendKeys(Keys.ENTER);
					test.log(LogStatus.PASS,
							"Same text pasted successfully".toString() + test.addScreenCapture(takeScreenShot()));
				} else if (subaction.equalsIgnoreCase("Edit")) {
					compose.click();
					compose.sendKeys("Edited", Keys.ENTER);
					if (messagemarkafteraction = remoteDriver
							.findElements(By.xpath("//p[@class='chatUserTitle']/span[text()='" + user
									+ "']/../../../../../..//div[@class='send-message' and text()='" + message
									+"Edited']/..//span[text() = 'Edited'] "))
							.size() > 0) {
						
						test.log(LogStatus.PASS,
								message+" edited text".toString() + test.addScreenCapture(takeScreenShot()));
					} else {
						test.log(LogStatus.FAIL,
								"Seems Text got edited but, Edited placeholder text is not available on top of the edited text"
										.toString() + test.addScreenCapture(takeScreenShot()));
					}
				}

				break;

			default:
				test.log(LogStatus.FAIL,
						"Please provided valid on hover action i.e. , option should be match with case value");
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Fora message either onhover options title is not displaying or failed to perform <b> " + action
							+ "</b> Action".toString() + test.addScreenCapture(takeScreenShot()));
		}

	}

	public boolean verifyDisplayOfChevronIcon(boolean expvisibility) throws Exception {
		boolean elementdisplayed = false;
		elementdisplayed = remoteDriver
				.findElements(By.xpath("//span[@class='kr-down_arrowBox'][contains(@style,'visibility: visible')]"))
				.size() > 0;
		if (expvisibility == elementdisplayed) {
			test.log(LogStatus.PASS, "For one participant Chevron icon (To enter Groupname) is not displayed".toString()
					+ test.addScreenCapture(takeScreenShot()));
		} else {
			test.log(LogStatus.FAIL,
					"Even for one participant Chevron icon (To enter Groupname) is getting displayed".toString()
							+ test.addScreenCapture(takeScreenShot()));
		}

		return elementdisplayed;

	}

	public void validateFromRecepientEnd(String initialtext, String replytext) throws Exception {
		boolean elementdisplayed = remoteDriver.findElements(By
				.xpath("//div[@class='replyMessage replyMessageBubble']//div[@class='leftCol']//div[@class='replayBubbleText'][text() = '"
						+ initialtext + "']/../../..//div[@class='send-message'][text() = '" + replytext + "'] "))
				.size() > 0;
		if (elementdisplayed) {
			test.log(LogStatus.PASS, "Displayed initial message as <b> " + initialtext
					+ " </b> and the reply text displated as <b> " + replytext + " </b>");

			test.log(LogStatus.PASS,
					"Please check the reply back UI".toString() + test.addScreenCapture(takeScreenShot()));
		} else {
			test.log(LogStatus.FAIL,
					"Either chat bar was not auto scrolled to the last message or Reply back functionality is not working"
							.toString() + test.addScreenCapture(takeScreenShot()));
		}
	}

	public void validateLongTextReadMoreTruncation() throws Exception {
		try {
			boolean elementdisplayed = remoteDriver.findElements(By.xpath(er.kmlongtextreadmore)).size() > 0;
			if (elementdisplayed) {
				/*
				 * moveToElement(er.kmreadmore, "xpath");
				 * test.log(LogStatus.PASS,
				 * "For<b> Read more </b>cursor type displayed as Hand icon");
				 * test.log(LogStatus.PASS,
				 * "For long text, text got truncated and Read more text got displayed as below"
				 * .toString() + test.addScreenCapture(takeScreenShot()));
				 * moveToElement(er.kmreadmore, "xpath"); click(er.kmreadmore,
				 * "Read more on long text message"); Thread.sleep(1000); //
				 * moveToElement(er.kmreadless, "xpath");
				 */
				test.log(LogStatus.FAIL, "For long text <b> Read more </b> option is getting displayed".toString()
						+ test.addScreenCapture(takeScreenShot()));

			} else {
				test.log(LogStatus.PASS,
						"For long text, Read moreis not getting displayed, which is expected".toString()
								+ test.addScreenCapture(takeScreenShot()));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Hand icon is not displaying either on Read more or Read less".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}
	}

	public void validateChatsAndDRS(boolean chats, boolean dr) throws Exception {
		try {
			boolean onlychats = false;
			boolean onlydrs = false;

			onlychats = remoteDriver.findElements(By.xpath("//div[@class='userDetails chat']")).size() > 0;
			onlydrs = remoteDriver.findElements(By.xpath("//div[@class='userDetails discussion']")).size() > 0;

			if ((chats) && (!dr)) {
				if ((chats && onlychats) && ((chats && (!onlydrs)))) {
					test.log(LogStatus.PASS, "Under chats, only chats are being displayed".toString()
							+ test.addScreenCapture(takeScreenShot()));
				} else {
					test.log(LogStatus.PASS,
							"Under chats, it displayes chats and DR's or instead of chats it dislayes DR's".toString()
									+ test.addScreenCapture(takeScreenShot()));
				}
			} else if ((!chats) && (dr)) {
				if ((dr && onlydrs) && ((dr && (!onlychats)))) {
					test.log(LogStatus.PASS, "Under DR, only DR's are being displayed".toString()
							+ test.addScreenCapture(takeScreenShot()));
				} else {
					test.log(LogStatus.PASS,
							"Under DR, it displayes DR and chats or instead of DR it dislayes Chats".toString()
									+ test.addScreenCapture(takeScreenShot()));
				}

			} else if (chats && dr) {
				if (onlychats && onlydrs) {
					test.log(LogStatus.PASS, "Under All messages, Displayed both chats and Dr's".toString()
							+ test.addScreenCapture(takeScreenShot()));
				} else {
					test.log(LogStatus.PASS,
							"Under All messages, Displayed either only chats nor only DR's but not both. It should display both"
									.toString() + test.addScreenCapture(takeScreenShot()));
				}

			}
		} catch (Exception e) {
			System.out.println("Retry");
		}
	}

	public void selectOptionFromRightNav3Dots(String user, String right3dotsoption) throws Exception {
		click(er.kdrManageRoom3dots0 + user + er.kdrManageRoom3dots1, "Right pane 3 dots");
		boolean flag = false;
		waitTillappear(er.kwfilterbyws, "xpath", "Left bottom header");
		List<WebElement> Menulist = remoteDriver.findElements(By.xpath(er.kmright3dotoptions));
		for (WebElement e : Menulist) {
			if (e.getText().trim().equalsIgnoreCase(right3dotsoption)) {
				flag = true;
				e.click();
				System.out.println(right3dotsoption + " Workspace got selected");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, "Selected <b>" + right3dotsoption + " </b>option from left menu".toString()
						+ test.addScreenCapture(takeScreenShot()));
				break;
			}
		}
		if (!flag) {
			System.out.println(right3dotsoption + " Workspace was not selected");
			test.log(LogStatus.FAIL,
					right3dotsoption
							+ "  Workspace not selected or it is not available from the workspaces list".toString()
							+ test.addScreenCapture(takeScreenShot()));
			System.out.println("Reached FailXXXXXXXX " + right3dotsoption
					+ " workspace is not available on the Dom for top header menu");
		}
		viewFiles();
	}

	public void viewFiles() throws Exception {

		if (!elementIsDisplayed(er.kmviewfiles, "xpath"))
			test.log(LogStatus.FAIL,
					"For view files, View Files header is not displayed or there could be a change in element"
							.toString() + test.addScreenCapture(takeScreenShot()));

		test.log(LogStatus.WARNING, "On click of view files, View Files header got displayed".toString()
				+ test.addScreenCapture(takeScreenShot()));
		click("//div[@class='p-dialog-titlebar-icons']", "Close view files");

	}

	public void forwardPostOrValidation(boolean forwardpost, String chatheadername, String forwardedmsg,
			String newparticipants) throws Exception {
		try {
			if (forwardpost) {
				if (elementIsDisplayed("//span[text() = 'Forward Message']", "xpath")) {
					System.out.println("Forward New Conversation -");
					click(er.kdstartnewconversation, " Click on Start New conversation");
					if (newparticipants.contains(",")) {
						String result[] = newparticipants.trim().split("\\s*,\\s*");
						for (String part : result) {
							System.out.println(part);
							select(part);
						}
					} else {
						select(newparticipants);
					}
					click(er.kdcreatenforwardpost, "click on  Create & Forward");
					Thread.sleep(2000);
				}
			}
			if (elementIsDisplayed("//p[@class='chatUserTitle']/span[text()='" + chatheadername
					+ "']/../../../../../..//div[@class='send-message' and text()='" + forwardedmsg
					+ "']/..//span[text() = 'Forwarded']", "xpath")) {
				test.log(LogStatus.PASS, forwardedmsg+" got displayed as <b> Forwarded </b> message " + test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL,
						"After forward, forward placeholder is not diaplyed on top of <b>"+forwardpost+" <b> text or it is not scrolled completely to the bottom"
								.toString() + test.addScreenCapture(takeScreenShot()));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Forward message pop up is not displayedor unable to select the provided participant".toString()
							+ test.addScreenCapture(takeScreenShot()));
		}
	}
	
	public void validationOfForwardedOrEdited(String chatheadername, String expectedmsg,String ForwardedorEdited) throws Exception {
		try {
			
			if (elementIsDisplayed("//p[@class='chatUserTitle']/span[text()='" + chatheadername
					+ "']/../../../../../..//div[@class='send-message' and text()='" + expectedmsg
					+ "']/..//span[text() = '"+ForwardedorEdited+"']", "xpath")) {
				test.log(LogStatus.PASS, expectedmsg+" got displayed as <b>"+ ForwardedorEdited +"</b> message " + test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL,
						"After forward, forward placeholder is not diaplyed on top of <b>"+expectedmsg+" <b> text or it is not scrolled completely to the bottom"
								.toString() + test.addScreenCapture(takeScreenShot()));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Forward message pop up is not displayedor unable to select the provided participant".toString()
							+ test.addScreenCapture(takeScreenShot()));
		}
	}
}
