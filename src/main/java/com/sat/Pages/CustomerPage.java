package com.sat.Pages;

import java.awt.Desktop.Action;
import java.util.List;
import com.sat.testUtil.Wait;
import com.sat.testbase.TestBase;

import io.cucumber.datatable.DataTable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerPage {

	TestBase testbase = new TestBase();
	WebDriver driver= TestBase.getDriver();
	Actions actions= new Actions(driver);
	
	@FindBy(xpath = "//div[@title='Blocks']")
	WebElement blocks;
	@FindBy(xpath = "//div[@title='Neighborhoods']")
	WebElement neighborhoods;
	@FindBy(xpath = "//div[@title='Schemes']")
	WebElement schemes;
	@FindBy(xpath = "//div[@title='Contacts']")
	WebElement contact;
	@FindBy(xpath = "//div[@title='Data Protection']")
	WebElement dataprotection;
	@FindBy(xpath = "//div[@title='Tenancies']")
	WebElement tenancies;
	@FindBy(xpath = "//div[@title='Knowledge Articles']")
	WebElement knowledgeArticles;

	@FindBy(xpath = "//div[@title='Accounts']")
	WebElement account;
	@FindBy(xpath = "//span/i[contains(@class,'Button-menuIcon')]") // *[text()='Accounts Being Followed']")
	WebElement dropdown;
	@FindBy(xpath = "//div[@aria-label='Select a queue filter']")
	WebElement selectQueueFilter;
	@FindBy(xpath = "//*[text()='All Accounts']")
	WebElement allaccount;
	@FindBy(xpath = "//input[@type='text']")
	WebElement searchtextbox;
	@FindBy(xpath = "//span[contains(@class,'Cancel')]")
	WebElement cancelSearch;
	
	@FindBy(xpath = "//span[contains(@class,'DropdownArrow')]")
	WebElement caseDropDown;

	@FindBy(xpath = "//button[@title='Start search']") // "//button[@id='quickFind_button_1']")
	WebElement searchicon;
	@FindBy(xpath = "//button[@id='searchLauncher-button']") // "//button[@id='quickFind_button_1']")
	WebElement globalSearch;
	@FindBy(xpath="//div[@aria-label='Select a queue filter']")
	WebElement queueFilterDropDown;
	
	public WebElement selectQueueFilterDropdown() {
		return queueFilterDropDown;
		
	}
	public boolean isElementPresent(By by) {
		List<WebElement> ele = driver.findElements(by);
		if (ele.isEmpty())
			return false;
		else
			return true;

	}

	public CustomerPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public void localSearch(DataTable datatable) {
		int i, j;
		List<List<String>> data = datatable.cells();

			for (i = 0; i < 1; i++) {
				for (j = 0; j < 3; j++) {
					
					searchtextbox.sendKeys(data.get(i).get(j).trim(), Keys.ENTER);
					Wait.untilPageLoadComplete(driver);
							try {	
							if (data.get(i).get(j).trim().contains("*")) 
							
								Wait.waitUntilElementVisible(driver, searchText(data.get(i).get(j).trim().replace("*", "")));
							else
								Wait.waitUntilElementVisible(driver, searchText(data.get(i).get(j).trim()));
							}
							catch (Exception e) {
								System.out.println("Exception :" + e);
							}
					Wait.untilPageLoadComplete(driver);
					verifysearchresult(data.get(i).get(j).trim());
					cancelSearch.click();
					Wait.untilPageLoadComplete(driver);
				}}}
					
		public void verifyleftsidepane() throws InterruptedException {
		account.click();
		Thread.sleep(2000);
		
		Wait.untilPageLoadComplete(driver);
		blocks.click();
		isElementPresent(By.xpath("//div[@title='Blocks']"));
		Wait.untilPageLoadComplete(driver);
		Thread.sleep(2000);

		neighborhoods.click();
		isElementPresent(By.xpath("//div[@title='Neighborhoods']"));
		Wait.untilPageLoadComplete(driver);
		Thread.sleep(2000);
		schemes.click();
		isElementPresent(By.xpath("//div[@title='Schemes']"));
		Wait.untilPageLoadComplete(driver);
		Thread.sleep(2000);
		contact.click();
		isElementPresent(By.xpath("//div[@title='Contacts']"));
		Wait.untilPageLoadComplete(driver);
		Thread.sleep(2000);
		dataprotection.click();
		isElementPresent(By.xpath("//div[@title='Data Protection']"));
		Wait.untilPageLoadComplete(driver);
		Thread.sleep(2000);
		tenancies.click();
		isElementPresent(By.xpath("//div[@title='Tenancies']"));
		Wait.untilPageLoadComplete(driver);
		Thread.sleep(2000);

		knowledgeArticles.click();
		isElementPresent(By.xpath("//div[@title='Knowledge Articles']"));
		Wait.untilPageLoadComplete(driver);
		Thread.sleep(2000);
	}

	public void entity(String tab) {
		try {
			Thread.sleep(1000);
			actions.click(driver.findElement(By.xpath("//div[@title='" + tab + "']"))).build().perform();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickonDropdownList() throws InterruptedException {
		Thread.sleep(1000);
		dropdown.click();
	}
	
	public void clickonQueueFilter() {
		selectQueueFilter.click();
	}
	
	public void clickonCaseDropdownList() {
		caseDropDown.click();
	}
	public void clickonAllAccount(String option) {
		Wait.untilPageLoadComplete(driver,300);
		Wait.elementToBeClickable(driver, driver.findElement(By.xpath("//*[text()='" + option + "']")), 300);
		Wait.untilPageLoadComplete(driver,300);
		driver.findElement(By.xpath("//*[text()='" + option + "']")).click();
		Wait.untilPageLoadComplete(driver,300);
	}
	public void clickonQueue(String option) {
//		accountpage.clickonQueueFilter();
		driver.findElement(By.xpath("//input[@aria-label='Select a queue filter']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Select a queue filter']")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		driver.findElement(By.xpath("//input[@aria-label='Select a queue filter']")).sendKeys(option);
		Wait.elementToBeClickable(driver, driver.findElement(By.xpath("//li[text()='" + option + "']")), 300);
		driver.findElement(By.xpath("//li[text()='" + option + "']")).click();
	}
	public void Entertextinsearch(String SearchText) throws InterruptedException {
		
		String[] searchtexts = SearchText.split(";");
		for (int i = 0; i < searchtexts.length; i++) {
			searchtextbox.sendKeys(searchtexts[i].trim(), Keys.ENTER);
			Wait.untilPageLoadComplete(driver);
			try {
			if (searchtexts[i].trim().contains("*"))
				Wait.waitUntilElementVisible(driver, searchText(searchtexts[i].replace("*", "").trim()));
			else
				Wait.waitUntilElementVisible(driver, searchText(searchtexts[i].trim()));
			}catch(Exception e)
			{
				System.out.println("Exception" +e+ "has occurred");
			}
			Wait.untilPageLoadComplete(driver);
			verifysearchresult(searchtexts[i].trim());
			cancelSearch.click();
			Wait.untilPageLoadComplete(driver);
		}
		

	}

	public void enterTextInGlobalSearch(String text) throws InterruptedException {
		String[] searchtexts = text.split(";");
		for (int i = 0; i < searchtexts.length; i++) {
			globalSearch.click();
			Wait.untilPageLoadComplete(driver,500);
			searchtextbox.sendKeys(searchtexts[i].trim(), Keys.ENTER);
			Wait.waitUntilElementVisible(driver, searchText(searchtexts[i].trim().replace("*", "")));
			Wait.untilPageLoadComplete(driver,500);
			verifysearchresult(searchtexts[i].trim());
			Wait.untilPageLoadComplete(driver,500);
		}

	}

	public void clickonsearchicon() {
		Wait.untilPageLoadComplete(driver,500);
		searchicon.click();
		Wait.untilPageLoadComplete(driver,500);
	}

	public WebElement searchText(String text) {

		return driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]"));
	}

	
	public void verifysearchresult(String searchtext) {
		By ele;
		if(searchtext.contains("*"))
		 ele = By.xpath("//*[contains(text(),'" + searchtext.replace("*", "") + "')]");
		else
			ele = By.xpath("//*[contains(text(),'" + searchtext+ "')]");
		boolean present = isElementPresent(ele);

		if (present) {
//			Assert.assertTrue(true);
			System.out.println("Search result contains " + searchtext);
		} else {
			System.out.println("Search result not contains " + searchtext);
		}

	}
	public void selectQueueFilter(String filter) {
		WebElement fil = driver.findElement(By.xpath("//div[@aria-label='Select a queue filter']"));
		fil.click();
		driver.findElement(By.xpath("(//*[text()='"+filter+"'])[last()]")).click();
	}

}