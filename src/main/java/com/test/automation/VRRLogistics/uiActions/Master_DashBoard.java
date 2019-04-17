package com.test.automation.VRRLogistics.uiActions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.VRRLogistics.testBase.TestBase;

public class Master_DashBoard extends TestBase {

	public static final Logger log = Logger.getLogger(Master_DashBoard.class.getName());

	WebDriver driver;

	@FindBy(xpath = "//ul[@class='page-sidebar-menu']/li/div[@class='sidebar-toggler hidden-phone']")
	WebElement hamburger;

	@FindBy(xpath = "//span[text()='Masters']")
	WebElement master;

	@FindBy(xpath = "//*[@id=\"lnk7\"]/a")
	WebElement admin;

	@FindBy(xpath = "//*[@id=\"lnk166\"]/a")
	WebElement DynamicTripUpload;


	public Master_DashBoard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void Master() throws InterruptedException {
		hamburger.click();
		log("Clicked on hamburger icon id====>>" + hamburger + " and object is" + hamburger.toString());
		test.log(LogStatus.INFO, "Clicked on hamburger icon====>>" + hamburger);
		master.click();
		log("Clicked on master id====>>" + master + " and object is" + master.toString());
		test.log(LogStatus.INFO, "Clicked on master option====>>" + master);
		admin.click();
		log("Clicked on admin id====>>" + admin + " and object is" + admin.toString());
		test.log(LogStatus.INFO, "Clicked on admin option====>>" + admin);
		DynamicTripUpload.click();
		log("Clicked on DynamicTripUpload id====>>" + DynamicTripUpload + " and object is"
				+ DynamicTripUpload.toString());
		test.log(LogStatus.INFO, "Clicked on DynamicTripUpload option====>>" + DynamicTripUpload);

	}

	public void Verifying_TotalNum_Of_Col_Rows() throws Exception {

		List<WebElement> col = driver.findElements(By.xpath(
				"//table[@class='table table-striped table-hover table-coluredheader dataTable']/thead/tr/th[string-length(text()) > 0] "));
		int colCount = col.size();
		log("Total Number of columns count in a table: " + colCount);
		test.log(LogStatus.INFO, "Total Number of columns count in a table==>" + colCount);

		List<WebElement> row = driver.findElements(By.xpath("//tbody[@id='lstBody']/tr"));
		int rowCount = row.size();
		log("Total Number of rows count in a table: " + rowCount);
		test.log(LogStatus.INFO, "Total Number of rows count in a table==>" + rowCount);

		String Before_xpath = "//tbody[@id='lstBody']/tr[";
		String After_xpath = "]/td[3]";

		for (int i = 1; i <= rowCount; i++) {
			String name = driver.findElement(By.xpath(Before_xpath + i + After_xpath)).getText();
			System.out.println(name.toString());
			if (name.contains("RSR1904010000002")) {
				// tbody[@id="lstBody"]/tr[1]/td[1]/input
				driver.findElement(By.xpath("//tbody[@id='lstBody']/tr[" + i + "]/td[1]/input")).click();
			}
		}

	}

	public void Method2_Verifying_TotalNum_Of_Col_Rows() {
		driver.findElement(By.xpath(
				"//a[contains(text(),'RSA1903270000008')]/parent::td//preceding-sibling::td[2]/input[@type='checkbox']"))
				.click();

	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

}
