package com.test.automation.VRRLogistics.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.VRRLogistics.testBase.TestBase;

public class VerifyingLogo extends TestBase {
	
	public static final Logger log = Logger.getLogger(VerifyingLogo.class.getName());

	public WebDriver driver;
	
	
	public VerifyingLogo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void Verifylogo(){
		
		log("Checking logo is present or not");
		test.log(LogStatus.INFO, "Checking logo is present or not");
		Boolean logoPresent = driver.findElement(By.xpath("//a[@class='navbar-brand']/img[@id='CurrentUserLogoImg']")).isDisplayed();
		Assert.assertTrue(logoPresent);
	}
	
	
}
