package com.org.kore.web.pages;

import java.util.List;

import org.openqa.selenium.By;
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

public class KoraHomePage extends PageBase {
	CPCommonFunctions cf;
	ElementRepository er = DriverSetUp.er;

	public KoraHomePage(RemoteWebDriver remoteWebDriver) {
		super(remoteWebDriver);
		cf = new CPCommonFunctions(remoteWebDriver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param menuoption
	 *            To select menu option by passing header menu text (Home
	 *            ,Workspaces, Tasks, Messages, Meetings, Knowledge)
	 * @throws Exception
	 */

	public void selectMenuOption(String menuoption) throws Exception {
		boolean flag = false;
		waitTillappear(er.kheadermenu, "xpath", "Top header");
		System.out.println("Header menu displayed");
		Thread.sleep(5000);
		List<WebElement> Menulist = remoteDriver.findElements(By.xpath(er.kheadermenu));
		for (WebElement e : Menulist) {
			if (e.getText().trim().equalsIgnoreCase(menuoption)) {
				flag = true;
				e.click();
				System.out.println(menuoption+" option got selected");
				Thread.sleep(1000);
				test.log(LogStatus.PASS, menuoption + " selected".toString() + test.addScreenCapture(takeScreenShot()));
				break;
			}
		}
		if (!flag) {
			System.out.println(menuoption+" option was not selected");
			test.log(LogStatus.FAIL,
					menuoption + "  option not selected or it is not available in the options".toString()
							+ test.addScreenCapture(takeScreenShot()));
			System.out.println("Reached FailXXXXXXXX, Provided option is not available on the Dom");
		}
	}

}
