package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver ldriver) {
		this.driver = ldriver;	
	}

	@FindBy(name = "email")
	WebElement uname;
	@FindBy(name = "password")
	WebElement pasword;
	@FindBy(xpath = "//div[text()='Login']")
	WebElement login_button;

	public void loginToCRM(String username, String password) {

		uname.sendKeys(username);
		pasword.sendKeys(password);
		login_button.click();

	}

}
