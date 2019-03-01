package com.test.automation.VRRLogistics.customListener;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;

public class WebEventListener extends AbstractWebDriverEventListener implements WebDriverEventListener {

	public static final Logger log = Logger.getLogger(WebEventListener.class.getName());

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		log(" Element value changed to:" + element.toString());

	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		log(" Clicked on:" + element.toString());

	}

	/*
	 * public void afterFindBy(By by, WebElement element, WebDriver driver) {
	 * log(" Found Element by:" + element.toString());
	 * 
	 * }
	 */

	public void afterNavigateBack(WebDriver driver) {
		log("Navigate back to previous page ");

	}

	public void afterNavigateForward(WebDriver driver) {
		log("Navigate forword to next page ");

	}

	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateTo(String url, WebDriver driver) {
		log("Navigated to:'" + url + "'");

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		log("Value of the:" + element.toString() + "before any change made");

	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		log(" Trying to click on:" + element.toString());

	}

	/*
	 * public void beforeFindBy(By by, WebElement element, WebDriver driver) {
	 * log(" Trying to find Element by:" + element.toString()); }
	 */

	public void beforeNavigateBack(WebDriver driver) {
		log("Navigate back to previous page ");

	}

	public void beforeNavigateForward(WebDriver driver) {
		log("Navigate forword to next page ");

	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		log("Before navigate to:'" + url + "'");
	}

	public void onException(Throwable error, WebDriver driver) {
		log("Exception occured:" + error);

	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);

	}

}
