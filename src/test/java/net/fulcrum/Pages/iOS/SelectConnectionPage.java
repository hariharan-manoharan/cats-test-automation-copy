package net.fulcrum.Pages.iOS;

import org.openqa.selenium.*;
import net.fulcrum.Drivers.iOSMobilityDriver;


public class SelectConnectionPage extends iOSMainPage {
	
	protected static By addButton = By.name("Add");
	protected static By backButton = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]");
    protected static By selectConnectionText = By.name("Select connection");
	protected static By selectConnectionField =By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]");


	public SelectConnectionPage (iOSMobilityDriver driver) {
		this.driver = driver;
	}
	public void clickAdd()
	{
		driver.click(addButton);
	}

	public void clickConnection()
	{
		driver.click(selectConnectionField);
	}

}
