package net.fulcrum.Pages.iOS;

import org.openqa.selenium.*;
import net.fulcrum.Drivers.iOSMobilityDriver;



public class MainMenuPage extends iOSMainPage {

	protected static By profilesButton = By.name("Profiles");
	protected static By validationsButton = By.name("retina 0120 document");
	protected static By batchButton = By.name("retina 0007 layers");
	protected static By viewInventoryButton = By.name("retina 0006 eye");
	protected static By viewBatchRecords = By.name("View Batch Records");

	public MainMenuPage (iOSMobilityDriver driver) {
		this.driver = driver;
	}

	public void clickViewInventory () {
		driver.click(viewInventoryButton);
	}

	public void clickValidationsButton () {
		driver.click(validationsButton);
	}

	public void clickBatchButton () {
		driver.click(batchButton);
	}

	public void clickViewBatch() {
		driver.click(viewBatchRecords);
	}

}
