package com.org.kore.testbase;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.org.kore.utilities.ExtentReportUtility;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PageBase extends DriverSetUp {

	public RemoteWebDriver remoteDriver;
	static DesiredCapabilities dc = new DesiredCapabilities();
	public static String app;
	public AppiumDriver<MobileElement> appiumDriver;
	protected final String XPATH = "xpath=";
	protected final String ID = "id=";
	protected final String NAME = "name=";
	protected final String LINKTEXT = "link=";
	protected final String PARTLINKTEXT = "partlink=";
	protected final String COORDINATES = "coordinates=";
	public static String downloadFilepath = null;
	public static String webBrowser = null, chromeDriverPath = null, fireFoxDriverPath = null, IEDriverPath = null,
			deviceName = null, appiumPort = null, deviceVersion = null, appPackage = null, appActivity = null,
			Android_Appium_Server_Path = null, appiumPort_Ios = null, devicePlatformName_Ios = null,
			deviceVersion_Ios = null, device_UDID = null, platformName = null, applicationPath = null, appiumURL = null,
			ParentWinhadleMob = null, ParentWinhadle = null, mobileCloud = null, bundleID = null, edgeDriverPath = null;

	public PageBase(AppiumDriver driver) {
		this.appiumDriver = driver;

	}

	public PageBase(RemoteWebDriver remoteDriver) {
		this.remoteDriver = remoteDriver;

	}

	/**
	 * To Start the Appium driver based on the PlatformName
	 * 
	 * @param platformName
	 * @throws IOException
	 */
	public AppiumDriver<MobileElement> startAppiumDriver(String App) throws IOException {

		if (App.equalsIgnoreCase("ANDROIDNATIVE")) {
			// EnhancedAndroidDriver<MobileElement> driver;
			dc.setCapability("automationName", "UiAutomator2");
			dc.setCapability(MobileCapabilityType.UDID, DriverSetUp.propsMap.get("deviceName"));
			// dc.setCapability("app", app);
			// dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
			// DriverSetUp.propsMap.get("appPackage"));
			// dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
			// DriverSetUp.propsMap.get("appActivity"));
			dc.setCapability(MobileCapabilityType.APP, "D:\\CodeBases\\Kore\\TestAutomation\\Korav2.apk");
			dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60000);
			appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);

		} else if (App.equalsIgnoreCase("IOSNATIVE")) {
			// EnhancedIOSDriver<WebElement> driver;
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			// dc.setCapability("app", applicationPath);
			dc.setCapability(MobileCapabilityType.UDID, DriverSetUp.propsMap.get("device_UDID"));
			dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, DriverSetUp.propsMap.get("bundleID"));
			appiumDriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		} else if (App.equalsIgnoreCase("IOSWEB")) {
			dc.setCapability(MobileCapabilityType.UDID, DriverSetUp.propsMap.get("device_UDID"));
			dc.setBrowserName(MobileBrowserType.SAFARI);
			appiumDriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		}
		return appiumDriver;
	}

	public RemoteWebDriver startBrowser(String browser) throws Exception {
		try {

			if (browser.equalsIgnoreCase("chrome")) {

				System.out.println("In ChromeDriver");
				if (System.getProperty("os.name").contains("Mac OS")) {
					System.setProperty("webdriver.chrome.driver", DriverSetUp.UtilityMap.get("macchromeDriverPath"));
					ChromeOptions options = new ChromeOptions();
					// options.addArguments("user-data-dir=/path/to/your/custom/profile");
					options.addArguments("--profile-directory=Default");
					options.addArguments("--whitelisted-ips");
					options.addArguments("--disable-plugins-discovery");
					options.addArguments("--disable-extensions");
					options.addArguments("--test-type");
					options.addArguments("start-maximized");
					options.setExperimentalOption("useAutomationExtension", false);
					options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
					remoteDriver = new ChromeDriver();
					System.out.println("chrome started in Mac");
				} else {
					System.setProperty("webdriver.chrome.driver", DriverSetUp.UtilityMap.get("winchromeDriverPath"));
					ChromeOptions options = new ChromeOptions();
					
					options.addArguments("--profile-directory=Default");
					options.addArguments("--whitelisted-ips");
					options.addArguments("--disable-plugins-discovery");
					options.addArguments("--disable-extensions");
					options.addArguments("--test-type");
					options.addArguments("start-maximized");
					//options.addArguments("--start-fullscreen");
					//options.addArguments("window-size=1382,744");
					//options.setExperimentalOption("useAutomationExtension", false);
					
					//for incognito with headless
					//options.addArguments("--headless", "--window-size=1382,744", "--disable-gpu", "--disable-extensions", "--no-sandbox", "-incognito");*/
					
				//	options.addArguments("--headless", "--disable-gpu", "--window-size=1382,744","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
				//	options.addArguments("--headless");
					
					DesiredCapabilities cap = DesiredCapabilities.chrome();
					cap.setCapability("ignoreZoomSetting", true);
					cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					cap.setCapability(ChromeOptions.CAPABILITY, options);
					remoteDriver = new ChromeDriver(cap);
					System.out.println("chrome started in windows");
				}

			} else if (browser.equalsIgnoreCase("firefox")) {
				System.out.println("firefox started");
				WebDriverManager.firefoxdriver().setup();
				remoteDriver = new FirefoxDriver();
				remoteDriver.manage().window().maximize();

			} else if (browser.equalsIgnoreCase("edge")) {
				System.out.println("launching Microsoft Edge browser");
				WebDriverManager.edgedriver().setup();
				remoteDriver = new EdgeDriver();
				remoteDriver.manage().window().maximize();
			} else if (browser.equalsIgnoreCase("IE")) {
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
				// IEDriverPath = DriverSetUp.UtilityMap.get("ieDriverPath");
				// System.setProperty("webdriver.ie.driver", IEDriverPath);
				WebDriverManager.iedriver().setup();
				System.out.println("ie driver");
				remoteDriver = new InternetExplorerDriver();
				remoteDriver.manage().window().maximize();

			} else if (browser.equalsIgnoreCase("safari")) {
				System.out.println("launching Safari browser");
				remoteDriver = new SafariDriver();
				remoteDriver.manage().window().maximize();
			}
		} catch (Exception e) {
			System.out.println("Provide valid broser to start your execution");
		}
		return remoteDriver;
	}

	public List<MobileElement> findElements(String property) {
		List<MobileElement> elements = null;
		if (property.startsWith(ID)) {
			elements = appiumDriver.findElements(By.id(property.replaceFirst(ID, "")));
		} else if (property.startsWith(NAME)) {
			elements = appiumDriver.findElements(By.name(property.replaceFirst(NAME, "")));
		} else if (property.startsWith(LINKTEXT)) {
			elements = appiumDriver.findElements(By.linkText(property.replaceFirst(LINKTEXT, "")));
		} else if (property.startsWith(PARTLINKTEXT)) {
			elements = appiumDriver.findElements(By.partialLinkText(property.replaceFirst(PARTLINKTEXT, "")));
		} else if (property.startsWith(XPATH)) {
			elements = appiumDriver.findElements(By.xpath(property.replaceFirst(XPATH, "")));
		}
		return elements;
	}

	public WebElement findElement(String property) {
		WebElement element = null;
		if (property.startsWith(ID)) {
			element = appiumDriver.findElement(By.id(property.replaceFirst(ID, "")));
		} else if (property.startsWith(NAME)) {
			element = appiumDriver.findElement(By.name(property.replaceFirst(NAME, "")));
		} else if (property.startsWith(LINKTEXT)) {
			element = appiumDriver.findElement(By.linkText(property.replaceFirst(LINKTEXT, "")));
		} else if (property.startsWith(PARTLINKTEXT)) {
			element = appiumDriver.findElement(By.partialLinkText(property.replaceFirst(PARTLINKTEXT, "")));
		} else if (property.startsWith(XPATH)) {
			element = appiumDriver.findElement(By.xpath(property.replaceFirst(XPATH, "")));
		}
		return element;
	}
	
	public void clickNIgnoreFail(String xpath, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				appiumDriver.findElementByXPath(xpath).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 5, 500);
				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				remoteDriver.findElementByXPath(xpath).click();
				break;
			}
		} catch (Exception exc) {
			test.log(LogStatus.INFO,
					elementName+" element is not available but still it doesn't have any impact".toString() + test.addScreenCapture(takeScreenShot()));
		}
	}

	public void waitToappearIgnoreFail(String locator, String locatorType, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 20, 500);
				if (locatorType.equalsIgnoreCase("xpath")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				} else if (locatorType.equalsIgnoreCase("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

				} else if (locatorType.equalsIgnoreCase("css")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

				} else if (locatorType.equalsIgnoreCase("name")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));

				}

				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 20, 500);
				if (locatorType.equalsIgnoreCase("xpath")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				} else if (locatorType.equalsIgnoreCase("id")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

				} else if (locatorType.equalsIgnoreCase("css")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

				} else if (locatorType.equalsIgnoreCase("name")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));

				} else if (locatorType.equalsIgnoreCase("class")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

				}

				break;
			}

		} catch (Exception exc) {
			System.out.println("Failed to find in waitToappearIgnoreFail " + locator+" i.e."+ elementName);
		}
	}
	
	public void click(String xpath, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				appiumDriver.findElementByXPath(xpath).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				remoteDriver.findElementByXPath(xpath).click();
				break;
			}
		} catch (Exception exc) {
			test.log(LogStatus.FAIL,
					elementName + " element is not displayed".toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public void jsClick(String xpath, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":
				
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				appiumDriver.findElementByXPath(xpath).click();
				break;
				
			case "Selenium":
				
				Thread.sleep(2000);
				JavascriptExecutor js = (JavascriptExecutor) remoteDriver;
				WebElement button = remoteDriver.findElement(By.xpath(xpath));
				js.executeScript("arguments[0].click();", button);
				break;
			}
		} catch (Exception exc) {
			test.log(LogStatus.FAIL, exc + "Exception on clicking webelement i.e. <b>"+elementName+"</b> ",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public void clickByLinkText(String linkText, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
				appiumDriver.findElementByLinkText(linkText).click();
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
				remoteDriver.findElementByLinkText(linkText).click();
				break;
			}
			test.log(LogStatus.PASS,
					"Click on Element " + elementName + " successful &nbsp" + test.addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			test.log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public void waitTillClickable(String xpath, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":
				
				WebDriverWait wait = new WebDriverWait(appiumDriver, 20, 500);
				WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
				break;
			case "Selenium":
				
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 30, 500);
				WebElement el = waitSelenium.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
				//el.click();
				break;

			}
		} catch (Exception exc) {
			System.out.println("Fail... Waited for 20 secs for element to be clickable");
			test.log(LogStatus.UNKNOWN, "Exception on  Element to get disappear from the screen, even after 120 secs"
					.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	
	
	public void waitUntilDissapear(String xpath, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 300, 500);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 120, 500);
				waitSelenium.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
				break;

			}
		} catch (Exception exc) {
			System.out.println("Fail... Waited for 2 minutes to disappear //div[@class='lds-ring'] and to get Home screen loaded");
			test.log(LogStatus.UNKNOWN, "Exception on  Element to get disappear from the screen, even after 120 secs"
					.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public void iOS_scroll(WebElement source, WebElement target, String scrollDirection) throws Exception {
		try {
			for (int i = 0; i < 100; i++) {
				JavascriptExecutor jse = (JavascriptExecutor) appiumDriver;
				HashMap scobj = new HashMap();
				scobj.put("direction", scrollDirection);
				scobj.put("element", source);
				jse.executeScript("mobile:scroll", scobj);
				Thread.sleep(1000);

				if (target.isDisplayed() || target.isEnabled()) {
					test.log(LogStatus.PASS,
							"The element should be displayed " + "\n" + "The element is displayed" + appiumDriver);
					break;
				} else {
					System.out.println("For now do nothing");
				}

			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "iOS_scrollDown" + "\n" + e.getMessage() + appiumDriver);
		}
	}

	public void waitUntilDissapear(String locator, String locatorType, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 300, 500);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 40, 500);
				if (locatorType.equalsIgnoreCase("xpath")) {
					waitSelenium.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
				} else if (locatorType.equalsIgnoreCase("id")) {
					waitSelenium.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));

				} else if (locatorType.equalsIgnoreCase("css")) {
					waitSelenium.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator)));

				}

				break;
			}

		} catch (Exception exc) {
			test.log(LogStatus.FAIL, exc + "Exception on  Element not present",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public void waitAndContinue(String locator, String locatorType, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 20, 500);
				if (locatorType.equalsIgnoreCase("xpath")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				} else if (locatorType.equalsIgnoreCase("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

				} else if (locatorType.equalsIgnoreCase("css")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

				} else if (locatorType.equalsIgnoreCase("name")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));

				}

				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 20, 500);
				if (locatorType.equalsIgnoreCase("xpath")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				} else if (locatorType.equalsIgnoreCase("id")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

				} else if (locatorType.equalsIgnoreCase("css")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

				} else if (locatorType.equalsIgnoreCase("name")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));

				} else if (locatorType.equalsIgnoreCase("class")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

				}

				break;
			}

		} catch (Exception exc) {
			test.log(LogStatus.INFO,
					elementName + " element is not displayed but still it doesn't have any impact".toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}
	
	public void waitTillappear(String locator, String locatorType, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 20, 500);
				if (locatorType.equalsIgnoreCase("xpath")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				} else if (locatorType.equalsIgnoreCase("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

				} else if (locatorType.equalsIgnoreCase("css")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

				} else if (locatorType.equalsIgnoreCase("name")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));

				}

				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
				if (locatorType.equalsIgnoreCase("xpath")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				} else if (locatorType.equalsIgnoreCase("id")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

				} else if (locatorType.equalsIgnoreCase("css")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

				} else if (locatorType.equalsIgnoreCase("name")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));

				} else if (locatorType.equalsIgnoreCase("class")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

				}

				break;
			}

		} catch (Exception exc) {
			System.out.println("Failed to find " + locator+" i.e."+ elementName);
			test.log(LogStatus.FAIL,
					locator + " element is not displayed ".toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}
	
	public void waitToappear(String locator, String locatorType, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 20, 500);
				if (locatorType.equalsIgnoreCase("xpath")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				} else if (locatorType.equalsIgnoreCase("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

				} else if (locatorType.equalsIgnoreCase("css")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

				} else if (locatorType.equalsIgnoreCase("name")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));

				}

				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 15, 500);
				if (locatorType.equalsIgnoreCase("xpath")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				} else if (locatorType.equalsIgnoreCase("id")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

				} else if (locatorType.equalsIgnoreCase("css")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

				} else if (locatorType.equalsIgnoreCase("name")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));

				} else if (locatorType.equalsIgnoreCase("class")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

				}

				break;
			}

		} catch (Exception exc) {
			System.out.println("Failed to find " + locator+" i.e."+ elementName);
			test.log(LogStatus.FAIL,
					elementName + " element is not displayed ".toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public void click(WebElement e, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 80, 500);
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(e)));
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
				waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
				break;
			}
			e.click();
			test.log(LogStatus.PASS, "Click on " + elementName + " successful".toString(),
					test.addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			test.log(LogStatus.FAIL, elementName + " not found",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(elementName + " not found");

		}

	}

	public void clear(String xpath, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				appiumDriver.findElementByXPath(xpath).clear();
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				remoteDriver.findElementByXPath(xpath).clear();
				break;
			}
			test.log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					test.addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			test.log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public void clickbyid(String id, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				appiumDriver.findElementById(id).click();
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 1000);
				remoteDriver.findElementById(id).click();
				break;
			}
			test.log(LogStatus.INFO, elementName + "   Clicked Successfully");

		} catch (Exception exc) {
			test.log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public void scrollBottomOfPage() {

		JavascriptExecutor js = (JavascriptExecutor) remoteDriver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void moveToElement(String locator, String locatorType) throws InterruptedException, Exception {
		if (locatorType.equals("xpath")) {

			WebElement element = remoteDriver.findElement(By.xpath(locator));
			Actions actions = new Actions(remoteDriver);
			actions.moveToElement(element);
			actions.perform();
		} else if (locatorType.equals("css")) {

			WebElement element = remoteDriver.findElement(By.cssSelector(locator));
			Actions actions = new Actions(remoteDriver);
			actions.moveToElement(element);
			actions.perform();
		} else if (locatorType.equals("id")) {

			WebElement element = remoteDriver.findElement(By.id(locator));
			Actions actions = new Actions(remoteDriver);
			actions.moveToElement(element);
			actions.perform();
		} else if (locatorType.equals("class")) {

			WebElement element = remoteDriver.findElement(By.className(locator));
			Actions actions = new Actions(remoteDriver);
			actions.moveToElement(element);
			actions.perform();
		}
	}

	public void scrollToElement(String locator, String locatorType) throws InterruptedException {
		WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 1000);

		if (locatorType.equals("xpath")) {
			waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			WebElement element = remoteDriver.findElement(By.xpath(locator));
			((JavascriptExecutor) remoteDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
		} else if (locatorType.equals("css")) {
			waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			WebElement element = remoteDriver.findElement(By.cssSelector(locator));
			((JavascriptExecutor) remoteDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
		} else if (locatorType.equals("id")) {
			waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			WebElement element = remoteDriver.findElement(By.id(locator));
			((JavascriptExecutor) remoteDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
		} else if (locatorType.equals("class")) {
			waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			WebElement element = remoteDriver.findElement(By.className(locator));
			((JavascriptExecutor) remoteDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
		}

	}

	public void clickbycss(String css, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 100, 500);
				appiumDriver.findElementByCssSelector(css).click();
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 1000);
				remoteDriver.findElementByCssSelector(css).click();
				break;
			}
			test.log(LogStatus.INFO, elementName + "   Clicked Successfully");

		} catch (Exception exc) {

			exc.printStackTrace();
			test.log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public int getSize(String xpath) throws Exception {
		try {
			int size = 0;
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				List<MobileElement> sizeAppium = appiumDriver.findElementsByXPath(xpath);
				size = sizeAppium.size();
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 1000);
				List<WebElement> sizeSelenium = remoteDriver.findElementsByXPath(xpath);
				size = sizeSelenium.size();

				break;
			}

			return size;

		} catch (Exception exc) {

			exc.printStackTrace();
			test.log(LogStatus.FAIL, exc + "Exception in getting of element",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public ArrayList<String> selectVal(String id, String Selectvalue) {

		WebElement element = remoteDriver.findElement(By.id(id));
		Select oSelect = new Select(element);
		List<WebElement> elementCount = oSelect.getOptions();
		ArrayList<String> elementList = new ArrayList<>();
		for (int i = 0; i < elementCount.size(); i++) {
			elementList.add(elementCount.get(i).getText());
		}
		oSelect.selectByVisibleText(Selectvalue);
		return elementList;
	}

	public ArrayList<String> getListValuesinDropdown(String id) {

		WebElement element = remoteDriver.findElement(By.id(id));
		Select oSelect = new Select(element);
		List<WebElement> elementCount = oSelect.getOptions();
		ArrayList<String> elementList = new ArrayList<>();
		for (int i = 0; i < elementCount.size(); i++) {
			elementList.add(elementCount.get(i).getText());
		}

		return elementList;
	}

	public ArrayList<String> getListFrmDrpDwn(String id) {
		WebElement element = remoteDriver.findElement(By.id(id));
		Select oSelect = new Select(element);
		List<WebElement> elementCount = oSelect.getOptions();
		ArrayList<String> elementList = new ArrayList<>();
		for (int i = 0; i < elementCount.size(); i++) {
			elementList.add(elementCount.get(i).getText());
		}

		return elementList;
	}

	public void clickbyClassName(String className, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
				appiumDriver.findElementByClassName(className).click();

				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 1000);
				remoteDriver.findElementByClassName(className).click();
				break;
			}
			test.log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					test.addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			exc.printStackTrace();
			test.log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public void clickByElementName(String name, String elementName) throws Exception {

		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
				appiumDriver.findElementByName(name).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
				waitSelenium.until(ExpectedConditions.elementToBeClickable(By.name(name)));
				remoteDriver.findElementByName(name).click();
				break;
			}

			test.log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					test.addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {

			test.log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public void clickAlert() throws Exception {

		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				Alert a2 = remoteDriver.switchTo().alert();
				a2.accept();
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 15, 500);
				Alert a1 = remoteDriver.switchTo().alert();
				a1.accept();
				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();

			test.log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public void dragAndDrop(WebElement e1, WebElement e2) throws Exception {

		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				Actions action = new Actions(appiumDriver);
				action.dragAndDrop(e1, e2).perform();
				break;
			case "Selenium":

				Actions action1 = new Actions(remoteDriver);
				action1.dragAndDrop(e1, e2).perform();
				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();

			test.log(LogStatus.FAIL, exc + "Exception on Drag element",
					exc.toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public String getPageTitle() throws Exception {

		String pageTitle = "";
		if (DriverSetUp.propsMap.get("tool").equalsIgnoreCase("Selenium")) {
			pageTitle = remoteDriver.getTitle();
		} else {
			Thread.sleep(2000);
			pageTitle = appiumDriver.getTitle();

		}

		return pageTitle;
	}

	public boolean isEnabled(String locator, String locatorType) throws Exception {
		boolean flag = false;
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				if (locatorType.equals("xpath")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					flag = appiumDriver.findElementByXPath(locator).isEnabled();

				} else if (locatorType.equals("css")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
					flag = appiumDriver.findElementByCssSelector(locator).isEnabled();

				} else if (locatorType.equals("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
					flag = appiumDriver.findElementById(locator).isEnabled();

				} else if (locatorType.equals("class")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
					flag = appiumDriver.findElementByClassName(locator).isEnabled();
				}

				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 20, 250);

				if (locatorType.equals("xpath")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					flag = remoteDriver.findElementByXPath(locator).isEnabled();

				} else if (locatorType.equals("css")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
					flag = remoteDriver.findElementByCssSelector(locator).isEnabled();

				} else if (locatorType.equals("id")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
					flag = remoteDriver.findElementById(locator).isEnabled();

				} else if (locatorType.equals("class")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
					flag = remoteDriver.findElementByClassName(locator).isEnabled();
				}

				break;
			}

		} catch (Exception exc) {
			return false;
		}
		return flag;

	}

	public String getValue(WebElement e, String elementName) throws Exception {

		switch (DriverSetUp.propsMap.get("tool")) {
		case "Appium":

			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(e));
			break;
		case "Selenium":

			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			break;
		}

		String text = e.getAttribute("value");
		test.log(LogStatus.PASS, "Click on Element " + elementName + " successful",
				test.addScreenCapture(takeScreenShot()));
		return text;

	}

	public void enterTextWebelement(WebElement e, String cred, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				e.click();
				e.sendKeys(cred);
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				break;
			}

			test.log(LogStatus.PASS, " Enter text in " + elementName + " successful",
					test.addScreenCapture(takeScreenShot()));
		} catch (Exception exc) {

			test.log(LogStatus.FAIL, "Enter text failed", test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc + " Exception on Verified webelement");

		}

	}

	public void enterText(String locator, String cred, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				appiumDriver.findElement(By.xpath(locator)).clear();
				appiumDriver.findElement(By.xpath(locator)).sendKeys(cred);
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				remoteDriver.findElement(By.xpath(locator)).click();
				remoteDriver.findElement(By.xpath(locator)).clear();
				remoteDriver.findElement(By.xpath(locator)).sendKeys(cred);
				break;
			}

			test.log(LogStatus.PASS, " Entered " + cred + " in " + elementName);
		} catch (Exception exc) {

			test.log(LogStatus.FAIL,
					"Enter text failed on" + elementName + " ".toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc + " Exception on Verified webelement");

		}

	}

	public void clearAndenterText(String locator, String cred, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				appiumDriver.findElement(By.xpath(locator)).click();
				WebElement mele = remoteDriver.findElement(By.xpath(locator));
				mele.sendKeys(Keys.CONTROL + "a");
				mele.sendKeys(Keys.DELETE);
				appiumDriver.findElement(By.xpath(locator)).sendKeys(cred);
				break;
			case "Selenium":
				
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				remoteDriver.findElement(By.xpath(locator)).click();
				WebElement ele = remoteDriver.findElement(By.xpath(locator));
				
				String os = System.getProperty("os.name");
				if (os.equalsIgnoreCase("WINDOWS")){
				ele.sendKeys(Keys.CONTROL + "a");
				ele.sendKeys(Keys.DELETE);
				}else{
				ele.sendKeys(Keys.COMMAND + "a");
				ele.sendKeys(Keys.DELETE);
				}
				/*ele.sendKeys(Keys.CONTROL + "a");
				ele.sendKeys(Keys.DELETE);*/
				remoteDriver.findElement(By.xpath(locator)).sendKeys(cred);
				test.log(LogStatus.PASS, "Entered as " + cred + " in " + elementName + " ".toString()
						+ test.addScreenCapture(takeScreenShot()));
				break;
			}

		} catch (Exception exc) {
			test.log(LogStatus.FAIL,
					"Enter text failed on" + elementName + " ".toString() + test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc + " Exception on Verified webelement");

		}

	}

	public void enterText(String locator, String cred, String locatorType, String elementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				if (locatorType.equals("xpath")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					appiumDriver.findElement(By.xpath(locator)).clear();
					appiumDriver.findElement(By.xpath(locator)).sendKeys(cred);
				} else if (locatorType.equals("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
					appiumDriver.findElement(By.id(locator)).clear();
					appiumDriver.findElement(By.id(locator)).sendKeys(cred);
				} else if (locatorType.equals("css")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
					appiumDriver.findElement(By.cssSelector(locator)).clear();
					appiumDriver.findElement(By.cssSelector(locator)).sendKeys(cred);
				} else if (locatorType.equals("name")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
					appiumDriver.findElement(By.name(locator)).clear();
					appiumDriver.findElement(By.name(locator)).sendKeys(cred);
				}
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
				if (locatorType.equals("xpath")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					remoteDriver.findElement(By.xpath(locator)).clear();
					remoteDriver.findElement(By.xpath(locator)).sendKeys(cred);
				} else if (locatorType.equals("id")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
					remoteDriver.findElement(By.id(locator)).clear();
					remoteDriver.findElement(By.id(locator)).sendKeys(cred);
				} else if (locatorType.equals("css")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
					remoteDriver.findElement(By.cssSelector(locator)).clear();
					remoteDriver.findElement(By.cssSelector(locator)).sendKeys(cred);
				} else if (locatorType.equals("name")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
					remoteDriver.findElement(By.name(locator)).clear();
					remoteDriver.findElement(By.name(locator)).sendKeys(cred);
				}

				break;
			}

		} catch (Exception exc) {
			test.log(LogStatus.FAIL, "Enter text failed", test.addScreenCapture(takeScreenShot()));
			throw new Exception(exc + " Exception on Verified webelement");
		}

	}

	public void waitTime(long milsec) throws InterruptedException {
		Thread.sleep(milsec);
	}

	public String getText(String locator, String locatorType) throws Exception {
		String text = null;
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				if (locatorType.equals("xpath")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

					text = appiumDriver.findElement(By.xpath(locator)).getText();
				} else if (locatorType.equals("css")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector((locator))));

					text = appiumDriver.findElement(By.cssSelector(locator)).getText();
				} else if (locatorType.equals("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

					text = appiumDriver.findElement(By.id(locator)).getText();
				} else if (locatorType.equals("class")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

					text = appiumDriver.findElement(By.className(locator)).getText();
				}
				break;
			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
				if (locatorType.equals("xpath")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

					text = remoteDriver.findElement(By.xpath(locator)).getText();
				} else if (locatorType.equals("css")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

					text = remoteDriver.findElement(By.cssSelector(locator)).getText();
				} else if (locatorType.equals("id")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

					text = remoteDriver.findElement(By.id(locator)).getText();
				} else if (locatorType.equals("class")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

					text = remoteDriver.findElement(By.className(locator)).getText();
				}
				break;
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Failed to get text from " + locator + " ".toString() + test.addScreenCapture(takeScreenShot()));
		}
		return text;

	}

	public String getText(String xpath) throws Exception {
		String text = null;

		switch (DriverSetUp.propsMap.get("tool")) {
		case "Appium":

			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByXPath(xpath)));
			text = appiumDriver.findElementByXPath(xpath).getText();
			break;
		case "Selenium":

			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 8, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElementByXPath(xpath)));
			text = remoteDriver.findElementByXPath(xpath).getText();

			break;
		}

		return text;
	}

	public String getAttributeValue(String xpath, String attribute) throws Exception {
		String text = null;
		switch (DriverSetUp.propsMap.get("tool")) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			text = appiumDriver.findElementByXPath(xpath).getAttribute(attribute).trim();
			break;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
			waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			text = remoteDriver.findElementByXPath(xpath).getAttribute(attribute).trim();
			break;
		}

		return text;

	}

	public boolean elementIsDisplayed(String locator, String locatorType, long timeOut, long sleepIn) throws Exception {
		boolean flag = false;
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, timeOut, sleepIn);
				if (locatorType.equals("xpath")) {

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					flag = true;
				} else if (locatorType.equals("css")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

					flag = true;
				} else if (locatorType.equals("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

					flag = true;
				} else if (locatorType.equals("class")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

					flag = true;
				}

				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, timeOut, sleepIn);

				if (locatorType.equals("xpath")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					flag = true;

				} else if (locatorType.equals("css")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

					flag = true;
				} else if (locatorType.equals("id")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

					flag = true;
				} else if (locatorType.equals("class")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

					flag = true;
				}
				break;
			}

		} catch (Exception exc) {
			return false;
		}
		return flag;

	}

	public boolean elementIsDisplayed(String locator, String locatorType) throws Exception {
		boolean flag = false;
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				if (locatorType.equals("xpath")) {

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					flag = true;
				} else if (locatorType.equals("css")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

					flag = true;
				} else if (locatorType.equals("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

					flag = true;
				} else if (locatorType.equals("class")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

					flag = true;
				}

				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 100);

				if (locatorType.equals("xpath")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					flag = true;

				} else if (locatorType.equals("css")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

					flag = true;
				} else if (locatorType.equals("id")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));

					flag = true;
				} else if (locatorType.equals("class")) {
					waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));

					flag = true;
				}
				break;
			}

		} catch (Exception exc) {
			return false;
		}
		return flag;

	}

	public boolean elementIsDisplayedById(String id, String ElementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 10, 500);
				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
				break;
			}
			return true;

		} catch (Exception exc) {
			return false;
		}

	}

	public boolean elementIsDisplayedByXpath(String xpath, String ElementName) throws Exception {
		try {
			switch (DriverSetUp.propsMap.get("tool")) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 10, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 5, 500);
				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				break;
			}
			return true;

		} catch (Exception exc) {
			return false;
		}

	}

	public void switchToCurrentWindowTitle() throws InterruptedException {
		try {

			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":
				Thread.sleep(10000);
				int size = appiumDriver.getWindowHandles().size();
				for (String winHandle : appiumDriver.getWindowHandles()) {
					appiumDriver.switchTo().window(winHandle);
				}

				break;
			case "Selenium":
				for (String winHandle : remoteDriver.getWindowHandles()) {
					remoteDriver.switchTo().window(winHandle);
					Thread.sleep(5000);
				}
				break;
			}
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}

	}

	public String takeScreenShot() throws IOException {
		Calendar cal = Calendar.getInstance();
		long s = cal.getTimeInMillis();
		File screen = null;
		try {
			switch (DriverSetUp.propsMap.get("tool")) {
			case "Appium":
				screen = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				screen = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(screen, new File(
					"ReportGenerator/" + ExtentReportUtility.reportFolder + "/Screenshots/image" + s + ".png"));
			Files.deleteIfExists(Paths.get(screen.getAbsolutePath()));
		} catch (Exception e) {
			System.out.println(e);
		}
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		File srcFile = new File(
				"ReportGenerator/" + ExtentReportUtility.reportFolder + "/Screenshots/image" + s + ".png");
		fileInputStreamReader = new FileInputStream(
				new File("ReportGenerator/" + ExtentReportUtility.reportFolder + "/Screenshots/image" + s + ".png"));
		byte[] bytes = new byte[(int) srcFile.length()];
		fileInputStreamReader.read(bytes);
		encodedBase64 = new String(Base64.encodeBase64(bytes));

		String completepath = srcFile.getAbsolutePath();
		// return srcFile.getAbsolutePath();

		// Added to avaoid Absolute path dependency
		String result[] = completepath.trim().split("Screenshots");
		String finalwithscr = "Screenshots" + result[1];
		return finalwithscr;

	}

	public void takeScreenshot(String screenshotName, String videoPlayerPath) {

		try {
			WebElement ele = remoteDriver.findElement(By.xpath(videoPlayerPath));
			if (ele.isDisplayed()) {
				File screen = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);

				int ImageWidth = ele.getSize().getWidth();
				int ImageHeight = ele.getSize().getHeight();
				Point point = ele.getLocation();
				int xcord = point.getX();
				int ycord = point.getY();
				BufferedImage img = ImageIO.read(screen);
				BufferedImage dest = img.getSubimage(xcord, ycord, ImageWidth, ImageHeight);
				ImageIO.write(dest, "png", screen);
				FileUtils.copyFile(screen, new File("PhotoPassScreenshots/" + screenshotName + ".png"));
			}
		} catch (Exception e) {

			try {
				test.log(LogStatus.FAIL, e + " Taking Screenshots Fails ", test.addScreenCapture(takeScreenShot()));
				throw new Exception(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public void clearChromeCache() throws Exception {
		try {
			remoteDriver.get("chrome://settings/clearBrowserData");
			Thread.sleep(3000);

			JavascriptExecutor js = (JavascriptExecutor) remoteDriver;
			String clickcleardata = "document.querySelector('body > settings-ui').shadowRoot.querySelector('#main').shadowRoot.querySelector('settings-basic-page').shadowRoot.querySelector('settings-section > settings-privacy-page').shadowRoot.querySelector('settings-clear-browsing-data-dialog').shadowRoot.querySelectorAll('cr-button')[1]"
					+ ".click()";
			js.executeScript(clickcleardata);
			test.log(LogStatus.INFO, " Browser cache cleared successfully to login with different user with in the same session");

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					e + "Failed to clear the cache so your test case may faill to login with other user with in the same session"
							.toString() + test.addScreenCapture(takeScreenShot()));
			System.out.println("In catch");
			e.printStackTrace();
		}
	}

	public void scrollDown() {

		RemoteWebElement element = (RemoteWebElement) appiumDriver.findElement(By.className("XCUIElementTypeCell"));
		String elementID = element.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", elementID);
		scrollObject.put("direction", "down");
		appiumDriver.executeScript("mobile:scroll", scrollObject);
	}
	
	public void getCoordinates(String element) throws Exception {
		WebElement ele = remoteDriver.findElement(By.xpath(element));
		// Used points class to get x and y coordinates of element.
		Point classname = ele.getLocation();
		int xcordi = classname.getX();
		System.out.println("Element's Position from left side" + xcordi + " pixels.");
		int ycordi = classname.getY();
		System.out.println("Element's Position from top" + ycordi + " pixels.");
	}

}
