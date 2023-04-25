package com.sat.testUtil;
//import com.sat.testUtil.Log;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait{
	static int TIME_OUT = 50;

	public static void untilPageLoadComplete(WebDriver driver) {
		untilPageLoadComplete(driver, TIME_OUT);
	}

	public static void untilPageLoadComplete(final WebDriver driver, int timeoutInSeconds) {
		until(driver, new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver d) {
				Boolean isPageLoaded = (Boolean) "complete"
						.equals(((JavascriptExecutor) driver).executeScript("return document.readyState"));
				if (!isPageLoaded)
					Log.debug("Document is loading");
				return isPageLoaded;
			}
		}, timeoutInSeconds);
	}

	public static void waitUntilElementVisible(WebDriver waitdriver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(waitdriver, 30);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition) {
		until(driver, waitCondition, TIME_OUT);
	}

	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, int timeoutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			Log.debug("until, Exception Error message : " + e);
		}
	}



	public static void elementToBeClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
		try {
			new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			Log.debug("until, Exception Error message : " + e);
		}
	}
	
	public static void implicitwait() throws InterruptedException
	{
		try {
		Thread.sleep(3000);}
		catch(Exception e)
		{
			System.out.print("Time out Exception"+e);
		}
	}
}
