
package com.test.automation.VRRLogistics.loginpage;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.VRRLogistics.excelReader.Excel_Reader;
import com.test.automation.VRRLogistics.testBase.TestBase;
import com.test.automation.VRRLogistics.uiActions.LoginPage;

public class TC001_Verifylogin extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_Verifylogin.class.getName());

	LoginPage loginpage;

	@DataProvider
	public Object[][] getDataFromExcel() throws Exception
	{
		Object[][] data = Excel_Reader.read_excel("LoginTestData");//sheet name
		return data;
	}

	@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test(dataProvider = "getDataFromExcel")
	public void verifyLogin(String loginid, String password, String runMode) throws Exception {
		if (runMode.equalsIgnoreCase("n"))
		{
			throw new SkipException("User marked this not to run");
		}
		log("=========>Started verify login");
		//test.log(LogStatus.INFO, "");
		loginpage = new LoginPage(driver);
		loginpage.loginToApplication(loginid, password);
		Assert.assertEquals(true, loginpage.getLoginSuccess());
		//getScreenShot("verifyLogin_" + loginid);
		log("=========>Finished verify login");
		
		
	}

}
