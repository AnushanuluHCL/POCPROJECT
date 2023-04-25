package com.sat.StepDefinitions;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.sat.Pages.LeadsCreationPage;
import com.sat.Pages.LoginPage;
import com.sat.testUtil.Assertions;
import com.sat.testUtil.Log;
import com.sat.testUtil.Testutil;
import com.sat.testbase.TestBase;

import io.cucumber.java.en.Then;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LeadCreationLGSteps {
	
	TestBase testbase = new TestBase();
	WebDriver driver = TestBase.getDriver();
	LoginPage loginpage = new LoginPage(driver);
	LeadsCreationPage leadcreationpage= new LeadsCreationPage(driver);
	Testutil testUtil = new Testutil(driver);
	Assertions assertion = new Assertions(driver);
	Actions actions = new Actions(driver);
	public Properties prop;
	String workingDir = System.getProperty("user.dir");

	
	
	@Then("user fill {string} field as {string}")
	public void user_fill_field_as(String string, String Topic) throws InterruptedException {

		leadcreationpage.enterTopic(Topic);
		Thread.sleep(1000);
		
	}

	@Then("user fill {string} field As {string}")
	public void user_fill_field_As(String string, String lastname) throws InterruptedException {
		leadcreationpage.EnterlastName(lastname);
	}

	@Then("user click Save")
	public void user_click() throws InterruptedException {
		Thread.sleep(1000);
		leadcreationpage.clicksave();
	}
	@Then("user click on {string} lead")
	public void user_click_on_lead(String leadName) {
		leadcreationpage.clickonlead(leadName);
	}
	
	@Then("user click {string} bpf stage")
	public void user_click_bpf_stage(String stageStatus) throws InterruptedException{
        Thread.sleep(1000);
		leadcreationpage.clickstagebpf(stageStatus);
		
	}
	@Then("user check the {string} field")
	public void user_check_field(String fieldName) throws InterruptedException {

		leadcreationpage.clickcheckfield(fieldName);
		Thread.sleep(1000);
		
	}
	
	@Then("user click Qualify")
	public void user_click_Qualify() throws InterruptedException {
		leadcreationpage.clickqualify();
	}
	
	
	@Then("user click {string} opportunity")
	public void user_click_on_opportuinity(String oportuinityName) {
		leadcreationpage.clickonopportunity(oportuinityName);
	}
	
	
	@Then("user click on Next Stage button under Develop BPF")
	public void user_click_Next_stage_under_develop() throws InterruptedException {
		leadcreationpage.clicknextstage();
	}
	@Then("user click on Next Stage under Propose BPF")
	public void user_click_Next_stage_under_propose() throws InterruptedException {
		leadcreationpage.clicknextstage();
	}
	@Then("user click on finish")
	public void user_click_finish()  throws InterruptedException {
		leadcreationpage.clickfinish();
	}

	@Then("user validates {string} is showing in the {string} list")
	public void user_validates_Lead_is_showing_in_the_list(String string,String string2) throws InterruptedException {
	
		Thread.sleep(4000);
	//actions.click(driver.findElement(By.xpath("//div[@aria-rowindex='1']//div[@aria-colindex='2']"))).build().perform();
		actions.click(driver.findElement(By.xpath("(//div[@role='button']//i)[1]"))).build().perform();
		Thread.sleep(1000);
		actions.click(driver.findElement(By.xpath("//span[text()='Filter by']"))).build().perform();
	actions.sendKeys(driver.findElement(By.xpath("//input[@aria-label='Filter by value']")),string).build().perform();
	Thread.sleep(1000);
	actions.click(driver.findElement(By.xpath("//span[text()='Apply']"))).build().perform();
	Thread.sleep(2000);
	List<WebElement> foundIt = driver.findElements(By.xpath("//a[@aria-label='"+string+"']"));
	assertion.CheckAssertionTrue(foundIt.size()!=0, string);
	//assertion.CheckAssertionTrue(driver.findElement(By.xpath("//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']")).getAttribute("textContent").contains(string), string);
}
	@Then("user validates {string} under customer")
	public void user_validates_entity_in_the_list(String string){
		List<WebElement> foundIt = driver.findElements(By.xpath("//li[@aria-label='"+string+"']"));
		assertion.CheckAssertionTrue(foundIt.size()!=0, string);
		
	}
	@Then("user validates {string} heading view")
	public void user_validates_heading_view(String heading){
		List<WebElement> foundIt = driver.findElements(By.xpath("//h1[@title='"+heading+"']"));
		assertion.CheckAssertionTrue(foundIt.size()!=0, heading);
	}
	
}
