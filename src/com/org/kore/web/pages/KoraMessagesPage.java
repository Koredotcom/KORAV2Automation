package com.org.kore.web.pages;

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
		waitTillappear(er.kmplusicon, "xpath", "Plus icon");
		String plusicon = getAttributeValue(er.kmplusicon, "title");
		if (!plusicon.equalsIgnoreCase("New Conversation"))
			test.log(LogStatus.FAIL, "New conversation text displayed as " + plusicon);
		test.log(LogStatus.INFO, "For + icon text displayed as : <b>New Conversation</b> ");
	}

	public void checkRecents() throws Exception {
		try {
			List<WebElement> recents = remoteDriver.findElements(By.xpath(er.kmrecent));
			if (recents.size() > 0)
				test.log(LogStatus.PASS, "Displayed " + recents.size() + " Recent suggestions");
			test.log(LogStatus.INFO, test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Not displaying any recent suggestions for a new conversation");
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
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
			click(er.kmplusicon, "New Conversation");
			click(er.kmenterparticipant, "Enter participant name");
			checkRecents();
			enterText(er.kmenterparticipant, nameorletter, "xpath", "Participant name");
			Thread.sleep(5000);
			waitTillappear(er.kmsuggestnames, "xpath", "Suggestions");
			test.log(LogStatus.INFO, test.addScreenCapture(takeScreenShot()));
			List<WebElement> msuggestions = remoteDriver.findElements(By.xpath(er.kmsuggestnames));
			test.log(LogStatus.INFO,
					"Total suggestions displayed with <b>" + nameorletter + "</b> is : " + msuggestions.size());
			System.out.println("Displayed suggestion list is : " + msuggestions.size());
			for (WebElement e : msuggestions) {
				String username = getText("(" + er.kmsuggestnames + "//div[1])" + "[" + i + "]");
				String firstchar = cf.getFirstChar(username);
				String profile = getText("(" + er.kmsuggestnames + "/../div[@class='circle'])" + "[" + i + "]");
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
				click(er.kmplusicon, "New Conversation");
			click(er.kmenterparticipant, "Enter participant name");
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

	public void select(String participant) throws Exception {
		enterText(er.kmenterparticipant, participant, "xpath", "Participant name");
		Thread.sleep(1000);
		waitTillappear(er.kmsuggestmailids, "xpath", "Suggested emails");
		List<WebElement> mailid = remoteDriver.findElements(By.xpath(er.kmsuggestmailids));
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
			click(er.kmgroupchevronicon, "Chevron Icon");
			click(er.kmgroupname, "GroupName");
			Thread.sleep(1000);
			enterText(er.kmgroupname, groupname, "GroupName");
			test.log(LogStatus.INFO, "Group created successfully as" + groupname);
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
	public void enterYourMessageAs(String enterthistext) throws Exception {
		try {
			WebElement compose = remoteDriver.findElement(By.xpath("//div[@placeholder='Type your message']"));
			compose.sendKeys(enterthistext, Keys.ENTER);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Entered messages as : <b>" + enterthistext + "</b>");
			test.log(LogStatus.INFO, "Entered message as " + enterthistext, test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Compose bar is not displaying");
			test.log(LogStatus.FAIL, "Unable to enter the message", test.addScreenCapture(takeScreenShot()));
		}

	}

	/**
	 * 
	 * @param groupname
	 *            : Will get the time stamp of the group provided by user
	 * @throws Exception
	 */
	public void getGroupTimestamp(String groupname) throws Exception {
		String timestamp = getText(er.kmmidgroup + groupname + "']/..//span[@class='dayTime']");
		test.log(LogStatus.INFO, "For " + groupname + "Timestamp displayed as : <b>" + timestamp + "</b>");
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
		moveToElement(er.kmmidgroup + groupname + er.kmmidchatdesc, "xpath");
		click(er.kmmidgroup + groupname + er.kmmidchatdesc, "Group click");
		/* click(er.kmmidgroup + groupname + "']", "Group click"); */
		try {
			if (check) {
				switch (action.trim()) {
				case "Star":
					moveToElement(er.kmmidgroup + groupname + "']/../../..//span[@title='Star']", "xpath");
					click(er.kmmidgroup + groupname + "']/../../..//span[@title='Star']", "Star");
					break;

				case "Unstar":
					moveToElement(er.kmmidgroup + groupname + "']/../../..//span[@title='Unstar']", "xpath");
					click(er.kmmidgroup + groupname + "']/../../..//span[@title='Unstar']", "Unstar");
					break;

				case "Mute":
					moveToElement(er.kmmidgroup + groupname + "']/../../..//i[@title='Mute']", "xpath");
					click(er.kmmidgroup + groupname + "']/../../..//i[@title='Mute']", "Mute");
					break;

				case "read":
					moveToElement(er.kmmidgroup + groupname + "']/../../..//i[@title='Read']", "xpath");
					click(er.kmmidgroup + groupname + "']/../../..//i[@title='Un-Read']", "Read");
					break;

				case "unread":
					moveToElement(er.kmmidgroup + groupname + "']/../../..//i[@title='Un-Read']", "xpath");
					click(er.kmmidgroup + groupname + "']/../../..//i[@title='Un-Read']", "Un-Read");
					break;

				case "3dots":
					moveToElement(er.kmmidgroup + groupname
							+ "']/../../..//div[@class='_content']/i[@class='icon __i kr-ellipsis']", "xpath");
					click(er.kmmidgroup + groupname
							+ "']/../../..//div[@class='_content']/i[@class='icon __i kr-ellipsis']", "xpath");
					break;

				default:
					test.log(LogStatus.FAIL, "User provided action is not available");
				}
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click on " + action,
					toString() + test.addScreenCapture(takeScreenShot()));
		}

	}

	public void optionsDisplayedOn3Dots(String... actual) {
		ArrayList<String> actuallist = null;
		try {
			List<WebElement> options = remoteDriver
					.findElements(By.xpath("//div[@class='krDropDownMenu active']//div"));
			int i = 0;
			for (WebElement ele : options) {

				String act = ele.getText();
				actuallist.add(act);
				boolean check = actual[i].equals(act);
				i++;
				if (check) {
					test.log(LogStatus.PASS, "Option " + i + 1 + " displayed as " + check);
					System.out.println("Pass");
				} else {
					System.out.println("Pass");
					test.log(LogStatus.FAIL, "Option " + i + 1 + " displayed as " + check);
				}
			}
			test.log(LogStatus.INFO, "3 dot options displayed as " + actuallist);
		} catch (Exception e) {
			System.out.println("wnt  wrong");
		}
	}

	/**
	 * 
	 * @param operation
	 *            : It should match with the text displayed in App operation
	 *            parameter can be given as follow : Leave Conversation , Manage
	 *            Conversation , Clear Chat History , Delete Conversation
	 * @throws Exception
	 */
	public void operationsFrom3Dots(String operation) throws Exception {
		try {
			click(er.km3dotoptions + operation + "']", "xpath");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Selected" + operation + "3dots");

		} catch (Exception e) {
			System.out.println(e);
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
			moveToElement(er.kmmidgroup + groupname
					+ "']/../../..//div[@class='avatarDiv']//span[@class='nameAvatar chatAvatar']", "xpath");
			Thread.sleep(2000);
			List<WebElement> totalparticipants = remoteDriver
					.findElements(By.xpath(er.kmmidgroup + groupname + "']/../../..//div[@class='userPopupUi']"));
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
			List<WebElement> profileicons = remoteDriver.findElements(By.xpath(er.kmmidgroup + groupname
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
			test.log(LogStatus.FAIL, "Failed to get Group profile icons of : " + groupname.toString()
					+ test.addScreenCapture(takeScreenShot()));
		}
		return allicons;
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

}
