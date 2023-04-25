package com.sat.StepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sat.Pages.CasePage;
import com.sat.Pages.CommonPages;
import com.sat.Pages.ContactForm;
import com.sat.Pages.LeadsCreationPage;
import com.sat.testUtil.Log;
import com.sat.testUtil.Testutil;
import com.sat.testUtil.Wait;
import com.sat.testbase.TestBase;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps {
	TestBase testbase = new TestBase();
	WebDriver driver = TestBase.getDriver();

	private LeadsCreationPage accountpage = new LeadsCreationPage(driver);
	private CommonPages common = new CommonPages(driver);
	private CasePage cases = new CasePage(driver);
	private ContactForm cntctform = new ContactForm(driver);
	public Properties prop;
	String workingDir = System.getProperty("user.dir");

	Testutil testUtil = new Testutil(driver);

	// anusha - changed to service from custoemr
	@When("user click on {string} under service")
	public void user_clicked_on_under_service(String tab) {
		try {
			if (!driver.findElements(By.xpath("//button[@aria-label='Discard changes']")).isEmpty())
				driver.findElement(By.xpath("//button[@aria-label='Discard changes']")).click();
			Thread.sleep(2000);
			accountpage.entity(tab);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}
	// anusha - changed to clicked on
	@Then("user clicked on {string}")
	public void user_click_on_(String option) throws InterruptedException {
		try {

			Log.info("Option is == " + option);
			Thread.sleep(3000);
			// common.clickOn(option);
			cntctform.clickOn(option);
			Thread.sleep(3000);
			List<WebElement> li = driver.findElements(By.xpath("//button[@aria-label='Confirm']"));
			if (li.size() != 0)
				testUtil.clickOn(driver, driver.findElement(By.xpath("//button[@aria-label='Confirm']")), 1);
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	// anusha - added by me
	@Then("user filled {string} field as {string}")
	public void user_filled_field_as(String fieldname, String value) throws InterruptedException {
		testUtil.sendKeys(driver, driver.findElement(By.xpath("//input[contains(@aria-label,'" + fieldname + "')]")), 1,
				value);
		//Thread.sleep(1000);
	}

	

//anusha - added by me
	@Then("user validates case number is created")
	public void user_validates_case_number_is_created() throws InterruptedException {
		cases.validateCase();

	}
}