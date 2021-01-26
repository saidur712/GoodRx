package ui.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static ui.helper.Constants.DEFULT_TIMEOUT_SECONDS;

public abstract class PageHelper {

	public WebDriver driver;

	public PageHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void alertAccept(By locator) {
		driver.findElement(locator).click();
		driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

	}

	public void selectByVisibleText(By locator, String value) {
		new Select(driver.findElement(locator)).selectByVisibleText(value);
	}

	public void clickOnElement(By locator) {

		new WebDriverWait(driver, DEFULT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
		driver.findElement(locator).click();
	}

	public void setField(By locator, String value) {

		new WebDriverWait(driver, DEFULT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(value);
	}

	public String getText(By locator) {
		new WebDriverWait(driver, DEFULT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
		return driver.findElement(locator).getText().trim();
	}

	public void hoverOver(By locator) {
		new Actions(driver).moveToElement(driver.findElement(locator)).build().perform();
	}

	public void clickAndSelect(By dropDownElement, String visibleString) {
		if (visibleString != " " || visibleString != null) {
			clickOnElement(dropDownElement);
			clickOnElement(By.cssSelector("[data-value=' " + visibleString.toUpperCase() + " ']"));
		}
	}

	public Map<String, String> getMap(By headerElements, By columnElements) {
		Map<String, String> employeeDetailsMap = new HashMap<String, String>();
		List<String> headerList = getStringList(headerElements);
		List<String> valueList = getStringList(columnElements);
		for (int i = 0; i < headerList.size(); i++) {
			employeeDetailsMap.put(headerList.get(i), valueList.get(i));
		}
		return employeeDetailsMap;
	}

	public List<String> getStringList(By by) {
		List listString = new ArrayList();
		List<WebElement> elements = driver.findElements(by);
		for (WebElement element : elements) {
			listString.add(element.getText());
		}
		return listString;

	}

	public void webTable(By headerElements, By columnElements) {
		WebElement table = driver.findElement(columnElements);
		List<WebElement> trCount = table.findElements(headerElements);
		// System.out.println(trCount.size());
		for (int k = 0; k < trCount.size(); k++) {
			List<WebElement> colCount = table.findElements(headerElements);
			// System.out.println(colCount.size());
			for (int i = 0; i < colCount.size(); i++) {
				// System.out.println(colCount.get(i).getText());
			}
		}
	}
}
