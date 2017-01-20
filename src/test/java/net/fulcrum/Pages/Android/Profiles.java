package net.fulcrum.Pages.Android;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import org.openqa.selenium.By;

public class Profiles extends AndroidPageMain{

	protected By signOutButton = By.name("Sign Out");
	protected By refreshButton = By.name("Refresh");

	public Profiles (AndroidMobilityDriver driver) {
		this.driver = driver;
	}
	
	public void signOut()
	{
		driver.click(signOutButton);
		driver.waitForElement(By.name("Connect"), 30);
	}

	public void refresh()
	{
		driver.click(refreshButton);
		driver.verifyTextView("Profiles");
	}
	
	public void selectProfile(String selectProfile) {
		driver.clickTextView(selectProfile);
		driver.verifyTextView("Routines");
	}
}
