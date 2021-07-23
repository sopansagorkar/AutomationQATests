package com.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {

	public static String facebookUrl;
	public static String username;
	public static String password;
	public static String wallethubUrl;
	public static String wallethubReviewUrl;
	public static String wallethubProfileUrl;
	public static String wallethubUser;
	public static String wallethubPassword;
	public static String wallethubReviewText;

	static Properties properties = new Properties();

	public static void loadProperty() {
		try {
			properties.load(new FileInputStream(new File("config.properties")));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		facebookUrl = properties.getProperty("facebookUrl");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
		wallethubUser = properties.getProperty("wallethubUser");
		wallethubPassword = properties.getProperty("wallethubPassword");
		wallethubUrl = properties.getProperty("wallethubUrl");
		wallethubReviewText = properties.getProperty("wallethubReviewText");
		wallethubReviewUrl = properties.getProperty("wallethubReviewUrl");
		wallethubProfileUrl = properties.getProperty("wallethubProfileUrl");
	}

}
