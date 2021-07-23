package com.test.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.utils.Base;
import com.test.utils.GetProperties;


public class Assignment_1 {

	static WebDriver driver;

	@BeforeTest
	public void setup() {
		driver = Base.getChromeDriver(); // Getting Driver object from Base Class
		GetProperties.loadProperty();    // Loading properties file
		driver.get(GetProperties.facebookUrl); // Getting Base URL from Property file Variable
	}

	@Test
	public void loginFacebook() {
		driver.findElement(By.id("email")).sendKeys(GetProperties.username);  // Getting username from Property file Variable
		driver.findElement(By.id("pass")).sendKeys(GetProperties.password);   // Getting password from Property file Variable
		driver.findElement(By.name("login")).click();
	}

	@Test
	public void postStatus() {
		driver.findElement(By.cssSelector(".gy2v8mqq > .a8c37x1j")).click();
		driver.findElement(By.xpath("//div[@class='_1mf _1mj']")).sendKeys("Hello World");
		driver.findElement(By.cssSelector(".k4urcfbm > .oajrlxb2 > .rq0escxv")).click();
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
