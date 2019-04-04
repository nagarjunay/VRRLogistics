package com.test.automation.VRRLogistics.loginpage;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.VRRLogistics.excelReader.Excel_Reader;
import com.test.automation.VRRLogistics.testBase.TestBase;
import com.test.automation.VRRLogistics.uiActions.ChangePassword;
import com.test.automation.VRRLogistics.uiActions.LoginPage;

public class TC005_ChangePassword extends TestBase {

	LoginPage loginpage;
	ChangePassword cp;
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

	@SuppressWarnings("unused")
	@Test(dataProvider = "getDataFromExcel")
	public void verifyLogo(String loginid, String password, String runMode) throws Exception 
	{
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("User marked this not to run");
		}
		log("=========>Started to click on Change Password option");
		loginpage = new LoginPage(driver);
		loginpage.loginToApplication(loginid, password);
		cp = new ChangePassword(driver);
		cp.Mouse_over();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(" Sound Un-Mute");
		list.add(" Change Password");
		list.add(" Full Screen");
		list.add(" Help");
		list.add(" About/Contact Us");
		list.add(" Log Out");
		ArrayList<Object> drop_down_values =cp.Click_ChangePassword();
		log("=========>Clicked on Change password option");
        Thread.sleep(3000);
        cp.changepassword_validation();
	}
	
}
