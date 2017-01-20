package net.fulcrum.Pages.iOS;

import org.openqa.selenium.*;
import net.fulcrum.Drivers.iOSMobilityDriver;

public class LoginPage extends iOSMainPage {
	
	protected static By selectConnectionSettings = By.name("Select connection settings");
	protected static By usernameField = By.name("Username");
	protected static By passwordField = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]");
	protected static By rememberMeCheckBox = By.name("☐");
	protected static By connectButton = By.name("Connect");
    protected static By arrowButton = By.name("right blue 20");
	protected static By infoButton = By.name("More Info");

	public LoginPage (iOSMobilityDriver driver) {
		this.driver = driver;
	}
	
	public void selectConnection () {
		driver.click(selectConnectionSettings);
	}
	
	public void enterUsername(String username) {
		driver.sendText(usernameField, username);
	}
	public void enterPassword(String password) {
		driver.sendText(passwordField, password);
	}

	public void clickConnect() {
		driver.click(connectButton);
	}

    public void clickArrow() {
		driver.click(arrowButton);
    }

    public void clickRememberMe () {
		driver.click(rememberMeCheckBox);
    }

	public void clickInfo () {
		driver.click(infoButton);
	}

}
