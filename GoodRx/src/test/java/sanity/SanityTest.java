package sanity;

import org.junit.Test;

import ui.helper.TestHelper;
import ui.page.SearchPrescription;

public class SanityTest extends TestHelper {
	SearchPrescription searchPrescription = new SearchPrescription(driver);

	@Test
	public void verifySerchField() {
		searchPrescription.searchField();
	}

	@Test
	public void clickOnSearch() {
		searchPrescription.searchButtton();
	}

	@Test
	public void verifyMedicare() {
		searchPrescription.medicare();
	}

}
