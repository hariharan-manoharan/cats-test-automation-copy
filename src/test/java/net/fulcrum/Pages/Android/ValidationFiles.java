package net.fulcrum.Pages.Android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.fulcrum.Drivers.AndroidMobilityDriver;

import java.util.Hashtable;


public class ValidationFiles extends AndroidPageMain {
	
	private Hashtable<String, String> xPathTable = new Hashtable<String,String>();
		
	public ValidationFiles(AndroidMobilityDriver driver)
	{
		this.driver = driver;
	}
	// Header Frame
	protected static By optionsButton = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[3]");
	protected static By deleteAllButton = By.id("net.fulcrum.mobility:id/delete");
	protected static By downloadAllButton = By.id("net.fulcrum.mobility:id/download");	
	protected static By progressIcon = By.id("android:id/progress");
	protected static By backButton  = By.id("android:id/up");
	private final String successMessage = "Validation File download completed";
	
	public void downloadValidationFile(String file) {
		deleteAll();
		driver.verifyTextView("Validation Files");
		driver.ad.scrollToExact(file);
		driver.clickTextView(file);
		driver.verifyTextView(successMessage);
		driver.clickOk();
	}

	public void viewValidationFile(String file) {
		driver.ad.scrollToExact(file);
		driver.clickTextView(file);
		clickBack();
	}

	public void deleteAll(){
		driver.click(deleteAllButton);
	}

	public void clickBack(){
		driver.click(backButton);
	}
}
