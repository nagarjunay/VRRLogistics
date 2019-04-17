package com.test.automation.VRRLogistics.dashboard;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.automation.VRRLogistics.excelReader.Excel_Reader;
import com.test.automation.VRRLogistics.loginpage.TC001_Verifylogin;
import com.test.automation.VRRLogistics.testBase.TestBase;
import com.test.automation.VRRLogistics.uiActions.LoginPage;
import com.test.automation.VRRLogistics.uiActions.Master_DashBoard;

public class TC002_MasterOption extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC002_MasterOption.class.getName());
	LoginPage loginpage;
	Master_DashBoard dashboard;

	@DataProvider
	public Object[][] getDataFromExcel() throws Exception {
		Object[][] data = Excel_Reader.read_excel("Master");// sheet name
		return data;
	}

	
	@BeforeClass
	@Parameters("Browser_Name")
	public void setUp(String Browser_Name) throws IOException {
		init(Browser_Name);
		
	}

	@Test(dataProvider = "getDataFromExcel")
	public void masterOption(String loginid, String password, String runMode) throws Exception {
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
