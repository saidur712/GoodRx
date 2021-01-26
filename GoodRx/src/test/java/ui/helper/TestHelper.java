package ui.helper;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestHelper {

	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();
	public static WebDriver driver;

	@BeforeClass
	public static void startupDiver() {
		driver = CommonHelper.loadDriver("chrome");
		driver.get("https://www.goodrx.com/");
	}

	@AfterClass
	public static void teardownDriver() {

		//CommonHelper.quitDriver();
	}

	public void assertTrue(String message, boolean actual) {
		try {
			assertTrue(message, actual);
		} catch (AssertionError i) {
			errorCollector.addError(i);
		}
	}

	public void assertFalse(String message, boolean actual) {
		try {
			assertTrue(message, actual);
		} catch (AssertionError i) {
			errorCollector.addError(i);
		}
	}

	public void assertEquales(String message, String actual, String expected) {
		try {
			assertEquales(message, actual, expected);
		} catch (AssertionError i) {
			errorCollector.addError(i);
		}
	}

}
