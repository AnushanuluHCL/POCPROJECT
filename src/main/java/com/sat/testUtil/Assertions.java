package com.sat.testUtil;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.sat.testbase.TestBase;

import io.qameta.allure.Allure;

public class Assertions {

	TestBase testbase = new TestBase();
	WebDriver driver = TestBase.getDriver();
	Testutil testutil = new Testutil(testbase.driver);

	public Assertions(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public void CheckAssertion(String Expected, String Actual) throws IOException {

		try {
			if (!Expected.trim().equals(Actual.trim())) {
				System.out.println("checking assertion");
				System.out.println(Expected + "==" + Actual);
				Assert.fail(Expected + " does not exists");
			} else {
				System.out.println("checking assertion");
				System.out.println(Expected + "==" + Actual);
				Allure.getLifecycle()
						.updateStep(stepResult -> stepResult.setStatus(io.qameta.allure.model.Status.PASSED));

				// ExtentCucumberAdapter.getCurrentStep().pass("As expected");

			}
		} catch (Exception e) {
			// ExtentCucumberAdapter.getCurrentStep().info(e);
		}

	}

	public void CheckAssertionTrue(WebElement E, String msg) {
		if (E.isDisplayed() != true) {
			//Allure.getLifecycle().updateStep(stepResult -> stepResult.setStatus(io.qameta.allure.model.Status.FAILED));
			Assert.fail(msg + " does not exists");

		} else {
			// ExtentCucumberAdapter.getCurrentStep().pass("");
			Log.info("pass");
		}
	}

	public void CheckAssertionTrue(Boolean b, String listValue) {
		System.out.println("in CheckAssertionTrue is: " + b);
		try {
			if (!b) {

				// System.out.println(" getCurrentStep = " +
				// ExtentCucumberAdapter.getCurrentStep());

				// Allure.getLifecycle().updateTestCase(stepResult ->
				// stepResult.setStatus(io.qameta.allure.model.Status.FAILED));
				Log.info(listValue + " does not exist in the list");
				Assert.fail(listValue + " does not exist in the list");

				// ExtentCucumberAdapter.getCurrentStep().fail("Expected value doest not
				// match");

			} else {
				// ExtentCucumberAdapter.getCurrentStep().pass("");
				Log.info("pass");
				Log.info(listValue + " exist in the list");
				// Allure.getLifecycle().updateStep(stepResult ->
				// stepResult.setStatus(io.qameta.allure.model.Status.PASSED));

			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void CheckAssertionNull(WebElement E) {
		if (E.getAttribute("innerText") != null) {
			Allure.getLifecycle().updateStep(stepResult -> stepResult.setStatus(io.qameta.allure.model.Status.FAILED));

		} else {
			// ExtentCucumberAdapter.getCurrentStep().pass("");
		}
	}

	public void CheckAssertion(Integer Expected, Integer Actual) throws Exception {
		System.out.println(Expected + "==" + Actual);
		if (Expected != Actual) {
			System.out.println("in if");
			// ExtentCucumberAdapter.addTestStepLog("test");
			System.out.println("Expected is '" + Expected + "' But Actual got as '" + Actual + "'");
			Allure.getLifecycle().updateStep(stepResult -> stepResult.setStatus(io.qameta.allure.model.Status.FAILED));

			System.out.println("Assert failed on :" + Actual);
		} else {
			// ExtentCucumberAdapter.getCurrentStep().pass("");
		}

	}

	public void checkValueExists(String string) {
		Log.info("Fail");
		Log.info(string + " Name does not exist ");
		Assert.fail(string + " name does not exist");
		
	}

	public void CheckAssertionNull(boolean b) {
Assert.assertTrue(b);
	}



}
