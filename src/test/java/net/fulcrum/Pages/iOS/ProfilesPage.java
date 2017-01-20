package net.fulcrum.Pages.iOS;

import org.openqa.selenium.*;
import net.fulcrum.Drivers.iOSMobilityDriver;

public class ProfilesPage extends iOSMainPage {

	protected static By adminProfile = By.name("ADMIN");
	protected static By logout = By.name("Log Out");
	protected static By profiles = By.name("Profiles");

	public ProfilesPage(iOSMobilityDriver driver) {
		this.driver = driver;
	}

	public void selectAdminProfile() {
		driver.click(adminProfile);
	}

	public void logout() {
		driver.click(logout);
	}

}
