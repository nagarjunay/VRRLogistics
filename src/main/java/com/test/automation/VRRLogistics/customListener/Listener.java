package com.test.automation.VRRLogistics.customListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.test.automation.VRRLogistics.testBase.TestBase;

public class Listener extends TestBase implements ITestListener {

	/*
	 * WebDriver driver; public Listener(WebDriver driver) { this.driver= driver; }
	 */

	public void onTestStart(ITestResult result) {
		Reporter.log("Test is started:" + result.getName());

	}

	public void onTestSuccess(ITestResult result) {

		if(result.isSuccess()){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			
			String methodName = result.getName();

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/VRRLogistics/";
				File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
				
				FileUtils.copyFile(scrFile, destFile);
				
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void onTestFailure(ITestResult result) {
		if(!result.isSuccess()){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			
			String methodName = result.getName();

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/VRRLogistics/";
				File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
				
				FileUtils.copyFile(scrFile, destFile);
				
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		Reporter.log("Test is skipped:" + result.getMethod().getMethodName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		Reporter.log("Test is finished:" + context.getName());

	}

}
