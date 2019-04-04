package com.test.automation.VRRLogistics.uiActions;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.VRRLogistics.testBase.TestBase;

public class UserProfile_DropDown extends TestBase 
{
	

	public static final Logger log = Logger.getLogger(UserProfile_DropDown.class.getName());

	public WebDriver driver;
	
	
	@FindBy(xpath = "//*[@id=\"username\"]")
	WebElement loginId;
	
	public UserProfile_DropDown(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void Mouse_over()
	{
		log("Performing mouse over on user profile dropdown");
		test.log(LogStatus.INFO, "Performing mouse over on user profile dropdown");
		mouse_movement(driver, "//li[@class='dropdown user']");
	}
	

	public ArrayList<Object> verify_drop_down()
	{
		log("Verifying dropdown list in user profile");
		test.log(LogStatus.INFO, "Verifying dropdown list in user profile");
		ArrayList<Object> values = dropdown("//ul[@class='dropdown-menu']/li/a[string-length(text()) > 0]");
		return values;
	}
	
	
	
	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

	
	
}
