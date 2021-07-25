package com.test.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.utils.Base;
import com.test.utils.GetProperties;

public class Assignment_2 {
	static WebDriver driver;
	WebDriverWait driverWait;
	Actions actions;

	@BeforeTest
	public void setup() {
		driver = Base.getChromeDriver(); // Getting Driver object from Base Class
		GetProperties.loadProperty(); // Loading properties file
		driver.get(GetProperties.wallethubUrl); // Getting Base URL from Property file Variable
		actions = new Actions(driver);
		driverWait = new WebDriverWait(driver, 20);
	}

	@Test
	public void loginWallethub() {
		driver.findElement(By.id("email")).sendKeys(GetProperties.wallethubUser); // Getting username from Property file
																					// Variable
		driver.findElement(By.id("password")).sendKeys(GetProperties.wallethubPassword); // Getting password from
																							// Property file Variable
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
		driverWait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Edit Profile')]"))));

	}

	@Test(dependsOnMethods = "loginWallethub")
	public void writeReview() {
		driver.navigate().to(GetProperties.wallethubReviewUrl); // Getting review URL from Property file
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)"); // Scrolling to Reviews
		actions.moveToElement(driver.findElement(
				By.cssSelector("div.rv.review-action.ng-enter-element > review-star > div > svg:nth-child(4)"))).click()
				.build().perform(); // Mouse over and Clicking on 4 start rating
	
		driver.findElement(By.xpath("//*[@id='reviews-section']//textarea")).click();
		driver.findElement(By.xpath("//*[@id='reviews-section']//textarea"))
				.sendKeys(GetProperties.wallethubReviewText); // Getting review text from property file
		
		driver.findElement(By.xpath("//span[contains(text(),'Select')]")).click();
		driver.findElement(By.cssSelector("write-review > div > ng-dropdown > div > ul > li:nth-child(2)")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Submit')]")).click();
		driverWait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(text(),'Continue')]"))));
		driver.findElement(By.xpath("//div[contains(text(),'Continue')]")).click();
	}

	@Test(dependsOnMethods = "writeReview")
	public void verifyReview() {
		driver.navigate().to(GetProperties.wallethubProfileUrl);
		Assert.assertEquals("I RECOMMEND", driver.findElement(By.xpath("//h2[@class='pr-rec-subtitle']")).getText());
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
