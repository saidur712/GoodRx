package ui.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.base.Strings;

import io.github.bonigarcia.wdm.WebDriverManager;

import static ui.helper.Constants.DEFULT_TIMEOUT_SECONDS;
import static ui.helper.Constants.TIMEOUT_SECONDS_THIRTY;

public class CommonHelper {

	public static WebDriver driver;

	public static WebDriver loadDriver() {
		String driverType = System.getenv("DRIVER_TYPE");
		if (driverType == null || driverType == "") {
			driverType = Constants.DEFULT_DRIVE;
		}
		return loadDriver(driverType);
	}

	public static WebDriver loadDriver(String driverType) {
		if (driverType.equalsIgnoreCase("chrome")) {

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("disable-infobars");
			try {
				// chrome
				System.setProperty("webdriver.chrome.driver", "/Users/saidur/projects/libs/drivers/chromedriverrrrrr");
				driver = new ChromeDriver(chromeOptions);
			} catch (Exception e) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
			}

		} else if (driverType.equalsIgnoreCase("firefox")) {
			// firefox
			System.setProperty("webdriver.gecko.driver", "/Users/saidur/projects/libs/drivers/geckodriver");
			driver = new FirefoxDriver();
		} else if (driverType.equalsIgnoreCase("ie")) {
			// ie
			System.setProperty("webdriver.chrome.driver", "/Users/saidur/projects/libs/drivers/ie");
			driver = new InternetExplorerDriver();
		} else if (driverType.equalsIgnoreCase("safari")) {
			// safari
			System.setProperty("webdriver.chrome  .driver", "/Users/saidur/projects/libs/drivers/safari");
			driver = new SafariDriver();
		} else {
			System.out.println("Please set the driver");
		}
		driver.manage().timeouts().pageLoadTimeout(DEFULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TIMEOUT_SECONDS_THIRTY, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public static String getEnvironment() {
		String defaultEnv = "test";
		String environment = System.getenv("ENVIRONMENT");
		if (StringUtils.isEmpty(environment)) {
			environment = defaultEnv;
			System.out.println("no eve found, using default env" + defaultEnv);
		}
		return environment.toLowerCase();
	}

	public static void sleep(int timeOutMilliseconds) {
		try {
			Thread.sleep(timeOutMilliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String getEnvironmentProperty(String propertyName) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(getEnvironment());
		String property = resourceBundle.getString(propertyName);
		if (Strings.isNullOrEmpty(property)) {
			// TODO
			// fail(propertyName, getEnvironment());
		}
		return property;
	}

	public static String getTheOrderNumber() {

		Connection connection = null;
		String userName = "hr";
		String password = "hr";
		String orderId = "";

		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@cognit.mynetgear.com:1521", userName, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from orders");
			if (resultSet.next()) {
				resultSet.getString("orderId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderId;
	}
}
