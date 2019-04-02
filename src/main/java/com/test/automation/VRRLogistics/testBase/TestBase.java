package com.test.automation.VRRLogistics.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public static WebDriver driver;
	public Properties OR = new Properties();
	public static ExtentReports extent;
	public static ExtentTest test;
	File file;

	/* This method is to select browser and url from the config file */
	public void init() throws IOException {
		loadData();
		selectBrowser(OR.getProperty("browser"));
		getUrl(OR.getProperty("url"));
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	}

	/*
	 * static will load once on runtime, so can generate single extent report for
	 * every test run
	 */
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");
		// extent = new ExtentReports(System.getProperty("user.dir")
		// + "/src/main/java/com/test/automation/VRRLogistics/report/VRRLogistics.html",
		// false);
		extent = new ExtentReports(
				System.getProperty("user.dir") + "/src/main/java/com/test/automation/VRRLogistics/report/VRRLogistics_"
						+ formater.format(calendar.getTime()) + ".html",
				true);
	}

	/* This method loads the data from the config file from main java folder */
	public void loadData() throws IOException {
		file = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\test\\automation\\VRRLogistics\\Config\\config.properties");
		FileInputStream f = new FileInputStream(file);
		OR.load(f);
	}

	/*
	 * This Method will select the different browsers as mentioned in config
	 * properties file
	 */
	public void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			log.info("creating object of" + browser);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			log.info("creating object of" + browser);
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
			log.info("creating object of" + browser);
			driver = new InternetExplorerDriver();

		}

	}

	/* This method will get url and maximizes browser window */
	public void getUrl(String url) {
		log.info("navigating to" + url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	/* This method is used for wait until element found */
	public void expliciteWait(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/* This Method is to capture screenshots to on calling getScreenShot method */
	public void getScreenShot(String name) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "\\src\\main\\java\\com\\test\\automation\\VRRLogistics\\screenshot\\";
			File destFile = new File(
					(String) reportDirectory + name + "-" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/></a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* This method is used to print logs in TestNG reports */
	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

	/*
	 * This Method is used for generate Extent reports with screenshots for
	 * Pass/Fail test cases
	 */
	public void getresult(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " Test Case PASSED ");
			String screen = captureScreen("");
			test.log(LogStatus.PASS, "  Success Screenshot : " + test.addScreenCapture(screen));
		}

		if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP,
					result.getName() + " Test Case SKIPPED and skip reason is:-" + result.getThrowable());
		}

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR,
					result.getName() + " Test case FAILED due to below issues: " + result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.FAIL, "  Failure Screenshot : " + test.addScreenCapture(screen));
		} else

		if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " Test is started");

		}
	}

	/* This Method is used for to capture screenshots in Extent reports */
	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "Screenshot";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = "\\src\\main\\java\\com\\test\\automation\\VRRLogistics\\screenshot\\";
			destFile = new File(
					(String) reportDirectory + fileName + "-" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(srcFile, destFile);
			// This will help us to link screen shot in TestNg report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/></a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}
	
	
	
	// /html/body/div[2]/div/ul/li[3]
	
	
	public static ArrayList<Object> dropdown(String xpathExpression)
	{
		List<WebElement> li = driver.findElements(By.xpath(xpathExpression));
		ArrayList<Object> array_list = new ArrayList<Object>();
		
		for (WebElement webElement : li) 
		{
			array_list.add(webElement.getText());	
		}
		return array_list;
	}
	
	
	public static void mouse_movement(WebDriver driver, String xpathExpression)
	{
		Actions act = new Actions(driver);
		WebElement target = driver.findElement(By.xpath(xpathExpression));
		act.moveToElement(target).build().perform();
	}
	
	
	
	

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		getresult(result);
		test.log(LogStatus.INFO, result.getName() + " Test is Finished");
		test.log(LogStatus.INFO, " Browser Closed");
	}

	@BeforeMethod
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, "Browser Launched");
		test.log(LogStatus.INFO, "Navigated to http://vrr.infotracktelematics.com/itlfmsv12/");
		test.log(LogStatus.INFO, result.getName() + " Test is Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		closeBrowser();
	}

	public void closeBrowser() {
		driver.quit();
		log.info("Browser closed");
		extent.endTest(test);
		extent.flush();
	}

}
