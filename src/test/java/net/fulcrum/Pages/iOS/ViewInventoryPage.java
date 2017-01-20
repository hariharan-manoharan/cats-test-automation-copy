package net.fulcrum.Pages.iOS;

import org.openqa.selenium.*;
import net.fulcrum.Drivers.iOSMobilityDriver;

public class ViewInventoryPage extends iOSMainPage {

	protected static By locationNameField = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[1]");
	protected static By locationStatusField = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[2]");
	protected static By partcodeField = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[3]");
	protected static By partManuField = By .xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[4]/UIATextField[1]");
	protected static By mfgPartNumberField = By .xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[5]/UIATextField[1]");
	protected static By partDescriptionField = By .xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[6]/UIATextField[1]");
	protected static By searchButton = By.name("Search");
	protected static By mainMenuButton = By.name("Main Menu");
	protected static By cancelButton = By .name("Cancel");

	public ViewInventoryPage (iOSMobilityDriver driver) {
		this.driver = driver;
	}
	
	public void enterLocationName(String location)
	{
		driver.sendText(locationNameField, location);
	}

	public void enterLocationStatus(String locationstatus )
	{
		driver.sendText(locationStatusField, locationstatus);
	}

	public void enterPartCode(String partcode )
	{
		driver.sendText(partcodeField, partcode);
	}

	public void enterPartMenuCode(String partMenu)
	{
		driver.sendText(partManuField, partMenu);
	}

	public void entermfgPartNumber(String mfgpartnumber)
	{
		driver.sendText(mfgPartNumberField, mfgpartnumber);
	}

	public void enterPartDescriptionField(String partDescription)
	{
		driver.sendText(partDescriptionField, partDescription);
	}

	public void clickSearch()
	{
		driver.click(searchButton);
	}

	public void clickMainMenu()
	{
		driver.click(mainMenuButton);
	}

	public void clickCancel()
	{
		driver.click(cancelButton);
	}
}

