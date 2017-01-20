package net.fulcrum.Pages.iOS;

import org.openqa.selenium.*;
import net.fulcrum.Drivers.iOSMobilityDriver;

public class AddConnectionPage extends iOSMainPage {
	
	protected static By nameField = By.xpath("//UIATextField[1]");
	protected static By hostField = By.xpath("//UIATextField[2]");
	protected static By portField = By.xpath("//UIATextField[3]");
	protected static By sslToggle = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIASwitch[1]");
	protected static By saveButton = By.name("Save");
    protected static By cancelButton = By.name("Cancel");


	public AddConnectionPage (iOSMobilityDriver driver) {
		this.driver = driver;
	}

	public void enterName(String name)
	{
		driver.sendText(nameField, name);
	}

	public void enterHost(String host)
	{
		driver.sendText(hostField, host);
	}
	public void enterPort(String port)
	{
		driver.sendText(portField, port);
	}

	public void toggleSSL()
	{
		driver.click(sslToggle);
	}

	public void clickSave()
	{
		driver.click(saveButton);
	}

    public void clickCancel()
    {
		driver.click(cancelButton);
    }
}
