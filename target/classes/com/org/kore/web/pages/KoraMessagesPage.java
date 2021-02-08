package com.org.kore.web.pages;

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
 * @Description : All the functions related Home
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

	public void messagesScreenValidations() throws Exception {
		// SoftAssert softAssertion= new SoftAssert();
		waitTillappear(er.kmplusicon, "xpath", "Plus icon");
		String plusicon = getAttributeValue(er.kmplusicon, "title");
		if (!plusicon.equalsIgnoreCase("New Conversation"))
			test.log(LogStatus.FAIL, "New conversation text displayed as " + plusicon);
		test.log(LogStatus.INFO, "For + icon text displayed as : <b>New Conversation</b> ");
	}
	
	public void checkRecents() throws Exception {
		try{
		List<WebElement> recents = remoteDriver.findElements(By.xpath(er.kmrecent));
		if (recents.size()>0)
		test.log(LogStatus.PASS, "Displayed "+recents.size()+" Recent suggestions");
		test.log(LogStatus.INFO, test.addScreenCapture(takeScreenShot()));
		
	}catch (Exception e){
		test.log(LogStatus.FAIL, "Not displaying any recent suggestions for a new conversation");
		test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
	}
		
	}

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

	// Will change this to Do while Logic so that one method would suffise our
	// req
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
	 *            : Mentioned text from here will be sent as a message
	 * @throws Exception
	 */
	public void enterYourMessageAs(String enterthistext) throws Exception {
		try {
			WebElement compose = remoteDriver.findElement(By.xpath("//div[@placeholder='Type your message']"));
			compose.sendKeys(enterthistext, Keys.ENTER);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Entered messages as : <b>" +  enterthistext + "</b>");
			test.log(LogStatus.INFO, "Entered message as " + enterthistext, test.addScreenCapture(takeScreenShot()));

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Compose bar is not displaying");
			test.log(LogStatus.FAIL, "Unable to enter the message", test.addScreenCapture(takeScreenShot()));
		}

	}
	
	// Yet to implement switch case for on hover elements
	public void goToGroupAndPerform(String groupname,String action) throws Exception {
		moveToElement(er.kmmidgroup+groupname+"']", "xpath");
		String timestamp=getText("//span[@class='dayTime']");
		test.log(LogStatus.INFO, "For "+groupname+"Timestamp displayed as : <b>"+timestamp+"</b>");
		
		try {
			switch (action.trim()) {
			case "Click":
				click(er.kmmidgroup+groupname+"']", "Group click");
				break;

			case "Star":
				moveToElement(er.kmmidgroup+groupname+"']/../../..//span[@title='Star']", "xpath");
				click(er.kmmidgroup+groupname+"']/../../..//span[@title='Star']", "Star");
				break;
				
			case "Unstar":
				moveToElement(er.kmmidgroup+groupname+"']/../../..//span[@title='Unstar']", "xpath");
				click(er.kmmidgroup+groupname+"']/../../..//span[@title='Unstar']", "Unstar");
				break;

			case "Mute":
				moveToElement(er.kmmidgroup+groupname+"']/../../..//i[@title='Mute']", "xpath");
				click(er.kmmidgroup+groupname+"']/../../..//i[@title='Mute']", "Mute");
				break;

			case "read":
				moveToElement(er.kmmidgroup+groupname+"']/../../..//i[@title='Read']", "xpath");
				click(er.kmmidgroup+groupname+"']/../../..//i[@title='Un-Read']", "Read");
				break;

			case "unread":
				moveToElement(er.kmmidgroup+groupname+"']/../../..//i[@title='Un-Read']", "xpath");
				click(er.kmmidgroup+groupname+"']/../../..//i[@title='Un-Read']", "Un-Read");
				break;
				
			case "3dots":
				moveToElement(er.kmmidgroup+groupname+"']/../../..//div[@class='_content']/i[@class='icon __i kr-ellipsis']", "xpath");
				click(er.kmmidgroup+groupname+"']/../../..//div[@class='_content']/i[@class='icon __i kr-ellipsis']", "xpath");
				break;

			default:
				test.log(LogStatus.FAIL, "User provided action is not available");
			}
			
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
		}

	}
	
	// This may not be required ... if needed yet to modigy 
	public void operationsFrom3Dots1(String operation) throws Exception {
		try {
			switch (operation.trim()) {
			case "Leave Conversation":
				click(er.km3dotoptions+operation+"']", "xpath");
				break;

			case "Manage Conversation":
				break;
				
			case "Clear Chat History":
				break;

			case "Delete Conversation":
				break;

			default:
				test.log(LogStatus.FAIL, "User provided action is not available");
			}
			
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
		}

	}
	
	/**
	 * 
	 * @param operation : It should match with the text displayed in App
	 * operation parameter can be given as follow :
	 * Leave Conversation , Manage Conversation , Clear Chat History , Delete Conversation
	 * @throws Exception
	 */
	public void operationsFrom3Dots(String operation) throws Exception{
		try{
			click(er.km3dotoptions+operation+"']", "xpath");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Selected" + operation +"3dots");
		
	}catch (Exception e){
		System.out.println(e);
	}
		
	}

}
