package com.learnautomation.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {
	
	public static String captuteScreenshot(WebDriver driver) {
		
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath=System.getProperty("user.dir")+"/screenshots/FreeCRM_"+getcurrentDateTime()+".png";
		try {
			FileHandler.copy(src, new File(screenshotPath));
			System.out.println("Test Failed Screenshot captured successfully");
		} catch (Exception e) {
			System.out.println("Unable to take screenshot: "+e.getMessage());
		}
		return screenshotPath;
	}
	public static void HandleFrames() {
		
	}
	public static void Alerts() {
		
	}
	
	public static String getcurrentDateTime() {
		Date date= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
		return sdf.format(date);
	}

}
