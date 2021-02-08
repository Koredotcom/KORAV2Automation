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
		try {
			waitTillappear(er.kheadermenu, "xpath", "Enter Email ID");
			System.out.println("Header menu displayed");
			Thread.sleep(5000);
			List<WebElement> Menulist = remoteDriver.findElements(By.xpath(er.kheadermenu));
			System.out.println("" + Menulist.size());
			for (WebElement e : Menulist) {
				if (e.getText().trim().equalsIgnoreCase(menuoption)) {
					flag = true;
					e.click();
					Thread.sleep(1000);
					System.out.println(menuoption + ":" + "clicked successfully");
					test.log(LogStatus.INFO, menuoption + " selected");
					test.log(LogStatus.PASS, test.addScreenCapture(takeScreenShot()));
					break;
				}
			}
			if (!flag) {
				test.log(LogStatus.FAIL, menuoption + "  option not selected or it is not available in the options");
				test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
				System.out.println("Reached FailXXXXXXXX, Provided option is not available on the Dom");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Not clicked on menu option");
			test.log(LogStatus.FAIL, "Header menu option element got changed or login was not success");
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenShot()));
			e.printStackTrace();
		}
	}

}
