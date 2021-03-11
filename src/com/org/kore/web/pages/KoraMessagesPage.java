package com.org.kore.web.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

public class KoraMessagesPage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;

	public KoraMessagesPage(RemoteWebDriver remoteWebDriver) {
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
	//	waitTillappear(er.kmcplusicon, "xpath", "Plus icon");
		String plusicon =null;
		plusicon = getAttributeValue(er.kmcplusicon, "title");
		if (!plusicon.equalsIgnoreCase("New Conversation"))
			test.log(LogStatus.FAIL, "For plus icon, New conversation text is not displaying".toString()
					+ test.addScreenCapture(takeScreenShot()));
		test.log(LogStatus.INFO, "For + icon text displayed as : <b>"+plusicon+"</b>");
	}

	/**
	 * @Description : To select an option after clicking on plus icon
	 * @param userchoice
	 *            : this should be either chat or discussion
	 * @throws Exception
	 */
	public void newChatOrDiscussion(String userchoice) throws Exception {
		click(er.kmcplusicon, "New Conversation");
		if (userchoice.equalsIgnoreCase("chat")) {
			click(er.kmchat, "Create a new Chat");
		} else if (userchoice.equalsIgnoreCase("discussion")) {
			click(er.kmdiscussion, "Create a Discussion Room");
		} else {
			test.log(LogStatus.FAIL, "Please provide valid userchaoice after clicking on + icon".toString()
					+ test.addScreenCapture(takeScreenShot()));
		}
	}

	public void checkDefaultFocus_Recents() throws Exception {
		try {
			// click(er.kmplusicon, "New Conversation");
			newChatOrDiscussion("chat");
			Thread.sleep(1500);
			List<WebElement> recents = remoteDriver.findElements(By.xpath(er.kmcrecent));
			if (recents.size() > 0) {
				test.log(LogStatus.PASS, "For a new chat displayed " + recents.size() + " Recent suggestions".toString()
						+ test.addScreenCapture(takeScreenShot()));
				test.log(LogStatus.PASS, "For a new chat default cursor focus is on Enter Participant name field");
			} else {
				test.log(LogStatus.FAIL, "For a new chat displayed " + recents.size() + " Recent suggestions".toString()
						+ test.addScreenCapture(takeScreenShot()));
				test.log(LogStatus.FAIL,
						"For a new chat default cursor focus is not on Enter Participant name field");
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
	public void startNewConversationWith(String participantlist, boolean plusicon) throws Exception {

		try {
			if (plusicon)
				// click(er.kmplusicon, "New Conversation");
				newChatOrDiscussion("chat");
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
				test.log(LogStatus.INFO, participant + " selected");
				test.log(LogStatus.PASS, test.addScreenCapture(takeScreenShot()));
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
			WebElement compose = remoteDriver.findElement(By.xpath(er.kmcomposebar));
			compose.sendKeys(enterthistext, Keys.ENTER);
			Thread.sleep(4000);
			chatheadername = getText("//div[@class='chatHeader']//span");
			test.log(LogStatus.INFO,
					"Entered message as " + enterthistext + " ".toString() + test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Compose bar or Chat header name is Missing ".toString() + test.addScreenCapture(takeScreenShot()));
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
			actbckgclr = remoteDriver.findElement(By.xpath("//div[@class='userDetails active']"))
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
		enterText(er.kmcomposebar, "@", "xpath", "Type your message");
		List<WebElement> atmentionusers = remoteDriver.findElements(By.xpath(er.kmcatmentionusernames));
		int atsize = atmentionusers.size();
		if (groupcount == atmentionusers.size()) {
			test.log(LogStatus.PASS,
					"Total participants count and @ mention users count is matchng including Everyone option "
							.toString() + test.addScreenCapture(takeScreenShot()));
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
			String timestamp = getText(er.kmcidgroup + groupname + "']/..//span[@class='dayTime']");
			test.log(LogStatus.INFO, "For " + groupname + " Timestamp displayed as : <b>" + timestamp + "</b>");
			List<WebElement> ele = remoteDriver.findElements(By.xpath(
					"//div[@class='userDetails active']//div[@class='userNameDiv'][text()='AutomationGroup']/../..//div[@class='userChatDEsc']//span"));
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
					// moveToElement(er.kmcidgroup + groupname +
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
						test.log(LogStatus.FAIL,
								groupname + " Badge count was not displayed for unread chat".toString()
										+ test.addScreenCapture(takeScreenShot()));
					}

					break;
				case "3dots":
					moveToElement(er.kmcidgroup + groupname + "']/.././../../.." + er.kmc3dots, "xpath");
					Thread.sleep(1000);
					click(er.kmcidgroup + groupname + "']/.././../../.." + er.kmc3dots, "3dots");
					Thread.sleep(1000);
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
		enterText("//*[contains (@placeholder, '" + searchfrom + "')]", searchwith, "Search from " + searchfrom);
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
	 * @param expectedoptions : Getting actual options from testdata json
	 * file @throws IOException @throws
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
				System.out.println("From applicaton:" + act);
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
	 * @throws IOException
	 */
	public void optionsDisplayedOn3Dots(String typeofConv, String expectedoptions) throws IOException {
		String[] exp = cf.convertStringstoArray(expectedoptions);
		int i = 0;
		boolean check = false;
		try {
			List<WebElement> options = remoteDriver.findElements(By.xpath(er.kmc3dotoptions));
			if(options.size()>0){
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
			}
			test.log(LogStatus.FAIL,
					"For <b>" + typeofConv + "</b> 3 dot options are not displayed".toString()
							+ test.addScreenCapture(takeScreenShot()));
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
			click(er.kmc3dotoptions + "[text()='" + operation + "']", "From 3 dots "+operation);
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
		String expected = "How about, Let’s start with just a hello?";
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
			test.log(LogStatus.FAIL, "Found incorrect elements in check empty screen".toString()
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
		boolean displayed = cf.elementIsDisplayed(er.kmcomposebar, "xpath");
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

	public void createGroupAndSendMessageAs(String participants, boolean plusicon, String groupname, String groupText)
			throws Exception {
		startNewConversationWith(participants, true);
		createGroupAs(groupname);
		enterYourMessageAs(groupText);
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
			moveToElement(er.kmcidgroup + groupname
					+ "']/../../..//div[@class='avatarDiv']//span[@class='nameAvatar chatAvatar']", "xpath");
			Thread.sleep(2000);
			List<WebElement> totalparticipants = remoteDriver
					.findElements(By.xpath(er.kmcidgroup + groupname + "']/../../..//div[@class='userPopupUi']"));
			if (i == totalparticipants.size()) {
				test.log(LogStatus.PASS,
						"Three group icouns count matching with the total participants from group Onhover");
				test.log(LogStatus.PASS, "For<b> " + groupname + "</b> On Hover total participants are " + i
						+ " ".toString() + test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL,
						"On Hover participants are not available".toString() + test.addScreenCapture(takeScreenShot()));
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
					+ "']/../../..//div[@class='avatarDiv']//span[@class='nameAvatar triple']//span"));
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
		String onhovericon = getText("//div[@class='circle']", "xpath");

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
			moveToElement("//div[@class='bodyMsg '][" + i + "]", "xpath");
			try {
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
			String creationdate = getText("//div[@class='threadCreationInfo']//span[@class='creationDate']");
			String firstchar = cf.getFirstChar(usernameforgroup);

			if (usernameforgroup.startsWith("You"))
				firstchar = cf.getFirstChar(currentuser);
			if (usernameforgroup.contains(currentuser))
				test.log(LogStatus.FAIL,
						"Group timeline name displayed as : " + currentuser + "<b> It should be displayed as you</b>");
			test.log(LogStatus.INFO, "Group timeline name displayed as : " + usernameforgroup);
			test.log(LogStatus.INFO, "Group timeline date and time displayed as : " + creationdate);
			if (firstchar.equalsIgnoreCase(userinitial)) {
				test.log(LogStatus.PASS, "User initial matches with the first character of the user".toString()
						+ test.addScreenCapture(takeScreenShot()));
			} else {
				test.log(LogStatus.FAIL, "Initial displayed as :" + userinitial + " but, expected inital is :<b>"
						+ firstchar + "</b>".toString() + test.addScreenCapture(takeScreenShot()));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Group creation time line is not displaying".toString() + test.addScreenCapture(takeScreenShot()));
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
				test.log(LogStatus.INFO, "Cross check the time lines from below screenshot".toString()
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

}
