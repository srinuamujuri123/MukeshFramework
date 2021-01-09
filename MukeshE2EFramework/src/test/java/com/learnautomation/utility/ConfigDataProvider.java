package com.learnautomation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
	Properties pro;

	public ConfigDataProvider() {

		File src = new File("./Config/Config.properties");
		pro = new Properties();
		try {
			FileInputStream fis = new FileInputStream(src);
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Not able to load the config file" + e.getMessage());
		}
	}

	public String getDataFromConfig(String keyToSearch) {
		return pro.getProperty(keyToSearch);

	}

	public String getBrowser() {
		return pro.getProperty("Browser");
	}

	public String getURL() {
		return pro.getProperty("qaURL");
	}

}
