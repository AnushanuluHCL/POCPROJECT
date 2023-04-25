package com.sat.TestRunner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

//import com.sat.testbase.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.java.Scenario;


//	@RunWith(Cucumber.class)
	@CucumberOptions(
			features={"src/test/resources/com.sat.Features"},
			glue={"com.sat.StepDefinitions","com.sat.AppHooks"},
			monochrome=true,
			strict = true,
			plugin={"pretty",
					//"json:target/cucumber.json",
					//"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					//"html:target/cucumber-reports/report.html"
					},
			
			  tags= {"@SingleCaseCreation or @MultipleCasesCreation"})
	
	public class TestRunner extends AbstractTestNGCucumberTests{
		

			@Override
			@DataProvider(parallel = false)
			public Object[][] scenarios() {
				return super.scenarios();

			}
		
	
	
		
		/*@BeforeStep
		public void beforeStep() {
		}

		@AfterStep
		public void afterStep() {
		}

		@Before
		public void beforeScenario() {
		}*/

//			@After
//		public void afterScenario(Scenario scenario) throws Exception {
//			TestBase testbase = new TestBase();
//			WebDriver driver= TestBase.getDriver();
//			scenario.attach(Util.takeScreenShot(), "image/png", scenario.getName());
			//testbase.generateAllureReport();
			
		//}
	
	}
	//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//	import cucumber.api.CucumberOptions;
//	import cucumber.api.testng.AbstractTestNGCucumberTests;
//	@CucumberOptions(features="src/test/resources/features",glue="stepDefinitions",tags="@Test01",plugin= {"pretty", "html:target/cucumber-reports" },monochrome=true)

//public class TestRunner {
//    private TestNGCucumberRunner testNGCucumberRunner;
// 
//    @BeforeClass(alwaysRun = true)
//    public void setUpClass() throws Exception {
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//    }
// 
//    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
//    public void feature(CucumberFeatureWrapper cucumberFeature) {
//        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
//    }
// 
//    @DataProvider
//    public Object[][] scenarios() {
//        return testNGCucumberRunner.provideScenarios();
//    }
// 
//    @AfterClass(alwaysRun = true)
//    public void tearDownClass() throws Exception {
//        testNGCucumberRunner.finish();
//    }
//}	
//	
//		
//	
