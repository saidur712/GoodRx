package ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ui.helper.PageHelper;

public class SearchPrescription extends PageHelper {

	// private By clickOnLoginLink =
	// By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");

	private By clickOnSearchField = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div[2]/div/form/div/div/input");
	private By clickOnSearchButton = By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/form/button");
	private By clickOnMedicareLink = By.xpath("//*[@id=\"app-root\"]/div/div/div/div/div/section/div[2]/div[1]/nav/ul/li[2]/a");

	public SearchPrescription(WebDriver driver) {
		super(driver);
	}

	public void searchField() {
		setField(clickOnSearchField, "zanamivir");
		//TODO
	}
	
	public void searchButtton() {
		hoverOver(clickOnSearchButton);
	}

	public void medicare() {
		clickOnElement(clickOnMedicareLink);
	}
//TODO
}
