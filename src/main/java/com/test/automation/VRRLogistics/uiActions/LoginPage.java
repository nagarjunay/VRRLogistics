package com.test.automation.VRRLogistics.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.VRRLogistics.testBase.TestBase;

public class LoginPage extends TestBase {

	public static final Logger log = Logger.getLogger(LoginPage.class.getName());

	WebDriver driver;

	@FindBy(xpath = "")
	WebElement loginId;

	@FindBy(xpath = "")
	WebElement loginPassword;

	@FindBy(xpath = "")
	WebElement submitButton;

	@FindBy(xpath = "")
	WebElement Errormessage;

     public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void loginToApplication(String loginid, String password) throws InterruptedException {
		loginId.clear();
		loginId.sendKeys(loginid);
		log("Entered login id====>>" + loginid + " and object is" + loginId.toString());
		test.log(LogStatus.INFO, "Entered login id is====>>" + loginid);
		loginPassword.clear();
		loginPassword.sendKeys(password);
		log("Entered password====>>" + password + " and object is" + loginPassword.toString());
		test.log(LogStatus.INFO, "Entered password is====>>" + password);
		//Thread.sleep(2000);
		expliciteWait(submitButton, 60 );
		submitButton.click();
		log("Clicked on submit button is" + submitButton.toString());
		test.log(LogStatus.INFO, "Clicked on submit button button");
	}

	public boolean getLoginSuccess() {

		try {
			driver.findElement(By.xpath("")).isDisplayed();
			return false;
		} catch (Exception e) {
			return true;
		}

	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

}
