package com.learnautomation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass {

	protected WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setupSuite() {
		Reporter.log("Settingup reports and test is getting ready", true);

		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();

		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/FreeCRM_" + Helper.getcurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);

		Reporter.log("Settings done and  test can start", true);
	}

	@BeforeClass
	public void setup() {
		Reporter.log("trying to start browser and getting application ready", true);
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getURL());
		System.out.println(driver.getTitle());
		Reporter.log("browser and application is up and running", true);
		
	}

	@AfterClass
	public void teardown()  {
		Reporter.log("browser is getting ready to quit", true);
		BrowserFactory.quitBrowser(driver);
		Reporter.log("browser quited successfully and test completed", true);
	}

	@AfterMethod
	public void teardownMethod(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {

			Reporter.log("trying to get scteenshot on failure", true);

			// the below one line will take screenshot on failure and saves it into
			// Screenshot folder
			Helper.captuteScreenshot(driver);

			// the below one line will take screenshot on failure and saves it into Reports
			// folder
			logger.fail("test failed ",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captuteScreenshot(driver)).build());
			
			Reporter.log("taken screenshot on failure", true);
		}

		// these below code will capture the screenshot at success or skip the test time

		/*
		 * else if(result.getStatus() ==ITestResult.SUCCESS) {
		 * Reporter.log("trying to take screenshot on success", true);
		 * logger.pass("Test passed ",MediaEntityBuilder.createScreenCaptureFromPath(
		 * Helper.captuteScreenshot(driver)).build());
		 * Reporter.log("taken screenshot on success", true); }
		 * 
		 * else if(result.getStatus() ==ITestResult.SKIP) {
		 * Reporter.log("trying to take screenshot on skip", true);
		 * logger.skip("Test skipped ",MediaEntityBuilder.createScreenCaptureFromPath(
		 * Helper.captuteScreenshot(driver)).build());
		 * Reporter.log("taken screenshot on skip", true); }
		 */

		report.flush();
	}
}
