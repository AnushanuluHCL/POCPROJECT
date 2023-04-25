package com.sat.StepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.base.Optional;
import com.sat.Pages.CasePage;
import com.sat.Pages.LoginPage;
import com.sat.testUtil.Assertions;
import com.sat.testUtil.Log;
import com.sat.testUtil.Testutil;
import com.sat.testUtil.Wait;
import com.sat.testbase.TestBase;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;

public class LoginPageSteps {

	TestBase testbase = new TestBase();
	WebDriver driver = TestBase.getDriver();
	LoginPage loginpage = new LoginPage(driver);
	Testutil testUtil = new Testutil(driver);
	Assertions assertion = new Assertions(driver);
	Actions actions = new Actions(driver);
	// public Properties prop;
	String workingDir = System.getProperty("user.dir" + "/src/main/java/com/sat/config/config.properties");

	@Given("User login into D365 page")
	public void user_navigates_to_D365_login_page() throws Exception {
		try {
			System.out.println("D365 Login Page");
			Thread.sleep(2000);
			testbase.UrlLaunch();
			String s = TestBase.prop.getProperty("UserLoginRequired");
			if (s.trim().contentEquals("Yes")) {
				Thread.sleep(2000);
				Wait.untilPageLoadComplete(driver);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("user enters valid username {string}")
	public void user_enters_valid_username(String uid) throws InterruptedException, Exception {
		String userid1 = TestBase.prop.getProperty(uid);
		loginpage.enteruserid(userid1);
		Thread.sleep(3000);
	}

	@And("Click on Next button")
	public void click_on_Next_button() {
		loginpage.clickonNext();
	}

	@When("user enter valid password {string}")
	public void user_enter_valid_password(String password) {
		String pwd = TestBase.prop.getProperty(password);
		loginpage.enterpassword(pwd);
	}

	@And("click on signin")
	public void clicks_on_signin() {
		loginpage.clickonsignin();
	}

	@And("Click on yes")
	public void click_on_yes() {
		loginpage.clickonYes();
	}

	@Then("user is navigated to {string} page")
	public void user_is_navigated_to_page(String exceptedtitle) {
		try {
			String title = loginpage.GetLoginPageTitle();
			assertion.CheckAssertion(exceptedtitle, title);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("user selects App {string}")
	public void user_selects_App(String appname) throws InterruptedException {

		try {
			Thread.sleep(2000);
			driver.switchTo().frame(driver.findElement(By.xpath("//*[@title='AppLandingPage']")));
			Thread.sleep(2000);
			loginpage.GetApp(appname).click();
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
		} catch (Exception E) {
			System.out.println(E);
		}
	}
}
