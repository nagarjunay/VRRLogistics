package com.test.automation.VRRLogistics.dashboard;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.VRRLogistics.excelReader.Excel_Reader;
import com.test.automation.VRRLogistics.testBase.TestBase;
import com.test.automation.VRRLogistics.uiActions.LoginPage;
import com.test.automation.VRRLogistics.uiActions.VerifyingLogo;

public class TC004_VerifyingLogo extends TestBase{
	
	LoginPage loginpage;
	VerifyingLogo logo;
	@DataProvider
	public Object[][] getDataFromExcel() throws Exception {
		Object[][] data = Excel_Reader.read_excel("Master");// sheet name
		return data;
	}

	@BeforeClass
	public void setUp() throws IOException {
		init();
		log.info("Browser opened");
	}

	@Test(dataProvider = "getDataFromExcel")
	public void verifyLogo(String loginid, String password, String runMode) throws Exception 
	{
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("User marked this not to run");
		}
		log("=========>Started Verifying logo");
		loginpage = new LoginPage(driver);
		loginpage.loginToApplication(loginid, password);
		logo = new VerifyingLogo(driver);
		logo.Verifylogo();
		log("=========>Finished Verifying logo");
		
	}

}
