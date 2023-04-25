package com.sat.testUtil;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sat.Pages.LoginPage;
//import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

//import com.sat.Pages.String;
import com.sat.testbase.TestBase;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class Testutil {
	WebDriver driver = TestBase.getDriver();
	public static long PAGE_LOAD_TIMEOUT = 50;
	public static long IMPLICIT_WAIT = 40;
	public static long EXPLICIT_WAIT = 5000;
	public static String currentTime = "";

	// TestBase testbase = new TestBase();
	// public WebDriver driver;
	Actions action = new Actions(driver);
	public Properties prop;
	// String workingDir = System.getProperty("user.dir"+
	// "/src/main/java/com/sat/config/config.properties");
	LoginPage loginpage = new LoginPage(driver);

	public Testutil(WebDriver driver) {
		this.driver = driver;
	}

	public void switchToFrame(String frame) {
		driver.switchTo().frame(frame);
	}

	public void loginApplication(String userid, String password) {
		try {
			loginpage.enteruserid(prop.getProperty(userid));
			loginpage.clickonNext();
			loginpage.enterpassword(prop.getProperty(password));
			Thread.sleep(3000);
			loginpage.clickonsignin();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void jsclick(WebElement ele, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", ele);
	}

	public void selectOptionInDropDownUsingIteration(List<WebElement> dropDownOptions, String optionToBeSelected) {
		boolean isFound = false;
		for (WebElement option : dropDownOptions) {
			if (option.getText().equals(optionToBeSelected)) {
				option.click();
				isFound = true;
				break;
			}
		}
		if (!isFound)
			System.out.println("No matching option found.");
	}

	public void selectOptionUsingValue(WebDriver driver, String valueToBeSelected) {
		String customLoc = "//option[text()='" + valueToBeSelected + "']";
		driver.findElement(By.xpath(customLoc)).click();
	}

	public void selectByVisibleText(WebDriver driver, WebElement element, String valueByText) {
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			// js2.executeScript("windows.scrollBy(0,500), ");
			// js.executeScript("arguments[0].scrollIntoView(true)", element);
			Select s = new Select(element);
			s.selectByVisibleText(valueByText);
			action.click().build().perform();
			Log.info(element + " - Element selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Log.info(element + " - Element does not Exist");
		}
	}

	public void sendKeys(WebDriver driver, WebElement element, int timeout, String value) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(Keys.CONTROL + "A");
			//Thread.sleep(1000);
			element.sendKeys(value);
		} catch (Exception e) {
			Log.debug("until, Exception Error message : " + e);
		}
	}

	public void inputText(WebDriver driver, String fieldPropertyNameName, int timeout, String value) {
		try {

			WebElement element = driver
					.findElement(By.xpath("//input[contains(@aria-label='" + fieldPropertyNameName + "')]"));
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(Keys.CONTROL + "A");
			Thread.sleep(2000);
			element.sendKeys(value);
		} catch (Exception e) {
			Log.debug("until, Exception Error message : " + e);
		}
	}

	public void selectText(WebDriver driver, String fieldName, String valueByText) {
		try {
			WebElement element = driver.findElement(By.xpath("//select[@aria-label='" + fieldName + "']"));
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			// js2.executeScript("windows.scrollBy(0,500), ");
			// js.executeScript("arguments[0].scrollIntoView(true)", element);
			Select s = new Select(element);
			s.selectByVisibleText(valueByText);
			action.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOn(WebDriver driver1, WebElement element, int timeout) {
		new WebDriverWait(driver1, timeout).until(ExpectedConditions.elementToBeClickable(element));
		Log.info("user click on element : " + element);
		// action.click(element).build().perform();
		element.click();
	}

	public static void switchToNewWindow(WebDriver driver) {

		for (String winHandle : driver.getWindowHandles()) {
			Wait.untilPageLoadComplete(driver, 2000);
			driver.switchTo().window(winHandle);
		}
		Wait.untilPageLoadComplete(driver, 2000);
		driver.manage().window().maximize();
		Wait.untilPageLoadComplete(driver, 2000);
	}

	public String getTooltip(WebDriver driver1, WebElement element, int timeout) {
		new WebDriverWait(driver1, timeout).until(ExpectedConditions.visibilityOf(element));
		return element.getAttribute("title");

	}

	public List<WebElement> getchildElements(WebDriver driver1, WebElement parentElement, int timeout) {
		new WebDriverWait(driver1, timeout).until(ExpectedConditions.visibilityOf(parentElement));
		return parentElement.findElements(By.xpath("./child::*"));
	}

	public Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {

			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			// ExtentCucumberAdapter.getCurrentStep().info(fnfe);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			// ExtentCucumberAdapter.getCurrentStep().info(ioe);
		} finally {
			fis.close();
		}
		return prop;
	}

	public static void generateAllureReport() {
		String pattern = "dd-MM-yyyy_HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String reportfolder = "allure-report_" + simpleDateFormat.format(new Date());
		executeShellCmd("allure generate allure-results");
		executeShellCmd("mv allure-report " + reportfolder);
		executeShellCmd("cp -R src/main/resources/config/allure-2.18.1 " + reportfolder);
		// executeShellCmd("cp src/main/resources/config/open_report_mac.sh
		// "+reportfolder);
		// executeShellCmd("cp src/main/resources/config/open_report_windows.bat
		// "+reportfolder);
	}

	public static void executeShellCmd(String shellCmd) {
		try {
			Process process = Runtime.getRuntime().exec(shellCmd);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in Executing the command " + shellCmd);
		}
	}

	public void lockedUnlocked(DataTable datatable) {

		List<List<String>> actual = datatable.cells();
		System.out.println("Size :" + datatable.asList().size());
		try {
			for (int j = 1; j <= datatable.asList().size(); j++) {
				List<WebElement> field = driver.findElements(By.xpath("//label[text()='" + actual.get(0).get(j)
						+ "']/ancestor::div[contains(@id,'FieldSectionItemContainer')]"));
				if (!field.isEmpty()) {
					// Checking for locked fields
					List<WebElement> locked = driver.findElements(By.xpath("//label[text()='" + actual.get(0).get(j)
							+ "']/ancestor::div[contains(@id,'FieldSectionItemContainer')]/div/div[contains(@id,'locked')]"));
					if (!locked.isEmpty())
						System.out.println("Locked field :" + actual.get(0).get(j));
					else
						System.out.println("Unlocked field :" + actual.get(0).get(j));
				}
			}

		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurered");
		}
	}

}