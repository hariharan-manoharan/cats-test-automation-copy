package net.fulcrum.Pages.Android;

import java.util.NoSuchElementException;


import net.fulcrum.Drivers.AndroidMobilityDriver;
import org.openqa.selenium.By;


public class ConnectionsEdit extends AndroidPageMain {

    protected By saveButton = By.id("net.fulcrum.mobility:id/connections_save");
    protected By connectionNameField = By.id("net.fulcrum.mobility:id/connection_edit_name");
    protected By hostField = By.id("net.fulcrum.mobility:id/connection_edit_host");
    protected By portField = By.id("net.fulcrum.mobility:id/connection_edit_port");
    protected By sslToggle = By.id("net.fulcrum.mobility:id/connection_edit_ssl");

    public ConnectionsEdit (AndroidMobilityDriver driver) {
        this.driver = driver;
    }
	
	public void createConnection(String connectionName, String host, String port, boolean ssl)
	{
		enterConnectionName(connectionName);
		enterHost(host);
		//clearPort(4);
		enterPort(port);
		if (ssl) {
			toggleSSl();
		}
		saveConnection();
	}
	
	public void enterConnectionName(String value) {
		driver.sendKeys(connectionNameField,value);
	}

	public void enterHost(String value) {
		driver.sendKeys(hostField, value);
	}

	public void clearPort(int characters) {
		try {
			element = driver.findElement(portField);
            for (int i = 0; i <= characters; i++) {
                element.clear();
            }
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find text object to clear value");
        }
	}

	public void enterPort(String value) {
		driver.sendKeys(portField, value);
	}

	public void toggleSSl() {
		driver.click(sslToggle);
	}

	public void saveConnection() {
		driver.click(saveButton);
	}
}
