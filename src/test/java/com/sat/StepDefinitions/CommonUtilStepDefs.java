package com.sat.StepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sat.Pages.CommonPages;
import com.sat.Pages.ContactForm;
import com.sat.testUtil.Log;
import com.sat.testUtil.Testutil;
import com.sat.testUtil.Wait;
import com.sat.testbase.TestBase;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonUtilStepDefs {

	WebDriver driver= TestBase.getDriver();
	TestBase testbase = new TestBase();
	private CommonPages common = new CommonPages(driver);
	Testutil testUtil = new Testutil(driver);
	public static String output = "";
	private ContactForm contact = new ContactForm(driver);

//	public CommonUtilStepDefs(WebDriver driver) {
//		this.driver = driver;
//		}
	@Then("user selects {string} as {string}")
	public void user_selects_with(String label, String option) throws InterruptedException {
		common.selectLabelWithOption(label, option);
		Wait.untilPageLoadComplete(driver);
	}

	@When("user filters {string} as {string} with {string}")
	public void user_filters_with(String filter, String option, String filterOption) throws InterruptedException {
		user_selects_with(filter, option);
		common.selectFilterWithOption(filterOption);
	}
	
	@Then("user click on save button")
	public void user_click_on_save_button() throws InterruptedException {
		try {
			contact.clickOnSave();
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	@Then("user click on save and close button")
	public void user_click_on_save_and_close_button() throws InterruptedException {
		try {
			contact.clickOnSaveAndClose();
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}
	
	@Then("user click on saveclose button")
	public void user_click_on_saveclose_button() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//li[contains(@id,'queue')]//button[@aria-label='Save & Close']")).click();
			

		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}
	
	@Then("user click on Save and continue button")
	public void Save_and_continue_click() throws InterruptedException
	{
	if (driver.findElements(By.xpath("//span[text()='Save and continue']//parent::button")).size() != 0)
		testUtil.clickOn(driver, driver.findElement(By.xpath("//span[text()='Save and continue']//parent::button")),
				10);
	Thread.sleep(2000);
	}
	
	@Then("user click on Discard changes button")
	public void DiscardChanges_click() throws InterruptedException
	{
	if (driver.findElements(By.xpath("//span[text()='Discard changes']//parent::button")).size() != 0)
		testUtil.clickOn(driver, driver.findElement(By.xpath("//span[text()='Discard changes']//parent::button")),
				10);
	Thread.sleep(2000);
	}

	
	
	@Then("user click on Ignore and Save button")
	public void user_click_on_Ignore_and_Save_button()
	{
		if (driver.findElements(By.xpath("//h1[text()='Scheduling Alerts']")).size() != 0);
		{
			testUtil.clickOn(driver,
					driver.findElement(By.xpath("//span[text()='Ignore And Save']//parent::button")), 10);
		}
	}
	
	@Then("user click on {string}")
	public void user_click_on_(String option) throws InterruptedException {
		try {
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Scrolling down the page till the element is found
			js.executeScript("arguments[0].scrollIntoView();",
					driver.findElement(By.xpath("//*[text()='" + option + "']")));
			Log.info("Option is == " + option);
			Thread.sleep(1000);
			contact.clickOn(option);
			Thread.sleep(1000);
			
			List<WebElement> li = driver.findElements(By.xpath("//button[@aria-label='Confirm']"));
			if (li.size() != 0)
				testUtil.clickOn(driver, driver.findElement(By.xpath("//button[@aria-label='Confirm']")), 1);
			
			Thread.sleep(1000);
			Log.info("user clicked on : " + option);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	@Then("user click on {string} button")
	public void user_click_on_button(String option) throws InterruptedException {
		try {
			Log.info("Option is == " + option);
			Thread.sleep(3000);
			testUtil.clickOn(driver, driver.findElement(By.xpath("//*[text()='" + option + "']")), 10);
			// contact.clickOn(option);
			Thread.sleep(3000);

		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	@And("user clicks on {string}")
	public void user_clicks_on_(String option) throws InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//button[@aria-label='More commands for Action Plan']")).size() != 0) {
				driver.findElement(By.xpath("//button[@aria-label='More commands for Action Plan']")).click();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);
			contact.clicksOn(option);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}
	
	@And("user clicks on locality queue {string}")
	public void user_clicks_on_locality_queue(String string)
	{
		Log.info("User clicked : "+ string);
		
		testUtil.jsclick(driver.findElement(By.xpath("//a[contains(@aria-label,'<"+ string + ">')]")), driver);
	}
	
	@And("user clicks on {string} option")
	public void user_clicks_on_option(String option) throws InterruptedException {
		try {
			Thread.sleep(2000);			
			Wait.untilPageLoadComplete(driver,500);
			Log.info("User clicked : "+ option);
			
			Thread.sleep(2000);		
			testUtil.jsclick(driver.findElement(By.xpath("//*[contains(@aria-label,'" + option + "')]")), driver);
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}
	
	@And("user perform click on {string}")
	public void user_performs_clicks_on_(String option) throws InterruptedException {
		try {			
			Thread.sleep(2000);
			Wait.untilPageLoadComplete(driver,500);
			Log.info("User clicked : "+ option);
			testUtil.jsclick(driver.findElement(By.xpath("//*[contains(@aria-label,'" + option + "')]")), driver);
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	@Then("user click on refresh button")
	public void user_click_on_refresh_button() throws InterruptedException {
		try {
			contact.clickOnRefresh();
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	@Then("User validates the text {string}")
	public void user_validates_text(String text) throws InterruptedException {
		try {
			contact.validateText(text);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	@Then("User validates the text {string} not present")
	public void user_validates_text_not_present(String text) throws InterruptedException {
		try {
			contact.validateTextNotPresent(text);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	@Then("user click on Back button")
	public void user_click_on_back_button() throws InterruptedException {
		try {
			contact.clickOnBack();
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}
}