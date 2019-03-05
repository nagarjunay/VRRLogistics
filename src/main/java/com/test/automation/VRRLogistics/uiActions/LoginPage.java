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

	@FindBy(xpath = "//*[@id=\"username\"]")
	WebElement loginId;

	@FindBy(xpath = "//*[@id=\"password\"]")
	WebElement loginPassword;

	@FindBy(xpath = "/html/body/div[2]/form[1]/div[6]/button")
	WebElement submitButton;

	@FindBy(xpath = "/html/body/div[2]/form[1]/div[2]/span")
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
		expliciteWait(submitButton, 20);
		submitButton.click();
		log("Clicked on submit button is" + submitButton.toString());
		test.log(LogStatus.INFO, "Clicked on submit button button");
	}

	public boolean getLoginSuccess() {

		try {
			driver.findElement(By.xpath("/html/body/div[2]/form[1]/div[2]/span")).isDisplayed();
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
