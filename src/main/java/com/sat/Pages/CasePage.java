package com.sat.Pages;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import com.sat.testUtil.Assertions;
import com.sat.testUtil.Testutil;
import com.sat.testUtil.Wait;
import com.sat.testbase.TestBase;

import io.cucumber.java.en.Then;

public class CasePage {
	WebDriver driver = TestBase.getDriver();
	TestBase testbase = new TestBase();

	Testutil testutil = new Testutil(driver);
	Actions action = new Actions(driver);
	Assertions assertion = new Assertions(driver);

	public CasePage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//*[text()='Assign']/parent::button")
	WebElement assignButton;

	public By initialVictimQuestions = By.xpath(
			"//h2[text()='INITIAL VICTIM ASSESSMENT QUESTIONS']/parent::section/following-sibling::div/div/div//select");

	public String getOwnerFromTheCreatedcase() {
		WebElement ele = driver
				.findElement(By.xpath("//*[contains(@id,'ownerid')][contains(@id,'selected_tag_text')]"));
		return ele.getText();
	}

	public String getQueueFromTheCreatedcase() {
		WebElement ele = driver
				.findElement(By.xpath("//*[contains(@id,'queueid')][contains(@id,'selected_tag_text')]"));
		return ele.getText();
	}

	public String getCaseNumber() {
		WebElement ele = driver.findElement(
				By.xpath("//input[@aria-label='Case Number'][contains(@id,'ticketnumber')][contains(@id,'text')]"));
		return ele.getAttribute("title");
	}

	public void fillDetails(String field, String value) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath("//input[contains(@aria-label,'" + field + "')]")));
			driver.findElement(By.xpath("//input[contains(@aria-label,'" + field + "')]")).click();
			// Wait.untilPageLoadComplete(driver,5);
			if (value.contains("CAT"))
				value = value.split(",")[1].trim();
			driver.findElement(By.xpath("//input[contains(@aria-label,'" + field + "')]")).sendKeys(value);
			action.click();
			Thread.sleep(2000);
			// Wait.untilPageLoadComplete(driver);
//			driver.findElement(By.xpath("//*[text()='" + value + "']")).click();
			if (value.contains("CAT")) {
				Wait.elementToBeClickable(driver, driver.findElement(By.xpath("//li[@aria-label='" + value + "']")),
						70);
				testutil.jsclick(driver.findElement(By.xpath("//li[@aria-label='" + value + "']")), driver);
				Thread.sleep(2000);
//				driver.findElement(By.xpath("//li[@aria-label='" + value + "']")).click();
			} else {
				Thread.sleep(3000);
				testutil.jsclick(driver.findElement(By.xpath("//*[text()='" + value + "']")), driver);
				Thread.sleep(2000);
//				driver.findElement(By.xpath("//*[text()='" + value + "']")).click();
			}
			Wait.untilPageLoadComplete(driver, 5);
//			if(value.contains("Fraud"))
//				driver.findElement(By.xpath("//*[text()='" + value + "']")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement Category(String category) {
		return driver.findElement(By.xpath("//*[contains(@aria-label,'" + category + "')]"));
	}

	public void clickOnAssign() {
		testutil.jsclick(assignButton, driver);
		Wait.untilPageLoadComplete(driver, 5);
	}

	public void validateCase() throws InterruptedException {
		Thread.sleep(3000);
		Wait.waitUntilElementVisible(driver, driver.findElement(By.xpath("//*[contains(text(),'Saved')]")));
		boolean check=getCaseNumber().isEmpty();
		System.out.println("check:"+check);
		assertion.CheckAssertionTrue(!check, "Case number is null");
		//assertion.CheckAssertionNull(check);
	}
	}

