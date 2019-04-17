
package com.test.automation.VRRLogistics.loginpage;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.automation.VRRLogistics.excelReader.Excel_Reader;
import com.test.automation.VRRLogistics.loginCredentials.LoginData;
import com.test.automation.VRRLogistics.testBase.TestBase;
import com.test.automation.VRRLogistics.uiActions.LoginPage;

public class TC001_Verifylogin extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_Verifylogin.class.getName());

	LoginPage loginpage;
	

	@DataProvider
	public Object[][] getDataFromExcel() throws Exception {
		Object[][] data = Excel_Reader.read_excel("LoginData");// sheet name
		return data;
	}

	@BeforeClass
	@Parameters("Browser_Name")
	public void setUp(String Browser_Name) throws IOException {
		init(Browser_Name);
		
	}

	@Test(dataProvider = "getDataFromExcel")
	public void verifyLogin(String loginid, String password, String runMode) throws Exception {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("User marked this not to run");
		}
		log("=========>Started verify login");
		loginpage = new LoginPage(driver);
		/*String lcs=LoginData.credentials().get("LoginCredentials1");
		String logininfo[]=lcs.split("_");*/
		loginpage.loginToApplication(loginid, password);
		Assert.assertEquals(false, loginpage.getLoginSuccess());
		log("=========>Finished verify login");
	}

}
