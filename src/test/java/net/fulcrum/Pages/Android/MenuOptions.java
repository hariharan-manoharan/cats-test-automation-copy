package net.fulcrum.Pages.Android;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import org.openqa.selenium.By;

public class MenuOptions extends AndroidPageMain{

	protected By menu = By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/" +
			"android.widget.LinearLayout[1]");

	protected By profiles = By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/" +
			"android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/" +
			"android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[1]");

	protected By routines = By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/" +
			"android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/" +
			"android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[2]");

	protected By locateInventory = By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/" +
			"android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/" +
			"android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[3]");

	protected By validationData = By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/" +
			"android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/" +
			"android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[4]");

	protected By signOut = By.name("Sign out");

	protected By popup = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/" +
			"android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[43]");
	
	public MenuOptions (AndroidMobilityDriver driver) {
		this.driver = driver;
	}
	
	public void selectMenu() {
		driver.click(menu);
		driver.waitForElement(By.name("Menu"), 30);
	}

	public void selectProfiles() {
		driver.click(profiles);
		driver.waitForElement(By.name("Profiles"), 30);
	}

	public void selectRoutines() {
		driver.click(routines);
		driver.waitForElement(By.name("Routines"), 30);
	}

	public void selectLocateInventory() {
		driver.click(locateInventory);
		driver.waitForElement(By.name("Locate Inventory"), 30);
	}

	public void selectValidationData() {
		driver.click(validationData);
		driver.waitForElement(By.name("Validation Files"), 30);
	}

	public void signOut() {
		driver.click(signOut);
		driver.waitForElement(By.name("Connect"), 30);
	}

	public void clickOk() {
		driver.click(popup);
	}
}
