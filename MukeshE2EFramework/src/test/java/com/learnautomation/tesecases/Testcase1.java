package com.learnautomation.tesecases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;

public class Testcase1 extends BaseClass {

	@Test(priority = 1)
	public void loginTest() {
		logger = report.createTest("Login to CRM");

		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		loginpage.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		// Helper.captuteScreenshot(driver);
		logger.pass("Login successfully");

	}
//	@Test(priority = 2)
//	public void logoutTest() {
//		logger=report.createTest("Logout");
//		logger.fail("Logout failed purposefully");
//	}

}
