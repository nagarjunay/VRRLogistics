package com.test.automation.VRRLogistics.dashboard;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.automation.VRRLogistics.excelReader.Excel_Reader;
import com.test.automation.VRRLogistics.testBase.TestBase;
import com.test.automation.VRRLogistics.uiActions.LoginPage;
import com.test.automation.VRRLogistics.uiActions.UserProfile_DropDown;

public class TC003_UserProfile extends TestBase {
	
	public static final Logger log = Logger.getLogger(TC003_UserProfile.class.getName());
	
	LoginPage loginpage;
	UserProfile_DropDown up;
	
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
	public void userProfile(String loginid, String password, String runMode) throws Exception 
	{
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("User marked this not to run");
		}
		log("=========>Started User Profile Dropdown");
		loginpage = new LoginPage(driver);
		loginpage.loginToApplication(loginid, password);
		up = new UserProfile_DropDown(driver);
		up.Mouse_over();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(" Sound Un-Mute");
		list.add(" Change Password");
		list.add(" Full Screen");
		list.add(" Help");
		list.add(" About/Contact Us");
		list.add(" Log Out");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		up = new UserProfile_DropDown(driver);
		ArrayList<Object> drop_down_values =up.verify_drop_down();
		System.out.println("List Values are " +drop_down_values);
		Assert.assertEquals(drop_down_values, list);
		log("=========>Finished User Profile Dropdown");
	}

	

}
