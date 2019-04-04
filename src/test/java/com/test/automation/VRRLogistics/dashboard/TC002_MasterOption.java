package com.test.automation.VRRLogistics.dashboard;

import java.io.IOException;


import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.VRRLogistics.excelReader.Excel_Reader;
import com.test.automation.VRRLogistics.testBase.TestBase;
import com.test.automation.VRRLogistics.uiActions.LoginPage;
import com.test.automation.VRRLogistics.uiActions.Master_DashBoard;

public class TC002_MasterOption extends TestBase{
	LoginPage loginpage;
	Master_DashBoard dashboard;

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
	public void verifyLogin(String loginid, String password, String runMode) throws Exception {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("User marked this not to run");
		}
		log("=========>Started Master-->Admin Module");
		loginpage = new LoginPage(driver);
		loginpage.loginToApplication(loginid, password);
		dashboard = new Master_DashBoard(driver);
		dashboard.Master();
		dashboard.Verifying_TotalNum_Of_Col_Rows();
		log("=========>Finished Master-->Admin Module");
	}

}
