package com.sat.Pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.sat.testUtil.Assertions;
import com.sat.testUtil.Log;
import com.sat.testUtil.Testutil;
import com.sat.testUtil.Wait;
import com.sat.testbase.TestBase;
import io.cucumber.datatable.DataTable;

public class ContactForm {

	TestBase testbase = new TestBase();
	WebDriver driver = TestBase.getDriver();
	Testutil testutil = new Testutil(driver);
	Actions action = new Actions(driver);
	Assertions assertion = new Assertions(driver);


	@FindBy(xpath = "//ul[@aria-label='Contact Form']/li ")
	WebElement alltab;
	@FindBy(xpath = "//div[@aria-label='Summary']/section")
	WebElement allsection;

	@FindBy(xpath = "//input[@type='text']")
	public WebElement objsearchField;

	@FindBy(xpath = "//li[text()='Summary']")
	WebElement summary;
	@FindBy(xpath = "//li[text()='Correspondence']")
	WebElement correspondence;
	@FindBy(xpath = "//*[text()='New Emergency Contact']")
	WebElement newEmergencyContact;
	@FindBy(xpath = "//input[@aria-label='Name']")
	WebElement ContactName;

	@FindBy(xpath = "//*[text()='New Contact']")
	WebElement newContact;
	@FindBy(xpath = "//span[text()='Discard changes']")
	WebElement discardChanges;
	@FindBy(xpath = "//span[text()='Save and continue']")
	WebElement saveAndContinue;
	@FindBy(xpath = "//*[text()='Save & Close']") // (//*[text()='Save & Close'])[last()]
	WebElement saveAndClose;
	@FindBy(xpath = "//*[text()='Save']")
	WebElement save;
	@FindBy(xpath = "//button[@aria-label='Refresh']")
	WebElement refresh;
	@FindBy(xpath = "//button[@title='Go back']")
	WebElement goBack;

	@FindBy(xpath = "//li[text()='Data Protection']")
	WebElement dataprotection;
	@FindBy(xpath = "//*[text()='DATA PROTECTION DETAILS']")
	WebElement newdataprotDetail;
	@FindBy(xpath = "//*[text()='New Data Protection']")
	WebElement addNewdataprot;

	@FindBy(xpath = "//li[text()='Tenancies']")
	WebElement tenancies;
	@FindBy(xpath = "//*[text()='Active Tenancies']")
	WebElement activeTenanciesDropDown;
	@FindBy(xpath = "//a[@title='Miss J Shepherd']")
	WebElement openTenanciesForm;

	@FindBy(xpath = "//li[text()='Rent Accounts']")
	WebElement rentaccount;
	@FindBy(xpath = "//a[@title='Flat- Flat 25 Odiham Drive, Newbury ,RG14 1GA']")
	WebElement openRentAccountForm;

	@FindBy(xpath = "//li[text()='Conflicts Tab']")
	WebElement conflictstab;
	@FindBy(xpath = "//li[text()='Related']")
	WebElement related;
	@FindBy(xpath = "//div[text()='Flat 25 Odiham Drive, Speen, Newbury, Berkshire, RG14 1GA']")
	WebElement accountHperlink;
	@FindBy(xpath = "")
	WebElement documentUpload;
	@FindBy(xpath = "//button[@title='Create a timeline record.']")
	WebElement addTimeLine;
	@FindBy(xpath = "//button[@id='confirmButton']")
	WebElement confirm;
//	@FindBy(xpath = "//span[contains(@class,'MoreVertical')]")
//	WebElement ;

	// button[@aria-label='More commands for Case']
	// button[@aria-label='More commands for Contact']

	public WebElement verticalItem(String option) {
		return driver.findElement(By.xpath("//button[@aria-label='More commands for " + option + "']"));
	}

	public WebElement details() {
		return driver.findElement(By.xpath("//h2[@title='DETAILS']"));
	}

	private By responseTime = By.xpath("//select[@aria-label='Response Time']");

	private By taskcategory = By.xpath("//select[@aria-label='Task Category']");

	public WebElement taskCategory() {
		return driver.findElement(taskcategory);
	}

	private By taskSubCategory = By.xpath("//select[@aria-label='Task Sub-Category']");

	public WebElement taskSubCategory() {
		return driver.findElement(taskSubCategory);
	}

	private By description = By.xpath("//textarea[@aria-label='Description']");

	public void enterDescription() {
		driver.findElement(description).click();
		driver.findElement(description).sendKeys("text");
		List<WebElement> findResponsetime = driver.findElements(responseTime);
		if (!findResponsetime.isEmpty()) {
			driver.findElement(responseTime).click();
			testutil.selectByVisibleText(driver, driver.findElement(responseTime), "Routine (5 Working Days)");
		}

	}

	public void description() throws Exception {
		Wait.untilPageLoadComplete(driver,500);
		List<WebElement> ele = driver.findElements(description);
//			if (ele.isEmpty()) {
		System.out.println("ele.size " + ele.size() + " currenttime " + System.currentTimeMillis());
		long end = System.currentTimeMillis() + 300000;
		while (driver.findElements(description).size() == 0 && System.currentTimeMillis() < end) {
//		driver.findElement(By.xpath("(//div[contains(@id,'subcategory.fieldControl-Lookup')])[1]")).click();
			System.out.println("ele.size " + ele.size() + " currenttime " + System.currentTimeMillis());
			Actions act = new Actions(driver);
			act.sendKeys(Keys.PAGE_DOWN).build().perform();

			Thread.sleep(1000);
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(description));
			if (!driver.findElements(description).isEmpty()) {
				testutil.jsclick(driver.findElement(description), driver);
				driver.findElement(description).sendKeys("text");
				Wait.untilPageLoadComplete(driver,500);
				Thread.sleep(1000);
			}
		}
//			else
//			{
////				testutil.jsclick(driver.findElement(By.xpath("//*[@aria-label()='Summary']")),driver);
//				while(ele.isEmpty()&&System.currentTimeMillis() < 120000) {
////					driver.findElement(By.xpath("//li[text()='Summary']")).sendKeys(Keys.PAGE_DOWN);
//					
//					Thread.sleep(2000);
//					
//					driver.findElement(By.xpath("(//div[contains(@id,'subcategory.fieldControl-Lookup')])[1]")).click();
//					driver.findElement(By.xpath("(//div[contains(@id,'subcategory.fieldControl-Lookup')])[1]")).sendKeys(Keys.PAGE_DOWN);
//////					Robot robot = new Robot(); 
////			        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//			        Thread.sleep(2000);
//			        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//					
//					((JavascriptExecutor) driver)
//					.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(description));
//					driver.findElement(By.xpath("//*[@aria-label='Originating Neighbourhood, Lookup']")).sendKeys(Keys.PAGE_DOWN);
//					Actions action = new Actions(driver);
//					action.sendKeys(Keys.PAGE_DOWN).build().perform();
		if (!ele.isEmpty()) {
			driver.findElement(description).click();
			driver.findElement(description).sendKeys(Keys.CONTROL + "A");
			driver.findElement(description).sendKeys("text");
		}
//				}
//		}
	}

	/* Locked unlocked fields */
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

	/* AutoRouting */

	public void addTimeLineRecord() {
		Wait.elementToBeClickable(driver, addTimeLine, 30);
		// testutil.jsclick(addTimeLine, driver);
		addTimeLine.click();
		Wait.untilPageLoadComplete(driver,500);
	}

	public WebElement timeLineRecord(String record) {
		WebElement timeLine = driver.findElement(By.xpath("//div[text()='" + record + "']"));
		return timeLine;
	}

	public ContactForm(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public void selectTaskByContact(String contact) throws InterruptedException {
		WebElement record = driver.findElement(By.xpath("(//*[contains(text(),'" + contact + "')])[1]"));
		//WebElement record = driver.findElement(By.xpath("(//*[contains(text(),'" + contact + "')]"));
		testutil.jsclick(record, driver);
		
	}

	public String enteredTime(String contact) {
		WebElement record = driver.findElement(By.xpath("(//*[contains(text(),'" + contact
				+ "')])[1]//ancestor::div[@col-id='title']/following-sibling::div[@col-id='enteredon']//label"));
		return record.getText();
	}

	public void clickOnSave() throws InterruptedException {
		Thread.sleep(3000);
		testutil.jsclick(save, driver);
		Thread.sleep(8000);
		Wait.untilPageLoadComplete(driver,500);
//		WebDriverWait wait = new WebDriverWait(driver, 120);
//		List<WebElement> saving = driver.findElements(By.xpath("//*[@role='alert'][text()='Saving...']"));
//		if (saving.size() != 0)
//			wait.until(ExpectedConditions
//					.invisibilityOf(driver.findElement(By.xpath("//*[@role='alert'][text()='Saving...']"))));
	}

	public void clickOnSaveAndClose() throws InterruptedException {
		Wait.untilPageLoadComplete(driver,500);
		testutil.jsclick(saveAndClose, driver);
		Thread.sleep(1200);
		Wait.untilPageLoadComplete(driver, 300);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		List<WebElement> saving = driver.findElements(By.xpath("//*[@role='alert'][text()='Saving...']"));
		if (saving.size() != 0)
			wait.until(ExpectedConditions
					.invisibilityOf(driver.findElement(By.xpath("//*[@role='alert'][text()='Saving...']"))));

	}

	public void clickOn(String option) throws Exception {
		try {
			Thread.sleep(1000);
			// new WebDriverWait(driver,
			// 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='"
			// + option + "']"))));
			testutil.jsclick(driver.findElement(By.xpath("//*[text()='" + option + "']")), driver);
			Thread.sleep(500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clicksOn(String option) throws InterruptedException {
		Wait.untilPageLoadComplete(driver,500);
		Log.info("User clicked : "+ option);
		testutil.jsclick(driver.findElement(By.xpath("//*[contains(@aria-label,'" + option + "')]")), driver);
		Thread.sleep(2000);
		Wait.untilPageLoadComplete(driver,500);
		if (driver.findElements(By.xpath("//*[text()='Deactivate']/parent::button")).size()!=0)
		{
		action.moveToElement(driver.findElement(By.xpath("//*[text()='Deactivate']/parent::button"))).build().perform();
		action.click(driver.findElement(By.xpath("//*[text()='Deactivate']/parent::button"))).build().perform();
		}
		if (driver.findElements(By.xpath("//*[text()='Activate']/parent::button")).size()!=0)
		{
		action.moveToElement(driver.findElement(By.xpath("//*[text()='Activate']/parent::button"))).build().perform();
		action.click(driver.findElement(By.xpath("//*[text()='Activate']/parent::button"))).build().perform();
		}
		
	}

	public void clicksOnAndSendText(String option, String text) throws InterruptedException {
		Wait.untilPageLoadComplete(driver,500);
		testutil.jsclick(driver.findElement(By.xpath("//*[@aria-label='" + option + "']")), driver);
		driver.findElement(By.xpath("//*[@aria-label='" + option + "']")).sendKeys(text);
		Wait.untilPageLoadComplete(driver,500);
	}

	public void clickOnRefresh() throws InterruptedException {
		Wait.untilPageLoadComplete(driver,500);
		testutil.jsclick(refresh, driver);
		Wait.untilPageLoadComplete(driver,500);
	}

	public void clickOnBack() throws InterruptedException {
		testutil.jsclick(goBack, driver);
		Thread.sleep(2000);
		Wait.untilPageLoadComplete(driver,500);
	}

	public void clickOnAccountHyperlink() throws InterruptedException, AWTException {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].scrollIntoView(true);", accountHperlink);
//		driver.findElement(By.xpath("//*[text()='GENERAL INFORMATION']")).click();
		Thread.sleep(2000);
        // Scrolling down the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath("//label[text()='Last Name']")));

          js.executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath("//label[text()='Email']")));
		//driver.findElement(By.cssSelector("body")).sendKeys(Keys.TAB, Keys.PAGE_DOWN);
		Thread.sleep(2000);
		testutil.jsclick(accountHperlink, driver);
		System.out.println("clicked on accounthperlink");
		Thread.sleep(5000);
		
	}

	public void openForm(String text) throws InterruptedException {

		WebElement form = driver.findElement(By.xpath("//a[@aria-label='" + text + "']"));
		// WebDriverWait wait = new WebDriverWait(driver, 120);
		// wait.until(ExpectedConditions.invisibilityOf(form));
		// wait.until(ExpectedConditions.presenceOfElementLocated(form));
		Wait.elementToBeClickable(driver, form, 300);
		Thread.sleep(5000);
		testutil.jsclick(form, driver);
		Thread.sleep(3000);
		Wait.untilPageLoadComplete(driver,500);

	}

	public void filterOfTimeline() throws InterruptedException {
		// clicking on filter icon
		driver.findElement(By.xpath("//button[@title='No filter applied. Select to open or close filter pane.']"))
				.click();
		Thread.sleep(2000);
		// Selecting Activites checkbox
		WebElement recordType = driver.findElement(By.xpath("//label[text()='Record type']"));
		Wait.elementToBeClickable(driver, recordType, 20);
		recordType.click();
		// testutil.jsclick(recordType,driver);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@aria-label='Record type']//span[contains(text(),'Activities')] ")).click();
		Thread.sleep(2000);
		// Selecting Phone Call checkbox
		driver.findElement(By.xpath("//label[text()='Activity type']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Phone Call')]")).click();
		Thread.sleep(2000);
		// closing on filter icon
		driver.findElement(By.xpath("//button[@title='Close filters']")).click();
		Thread.sleep(2000);
		driver.getPageSource().contains("Phone Call from");
	}

// Data Protection Tab	

	public void verifyheading(String text) {
		System.out.println(newdataprotDetail.getText());
		Assert.assertEquals(newdataprotDetail.getText(), text, "Element is not Displayed");
	}

	public void clickOnNewDataProtection() throws InterruptedException {
		Thread.sleep(5000);
		Wait.untilPageLoadComplete(driver,500);
		testutil.jsclick(addNewdataprot, driver);
		Wait.untilPageLoadComplete(driver,500);
		// discardChanges.click();
		Thread.sleep(2000);
		Wait.untilPageLoadComplete(driver,500);
		try {
			for (int i = 0; i < 5; i++) {
				// Clicking on text of three ---
				driver.findElement(By.xpath("//span[text()='---']")).click();
				Wait.untilPageLoadComplete(driver,500);
				// Clicking on drop down icon
				driver.findElement(By.xpath("(//span[1][@class='wj-glyph-down'])[2]")).click();
				Wait.untilPageLoadComplete(driver,500);
				// Selecting drop down value
				driver.findElement(By.xpath("//div[text()='Pass']")).click();
				Wait.untilPageLoadComplete(driver,500);
				// Clicking on save icon
			}
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			Wait.untilPageLoadComplete(driver,500);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void verifySectiOnOfTab(DataTable datatable) throws InterruptedException {
		Thread.sleep(2000);
		List<List<String>> actual = datatable.cells();
		Thread.sleep(2000);
		List<WebElement> element = driver
				.findElements(By.xpath("//div[@aria-label='" + actual.get(0).get(0) + "']/section//h2"));
		Thread.sleep(2000);
		System.out.println(actual.size());
		try {
			for (int i = 0; i < actual.size(); i++) {
				for (int j = 1; j < element.size() - 1; j++) {
					System.out.println(element.get(j - 1).getText());
					Thread.sleep(2000);
					assertion.CheckAssertion(actual.get(i).get(j), element.get(j - 1).getText());
					//Assert.assertEquals(actual.get(i).get(j), element.get(j - 1).getText(), "Element is not Displayed");
					Thread.sleep(2000);

				}
			}
		} catch (Exception e) {
			System.out.println("EXception :" + e + " has occurered");
		}
	}

	public void Clickoncontactname(String name) throws InterruptedException {
		Thread.sleep(2500);		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(name, Keys.ENTER);
		Wait.untilPageLoadComplete(driver,500);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + name + "']"))));
		//testutil.jsclick(driver.findElement(By.xpath("//*[text()='" + name + "']")), driver);
		action.click(driver.findElement(By.xpath("//*[text()='" + name + "']"))).build().perform();
		Thread.sleep(3000);
		Wait.untilPageLoadComplete(driver,500);
	}

	public void clickOnTab(String tab) {
		try {
			Thread.sleep(2000);
			testutil.clickOn(driver, driver.findElement(By.xpath("//li[text()='" + tab + "']")), 5);
			Wait.untilPageLoadComplete(driver, 500);
		} catch (Exception e) {

		}
	}

	public void clickOnNewEmergencyContact() throws InterruptedException {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		newEmergencyContact.click();
		Thread.sleep(5000);
		Wait.untilPageLoadComplete(driver,500);
		// discardChanges.click();
		ContactName.sendKeys("TestData");
		Wait.untilPageLoadComplete(driver,500);
	}

	/*
	 * public boolean scrollDown(WebElement ele) { boolean flag = false;
	 * while(!flag) { if(!ele.isEnabled()) {
	 * driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
	 * } else flag = true; } return flag;
	 * 
	 * }
	 */
	public void clickOnNewAccount() throws InterruptedException {
		Wait.untilPageLoadComplete(driver,500);
		driver.findElement(By.xpath("//*[text()='New Account']")).click();
		Thread.sleep(3000);
		Wait.untilPageLoadComplete(driver,500);
		driver.findElement(By.xpath("//input[@aria-label='Account Name']")).sendKeys("test");
		Thread.sleep(3000);
		Wait.untilPageLoadComplete(driver,500);
		Wait.untilPageLoadComplete(driver,500);
		driver.findElement(By.xpath("//button[@aria-label='Save and Close']")).click();
		Thread.sleep(6000);
	}

	public void clickOnNewContact() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='MAIN ADDRESS']")).click();
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.TAB, Keys.PAGE_DOWN);
		Wait.untilPageLoadComplete(driver,500);
		newContact.click();
		Wait.untilPageLoadComplete(driver,500);
		WebElement contact = driver.findElement(By.xpath("//input[@aria-label='Contact Type, Lookup']"));
		// List<WebElement> list =
		// driver.findElements(By.xpath("//ul[@aria-label='Lookup results']//li"));
		contact.click();
		Wait.untilPageLoadComplete(driver,500);
		contact.sendKeys("test");
		driver.findElement(By.xpath("//*[text()='test']")).click();
//		driver.findElement(By.xpath("//li[@aria-label='test']")).click();
		Wait.untilPageLoadComplete(driver,500);
		WebElement LastName = driver.findElement(By.xpath("//input[@aria-label='Last Name']"));
		LastName.sendKeys("TestData");
		Thread.sleep(2000);

	}

	public void verifyalltab(DataTable datatable) {
		List<List<String>> actual = datatable.cells();
		List<WebElement> element = driver.findElements(By.xpath("//ul[@aria-label='Contact Form']/li"));
		try {
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < element.size(); j++) {
					System.out.println(element.get(j).getText());
					Assert.assertEquals(actual.get(i).get(j), element.get(j).getText(), "Element is not Displayed");
				}

			}
		} catch (Exception e) {
			System.out.println("EXception :" + e + " has occurered");
		}

	}

	public String getRouteFromTheCreatedTask() {
		WebElement ele = driver.findElement(By.xpath("//*[text()='Owner']/parent::div/div[1]/a"));
		return ele.getText();
	}

	public void selectVerticalSymbolOfQueues(String option, String item) {
		verticalItem(option).click();
		Wait.untilPageLoadComplete(driver,500);
		WebElement itm = driver.findElement(By.xpath("//*[text()='" + item + "']"));
		itm.click();
	}

	public String getQueueItemText(String items) {
		WebElement text = driver.findElement(By.xpath("(//*[text()='" + items + "'])[last()]"));
		return text.getText();
	}

	public void validateText(String text) {
		SoftAssert softassert = new SoftAssert();
		List<WebElement> actualText = driver.findElements(By.xpath("//*[text()='text']"));
		Wait.untilPageLoadComplete(driver,500);
		softassert.assertTrue(!actualText.isEmpty(), "The text " + text + " is present");
	}

	public void clickOnConfirm() {
		Wait.untilPageLoadComplete(driver,500);
		testutil.jsclick(confirm, driver);
		Wait.untilPageLoadComplete(driver,500);

	}

	public void validateTextNotPresent(String text) {
		SoftAssert softassert = new SoftAssert();
		List<WebElement> actualText = driver.findElements(By.xpath("//*[text()='text']"));
		Wait.untilPageLoadComplete(driver,500);
		softassert.assertTrue(actualText.isEmpty(), "The text " + text + " is not present");
	}

}