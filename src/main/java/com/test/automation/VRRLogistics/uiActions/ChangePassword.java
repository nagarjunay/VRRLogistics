package com.test.automation.VRRLogistics.uiActions;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.VRRLogistics.testBase.TestBase;

public class ChangePassword extends TestBase {

	public static final Logger log = Logger.getLogger(ChangePassword.class.getName());

	public WebDriver driver;

	@FindBy(xpath = "//div[@class='input-group']/input[@id='currentpassword']")
	WebElement CurrentPassword;
	
	@FindBy(xpath = "//div[@class='input-group']/input[@id='newpassword']")
	WebElement NewPassword;
	
	@FindBy(xpath = "//div[@class='input-group']/input[@id='confirmpassword']")
	WebElement ConfirmPassword;
	
	@FindBy(xpath = "//button[@type='button'][contains(text(),'Change')]")
	WebElement ChangeButton;
	
	@FindBy(xpath = "//button[@class='btn green']")
	WebElement CancelButton;

	
	public ChangePassword(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void Mouse_over() {
		log("Performing mouse over on user profile dropdown");
		test.log(LogStatus.INFO, "Performing mouse over on user profile dropdown");
		mouse_movement(driver, "//li[@class='dropdown user']");
	}

	public ArrayList<Object> Click_ChangePassword() throws Exception {
		log("Clicking on Change password from user profile dropdown");
		test.log(LogStatus.INFO, "Clicking on Change password from user profile dropdown");
	    ArrayList<Object> values = dropdown("//ul[@class='dropdown-menu']/li/a[string-length(text()) > 0]");
		if (values.contains(" Change Password")) 
		{
			driver.findElement(By.xpath("//a[@href='#ChangePassword']")).click();
		}
		return values;	
	}
	
	
	public void changepassword_validation() throws Exception {
		
		CurrentPassword.sendKeys("");
		log("Entered Current Password is====>>" + CurrentPassword);
		test.log(LogStatus.INFO, "Entered Current Password is====>>" + CurrentPassword);
		NewPassword.sendKeys("");
		log("Entered New Password is====>>" + NewPassword);
		test.log(LogStatus.INFO, "Entered New Password is====>>" + NewPassword);
		ConfirmPassword.sendKeys("");
		log("Entered Confirm Password is====>>" + ConfirmPassword);
		test.log(LogStatus.INFO, "Entered Confirm Password is====>>" + ConfirmPassword);
		try {
			ChangeButton.click();
			log("Clicked on Change Button ====>>" + ChangeButton);
			test.log(LogStatus.INFO, "Clicked on Change Button ====>>" + ChangeButton);
		} catch (UnhandledAlertException f) {
		    try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        System.out.println();
		        log("Alert data: " + alertText);
		        test.log(LogStatus.INFO, "Alert data: " + alertText);
		        alert.accept();
		    } catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }
		}
		
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

}
